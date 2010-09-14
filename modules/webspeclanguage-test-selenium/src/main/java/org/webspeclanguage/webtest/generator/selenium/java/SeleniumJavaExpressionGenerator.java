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
package org.webspeclanguage.webtest.generator.selenium.java;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.webspeclanguage.expression.base.AddExpression;
import org.webspeclanguage.expression.base.AndExpression;
import org.webspeclanguage.expression.base.ArrayAccessExpression;
import org.webspeclanguage.expression.base.ArrayExpression;
import org.webspeclanguage.expression.base.BinaryExpression;
import org.webspeclanguage.expression.base.BooleanConstant;
import org.webspeclanguage.expression.base.ConcatExpression;
import org.webspeclanguage.expression.base.DivExpression;
import org.webspeclanguage.expression.base.EqualsExpression;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.ExpressionType;
import org.webspeclanguage.expression.base.ExpressionVisitor;
import org.webspeclanguage.expression.base.FunctionCallExpression;
import org.webspeclanguage.expression.base.GeneratorExpression;
import org.webspeclanguage.expression.base.GreaterEqualExpression;
import org.webspeclanguage.expression.base.GreaterExpression;
import org.webspeclanguage.expression.base.ImpliesExpression;
import org.webspeclanguage.expression.base.LowerEqualExpression;
import org.webspeclanguage.expression.base.LowerExpression;
import org.webspeclanguage.expression.base.MulExpression;
import org.webspeclanguage.expression.base.NativeFunctionCallExpression;
import org.webspeclanguage.expression.base.NotEqualsExpression;
import org.webspeclanguage.expression.base.NotExpression;
import org.webspeclanguage.expression.base.NumberConstant;
import org.webspeclanguage.expression.base.OrExpression;
import org.webspeclanguage.expression.base.StringConstant;
import org.webspeclanguage.expression.base.SubExpression;
import org.webspeclanguage.expression.base.ToBooleanFunctionCallExpression;
import org.webspeclanguage.expression.base.ToNumberFunctionCallExpression;
import org.webspeclanguage.expression.base.ToStringFunctionCallExpression;
import org.webspeclanguage.expression.base.VariableValue;
import org.webspeclanguage.expression.base.WidgetPropertyReference;
import org.webspeclanguage.expression.base.WidgetReference;

/**
 * An {@link Expression} writer
 * 
 * @author Esteban Robles Luna
 */
public class SeleniumJavaExpressionGenerator {

  private SeleniumJavaWebTestGenerator generator;
  private Set<String> specialFunctionCalls;

  public SeleniumJavaExpressionGenerator(SeleniumJavaWebTestGenerator generator) {
    this.setGenerator(generator);
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
    return (String) expression.accept(new ExpressionVisitor() {
      private String visitBinaryExpression(BinaryExpression binaryExpression, String op) {
        String op1 = generate(binaryExpression.getOp1());
        String op2 = generate(binaryExpression.getOp2());

        op1 = binaryExpression.getOp1().isConstant() ? op1 : "(" + op1 + ")";
        op2 = binaryExpression.getOp2().isConstant() ? op2 : "(" + op2 + ")";

        return op1 + " " + op + " " + op2;
      }

      private String visitMessageSend(BinaryExpression binaryExpression,
          String op) {
        String op1 = generate(binaryExpression.getOp1());
        String op2 = generate(binaryExpression.getOp2());

        return op1 + "." + op + "(" + op2 + ")";
      }

      private String visitNumberMessageSend(BinaryExpression binaryExpression,
          String op) {
        String genOp1 = generate(binaryExpression.getOp1());
        String genOp2 = generate(binaryExpression.getOp2());

        String op1 = getGenerator().getTypechecker().typecheck(
            binaryExpression.getOp1()).equals(ExpressionType.NUMBER) ? genOp1
            : "new BigDecimal(" + genOp1 + ")";
        String op2 = getGenerator().getTypechecker().typecheck(
            binaryExpression.getOp2()).equals(ExpressionType.NUMBER) ? genOp2
            : "new BigDecimal(" + genOp2 + ")";

        return op1 + "." + op + "(" + op2 + ")";
      }

      public Object visitAddExpression(AddExpression expression) {
        return this.visitNumberMessageSend(expression, "add");
      }

      public Object visitAndExpression(AndExpression and) {
        return this.visitBinaryExpression(and, "&&");
      }

      public Object visitConcatExpression(ConcatExpression concat) {
        return this.visitBinaryExpression(concat, "+");
      }

      public Object visitBooleanConstant(BooleanConstant booleanConstant) {
        return new Boolean(booleanConstant.getConstant()).toString();
      }

      public Object visitDivExpression(DivExpression expression) {
        return this.visitNumberMessageSend(expression, "divide");
      }

      public Object visitEqualsExpression(EqualsExpression equalsExpression) {
        return this.visitMessageSend(equalsExpression, "equals");
      }

      public Object visitGreaterEqualExpression(
          GreaterEqualExpression expression) {
        return this.visitMessageSend(expression, "compareTo") + " >= 0";
      }

      public Object visitGreaterExpression(GreaterExpression expression) {
        return this.visitMessageSend(expression, "compareTo") + " > 0";
      }

      public Object visitLowerEqualExpression(LowerEqualExpression expression) {
        return this.visitMessageSend(expression, "compareTo") + " <= 0";
      }

      public Object visitLowerExpression(LowerExpression expression) {
        return this.visitMessageSend(expression, "compareTo") + " < 0";
      }

      public Object visitMulExpression(MulExpression expression) {
        return this.visitNumberMessageSend(expression, "multiply");
      }

      public Object visitNotEqualsExpression(NotEqualsExpression expression) {
        return "!(" + this.visitMessageSend(expression, "equals") + ")";
      }

      public Object visitNotExpression(NotExpression notExpression) {
        String op = generate(notExpression.getExpression());
        
        op = notExpression.getExpression().isConstant() ? op : "(" + op + ")";
        
        return "!" + op;
      }

      public Object visitNumberConstant(NumberConstant integerConstant) {
        return 
          "new BigDecimal(\"" 
            + integerConstant.getConstant().toString()
            + "\")";
      }

      public Object visitOrExpression(OrExpression expression) {
        return this.visitBinaryExpression(expression, "||");
      }

      public Object visitStringConstant(StringConstant stringConstant) {
        return "\"" + stringConstant.getConstant() + "\"";
      }

      public Object visitSubExpression(SubExpression expression) {
        return this.visitNumberMessageSend(expression, "subtract");
      }

      public Object visitVariableValue(VariableValue variableValue) {
        return variableValue.getVariableName();
      }

      public Object visitWidgetPropertyReference(
          WidgetPropertyReference widgetPropertyReference) {
        return "selenium.getAttribute(" + "\""
            + widgetPropertyReference.getPreferedLocation() + "@"
            + widgetPropertyReference.getPropertyName() + "\"" + ")";
      }

      public Object visitWidgetReference(WidgetReference widgetReference) {
        return "selenium.getText(" + "\""
            + widgetReference.getPreferedLocation() + "\"" + ")";
      }

      public Object visitGeneratorExpression(
          GeneratorExpression generatorExpression) {
        throw new RuntimeException("Should be concrete");
      }

      private String getArguments(List<Expression> expressions) {
        String arguments = "";
        for (Expression exp : expressions) {
          String expS = null;
          if (exp instanceof WidgetReference) {
            expS = "\"" + ((WidgetReference) exp).getPreferedLocation() + "\"";
          } else if (exp instanceof WidgetPropertyReference) {
            expS = "\"" + ((WidgetPropertyReference) exp).getPreferedLocation()
                + "@" + ((WidgetPropertyReference) exp).getPropertyName()
                + "\"";
          } else {
            expS = generate(exp);
          }
          arguments = arguments + expS + ", ";
        }
        arguments = arguments.substring(0, arguments.length() - 2);
        return arguments;
      }
      
      public Object visitFunctionCallExpression(
          FunctionCallExpression functionCallExpression) {
        
        String arguments = getArguments(functionCallExpression.getArguments());

        if (getSpecialFunctionCalls().contains(
            functionCallExpression.getFunctionName())) {
          return "selenium." + functionCallExpression.getFunctionName() + "("
              + arguments + ")";
        } else {
          return "selenium.execute(\""
              + functionCallExpression.getFunctionName() + "\", " + arguments
              + ")";
        }
      }

      public Object visitNativeFunctionCallExpression(
          NativeFunctionCallExpression functionCallExpression) {
        
        String arguments = getArguments(functionCallExpression.getArguments());

        return "this." + functionCallExpression.getFunctionName() + "("
            + arguments + ")";
      }
      
      public Object visitToBooleanFunctionCallExpression(
          ToBooleanFunctionCallExpression functionCallExpression) {
        return "Boolean.valueOf("
          + "(" 
          + getArguments(functionCallExpression.getArguments()) 
          + ").toString())";
      }

      public Object visitToNumberFunctionCallExpression(
          ToNumberFunctionCallExpression functionCallExpression) {
        return "new BigDecimal(" + getArguments(functionCallExpression.getArguments()) + ")";
      }

      public Object visitToStringFunctionCallExpression(
          ToStringFunctionCallExpression functionCallExpression) {
        return "(" + getArguments(functionCallExpression.getArguments()) + ").toString()";
      }

      public Object visitImpliesExpression(ImpliesExpression impliesExpression) {
        return this.visitOrExpression(impliesExpression.getEquivalentOrExpression());
      }

      public Object visitArrayExpression(ArrayExpression arrayExpression) {
        // TODO Auto-generated method stub
        Object o = new Object[] { "aaa", new BigDecimal("aaa"), true }[1];
        return "arrayExp";
      }

      public Object visitArrayAccessExpression(
          ArrayAccessExpression arrayAccessExpression) {
        return generate(arrayAccessExpression.getExpression());
      }
    });
  }

  private SeleniumJavaWebTestGenerator getGenerator() {
    return generator;
  }

  private void setGenerator(SeleniumJavaWebTestGenerator generator) {
    this.generator = generator;
  }

  private Set<String> getSpecialFunctionCalls() {
    return specialFunctionCalls;
  }

  private void setSpecialFunctionCalls(Set<String> specialFunctionCalls) {
    this.specialFunctionCalls = specialFunctionCalls;
  }
}
