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
package org.webspeclanguage.metamock.model.impl;

import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.utils.SuiVisitor;

/**
 * Default implementation of {@link org.webspeclanguage.metamock.model.List}
 * 
 * @author Jose Matias Rivero
 */
public class ListImpl extends SimpleControlImpl implements org.webspeclanguage.metamock.model.List {

  public ListImpl(String controlID, Integer x, Integer y, Integer width, Integer height) {
    super(controlID, x, y, width, height);
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitList(this);
  }

  @Override
  public Widget copyConcreteControl() {
    return new ListImpl(this.getControlId(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
  }
}
