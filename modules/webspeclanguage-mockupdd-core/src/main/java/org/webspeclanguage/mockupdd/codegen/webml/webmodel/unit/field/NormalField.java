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

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Type;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;
import java.util.*;

/**
 * @author Franco Giacosa
 */
public class NormalField extends Field{


  private Boolean preloaded = true;
	private Boolean modifiable = true;
	
  public NormalField(String id, String name, Type type) {
    super(id, name, type);
  }
	public Boolean getPreloaded() {
		return preloaded;
	}
	public void setPreloaded(Boolean preloaded) {
		this.preloaded = preloaded;
	}
	public Boolean getModifiable() {
		return modifiable;
	}
	public void setModifiable(Boolean modifiable) {
		this.modifiable = modifiable;
	}
	public void accept(WebModelVisitor visitor){
	  visitor.visit(this);
	}
  @Override
  public List<Parameter> getInputParameters() {
    List<Parameter> parameters = new ArrayList<Parameter>();
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    
    if(preloaded){
      parameters.add(webFactory.createNormalFieldParameter(this));
    }
    return parameters;  
  }
  @Override
  public List<Parameter> getOutputParameters() {
    List<Parameter> parameters = new ArrayList<Parameter>();
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();

    parameters.add(webFactory.createNormalFieldParameter(this));
    
    return parameters; 
  }
	
}
