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

import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;
import org.webspeclanguage.mockupdd.specs.processors.SuiModelProcessingError;
import org.webspeclanguage.mockupdd.sui.model.Page;
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
  private MultiListMap<Page, NavigationSpec> navigationSpecs;
  private Map<String, ClassSpec> classSpecsByName;

  public SuiSpecsInferenceState(SuiModel model) {
    super();
    this.model = model;
    this.tagIndexer = new TagIndexer(model);
    this.errors = new ArrayList<SuiModelProcessingError>();
    this.navigationSpecs = new MultiArrayListHashMap<Page, NavigationSpec>();
    this.classSpecsByName = new HashMap<String, ClassSpec>();
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
    return Collections.unmodifiableList(this.getNavigationSpecs().get(p));
  }

  public void addNavigationSpec(NavigationSpec ns) {
    this.getNavigationSpecs().putOnce(ns.getTrigger().getPage(), ns);
  }

  private MultiListMap<Page, NavigationSpec> getNavigationSpecs() {
    return navigationSpecs;
  }

  public List<SuiModelProcessingError> getErrors() {
    return Collections.unmodifiableList(errors);
  }

  public void addError(SuiModelProcessingError error) {
    this.getErrors().add(error);
  }
  
  public ClassSpec getClassSpecByName(String name) {
    return this.classSpecsByName.get(name);
  }

  public ClassSpec addClassSpec(ClassSpec classSpec) {
    return this.classSpecsByName.put(classSpec.getName(), classSpec);
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

}
