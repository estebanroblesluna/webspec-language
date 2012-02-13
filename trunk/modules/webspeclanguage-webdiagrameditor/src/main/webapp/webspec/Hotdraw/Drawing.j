@import <AppKit/CPView.j>
@import "Figure.j"
@import "Tool.j"
@import "SelectionTool.j"

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
		_gridColor = [CPColor colorWithHexString: @"EFEFEF"];
		return self;
	}
} 

- (void)drawRect:(CGRect)rect 
{
    var context = [[CPGraphicsContext currentContext] graphicsPort];
	CGContextSetFillColor(context, [CPColor colorWithHexString: @"FEFEFE"]);
	CGContextFillRect(context, rect);
	
	if (_showGrid) {
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
	CGContextSetLineWidth(context, 0.25);
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

@end