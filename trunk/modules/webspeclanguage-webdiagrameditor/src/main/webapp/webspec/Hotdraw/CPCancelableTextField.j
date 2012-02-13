@import <Foundation/CPObject.j>


@implementation CPCancelableTextField : CPTextField
{
	id _cancelator;
}

- (void) cancelator: (id) aCancelator
{
	_cancelator = aCancelator;
}

- (void) keyDown: (CPEvent) anEvent
{
	[super keyDown: anEvent];
	
	if (_cancelator != nil && [anEvent keyCode] == CPKeyCodes.ESC) {
		[_cancelator cancelEditing];
	}
}
@end
