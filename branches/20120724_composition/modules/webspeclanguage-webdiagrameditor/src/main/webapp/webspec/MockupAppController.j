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
@import <CupDraw/CupDraw.j>
@import "Mockup/Mockup.j"


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


	[self initializeMockupDrawing: contentView window: theWindow];
    [theWindow orderFront: self];
	[theWindow makeFirstResponder: drawing];
}

- (void)initializeMockupDrawing:(id) contentView window: (id) theWindow
{
	var drawing = [MockupDrawing frame: [contentView bounds]];

	var mockupToolbox = [MockupToolbox initializeWith: drawing at: CGPointMake(20,20)];
	
	[mockupToolbox addTool: [CreateWidgetTool drawing: drawing figure: [ButtonFigure class]] withTitle: @"Widget" image: @"Resources/Button.gif"];
	[mockupToolbox addTool: [CreateWidgetTool drawing: drawing figure: [LabelFigure class]] withTitle: @"Label" image: @"Resources/Label.gif"];
	[mockupToolbox addTool: [CreateWidgetTool drawing: drawing figure: [CheckBoxFigure class]] withTitle: @"CheckBox" image: @"Resources/CheckBox.gif"];
	[mockupToolbox addTool: [CreateWidgetTool drawing: drawing figure: [RadioButtonFigure class]] withTitle: @"RadioButton" image: @"Resources/RadioButton.gif"];
	[mockupToolbox addTool: [CreateWidgetTool drawing: drawing figure: [TextFieldFigure class]] withTitle: @"TextField" image: @"Resources/TextField.gif"];
	[mockupToolbox addTool: [CreateWidgetTool drawing: drawing figure: [PanelFigure class]] withTitle: @"Panel" image: @"Resources/PanelContainer.gif"];
	[mockupToolbox addTool: [CreateWidgetTool drawing: drawing figure: [WindowFigure class]] withTitle: @"Window" image: @"Resources/PanelContainer.gif"];
	
	[drawing toolbox: mockupToolbox];
	
	var commonToolbox = [MockupToolbox initializeWith: drawing at: CGPointMake(800,20)];
	[commonToolbox columns: 2];

	[commonToolbox addTool: [SelectionTool drawing: drawing] withTitle: @"Selection" image: @"Resources/Selection.png"];
	//[commonToolbox addCommand: [FigureJSONSerializer class] withTitle: @"Save" image: @"Resources/AlignLeft.gif"];
	
	[commonToolbox addCommand: [GroupCommand class] withTitle: @"Group" image: @"Resources/UniformNumberDistribution.gif"];
	[commonToolbox addCommand: [UngroupCommand class] withTitle: @"Ungroup" image: @"Resources/UniformNumberDistribution.gif"];

	[commonToolbox addCommand: [LockCommand class] withTitle: @"Lock" image: @"Resources/UniformNumberDistribution.gif"];
	[commonToolbox addCommand: [UnlockCommand class] withTitle: @"Unlock" image: @"Resources/UniformNumberDistribution.gif"];

	[commonToolbox addCommand: [BringToFrontCommand class] withTitle: @"Bring to front" image: @"Resources/BringToFront.gif"];
	[commonToolbox addCommand: [SendToBackCommand class] withTitle: @"Send to back" image: @"Resources/SendToBack.gif"];
	[commonToolbox addCommand: [BringForwardCommand class] withTitle: @"Bring forward" image: @"Resources/BringForward.gif"];
	[commonToolbox addCommand: [SendBackwardCommand class] withTitle: @"Send backward" image: @"Resources/SendBackward.gif"];

	[drawing toolbox: commonToolbox];
	
	var alignToolbox = [MockupToolbox initializeWith: drawing at: CGPointMake(800,200)];
	[alignToolbox columns: 3];

	[alignToolbox addCommand: [AlignLeftCommand class] withTitle: @"Align left" image: @"Resources/AlignLeft.gif"];
	[alignToolbox addCommand: [AlignCenterCommand class] withTitle: @"Align center" image: @"Resources/AlignCenter.gif"];
	[alignToolbox addCommand: [AlignRightCommand class] withTitle: @"Align right" image: @"Resources/AlignRight.gif"];
	[alignToolbox addCommand: [AlignTopCommand class] withTitle: @"Align top" image: @"Resources/AlignTop.gif"];
	[alignToolbox addCommand: [AlignMiddleCommand class] withTitle: @"Align middle" image: @"Resources/AlignMiddle.gif"];
	[alignToolbox addCommand: [AlignBottomCommand class] withTitle: @"Align bottom" image: @"Resources/AlignBottom.gif"];
	
	[drawing toolbox: alignToolbox];
	
	var properties = [PropertiesFigure newAt: CGPointMake(20,400) drawing: drawing];
	[drawing properties: properties];
	
	[drawing setAutoresizingMask: CPViewWidthSizable | CPViewHeightSizable];

	[contentView addSubview: drawing];
}

- (void)initializeGenericDrawing:(id) contentView
{
	var drawing = [Drawing frame: [contentView bounds]];
	[drawing showGrid: YES];
	[drawing gridSize: 20];
	[drawing snapToGrid: YES];
	
	var toolBox = [ToolboxFigure initializeWith: drawing at: CGPointMake(10,10)];
	[drawing addFigure: toolBox];

	[contentView addSubview: drawing];
}
@end
