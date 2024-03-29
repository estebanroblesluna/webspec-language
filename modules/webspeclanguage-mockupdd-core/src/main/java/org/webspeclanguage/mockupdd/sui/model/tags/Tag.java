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
package org.webspeclanguage.mockupdd.sui.model.tags;

import java.util.Collection;
import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.Widget;

/**
 * @author Jose Matias Rivero
 */
public interface Tag {

  String getName();

  java.util.List<TagParameter> getParameters();

  TagSet getTagSet();

  Collection<Class< ? extends Widget>> applicableOver();

  TagApplication applyOver(Widget widget, List<TagParameterValue> parameterValues) throws TagApplicationException;

  TagApplication applyOverWithValues(Widget w, List<String> paramValues) throws TagApplicationException;

  boolean isParameterStrict();

}
