@import <AppKit/CPView.j>
@import "Hotdraw/Figure.j"
@import "Hotdraw/Handle.j"

@implementation ProjectFigure : Figure 
{ 
	CPTextField _label;
	CPColor _backColor;
	CPColor _foreColor;
} 

- (id)initX:(int) x y: (int)y label: (id)aString backColor: (CPColor) aBackColor foreColor: (CPColor) aForeColor
{ 
	_backColor = aBackColor;
	_foreColor = aForeColor;
	var label = [[CPTextField alloc] initWithFrame:CGRectMakeZero()];

	var font = [CPFont systemFontOfSize: 11];
	_label = label;
	[label setStringValue: aString];
	[label setTextColor: _foreColor];
	[label setFont: font];
	[label sizeToFit];
	
	var frame = CGRectMake(x, y, 140, 50);
	self = [super initWithFrame: frame];

	if (self) {
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"topLeft"]];
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"topMiddle"]];
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"topRight"]];

		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"middleLeft"]];
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"middleRight"]];

		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"bottomLeft"]];
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"bottomMiddle"]];
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"bottomRight"]];
		
		//DRAW INTERACTION NAME
		//[label setFrameOrigin:CGPointMake(22, 4)];
		[self addSubview: label];

		var bounds = [self bounds];
		var center = CGPointMake(bounds.size.width / 2, bounds.size.height / 2);
		[label setCenter: center];
		[label setAutoresizingMask: CPViewMinXMargin |
		                            CPViewMaxXMargin |
		                            CPViewMinYMargin |
		                            CPViewMaxYMargin];

		return self;
	}
} 

- (void)drawRect:(CGRect)rect on: (id)context
{
	//if (_selected) {
		CGContextSetAlpha(context, 1);
		[_label setAlphaValue: 1];
	//} else {
	//	CGContextSetAlpha(context, 0.5);
	//	[_label setAlphaValue: 0.5];
	//}
	 
	//DRAW RECTANGLE
	var radius = 6.0;
	CGContextSetFillColor(context, _backColor); 
	CGContextFillRoundedRectangleInRect(context, [self bounds], radius, YES, YES, YES, YES); 

	CGContextSetStrokeColor(context, [self borderColor]); 
	CGContextStrokeRoundedRectangleInRect(context, [self bounds], radius, YES, YES, YES, YES);
}

- (void)select
{ 
	[super select];
	[self setNeedsDisplay: YES];
}

- (void)unselect
{ 
	[super unselect];
	[self setNeedsDisplay: YES];
}

- (CPColor)borderColor
{ 
	return [CPColor colorWithHexString: @"333333"];
}

- (CPColor)handleColor
{ 
	return [self borderColor];
}

- (bool)isSelectable
{ 
	return true;
}
@end