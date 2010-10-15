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

import org.webspeclanguage.impl.expression.core.ArrayExpression;
import org.webspeclanguage.impl.expression.core.StringConstant;
import org.webspeclanguage.impl.generator.OneOfArrays;
import org.webspeclanguage.impl.generator.OneOfNumbers;
import org.webspeclanguage.io.AbstractElementParser;
import org.webspeclanguage.io.ParseContext;
import org.xml.sax.Attributes;

/**
 * A {@link ArrayExpression} parser
 * 
 * @author Esteban Robles Luna
 */
public class ArrayParser extends AbstractElementParser {

  /**
   * {@inheritDoc}
   */
  public void parse(Attributes attributes, ParseContext context) {
    String[] values = attributes.getValue("values").split(",");
    StringConstant[] constants = new StringConstant[values.length];
    for (int i = 0; i < values.length; i++) {
      String value = values[i];
      constants[i] = new StringConstant(value);
    }
    ArrayExpression expression = new ArrayExpression(constants);
    this.setResult(expression);
  }
}
