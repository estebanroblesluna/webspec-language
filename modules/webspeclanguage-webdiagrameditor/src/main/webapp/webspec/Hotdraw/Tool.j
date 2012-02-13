@import <Foundation/CPObject.j>


@implementation Tool : CPObject
{
	Drawing _drawing;
}

- (id) initWithDrawing: (Drawing) aDrawing 
{ 
	self = [super init];
	if (self) {
		_drawing = aDrawing;
		return self;
	}
}

- (void) mouseDown:(CPEvent) anEvent	 
{
}

- (void) mouseDragged:(CPEvent) anEvent
{
}

- (void) mouseUp:(CPEvent) anEvent
{
}

- (void) keyUp: (CPEvent) anEvent
{
}

- (void) keyDown: (CPEvent) anEvent
{
}
@end
