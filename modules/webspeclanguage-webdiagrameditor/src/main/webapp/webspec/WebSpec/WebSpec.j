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

@import <AppKit/CPView.j>
@import <AppKit/CPTextField.j>

@import "Model/GeneratorModel.j"
@import "Model/WidgetModel.j"
@import "Model/ButtonModel.j"
@import "Model/InteractionModel.j"
@import "Model/TransitionModel.j"
@import "Model/NavigationModel.j"
@import "Model/RichBehaviorModel.j"
@import "Model/CheckBoxModel.j"
@import "Model/ComboBoxModel.j"
@import "Model/LabelModel.j"
@import "Model/LinkModel.j"
@import "Model/ListBoxModel.j"
@import "Model/RadioButtonModel.j"
@import "Model/TextFieldModel.j"
@import "Model/OneOfManyStringsModel.j"
@import "Model/OneOfManyNumbersModel.j"
@import "Model/OneOfManyArraysModel.j"
@import "Model/RandomStringModel.j"
@import "Model/UniformDistributionModel.j"
@import "Model/PanelContainerModel.j"
@import "Model/ListOfContainerModel.j"

@import "Figure/GeneratorFigure.j"
@import "Figure/InteractionFigure.j"
@import "Figure/WidgetFigure.j"
@import "Figure/ButtonFigure.j"
@import "Figure/CheckBoxFigure.j"
@import "Figure/ComboBoxFigure.j"
@import "Figure/LabelFigure.j"
@import "Figure/LinkFigure.j"
@import "Figure/ListBoxFigure.j"
@import "Figure/RadioButtonFigure.j"
@import "Figure/TextFieldFigure.j"
@import "Figure/ContainerFigure.j"
@import "Figure/PanelContainerFigure.j"
@import "Figure/ListOfContainerFigure.j"
@import "Figure/NavigationFigure.j"
@import "Figure/RichBehaviorFigure.j"
@import "Figure/OneOfManyStringsFigure.j"
@import "Figure/OneOfManyNumbersFigure.j"
@import "Figure/OneOfManyArraysFigure.j"
@import "Figure/RandomStringFigure.j"
@import "Figure/UniformDistributionFigure.j"
@import "Figure/TransitionIconLabelFigure.j"

@import "WebSpecDrawing.j"

@import "Tool/CreateConnectionTool.j"
@import "Tool/CreateInteractionTool.j"
@import "Tool/CreateWidgetTool.j"
@import "Tool/CreateGeneratorTool.j"
@import "Tool/CreateContainerTool.j"
@import "Tool/FigureJSONSerializer.j"
@import "Tool/DiagramLoaderCommand.j"
@import "Tool/ToUserStoryCommand.j"
@import "Tool/ToUserStoryHTMLCommand.j"
@import "Tool/ToUserStoryWordEnumerationCommand.j"
@import "Tool/ToUserStoryWordTabularCommand.j"

@import "WebSpecToolbox.j"


/**
 * @author "Esteban Robles Luna <esteban.roblesluna@gmail.com>"
 */
@implementation WebSpec : CPObject
{
}
@end