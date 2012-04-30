/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author "Esteban Robles Luna <esteban.roblesluna@gmail.com>"
 */
@implementation DiagramLoaderCommand : Command
{
	id _diagramId;
}

+ (id) drawing: (Drawing) aDrawing diagramId: (id) anID
{
	var command = [super drawing: aDrawing];
	[command initWithDiagramId: anID];
	return command;
}

- (id) initWithDiagramId: (id) anID
{ 
	_diagramId = anID;
	return self;
}

- (void) execute
{
    $.ajax({
      url: '../service/projects/diagram/' + _diagramId,
      type: 'GET',
      cache: false,
      success: function(response) {
	    [self basicExecute: response];
      }
    });
}

- (void) basicExecute: (id) anObject
{
	//CPLog.info(anObject);
	//[_drawing clearFigures];
	var webSpecDrawing = [self fromJSON: anObject];
	var figures = [webSpecDrawing figures];
	for (var i = 0; i < [figures count]; i++) { 
	    var figure = [figures objectAtIndex: i];
		//CPLog.info("Adding figure to drawing " + figure);
		[_drawing addFigure: figure];
	}
}

- (id) fromJSON: (id) anObject
{
	return [self parseFigure: anObject];
}

- (id) parseFigure: (id) anObject
{
	var result = nil;
	//CPLog.info("Parsing: " + anObject.figureType);
	
	if (anObject.figureType == "WebSpecDrawing") {
		result = [self parseDiagram: anObject]; //DONE
	} else if (anObject.figureType == "InteractionFigure") {
		result = [self parseInteraction: anObject]; //DONE
	} else if (anObject.figureType == "NavigationFigure") {
		result = [self parseTransition: anObject]; //DONE MISSING INTERACTION LOOKUP
	} else if (anObject.figureType == "RichBehaviorFigure") {
		result = [self parseTransition: anObject]; //DONE MISSING INTERACTION LOOKUP
	} else if (anObject.figureType == "ButtonFigure") {
		result = [self parseWidget: anObject]; //DONE
	} else if (anObject.figureType == "CheckBoxFigure") {
		result = [self parseWidget: anObject]; //DONE
	} else if (anObject.figureType == "ComboBoxFigure") {
		result = [self parseWidget: anObject]; //DONE
	} else if (anObject.figureType == "LabelFigure") {
		result = [self parseWidget: anObject]; //DONE
	} else if (anObject.figureType == "LinkFigure") {
		result = [self parseWidget: anObject]; //DONE
	} else if (anObject.figureType == "ListBoxFigure") {
		result = [self parseWidget: anObject]; //DONE
	} else if (anObject.figureType == "RadioButtonFigure") {
		result = [self parseWidget: anObject]; //DONE
	} else if (anObject.figureType == "TextFieldFigure") {
		result = [self parseWidget: anObject]; //DONE
	} else if (anObject.figureType == "PanelContainerFigure") {
		result = [self parseContainer: anObject]; //DONE
	} else if (anObject.figureType == "ListOfContainerFigure") {
		result = [self parseContainer: anObject]; //DONE
	} else if (anObject.figureType == "OneOfManyStringsFigure") {
		result = [self parseGenerator: anObject]; //DONE
	} else if (anObject.figureType == "OneOfManyNumbersFigure") {
		result = [self parseGenerator: anObject]; //DONE
	} else if (anObject.figureType == "OneOfManyArraysFigure") {
		result = [self parseGenerator: anObject]; //DONE
	} else if (anObject.figureType == "RandomStringFigure") {
		result = [self parseGenerator: anObject]; //DONE
	} else if (anObject.figureType == "UniformDistributionFigure") {
		result = [self parseGenerator: anObject]; //DONE
	}

	[result update];

	return result;
}


- (id) parseDiagram: (id) anObject
{
	var drawing = [WebSpecDrawing new];
	[self fill: drawing with: anObject];
	
	var subfigures = [self parseSubfigures: anObject];
	for (var i = 0; i < [subfigures count]; i++) { 
	    var subfigure = [subfigures objectAtIndex: i];
		[drawing addFigure: subfigure];
	}
	
	return drawing;
}

- (id) parseInteraction: (id) anObject
{
	var interactionFigure = [self basicParseFigure: anObject];
	var frame = [self parseFrame: anObject];
	[interactionFigure setFrame: frame];
	var subfigures = [self parseSubfigures: anObject]; //we will have only 1 element with the children of the interaction
	subfigures = [[subfigures objectAtIndex: 0] subviews];
	for (var i = 0; i < [subfigures count]; i++) { 
	    var subfigure = [subfigures objectAtIndex: i];
		[interactionFigure addFigure: subfigure];
	}
	return interactionFigure;
}

- (id) parseTransition: (id) anObject
{
	var figureClass = CPClassFromString(anObject.figureType);
	var transition = [figureClass source: sourceFigure target: targetFigure];
	[self fill: transition with: anObject];
	return transition;
}

- (id) parseContainer: (id) anObject
{
	var container = [self basicParseFigure: anObject];
	var frame = [self parseFrame: anObject];
	[container setFrame: frame];
	var subfigures = [self parseSubfigures: anObject];
	for (var i = 0; i < [subfigures count]; i++) { 
	    var subfigure = [subfigures objectAtIndex: i];
		[container addFigure: subfigure];
	}
	return container;
}

- (id) parseWidget: (id) anObject
{
	var widget = [self basicParseFigure: anObject];
	return widget;
}

- (id) parseGenerator: (id) anObject
{
	var generator = [self basicParseFigure: anObject];
	return generator;
}

- (id) basicParseFigure: (id) anObject
{
	var figureClass = CPClassFromString(anObject.figureType);
	var point = [self parseOrigin: anObject];
	var figure = [figureClass newAt: point];
	[self fill: figure with: anObject];
	return figure;
}

- (void) fill: (Figure) aFigure with: (id) anObject
{
	if (anObject["model"] != nil) {
		var aModelObject = anObject.model;
		var model = [aFigure model];

		[model fireNotifications: NO];
		//JS hack
		for (var property in aModelObject) {
	    	if (aModelObject.hasOwnProperty(property)) {
				var value = aModelObject[property];
				[model propertyValue: property be: value];
			}
		}
		[model fireNotifications: YES];
	}
}

- (CPPoint) parseOrigin: (id) anObject
{
	return CGPointMake(anObject.x, anObject.y);
}

- (CPPoint) parseFrame: (id) anObject
{
	return CGRectMake(anObject.x, anObject.y, anObject.width, anObject.height);
}

- (id) parseSubfigures: (id) anObject
{
	var subfigures = [CPMutableArray array];
	if (anObject["subfigures"] != nil) {
		var figs = anObject["subfigures"];
		for (var i = 0; i < [figs count]; i++) { 
		    var jsonFigure = [figs objectAtIndex: i];
			var figure = [self parseFigure: jsonFigure];
			[subfigures addObject: figure];
		}
	}
	return subfigures;
}
@end