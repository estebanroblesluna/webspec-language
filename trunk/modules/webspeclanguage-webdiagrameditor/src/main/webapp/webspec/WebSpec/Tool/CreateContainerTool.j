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
@implementation CreateContainerTool : AbstractCreateFigureTool
{
	id _figureClass;
}

+ (id) drawing: (Drawing) aDrawing figure: (id) aFigureClass
{
	var tool = [super drawing: aDrawing];
	[tool figureClass: aFigureClass];
	return tool;
}

- (void) figureClass: (id) aFigureClass
{
	_figureClass = aFigureClass;
}

- (void) createFigureAt: (id) aPoint on: (id) aDrawing
{
	var figure = [aDrawing figureAt: aPoint];
	var widgetContainer = [aDrawing widgetContainerOf: figure];
	if (widgetContainer != nil) {
		var widgetContainerOrigin = [widgetContainer globalToLocal: aPoint];
		//var widgetContainerOrigin = [widgetContainer frameOrigin];
		//TODO corregir translacion
		//aPoint = CGPointMake(aPoint.x - widgetContainerOrigin.x - 4, aPoint.y - widgetContainerOrigin.y - 32);
		var widget = [_figureClass newAt: widgetContainerOrigin];
		[widgetContainer addWidget: widget];
		[self activateSelectionTool];
	}
}
@end
