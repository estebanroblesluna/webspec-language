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
public interface HypertextSpecFactory {

  <W extends CompositeWidget> AssociateActionSpec createAssociateActionSpec(ClassMappingSpec<W> type1, ClassMappingSpec<W> type2);
  
  AttributeMappingSpec createAttributeMappingSpec(SimpleWidget widget, AttributeSpec attributeSpec);
  
  <W extends CompositeWidget> DeleteActionSpec createDeleteActionSpec(ClassMappingSpec<W> spec);
  
  <W extends CompositeWidget> DissociateActionSpec createDissociateActionSpec(ClassMappingSpec<W> type1, ClassMappingSpec<W> type2);
  
  InputPanelSpec createInputPanelSpec(Panel panel, ClassSpec classSpec, List<InputWidget> inputs);
  
  NavigationSpec createNavigationSpec(Page to, TriggerWidget trigger, List<ActionSpec> actions, List<ObjectTransferSpec> transfers);
  
  ObjectTransferSpec createObjectTransferSpec(Widget from, Widget to);
  
  PanelClassMappingSpec createPanelClassMappingSpec(Panel panel, ClassSpec classSpec);
  
  RepetitionClassMappingSpec createRepetitionClassMappingSpec(Repetition repetition, ClassSpec classSpec);
  
  <W extends CompositeWidget> SaveActionSpec createSaveActionSpec(ClassMappingSpec<W> spec);
  
  SelectableRepetitionSpec createSelectableRepetitionSpec(Repetition repetition, ClassSpec classSpec, SelectionWidget selectable);
  
  WidgetActionsSpec createWidgetActionsSpec(TriggerWidget trigger, List<ActionSpec> actions);

}
