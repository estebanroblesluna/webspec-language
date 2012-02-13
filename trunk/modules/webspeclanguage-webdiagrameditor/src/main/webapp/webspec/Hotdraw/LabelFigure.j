@import <AppKit/CPView.j>
@import "Figure.j"

@implementation LabelFigure : Figure 
{
	CPTextField _textField;
}

+ (LabelFigure) initializeWithTextField: (CPTextField) textField
{
	var label = [[self alloc] initWithFrame: [textField frame] textField: textField];
	return label;
}

- (id) initWithFrame: (CGRect) aFrame textField: aTextField
{ 
	self = [super initWithFrame:aFrame];
	if (self) {
		_textField = aTextField;
		[self addSubview: _textField];
		return self;
	}
}

- (void) figureAt: (CPPoint) aPoint
{
	var frame = [self frame];
	if (CPRectContainsPoint(frame, aPoint)) {
		return self;
	} else {
		return nil;
	}
}