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
package org.webspeclanguage.impl.generator;

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.api.Generator;
import org.webspeclanguage.impl.expression.core.ConstantExpression;
import org.webspeclanguage.impl.expression.core.ExpressionType;
import org.webspeclanguage.impl.expression.core.StringConstant;

/**
 * A {@link Generator} for {@link StringConstant} 
 * for random strings of a maximum length
 * 
 * @author Esteban Robles Luna
 */
public class RandomStringGenerator extends AbstractGenerator {

  private int maxLength;
  private List<Character> characters;

  public RandomStringGenerator(String name, int maxLength) {
    this(name, 0, maxLength);
  }

  public RandomStringGenerator(String name, int minLength, int maxLength) {
    super(name);
    
    this.maxLength = maxLength;
    this.characters = new ArrayList<Character>();
    
    this.initializeCharacterSet();
  }

  private void initializeCharacterSet() {
    this.addFromTo('\u0041', '\u005a');
    this.addFromTo('\u0061', '\u007a');
    this.addFromTo('\u0030', '\u0039');
  }

  private void addFromTo(char begin, char end) {
    for (char init = begin; init <= end; init++) {
      this.getCharacters().add(new Character(init));
    }
  }

  @SuppressWarnings("unchecked")
  public ConstantExpression generate() {
    int length = (int) Math.round(Math.random() * this.getMaxLength());
    if (length == 0) {
      length = 1;
    }
    StringBuffer s = new StringBuffer();
    for (int i = 0; i < length; i++) {
      int index = (int) Math.round(Math.random()
          * (this.getCharacters().size() - 1));
      Character c = this.getCharacters().get(index);
      s.append(c);
    }
    return new StringConstant(s.toString());
  }

  public ExpressionType getGenerationType() {
    return ExpressionType.STRING;
  }

  private int getMaxLength() {
    return maxLength;
  }

  private List<Character> getCharacters() {
    return characters;
  }
}
