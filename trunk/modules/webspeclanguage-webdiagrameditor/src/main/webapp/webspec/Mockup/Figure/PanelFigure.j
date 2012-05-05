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
@implementation PanelFigure : ContainerFigure 
{
    bool _drawBorder;
}

- (id) init
{ 
	[super init];
	[self model: [PanelContainerModel new]];
	_backgroundColor = [CPColor whiteColor];
	_foregroundColor = [CPColor blackColor];
	_drawBorder = YES;
	return self;
}

- (void) drawRect:(CGRect)rect on: (id)context
{
    CGContextSetFillColor(context, [CPColor whiteColor]); 
    CGContextFillRect(context, rect);
    if (_drawBorder) {
        CGContextSetLineWidth(context, 3);
        CGContextStrokeRect(context, rect);
    }
}

- (void) hideBorder
{
    _drawBorder = NO;
}

@end