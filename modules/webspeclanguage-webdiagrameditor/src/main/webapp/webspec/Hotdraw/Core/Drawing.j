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
@implementation Drawing : CPView 
{
	Tool _currentTool;
	int _gridSize;
	bool _showGrid;
	CPColor _gridColor;
	bool _snapToGrid;
} 

- (id)initWithFrame:(CGRect)aFrame 
{ 
	self = [super initWithFrame:aFrame];
	if (self) {
		_currentTool = [[SelectionTool alloc] initWithDrawing: self];
		_gridSize = 20;
		_showGrid = false;
		_snapToGrid = false;
		_gridColor = [CPColor colorWithHexString: @"F7F0F3"];
		return self;
	}
} 

- (void)drawRect:(CGRect)rect 
{
    var context = [[CPGraphicsContext currentContext] graphicsPort];
	CGContextSetFillColor(context, [CPColor colorWithHexString: @"FEFEFE"]);
	CGContextFillRect(context, rect);
	
	if (_showGrid) {
		CGContextSetLineWidth(context, 0.25);
		for (var p = 0; p <= 3000 ; p = p + _gridSize) {
			[self drawGridLineX: p y: 0 x: p y: 3000 context: context];
			[self drawGridLineX: 0 y: p x: 3000 y: p context: context];
		} 
	}
}

- (void)drawGridLineX: (int) x1 y: (int) y1 x: (int) x2 y: (int) y2 context: context
{
	CGContextMoveToPoint(context, x1, y1);
	CGContextAddLineToPoint(context, x2, y2);
	CGContextSetStrokeColor(context, _gridColor);
    CGContextStrokePath(context);
}

- (Drawing) drawing
{ 
	return self;
}

- (void) clearFigures
{
	[self setSubviews: [CPMutableArray array]];
}

- (void) showGrid:(bool)aBoolean 
{
	_showGrid = aBoolean;
	[self setNeedsDisplay: YES];
}

- (void) snapToGrid:(bool)aBoolean 
{
	_snapToGrid = aBoolean;
}

-(bool)snapToGrid
{
	return _snapToGrid;
}

- (void)gridSize
{
	return _gridSize;
}

- (void)gridSize:(int)gridSize 
{
	_gridSize = gridSize;
	[self setNeedsDisplay: YES];
}

- (void) mouseDown:(CPEvent) anEvent	 
{
	[_currentTool mouseDown: anEvent];
}

- (void) mouseDragged:(CPEvent) anEvent
{
	[_currentTool mouseDragged: anEvent];
}

- (void) mouseUp:(CPEvent) anEvent
{
	[_currentTool mouseUp: anEvent];
}

- (BOOL)acceptsFirstResponder 
{
    return YES;
}

- (void) keyUp: (CPEvent) anEvent
{
	[_currentTool keyUp: anEvent];
}

- (void) keyDown: (CPEvent) anEvent
{
	[_currentTool keyDown: anEvent];
}

- (void)figureAt:(CPPoint)aPoint
{
	var subviews = [self subviews];
	
	for (var i = [subviews count] -1; i >= 0 ; i--) { 
	    var figure = [subviews objectAtIndex:i];
		var containedFigure = [figure figureAt: aPoint];
		if (containedFigure != nil) {
			return containedFigure;
		}
	}
	
	return nil;
}

- (void) tool: (Tool) aTool
{
	_currentTool = aTool;
}

@end