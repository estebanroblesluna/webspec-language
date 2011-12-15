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

package org.webspeclanguage.mockupdd.specs.hypertext.impl;

import java.util.ArrayList;
import java.util.List;
import org.webspeclanguage.mockupdd.specs.data.AttributeSpec;
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.AssociateActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.AttributeMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.DeleteActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.DissociateActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.HypertextSpecFactory;
import org.webspeclanguage.mockupdd.specs.hypertext.InputPanelSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ObjectTransferSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.PanelClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.RepetitionClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SaveActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SelectableRepetitionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.WidgetActionsSpec;
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
  
  private List<AssociateActionSpec> associateActionSpecs = new ArrayList<AssociateActionSpec>();
  private List<AttributeMappingSpec> attributeMappingSpecs = new ArrayList<AttributeMappingSpec>();
  private List<ClassMappingSpec> classMappingSpecs = new ArrayList<ClassMappingSpec>();
  private List<DeleteActionSpec> deleteActionSpecs = new ArrayList<DeleteActionSpec>();
  private List<DissociateActionSpec> dissociateActionSpecs = new ArrayList<DissociateActionSpec>();
  private List<InputPanelSpec> inputPanelSpecs = new ArrayList<InputPanelSpec>();
  private List<NavigationSpec> navigationSpecs = new ArrayList<NavigationSpec>();
  private List<ObjectTransferSpec> objectTransferSpecs = new ArrayList<ObjectTransferSpec>();
  private List<PanelClassMappingSpec> panelClassMappingSpecs = new ArrayList<PanelClassMappingSpec>();
  private List<RepetitionClassMappingSpec> repetitionClassMappingSpecs = new ArrayList<RepetitionClassMappingSpec>();
  private List<SaveActionSpec> saveActionSpecs = new ArrayList<SaveActionSpec>();
  private List<SelectableRepetitionSpec> selectableRepetitionSpecs = new ArrayList<SelectableRepetitionSpec>();
  private List<WidgetActionsSpec> widgetActionsSpecs = new ArrayList<WidgetActionsSpec>();
  
  public AssociateActionSpec createAssociateActionSpec(ClassMappingSpec type1, ClassMappingSpec type2) {
    AssociateActionSpec associateActionSpec = new AssociateActionSpecImpl(type1, type2);
    this.getAssociateActionSpecs().add(associateActionSpec);
    return associateActionSpec;
 }

  public AttributeMappingSpec createAttributeMappingSpec(SimpleWidget widget, AttributeSpec attributeSpec) {
    AttributeMappingSpec attributeMappingSpec = new AttributeMappingSpecImpl(widget, attributeSpec);
    this.getAttributeMappingSpecs().add(attributeMappingSpec);
    return attributeMappingSpec;
  }

  public ClassMappingSpec createClassMappingSpec(ClassSpec classSpec) {
    ClassMappingSpec classMappingSpec = new ClassMappingSpecImpl(classSpec);
    this.getClassMappingSpecs().add(classMappingSpec);
    return classMappingSpec;
  }

  public DeleteActionSpec createDeleteActionSpec(ClassMappingSpec spec) {
    DeleteActionSpec deleteActionSpec = new DeleteActionSpecImpl(spec);
    this.getDeleteActionSpecs().add(deleteActionSpec);
    return deleteActionSpec;
  }

  public DissociateActionSpec createDissociateActionSpec(ClassMappingSpec type1, ClassMappingSpec type2) {
    DissociateActionSpec dissociateActionSpec = new DissociateActionSpecImpl(type1, type2);
    this.getDissociateActionSpecs().add(dissociateActionSpec);
    return dissociateActionSpec;
  }

  public InputPanelSpec createInputPanelSpec(Panel panel, ClassSpec classSpec, List<InputWidget> inputs) {
    InputPanelSpec inputPanelSpec = new InputPanelSpecImpl(panel, classSpec, inputs);
    this.getInputPanelSpecs().add(inputPanelSpec);
    return inputPanelSpec;
  }

  public NavigationSpec createNavigationSpec(Page to, TriggerWidget trigger, List<ActionSpec> actions, List<ObjectTransferSpec> transfers) {
    NavigationSpec navigationSpec = new NavigationSpecImpl(to, trigger, actions, transfers);
    this.getNavigationSpecs().add(navigationSpec);
    return navigationSpec;
  }

  public ObjectTransferSpec createObjectTransferSpec(Widget from, Widget to) {
    ObjectTransferSpec objectTransferSpec = new ObjectTransferSpecImpl(from, to);
    this.getObjectTransferSpecs().add(objectTransferSpec);
    return objectTransferSpec;
  }

  public PanelClassMappingSpec createPanelClassMappingSpec(Panel panel, ClassSpec classSpec) {
    PanelClassMappingSpec panelClassMappingSpec = new PanelClassMappingSpecImpl(panel, classSpec);
    this.getPanelClassMappingSpecs().add(panelClassMappingSpec);
    return panelClassMappingSpec;
  }

  public RepetitionClassMappingSpec createRepetitionClassMappingSpec(Repetition repetition, ClassSpec classSpec) {
    RepetitionClassMappingSpec repetitionClassMappingSpec = new RepetitionClassMappingSpecImpl(repetition, classSpec);
    this.getRepetitionClassMappingSpecs().add(repetitionClassMappingSpec);
    return repetitionClassMappingSpec;
  }

  public SaveActionSpec createSaveActionSpec(ClassMappingSpec spec) {
    SaveActionSpec saveActionSpec = new SaveActionSpecImpl(spec);
    this.getSaveActionSpecs().add(saveActionSpec);
    return saveActionSpec;
  }

  public SelectableRepetitionSpec createSelectableRepetitionSpec(Repetition repetition, ClassSpec classSpec, SelectionWidget selectable) {
    SelectableRepetitionSpec selectableRepetitionSpec = new SelectableRepetitionSpecImpl(repetition, classSpec, selectable );
    this.getSelectableRepetitionSpecs().add(selectableRepetitionSpec);
    return selectableRepetitionSpec;
  }

  public WidgetActionsSpec createWidgetActionsSpec(TriggerWidget trigger, List<ActionSpec> actions) {
    WidgetActionsSpec widgetActionsSpec = new WidgetActionsSpecImpl(trigger, actions);
    this.getWidgetActionsSpecs().add(widgetActionsSpec);
    return widgetActionsSpec;
  }
  
  
  public List<AssociateActionSpec> getAssociateActionSpecs() {
    return associateActionSpecs;
  }

  
  public void setAssociateActionSpecs(List<AssociateActionSpec> associateActionSpecs) {
    this.associateActionSpecs = associateActionSpecs;
  }

  
  public List<AttributeMappingSpec> getAttributeMappingSpecs() {
    return attributeMappingSpecs;
  }

  
  public void setAttributeMappingSpecs(List<AttributeMappingSpec> attributeMappingSpecs) {
    this.attributeMappingSpecs = attributeMappingSpecs;
  }

  
  public List<ClassMappingSpec> getClassMappingSpecs() {
    return classMappingSpecs;
  }

  
  public void setClassMappingSpecs(List<ClassMappingSpec> classMappingSpecs) {
    this.classMappingSpecs = classMappingSpecs;
  }

  
  public List<DeleteActionSpec> getDeleteActionSpecs() {
    return deleteActionSpecs;
  }

  
  public void setDeleteActionSpecs(List<DeleteActionSpec> deleteActionSpecs) {
    this.deleteActionSpecs = deleteActionSpecs;
  }

  
  public List<DissociateActionSpec> getDissociateActionSpecs() {
    return dissociateActionSpecs;
  }

  
  public void setDissociateActionSpecs(List<DissociateActionSpec> dissociateActionSpecs) {
    this.dissociateActionSpecs = dissociateActionSpecs;
  }

  
  public List<InputPanelSpec> getInputPanelSpecs() {
    return inputPanelSpecs;
  }

  
  public void setInputPanelSpecs(List<InputPanelSpec> inputPanelSpecs) {
    this.inputPanelSpecs = inputPanelSpecs;
  }

  
  public List<NavigationSpec> getNavigationSpecs() {
    return navigationSpecs;
  }

  
  public void setNavigationSpecs(List<NavigationSpec> navigationSpecs) {
    this.navigationSpecs = navigationSpecs;
  }

  
  public List<ObjectTransferSpec> getObjectTransferSpecs() {
    return objectTransferSpecs;
  }

  
  public void setObjectTransferSpecs(List<ObjectTransferSpec> objectTransferSpecs) {
    this.objectTransferSpecs = objectTransferSpecs;
  }

  
  public List<PanelClassMappingSpec> getPanelClassMappingSpecs() {
    return panelClassMappingSpecs;
  }

  
  public void setPanelClassMappingSpecs(List<PanelClassMappingSpec> panelClassMappingSpecs) {
    this.panelClassMappingSpecs = panelClassMappingSpecs;
  }

  
  public List<RepetitionClassMappingSpec> getRepetitionClassMappingSpecs() {
    return repetitionClassMappingSpecs;
  }

  
  public void setRepetitionClassMappingSpecs(List<RepetitionClassMappingSpec> repetitionClassMappingSpecs) {
    this.repetitionClassMappingSpecs = repetitionClassMappingSpecs;
  }

  
  public List<SaveActionSpec> getSaveActionSpecs() {
    return saveActionSpecs;
  }

  
  public void setSaveActionSpecs(List<SaveActionSpec> saveActionSpecs) {
    this.saveActionSpecs = saveActionSpecs;
  }

  
  public List<SelectableRepetitionSpec> getSelectableRepetitionSpecs() {
    return selectableRepetitionSpecs;
  }

  
  public void setSelectableRepetitionSpecs(List<SelectableRepetitionSpec> selectableRepetitionSpecs) {
    this.selectableRepetitionSpecs = selectableRepetitionSpecs;
  }

  
  public List<WidgetActionsSpec> getWidgetActionsSpecs() {
    return widgetActionsSpecs;
  }

  
  public void setWidgetActionsSpecs(List<WidgetActionsSpec> widgetActionsSpecs) {
    this.widgetActionsSpecs = widgetActionsSpecs;
  }


}
