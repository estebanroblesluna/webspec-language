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
import java.util.Set;

import org.webspeclanguage.api.Action;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.api.Generator;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.PathItem;
import org.webspeclanguage.api.PathItemVisitor;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.impl.action.ActionVisitor;
import org.webspeclanguage.impl.action.ExpressionAction;
import org.webspeclanguage.impl.action.LetVariable;
import org.webspeclanguage.impl.core.Path;
import org.webspeclanguage.impl.core.PathComputer;
import org.webspeclanguage.impl.expression.concretizer.ExpressionConcretizer;
import org.webspeclanguage.impl.expression.conjunctivenormalform.ExpressionConvertorToConjunctiveNormalForm;
import org.webspeclanguage.impl.expression.core.BooleanConstant;
import org.webspeclanguage.impl.expression.core.ConstantExpression;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.core.ExpressionType;
import org.webspeclanguage.impl.expression.core.FunctionCallExpression;
import org.webspeclanguage.impl.expression.core.NumberConstant;
import org.webspeclanguage.impl.expression.core.StringConstant;
import org.webspeclanguage.impl.expression.core.WidgetPropertyReference;
import org.webspeclanguage.impl.expression.core.WidgetReference;
import org.webspeclanguage.impl.expression.optimizer.ExpressionOptimizer;
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

  private Diagram currentDiagram;
  private String homePath;
  private SimulationGenerationResult result;
  
  private ExpressionPrettyPrinter prettyPrinter;

  public WebSpec2WebSimulationTransformation(String homePath) {
    this.homePath = homePath;
    this.prettyPrinter = new ExpressionPrettyPrinter();
    this.cnfConvertor = new ExpressionConvertorToConjunctiveNormalForm();
    this.basicInitializeOptimizerAndConcretizer();
  }

  public SimulationGenerationResult transform(Diagram diagram) {
    this.setCurrentDiagram(diagram);
    this.createResult(diagram);

    List<Path> paths = this.computePathsFor(diagram);

    for (Path path : paths) {
      try {
        this.initializeOptimizerAndConcretizer();
        Simulation simpleSimulation = this.computeSimpleSimulation(path, diagram);
        this.getResult().addSimulation(path, simpleSimulation);
      } catch (UnsatisfiedPreconditionException e) {
        //ignore paths with unsatisfied preconditions
      } catch (UnsatisfiedInvariantException e) {
        //ignore paths with unsatisfied invariants
      }
    }
    return this.getResult();
  }

  protected void generateItemsForTransition(Transition transition, Simulation simulation) {
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
    action.accept(new ActionGenerator(simulation));
  }
  
  private String readableVersionOf(Expression expression) {
    return this.prettyPrinter.prettyPrint(expression).replaceAll("\"", "'");
  }

  @SuppressWarnings("unchecked")
  protected void generateItemsFor(Interaction interaction,  Simulation simulation) {
    simulation.addItem(new OpenMockup("file://" 
        + this.homePath
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
        List<Expression> expressions = this.cnfConvertor.convertAndObtainDisjunctions(expression);
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

    for (Generator generator : this.getCurrentDiagram().getGenerators()) {
      this.getConcretizer().set(generator);
    }
  }

  private void basicInitializeOptimizerAndConcretizer() {
    this.optimizer = new ExpressionOptimizer();
    this.concretizer = new ExpressionConcretizer();
  }

  public List<Path> computePathsFor(Diagram diagram) {
    return new PathComputer(diagram.getCyclesAllowed())
        .computePathsFor(diagram);
  }

  public Simulation computeSimpleSimulation(Path path, Diagram diagram) {
    final Simulation simulation = this.createSimulation(this.computeNameFor(path));

    for (PathItem item : path.getItems()) {
      item.accept(new PathItemVisitor() {
        public Object visitInteraction(Interaction interaction) {
          generateItemsFor(interaction, simulation);
          return null;
        }

        public Object visitNavigation(Navigation navigation) {
          generateItemsFor(navigation, simulation);
          simulation.addItem(new ShowGeneralDescription("Navigation must occur"));
          return null;
        }

        public Object visitRichBehavior(RichBehavior richBehavior) {
          generateItemsFor(richBehavior, simulation);
          return null;
        }

        public Object visitOperationReference(OperationReference operationReference) {
          for (PathItem item : operationReference.getReference().getItems()) {
            item.accept(this);
          }
          return null;
        }
      });
    }

    return simulation;
  }

  protected void createResult(Diagram diagram) {
    this.result = new SimulationGenerationResult(diagram.getName());
  }

  protected SimulationGenerationResult getResult() {
    return this.result;
  }

  protected Simulation createSimulation(String name) {
    return new Simulation(name);
  }

  protected void generateItemsFor(RichBehavior richBehavior, Simulation simulation) {
    generateItemsForTransition(richBehavior, simulation);
  }

  protected void generateItemsFor(Navigation navigation, Simulation simulation) {
    generateItemsForTransition(navigation, simulation);
  }

  protected Generator getGenerator(String generatorName) {
    return this.getCurrentDiagram().getGeneratorNamed(generatorName);
  }

  protected Expression makeConcreteAndOptimize(Expression expression) {
    Expression concrete = this.getConcretizer().makeConcrete(expression);
    concrete = this.optimizer.optimize(concrete);
    return concrete;
  }

  private String computeNameFor(Path path) {
    return path.getName();
  }

  public boolean isAllowCycles() {
    return this.getCyclesAllowed() > 0;
  }

  public int getCyclesAllowed() {
    return this.getCurrentDiagram().getCyclesAllowed();
  }

  protected ExpressionConcretizer getConcretizer() {
    return concretizer;
  }

  protected Diagram getCurrentDiagram() {
    return currentDiagram;
  }

  public void setCurrentDiagram(Diagram currentDiagram) {
    this.currentDiagram = currentDiagram;
  }

  private final class ActionGenerator implements ActionVisitor {

    private final Simulation simulation;

    private ActionGenerator(Simulation simulation) {
      this.simulation = simulation;
    }
    
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
  }
}
