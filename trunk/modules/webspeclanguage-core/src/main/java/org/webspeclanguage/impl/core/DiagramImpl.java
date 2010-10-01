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
package org.webspeclanguage.impl.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.api.Action;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.api.Generator;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.Operation;
import org.webspeclanguage.api.PathItem;
import org.webspeclanguage.api.PathItemVisitor;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.impl.action.ActionParser;
import org.webspeclanguage.impl.action.ActionVisitor;
import org.webspeclanguage.impl.action.ExpressionAction;
import org.webspeclanguage.impl.action.LetVariable;
import org.webspeclanguage.impl.expression.core.VariableValue;
import org.webspeclanguage.impl.expression.typechecker.DiagramTypechecker;
import org.webspeclanguage.impl.expression.typechecker.TypecheckingResult;
import org.webspeclanguage.impl.widget.Widget;

/**
 * A diagram represents a set of scenarios that the
 * Web application must satisfy
 * 
 * @author Esteban Robles Luna
 */
public class DiagramImpl implements Diagram {

  private String name;
  private Interaction startingInteraction;

  private int cyclesAllowed;

  private Set<Interaction> interactions;
  private Map<String, Generator> generators;
  private List<Action> actionsSetup;

  public DiagramImpl(String name) {
    Validate.notNull(name);
    
    this.name = name;
    this.interactions = new HashSet<Interaction>();
    this.generators = new HashMap<String, Generator>();
    this.cyclesAllowed = 0;
    this.actionsSetup = new ArrayList<Action>();
  }

  public void addActionSetup(Action action) {
    Validate.notNull(action);
    
    this.getActionsSetup().add(action);
  }

  public TypecheckingResult typecheck() {
    return new DiagramTypechecker().typecheck(this);
  }

  public Map<Path, Set<String>> getUndeclaredVariables() {
    Map<Path, Set<String>> undeclaredVariables = new HashMap<Path, Set<String>>();
    PathComputer computer = new PathComputer(this.getCyclesAllowed());
    List<Path> paths = computer.computePathsFor(this);
    for (Path path : paths) {
      Set<String> undeclaredVariablesInPath = this.getUndeclaredVariables(path);
      if (!undeclaredVariablesInPath.isEmpty()) {
        undeclaredVariables.put(path, undeclaredVariablesInPath);
      }
    }
    return undeclaredVariables;
  }

  @SuppressWarnings("unchecked")
  private Set<String> getUndeclaredVariables(Path path) {
    final Set<String> declaredVariables = new HashSet<String>();
    Set<String> undeclaredVariables = new HashSet<String>();
    final Set references = new HashSet();

    for (Action action : this.getActionsSetup()) {
      analyzeUndeclaredOn(declaredVariables, references, action);
    }

    for (PathItem item : path.getItems()) {
      references.clear();

      item.accept(new UndeclareVariableAnalyzer(references, declaredVariables));

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

  public void addInteraction(Interaction anInteraction) {
    Validate.notNull(anInteraction);
    
    if (this.containsInteractionNamed(anInteraction.getName())) {
      throw new ExistingInteractionException(anInteraction.getName());
    } else {
      this.interactions.add(anInteraction);
      anInteraction.setDiagram(this);
    }
  }

  public void addGenerator(Generator generator) {
    Validate.notNull(generator);
    
    if (this.generators.containsKey(generator.getName())) {
      throw new DuplicatedGeneratorException(generator.getName());
    }
    this.generators.put(generator.getName(), generator);
  }

  public Generator getGeneratorNamed(String name) {
    return this.generators.get(name);
  }

  private boolean containsInteractionNamed(String name) {
    return this.getInteractionNamed(name) != null;
  }

  public Interaction getInteractionNamed(String interactionName) {
    for (Interaction interaction : this.interactions) {
      if (interaction.getName().equals(interactionName)) {
        return interaction;
      }
    }
    return null;
  }

  public Widget getWidget(String interactionName, String widgetName) {
    for (Interaction interaction : this.interactions) {
      if (interaction.getName().equals(interactionName)) {
        return interaction.getWidget(widgetName);
      }
    }
    return null;
  }

  public Interaction getStartingInteraction() {
    return startingInteraction;
  }

  public void setStartingInteraction(InteractionImpl startingInteraction) {
    if (startingInteraction.getLocation() == null) {
      throw new InvalidStartingInteractionException(startingInteraction);
    }
    this.startingInteraction = startingInteraction;
  }

  public String getName() {
    return name;
  }

  public Set<Interaction> getInteractions() {
    return Collections.unmodifiableSet(this.interactions);
  }

  public Collection<Generator> getGenerators() {
    return Collections.unmodifiableCollection(this.generators.values());
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
  
  private final class UndeclareVariableAnalyzer implements PathItemVisitor {

    private final Set references;
    private final Set<String> declaredVariables;

    private UndeclareVariableAnalyzer(Set references, Set<String> declaredVariables) {
      this.references = references;
      this.declaredVariables = declaredVariables;
    }
    
    public Object visitInteraction(Interaction interaction) {
      if (interaction.getInvariant() != null) {
        interaction.getInvariant().getInstancesOfOn(VariableValue.class, references);
      }
      return null;
    }
    
    public Object visitNavigation(Navigation navigation) {
      return analyze(navigation);
    }
    
    public Object visitRichBehavior(RichBehavior richBehavior) {
      return analyze(richBehavior);
    }
    
    private Object analyze(Transition transition) {
      if (transition.getPrecondition() != null) {
        transition.getPrecondition().getInstancesOfOn(VariableValue.class, references);
      }
      for (Action action : transition.getActions()) {
        analyzeUndeclaredOn(declaredVariables, references, action);
      }
      return null;
    }

    public Object visitOperation(Operation operation) {
      for (PathItem item : operation.getItems()) {
        item.accept(this);
      }
      return null;
    }
  }
}
