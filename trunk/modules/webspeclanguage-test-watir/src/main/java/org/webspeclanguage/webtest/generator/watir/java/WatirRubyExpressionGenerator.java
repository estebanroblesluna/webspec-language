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
package org.webspeclanguage.webtest.generator.watir.java;

import java.nio.channels.UnresolvedAddressException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.webspeclanguage.impl.exception.WebspecException;
import org.webspeclanguage.impl.expression.core.AddExpression;
import org.webspeclanguage.impl.expression.core.AndExpression;
import org.webspeclanguage.impl.expression.core.ArrayAccessExpression;
import org.webspeclanguage.impl.expression.core.ArrayExpression;
import org.webspeclanguage.impl.expression.core.BinaryExpression;
import org.webspeclanguage.impl.expression.core.BooleanConstant;
import org.webspeclanguage.impl.expression.core.ConcatExpression;
import org.webspeclanguage.impl.expression.core.DivExpression;
import org.webspeclanguage.impl.expression.core.EqualsExpression;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.core.ExpressionVisitor;
import org.webspeclanguage.impl.expression.core.FunctionCallExpression;
import org.webspeclanguage.impl.expression.core.GeneratorExpression;
import org.webspeclanguage.impl.expression.core.GreaterEqualExpression;
import org.webspeclanguage.impl.expression.core.GreaterExpression;
import org.webspeclanguage.impl.expression.core.ImpliesExpression;
import org.webspeclanguage.impl.expression.core.InteractionPropertyExpression;
import org.webspeclanguage.impl.expression.core.LowerEqualExpression;
import org.webspeclanguage.impl.expression.core.LowerExpression;
import org.webspeclanguage.impl.expression.core.MulExpression;
import org.webspeclanguage.impl.expression.core.NativeFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.NotEqualsExpression;
import org.webspeclanguage.impl.expression.core.NotExpression;
import org.webspeclanguage.impl.expression.core.NumberConstant;
import org.webspeclanguage.impl.expression.core.OrExpression;
import org.webspeclanguage.impl.expression.core.StringConstant;
import org.webspeclanguage.impl.expression.core.SubExpression;
import org.webspeclanguage.impl.expression.core.ToBooleanFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.ToNumberFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.ToStringFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.VariableValue;
import org.webspeclanguage.impl.expression.core.WidgetPropertyReference;
import org.webspeclanguage.impl.expression.core.WidgetReference;
import org.webspeclanguage.webtest.generator.watir.java.ExpressionComparator;

/**
 * Watir Expression Generator for Ruby language 
 * 
 * @author Gonzalo G. Testa
 */
public class WatirRubyExpressionGenerator {

  private Set<String> specialFunctionCalls;

  private static final String BROWSER_OBJ = "$browser.";

  public WatirRubyExpressionGenerator() {
    this.setSpecialFunctionCalls(new HashSet<String>());
    this.configureSpecialFunctionCalls();
  }

  private void configureSpecialFunctionCalls() {
    this.getSpecialFunctionCalls().add("isTextPresent");
    this.getSpecialFunctionCalls().add("click");
    this.getSpecialFunctionCalls().add("typeKeys");
    this.getSpecialFunctionCalls().add("type");
    this.getSpecialFunctionCalls().add("select");
    this.getSpecialFunctionCalls().add("open");
  }

  public String generate(Expression expression) {
    ExpressionVisitor elem = new ExpressionGenerator();
    return (String) expression.accept(elem);
  }

  private void setSpecialFunctionCalls(Set<String> specialFunctionCalls) {
    this.specialFunctionCalls = specialFunctionCalls;
  }
  private Set<String> getSpecialFunctionCalls() {
    return specialFunctionCalls;
  }

  private final class ExpressionGenerator implements ExpressionVisitor {

    private String visitBinaryExpression(BinaryExpression binaryExpression, String op) {
      String op1 = generate(binaryExpression.getOp1());
      String op2 = generate(binaryExpression.getOp2());

      op1 = binaryExpression.getOp1().isConstant() ? op1 : "(" + op1 + ")";
      op2 = binaryExpression.getOp2().isConstant() ? op2 : "(" + op2 + ")";

      return op1 + " " + op + " " + op2;
    }

    private String visitMessageSend(BinaryExpression binaryExpression, String op) {
      String op1 = generate(binaryExpression.getOp1());
      String op2 = generate(binaryExpression.getOp2());

      return op1 + "." + op + "(" + op2 + ")";
    }

    private String visitNumberMessageSend(BinaryExpression binaryExpression, String op) {
      String op1 = generate(binaryExpression.getOp1());
      String op2 = generate(binaryExpression.getOp2());

      return op1 + op + op2;
    }

    public Object visitAddExpression(AddExpression expression) {
      return this.visitNumberMessageSend(expression, " + ");
    }

    public Object visitAndExpression(AndExpression and) {
      return this.visitBinaryExpression(and, "and");
    }

    public Object visitConcatExpression(ConcatExpression concat) {
      return this.visitBinaryExpression(concat, "+");
    }

    public Object visitBooleanConstant(BooleanConstant booleanConstant) {
      return booleanConstant.getConstant().toString();
    }

    public Object visitDivExpression(DivExpression expression) {
      return this.visitNumberMessageSend(expression, " / ");
    }

    public Object visitEqualsExpression(EqualsExpression equalsExpression) {
      return this.visitMessageSend(equalsExpression, "eql?");
    }

    public Object visitGreaterEqualExpression(GreaterEqualExpression expression) {
      return this.visitMessageSend(expression," >= ");
    }

    public Object visitGreaterExpression(GreaterExpression expression) {
      return this.visitMessageSend(expression, " > ");
    }

    public Object visitLowerEqualExpression(LowerEqualExpression expression) {
      return this.visitMessageSend(expression, " <= ");
    }

    public Object visitLowerExpression(LowerExpression expression) {
      return this.visitMessageSend(expression, " < ");
    }

    public Object visitMulExpression(MulExpression expression) {
      return this.visitNumberMessageSend(expression, " * ");
    }

    public Object visitNotEqualsExpression(NotEqualsExpression expression) {
      return "" + this.visitMessageSend(expression, " != ") + "";
    }

    public Object visitNotExpression(NotExpression notExpression) {
      String op = generate(notExpression.getExpression());

      op = notExpression.getExpression().isConstant() ? op : "(" + op + ")";

      return "!" + op;
    }

    public Object visitNumberConstant(NumberConstant integerConstant) {
      return 
      "" 
      + integerConstant.getConstant().toString();
    }

    public Object visitOrExpression(OrExpression expression) {
      return this.visitBinaryExpression(expression, "or");
    }

    public Object visitStringConstant(StringConstant stringConstant) {
      return "\"" + stringConstant.getConstant() + "\"";
    }

    public Object visitSubExpression(SubExpression expression) {
      return this.visitNumberMessageSend(expression, " - ");
    }

    public Object visitVariableValue(VariableValue variableValue) {
      return variableValue.getVariableName();
    }

    public Object visitWidgetPropertyReference(WidgetPropertyReference widgetPropertyReference) {
      return BROWSER_OBJ + this.convertArguments(widgetPropertyReference.getPreferedLocation()) + ".text";
    }

    public Object visitWidgetReference(WidgetReference widgetReference) {
      return BROWSER_OBJ + this.convertArguments(widgetReference.getPreferedLocation()) + ".text";
    }

    public Object visitGeneratorExpression(GeneratorExpression generatorExpression) {
      throw new WebspecException(generatorExpression.getGeneratorName() + " should be concrete");
    }

    private String convertArguments(String expr){
      return (new ExpressionComparator(expr)).locators();
    }

    private String getArguments(List<Expression> expressions) {
      String arguments = "";
      for (Expression exp : expressions) {
        String expS = null;
        if (exp instanceof WidgetReference) {

          String expr = ((WidgetReference) exp).getPreferedLocation();
          expS =  this.convertArguments(expr);

        } else if (exp instanceof WidgetPropertyReference) {
          //          String expr = this.convertArguments(expr);

          String expr  = "\"" + ((WidgetPropertyReference) exp).getPreferedLocation()
          + "@" + ((WidgetPropertyReference) exp).getPropertyName()
          + "\"";
          expS = expr;

        }
        else {
          expS = generate(exp);
        }

        arguments = arguments + expS;
      }

      return arguments;
    }

    public Object visitFunctionCallExpression(FunctionCallExpression functionCallExpression) {

      String str = "";

      String arguments = getArguments(functionCallExpression.getArguments());
      if (!arguments.equals("null")){
        // get the function to generate the Watir statements
        String function = functionCallExpression.getFunctionName().toString();

        // generate starting string for statement
        str = BROWSER_OBJ + "";

        if (function.equals("type") || function.equals("select")){
          
          if (function.equals("type"))
            function = "set";
          
          // generates the Watir statement for multiple parameters functions
          str = str + arguments.substring(0,arguments.indexOf(")")+1) + "." + function + " " + arguments.substring(arguments.indexOf(")")+1);
        }
        else{
          if (function.equals("isTextPresent")){
            str = str + "contains_text " + arguments;
          }
          else{
              // generates the Watir statement for simple functions
              str = str + arguments + ".click";
          }
        } 
      }

      return str;
    }

    public Object visitNativeFunctionCallExpression(
            NativeFunctionCallExpression functionCallExpression) {

      String arguments = getArguments(functionCallExpression.getArguments());

      return "self." + functionCallExpression.getFunctionName() + "("
      + arguments + ")";
    }

    public Object visitToBooleanFunctionCallExpression(
            ToBooleanFunctionCallExpression functionCallExpression) {
      return ""
      + "" 
      + getArguments(functionCallExpression.getArguments()) 
      + ".to_s";
    }

    public Object visitToNumberFunctionCallExpression(ToNumberFunctionCallExpression functionCallExpression) {
      return "" + getArguments(functionCallExpression.getArguments()) + ".to_i";
    }

    public Object visitToStringFunctionCallExpression(ToStringFunctionCallExpression functionCallExpression) {
      return "" + getArguments(functionCallExpression.getArguments()) + ".to_s";
    }

    public Object visitImpliesExpression(ImpliesExpression impliesExpression) {
      return this.visitOrExpression(impliesExpression.getEquivalentOrExpression());
    }

    public Object visitArrayExpression(ArrayExpression arrayExpression) {
      throw new UnresolvedAddressException();
    }

    public Object visitArrayAccessExpression(ArrayAccessExpression arrayAccessExpression) {
      throw new UnresolvedAddressException();
    }

    public Object visitInteractionPropertyExpression(InteractionPropertyExpression interactionPropertyExpression) {
      String element = interactionPropertyExpression.getInteraction().getRoot().getPreferedLocation();      
      return "element_by_xpath(\"//*[@" + element.substring(0, element.indexOf("=")) +   "='" + interactionPropertyExpression.getProperty() +"']\")";
    }
  }
}
