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
@implementation ButtonFigure : LabeledFigure 
{ 
}

- (id) initWithFrame: (CGRect) aFrame
{ 
	self = [self 
	    initWithFrame: aFrame 
	    model: [ButtonModel new]
	    modelFeature: @"Name" 
	    labelValue: "Button" 
	    labelLocation: CGPointMake(0, 0)];	    
	if (self) {
		[self addAllHandles];
		return self;
	}
}

- (void) drawRect:(CGRect)rect on: (id)context
{
	//DRAW RECTANGLE AND LABEL
        var radius = 10.0;
        CGContextSetFillColor(context, [CPColor whiteColor]); 
        CGContextFillRoundedRectangleInRect(context, [self bounds], radius, YES, YES, YES, YES); 
        CGContextSetFillColor(context, [self borderColor]); 
        CGContextSetLineWidth(context, 3);
        CGContextSetStrokeColor(context, [self borderColor]);
        CGContextStrokeRoundedRectangleInRect(context, [self bounds], radius, YES, YES, YES, YES);
        [[self label] setCenter: CGPointMake(rect.origin.x + rect.size.width / 2, rect.origin.y + rect.size.height / 2)];
}

@end