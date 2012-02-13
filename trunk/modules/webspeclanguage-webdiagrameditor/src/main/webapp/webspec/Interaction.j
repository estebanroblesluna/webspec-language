@import <AppKit/CPView.j>
@import "Hotdraw/Figure.j"
@import "Hotdraw/CompositeFigure.j"
@import "Hotdraw/Handle.j"
@import "Hotdraw/EditorDelegate.j"
@import "Hotdraw/LabelFigure.j"
@import "Widget.j"

@implementation Interaction : Figure 
{ 
	CPTextField _label;
	CPView _widgetContainer;
} 

- (id)initWithFrame:(CGRect)aFrame 
{ 
	self = [super initWithFrame:aFrame];
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
		var label = [[CPTextField alloc] initWithFrame:CGRectMakeZero()];
		[label setStringValue: @"Interaction"];
		[label setTextColor: [CPColor blackColor]];
		[label setFrameOrigin: CGPointMake(22, 4)];
		[label sizeToFit];
		[label setDelegate: self];
		[label setBordered: NO];
		[label setBezeled: NO];
		[self addSubview: label];

		_label = label;

		//DRAW ICON
		var icon = [[CPImage alloc]
		            initWithContentsOfFile: @"Resources/Interaction.gif" 
		            size:CGSizeMake(16, 16)];
		
		var iconView = [[CPImageView alloc] initWithFrame:CGRectMake(4, 4, 16, 160)];
		[iconView setHasShadow:NO];
		[iconView setImageScaling:CPScaleNone];
		
		var iconSize = [icon size];
		[iconView setFrameSize: iconSize];
		[iconView setImage: icon];
		[self addSubview: iconView];
		
		//WIDGET CONTAINER
		_widgetContainer = [[CompositeFigure alloc] initWithFrame: CGRectMake(2, 28, [self frameSize].width - 4, [self frameSize].height - 31)];
		[_widgetContainer setAutoresizingMask: CPViewWidthSizable | CPViewHeightSizable];
		[self addSubview: _widgetContainer];
		
		return self;
	}
} 

- (void)drawRect:(CGRect)rect on: (id)context
{
		//DRAW RECTANGLE
        var radius = 6.0;
        CGContextSetFillColor(context, [CPColor whiteColor]); 
        CGContextFillRoundedRectangleInRect(context, [self bounds], radius, YES, YES, YES, YES); 

        CGContextSetFillColor(context, [self borderColor]); 
        CGContextSetStrokeColor(context, [self borderColor]); 
        CGContextStrokeRoundedRectangleInRect(context, [self bounds], radius, YES, YES, YES, YES);

		//DRAW LINE UNDER INTERACTION NAME
		CGContextSetLineWidth(context, 1);
		CGContextFillRect(context, CGRectMake(0, 24,  [self frameSize].width, 1));
}

- (CPColor)borderColor
{ 
	return [CPColor colorWithHexString: @"777777"];
}

- (CPColor)handleColor
{ 
	return [self borderColor];
}

- (bool) isSelectable
{ 
	return true;
}

- (bool) isMoveable
{ 
	return true;
}

- (bool)isEditable
{ 
	return true;
}

- (void) addWidget:(Widget) aWidget
{
	[_widgetContainer addSubview: aWidget];
}

- (void) switchToEditMode
{
	var editorDelegate = [[EditorDelegate alloc] 
		initWithWidget: _label 
		value: [_label objectValue]
		window: [self window]
		figureContainer: self
		drawing: [self superview]];
}

- (void) setEditionResult: (String) aValue
{
	[_label setObjectValue: aValue];
	[_label sizeToFit];
}

@end