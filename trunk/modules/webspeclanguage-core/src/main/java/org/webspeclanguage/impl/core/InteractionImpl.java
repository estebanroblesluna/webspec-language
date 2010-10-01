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
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.PathItemVisitor;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.api.TransitionTarget;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.utils.ExpressionUtils;
import org.webspeclanguage.impl.widget.Button;
import org.webspeclanguage.impl.widget.Container;
import org.webspeclanguage.impl.widget.Label;
import org.webspeclanguage.impl.widget.ListOfContainer;
import org.webspeclanguage.impl.widget.Panel;
import org.webspeclanguage.impl.widget.TextField;
import org.webspeclanguage.impl.widget.Widget;

/**
 * An interaction represents a point where the user can interact
 * with the application using its interface objects (widgets)
 * 
 * @author Esteban Robles Luna
 */
public class InteractionImpl implements Interaction {

  private String name;
  private List<Transition> forwardTransitions;
  private List<Transition> backwardTransitions;
  private String location;
  private Expression title;
  private String mockupFile;
  private Expression invariant;
  private Container root;
  private Diagram diagram;

  public InteractionImpl(String name) {
    Validate.notNull(name);

    this.name = name;
    this.forwardTransitions = new ArrayList<Transition>();
    this.backwardTransitions = new ArrayList<Transition>();
    this.root = new Panel();
  }

  public String toString() {
    return this.getName();
  }

  /**
   * {@inheritDoc}
   */
  public Object accept(PathItemVisitor pathItemVisitor) {
    return pathItemVisitor.visitInteraction(this);
  }

  public Navigation navigateTo(Interaction interaction) {
    return new NavigationImpl(this, interaction);
  }

  public RichBehavior richBehaviorTo(Interaction interaction) {
    return new RichBehaviorImpl(this, interaction);
  }
  
  public void addForwardTransition(Transition transition) {
    this.forwardTransitions.add(transition);
  }

  public void addBackwardTransition(Transition transition) {
    this.backwardTransitions.add(transition);
  }

  public void addWidget(Widget widget) {
    this.getRoot().addWidget(widget);
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Expression getTitle() {
    return title;
  }

  public void setTitle(Expression title) {
    this.title = title;
  }

  public void setTitle(String expressionString) {
    this.setTitle(ExpressionUtils.getExpression(expressionString, getDiagram()));
  }

  public Expression getInvariant() {
    return invariant;
  }

  public void setInvariant(Expression invariant) {
    this.invariant = invariant;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TextField createTextFieldWithLocation(String aLocation) {
    return this.getRoot().createTextFieldWithLocation(aLocation);
  }

  public Button createButtonWithLocation(String aLocation) {
    return this.getRoot().createButtonWithLocation(aLocation);
  }

  public TextField createTextFieldWithId(String anID) {
    return this.getRoot().createTextFieldWithId(anID);
  }

  public ListOfContainer createListOfContainer(String indexVariable) {
    return this.getRoot().createListOfContainer(indexVariable);
  }

  public Label createLabelWithLocation(String location) {
    return this.getRoot().createLabelWithLocation(location);
  }

  public Container getRoot() {
    return root;
  }

  public Widget getWidget(String widgetName) {
    return this.getRoot().getWidgetNamed(widgetName);
  }

  public <T extends Transition> List<T> getForwardTransitionsTo(TransitionTarget target, Class<T> theClass) {
    List<T> result = new ArrayList<T>();
    for (Transition transition : this.getForwardTransitions()) {
      if (theClass.isAssignableFrom(transition.getClass())
          && transition.getTarget().equals(target)) {
        result.add((T) transition);
      }
    }
    return result;
  }
  
  public void setInvariant(String expressionString) {
    Expression expression = ExpressionUtils.getExpression(expressionString, this.getDiagram());
    this.setInvariant(expression);
  }

  Diagram getDiagram() {
    return diagram;
  }

  public String getMockupFile() {
    return mockupFile;
  }

  public void setMockupFile(String mockupFile) {
    this.mockupFile = mockupFile;
  }

  public List<Transition> getForwardTransitions() {
    return Collections.unmodifiableList(this.forwardTransitions);
  }

  public void setDiagram(Diagram diagram) {
    this.diagram = diagram;
  }

  public List<Transition> getBackwardTransitions() {
    return Collections.unmodifiableList(this.backwardTransitions);
  }
}
