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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.api.Action;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.impl.action.ExpressionAction;
import org.webspeclanguage.impl.action.LetVariable;
import org.webspeclanguage.impl.core.InteractionImpl;
import org.webspeclanguage.impl.expression.core.AbstractFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.AddExpression;
import org.webspeclanguage.impl.expression.core.AndExpression;
import org.webspeclanguage.impl.expression.core.ArrayAccessExpression;
import org.webspeclanguage.impl.expression.core.ArrayExpression;
import org.webspeclanguage.impl.expression.core.ArrayHolder;
import org.webspeclanguage.impl.expression.core.BooleanConstant;
import org.webspeclanguage.impl.expression.core.ConcatExpression;
import org.webspeclanguage.impl.expression.core.ConstantExpression;
import org.webspeclanguage.impl.expression.core.DivExpression;
import org.webspeclanguage.impl.expression.core.EqualsExpression;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.core.ExpressionType;
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
import org.webspeclanguage.impl.expression.parser.analysis.DepthFirstAdapter;
import org.webspeclanguage.impl.expression.parser.node.AAddNumExpr;
import org.webspeclanguage.impl.expression.parser.node.AAndExpr;
import org.webspeclanguage.impl.expression.parser.node.AArray;
import org.webspeclanguage.impl.expression.parser.node.AArrayAccessValue;
import org.webspeclanguage.impl.expression.parser.node.AArrayValue;
import org.webspeclanguage.impl.expression.parser.node.AArrayVariableorliteralarray;
import org.webspeclanguage.impl.expression.parser.node.ABooleanValue;
import org.webspeclanguage.impl.expression.parser.node.ACompExprExpr;
import org.webspeclanguage.impl.expression.parser.node.AConcatFactor;
import org.webspeclanguage.impl.expression.parser.node.ADivFactor;
import org.webspeclanguage.impl.expression.parser.node.AEqualCompExpr;
import org.webspeclanguage.impl.expression.parser.node.AExprAction;
import org.webspeclanguage.impl.expression.parser.node.AFactorNumExpr;
import org.webspeclanguage.impl.expression.parser.node.AFalseBoolean;
import org.webspeclanguage.impl.expression.parser.node.AFunctioncallValue;
import org.webspeclanguage.impl.expression.parser.node.AGeneratorValue;
import org.webspeclanguage.impl.expression.parser.node.AGreaterCompExpr;
import org.webspeclanguage.impl.expression.parser.node.AGreaterEqualCompExpr;
import org.webspeclanguage.impl.expression.parser.node.AImpliesExpr;
import org.webspeclanguage.impl.expression.parser.node.AInterfactionPropertyValue;
import org.webspeclanguage.impl.expression.parser.node.AInterfactionWidgetPropertyValue;
import org.webspeclanguage.impl.expression.parser.node.ALetAction;
import org.webspeclanguage.impl.expression.parser.node.ALowerCompExpr;
import org.webspeclanguage.impl.expression.parser.node.ALowerEqualCompExpr;
import org.webspeclanguage.impl.expression.parser.node.AManyactionsActions;
import org.webspeclanguage.impl.expression.parser.node.AManyargsArguments;
import org.webspeclanguage.impl.expression.parser.node.AMulFactor;
import org.webspeclanguage.impl.expression.parser.node.ANativefunctioncallValue;
import org.webspeclanguage.impl.expression.parser.node.ANotEqualCompExpr;
import org.webspeclanguage.impl.expression.parser.node.ANotExpr;
import org.webspeclanguage.impl.expression.parser.node.ANumExprCompExpr;
import org.webspeclanguage.impl.expression.parser.node.ANumberValue;
import org.webspeclanguage.impl.expression.parser.node.AOneargArguments;
import org.webspeclanguage.impl.expression.parser.node.AOrExpr;
import org.webspeclanguage.impl.expression.parser.node.AParensValue;
import org.webspeclanguage.impl.expression.parser.node.ASimplewidgetWidgetOrWidgetAccess;
import org.webspeclanguage.impl.expression.parser.node.ASingleactionActions;
import org.webspeclanguage.impl.expression.parser.node.AStringValue;
import org.webspeclanguage.impl.expression.parser.node.ASubNumExpr;
import org.webspeclanguage.impl.expression.parser.node.ATrueBoolean;
import org.webspeclanguage.impl.expression.parser.node.AValueFactor;
import org.webspeclanguage.impl.expression.parser.node.AVariable;
import org.webspeclanguage.impl.expression.parser.node.AVariableValue;
import org.webspeclanguage.impl.expression.parser.node.AVariableVariableorliteralarray;
import org.webspeclanguage.impl.expression.parser.node.AWidgetarrayaccessWidgetOrWidgetAccess;
import org.webspeclanguage.impl.expression.parser.node.Start;
import org.webspeclanguage.impl.expression.parser.node.TNumber;
import org.webspeclanguage.impl.widget.Container;
import org.webspeclanguage.impl.widget.ListOfContainer;
import org.webspeclanguage.impl.widget.Widget;

/**
 * A transformer that DFS the parsing tree and build the
 * {@link Expression} tree
 * 
 * @author Esteban Robles Luna
 */
@SuppressWarnings("unchecked")
public class ExpressionTransformer extends DepthFirstAdapter {

  private Diagram diagram;

  public ExpressionTransformer(Diagram diagram) {
    this.diagram = diagram;
  }

  public List<Action> transform(Start start) {
    this.caseStart(start);
    return (List<Action>) this.getOut(start);
  }

  @Override
  public void outAAddNumExpr(AAddNumExpr node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new AddExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outAAndExpr(AAndExpr node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new AndExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outAImpliesExpr(AImpliesExpr node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new ImpliesExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outAConcatFactor(AConcatFactor node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new ConcatExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outABooleanValue(ABooleanValue node) {
    this.setOut(node, this.getOut(node.getBoolean()));
  }

  @Override
  public void outACompExprExpr(ACompExprExpr node) {
    this.setOut(node, this.getOut(node.getCompExpr()));
  }

  @Override
  public void outADivFactor(ADivFactor node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new DivExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outAEqualCompExpr(AEqualCompExpr node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new EqualsExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outAFactorNumExpr(AFactorNumExpr node) {
    this.setOut(node, this.getOut(node.getFactor()));
  }

  @Override
  public void outAFalseBoolean(AFalseBoolean node) {
    this.setOut(node, new BooleanConstant(false));
  }

  @Override
  public void outAGreaterCompExpr(AGreaterCompExpr node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new GreaterExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outAGreaterEqualCompExpr(AGreaterEqualCompExpr node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new GreaterEqualExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outANumberValue(ANumberValue node) {
    this.setOut(node, this.getNumber(node.getNumber()));
  }

  private NumberConstant getNumber(TNumber node) {
    return new NumberConstant(new BigDecimal(node.getText()));
  }

  @Override
  public void outALowerCompExpr(ALowerCompExpr node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new LowerExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outALowerEqualCompExpr(ALowerEqualCompExpr node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new LowerEqualExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outAMulFactor(AMulFactor node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new MulExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outANotEqualCompExpr(ANotEqualCompExpr node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new NotEqualsExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outANotExpr(ANotExpr node) {
    Expression exp = (Expression) this.getOut(node.getCompExpr());

    Expression result = new NotExpression(exp);

    this.setOut(node, result);
  }

  @Override
  public void outANumExprCompExpr(ANumExprCompExpr node) {
    this.setOut(node, this.getOut(node.getNumExpr()));
  }

  @Override
  public void outAOrExpr(AOrExpr node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new OrExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outAParensValue(AParensValue node) {
    this.setOut(node, this.getOut(node.getExpr()));
  }

  @Override
  public void outAStringValue(AStringValue node) {
    this.setOut(node, new StringConstant(node.getString().getText().substring(
        1, node.getString().getText().length() - 1)));
  }

  @Override
  public void outASubNumExpr(ASubNumExpr node) {
    Expression left = (Expression) this.getOut(node.getLeft());
    Expression right = (Expression) this.getOut(node.getRight());

    Expression result = new SubExpression(left, right);

    this.setOut(node, result);
  }

  @Override
  public void outATrueBoolean(ATrueBoolean node) {
    this.setOut(node, new BooleanConstant(true));
  }

  @Override
  public void outAValueFactor(AValueFactor node) {
    this.setOut(node, this.getOut(node.getValue()));
  }

  @Override
  public void outAVariableValue(AVariableValue node) {
    this.setOut(node, this.getOut(node.getVariable()));
  }

  @Override
  public void outAArrayAccessValue(AArrayAccessValue node) {
    Expression exp = (Expression) this.getOut(node.getExpr());
    ArrayHolder arrayHolder = (ArrayHolder) this.getOut(node.getVariableorliteralarray());
    this.setOut(node, new ArrayAccessExpression(arrayHolder, exp));
  }

  @Override
  public void outAArrayVariableorliteralarray(AArrayVariableorliteralarray node) {
    this.setOut(node, this.getOut(node.getArray()));
  }

  @Override
  public void outAVariableVariableorliteralarray(AVariableVariableorliteralarray node) {
    this.setOut(node, this.getOut(node.getVariable()));
  }

  @Override
  public void outAArrayValue(AArrayValue node) {
    this.setOut(node, this.getOut(node.getArray()));
  }
  
  @Override
  public void outAArray(AArray node) {
    List<Expression> arguments = (List<Expression>) this.getOut(node.getArguments());
    ConstantExpression<?>[] exps = new ConstantExpression<?>[arguments.size()];
    for (int i = 0; i < arguments.size(); i++) {
      ConstantExpression<?> constantExpression = (ConstantExpression<?>) arguments
          .get(i);
      exps[i] = constantExpression;
    }
    this.setOut(node, new ArrayExpression(exps));
  }

  @Override
  public void outStart(Start node) {
    this.setOut(node, this.getOut(node.getPActions()));
  }

  @Override
  public void outAExprAction(AExprAction node) {
    this.setOut(node, new ExpressionAction((Expression) this.getOut(node
        .getExpr())));
  }

  @Override
  public void outAFunctioncallValue(AFunctioncallValue node) {
    String functionName = (String) node.getIdentifier().getText();
    AbstractFunctionCallExpression fc = null;
    List<Expression> arguments = (List<Expression>) this.getOut(node.getArguments());
    
    if (functionName.equals("toString")) {
     fc = new ToStringFunctionCallExpression(arguments);
    } else if (functionName.equals("toNumber")) {
      fc = new ToNumberFunctionCallExpression(arguments);
    } else if (functionName.equals("toBoolean")) {
      fc = new ToBooleanFunctionCallExpression(arguments);
    } else {
      fc = new FunctionCallExpression(functionName, arguments);
    }
    
    this.setOut(node, fc);
  }

  @Override
  public void outANativefunctioncallValue(ANativefunctioncallValue node) {
    AbstractFunctionCallExpression fc = new NativeFunctionCallExpression((String) node
        .getIdentifier().getText(), (List<Expression>) this.getOut(node
        .getArguments()));
    this.setOut(node, fc);
  }

  @Override
  public void outALetAction(ALetAction node) {
    ExpressionType type = null;
    try {
      type = ExpressionType.valueOf(node.getType().toString().trim()
          .toUpperCase());
    } catch (Exception e) {
    }

    LetVariable let = new LetVariable(node.getIdentifier().getText(),
        (Expression) this.getOut(node.getExpr()), type);
    this.setOut(node, let);
  }

  @Override
  public void outAManyargsArguments(AManyargsArguments node) {
    List<Expression> arguments = new ArrayList<Expression>();
    arguments.add((Expression) this.getOut(node.getExpr()));
    arguments.addAll((Collection<? extends Expression>) this.getOut(node
        .getArguments()));
    this.setOut(node, arguments);
  }

  @Override
  public void outAOneargArguments(AOneargArguments node) {
    List<Expression> arguments = new ArrayList<Expression>();
    arguments.add((Expression) this.getOut(node.getExpr()));
    this.setOut(node, arguments);
  }

  @Override
  public void outAManyactionsActions(AManyactionsActions node) {
    List<Action> actions = new ArrayList<Action>();
    actions.add((Action) this.getOut(node.getAction()));
    actions.addAll((Collection<? extends Action>) this
        .getOut(node.getActions()));
    this.setOut(node, actions);
  }

  @Override
  public void outASingleactionActions(ASingleactionActions node) {
    List<Action> actions = new ArrayList<Action>();
    actions.add((Action) this.getOut(node.getAction()));
    this.setOut(node, actions);
  }

  @Override
  public void outAGeneratorValue(AGeneratorValue node) {
    this.setOut(node, new GeneratorExpression(node.getIdentifier().getText()));
  }

  @Override
  public void outAInterfactionPropertyValue(AInterfactionPropertyValue node) {
    String iName = node.getInteraction().getText();
    String propertyOrWidget = node.getProperty().getText();
    
    Interaction interaction = this.diagram.getInteractionNamed(iName);
    Widget widget = interaction.getWidget(propertyOrWidget);
    if (widget != null) {
      this.setOut(node, new WidgetReference(widget));
    } else {
      this.setOut(node, new InteractionPropertyExpression(interaction, propertyOrWidget));
    }
  }

  @Override
  public void outAVariable(AVariable node) {
    this.setOut(node, new VariableValue(node.getI().getText()));
  }
  
  private Container currentContainer;
  private Map<String, Expression> currentVariables = new HashMap<String, Expression>();
  private Widget lastWidget;
  private String interactionName;
  private String property;
  
  @Override
  public void inAInterfactionWidgetPropertyValue(AInterfactionWidgetPropertyValue node) {
    this.currentVariables.clear();
    this.interactionName = node.getInteraction().getText();
    Interaction interaction = this.diagram.getInteractionNamed(this.interactionName);
    this.currentContainer = interaction.getRoot();
    this.property = node.getProperty().getText();
  }

  @Override
  public void outASimplewidgetWidgetOrWidgetAccess(ASimplewidgetWidgetOrWidgetAccess node) {
    String widgetName = node.getWidget().getText();
    if (this.currentContainer != null && this.currentContainer.getWidgetNamed(widgetName) != null) {
      this.lastWidget = this.currentContainer.getWidgetNamed(widgetName);
      if (this.lastWidget instanceof Container) {
        this.currentContainer = (Container) this.lastWidget;
      }
    } else {
      throw new WidgetNotFoundException(this.interactionName, widgetName);
    }
  }

  @Override
  public void outAWidgetarrayaccessWidgetOrWidgetAccess(AWidgetarrayaccessWidgetOrWidgetAccess node) {
    String widgetName = node.getWidget().getText();
    if (this.currentContainer != null && this.currentContainer.getWidgetNamed(widgetName) != null) {
      Widget widget = this.currentContainer.getWidgetNamed(widgetName);
      if (!(widget instanceof ListOfContainer)) {
        throw new IncompatibleWidgetException(this.interactionName, widgetName);
      } else {
        this.currentContainer = (Container) widget;
        this.currentVariables.put(widgetName, (Expression) this.getOut(node.getExpr()));
      }
    } else {
      throw new WidgetNotFoundException(this.interactionName, widgetName);
    }
  }

  @Override
  public void outAInterfactionWidgetPropertyValue(AInterfactionWidgetPropertyValue node) {
    Widget widget = this.currentContainer.getWidgetNamed(this.property);
    if (widget != null) {
      this.setOut(node, new WidgetReference(widget, this.currentVariables));
    } else {
      WidgetPropertyReference widgetPropertyReference = new WidgetPropertyReference(this.lastWidget, this.property, this.currentVariables);
      this.setOut(node, widgetPropertyReference);
    }
  }
}
