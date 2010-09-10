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
package org.webspeclanguage.webspec2test;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.action.Action;
import org.webspeclanguage.action.ActionVisitor;
import org.webspeclanguage.action.ExpressionAction;
import org.webspeclanguage.action.LetVariable;
import org.webspeclanguage.base.PathComputer;
import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.base.WebSpecInteraction;
import org.webspeclanguage.base.WebSpecNavigation;
import org.webspeclanguage.base.WebSpecPath;
import org.webspeclanguage.base.WebSpecPathItem;
import org.webspeclanguage.base.WebSpecPathItemVisitor;
import org.webspeclanguage.base.WebSpecRichBehavior;
import org.webspeclanguage.base.WebSpecTransition;
import org.webspeclanguage.expression.base.BooleanConstant;
import org.webspeclanguage.expression.base.ConstantExpression;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.concretizer.ExpressionConcretizer;
import org.webspeclanguage.expression.conjunctivenormalform.ExpressionConvertorToConjunctiveNormalForm;
import org.webspeclanguage.expression.optimizer.ExpressionOptimizer;
import org.webspeclanguage.generator.Generator;
import org.webspeclanguage.webtest.action.WebAction;
import org.webspeclanguage.webtest.action.WebCreateVariableFromExpression;
import org.webspeclanguage.webtest.action.WebExpression;
import org.webspeclanguage.webtest.action.WebOpenUrl;
import org.webspeclanguage.webtest.action.WebWaitPageToLoad;
import org.webspeclanguage.webtest.assertion.WebAssertExpression;
import org.webspeclanguage.webtest.assertion.WebAssertTitle;
import org.webspeclanguage.webtest.base.SimpleWebTest;

/**
 * A transformation from diagrams to tests
 * 
 * @author Esteban Robles Luna
 */
public class WebSpec2WebTestTransformation {

  private ExpressionOptimizer optimizer;
  private ExpressionConcretizer concretizer;
  private ExpressionConvertorToConjunctiveNormalForm cnfConvertor;

  private WebSpecDiagram currentDiagram;

  private TestGenerationResult result;
  private SimpleWebTest currentTest;

  public WebSpec2WebTestTransformation() {
    this.setCnfConvertor(new ExpressionConvertorToConjunctiveNormalForm());
    this.basicInitializeOptimizerAndConcretizer();
  }

  public TestGenerationResult transform(WebSpecDiagram diagram) {
    Validate.notNull(diagram);
    
    this.setCurrentDiagram(diagram);
    this.createResult(diagram);

    List<WebSpecPath> paths = this.computePathsFor(diagram);

    for (WebSpecPath path : paths) {
      try {
        this.initializeOptimizerAndConcretizer();
        SimpleWebTest result = this.computeResult(path, diagram);
        this.getResult().addTest(path, result);
      } catch (UnsatisfiedPreconditionException e) {
      } catch (UnsatisfiedInvariantException e) {
      }
    }
    return this.getResult();
  }

  private void initializeOptimizerAndConcretizer() {
    this.basicInitializeOptimizerAndConcretizer();

    for (String generatorName : this.getCurrentDiagram().getGeneratorsNames()) {
      Generator generator = this.getCurrentDiagram().getGeneratorNamed(
          generatorName);
      this.getConcretizer().set(generatorName, generator);
    }
  }

  private void basicInitializeOptimizerAndConcretizer() {
    this.setOptimizer(new ExpressionOptimizer());
    this.setConcretizer(new ExpressionConcretizer());
  }

  public List<WebSpecPath> computePathsFor(WebSpecDiagram diagram) {
    return new PathComputer(diagram.getCyclesAllowed())
        .computePathsFor(diagram);
  }

  public SimpleWebTest computeResult(WebSpecPath path, WebSpecDiagram diagram) {
    final SimpleWebTest aT = this.createTest(this.computeNameFor(path));

    this.preItemsIteration();

    for (WebSpecPathItem item : path.getItems()) {
      item.accept(new WebSpecPathItemVisitor() {
        public Object visitWebSpecInteraction(WebSpecInteraction interaction) {
          generateItemsFor(interaction, aT);
          return null;
        }

        public Object visitWebSpecNavigation(WebSpecNavigation navigation) {
          generateItemsFor(navigation, aT);
          return null;
        }

        public Object visitWebSpecRichBehavior(WebSpecRichBehavior richBehavior) {
          generateItemsFor(richBehavior, aT);
          return null;
        }
      });
    }

    return aT;
  }

  protected WebAction generateWebActionFor(Action action, final SimpleWebTest test) {
    
    WebAction webAction = (WebAction) action.accept(new ActionVisitor() {
      public Object visitExpressionAction(ExpressionAction expressionAction) {
        Expression expression = makeConcreteAndOptimize(expressionAction.getExpression());
        return new WebExpression(expression);
      }

      @SuppressWarnings("unchecked")
      public Object visitLetVariable(LetVariable letVariable) {
        Expression expression = makeConcreteAndOptimize(letVariable
            .getExpression());
        if (expression.isConstant()) {
          getConcretizer().set(letVariable.getVariableName(), (ConstantExpression) expression);
          return null;
        } else {
          getConcretizer().removeConstantVariable(letVariable.getVariableName());
          return new WebCreateVariableFromExpression(letVariable
              .getVariableName(), expression, letVariable.getType());
        }
      }
    });

    return webAction;
  }

  protected void createResult(WebSpecDiagram diagram) {
    this.result = new TestGenerationResult(diagram.getName());
  }

  protected TestGenerationResult getResult() {
    return this.result;
  }

  protected SimpleWebTest createTest(String name) {
    this.currentTest = new SimpleWebTest(name);
    return this.currentTest;
  }

  protected void preItemsIteration() {
    for (Action action : this.getCurrentDiagram().getActionsSetup()) {
      WebAction webAction = this.generateWebActionFor(action, this.currentTest);
      if (webAction != null) {
        this.currentTest.addItem(webAction);
      }
    }
  }

  protected void generateItemsFor(WebSpecRichBehavior richBehavior,
      SimpleWebTest test) {
    generateItemsForTransition(richBehavior, test);
  }

  protected void generateItemsFor(WebSpecNavigation navigation,
      SimpleWebTest test) {
    generateItemsForTransition(navigation, test);
    test.addItem(new WebWaitPageToLoad());
  }

  protected void generateItemsForTransition(WebSpecTransition transition,
      SimpleWebTest test) {
    if (transition.getPrecondition() != null) {
      Expression expression = makeConcreteAndOptimize(transition
          .getPrecondition());
      if (expression.equals(BooleanConstant.FALSE)) {
        throw new UnsatisfiedPreconditionException(transition, this
            .getConcretizer().getVariables());
      } else {
        test.addItem(new WebAssertExpression(expression));
      }
    }

    for (Action action : transition.getActions()) {
      WebAction webAction = this.generateWebActionFor(action, test);
      if (webAction != null) {
        test.addItem(webAction);
      }
    }
  }

  protected void generateItemsFor(WebSpecInteraction interaction,
      SimpleWebTest test) {
    if (this.getCurrentDiagram().getStartingInteraction().equals(interaction)) {
      test.addSetUpItem(new WebOpenUrl(interaction.getLocation()));
    }

    if (interaction.getTitle() != null) {
      Expression title = this.makeConcreteAndOptimize(interaction.getTitle());
      test.addItem(new WebAssertTitle(title));
    }

    if (interaction.getInvariant() != null) {
      Expression expression = this.makeConcreteAndOptimize(interaction
          .getInvariant());
      if (expression.equals(BooleanConstant.FALSE)) {
        throw new UnsatisfiedInvariantException(interaction, this.getConcretizer().getVariables());
      } else {
        List<Expression> expressions = this.getCnfConvertor().convertAndObtainDisjunctions(expression);
        for (Expression exp : expressions) {
          test.addItem(new WebAssertExpression(exp));
        }
      }
    }
  }

  protected Generator getGenerator(String generatorName) {
    return this.getGenerators().get(generatorName);
  }

  protected Expression makeConcreteAndOptimize(Expression expression) {
    Expression concrete = this.getConcretizer().makeConcrete(expression);
    concrete = this.getOptimizer().optimize(concrete);
    return concrete;
  }

  private String computeNameFor(WebSpecPath path) {
    return path.getName();
  }

  private Map<String, Generator> getGenerators() {
    return this.getCurrentDiagram().getGenerators();
  }

  public boolean isAllowCycles() {
    return this.getCyclesAllowed() > 0;
  }

  public int getCyclesAllowed() {
    return this.getCurrentDiagram().getCyclesAllowed();
  }

  private ExpressionOptimizer getOptimizer() {
    return optimizer;
  }

  private void setOptimizer(ExpressionOptimizer optimizer) {
    this.optimizer = optimizer;
  }

  protected ExpressionConcretizer getConcretizer() {
    return concretizer;
  }

  private void setConcretizer(ExpressionConcretizer concretizer) {
    this.concretizer = concretizer;
  }

  protected WebSpecDiagram getCurrentDiagram() {
    return currentDiagram;
  }

  public void setCurrentDiagram(WebSpecDiagram currentDiagram) {
    this.currentDiagram = currentDiagram;
  }

  private ExpressionConvertorToConjunctiveNormalForm getCnfConvertor() {
    return cnfConvertor;
  }

  private void setCnfConvertor(
      ExpressionConvertorToConjunctiveNormalForm cnfConvertor) {
    this.cnfConvertor = cnfConvertor;
  }
}
