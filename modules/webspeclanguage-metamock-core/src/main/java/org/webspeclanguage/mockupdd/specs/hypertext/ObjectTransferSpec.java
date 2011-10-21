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

import org.webspeclanguage.mockupdd.sui.model.Widget;

/**
 * Represents an individual object transfer from one {@link Page} to another in the context of a 
 * {@link NavigationSpec}
 * 
 * @author Jose Matias Rivero
 */
public class ObjectTransferSpec {

  private Widget from;
  private Widget to;

  public ObjectTransferSpec(Widget fom, Widget to) {
    super();
    this.setFrom(fom);
    this.setTo(to);
  }

  public Widget getFrom() {
    return from;
  }

  private void setFrom(Widget from) {
    this.from = from;
  }

  public Widget getTo() {
    return to;
  }

  private void setTo(Widget to) {
    this.to = to;
  }

}
