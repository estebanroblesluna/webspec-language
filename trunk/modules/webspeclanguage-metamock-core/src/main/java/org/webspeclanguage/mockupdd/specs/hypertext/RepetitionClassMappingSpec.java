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

import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.sui.model.Repetition;


/**
 * @author Franco Giacosa
 */
public class RepetitionClassMappingSpec extends ClassMappingSpec {

  public Repetition widget;
  
  public RepetitionClassMappingSpec(Repetition repetition, ClassSpec classSpec) {
    super(classSpec);
    this.setWidget(repetition);
  }

  
  public Repetition getWidget() {
    return widget;
  }

  
  public void setWidget(Repetition widget) {
    this.widget = widget;
  }
}