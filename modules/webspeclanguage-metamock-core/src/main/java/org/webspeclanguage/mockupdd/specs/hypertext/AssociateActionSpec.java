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

package org.webspeclanguage.mockupdd.specs.hypertext;

import org.webspeclanguage.mockupdd.specs.data.*;

/**
 * Represents an Association between a {@link ClassSpec} and another {@link ClassSpec}
 * @author Franco Giacosa
 */
public class AssociateActionSpec extends ActionSpec {
  
  private ClassSpec type1;
  private ClassSpec type2;

  public AssociateActionSpec(ClassSpec type1, ClassSpec type2) {
    super();
    this.type1 = type1;
    this.type2 = type2;
  }
  
  public ClassSpec getType1() {
    return type1;
  }
  
  public void setType1(ClassSpec type1) {
    this.type1 = type1;
  }
  
  public ClassSpec getType2() {
    return type2;
  }
  
  public void setType2(ClassSpec type2) {
    this.type2 = type2;
  }
}
