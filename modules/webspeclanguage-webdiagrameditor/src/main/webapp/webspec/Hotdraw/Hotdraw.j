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

@import "Extension/CPCancelableTextField.j"
@import "Extension/LPMultiLineTextField.j"
@import "Util/GeometryUtils.j"
@import "Util/HandleMagnet.j"

@import "Core/Drawing.j"

@import "Model/Model.j"
@import "Model/Property.j"

@import "Figure/Figure.j"
@import "Figure/Handle.j"
@import "Figure/CompositeFigure.j"
@import "Figure/Polyline.j"
@import "Figure/Connection.j"
@import "Figure/ImageFigure.j"
@import "Figure/ToolboxFigure.j"
@import "Figure/LabelFigure.j"
@import "Figure/PropertiesFigure.j"
@import "Figure/IconLabelFigure.j"


@import "Tool/Tool.j"
@import "Tool/SelectionTool.j"
@import "Tool/AbstractCreateFigureTool.j"
@import "Tool/CreateImageTool.j"
@import "Tool/CreateLabelTool.j"

@import "Util/EditorDelegate.j"

/**
 * @author "Esteban Robles Luna <esteban.roblesluna@gmail.com>"
 */
@implementation Hotdraw : CPObject
{
}
@end