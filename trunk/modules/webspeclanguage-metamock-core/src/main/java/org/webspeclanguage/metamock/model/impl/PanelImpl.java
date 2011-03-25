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

import org.webspeclanguage.metamock.model.CompositeControl;
import org.webspeclanguage.metamock.model.Panel;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Default implementation of {@link Panel}
 * 
 * @author Jose Matias Rivero
 */
public class PanelImpl extends CompositeControlImpl implements Panel {

  protected PanelImpl(String controlID, Integer x, Integer y, Integer width, Integer height, String containerId) {
    super(controlID, x, y, width, height, containerId);
  }

  public <T> T accept(MetaMockVisitor<T> v) {
    return v.visitPanel(this);
  }

  @Override
  protected CompositeControl createNewInstance() {
    return new PanelImpl(this.getControlId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getContainerId());
  }

}
