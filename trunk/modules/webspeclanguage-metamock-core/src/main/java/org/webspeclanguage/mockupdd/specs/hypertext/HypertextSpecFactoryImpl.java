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
public class HypertextSpecFactoryImpl implements HypertextSpecFactory {

  public AssociateActionSpec createAssociateActionSpec(ClassMappingSpec type1, ClassMappingSpec type2) {
    AssociateActionSpec associateActionSpec = new AssociateActionSpec(type1, type2);
    HypertextSpecFactory.associateActionSpecs.add(associateActionSpec);
    return associateActionSpec;
 }

  public AttributeMappingSpec createAttributeMappingSpec(SimpleWidget widget, AttributeSpec attributeSpec) {
    AttributeMappingSpec attributeMappingSpec = new AttributeMappingSpec(widget, attributeSpec);
    HypertextSpecFactory.attributeMappingSpecs.add(attributeMappingSpec);
    return attributeMappingSpec;
  }

  public ClassMappingSpec createClassMappingSpec(ClassSpec classSpec) {
    ClassMappingSpec classMappingSpec = new ClassMappingSpec(classSpec);
    HypertextSpecFactory.classMappingSpecs.add(classMappingSpec);
    return classMappingSpec;
  }

  public DeleteActionSpec createDeleteActionSpec(ClassMappingSpec spec) {
    DeleteActionSpec deleteActionSpec = new DeleteActionSpec(spec);
    HypertextSpecFactory.deleteActionSpecs.add(deleteActionSpec);
    return deleteActionSpec;
  }

  public DissociateActionSpec createDissociateActionSpec(ClassMappingSpec type1, ClassMappingSpec type2) {
    DissociateActionSpec dissociateActionSpec = new DissociateActionSpec(type1, type2);
    HypertextSpecFactory.dissociateActionSpecs.add(dissociateActionSpec);
    return dissociateActionSpec;
  }

  public InputPanelSpec createInputPanelSpec(Panel panel, ClassSpec classSpec, List<InputWidget> inputs) {
    InputPanelSpec inputPanelSpec = new InputPanelSpec(panel, classSpec, inputs);
    HypertextSpecFactory.inputPanelSpecs.add(inputPanelSpec);
    return inputPanelSpec;
  }

  public NavigationSpec createNavigationSpec(Page to, TriggerWidget trigger, List<ActionSpec> actions, List<ObjectTransferSpec> transfers) {
    NavigationSpec navigationSpec = new NavigationSpec(to, trigger, actions, transfers);
    HypertextSpecFactory.navigationSpecs.add(navigationSpec);
    return navigationSpec;
  }

  public ObjectTransferSpec createObjectTransferSpec(Widget from, Widget to) {
    ObjectTransferSpec objectTransferSpec = new ObjectTransferSpec(from, to);
    HypertextSpecFactory.objectTransferSpecs.add(objectTransferSpec);
    return objectTransferSpec;
  }

  public PanelClassMappingSpec createPanelClassMappingSpec(Panel panel, ClassSpec classSpec) {
    PanelClassMappingSpec panelClassMappingSpec = new PanelClassMappingSpec(panel, classSpec);
    HypertextSpecFactory.panelClassMappingSpecs.add(panelClassMappingSpec);
    return panelClassMappingSpec;
  }

  public RepetitionClassMappingSpec createRepetitionClassMappingSpec(Repetition repetition, ClassSpec classSpec) {
    RepetitionClassMappingSpec repetitionClassMappingSpec = new RepetitionClassMappingSpec(repetition, classSpec);
    HypertextSpecFactory.repetitionClassMappingSpecs.add(repetitionClassMappingSpec);
    return repetitionClassMappingSpec;
  }

  public SaveActionSpec createSaveActionSpec(ClassMappingSpec spec) {
    SaveActionSpec saveActionSpec = new SaveActionSpec(spec);
    HypertextSpecFactory.saveActionSpecs.add(saveActionSpec);
    return saveActionSpec;
  }

  public SelectableRepetitionSpec createSelectableRepetitionSpec(Repetition repetition, ClassSpec classSpec, SelectionWidget selectable) {
    SelectableRepetitionSpec selectableRepetitionSpec = new SelectableRepetitionSpec(repetition, classSpec, selectable );
    HypertextSpecFactory.selectableRepetitionSpecs.add(selectableRepetitionSpec);
    return selectableRepetitionSpec;
  }

  public WidgetActionsSpec createWidgetActionsSpec(TriggerWidget trigger, List<ActionSpec> actions) {
    WidgetActionsSpec widgetActionsSpec = new WidgetActionsSpec(trigger, actions);
    HypertextSpecFactory.widgetActionsSpecs.add(widgetActionsSpec);
    return widgetActionsSpec;
  }

}
