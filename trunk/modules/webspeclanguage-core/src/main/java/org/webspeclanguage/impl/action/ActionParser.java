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
package org.webspeclanguage.impl.action;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.webspeclanguage.api.Action;
import org.webspeclanguage.api.WidgetProvider;
import org.webspeclanguage.impl.expression.parser.ExpressionTransformer;
import org.webspeclanguage.impl.expression.parser.ParsingException;
import org.webspeclanguage.impl.expression.parser.lexer.Lexer;
import org.webspeclanguage.impl.expression.parser.lexer.LexerException;
import org.webspeclanguage.impl.expression.parser.node.Start;
import org.webspeclanguage.impl.expression.parser.parser.Parser;
import org.webspeclanguage.impl.expression.parser.parser.ParserException;

/**
 * A parser for {@link Action}
 * 
 * @author Esteban Robles Luna
 */
public class ActionParser {

  private static ActionParser parser = new ActionParser();

  /**
   * Parses input in the context of {@link WidgetProvider}
   * 
   * @param input the input
   * @param provider the provider
   * @return the list of actions as a result of the parsing process
   */
  public static List<Action> getActions(String input, WidgetProvider provider) {
    if (input == null) {
      return null;
    } else {
      return parser.parseFor(input, provider);
    }
  }
  
  /**
   * Parses input in the context of the {@link WidgetProvider}
   * 
   * @param input the input
   * @param provider the provider
   * @return the list of actions as a result of the parsing process
   * @throws ParsingException if an exception during parsing is thrown
   */
  public List<Action> parseFor(String input, WidgetProvider provider) throws ParsingException {
    String modifiedInput = input.replace(Character.valueOf((char) 8232).charValue(), '\n');
    modifiedInput = modifiedInput.replaceAll("\n", "");

    Lexer lexer = new Lexer(new PushbackReader(new StringReader(modifiedInput),
        1024));

    Parser sableParser = new Parser(lexer);

    try {
      Start ast = sableParser.parse();
      ExpressionTransformer transformer = new ExpressionTransformer(provider);
      return transformer.transform(ast);
    } catch (ParserException e) {
      throw new ParsingException(e);
    } catch (LexerException e) {
      throw new ParsingException(e);
    } catch (IOException e) {
      throw new ParsingException(e);
    }
  }
}