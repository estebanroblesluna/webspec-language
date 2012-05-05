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
@implementation MockupToolbox : ToolboxFigure 
{
}

- (id) initializeWith: (Drawing) aDrawing at: (CPPoint) aPoint
{
	self = [super initializeWith: aDrawing at: aPoint];
	if (self) {
		return self;
	}
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
	
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [ButtonFigure class]] withTitle: @"Widget" image: @"Resources/Button.gif"];
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [LabelFigure class]] withTitle: @"Label" image: @"Resources/Label.gif"];
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [CheckBoxFigure class]] withTitle: @"CheckBox" image: @"Resources/CheckBox.gif"];
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [RadioButtonFigure class]] withTitle: @"RadioButton" image: @"Resources/RadioButton.gif"];
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [TextFieldFigure class]] withTitle: @"TextField" image: @"Resources/TextField.gif"];
	[self addTool: [CreateWidgetTool drawing: _drawing figure: [PanelFigure class]] withTitle: @"Panel" image: @"Resources/PanelContainer.gif"];
	
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