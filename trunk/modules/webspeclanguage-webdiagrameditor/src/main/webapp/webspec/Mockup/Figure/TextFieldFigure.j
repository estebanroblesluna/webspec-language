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
@implementation TextFieldFigure : MockupFigure 
{ 
	CPTextField _label;
} 

+ (TextFieldFigure) newAt: (CGPoint) aPoint
{
	var frame = CGRectMake(aPoint.x, aPoint.y, 100, 25);
	var widget = [[self new] initWithFrame: frame];
	return widget;
}

- (id) initWithFrame: (CGRect) aFrame
{ 
	self = [super initWithFrame: aFrame andModelFeature: @"Name"];
	if (self) {
		
		[self addAllHandles];

		//DRAW WIDGET NAME
		var label = [[CPTextField alloc] initWithFrame:CGRectMakeZero()];
		[label setStringValue: @"TextField"];
		[label setTextColor:[CPColor blackColor]];
		[label sizeToFit];
		[label setFrameOrigin: CGPointMake(7, 3)];
		[self addSubview: label];
		_label = label;
	
		return self;
	}
}

- (void) drawRect:(CGRect)rect on: (id)context
{
	
	//DRAW BACKGROUND
        CGContextSetFillColor(context, [CPColor whiteColor]); 
        CGContextFillRect(context, [self bounds], YES, YES, YES, YES); 
        
        //DRAW TEXTBOX
	CGContextSetStrokeColor(context, [self borderColor]);
	CGContextSetLineWidth(context, 3);
	CGContextStrokeRect(context, [self bounds]);
        
        [_label setBounds: CGRectMake(0, 0, rect.size.width - 7, rect.size.height - 3)];
}

- (CPTextField) getEditableLabel
{
	return _label;	
}

@end