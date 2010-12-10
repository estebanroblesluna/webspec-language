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
package org.webspeclanguage.metamock.model;

import java.util.Collection;

import org.webspeclanguage.metamock.model.layout.Layout;

/**
 * Abstract interface containing common features present in composite controls
 * 
 * @author Jose Matias Rivero
 */
public interface CompositeControl extends UIControl {

  Collection<UIControl> getControls();

  Layout getLayout();

  void addChild(UIControl c);

  void removeChild(UIControl c);

  void setLayout(Layout layout);

  UIControl getControlById(String id);

  void setControls(Collection<UIControl> controls);

  String getContainerId();

  void removeAllChildren();

}
