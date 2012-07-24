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
@implementation TextFieldFigure : LabeledFigure 
{
}

- (id) initWithFrame: (CGRect) aFrame
{ 
	self = [self 
	    initWithFrame: aFrame 
	    model: [TextFieldModel new]
	    modelFeature: @"Name" 
	    labelValue: "TextField" 
	    labelLocation: CGPointMake(7, 3)];	    
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
        
    //DRAW TEXTBOX
	CGContextSetStrokeColor(context, [self borderColor]);
	CGContextSetLineWidth(context, 3);
	CGContextStrokeRect(context, [self bounds]);
        
    [[self label] setBounds: CGRectMake(0, 0, rect.size.width - 7, rect.size.height - 3)];
}

@end