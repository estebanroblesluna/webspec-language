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
@implementation CheckBoxFigure : MockupFigure 
{ 
	CPTextField _label;
} 

+ (CheckBoxFigure) newAt: (CGPoint) aPoint
{
	var frame = CGRectMake(aPoint.x, aPoint.y, 100, 25);
	var widget = [[self new] initWithFrame: frame];
	[widget setChecked: NO];
	return widget;
}

- (id) initWithFrame: (CGRect) aFrame
{ 
	self = [super initWithFrame: aFrame andModelFeature: @"Name"];
	if (self) {
		
		[self addLeftRightHandles];

		//DRAW WIDGET NAME
		var label = [[CPTextField alloc] initWithFrame:CGRectMakeZero()];
		[label setStringValue: @"CheckBox"];
		[label setTextColor:[CPColor blackColor]];
		[label sizeToFit];
		[label setFrameOrigin: CGPointMake(30, 0)];
		[self addSubview: label];
		_label = label;
	
		return self;
	}
}

- (void) drawRect:(CGRect)rect on: (id)context
{
	
	//DRAW CHECKBOX BACKGROUND
        CGContextSetFillColor(context, [CPColor whiteColor]); 
        CGContextFillRect(context, [self bounds], YES, YES, YES, YES); 
        
        //DRAW CHECKBOX
	CGContextSetStrokeColor(context, [self borderColor]);
	CGContextSetLineWidth(context, 2);
        CGContextStrokeRect(context, CGRectMake(5, 5, 15, 15), YES, YES, YES, YES);

        //DRAW MARK
        if ([self checked]) {
		CGContextBeginPath(context);
		CGContextMoveToPoint(context, 10, 10);
		CGContextAddLineToPoint(context, 15, 15);
		CGContextAddLineToPoint(context, 25, 0);
		CGContextSetStrokeColor(context, [self borderColor]);
		CGContextSetLineWidth(context, 2);
		CGContextStrokePath(context);
	}
        [_label setBounds: CGRectMake(0, 0, rect.size.width - 30, rect.size.height)];
        
}

- (CPTextField) getEditableLabel
{
	return _label;	
}

- (BOOL) checked
{
	return [[[self model] propertyValue: @"Checked"] boolValue];	
}

- (void) setChecked: (BOOL) checked
{
	return [[self model] propertyValue: @"Checked" be: checked];	
}

@end