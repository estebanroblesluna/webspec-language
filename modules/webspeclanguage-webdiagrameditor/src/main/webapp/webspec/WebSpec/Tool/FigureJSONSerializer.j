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
@implementation FigureJSONSerializer : Command
{
	CPMutableArray _whitelistClasses;
	id _idMapping;
	id _currentFigureId;
}

- (id) init {
	[super init];
	_whitelistClasses = [CPMutableArray array];
	
	[_whitelistClasses addObject: [WebSpecDrawing class]];
	[_whitelistClasses addObject: [WidgetFigure class]];
	[_whitelistClasses addObject: [InteractionFigure class]];
	[_whitelistClasses addObject: [NavigationFigure class]];
	[_whitelistClasses addObject: [RichBehaviorFigure class]];
	[_whitelistClasses addObject: [ContainerFigure class]];
	[_whitelistClasses addObject: [GeneratorFigure class]];
	
	return self;
}
- (void) execute
{
	var json = [self toJSON: _drawing];
	var diagramId = [_drawing diagramId];
	if (diagramId == nil || diagramId == undefined) {
	  diagramId = -1;
	}
	
	//post to the server using jquery
    $.ajax({
      url: '../service/projects/saveDiagram',
      type: 'POST',
      data: {
        "diagramId": diagramId,
        "diagram": json,
      },
      cache: false,
      success: function(response) {
      }
    });
}

- (id) toJSON: (id) anObject
{
	_idMapping = {};
    _currentFigureId = 0;

	var json = [self basicToJSON: anObject];
	var jsonText = JSON.stringify(json, null, 2);
	return jsonText;
}

- (id) idOf: (id) anObject
{
    if (_idMapping[anObject] != undefined) {
      return _idMapping[anObject];
    } else {
      _currentFigureId = _currentFigureId + 1;
      _idMapping[anObject] = _currentFigureId;
      return _currentFigureId;
    }
}

- (id) basicToJSON: (id) anObject
{
	var rootJSON = new Object();
	//CPLog.info(anObject);

	if ([self isAcceptedFigure: anObject]) {
		//UI
		var frame = [anObject frame];
		
		rootJSON["figureType"] = [anObject className];
		rootJSON["x"]      = frame.origin.x;
		rootJSON["y"]      = frame.origin.y;
		rootJSON["width"]  = frame.size.width;
		rootJSON["height"] = frame.size.height;
		rootJSON["id"]     = [self idOf: anObject];
		
		//CONNECTION 
        if ([anObject isKindOfClass: [Connection class]]) {
          var source = [anObject source];
          var target = [anObject target];
          rootJSON["source"] = [self idOf: source];
          rootJSON["target"] = [self idOf: target];
        }

		//MODEL
		var model = [anObject model];
		if (model != nil) {
			var modelJSON = [self basicToJSON: model];
			rootJSON["model"] = modelJSON;
		}

		//SUBVIEWS
		var subfigures = new Array();
		var index = 0;
		var subviews = [anObject subviews];
		for (var i = 0; i < [subviews count]; i++) { 
		    var figure = [subviews objectAtIndex: i];
			//to avoid failure we check if it is acceptable
			if ([self isAcceptedFigure: figure]) {
				var subfigureAsJSON = [self basicToJSON: figure];
				subfigures[index] = subfigureAsJSON;
				index++;
			}
		}
		if ([subfigures count] > 0) {
			rootJSON["subfigures"] = subfigures;
		}
	} else if ([anObject isKindOfClass: [Model class]]) {
		for (var i = 0; i < [anObject propertiesSize]; i++) { 
		    var name = [anObject propertyNameAt: i];
		    var value = [anObject propertyValueAt: i];
			rootJSON[name] = value;
		}
	} else {
		CPLog.error("Unable to convert: " + [anObject className] + " to JSON");
	}
	
	return rootJSON;
}

- (bool) isAcceptedFigure: (id) anObject
{
	if ([anObject isKindOfClass: [Figure class]]) {
		for (var i = 0; i < [_whitelistClasses count]; i++) { 
		    var aClass = [_whitelistClasses objectAtIndex: i];
			if ([anObject isKindOfClass: aClass]) {
				return true;
			}
		}
		return false;
	} else {
		return false;
	}
}
@end