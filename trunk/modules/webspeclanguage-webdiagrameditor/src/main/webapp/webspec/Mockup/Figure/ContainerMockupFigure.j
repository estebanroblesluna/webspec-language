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
@implementation ContainerMockupFigure : MockupFigure 
{
    PanelContainer _widgetContainer;
}

+ (Interaction) newAt: (CGPoint) aPoint
{
	var frame = CGRectMake(aPoint.x, aPoint.y, 150, 100);
	var window = [[self new] initWithFrame: frame];
	return window;
}

- (id) initWithFrame: (CGRect) aFrame model: aModel modelFeature: aModelFeature
{ 
	self = [super 
	    initWithFrame: aFrame
        model: aModel
        modelFeature: aModelFeature];
	
    _widgetContainer = [self buildWidgetContainer]
    [self addSubview: _widgetContainer];

	if (self) {
		[self addAllHandles];
		return self;
	}
}

- (id) initWithFrame: (CGRect) aFrame
{ 
	self = [super initWithFrame: aFrame];
	
    _widgetContainer = [self buildWidgetContainer]
    [self addSubview: _widgetContainer];

	if (self) {
		[self addAllHandles];
		return self;
	}
}

- (void) addWidget: (Widget) aWidget
{
	[_widgetContainer addSubview: aWidget];
}

- (void) addFigure: (Widget) aWidget
{
	[self addWidget: aWidget];              
}

@end