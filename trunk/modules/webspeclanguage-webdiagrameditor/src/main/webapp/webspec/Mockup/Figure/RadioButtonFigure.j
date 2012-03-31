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
@implementation RadioButtonFigure : LabeledFigure 
{
}

- (id) initWithFrame: (CGRect) aFrame
{ 
	self = [self 
	    initWithFrame: aFrame 
	    model: [RadioButtonModel new]
	    modelFeature: @"Name" 
	    labelValue: "RadioButton" 
	    labelLocation: CGPointMake(30, 0)];	    
	if (self) {
		[self addLeftRightHandles];
		return self;
	}
}

- (void) drawRect:(CGRect)rect on: (id)context
{
	
	//DRAW BACKGROUND
    CGContextSetFillColor(context, [CPColor whiteColor]); 
    CGContextFillRect(context, [self bounds], YES, YES, YES, YES); 
        
    //DRAW RADIO
	CGContextSetStrokeColor(context, [self borderColor]);
	CGContextSetFillColor(context, [self borderColor]);
	CGContextSetLineWidth(context, 2);
	CGContextStrokeEllipseInRect(context, CGRectMake(5, 5, 15, 15));
	
	// DRAW MARK
	if ([self checked]) {
	    CGContextFillEllipseInRect(context, CGRectMake(8, 8, 9, 9));
	}
        
    [[self label] setBounds: CGRectMake(0, 0, rect.size.width - 30, rect.size.height)];
}

- (BOOL) checked
{
	return [[[self model] propertyValue: @"Checked"] boolValue];	
}

- (void) checked: (BOOL) checked
{
	return [[self model] propertyValue: @"Checked" be: checked];	
}

@end