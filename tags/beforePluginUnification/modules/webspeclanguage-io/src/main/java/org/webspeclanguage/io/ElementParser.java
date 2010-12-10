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
package org.webspeclanguage.io;

import org.xml.sax.Attributes;

/**
 * A parser for XML elements
 * 
 * @author Esteban Robles Luna
 */
public interface ElementParser {

  /**
   * Parses the element with attributes in the current parsing context
   * 
   * @param attributes the attributes of the element
   * @param context the parsing context
   */
  void parse(Attributes attributes, ParseContext context);

  /**
   * Handles the result of parsing a child element
   * 
   * @param result the object obtained from parsing a child element
   */
  void handleChild(Object result);

  /**
   * @return the result of parsing this element
   */
  Object getResult();

  /**
   * Post process the current element with its corresponding attributes
   * 
   * @param attributes the attributes of the element
   */
  void postProcess(Attributes attributes);
}
