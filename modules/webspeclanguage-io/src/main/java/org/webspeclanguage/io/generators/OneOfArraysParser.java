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

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.impl.expression.core.ArrayExpression;
import org.webspeclanguage.impl.generator.OneOfArrays;
import org.webspeclanguage.io.AbstractElementParser;
import org.webspeclanguage.io.ParseContext;
import org.xml.sax.Attributes;

/**
 * A {@link OneOfArrays} parser
 * 
 * @author Esteban Robles Luna
 */
public class OneOfArraysParser extends AbstractElementParser {

  private List<ArrayExpression> arrays;
  
  public OneOfArraysParser() {
    this.arrays = new ArrayList<ArrayExpression>();
  }
  
  /**
   * {@inheritDoc}
   */
  public void parse(Attributes attributes, ParseContext context) {
    OneOfArrays generator = new OneOfArrays(attributes.getValue("name"), new ArrayExpression[] {});
    this.setResult(generator);
  }
  
  public void handleChild(Object result) {
    this.arrays.add((ArrayExpression) result);
  }

  @Override
  public Object getResult() {
    if (!this.arrays.isEmpty()) {
      OneOfArrays result = (OneOfArrays) super.getResult();
      result.setValues(this.arrays.toArray(new ArrayExpression[] {}));
    }
    return super.getResult();
  }
}
