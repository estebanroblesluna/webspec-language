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
package org.webspeclanguage.io.generators;

import java.math.BigDecimal;

import org.webspeclanguage.impl.generator.UniformNumberGenerator;
import org.webspeclanguage.io.AbstractElementParser;
import org.webspeclanguage.io.ParseContext;
import org.xml.sax.Attributes;

/**
 * A {@link UniformNumberGenerator} parser
 * 
 * @author Esteban Robles Luna
 */
public class UniformNumberParser extends AbstractElementParser {

  /**
   * {@inheritDoc}
   */
  public void parse(Attributes attributes, ParseContext context) {
    String name = attributes.getValue("name");
    BigDecimal minValue = new BigDecimal(attributes.getValue("minValue"));
    BigDecimal maxValue = new BigDecimal(attributes.getValue("maxValue"));
    this.setResult(new UniformNumberGenerator(name, minValue, maxValue));
  }
}
