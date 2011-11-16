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

package org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel;

import org.webspeclanguage.mockupdd.specs.hypertext.DeleteActionSpec;


/**
 * @author Franco Giacosa
 */
public class DeleteActionSpec2DeleteUnit {

  public DeleteActionSpec deleteActionSpec;

  
  public DeleteActionSpec2DeleteUnit(DeleteActionSpec deleteActionSpec) {
    super();
    this.deleteActionSpec = deleteActionSpec;
  }

  public void transform(){
    
  }

  public DeleteActionSpec getDeleteActionSpec() {
    return deleteActionSpec;
  }

  
  public void setDeleteActionSpec(DeleteActionSpec deleteActionSpec) {
    this.deleteActionSpec = deleteActionSpec;
  }
}