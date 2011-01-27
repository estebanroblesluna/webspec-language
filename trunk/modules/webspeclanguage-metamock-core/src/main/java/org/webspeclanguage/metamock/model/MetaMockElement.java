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

import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Abstract interface defining common features that must be present in any type
 * of widget in our model
 * 
 * @author Jose Matias Rivero
 */
public interface MetaMockElement {

  <T> T visit(MetaMockVisitor<T> v);

  Object copy();
}