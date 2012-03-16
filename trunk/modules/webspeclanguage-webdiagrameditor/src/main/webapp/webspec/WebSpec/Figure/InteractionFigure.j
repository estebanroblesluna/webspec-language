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
@implementation InteractionFigure : Figure 
{ 
	CPTextField _label;
	CPView _widgetContainer;
	ImageFigure _invariantFigure;
	IconLabelFigure _titleWidget;
} 

+ (Interaction) newAt: (CGPoint) aPoint
{
	return [self newAt: aPoint name: @"Interaction"];
}

+ (Interaction) newAt: (CGPoint) aPoint name: (String) aName
{
	var frame = CGRectMake(aPoint.x, aPoint.y, 150, 100);
	var interaction = [[self alloc] initWithFrame: frame];
	[interaction name: aName];
	return interaction;
}

- (id) initWithFrame:(CGRect)aFrame 
{ 
	self = [super initWithFrame:aFrame];
	if (self) {
		[self model: [InteractionModel new]];

		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"topLeft"]];
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"topMiddle"]];
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"topRight"]];

		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"middleLeft"]];
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"middleRight"]];

		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"bottomLeft"]];
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"bottomMiddle"]];
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"bottomRight"]];
		
		_titleWidget = [IconLabelFigure newAt: CGPointMake(2, 2) iconUrl: @"Resources/Interaction.gif"];
		[_titleWidget model: [self model]];
		[_titleWidget checkModelFeature: @"Name"];
		[_titleWidget selectable: NO];
		[_titleWidget moveable: NO];
		[self addSubview: _titleWidget];
		
		_invariantFigure = [ImageFigure initializeWithImage: @"Resources/Invariant.gif" x: 23 y: 6];
		[_invariantFigure showBorder: NO];
		[_invariantFigure selectable: NO];
		[_invariantFigure moveable: NO];
		[_invariantFigure setFrameOrigin: CGPointMake([self frameSize].width - 20, 6)];
		[_invariantFigure setAutoresizingMask:  CPViewMinXMargin];
		[self validateInvariantIcon];
		[self addSubview: _invariantFigure];
		
		//WIDGET CONTAINER
		//_widgetContainer = [[CompositeFigure alloc] initWithFrame: ];
		var widgetContainerFrame = CGRectMake(2, 28, [self frameSize].width - 4, [self frameSize].height - 31);
		var widgetContainerOrigin = widgetContainerFrame.origin;
		_widgetContainer = [PanelContainerFigure newAt: widgetContainerOrigin];
		[_widgetContainer setFrame: widgetContainerFrame];
		[_widgetContainer selectable: NO];
		[_widgetContainer hideBorder];
		[_widgetContainer setAutoresizingMask: CPViewWidthSizable | CPViewHeightSizable];
		[self addSubview: _widgetContainer];
		
		return self;
	}
} 

- (void) modelChanged
{
	[self validateInvariantIcon];
}

- (void) validateInvariantIcon
{
	var invariant = [[[self model] propertyValue: @"Invariant"] stringByTrimmingWhitespace];
	var hasInvariant = [@"" isEqualToString: invariant];
	[_invariantFigure setHidden: hasInvariant];	
}

- (void) drawRect:(CGRect)rect on: (id)context
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

- (bool) isEditable
{ 
	return true;
}

- (void) addWidget:(Widget) aWidget
{
	[_widgetContainer addSubview: aWidget];
}

- (void) switchToEditMode
{
	[_titleWidget switchToEditMode];
}

- (void) setEditionResult: (String) aValue
{
	[_label setObjectValue: aValue];
	[_label sizeToFit];
}

- (void) name: (String) aValue
{
	[_label setStringValue: aValue];
	[_label sizeToFit];
}
@end