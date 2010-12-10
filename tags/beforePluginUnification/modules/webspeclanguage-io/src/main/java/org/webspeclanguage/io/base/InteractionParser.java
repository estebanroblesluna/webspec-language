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

import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.impl.core.InteractionImpl;
import org.webspeclanguage.impl.widget.Widget;
import org.webspeclanguage.io.AbstractElementParser;
import org.webspeclanguage.io.ParseContext;
import org.xml.sax.Attributes;

/**
 * A {@link Interaction} parser
 * 
 * @author Esteban Robles Luna
 */
public class InteractionParser extends AbstractElementParser {

  private String invariant;

  public InteractionParser() {
    this.registerChild(Widget.class, "addWidget");
  }

  /**
   * {@inheritDoc}
   */
  public void parse(Attributes attributes, ParseContext context) {
    String starting = attributes.getValue("starting");
    Interaction interaction = new InteractionImpl(
            attributes.getValue("name"), 
            starting != null && starting.equalsIgnoreCase("true"));
    
    interaction.setLocation(attributes.getValue("location"));
    this.invariant = attributes.getValue("invariant");
    this.setResult(interaction);
    
    context.put(interaction.getName() + "-Interaction", interaction);
  }
  
  /**
   * {@inheritDoc}
   */
  public void postProcess(Attributes attributes) {
    ((Interaction) this.getResult()).setInvariant(this.invariant);
  }
}
