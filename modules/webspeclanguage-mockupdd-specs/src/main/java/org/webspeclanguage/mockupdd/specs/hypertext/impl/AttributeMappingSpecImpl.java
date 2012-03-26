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

package org.webspeclanguage.mockupdd.specs.hypertext.impl;

import org.webspeclanguage.mockupdd.specs.data.AttributeSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.AttributeMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ClassMappingSpec;
import org.webspeclanguage.mockupdd.sui.model.DataBoundWidget;
import org.webspeclanguage.mockupdd.sui.model.SimpleWidget;

/**
 * Represents an individual mapping between an {@link AttributeSpec} and a {@link SimpleWidget} in
 * the context of an {@link ClassMappingSpec} 
 * 
 * @author Jose Matias Rivero
 */
public class AttributeMappingSpecImpl implements AttributeMappingSpec {

  private SimpleWidget widget;
  private AttributeSpec attributeSpec;
  private DataBoundWidget dataSource;

  public AttributeMappingSpecImpl(SimpleWidget widget, AttributeSpec attributeSpec) {
    super();
    this.setWidget(widget);
    this.setAttributeSpec(attributeSpec);
  }

  public SimpleWidget getWidget() {
    return widget;
  }

  private void setWidget(SimpleWidget widget) {
    this.widget = widget;
  }

  public AttributeSpec getAttributeSpec() {
    return attributeSpec;
  }

  private void setAttributeSpec(AttributeSpec attributeSpec) {
    this.attributeSpec = attributeSpec;
  }

  @Override
  public DataBoundWidget getDataSource() {
    return this.dataSource;
  }

  @Override
  public void setDataSource(DataBoundWidget dataSource) {
    this.dataSource = dataSource;    
  }

}
