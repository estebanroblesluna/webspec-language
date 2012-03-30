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
	
	//[self addSeparator];
}
                                               
@end