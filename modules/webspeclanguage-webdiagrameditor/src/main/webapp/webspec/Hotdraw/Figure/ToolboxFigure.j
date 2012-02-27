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
 * @author "Esteban Robles Luna <esteban.roblesluna@gmail.com>"
 */
@implementation ToolboxFigure : Figure 
{
	Drawing _drawing;
	CPDictionary _buttonsMapping;
}

+ (ToolboxFigure) initializeWith: (Drawing) aDrawing at: (CPPoint) aPoint
{
	var figure = [[self alloc] initializeWith: aDrawing at: aPoint];
	return figure;
}

- (id) initializeWith: (Drawing) aDrawing at: (CPPoint) aPoint
{
	var frame = CGRectMake(aPoint.x, aPoint.y, 50, 1);
	self = [super initWithFrame: frame];
	if (self) {
		_drawing = aDrawing;
		_buttonsMapping = [CPDictionary dictionary];
		[self addDefaultTools];
		return self;
	}
}

- (void) addDefaultTools
{
	[self addTool: [[SelectionTool alloc] initWithDrawing: _drawing] withTitle: @"Selection" image: @""];
	[self addTool: [[CreateImageTool alloc] initWithDrawing: _drawing] withTitle: @"Image" image: @""];
	[self addTool: [[CreateLabelTool alloc] initWithDrawing: _drawing] withTitle: @"Label" image: @""];
}

- (void) addTool: (Tool) aTool withTitle: (id) aTitle image: (id) url
{
	var button = [CPButton buttonWithTitle: aTitle];

	var y = [_buttonsMapping count] * 25 + 15;
	var origin = CGPointMake(0, y);
	[button setFrameOrigin: origin];

	var icon = [[CPImage alloc]
	            initWithContentsOfFile: url];
	[button setImage: icon];
	[button setFrameSize: CGSizeMake(100, 25)];

	[self addSubview: button];

	[button setTarget: self];
	[button setAction: @selector(selectTool:)];

	[_buttonsMapping setObject: aTool forKey: button];
	
	var newSize = CGSizeMake(100, 25 * [_buttonsMapping count] + 15);
	[self setFrameSize: newSize];
}

- (void) selectTool: (CPButton) aButton
{
	var tool = [_buttonsMapping objectForKey: aButton];
	[_drawing tool: tool];
}

- (void)drawRect:(CGRect)rect on: (id)context
{
    CGContextSetFillColor(context, [CPColor grayColor]); 
    CGContextFillRect(context, [self bounds]); 
}

- (bool) isSelectable
{ 
	return true;
}

- (bool) isMoveable
{ 
	return true;
}
@end