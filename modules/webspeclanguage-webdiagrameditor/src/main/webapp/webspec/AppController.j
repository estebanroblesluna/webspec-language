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

@import <Foundation/CPObject.j>
@import <Foundation/Foundation.j>
@import "Hotdraw/Hotdraw.j"
@import "WebSpec/WebSpec.j"


/**
 * @author "Esteban Robles Luna <esteban.roblesluna@gmail.com>"
 */
@implementation AppController : CPObject
{
	CPTextField svnChangesTextField;
	CPTextField bwTextField;
	Drawing drawing;
	id anotherContentView;
}

- (void)applicationDidFinishLaunching:(CPNotification)aNotification
{
    CPLogRegister(CPLogPopup);
	var theWindow = [[CPWindow alloc] initWithContentRect:CGRectMakeZero() styleMask:CPBorderlessBridgeWindowMask],
        contentView = [theWindow contentView];


	[self initializeWebSpecDrawing: contentView window: theWindow];
    [theWindow orderFront: self];
	[theWindow makeFirstResponder: drawing];
}

- (void)initializeWebSpecDrawing:(id) contentView window: (id) theWindow
{
	var drawing = [[WebSpecDrawing alloc] initWithFrame: [contentView bounds]];
	[drawing showGrid: YES];
	[drawing gridSize: 20];
	[drawing snapToGrid: YES];

	var toolBox = [WebSpecToolbox initializeWith: drawing at: CGPointMake(20,20)];
	[drawing addSubview: toolBox];
	
	var properties = [PropertiesFigure newAt: CGPointMake(20,400) drawing: drawing];
	[drawing addSubview: properties];

	[contentView addSubview: drawing];
}

- (void)initializeGenericDrawing:(id) contentView
{
	var drawing = [[Drawing alloc] initWithFrame: [contentView bounds]];
	[drawing showGrid: YES];
	[drawing gridSize: 20];
	[drawing snapToGrid: YES];
	
	var toolBox = [ToolboxFigure initializeWith: drawing at: CGPointMake(10,10)];
	[drawing addSubview: toolBox];

	[contentView addSubview: drawing];
}
@end
