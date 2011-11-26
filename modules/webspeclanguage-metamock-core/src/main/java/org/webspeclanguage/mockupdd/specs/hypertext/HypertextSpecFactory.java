/* Licensed under the Apache License, Version 2.0 (the "License");
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

package org.webspeclanguage.mockupdd.specs.hypertext;

import java.util.ArrayList;
import java.util.List;
import org.webspeclanguage.mockupdd.specs.data.AttributeSpec;
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.sui.model.InputWidget;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SelectionWidget;
import org.webspeclanguage.mockupdd.sui.model.SimpleWidget;
import org.webspeclanguage.mockupdd.sui.model.TriggerWidget;
import org.webspeclanguage.mockupdd.sui.model.Widget;


/**
 * @author Franco Giacosa
 */
public interface HypertextSpecFactory {

  final List<AssociateActionSpec> associateActionSpecs = new ArrayList<AssociateActionSpec>();
  final List<AttributeMappingSpec> attributeMappingSpecs = new ArrayList<AttributeMappingSpec>();
  final List<ClassMappingSpec> classMappingSpecs = new ArrayList<ClassMappingSpec>();
  final List<DeleteActionSpec> deleteActionSpecs = new ArrayList<DeleteActionSpec>();
  final List<DissociateActionSpec> dissociateActionSpecs = new ArrayList<DissociateActionSpec>();
  final List<InputPanelSpec> inputPanelSpecs = new ArrayList<InputPanelSpec>();
  final List<NavigationSpec> navigationSpecs = new ArrayList<NavigationSpec>();
  final List<ObjectTransferSpec> objectTransferSpecs = new ArrayList<ObjectTransferSpec>();
  final List<PanelClassMappingSpec> panelClassMappingSpecs = new ArrayList<PanelClassMappingSpec>();
  final List<RepetitionClassMappingSpec> repetitionClassMappingSpecs = new ArrayList<RepetitionClassMappingSpec>();
  final List<SaveActionSpec> saveActionSpecs = new ArrayList<SaveActionSpec>();
  final List<SelectableRepetitionSpec> selectableRepetitionSpecs = new ArrayList<SelectableRepetitionSpec>();
  final List<WidgetActionsSpec> widgetActionsSpecs = new ArrayList<WidgetActionsSpec>();
  
  AssociateActionSpec createAssociateActionSpec(ClassMappingSpec type1, ClassMappingSpec type2);
  AttributeMappingSpec createAttributeMappingSpec(SimpleWidget widget, AttributeSpec attributeSpec);
  ClassMappingSpec createClassMappingSpec(ClassSpec classSpec);
  DeleteActionSpec createDeleteActionSpec(ClassMappingSpec spec);
  DissociateActionSpec createDissociateActionSpec(ClassMappingSpec type1, ClassMappingSpec type2);
  InputPanelSpec createInputPanelSpec(Panel panel, ClassSpec classSpec, List<InputWidget> inputs);
  NavigationSpec createNavigationSpec(Page to, TriggerWidget trigger, List<ActionSpec> actions, List<ObjectTransferSpec> transfers);
  ObjectTransferSpec createObjectTransferSpec(Widget from, Widget to);
  PanelClassMappingSpec createPanelClassMappingSpec(Panel panel, ClassSpec classSpec);
  RepetitionClassMappingSpec createRepetitionClassMappingSpec(Repetition repetition, ClassSpec classSpec);
  SaveActionSpec createSaveActionSpec(ClassMappingSpec spec);
  SelectableRepetitionSpec createSelectableRepetitionSpec(Repetition repetition, ClassSpec classSpec, SelectionWidget selectable);
  WidgetActionsSpec createWidgetActionsSpec(TriggerWidget trigger, List<ActionSpec> actions);
  
}
