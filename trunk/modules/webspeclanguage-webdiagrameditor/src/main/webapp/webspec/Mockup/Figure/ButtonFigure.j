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
 * @author "Jose Matias Rivero <jose.matias.rivero@gmail.com>"
 */
@implementation ButtonFigure : Figure 
{ 
	CPTextField _label;
} 

+ (ButtonFigure) newAt: (CGPoint) aPoint
{
	var frame = CGRectMake(aPoint.x, aPoint.y, 100, 25);
	var widget = [[self new] initWithFrame: frame];
	return widget;
}

- (id) initWithFrame: (CGRect) aFrame
{ 
	self = [super initWithFrame: aFrame];
	if (self) {
		
		[handles addObject: [Handle target: self selector: @"topLeft"]];
		[handles addObject: [Handle target: self selector: @"topMiddle"]];
		[handles addObject: [Handle target: self selector: @"topRight"]];

		[handles addObject: [Handle target: self selector: @"middleLeft"]];
		[handles addObject: [Handle target: self selector: @"middleRight"]];

		[handles addObject: [Handle target: self selector: @"bottomLeft"]];
		[handles addObject: [Handle target: self selector: @"bottomMiddle"]];
		[handles addObject: [Handle target: self selector: @"bottomRight"]];
		
		//DRAW WIDGET NAME
		var label = [[CPTextField alloc] initWithFrame:CGRectMakeZero()];
		[label setStringValue: @"Button"];
		[label setTextColor:[CPColor blackColor]];
		[label sizeToFit];
		[label setFrameOrigin: CGPointMake(0, 0)];
		[self addSubview: label];
		_label = label;
	
		return self;
	}
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
        [_label setCenter: CGPointMake(rect.origin.x + rect.size.width / 2, rect.origin.y + rect.size.height / 2)];
}

- (void) switchToEditMode
{
	if ([self isEditable]) {
		var editorDelegate = [[EditorDelegate alloc] 
			initWithWidget: _label 
			label: _label
			window: [self window]
			figureContainer: self
			drawing: [self drawing]];
	}
}

- (id) value
{
	return [[self model] propertyValue: _modelFeature];
}

- (void) value: (id) aValue
{
	[[self model] propertyValue: _modelFeature be: aValue];	
}

- (void) setEditionResult: (String) aValue
{
	if (_modelFeature != nil && ([self model] != nil)) {
		[self value: aValue];
	} else {
		[self setLabelValue: aValue];
	}
}

- (void) setLabelValue: (String) aValue
{
	if (aValue == nil) {
		aValue = @"";
	}
	
	[_label setObjectValue: aValue];
	[_label sizeToFit];
	
	var currentFrameSize = [self frameSize];
	currentFrameSize.width = [_label frameOrigin].x + [_label frameSize].width;
	currentFrameSize.height = [_label frameOrigin].y + [_label frameSize].height;
	[self setFrameSize: currentFrameSize];
}

- (void) propertyChanged
{
	var value = [self value];
	[self setLabelValue: value];
}

- (void) checkModelFeature: (id) aModelFeature
{
	if (_modelFeature != nil && ([self model] != nil)) {
		[[CPNotificationCenter defaultCenter] 
			removeObserver: self 
			name: ModelPropertyChangedNotification 
			object: [self model]];
		
	}

	_modelFeature = aModelFeature

	if (_modelFeature != nil && ([self model] != nil)) {
		[[CPNotificationCenter defaultCenter] 
			addObserver: self 
			selector: @selector(propertyChanged) 
			name: ModelPropertyChangedNotification 
			object: [self model]];
			
		[self propertyChanged];
	}
}
@end