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
package org.webspeclanguage.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.action.Action;
import org.webspeclanguage.action.ActionParser;
import org.webspeclanguage.action.ActionVisitor;
import org.webspeclanguage.action.ExpressionAction;
import org.webspeclanguage.action.LetVariable;
import org.webspeclanguage.expression.base.VariableValue;
import org.webspeclanguage.expression.typechecker.TypecheckingResult;
import org.webspeclanguage.expression.typechecker.WebSpecDiagramTypechecker;
import org.webspeclanguage.generator.Generator;
import org.webspeclanguage.widget.Widget;

/**
 * A diagram represents a set of scenarios that the
 * Web application must satisfy
 * 
 * @author Esteban Robles Luna
 */
public class WebSpecDiagram {

  private String name;
  private WebSpecInteraction startingInteraction;

  private int cyclesAllowed;

  private Set<WebSpecInteraction> interactions;
  private Map<String, Generator> generators;
  private List<Action> actionsSetup;

  public WebSpecDiagram(String name) {
    Validate.notNull(name);
    
    this.name = name;
    this.interactions = new HashSet<WebSpecInteraction>();
    this.generators = new HashMap<String, Generator>();
    this.cyclesAllowed = 0;
    this.actionsSetup = new ArrayList<Action>();
  }

  public void addActionSetup(Action action) {
    Validate.notNull(action);
    
    this.getActionsSetup().add(action);
  }

  public TypecheckingResult typecheck() {
    return new WebSpecDiagramTypechecker().typecheck(this);
  }

  public Map<WebSpecPath, Set<String>> getUndeclaredVariables() {
    Map<WebSpecPath, Set<String>> undeclaredVariables = new HashMap<WebSpecPath, Set<String>>();
    PathComputer computer = new PathComputer(this.getCyclesAllowed());
    List<WebSpecPath> paths = computer.computePathsFor(this);
    for (WebSpecPath path : paths) {
      Set<String> undeclaredVariablesInPath = this.getUndeclaredVariables(path);
      if (!undeclaredVariablesInPath.isEmpty()) {
        undeclaredVariables.put(path, undeclaredVariablesInPath);
      }
    }
    return undeclaredVariables;
  }

  @SuppressWarnings("unchecked")
  private Set<String> getUndeclaredVariables(WebSpecPath path) {
    final Set<String> declaredVariables = new HashSet<String>();
    Set<String> undeclaredVariables = new HashSet<String>();
    final Set references = new HashSet();

    for (Action action : this.getActionsSetup()) {
      analyzeUndeclaredOn(declaredVariables, references, action);
    }

    for (WebSpecPathItem item : path.getItems()) {
      references.clear();

      item.accept(new WebSpecPathItemVisitor() {
        public Object visitWebSpecInteraction(WebSpecInteraction interaction) {
          if (interaction.getInvariant() != null) {
            interaction.getInvariant().getInstancesOfOn(VariableValue.class,
                references);
          }
          return null;
        }

        public Object visitWebSpecNavigation(WebSpecNavigation navigation) {
          return analyze(navigation);
        }

        public Object visitWebSpecRichBehavior(WebSpecRichBehavior richBehavior) {
          return analyze(richBehavior);
        }

        private Object analyze(WebSpecTransition transition) {
          if (transition.getPrecondition() != null) {
            transition.getPrecondition().getInstancesOfOn(VariableValue.class,
                references);
          }
          for (Action action : transition.getActions()) {
            analyzeUndeclaredOn(declaredVariables, references, action);
          }
          return null;
        }
      });

      for (Iterator iterator = references.iterator(); iterator.hasNext();) {
        VariableValue variableValue = (VariableValue) iterator.next();
        if (!declaredVariables.contains(variableValue.getVariableName())) {
          undeclaredVariables.add(variableValue.getVariableName());
        }
      }
    }

    return undeclaredVariables;
  }

  @SuppressWarnings("unchecked")
  private void analyzeUndeclaredOn(final Set<String> declaredVariables,
      final Set references, Action action) {
    action.accept(new ActionVisitor() {
      public Object visitExpressionAction(ExpressionAction expressionAction) {
        expressionAction.getExpression().getInstancesOfOn(VariableValue.class,
            references);
        return null;
      }

      public Object visitLetVariable(LetVariable letVariable) {
        declaredVariables.add(letVariable.getVariableName());
        letVariable.getExpression().getInstancesOfOn(VariableValue.class,
            references);
        return null;
      }

    });
  }

  public void addInteraction(WebSpecInteraction anInteraction) {
    Validate.notNull(anInteraction);
    
    if (this.containsInteractionNamed(anInteraction.getName())) {
      throw new ExistingInteractionException(anInteraction.getName());
    } else {
      this.getInteractions().add(anInteraction);
    }
  }

  public void addGenerator(String name, Generator generator) {
    Validate.notNull(name);
    Validate.notNull(generator);
    
    if (this.getGenerators().containsKey(name)) {
      throw new DuplicatedGeneratorException(name);
    }
    this.getGenerators().put(name, generator);
  }

  public Set<String> getGeneratorsNames() {
    return this.getGenerators().keySet();
  }

  public Generator getGeneratorNamed(String name) {
    return this.getGenerators().get(name);
  }

  private boolean containsInteractionNamed(String name) {
    return this.getInteractionNamed(name) != null;
  }

  public WebSpecInteraction getInteractionNamed(String interactionName) {
    for (WebSpecInteraction interaction : this.getInteractions()) {
      if (interaction.getName().equals(interactionName)) {
        return interaction;
      }
    }
    return null;
  }

  public Widget getWidget(String interactionName, String widgetName) {
    for (WebSpecInteraction interaction : this.getInteractions()) {
      if (interaction.getName().equals(interactionName)) {
        return interaction.getWidget(widgetName);
      }
    }
    return null;
  }

  public WebSpecInteraction getStartingInteraction() {
    return startingInteraction;
  }

  public void setStartingInteraction(WebSpecInteraction startingInteraction) {
    if (startingInteraction.getLocation() == null) {
      throw new InvalidStartingInteractionException(startingInteraction);
    }
    this.startingInteraction = startingInteraction;
  }

  public String getName() {
    return name;
  }

  private Set<WebSpecInteraction> getInteractions() {
    return interactions;
  }

  public Map<String, Generator> getGenerators() {
    return generators;
  }

  public boolean areCyclesAllowed() {
    return this.getCyclesAllowed() > 0;
  }

  public int getCyclesAllowed() {
    return cyclesAllowed;
  }

  public void setCyclesAllowed(int cyclesAllowed) {
    this.cyclesAllowed = cyclesAllowed;
  }

  public List<Action> getActionsSetup() {
    return actionsSetup;
  }

  public void setActionsSetup(String actions) {
    this.actionsSetup = ActionParser.getActions(actions, this);
  }
}
