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
@implementation LabelFigure : LabeledFigure 
{
}

- (id) initWithFrame: (CGRect) aFrame
{ 
	self = [self 
	    initWithFrame: aFrame
	    model: [LabelModel new]
	    modelFeature: @"Name" 
	    labelValue: "Label" 
	    labelLocation: CGPointMake(0, 0)];	    
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
        [[self label] setBounds: CGRectMake(0, 0, rect.size.width, rect.size.height)];
}

@end