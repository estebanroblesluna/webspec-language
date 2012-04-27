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
@implementation WebSpecToolbox : ToolboxFigure 
{
}

- (void) selectTool: (CPButton) aButton
{
	[super selectTool: aButton];
	[_drawing unselectAll];
}

- (void) addDefaultTools
{
	[self addTool: [SelectionTool drawing: _drawing] withTitle: @"Selection" image: @"Resources/Selection.png"];
	
	//[self addSeparator];
	
	[self addTool: [CreateInteractionTool drawing: _drawing] withTitle: @"Interaction" image: @"Resources/Interaction.gif"];

	//[self addSeparator];

	[self addTool: [CreateConnectionTool drawing: _drawing figure: [NavigationFigure class]] withTitle: @"Connection" image: @"Resources/Navigation.gif"];
	[self addTool: [CreateConnectionTool drawing: _drawing figure: [RichBehaviorFigure class]] withTitle: @"Connection" image: @"Resources/RichBehavior.gif"];

	//[self addSeparator];

	[self addTool: [CreateWidgetTool drawing: _drawing figure: [ButtonFigure class]] withTitle: @"Widget" image: @"Resources/Button.gif"];
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [CheckBoxFigure class]] withTitle: @"Widget" image: @"Resources/CheckBox.gif"];
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [ComboBoxFigure class]] withTitle: @"Widget" image: @"Resources/ComboBox.gif"];
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [LabelFigure class]] withTitle: @"Widget" image: @"Resources/Label.gif"];
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [LinkFigure class]] withTitle: @"Widget" image: @"Resources/Link.gif"];
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [ListBoxFigure class]] withTitle: @"Widget" image: @"Resources/ListBox.gif"];
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [RadioButtonFigure class]] withTitle: @"Widget" image: @"Resources/RadioButton.gif"];
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [TextFieldFigure class]] withTitle: @"Widget" image: @"Resources/TextField.gif"];
	[self addTool: [CreateContainerTool drawing: _drawing figure: [PanelContainerFigure class]] withTitle: @"Widget" image: @"Resources/PanelContainer.gif"];
	[self addTool: [CreateContainerTool drawing: _drawing figure: [ListOfContainerFigure class]] withTitle: @"Widget" image: @"Resources/ListOfContainer.gif"];

	//[self addSeparator];

	[self addTool: [CreateGeneratorTool drawing: _drawing figure: [OneOfManyStringsFigure class]] withTitle: @"One of many strings" image: @"Resources/OneOfStrings.gif"];
	[self addTool: [CreateGeneratorTool drawing: _drawing figure: [OneOfManyNumbersFigure class]] withTitle: @"One of many numbers" image: @"Resources/OneOfNumbers.gif"];
	[self addTool: [CreateGeneratorTool drawing: _drawing figure: [OneOfManyArraysFigure class]] withTitle: @"One of many arrays" image: @"Resources/OneOfArray.gif"];
	[self addTool: [CreateGeneratorTool drawing: _drawing figure: [RandomStringFigure class]] withTitle: @"Random string" image: @"Resources/StringGenerator.gif"];
	[self addTool: [CreateGeneratorTool drawing: _drawing figure: [UniformDistributionFigure class]] withTitle: @"Uniform distribution of numbers" image: @"Resources/UniformNumberDistribution.gif"];

	[self addSeparator];

	[self addCommand: [GroupCommand class] withTitle: @"Group" image: @"Resources/Group.gif"];
	[self addCommand: [UngroupCommand class] withTitle: @"Ungroup" image: @"Resources/Ungroup.gif"];

	[self addCommand: [LockCommand class] withTitle: @"Lock" image: @"Resources/Lock.gif"];
	[self addCommand: [UnlockCommand class] withTitle: @"Unlock" image: @"Resources/Unlock.gif"];

	[self addCommand: [BringToFrontCommand class] withTitle: @"Bring to front" image: @"Resources/BringToFront.gif"];
	[self addCommand: [SendToBackCommand class] withTitle: @"Send to back" image: @"Resources/SendToBack.gif"];
	[self addCommand: [BringForwardCommand class] withTitle: @"Bring forward" image: @"Resources/BringForward.gif"];
	[self addCommand: [SendBackwardCommand class] withTitle: @"Send backward" image: @"Resources/SendBackward.gif"];

	[self addCommand: [AlignLeftCommand class] withTitle: @"Align left" image: @"Resources/AlignLeft.gif"];
	[self addCommand: [AlignCenterCommand class] withTitle: @"Align center" image: @"Resources/AlignCenter.gif"];
	[self addCommand: [AlignRightCommand class] withTitle: @"Align right" image: @"Resources/AlignRight.gif"];
	[self addCommand: [AlignTopCommand class] withTitle: @"Align top" image: @"Resources/AlignTop.gif"];
	[self addCommand: [AlignMiddleCommand class] withTitle: @"Align middle" image: @"Resources/AlignMiddle.gif"];
	[self addCommand: [AlignBottomCommand class] withTitle: @"Align bottom" image: @"Resources/AlignBottom.gif"];

}

@end