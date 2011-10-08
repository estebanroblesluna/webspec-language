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
package org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field;

import java.util.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Type;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;
/**
 * @author Franco Giacosa
 */
public class SelectionField extends Field {

	
    public SelectionField(String id, String name, Type type) {
	       super(id, name, type);
	  }
	  public void accept(WebModelVisitor visitor){
	    visitor.visit(this);
	  }
	  public List<Parameter> getInputParameters(){
	    List<Parameter> parameters = new ArrayList<Parameter>();
	    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
	    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
	    parameters.add(webFactory.createSelectionFieldPreselectionParameter(this.getId()+"_presel",this.getName()+"- Preselection",this));
      parameters.add(webFactory.createSelectionFieldLabelParameter(this.getId()+"_label",this.getName()+" [label]",this));
      parameters.add(webFactory.createSelectionFieldOutputParameter(this.getId()+"_output",this.getName()+" [output]",this));

	    return parameters;
	  }
	  public List<Parameter> getOutputParameters(){
	    List<Parameter> parameters = new ArrayList<Parameter>();
	    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
	    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
      
      parameters.add(webFactory.createOutputSelectionFieldParameter(this));
      
      return parameters;
	  }
}
