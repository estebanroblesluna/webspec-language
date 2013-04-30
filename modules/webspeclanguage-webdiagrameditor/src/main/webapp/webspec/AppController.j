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
	var drawing = [WebSpecDrawing frame: [contentView bounds]];

	var webspecToolbox = [WebSpecToolbox initializeWith: drawing at: CGPointMake(20,70)];
	[webspecToolbox columns: 3];

	[webspecToolbox addTool: [CreateInteractionTool drawing: drawing] withTitle: @"Interaction" image: @"Resources/Interaction.gif"];

	[webspecToolbox addTool: [CreateConnectionTool drawing: drawing figure: [NavigationFigure class]] withTitle: @"Connection" image: @"Resources/Navigation.gif"];
	[webspecToolbox addTool: [CreateConnectionTool drawing: drawing figure: [RichBehaviorFigure class]] withTitle: @"Connection" image: @"Resources/RichBehavior.gif"];

	[webspecToolbox addTool: [CreateWidgetTool drawing: drawing figure: [ButtonFigure class]] withTitle: @"Widget" image: @"Resources/Button.gif"];
	[webspecToolbox addTool: [CreateWidgetTool drawing: drawing figure: [CheckBoxFigure class]] withTitle: @"Widget" image: @"Resources/CheckBox.gif"];
	[webspecToolbox addTool: [CreateWidgetTool drawing: drawing figure: [ComboBoxFigure class]] withTitle: @"Widget" image: @"Resources/ComboBox.gif"];
	[webspecToolbox addTool: [CreateWidgetTool drawing: drawing figure: [LabelFigure class]] withTitle: @"Widget" image: @"Resources/Label.gif"];
	[webspecToolbox addTool: [CreateWidgetTool drawing: drawing figure: [LinkFigure class]] withTitle: @"Widget" image: @"Resources/Link.gif"];
	[webspecToolbox addTool: [CreateWidgetTool drawing: drawing figure: [ListBoxFigure class]] withTitle: @"Widget" image: @"Resources/ListBox.gif"];
	[webspecToolbox addTool: [CreateWidgetTool drawing: drawing figure: [RadioButtonFigure class]] withTitle: @"Widget" image: @"Resources/RadioButton.gif"];
	[webspecToolbox addTool: [CreateWidgetTool drawing: drawing figure: [TextFieldFigure class]] withTitle: @"Widget" image: @"Resources/TextField.gif"];
	[webspecToolbox addTool: [CreateContainerTool drawing: drawing figure: [PanelContainerFigure class]] withTitle: @"Widget" image: @"Resources/PanelContainer.gif"];
	[webspecToolbox addTool: [CreateContainerTool drawing: drawing figure: [ListOfContainerFigure class]] withTitle: @"Widget" image: @"Resources/ListOfContainer.gif"];

	[webspecToolbox addTool: [CreateGeneratorTool drawing: drawing figure: [OneOfManyStringsFigure class]] withTitle: @"One of many strings" image: @"Resources/OneOfStrings.gif"];
	[webspecToolbox addTool: [CreateGeneratorTool drawing: drawing figure: [OneOfManyNumbersFigure class]] withTitle: @"One of many numbers" image: @"Resources/OneOfNumbers.gif"];
	[webspecToolbox addTool: [CreateGeneratorTool drawing: drawing figure: [OneOfManyArraysFigure class]] withTitle: @"One of many arrays" image: @"Resources/OneOfArray.gif"];
	[webspecToolbox addTool: [CreateGeneratorTool drawing: drawing figure: [RandomStringFigure class]] withTitle: @"Random string" image: @"Resources/StringGenerator.gif"];
	[webspecToolbox addTool: [CreateGeneratorTool drawing: drawing figure: [UniformDistributionFigure class]] withTitle: @"Uniform distribution of numbers" image: @"Resources/UniformNumberDistribution.gif"];
	

	var commonToolbox = [WebSpecToolbox initializeWith: drawing at: CGPointMake(800,70)];
	[commonToolbox columns: 2];

	[commonToolbox addTool: [SelectionTool drawing: drawing] withTitle: @"Selection" image: @"Resources/Selection.png"];
	[commonToolbox addCommand: [FigureJSONSerializer class] withTitle: @"Save" image: @"Resources/AlignLeft.gif"];

    [commonToolbox addCommand: [GroupCommand class] withTitle: @"Group" image: @"Resources/Group.gif"];
    [commonToolbox addCommand: [UngroupCommand class] withTitle: @"Ungroup" image: @"Resources/Ungroup.gif"];

    [commonToolbox addCommand: [LockCommand class] withTitle: @"Lock" image: @"Resources/Lock.gif"];
    [commonToolbox addCommand: [UnlockCommand class] withTitle: @"Unlock" image: @"Resources/Unlock.gif"];

    [commonToolbox addCommand: [BringToFrontCommand class] withTitle: @"Bring to front" image: @"Resources/BringToFront.gif"];
    [commonToolbox addCommand: [SendToBackCommand class] withTitle: @"Send to back" image: @"Resources/SendToBack.gif"];
    [commonToolbox addCommand: [BringForwardCommand class] withTitle: @"Bring forward" image: @"Resources/BringForward.gif"];
    [commonToolbox addCommand: [SendBackwardCommand class] withTitle: @"Send backward" image: @"Resources/SendBackward.gif"];


	var alignToolbox = [WebSpecToolbox initializeWith: drawing at: CGPointMake(800,270)];
	[alignToolbox columns: 3];

	[alignToolbox addCommand: [AlignLeftCommand class] withTitle: @"Align left" image: @"Resources/AlignLeft.gif"];
	[alignToolbox addCommand: [AlignCenterCommand class] withTitle: @"Align center" image: @"Resources/AlignCenter.gif"];
	[alignToolbox addCommand: [AlignRightCommand class] withTitle: @"Align right" image: @"Resources/AlignRight.gif"];
	[alignToolbox addCommand: [AlignTopCommand class] withTitle: @"Align top" image: @"Resources/AlignTop.gif"];
	[alignToolbox addCommand: [AlignMiddleCommand class] withTitle: @"Align middle" image: @"Resources/AlignMiddle.gif"];
	[alignToolbox addCommand: [AlignBottomCommand class] withTitle: @"Align bottom" image: @"Resources/AlignBottom.gif"];
	
	
	var properties = [PropertiesFigure newAt: CGPointMake(20,520) drawing: drawing];
	
	[drawing setAutoresizingMask: CPViewWidthSizable | CPViewHeightSizable];

	[contentView addSubview: drawing];
	
	var sharedApplication = [CPApplication sharedApplication];
	var namedArguments = [sharedApplication namedArguments];
	
	var headless = window.top.location.hash;
    
	if (!(headless.localeCompare("#headless")) == 0) {
		[drawing toolbox: webspecToolbox];
		[drawing toolbox: commonToolbox];
		[drawing toolbox: alignToolbox];
        [drawing properties: properties];
	}

	if ([namedArguments containsKey: "diagramId"]) {
		var diagramId = [namedArguments objectForKey: "diagramId"];
		[drawing diagramId: diagramId];
		var command = [DiagramLoaderCommand drawing: drawing diagramId: diagramId];
		[command execute];
	}
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
