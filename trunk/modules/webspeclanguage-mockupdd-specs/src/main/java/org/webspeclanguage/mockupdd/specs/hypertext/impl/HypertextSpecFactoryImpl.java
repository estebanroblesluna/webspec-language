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
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
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
  

  public <W extends CompositeWidget> AssociateActionSpec createAssociateActionSpec(ClassMappingSpec<W> type1, ClassMappingSpec<W> type2) {

    AssociateActionSpec associateActionSpec = new AssociateActionSpecImpl(type1, type2);
    return associateActionSpec;
 }

  public AttributeMappingSpec createAttributeMappingSpec(SimpleWidget widget, AttributeSpec attributeSpec) {
    AttributeMappingSpec attributeMappingSpec = new AttributeMappingSpecImpl(widget, attributeSpec);
    return attributeMappingSpec;
  }

  public <W extends CompositeWidget> DeleteActionSpec createDeleteActionSpec(ClassMappingSpec<W> spec) {
    DeleteActionSpec deleteActionSpec = new DeleteActionSpecImpl(spec);
    return deleteActionSpec;
  }

  public <W extends CompositeWidget> DissociateActionSpec createDissociateActionSpec(ClassMappingSpec<W> type1, ClassMappingSpec<W> type2) {
    DissociateActionSpec dissociateActionSpec = new DissociateActionSpecImpl(type1, type2);
    return dissociateActionSpec;
  }

  public InputPanelSpec createInputPanelSpec(Panel panel, ClassSpec classSpec, List<InputWidget> inputs) {
    InputPanelSpec inputPanelSpec = new InputPanelSpecImpl(panel, classSpec, inputs);
    return inputPanelSpec;
  }

  public NavigationSpec createNavigationSpec(Page to, TriggerWidget trigger, List<ActionSpec> actions, List<ObjectTransferSpec> transfers) {
    NavigationSpec navigationSpec = new NavigationSpecImpl(to, trigger, actions, transfers);
    return navigationSpec;
  }

  public ObjectTransferSpec createObjectTransferSpec(Widget from, Widget to) {
    ObjectTransferSpec objectTransferSpec = new ObjectTransferSpecImpl(from, to);
    return objectTransferSpec;
  }

  public PanelClassMappingSpec createPanelClassMappingSpec(Panel panel, ClassSpec classSpec) {
    PanelClassMappingSpec panelClassMappingSpec = new PanelClassMappingSpecImpl(panel, classSpec);
    return panelClassMappingSpec;
  }

  public RepetitionClassMappingSpec createRepetitionClassMappingSpec(Repetition repetition, ClassSpec classSpec) {
    RepetitionClassMappingSpec repetitionClassMappingSpec = new RepetitionClassMappingSpecImpl(repetition, classSpec);
    return repetitionClassMappingSpec;
  }

  public <W extends CompositeWidget> SaveActionSpec createSaveActionSpec(ClassMappingSpec<W> spec) {
    SaveActionSpec saveActionSpec = new SaveActionSpecImpl(spec);
    return saveActionSpec;
  }

  public SelectableRepetitionSpec createSelectableRepetitionSpec(Repetition repetition, ClassSpec classSpec, SelectionWidget selectable) {
    SelectableRepetitionSpec selectableRepetitionSpec = new SelectableRepetitionSpecImpl(repetition, classSpec, selectable );
    return selectableRepetitionSpec;
  }

  public WidgetActionsSpec createWidgetActionsSpec(TriggerWidget trigger, List<ActionSpec> actions) {
    WidgetActionsSpec widgetActionsSpec = new WidgetActionsSpecImpl(trigger, actions);
    return widgetActionsSpec;
  }

}
