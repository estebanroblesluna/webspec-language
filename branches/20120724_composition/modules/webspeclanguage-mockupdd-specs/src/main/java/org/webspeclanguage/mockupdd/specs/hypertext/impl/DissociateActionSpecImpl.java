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

package org.webspeclanguage.mockupdd.specs.hypertext.impl;

import org.webspeclanguage.mockupdd.specs.hypertext.ActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.DissociateActionSpec;



/**
 * Represents a Dissociation between a {@link ClassClassMappingSpec} and another {@link ClassClassMappingSpec} 
 * who were Associated in some point
 * @author Franco Giacosa
 */
public class DissociateActionSpecImpl implements ActionSpec, DissociateActionSpec {

  private ClassMappingSpec type1;
  private ClassMappingSpec type2;
  
  public DissociateActionSpecImpl(ClassMappingSpec type1, ClassMappingSpec type2) {
    super();
    this.type1 = type1;
    this.type2 = type2;
  }
  
  public ClassMappingSpec getType1() {
    return type1;
  }
  
  public void setType1(ClassMappingSpec type1) {
    this.type1 = type1;
  }
  
  public ClassMappingSpec getType2() {
    return type2;
  }
  
  public void setType2(ClassMappingSpec type2) {
    this.type2 = type2;
  }
}
