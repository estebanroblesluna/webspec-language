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

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.AttributeDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.EntityDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.*;

import java.util.*;

/**
 * @author Franco Giacosa
 */
public class MultiEntryUnit extends ContentUnit {

  private Map<String,Field> fields = new HashMap<String,Field>();
  private String minLength = "1";
  private HashMap<String,Parameter> defaultOutputParameters = new HashMap<String,Parameter>();
	public MultiEntryUnit(String id, String name, EntityDecorator entity) {
		super(id, name, entity);
    WebModelFactory webFactory = new WebModelFactory();
    DefaultUnitParameter defaultUnitParameter1 = webFactory.createDefaultUnitParameter("Checked","Checked Objects");
    DefaultUnitParameter defaultUnitParameter2 = webFactory.createDefaultUnitParameter("key","Shown Objects");
		defaultOutputParameters.put(defaultUnitParameter1.getName(),defaultUnitParameter1);
		defaultOutputParameters.put(defaultUnitParameter2.getName(),defaultUnitParameter2);
		
		// TODO Auto-generated constructor stub
	}
	public void addField(Field field){
	  this.getFields().put(field.getId(), field);
	}
	public Map<String, Field> getFields() {
		return fields;
	}
	public void setFields(Map<String, Field> fields) {
		this.fields = fields;
	}
	public String getMinLength() {
	  return minLength;
	}
	public void setMinLength(String minLength) {
	   this.minLength = minLength;
	}
	public void accept(WebModelVisitor visitor) {
		visitor.visit(this);
	}
  public HashMap<String,Parameter> getDefaultOutputParameters() {
    return defaultOutputParameters;
  }
  public void createFields(){
    WebModelFactory webFactory = new WebModelFactory();

    if(this.getEntity() != null){
      Iterator<String> iteratorAttributes = this.getEntity().getAttributes().keySet().iterator();
      while(iteratorAttributes.hasNext()){
        String keyAttribute = (String)iteratorAttributes.next();
        AttributeDecorator attribute = this.getEntity().getAttributes().get(keyAttribute);
        this.addField(webFactory.createNormalField(attribute));
      }
    }
  }
  public void setDefaultOutputParameters(HashMap<String,Parameter> defaultOutputParameters) {
    this.defaultOutputParameters = defaultOutputParameters;
  }
  public HashMap<String,Parameter> getInputParameters() {
    HashMap<String,Parameter> inputParameters = new HashMap<String,Parameter>();
    
    Iterator<String> iteratorFields = this.getFields().keySet().iterator();
    
    while(iteratorFields.hasNext()){
      String key = (String)iteratorFields.next();
      ArrayList<Parameter> parameterList = this.getFields().get(key).getInputParameters();
      
      Iterator<Parameter> parameterIterator = parameterList.iterator();
      while(parameterIterator.hasNext()){
        Parameter parameter = (Parameter)parameterIterator.next();
        inputParameters.put(parameter.getName(),parameter);
      }
    }
    return inputParameters; 
  }
  public HashMap<String,Parameter> getOutputParameters() {
    HashMap<String,Parameter> outputParameters = new HashMap<String,Parameter>();
    
    Iterator<String> iteratorFields = this.getFields().keySet().iterator();
    
    while(iteratorFields.hasNext()){
      String key = (String)iteratorFields.next();
      ArrayList<Parameter> parameterList = this.getFields().get(key).getOutputParameters();
      
      Iterator<Parameter> parameterIterator = parameterList.iterator();
      while(parameterIterator.hasNext()){
        Parameter parameter = (Parameter)parameterIterator.next();
        outputParameters.put(parameter.getName(),parameter);
      }
    }
    outputParameters.putAll(this.getDefaultOutputParameters());
    return outputParameters; 
  }
}
