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
package org.webspeclanguage.expression.optimizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.expression.base.AddExpression;
import org.webspeclanguage.expression.base.AndExpression;
import org.webspeclanguage.expression.base.ArrayAccessExpression;
import org.webspeclanguage.expression.base.ArrayExpression;
import org.webspeclanguage.expression.base.ArrayHolder;
import org.webspeclanguage.expression.base.BinaryExpression;
import org.webspeclanguage.expression.base.BooleanConstant;
import org.webspeclanguage.expression.base.ConcatExpression;
import org.webspeclanguage.expression.base.ConstantExpression;
import org.webspeclanguage.expression.base.DivExpression;
import org.webspeclanguage.expression.base.EqualsExpression;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.ExpressionVisitor;
import org.webspeclanguage.expression.base.FunctionCallExpression;
import org.webspeclanguage.expression.base.GeneratorExpression;
import org.webspeclanguage.expression.base.GreaterEqualExpression;
import org.webspeclanguage.expression.base.GreaterExpression;
import org.webspeclanguage.expression.base.ImpliesExpression;
import org.webspeclanguage.expression.base.InteractionPropertyExpression;
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
 * An optimizer that optimizes an {@link Expression}.
 * For example the optimizer will optimize the following expressions:
 * "1 + 2" is optimized to "3"
 * "false -> (Home.message = 'new')" is optimized to "true"
 * 
 * @author Esteban Robles Luna
 */
@SuppressWarnings("unchecked")
public class ExpressionOptimizer implements ExpressionVisitor {

  public Expression optimize(Expression expression) {
    return (Expression) expression.accept(this);
  }

  private void optimizeBinary(BinaryExpression expression) {
    Expression e1 = this.optimize(expression.getOp1());
    Expression e2 = this.optimize(expression.getOp2());

    expression.setOp1(e1);
    expression.setOp2(e2);
  }

  public Object visitAddExpression(AddExpression expression) {
    this.optimizeBinary(expression);

    if (expression.getOp1().isConstant() && expression.getOp2().isConstant()) {
      NumberConstant constant1 = (NumberConstant) expression.getOp1();
      NumberConstant constant2 = (NumberConstant) expression.getOp2();
      return new NumberConstant(constant1.getConstant().add(
          constant2.getConstant()));
    } else {
      return expression;
    }
  }

  public Object visitAndExpression(AndExpression expression) {
    this.optimizeBinary(expression);

    boolean op1C = expression.getOp1().isConstant();
    boolean op2C = expression.getOp2().isConstant();

    if (op1C && expression.getOp1().equals(BooleanConstant.FALSE)) {
      return BooleanConstant.FALSE;
    }

    if (op2C && expression.getOp2().equals(BooleanConstant.FALSE)) {
      return BooleanConstant.FALSE;
    }

    if (op1C && expression.getOp1().equals(BooleanConstant.TRUE)) {
      return expression.getOp2();
    }

    if (op2C && expression.getOp2().equals(BooleanConstant.TRUE)) {
      return expression.getOp1();
    }

    if (op1C && op2C) {
      BooleanConstant constant1 = (BooleanConstant) expression.getOp1();
      BooleanConstant constant2 = (BooleanConstant) expression.getOp2();
      return new BooleanConstant(constant1.getConstant() && constant2.getConstant());
    } else {
      return expression;
    }
  }

  public Object visitConcatExpression(ConcatExpression expression) {
    this.optimizeBinary(expression);

    boolean op1C = expression.getOp1().isConstant();
    boolean op2C = expression.getOp2().isConstant();

    if (op1C && op2C) {
      StringConstant constant1 = (StringConstant) expression.getOp1();
      StringConstant constant2 = (StringConstant) expression.getOp2();
      return new StringConstant(constant1.getConstant()
          + constant2.getConstant());
    } else {
      return expression;
    }
  }

  public Object visitArrayAccessExpression(ArrayAccessExpression arrayAccessExpression) {
    Expression index = this.optimize(arrayAccessExpression.getIndex());
    Expression array = this.optimize(arrayAccessExpression.getArrayExpression());
    
    if (index.isConstant()) {
      if (array.isConstant()) {
        return ((ArrayExpression) array).get(((NumberConstant) index).getIntValue());
      }
    }
    
    return new ArrayAccessExpression((ArrayHolder) array, index);
  }

  public Object visitArrayExpression(ArrayExpression arrayExpression) {
    return arrayExpression;
  }

  public Object visitBooleanConstant(BooleanConstant booleanConstant) {
    return booleanConstant;
  }

  public Object visitDivExpression(DivExpression expression) {
    this.optimizeBinary(expression);

    if (expression.getOp1().isConstant() && expression.getOp2().isConstant()) {
      NumberConstant constant1 = (NumberConstant) expression.getOp1();
      NumberConstant constant2 = (NumberConstant) expression.getOp2();
      return new NumberConstant(constant1.getConstant().divide(
          constant2.getConstant()));
    } else {
      return expression;
    }
  }

  public Object visitEqualsExpression(EqualsExpression equalsExpression) {
    this.optimizeBinary(equalsExpression);

    if (equalsExpression.getOp1().isConstant()
        && equalsExpression.getOp2().isConstant()) {
      return new BooleanConstant(equalsExpression.getOp1().equals(
          equalsExpression.getOp2()));
    } else {
      return equalsExpression;
    }
  }
  
  private List<Expression> optimize(List<Expression> expressions) {
    List<Expression> exps = new ArrayList<Expression>();
    for (Expression exp : expressions) {
      exps.add(optimize(exp));
    }
    return exps;
  }

  public Object visitFunctionCallExpression(FunctionCallExpression functionCallExpression) {
    return new FunctionCallExpression(
        functionCallExpression.getFunctionName(), 
        optimize(functionCallExpression.getArguments()));
  }

  public Object visitNativeFunctionCallExpression(NativeFunctionCallExpression functionCallExpression) {
    return new NativeFunctionCallExpression(
        functionCallExpression.getFunctionName(), 
        optimize(functionCallExpression.getArguments()));
  }
  
  public Object visitToBooleanFunctionCallExpression(ToBooleanFunctionCallExpression functionCallExpression) {
    List<Expression> arguments = optimize(functionCallExpression.getArguments());
    if (arguments.size() == 1 && arguments.get(0).isConstant()) {
      return ((ConstantExpression<?>) arguments.get(0)).coerceToBoolean();
    } else {
      return new ToBooleanFunctionCallExpression(arguments);
    }
  }

  public Object visitToNumberFunctionCallExpression(ToNumberFunctionCallExpression functionCallExpression) {
    List<Expression> arguments = optimize(functionCallExpression.getArguments());
    if (arguments.size() == 1 && arguments.get(0).isConstant()) {
      return ((ConstantExpression<?>) arguments.get(0)).coerceToNumber();
    } else {
      return new ToNumberFunctionCallExpression(arguments);
    }
  }

  public Object visitToStringFunctionCallExpression(ToStringFunctionCallExpression functionCallExpression) {
    List<Expression> arguments = optimize(functionCallExpression.getArguments());
    if (arguments.size() == 1 && arguments.get(0).isConstant()) {
      return ((ConstantExpression<?>) arguments.get(0)).coerceToString();
    } else {
      return new ToStringFunctionCallExpression(arguments);
    }
  }

  public Object visitGeneratorExpression(GeneratorExpression generatorExpression) {
    return generatorExpression;
  }

  public Object visitGreaterEqualExpression(GreaterEqualExpression expression) {
    this.optimizeBinary(expression);

    if (expression.getOp1().isConstant() && expression.getOp2().isConstant()) {
      Comparable comp1 = (Comparable) ((ConstantExpression<?>) expression
          .getOp1()).getConstant();
      Comparable comp2 = (Comparable) ((ConstantExpression<?>) expression
          .getOp2()).getConstant();

      return new BooleanConstant(comp1.compareTo(comp2) >= 0);
    } else {
      return expression;
    }
  }

  public Object visitGreaterExpression(GreaterExpression expression) {
    this.optimizeBinary(expression);

    if (expression.getOp1().isConstant() && expression.getOp2().isConstant()) {
      Comparable comp1 = (Comparable) ((ConstantExpression<?>) expression
          .getOp1()).getConstant();
      Comparable comp2 = (Comparable) ((ConstantExpression<?>) expression
          .getOp2()).getConstant();

      return new BooleanConstant(comp1.compareTo(comp2) > 0);
    } else {
      return expression;
    }
  }

  public Object visitImpliesExpression(ImpliesExpression expression) {
    this.optimizeBinary(expression);

    boolean op1C = expression.getOp1().isConstant();
    boolean op2C = expression.getOp2().isConstant();

    if (op1C && expression.getOp1().equals(BooleanConstant.FALSE)) {
      return new BooleanConstant(true);
    }

    if (op1C && expression.getOp1().equals(BooleanConstant.TRUE)) {
      return expression.getOp2();
    }

    if (op1C && op2C) {
      BooleanConstant constant1 = (BooleanConstant) expression.getOp1();
      BooleanConstant constant2 = (BooleanConstant) expression.getOp2();
      return new BooleanConstant(!constant1.getConstant()
          || constant2.getConstant());
    } else {
      return expression;
    }
  }

  public Object visitLowerEqualExpression(LowerEqualExpression expression) {
    this.optimizeBinary(expression);

    if (expression.getOp1().isConstant() && expression.getOp2().isConstant()) {
      Comparable comp1 = (Comparable) ((ConstantExpression<?>) expression
          .getOp1()).getConstant();
      Comparable comp2 = (Comparable) ((ConstantExpression<?>) expression
          .getOp2()).getConstant();

      return new BooleanConstant(comp1.compareTo(comp2) <= 0);
    } else {
      return expression;
    }
  }

  public Object visitLowerExpression(LowerExpression expression) {
    this.optimizeBinary(expression);

    if (expression.getOp1().isConstant() && expression.getOp2().isConstant()) {
      Comparable comp1 = (Comparable) ((ConstantExpression<?>) expression
          .getOp1()).getConstant();
      Comparable comp2 = (Comparable) ((ConstantExpression<?>) expression
          .getOp2()).getConstant();

      return new BooleanConstant(comp1.compareTo(comp2) < 0);
    } else {
      return expression;
    }
  }

  public Object visitMulExpression(MulExpression expression) {
    this.optimizeBinary(expression);

    if (expression.getOp1().isConstant() && expression.getOp2().isConstant()) {
      NumberConstant constant1 = (NumberConstant) expression.getOp1();
      NumberConstant constant2 = (NumberConstant) expression.getOp2();
      return new NumberConstant(constant1.getConstant().multiply(
          constant2.getConstant()));
    } else {
      return expression;
    }
  }

  public Object visitNotEqualsExpression(NotEqualsExpression expression) {
    this.optimizeBinary(expression);

    if (expression.getOp1().isConstant() && expression.getOp2().isConstant()) {
      return new BooleanConstant(!expression.getOp1().equals(
          expression.getOp2()));
    } else {
      return expression;
    }
  }

  public Object visitNotExpression(NotExpression notExpression) {
    Expression exp = this.optimize(notExpression.getExpression());
    notExpression.setExpression(exp);
    if (exp.isConstant()) {
      return new BooleanConstant(!((BooleanConstant) exp).getConstant());
    } else {
      if (exp instanceof NotExpression) {
        return ((NotExpression) exp).getExpression();
      } else {
        return notExpression;
      }
    }
  }

  public Object visitNumberConstant(NumberConstant integerConstant) {
    return integerConstant;
  }

  public Object visitOrExpression(OrExpression expression) {
    this.optimizeBinary(expression);

    boolean op1C = expression.getOp1().isConstant();
    boolean op2C = expression.getOp2().isConstant();

    if (op1C && expression.getOp1().equals(BooleanConstant.TRUE)) {
      return BooleanConstant.TRUE;
    }

    if (op2C && expression.getOp2().equals(BooleanConstant.TRUE)) {
      return BooleanConstant.TRUE;
    }

    if (op1C && expression.getOp1().equals(BooleanConstant.FALSE)) {
      return expression.getOp2();
    }

    if (op2C && expression.getOp2().equals(BooleanConstant.FALSE)) {
      return expression.getOp1();
    }

    if (op1C && op2C) {
      BooleanConstant constant1 = (BooleanConstant) expression.getOp1();
      BooleanConstant constant2 = (BooleanConstant) expression.getOp2();
      return new BooleanConstant(constant1.getConstant()
          || constant2.getConstant());
    } else {
      return expression;
    }
  }

  public Object visitStringConstant(StringConstant stringConstant) {
    return new StringConstant(this.optimizeString(stringConstant.getConstant()));
  }

  public Object visitSubExpression(SubExpression expression) {
    this.optimizeBinary(expression);

    if (expression.getOp1().isConstant() && expression.getOp2().isConstant()) {
      NumberConstant constant1 = (NumberConstant) expression.getOp1();
      NumberConstant constant2 = (NumberConstant) expression.getOp2();
      return new NumberConstant(constant1.getConstant().subtract(
          constant2.getConstant()));
    } else {
      return expression;
    }
  }

  public Object visitVariableValue(VariableValue variableValue) {
    return variableValue;
  }

  public Object visitWidgetPropertyReference(WidgetPropertyReference widgetPropertyReference) {
    WidgetPropertyReference newWPR = new WidgetPropertyReference(
            widgetPropertyReference.getWidget(), 
            widgetPropertyReference.getPropertyName());
    String optimizedLocation = this.optimizeString(widgetPropertyReference.getPreferedLocation());
    newWPR.setLocation(optimizedLocation);
    newWPR.setVariables(this.optimizeMap(widgetPropertyReference.getVariables()));
    return newWPR;
  }

  public Object visitWidgetReference(WidgetReference widgetReference) {
    WidgetReference newWR = new WidgetReference(widgetReference.getWidget());
    String optimizedLocation = this.optimizeString(widgetReference.getPreferedLocation());
    newWR.setLocation(optimizedLocation);
    newWR.setVariables(this.optimizeMap(widgetReference.getVariables()));
    return newWR;
  }
  
  public Map<String, Expression> optimizeMap(Map<String, Expression> map) {
    Map<String, Expression> newMap = new HashMap<String, Expression>(map);
    for (String key : newMap.keySet()) {
      newMap.put(key, this.optimize(newMap.get(key)));
    }
    return newMap;
  }

  private String optimizeString(String constant) {
    return constant;
  }

  public Object visitInteractionPropertyExpression(InteractionPropertyExpression interactionPropertyExpression) {
    return interactionPropertyExpression;
  }
}
