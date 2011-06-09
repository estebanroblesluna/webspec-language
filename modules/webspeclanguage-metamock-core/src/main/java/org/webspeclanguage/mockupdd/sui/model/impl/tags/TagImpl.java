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
package org.webspeclanguage.mockupdd.sui.model.impl.tags;

import java.util.Collection;
import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.Tag;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameter;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameterValue;

/**
 * @author Jose Matias Rivero
 */
public class TagImpl implements Tag {

  private String name;
  private List<TagParameter> parameters;
  private Collection<Class<? extends Widget>> applicableOver;
  
  public TagImpl(String name, List<TagParameter> parameters, Collection<Class< ? extends Widget>> applicableOver) {
    super();
    this.setName(name);
    this.setParameters(parameters);
    this.setApplicableOver(applicableOver);
  }

  private void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  private void setParameters(List<TagParameter> parameters) {
    this.parameters = parameters;
  }

  public List<TagParameter> getParameters() {
    return parameters;
  }

  private void setApplicableOver(Collection<Class<? extends Widget>> applicableOver) {
    this.applicableOver = applicableOver;
  }

  public Collection<Class<? extends Widget>> applicableOver() {
    return applicableOver;
  }

  public TagApplication applyOver(Widget widget, List<TagParameterValue> parameterValues) throws TagApplicationException {
    return new TagApplicationImpl(widget, this, parameterValues);
  }
  
}
