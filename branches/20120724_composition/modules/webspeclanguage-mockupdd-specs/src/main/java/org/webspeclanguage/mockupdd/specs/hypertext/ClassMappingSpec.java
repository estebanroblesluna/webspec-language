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

import java.util.List;

import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.sui.model.DataBoundWidget;

/**
 * Represents a mapping between a {@link DataBoundWidget} and a {@link ClassSpec}. 
 * The semantics of this spec is that the {@link DataBoundWidget} reads or manages in some way
 * instances of the class represented by the {@link ClassSpec}.
 * 
 * @author Jose Matias Rivero
 */
public interface ClassMappingSpec<W extends DataBoundWidget> extends MappingSpec<W> {
  
  /**
   * @return The class mapped to the Widget
   */
  ClassSpec getClassSpec();
  
  /**
   * @return The list of attributes mapped from the class
   */
  List<AttributeMappingSpec> getAttributeMappings();

  void addAttributeMapping(AttributeMappingSpec attributeMappingSpec);

}