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
package org.webspeclanguage.io.base;

import org.webspeclanguage.base.Diagram;
import org.webspeclanguage.base.Interaction;
import org.webspeclanguage.generator.Generator;
import org.webspeclanguage.io.AbstractElementParser;
import org.webspeclanguage.io.ParseContext;
import org.xml.sax.Attributes;

/**
 * A {@link Diagram} parser
 * 
 * @author Esteban Robles Luna
 */
public class DiagramParser extends AbstractElementParser {

  public DiagramParser() {
    this.registerChild(Interaction.class, "addInteraction");
    this.registerChild(Generator.class, "addGenerator");
  }
  
  /**
   * {@inheritDoc}
   */
  public void parse(Attributes attributes, ParseContext context) {
    Diagram diagram = new Diagram(attributes.getValue("name"));
    this.setResult(diagram);
  }
}
