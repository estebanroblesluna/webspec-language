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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.KeyConditionParameter;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.Parameter;

/**
 * @author Franco Giacosa
 */
public class DeleteUnit extends OperationUnit {

  private Selector selector;
	public DeleteUnit(String id, String name, EntityDecorator entity, Selector selector) {
		super(id, name, entity);
		this.selector = selector;
	}
	public void accept(WebModelVisitor visitor) {
		visitor.visit(this);
	}
  
  public Selector getSelector() {
    return selector;
  }
  
  public void setSelector(Selector selector) {
    this.selector = selector;
  }
  public Map<String,Parameter> getInputParameters() {
    Map<String,Parameter> inputParameters = new HashMap<String,Parameter>();
    WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
    Iterator<String> iteratorK = this.getSelector().getKeyConditions().keySet().iterator();
    while(iteratorK.hasNext()){
      String key = (String)iteratorK.next();
      KeyConditionParameter keyConditionParameter = webFactory.createKeyConditionParameter(this.getSelector().getKeyConditions().get(key));
      inputParameters.put(keyConditionParameter.getName(),keyConditionParameter);
    }
    return inputParameters; 
  }
  public Map<String,Parameter> getOutputParameters() {
    return new HashMap<String,Parameter>();
  }

}
