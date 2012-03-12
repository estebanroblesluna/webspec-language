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
@implementation SelectionTool : Tool
{
	CPMutableArray _selectedFigures;
	CPMutableArray _moveableFigures;
	CPMutableArray _pushedMoveableFigures;
	CPDictionary _initialPositions;
	CGPoint _initialDragPoint;
}

- (id) initWithDrawing: (Drawing) aDrawing 
{ 
	self = [super initWithDrawing: aDrawing];
	if (self) {
		_selectedFigures = [CPMutableArray array];
		_moveableFigures = [CPMutableArray array];
		_pushedMoveableFigures = [CPMutableArray array];
		_initialPositions = [CPDictionary dictionary];
		return self;
	}
}

- (void) mouseDown: (CPEvent) anEvent	 
{
	var point = [anEvent locationInWindow];
	var figureUnderPoint = [_drawing figureAt: point];
	
	if (figureUnderPoint == nil) {
		//if no element clear selection
		[self clearSelection];
	} else {
		//if it is a handle and only 1 figure selected then allow moving the handle only
		if ([figureUnderPoint isHandle] && [_selectedFigures count] == 1) {
			[_pushedMoveableFigures addObjectsFromArray: _moveableFigures];
			[_moveableFigures removeAllObjects];
			[_moveableFigures addObject: figureUnderPoint];
			[_initialPositions setObject: [figureUnderPoint frameOrigin] forKey: figureUnderPoint];
		} else {
			if ([figureUnderPoint isSelectable] && !([_selectedFigures containsObject: figureUnderPoint])) {
				//if the element is selectable select it and add in case of ctrl
				[figureUnderPoint select];

				if (([anEvent modifierFlags] & (CPControlKeyMask | CPCommandKeyMask)) == 0) {
					//clear the selection
					[self clearSelection];
				}

				[_selectedFigures addObject: figureUnderPoint];

				if ([_selectedFigures count] == 1) {
					[_drawing selectedFigure: figureUnderPoint];	
				} else {
					[_drawing selectedFigure: nil];
				}
			}

			if ([figureUnderPoint isMoveable] && !([_moveableFigures containsObject: figureUnderPoint])) {
				[_moveableFigures addObject: figureUnderPoint];
				[_initialPositions setObject: [figureUnderPoint frameOrigin] forKey: figureUnderPoint];
			}			
		}
	}
	
	_initialDragPoint = point;
}

- (void) clearSelection
{
	for (var i = 0; i < [_selectedFigures count]; i++) { 
	    var selectedFigure = [_selectedFigures objectAtIndex:i];
		[selectedFigure unselect];
	}
	[_selectedFigures removeAllObjects];
	[_moveableFigures removeAllObjects];
	[_initialPositions removeAllObjects];
	[_drawing selectedFigure: nil];
}

- (void) mouseDragged:(CPEvent) anEvent
{
	if ([_moveableFigures count] > 0) {
    	var newLocation = [anEvent locationInWindow];
		var dragXOffset = newLocation.x - _initialDragPoint.x;
		var dragYOffset = newLocation.y - _initialDragPoint.y;
		
		//move each moveable figure
		for (var i = 0; i < [_moveableFigures count]; i++) { 
		    var moveableFigure = [_moveableFigures objectAtIndex:i];
			var initialFigurePosition = [_initialPositions objectForKey: moveableFigure];

			var newOrigin = CGPointMake(initialFigurePosition.x + dragXOffset, initialFigurePosition.y + dragYOffset);
			if ([_drawing snapToGrid]) {
				var gridSize = [_drawing gridSize];
				newOrigin = CGPointMake(ROUND(newOrigin.x / gridSize) * gridSize, ROUND(newOrigin.y / gridSize) * gridSize);
			}
	    	[moveableFigure translatedBy: newOrigin];
		}
	}
}

- (void) mouseUp:(CPEvent) anEvent
{
	_initialDragPoint = nil;
	
	//if moving a handle
	if ([_moveableFigures count] == 1 && [[_moveableFigures objectAtIndex: 0] isHandle]) {
		var handle = [_moveableFigures objectAtIndex: 0];
		[_moveableFigures removeAllObjects];
		[_moveableFigures addObjectsFromArray: _pushedMoveableFigures];
		[_pushedMoveableFigures removeAllObjects];
		[_initialPositions removeObjectForKey: handle];
	}
	
	//update the initial points
	for (var i = 0; i < [_moveableFigures count]; i++) { 
	    var moveableFigure = [_moveableFigures objectAtIndex:i];
		[_initialPositions setObject: [moveableFigure frameOrigin] forKey: moveableFigure];
	}
}

- (void) keyUp: (CPEvent) anEvent
{
	if (([anEvent keyCode] == CPKeyCodes.F2) && ([_selectedFigures count] == 1)) {
		var currentFigure = [_selectedFigures objectAtIndex: 0];
		if ([currentFigure isEditable]) {
			[currentFigure switchToEditMode];
		}
	}
}
@end
