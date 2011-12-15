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

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;

/**
 * @author Franco Giacosa
 */
public class KeyCondition {

    private String id;
    private String name;
    private String predicate = "in";
    private Boolean implied = false;
    private AttributeDecorator key;
    
    public KeyCondition(String id, String name){
      this.id = id;
      this.name = name;
    }
    public String getId() {
      return id;
    }  
    public void setId(String id) {
      this.id = id;
    }
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getPredicate() {
      return predicate;
    }
    public void setPredicate(String predicate) {
      this.predicate = predicate;
    }
    public Boolean getImplied() {
      return implied;
    }
    public void setImplied(Boolean required) {
      this.implied = required;
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
