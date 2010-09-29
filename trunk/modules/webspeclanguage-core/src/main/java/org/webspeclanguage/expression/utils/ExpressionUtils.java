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
package org.webspeclanguage.expression.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.webspeclanguage.base.Diagram;
import org.webspeclanguage.expression.base.ConstantExpression;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.parser.ExpressionParser;

/**
 * Utility functions for {@link Expression}
 * 
 * @author Esteban Robles Luna
 */
public final class ExpressionUtils {

  private static ExpressionParser parser = new ExpressionParser();
  private static Pattern pattern = Pattern.compile(".*\\$\\{[A-z]+\\}.*");

  private ExpressionUtils() { }
  
  public static Expression getExpression(String input, Diagram diagram) {
    if (input == null || input.trim().equals("")) {
      return null;
    } else {
      return parser.parseFor(input, diagram);
    }
  }
  
  public static Expression getExpression(String input) {
    return getExpression(input, null);
  }

  public static boolean hasVariables(String string) {
    // find all occurences of ${ ... }
    return pattern.matcher(string).matches();
  }

  public static String firstVariable(String string) {
    return variables(string).get(0);
  }

  public static List<String> variables(String string) {
    List<String> variables = new ArrayList<String>();
    if (string != null) {
      int index = string.indexOf("${", 0);
      while (index != -1) {
        int endIndex = string.indexOf('}', index + 1);
        String var = string.substring(index, endIndex + 1);
        index = string.indexOf("${", index + 1);
        variables.add(var);
      }
    }
    return variables;
  }

  public static List<String> strings(String string) {
    List<String> strings = new ArrayList<String>();
    if (string != null) {
      int index = string.indexOf('\"', 0);
      while (index != -1) {
        int endIndex = string.indexOf('\"', index + 1);
        String s = string.substring(index + 1, endIndex);
        index = string.indexOf('\"', endIndex + 1);
        strings.add(s);
      }
    }
    return strings;
  }

  @SuppressWarnings("unchecked")
  public static boolean constantVariables(String string,
      Map<String, ConstantExpression> constants) {
    List<String> variables = variables(string);
    for (String variableName : variables) {
      if (!constants.containsKey(variableName)) {
        return false;
      }
    }
    return true;
  }

  public static List<String> arrayStrings(String string) {
    List<String> arrays = new ArrayList<String>();
    if (string != null) {
      int index = string.indexOf('[', 0);
      while (index != -1) {
        int endIndex = string.indexOf(']', index);
        String s = string.substring(index, endIndex + 1);
        index = string.indexOf('[', endIndex + 1);
        arrays.add(s);
      }
    }
    return arrays;
  }
}
