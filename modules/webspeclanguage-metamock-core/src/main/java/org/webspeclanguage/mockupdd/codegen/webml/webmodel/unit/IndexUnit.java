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

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;
/**
 * @author Franco Giacosa
 */
public class IndexUnit extends ContentUnit {

	public IndexUnit(String id, String name, EntityDecorator entity) {
		super(id, name, entity);
		// TODO Auto-generated constructor stub
	}
	public void accept(WebModelVisitor visitor) {
		visitor.visit(this);
	}
	public Parameter getParameterKey(){
    AttributeDecorator attributeKey = this.getEntity().getEntityKey();
    WebModelFactory webFactory = new WebModelFactory();

    CurrentOIDParameter currentOIDParameter = webFactory.createCurrentOIDParameter(attributeKey);
    return currentOIDParameter;
	}
  public HashMap<String,Parameter> getInputParameters() {
    HashMap<String,Parameter> inputParameters = new HashMap<String,Parameter>();
    WebModelFactory webFactory = new WebModelFactory();
    AttributeDecorator attributeKey = this.getEntity().getEntityKey();
    CurrentOIDParameter currentOIDParameter = webFactory.createCurrentOIDParameter(attributeKey);
    inputParameters.put(currentOIDParameter.getName(),currentOIDParameter);
    return inputParameters;
  }
  public HashMap<String,Parameter> getOutputParameters() {
    
    return this.getEntity().getParametersPool();
  }
}
