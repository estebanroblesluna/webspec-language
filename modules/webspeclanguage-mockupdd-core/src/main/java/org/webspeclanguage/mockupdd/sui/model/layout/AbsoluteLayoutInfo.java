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
package org.webspeclanguage.mockupdd.sui.model.layout;

import org.webspeclanguage.mockupdd.sui.model.Widget;

/**
 * @author Jose Matias Rivero
 */
public interface AbsoluteLayoutInfo extends LayoutInfo {

  int getX();
  int getY();
  int getWidth();
  int getHeight();
  Widget getWidget();

}
