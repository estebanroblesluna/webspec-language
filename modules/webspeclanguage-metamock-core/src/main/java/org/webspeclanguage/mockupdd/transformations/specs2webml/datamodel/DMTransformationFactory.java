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

package org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel;

import java.util.*;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.EntityDecorator;
import org.webspeclanguage.mockupdd.specs.data.*;


/**
 * @author Franco Giacosa
 */
public interface DMTransformationFactory {

      final List<AttributeSpec2Attribute> attributeSpec2Attributes = new ArrayList<AttributeSpec2Attribute>();
      final List<AttributeTypeSpec2Type> attributeTypeSpec2Types = new ArrayList<AttributeTypeSpec2Type>();
      final List<ClassSpec2Entity> classSpec2Entitys = new ArrayList<ClassSpec2Entity>();
      
      AttributeSpec2Attribute transformAttributeSpec2Attribute(AttributeSpec attributeSpec);
      AttributeTypeSpec2Type transformAttributeTypeSpec2Type(AttributeTypeSpec attributeTypeSpec);
      ClassSpec2Entity transformClassSpec2Entity(ClassSpec classSpec);
      EntityDecorator getEntity(String classSpecName);
}
