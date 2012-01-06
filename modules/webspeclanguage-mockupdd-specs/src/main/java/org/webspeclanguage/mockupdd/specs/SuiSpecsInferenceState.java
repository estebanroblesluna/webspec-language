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
package org.webspeclanguage.mockupdd.specs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.AssociateActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.AttributeMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.DeleteActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.DissociateActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.InputPanelSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ObjectTransferSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.PanelClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.RepetitionClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SaveActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SelectableRepetitionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.WidgetActionsSpec;
import org.webspeclanguage.mockupdd.specs.processors.SuiModelProcessingError;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SimpleWidget;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.MultiArrayListHashMap;
import org.webspeclanguage.mockupdd.utils.MultiListMap;
import org.webspeclanguage.mockupdd.utils.TagIndexer;


/**
 * @author José Matías Rivero
 */
public class SuiSpecsInferenceState {

  private SuiModel model;
  private TagIndexer tagIndexer;
  private List<SuiModelProcessingError> errors;
  private MultiListMap<Page, NavigationSpec> navigationSpecsPerPage;
  private Map<String, ClassSpec> classSpecsByName;
  private List<NavigationSpec> navigationSpecs = new ArrayList<NavigationSpec>();
  private List<AssociateActionSpec> associateActionSpecs = new ArrayList<AssociateActionSpec>();
  private List<AttributeMappingSpec> attributeMappingSpecs = new ArrayList<AttributeMappingSpec>();
  private List<ClassMappingSpec> classMappingSpecs = new ArrayList<ClassMappingSpec>();
  private List<DeleteActionSpec> deleteActionSpecs = new ArrayList<DeleteActionSpec>();
  private List<DissociateActionSpec> dissociateActionSpecs = new ArrayList<DissociateActionSpec>();
  private List<InputPanelSpec> inputPanelSpecs = new ArrayList<InputPanelSpec>();
  private List<ObjectTransferSpec> objectTransferSpecs = new ArrayList<ObjectTransferSpec>();
  private List<PanelClassMappingSpec> panelClassMappingSpecs = new ArrayList<PanelClassMappingSpec>();
  private List<RepetitionClassMappingSpec> repetitionClassMappingSpecs = new ArrayList<RepetitionClassMappingSpec>();
  private List<SaveActionSpec> saveActionSpecs = new ArrayList<SaveActionSpec>();
  private List<SelectableRepetitionSpec> selectableRepetitionSpecs = new ArrayList<SelectableRepetitionSpec>();
  private List<WidgetActionsSpec> widgetActionsSpecs = new ArrayList<WidgetActionsSpec>();
  private Map<Widget, ClassMappingSpec<CompositeWidget>> classMappingSpecsByWidget;
  private Map<SimpleWidget, AttributeMappingSpec> attributeMappingSpecsByWidget;

  public SuiSpecsInferenceState(SuiModel model) {
    super();
    this.model = model;
    this.tagIndexer = new TagIndexer(model);
    this.errors = new ArrayList<SuiModelProcessingError>();
    this.navigationSpecsPerPage = new MultiArrayListHashMap<Page, NavigationSpec>();
    this.classSpecsByName = new HashMap<String, ClassSpec>();
    this.classMappingSpecsByWidget = new HashMap<Widget, ClassMappingSpec<CompositeWidget>>();
    this.attributeMappingSpecsByWidget = new HashMap<SimpleWidget, AttributeMappingSpec>();
  }

  public Page getPageByWidget(Widget widget){
	  Page selectedPage = null;
	  Iterator<Page> pIt = this.getModel().getPages().iterator();
	  while((pIt.hasNext()) && (selectedPage == null)){
		  Page p = pIt.next();
		  if(p.getWidgetById(widget.getWidgetId()) != null ){
			  selectedPage = p; 
		  }
	  }	  
	  return selectedPage;
  }
  
  public List<NavigationSpec> getNavigationSpecsForPage(Page p) {
    return Collections.unmodifiableList(this.getNavigationSpecsPerPage().get(p));
  }

  public void addNavigationSpec(NavigationSpec ns) {
    Validate.notNull(ns);
    
    this.getNavigationSpecsPerPage().putOnce(ns.getTrigger().getPage(), ns);
    this.navigationSpecs.add(ns);
  }

  public MultiListMap<Page, NavigationSpec> getNavigationSpecsPerPage() {
    return navigationSpecsPerPage;
  }

  public List<SuiModelProcessingError> getErrors() {
    return Collections.unmodifiableList(errors);
  }

  public void addError(SuiModelProcessingError error) {
    Validate.notNull(error);
    
    this.errors.add(error);
  }
  
  public ClassSpec getClassSpecByName(String name) {
    return this.classSpecsByName.get(name);
  }

  public ClassSpec addClassSpec(ClassSpec cs) {
    Validate.notNull(cs);
    
    this.classSpecsByName.put(cs.getName(), cs);
    return cs;
  }
  
  public Collection<ClassSpec> getClassSpecs() {
    return this.classSpecsByName.values();
  }
  
  public TagIndexer getTagIndexer() {
    return this.tagIndexer;
  }
  
  public SuiModel getModel() {
    return this.model;
  }

  public void addClassMappingSpec(ClassMappingSpec<CompositeWidget> mapping) {
    Validate.notNull(mapping);
    
    this.classMappingSpecsByWidget.put(mapping.getWidget(), mapping);
  }
  
  public ClassMappingSpec<CompositeWidget> getClassMappingSpecForWidget(CompositeWidget w) {
    return this.classMappingSpecsByWidget.get(w);
  }

  public void addAttributeMappingSpec(AttributeMappingSpec ams) {
    Validate.notNull(ams);
    Validate.notNull(ams.getWidget());
    
    this.attributeMappingSpecsByWidget.put(ams.getWidget(), ams);
  }
  
  public AttributeMappingSpec getAttributeMappingSpecForWidget(SimpleWidget w) {
    return this.attributeMappingSpecsByWidget.get(w);
  }


public List<ObjectTransferSpec> getObjectTransferSpecs() {
	return objectTransferSpecs;
}

public void setInputPanelSpecs(List<InputPanelSpec> inputPanelSpecs) {
	this.inputPanelSpecs = inputPanelSpecs;
}

public List<InputPanelSpec> getInputPanelSpecs() {
	return inputPanelSpecs;
}

public void setDissociateActionSpecs(List<DissociateActionSpec> dissociateActionSpecs) {
	this.dissociateActionSpecs = dissociateActionSpecs;
}

public List<DissociateActionSpec> getDissociateActionSpecs() {
	return dissociateActionSpecs;
}

public void setDeleteActionSpecs(List<DeleteActionSpec> deleteActionSpecs) {
	this.deleteActionSpecs = deleteActionSpecs;
}

public List<DeleteActionSpec> getDeleteActionSpecs() {
	return deleteActionSpecs;
}

public void setAttributeMappingSpecs(List<AttributeMappingSpec> attributeMappingSpecs) {
	this.attributeMappingSpecs = attributeMappingSpecs;
}

public List<AttributeMappingSpec> getAttributeMappingSpecs() {
	return attributeMappingSpecs;
}

public void setAssociateActionSpecs(List<AssociateActionSpec> associateActionSpecs) {
	this.associateActionSpecs = associateActionSpecs;
}

public List<AssociateActionSpec> getAssociateActionSpecs() {
	return associateActionSpecs;
}

public void setPanelClassMappingSpecs(List<PanelClassMappingSpec> panelClassMappingSpecs) {
	this.panelClassMappingSpecs = panelClassMappingSpecs;
}

public List<PanelClassMappingSpec> getPanelClassMappingSpecs() {
	return panelClassMappingSpecs;
}

public void setRepetitionClassMappingSpecs(
		List<RepetitionClassMappingSpec> repetitionClassMappingSpecs) {
	this.repetitionClassMappingSpecs = repetitionClassMappingSpecs;
}

public List<RepetitionClassMappingSpec> getRepetitionClassMappingSpecs() {
	return repetitionClassMappingSpecs;
}

public void setSaveActionSpecs(List<SaveActionSpec> saveActionSpecs) {
	this.saveActionSpecs = saveActionSpecs;
}

public List<SaveActionSpec> getSaveActionSpecs() {
	return saveActionSpecs;
}

public void setSelectableRepetitionSpecs(List<SelectableRepetitionSpec> selectableRepetitionSpecs) {
	this.selectableRepetitionSpecs = selectableRepetitionSpecs;
}

public List<SelectableRepetitionSpec> getSelectableRepetitionSpecs() {
	return selectableRepetitionSpecs;
}

public void setWidgetActionsSpecs(List<WidgetActionsSpec> widgetActionsSpecs) {
	this.widgetActionsSpecs = widgetActionsSpecs;
}

public List<WidgetActionsSpec> getWidgetActionsSpecs() {
	return widgetActionsSpecs;
}

public void setNavigationSpecs(List<NavigationSpec> navigationSpecs) {
	this.navigationSpecs = navigationSpecs;
}

public List<NavigationSpec> getNavigationSpecs() {
	return Collections.unmodifiableList(navigationSpecs);
}

}
