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
package org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit;

import java.util.*;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.EntityDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.DefaultUnitParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.Parameter;

/**
 * @author Franco Giacosa
 */
public class MultiChoiceIndexUnit extends ContentUnit {

  private Map<String,Parameter> defaultOutputParameters = new HashMap<String,Parameter>();

	public MultiChoiceIndexUnit(String id, String name, EntityDecorator entity) {
		super(id, name, entity);
		WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
	  WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    DefaultUnitParameter defaultUnitParameter1 = webFactory.createDefaultUnitParameter("Checked","Checked OID");
    defaultOutputParameters.put(defaultUnitParameter1.getName(),defaultUnitParameter1);
       
	}
  public Map<String, Parameter> getDefaultOutputParameters() {
    return defaultOutputParameters;
  }
  
  public void setDefaultOutputParameters(Map<String, Parameter> defaultOutputParameters) {
    this.defaultOutputParameters = defaultOutputParameters;
  }
	public void accept(WebModelVisitor visitor) {
		visitor.visit(this);
	}
  public Map<String,Parameter> getInputParameters() {
    return new HashMap<String,Parameter>();
  }
  public Map<String,Parameter> getOutputParameters() {
    Map<String,Parameter> outputParameters = new HashMap<String,Parameter>();
    outputParameters.putAll(this.getEntity().getParametersPool());
    outputParameters.putAll(this.getDefaultOutputParameters());
    return outputParameters;
  }
  

}
