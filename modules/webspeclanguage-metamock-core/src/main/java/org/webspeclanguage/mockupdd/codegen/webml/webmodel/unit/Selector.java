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
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;
/**
 * @author Franco Giacosa
 */
public class Selector {
  
    private String id;
    private String defaultPolicy = "Fill";
    private String booleanOperator = "and";
    private AttributeDecorator key;
    private Map<String,KeyCondition> keyConditions = new HashMap<String,KeyCondition>();
    
    public Selector(String id, AttributeDecorator key){
      this.id = id;
      this.key = key;
      
    }
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    public String getDefaultPolicy() {
      return defaultPolicy;
    }
    public void setDefaultPolicy(String defaultPolicy) {
      this.defaultPolicy = defaultPolicy;
    }
    public String getBooleanOperator() {
      return booleanOperator;
    }
    public void setBooleanOperator(String booleanOperation) {
      this.booleanOperator = booleanOperation;
    }
    public Map<String, KeyCondition> getKeyConditions() {
      return keyConditions;
    }
    public void setKeyConditions(Map<String, KeyCondition> keyConditions) {
      this.keyConditions = keyConditions;
    }
    public void addKeyCondition(KeyCondition keyCondition){
      keyCondition.setKey(this.getKey());
      this.getKeyConditions().put(keyCondition.getId(), keyCondition);
    }
    public void accept(WebModelVisitor visitor) {
      visitor.visit(this);
    }
    public AttributeDecorator getKey() {
      return key;
    }
    public void setKey(AttributeDecorator key) {
      this.key = key;
    }
}
