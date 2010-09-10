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
package org.webspeclanguage.webspec2simulation;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.webspeclanguage.expression.base.ExpressionType;
import org.webspeclanguage.expression.base.FunctionCallExpression;
import org.webspeclanguage.expression.base.NumberConstant;
import org.webspeclanguage.expression.base.StringConstant;
import org.webspeclanguage.expression.base.WidgetPropertyReference;
import org.webspeclanguage.expression.base.WidgetReference;
import org.webspeclanguage.expression.concretizer.ExpressionConcretizer;
import org.webspeclanguage.expression.conjunctivenormalform.ExpressionConvertorToConjunctiveNormalForm;
import org.webspeclanguage.expression.optimizer.ExpressionOptimizer;
import org.webspeclanguage.generator.Generator;
import org.webspeclanguage.simulation.ExecuteAction;
import org.webspeclanguage.simulation.ExpressionPrettyPrinter;
import org.webspeclanguage.simulation.OpenMockup;
import org.webspeclanguage.simulation.ShowDescriptionAt;
import org.webspeclanguage.simulation.ShowGeneralDescription;
import org.webspeclanguage.simulation.Simulation;

/**
 * A transformation from diagrams to simulations
 * 
 * @author Esteban Robles Luna
 */
public class WebSpec2WebSimulationTransformation {

  private ExpressionOptimizer optimizer;
  private ExpressionConcretizer concretizer;
  private ExpressionConvertorToConjunctiveNormalForm cnfConvertor;

  private WebSpecDiagram currentDiagram;
  private Simulation currentSimulation;
  private String homePath;
  private SimulationGenerationResult result;
  
  private ExpressionPrettyPrinter prettyPrinter;

  public WebSpec2WebSimulationTransformation(String homePath) {
    this.setHomePath(homePath);
    this.setPrettyPrinter(new ExpressionPrettyPrinter());
    this.setCnfConvertor(new ExpressionConvertorToConjunctiveNormalForm());
    this.basicInitializeOptimizerAndConcretizer();
  }

  public SimulationGenerationResult transform(WebSpecDiagram diagram) {
    this.setCurrentDiagram(diagram);
    this.createResult(diagram);

    List<WebSpecPath> paths = this.computePathsFor(diagram);

    for (WebSpecPath path : paths) {
      try {
        this.initializeOptimizerAndConcretizer();
        Simulation result = this.computeResult(path, diagram);
        this.getResult().addSimulation(path, result);
      } catch (UnsatisfiedPreconditionException e) {
        //ignore paths with unsatisfied preconditions
      } catch (UnsatisfiedInvariantException e) {
        //ignore paths with unsatisfied invariants
      }
    }
    return this.getResult();
  }

  protected void generateItemsForTransition(WebSpecTransition transition,
      Simulation simulation) {
    if (transition.getPrecondition() != null) {
      Expression expression = makeConcreteAndOptimize(transition.getPrecondition());
      if (expression.equals(BooleanConstant.FALSE)) {
        throw new UnsatisfiedPreconditionException(transition, this.getConcretizer().getVariables());
      } else {
        String readableExpression = this.readableVersionOf(expression);
        simulation.addItem(new ShowGeneralDescription(readableExpression));
      }
    }

    for (Action action : transition.getActions()) {
      this.generateSimulationsFor(action, simulation);
    }
  }

  private void generateSimulationsFor(Action action, final Simulation simulation) {
    action.accept(new ActionVisitor() {
      public Object visitExpressionAction(ExpressionAction expressionAction) {
        Expression concreteExpression = makeConcreteAndOptimize(expressionAction.getExpression());
        if (concreteExpression instanceof FunctionCallExpression) {
          FunctionCallExpression functionCall = (FunctionCallExpression) concreteExpression;
          
          boolean hasWidget = false;
          String widgetlocation = "";
          String widgetName = "";
          String widgetType = "";
          for (Expression argument : functionCall.getArguments()) {
            if (argument instanceof WidgetReference) {
              hasWidget = true;
              widgetlocation = ((WidgetReference) argument).getPreferedLocation();
              widgetName = ((WidgetReference) argument).getWidget().getName();
              widgetType = ((WidgetReference) argument).getWidget().getClass().getSimpleName();
            } else if (argument instanceof WidgetPropertyReference) {
              hasWidget = true;
              widgetlocation = ((WidgetPropertyReference) argument).getPreferedLocation();
              widgetName = ((WidgetPropertyReference) argument).getWidget().getName();
              widgetType = ((WidgetPropertyReference) argument).getWidget().getClass().getSimpleName();
            }          
          }
          
          if (hasWidget) {
            simulation.addItem(new ShowDescriptionAt(
                widgetlocation,
                "The user " 
                + functionCall.getFunctionName()
                + "s the " 
                + widgetName
                + " "
                + widgetType));
          } else {
            simulation.addItem(new ShowGeneralDescription(
                "The user " 
                + functionCall.getFunctionName()
                + "s"));
          }

          if (functionCall.getFunctionName().equals("type")) {
            simulation.addItem(new ExecuteAction(functionCall.getFunctionName(), functionCall.getArguments()));
          }
        }
        return null;
      }

      @SuppressWarnings("unchecked")
      public Object visitLetVariable(LetVariable letVariable) {
        Expression expression = makeConcreteAndOptimize(letVariable.getExpression());
        if (expression.isConstant()) {
          getConcretizer().set(letVariable.getVariableName(), (ConstantExpression) expression);
        } else {
          getConcretizer().removeConstantVariable(letVariable.getVariableName());

          boolean hasWidget = false;
          String widgetlocation = "";
          
          if (letVariable.getExpression() instanceof WidgetReference) {
            hasWidget = true;
            widgetlocation = ((WidgetReference) letVariable.getExpression()).getPreferedLocation();
          } else if (letVariable.getExpression() instanceof WidgetPropertyReference) {
            hasWidget = true;
            widgetlocation = ((WidgetPropertyReference) letVariable.getExpression()).getPreferedLocation();
          }
          
          ConstantExpression constantValue = new StringConstant("");
          if (letVariable.getType().equals(ExpressionType.BOOLEAN)) {
            constantValue = BooleanConstant.TRUE;
          } else if (letVariable.getType().equals(ExpressionType.NUMBER)) {
            constantValue = new NumberConstant(new BigDecimal("100"));
          } else if (letVariable.getType().equals(ExpressionType.STRING)) {
            constantValue = new StringConstant("3456");
          }
          
          getConcretizer().set(letVariable.getVariableName(), constantValue);
          String assumed = ". Assumed value " + readableVersionOf(constantValue);
          
          String readableExpression = readableVersionOf(expression);
          if (hasWidget) {
            simulation.addItem(new ShowDescriptionAt(
                widgetlocation,
                "Variable " 
                + letVariable.getVariableName() 
                + " will take the value: " 
                + readableExpression
                + assumed));
          } else {
            simulation.addItem(new ShowGeneralDescription(
                "Variable " 
                + letVariable.getVariableName() 
                + " will take the value: " 
                + readableExpression
                + assumed));
          }
        }
        return null;
      }
    });
  }
  
  private String readableVersionOf(Expression expression) {
    return this.getPrettyPrinter().prettyPrint(expression).replaceAll("\"", "'");
  }

  @SuppressWarnings("unchecked")
  protected void generateItemsFor(WebSpecInteraction interaction,  Simulation simulation) {
    simulation.addItem(new OpenMockup("file://" 
        + this.getHomePath()
        + interaction.getMockupFile()));

    if (interaction.getTitle() != null) {
      Expression expression = this.makeConcreteAndOptimize(interaction.getTitle());
      String readableExpression = this.readableVersionOf(expression);
      simulation.addItem(new ShowGeneralDescription(
          "The title should be: "
          + readableExpression));
    }

    if (interaction.getInvariant() != null) {
      Expression expression = this.makeConcreteAndOptimize(interaction.getInvariant());
      if (expression.equals(BooleanConstant.FALSE)) {
        throw new UnsatisfiedInvariantException(interaction, this.getConcretizer().getVariables());
      } else {
        List<Expression> expressions = this.getCnfConvertor().convertAndObtainDisjunctions(expression);
        for (Expression exp : expressions) {
          String readableExpression = this.readableVersionOf(exp);
          
          Set instances = new HashSet();
          exp.getInstancesOfOn(WidgetReference.class, instances);
          if (instances.size() == 1) {
            String widgetLocation = ((WidgetReference) instances.iterator().next()).getPreferedLocation();
            simulation.addItem(new ShowDescriptionAt(
                widgetLocation,
                "It should be: "
                + readableExpression));
          } else {
            instances.clear();
            exp.getInstancesOfOn(WidgetPropertyReference.class, instances);
            if (instances.size() == 1) {
              String widgetLocation = ((WidgetPropertyReference) instances.iterator().next()).getPreferedLocation();
              simulation.addItem(new ShowDescriptionAt(
                  widgetLocation,
                  "It should be: "
                  + readableExpression));
            } else {
              simulation.addItem(new ShowGeneralDescription(
                  "It should be: "
                  + readableExpression));
              
            }
          }
        }
      }
    }
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

  public Simulation computeResult(WebSpecPath path, WebSpecDiagram diagram) {
    final Simulation simulation = this.createSimulation(this
        .computeNameFor(path));

    for (WebSpecPathItem item : path.getItems()) {
      item.accept(new WebSpecPathItemVisitor() {
        public Object visitWebSpecInteraction(WebSpecInteraction interaction) {
          generateItemsFor(interaction, simulation);
          return null;
        }

        public Object visitWebSpecNavigation(WebSpecNavigation navigation) {
          generateItemsFor(navigation, simulation);
          simulation.addItem(new ShowGeneralDescription("Navigation must occur"));
          return null;
        }

        public Object visitWebSpecRichBehavior(WebSpecRichBehavior richBehavior) {
          generateItemsFor(richBehavior, simulation);
          return null;
        }
      });
    }

    return simulation;
  }

  protected void createResult(WebSpecDiagram diagram) {
    this.result = new SimulationGenerationResult(diagram.getName());
  }

  protected SimulationGenerationResult getResult() {
    return this.result;
  }

  protected Simulation createSimulation(String name) {
    this.currentSimulation = new Simulation(name);
    return this.currentSimulation;
  }

  protected void generateItemsFor(WebSpecRichBehavior richBehavior,
      Simulation simulation) {
    generateItemsForTransition(richBehavior, simulation);
  }

  protected void generateItemsFor(WebSpecNavigation navigation,
      Simulation simulation) {
    generateItemsForTransition(navigation, simulation);
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

  private String getHomePath() {
    return homePath;
  }

  private void setHomePath(String homePath) {
    this.homePath = homePath;
  }

  private ExpressionPrettyPrinter getPrettyPrinter() {
    return prettyPrinter;
  }

  private void setPrettyPrinter(ExpressionPrettyPrinter prettyPrinter) {
    this.prettyPrinter = prettyPrinter;
  }

  private ExpressionConvertorToConjunctiveNormalForm getCnfConvertor() {
    return cnfConvertor;
  }

  private void setCnfConvertor(
      ExpressionConvertorToConjunctiveNormalForm cnfConvertor) {
    this.cnfConvertor = cnfConvertor;
  }
}
