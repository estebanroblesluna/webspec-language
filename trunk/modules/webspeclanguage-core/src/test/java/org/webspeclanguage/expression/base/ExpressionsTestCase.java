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
package org.webspeclanguage.expression.base;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.base.WebSpecInteraction;
import org.webspeclanguage.expression.base.AddExpression;
import org.webspeclanguage.expression.base.BooleanConstant;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.ExpressionType;
import org.webspeclanguage.expression.base.NotExpression;
import org.webspeclanguage.expression.base.NumberConstant;
import org.webspeclanguage.expression.concretizer.ExpressionConcretizer;
import org.webspeclanguage.expression.conjunctivenormalform.ExpressionConvertorToConjunctiveNormalForm;
import org.webspeclanguage.expression.optimizer.ExpressionOptimizer;
import org.webspeclanguage.expression.parser.ExpressionParser;
import org.webspeclanguage.expression.typechecker.ExpressionTypechecker;
import org.webspeclanguage.expression.typechecker.TypecheckException;
import org.webspeclanguage.expression.typechecker.InexistentGeneratorException;
import org.webspeclanguage.expression.typechecker.UnmatchedFunctionException;
import org.webspeclanguage.generator.BooleanGenerator;
import org.webspeclanguage.generator.OneOfNumbers;
import org.webspeclanguage.generator.OneOfStrings;
import org.webspeclanguage.generator.StringGenerator;
import org.webspeclanguage.generator.UniformNumberGenerator;
import org.webspeclanguage.widget.Button;
import org.webspeclanguage.widget.ListOfContainer;
import org.webspeclanguage.widget.TextField;

/**
 * @author Esteban Robles Luna
 */
public class ExpressionsTestCase extends TestCase {

  private ExpressionParser parser;
  private WebSpecDiagram diagram;

  private ExpressionOptimizer optimizer;
  private ExpressionConcretizer concretizer;
  private ExpressionTypechecker typechecker;
  private ExpressionConvertorToConjunctiveNormalForm convertorCNF;

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    this.parser = new ExpressionParser();
    this.diagram = new WebSpecDiagram("a");

    WebSpecInteraction interaction = new WebSpecInteraction("Home");
    this.diagram.addInteraction(interaction);
    Button button = interaction.createButtonWithLocation("loc");
    button.setName("register");
    interaction.addWidget(button);
    
    ListOfContainer list = new ListOfContainer();
    list.setName("list");
    interaction.addWidget(list);
    
    TextField textField = new TextField();
    textField.setName("textField");
    list.addWidget(textField);

    this.convertorCNF = new ExpressionConvertorToConjunctiveNormalForm();
    this.optimizer = new ExpressionOptimizer();
    this.concretizer = new ExpressionConcretizer();
    this.typechecker = new ExpressionTypechecker(this.diagram);

    this.concretizer.set("b", BooleanConstant.TRUE);
    this.concretizer.set("n", new NumberConstant(new BigDecimal(2)));
    this.concretizer.set("array", new ArrayExpression(new NumberConstant("1"), 
            new NumberConstant("2"), new NumberConstant("3"), new NumberConstant("4")));
    
    this.concretizer.set(new OneOfNumbers("genI", 1));
    this.concretizer.set(new UniformNumberGenerator("uni", 0, 1));

    this.typechecker.set(new OneOfNumbers("genI", 1));
    this.typechecker.set(new BooleanGenerator("bools"));
    this.typechecker.set(new OneOfStrings("ss", "a", "b"));
    this.typechecker.set(new UniformNumberGenerator("uni", 0, 1));
    this.typechecker.set(new StringGenerator("sss", 25));
  }

  public void testOptimize() {
    this.basicOptimize("true", "true");
    this.basicOptimize("false", "false");

    this.basicOptimize("true && ${a}", "${a}");
    this.basicOptimize("${a} && true", "${a}");
    this.basicOptimize("false && ${a}", "false");
    this.basicOptimize("${a} && false", "false");
    this.basicOptimize("true && false", "false");

    this.basicOptimize("true && ${b}", "true");

    this.basicOptimize("true || ${a}", "true");
    this.basicOptimize("${a} || true", "true");
    this.basicOptimize("false || ${a}", "${a}");
    this.basicOptimize("${a} || false", "${a}");

    this.basicOptimize("true -> ${a}", "${a}");
    this.basicOptimize("false -> ${a}", "true");
    this.basicOptimize("true -> false", "false");

    this.basicOptimize("${non}", "${non}");
    this.basicOptimize("\"a\"", "\"a\"");
    this.basicOptimize("click(Home.register)", "click(Home.register)");

    this.basicOptimize("\"a\" & \"b\"", "\"ab\"");
    this.basicOptimize("\"a\" & $ss$", "\"a\" & $ss$");

    this.basicOptimize("!true", "false");
    this.basicOptimize("!false", "true");

    this.basicOptimize("1", "1");
    this.basicOptimize("1 + 1", "2");
    this.basicOptimize("1 + ${a}", "1 + ${a}");
    this.basicOptimize("1 - 1", "0");
    this.basicOptimize("1 - ${a}", "1 - ${a}");
    this.basicOptimize("2 * 4", "8");
    this.basicOptimize("1 * ${a}", "1 * ${a}");
    this.basicOptimize("8 / 4", "2");
    this.basicOptimize("1 / ${a}", "1 / ${a}");
    this.basicOptimize("1 + ${n}", "3");

    this.basicOptimize("1 = 1", "true");
    this.basicOptimize("1 = ${a}", "1 = ${a}");
    this.basicOptimize("1 != 1", "false");
    this.basicOptimize("1 != ${a}", "1 != ${a}");
    this.basicOptimize("1 >= 1", "true");
    this.basicOptimize("1 >= ${a}", "1 >= ${a}");
    this.basicOptimize("1 > 1", "false");
    this.basicOptimize("1 > ${a}", "1 > ${a}");
    this.basicOptimize("1 <= 1", "true");
    this.basicOptimize("1 <= ${a}", "1 <= ${a}");
    this.basicOptimize("1 < 1", "false");
    this.basicOptimize("1 < ${a}", "1 < ${a}");

    this.basicOptimize("1 = $genI$", "true");
    this.basicOptimize("(1 + $uni$) <= 2", "true");

    this.basicOptimize("%nat(1 + 2)", "%nat(3)");

    this.basicOptimize("true && (true -> ${a}) && (1 = $genI$)", "${a}");
    this.basicOptimize(
        "true && (true -> ${a}) && (1 = $genI$) && ${b} && ${c}",
        "${a} && ${c}");
    
    this.basicOptimize("toString(1)", "\"1\"");
    this.basicOptimize("toString(true)", "\"true\"");
    this.basicOptimize("toString(false)", "\"false\"");
    this.basicOptimize("toString(\"a\")", "\"a\"");

    this.basicOptimize("toNumber(1)", "1");
    this.basicOptimize("toNumber(true)", "1");
    this.basicOptimize("toNumber(false)", "0");
    this.basicOptimize("toNumber(\"asd\")", "0");
    this.basicOptimize("toNumber(\"123\")", "123");

    this.basicOptimize("toBoolean(1)", "true");
    this.basicOptimize("toBoolean(0)", "false");
    this.basicOptimize("toBoolean(32525)", "false");
    this.basicOptimize("toBoolean(\"true\")", "true");
    this.basicOptimize("toBoolean(\"false\")", "false");
    this.basicOptimize("toBoolean(true)", "true");
    this.basicOptimize("toBoolean(false)", "false");
    
    this.basicOptimize("!(!($bools$))", "$bools$");
    
    this.basicOptimize("Home.list[1 + 5].textField.text", "Home.list[6].textField.text");
    this.basicOptimize("Home.list[1 + ${n}].textField.text", "Home.list[3].textField.text");
    this.basicOptimize("Home.list[1 + toNumber(\"44\")].textField.text", "Home.list[45].textField.text");

    this.basicOptimize("[1, 2, 3, 4][(2 * 5) - 10]", "1");
    this.basicOptimize("[1, 2, 3, 4][1]", "2");
    this.basicOptimize("${array}[1]", "2");
  }

  public void testTypecheck() {
    this.basicTypecheck("true", ExpressionType.BOOLEAN);
    this.basicTypecheck("false", ExpressionType.BOOLEAN);
    this.basicTypecheck("true -> $bools$", ExpressionType.BOOLEAN);
    this.basicTypecheck("true && $bools$", ExpressionType.BOOLEAN);
    this.basicTypecheck("true || $bools$", ExpressionType.BOOLEAN);
    this.basicTypecheck("!$bools$", ExpressionType.BOOLEAN);

    this.basicTypecheck("$genI$", ExpressionType.NUMBER);
    this.basicTypecheck("1 + $genI$", ExpressionType.NUMBER);
    this.basicTypecheck("1 + 2", ExpressionType.NUMBER);
    this.basicTypecheck("1 - 2", ExpressionType.NUMBER);
    this.basicTypecheck("1 * 2", ExpressionType.NUMBER);
    this.basicTypecheck("1 / 2", ExpressionType.NUMBER);
    
    this.basicTypecheck("toString(1 + 2)", ExpressionType.STRING);

    this.basicTypecheck("1 = 1", ExpressionType.BOOLEAN);
    this.basicTypecheck("1 != 1", ExpressionType.BOOLEAN);
    this.basicTypecheck("1 >= 1", ExpressionType.BOOLEAN);
    this.basicTypecheck("1 > 1", ExpressionType.BOOLEAN);
    this.basicTypecheck("1 <= 1", ExpressionType.BOOLEAN);
    this.basicTypecheck("1 < 1", ExpressionType.BOOLEAN);

    this.basicTypecheck("$ss$", ExpressionType.STRING);
    this.basicTypecheck("$sss$", ExpressionType.STRING);
    this.basicTypecheck("$uni$", ExpressionType.NUMBER);

    this.basicTypecheck("toString(1)", ExpressionType.STRING);
    this.basicTypecheck("toNumber(\"a\")", ExpressionType.NUMBER);
    this.basicTypecheck("toBoolean(\"true\")", ExpressionType.BOOLEAN);

    this.basicTypecheck("\"a\" & \"b\"", ExpressionType.STRING);

    this.basicTypecheck("click(Home.register)", ExpressionType.VOID);
    this.basicTypecheck("%native()", ExpressionType.VOID);

    this.basicTypecheck("Home.title", ExpressionType.STRING);
    this.basicTypecheck("Home.list", ExpressionType.WIDGET);
    this.basicTypecheck("Home.list.visible", ExpressionType.BOOLEAN);
    this.basicTypecheck("Home.list[1 + 5].textField.text", ExpressionType.STRING);
    this.basicTypecheck("Home.list[1 + $genI$].textField.text", ExpressionType.STRING);
    this.basicTypecheck("Home.list[1].textField", ExpressionType.WIDGET);
    this.basicTypecheck("Home.list[1 + $genI$].textField", ExpressionType.WIDGET);
    
    this.basicTypecheckError("1 + true");
    this.basicTypecheckError("true && $genI$");
    this.basicTypecheckError("1 & true");
    this.basicTypecheckError("Home.list[\"a\"].textField");
    this.basicTypecheckError("Home.list[true].textField");

    this.basicUnmatchedError("toString(1, 2)");
    this.basicUnmatchedError("toNumber(1, 2)");
    this.basicUnmatchedError("toBoolean(1, 2)");

    this.basicTypecheckMissingGenerator("$gg$", "gg");
  }
  
  public void testConjunctiveNormalForm() {
    this.basicTestConjunctiveNormalForm("true -> false", "!true || false");
    this.basicTestConjunctiveNormalForm("!(true && false)", "(!true) || (!false)");
    this.basicTestConjunctiveNormalForm("!(true || false)", "(!true) && (!false)");
    this.basicTestConjunctiveNormalForm("${a} && (${b} || ${c})", "${a} && (${b} || ${c})");
    this.basicTestConjunctiveNormalForm("${a} || (${b} && ${c})", "(${a} || ${b}) && (${a} || ${c})");
    this.basicTestConjunctiveNormalForm("(${b} && ${c}) || ${a} ", "(${b} || ${a}) && (${c} || ${a})");
  }

  public void testDisjunctions() {
    this.basicDisjunctionSize("true -> false", 1);
    this.basicDisjunctionAt("true -> false", 0, "!true || false");

    this.basicDisjunctionSize("!(true && false)", 1);
    this.basicDisjunctionAt("!(true && false)", 0, "(!true) || (!false)");

    this.basicDisjunctionSize("!(true || false)", 2);
    this.basicDisjunctionAt("!(true || false)", 0, "(!true)");
    this.basicDisjunctionAt("!(true || false)", 1, "(!false)");

    this.basicDisjunctionSize("${a} && (${b} || ${c})", 2);
    this.basicDisjunctionAt("${a} && (${b} || ${c})", 0, "${a}");
    this.basicDisjunctionAt("${a} && (${b} || ${c})", 1, "(${b} || ${c})");

    this.basicDisjunctionSize("${a} || (${b} && ${c})", 2);
    this.basicDisjunctionAt("${a} || (${b} && ${c})", 0, "(${a} || ${b})");
    this.basicDisjunctionAt("${a} || (${b} && ${c})", 1, "(${a} || ${c})");

    this.basicDisjunctionSize("(${b} && ${c}) || ${a} ", 2);
    this.basicDisjunctionAt("(${b} && ${c}) || ${a} ", 0, "(${b} || ${a})");
    this.basicDisjunctionAt("(${b} && ${c}) || ${a} ", 1, "(${c} || ${a})");

    this.basicDisjunctionSize("${a} && ${b} && ${c} && ${d}", 4);
    this.basicDisjunctionAt("${a} && ${b} && ${c} && ${d}", 0, "${a}");
    this.basicDisjunctionAt("${a} && ${b} && ${c} && ${d}", 1, "${b}");
    this.basicDisjunctionAt("${a} && ${b} && ${c} && ${d}", 2, "${c}");
    this.basicDisjunctionAt("${a} && ${b} && ${c} && ${d}", 3, "${d}");
  }
  
  private void basicDisjunctionAt(String expressionString, int index, String expectedExpressionString) {
    Expression actualE = this.parser.parseFor(expressionString, this.diagram);
    Expression expectedE = this.parser.parseFor(expectedExpressionString, this.diagram);
    
    Expression actualEAtIndex = this.convertorCNF.convertAndObtainDisjunctions(actualE).get(index);
    assertEquals(actualEAtIndex, expectedE);
  }

  private void basicDisjunctionSize(String expressionString, int expectedSize) {
    Expression expression = this.parser.parseFor(expressionString, this.diagram);
    assertEquals(this.convertorCNF.convertAndObtainDisjunctions(expression).size(), expectedSize);
  }

  private void basicTestConjunctiveNormalForm(String actual, String expected) {
    Expression actualE = this.parser.parseFor(actual, this.diagram);
    Expression expectedE = this.parser.parseFor(expected, this.diagram);

    assertEquals(this.convertToConjunctiveNormalForm(actualE), expectedE);
  }

  private Expression convertToConjunctiveNormalForm(Expression expression) {
    return this.convertorCNF.convert(expression);
  }

  public void testGetInstancesOfOn() {
    this.basicGetInstancesOfOn("!${a}", NotExpression.class, 1);
    this.basicGetInstancesOfOn("1 + 2 + 3", AddExpression.class, 2);
  }

  @SuppressWarnings("unchecked")
  private void basicGetInstancesOfOn(String expression, Class<?> aClass,
      int size) {
    Expression actualE = this.parser.parseFor(expression, this.diagram);
    Set instances = new HashSet();
    actualE.getInstancesOfOn(aClass, instances);
    assertEquals(instances.size(), size);
  }

  private void basicOptimize(String actual, String expected) {
    Expression actualE = this.parser.parseFor(actual, this.diagram);
    Expression expectedE = this.parser.parseFor(expected, this.diagram);

    assertEquals(this.optimizeAndEvaluate(actualE), expectedE);
  }

  private Expression optimizeAndEvaluate(Expression expression) {
    Expression concreteExpression = this.concretizer.makeConcrete(expression);
    return this.optimizer.optimize(concreteExpression);
  }

  private void basicTypecheckMissingGenerator(String expression, String generatorName) {
    Expression expressionE = this.parser.parseFor(expression, this.diagram);
    try {
      this.typechecker.typecheck(expressionE);
      fail("An exception should be thrown");
    } catch (InexistentGeneratorException e) {
      assertEquals(e.getGeneratorName(), generatorName);
    }
  }

  private void basicUnmatchedError(String expression) {
    Expression expressionE = this.parser.parseFor(expression, this.diagram);
    try {
      this.typechecker.typecheck(expressionE);
      fail("An exception should be thrown");
    } catch (UnmatchedFunctionException e) {
      // EXCELLENT
    }
  }
  
  private void basicTypecheckError(String expression) {
    Expression expressionE = this.parser.parseFor(expression, this.diagram);
    try {
      this.typechecker.typecheck(expressionE);
      fail("An exception should be thrown");
    } catch (TypecheckException e) {
      // EXCELLENT
    }
  }

  private void basicTypecheck(String expression, ExpressionType type) {
    Expression expressionE = this.parser.parseFor(expression, this.diagram);
    assertEquals(type, this.typechecker.typecheck(expressionE));
  }
}
