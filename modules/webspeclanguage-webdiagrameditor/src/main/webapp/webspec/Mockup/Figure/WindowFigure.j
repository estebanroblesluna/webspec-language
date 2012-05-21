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
@implementation WindowFigure : ContainerMockupFigure 
{
    CPTextField _title;
}

+ (Interaction) newAt: (CGPoint) aPoint
{
	var frame = CGRectMake(aPoint.x, aPoint.y, 150, 100);
	var window = [[self new] initWithFrame: frame];
	return window;
}

- (id) initWithFrame: (CGRect) aFrame 
{ 
	self = [super 
	    initWithFrame: aFrame
        model: [PanelContainerModel new]
        modelFeature: "Name"];
		
	var title = [[CPTextField alloc] initWithFrame:CGRectMakeZero()];
    [title setStringValue: "Title"];
    [title setTextColor:[CPColor blackColor]];
    [title sizeToFit];
    [title setFrameOrigin: CGPointMake(3, 0)];
    [self addSubview: title];
    _title = title;
		
	if (self) {
		[self addAllHandles];
		return self;
	}
}

- (void) buildWidgetContainer 
{
    var widgetContainerFrame = CGRectMake(2, 20, [self frameSize].width - 4, [self frameSize].height - 23);
    var widgetContainerOrigin = widgetContainerFrame.origin;
    var widgetContainer = [PanelFigure newAt: widgetContainerOrigin];
    [widgetContainer setFrame: widgetContainerFrame];
    [widgetContainer selectable: NO];
    [widgetContainer hideBorder];
    [widgetContainer setAutoresizingMask: CPViewWidthSizable | CPViewHeightSizable];
    return widgetContainer
}

- (void) drawRect:(CGRect)rect on: (id)context
{
    CGContextSetFillColor(context, [CPColor whiteColor]); 
    CGContextFillRect(context, rect); 
    CGContextSetLineWidth(context, 3);
    CGContextStrokeRect(context, rect);
    CGContextStrokeRect(context, CGRectMake(rect.origin.x, rect.origin.y, rect.size.width, 20));
    
    CGContextSetLineWidth(context, 2);
    //DRAW CLOSE BUTTON
    CGContextBeginPath(context);
    CGContextMoveToPoint(context, rect.size.width - 16, 4);
    CGContextAddLineToPoint(context, rect.size.width - 4, 16);
    CGContextStrokePath(context);
    CGContextBeginPath(context);
    CGContextMoveToPoint(context, rect.size.width - 16, 16);
    CGContextAddLineToPoint(context, rect.size.width - 4, 4);
    CGContextStrokePath(context);
    
    //DRAW MINIMIZE BUTTON
    CGContextBeginPath(context);
    CGContextMoveToPoint(context, rect.size.width - 32, 16);
    CGContextAddLineToPoint(context, rect.size.width - 20, 16);
    CGContextStrokePath(context);
}

- (CPTextField) getEditableLabel
{
	return _title;	
}

@end