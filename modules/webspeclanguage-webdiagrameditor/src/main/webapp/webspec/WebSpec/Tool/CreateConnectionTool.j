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
@implementation CreateConnectionTool : Tool
{
	Connection _connection;
	Interaction _initialInteraction;
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

- (void) mouseDown: (CPEvent) anEvent	 
{
	var point = [anEvent locationInWindow];
	var figure = [_drawing figureAt: point];
	var interaction = [_drawing interactionOf: figure];

	if (interaction != nil) {
		var points = [CPMutableArray array];
		[points addObject: [figure center]];
		[points addObject: [figure center]];

		var connection = [[Connection alloc] initWithPoints: points];
		[connection recomputeFrame];
		[_drawing addSubview: connection];

		_connection = connection;
		_initialInteraction = interaction;
	}
}

- (void) mouseDragged:(CPEvent) anEvent
{
	if (_connection != nil) {
		var point = [anEvent locationInWindow];
		point = CGPointMake(point.x - 6, point.y - 6);
		[_connection pointAt: 1 put: point];
	}
}

- (void) mouseUp:(CPEvent) anEvent
{
	if (_connection != nil) {
		var point = [anEvent locationInWindow];
		var figure = [_drawing figureAt: point];
		var endInteraction = [_drawing interactionOf: figure];
		
		if (endInteraction != nil) {
			var connection = [_figureClass source: _initialInteraction target: endInteraction];
			[_drawing addSubview: connection];
		}
		
		[_connection removeFromSuperview];
		_connection = nil;
		_initialInteraction = nil;
		[self activateSelectionTool];
	}
}

@end