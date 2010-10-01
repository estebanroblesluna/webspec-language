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
package org.webspeclanguage.impl.expression.optimizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.impl.expression.core.AddExpression;
import org.webspeclanguage.impl.expression.core.AndExpression;
import org.webspeclanguage.impl.expression.core.ArrayAccessExpression;
import org.webspeclanguage.impl.expression.core.ArrayExpression;
import org.webspeclanguage.impl.expression.core.ArrayHolder;
import org.webspeclanguage.impl.expression.core.BinaryExpression;
import org.webspeclanguage.impl.expression.core.BooleanConstant;
import org.webspeclanguage.impl.expression.core.ConcatExpression;
import org.webspeclanguage.impl.expression.core.ConstantExpression;
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
