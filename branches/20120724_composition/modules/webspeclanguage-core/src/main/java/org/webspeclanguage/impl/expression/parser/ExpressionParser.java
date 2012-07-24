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
package org.webspeclanguage.impl.expression.parser;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.webspeclanguage.api.Action;
import org.webspeclanguage.api.WidgetProvider;
import org.webspeclanguage.impl.action.ExpressionAction;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.parser.lexer.Lexer;
import org.webspeclanguage.impl.expression.parser.lexer.LexerException;
import org.webspeclanguage.impl.expression.parser.node.Start;
import org.webspeclanguage.impl.expression.parser.parser.Parser;
import org.webspeclanguage.impl.expression.parser.parser.ParserException;

/**
 * A parser for {@link Expression}
 * 
 * @author Esteban Robles Luna
 */
public class ExpressionParser {

  public Expression parseFor(String input, WidgetProvider provider)
      throws ParsingException {
    String modifiedInput = input.replace(Character.valueOf((char) 8232).charValue(), '\n');
    modifiedInput = modifiedInput.replaceAll("\n", "");

    Lexer lexer = new Lexer(new PushbackReader(new StringReader(modifiedInput),
        1024));

    Parser parser = new Parser(lexer);

    try {
      Start ast = parser.parse();
      ExpressionTransformer transformer = new ExpressionTransformer(provider);
      List<Action> actions = transformer.transform(ast);
      if (actions.size() == 1) {
        if (actions.get(0) instanceof ExpressionAction) {
          return ((ExpressionAction) actions.get(0)).getExpression();
        } else {
          throw new ParsingException("Invalid expression");
        }
      } else {
        throw new ParsingException("Invalid expression");
      }
    } catch (ParserException e) {
      throw new ParsingException(e);
    } catch (LexerException e) {
      throw new ParsingException(e);
    } catch (IOException e) {
      throw new ParsingException(e);
    }
  }
}
