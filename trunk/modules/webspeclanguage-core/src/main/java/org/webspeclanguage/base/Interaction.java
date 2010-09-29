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
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.utils.ExpressionUtils;
import org.webspeclanguage.widget.Button;
import org.webspeclanguage.widget.Container;
import org.webspeclanguage.widget.Label;
import org.webspeclanguage.widget.ListOfContainer;
import org.webspeclanguage.widget.Panel;
import org.webspeclanguage.widget.TextField;
import org.webspeclanguage.widget.Widget;

/**
 * An interaction represents a point where the user can interact
 * with the application using its interface objects (widgets)
 * 
 * @author Esteban Robles Luna
 */
public class Interaction implements PathItem {

  private List<Transition> forwardTransitions;
  private String location;
  private Expression title;
  private String name;
  private String mockupFile;
  private Expression invariant;
  private Container root;
  private Diagram diagram;

  public Interaction(String name) {
    Validate.notNull(name);

    this.forwardTransitions = new ArrayList<Transition>();
    this.name = name;
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
    return new Navigation(this, interaction);
  }

  public RichBehavior richBehaviorTo(Interaction interaction) {
    return new RichBehavior(this, interaction);
  }
  
  public void addTransition(Transition webSpecTransition) {
    this.forwardTransitions.add(webSpecTransition);
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

  public List<Navigation> navigationsTo(Interaction currentInteraction) {
    List<Navigation> navigations = new ArrayList<Navigation>();
    for (Transition transition : this.getForwardTransitions()) {
      if (transition instanceof Navigation
          && transition.getTo().equals(currentInteraction)) {
        navigations.add((Navigation) transition);
      }
    }
    return navigations;
  }
  
  public List<RichBehavior> richBehaviorsTo(Interaction currentInteraction) {
    List<RichBehavior> navigations = new ArrayList<RichBehavior>();
    for (Transition transition : this.getForwardTransitions()) {
      if (transition instanceof RichBehavior
          && transition.getTo().equals(currentInteraction)) {
        navigations.add((RichBehavior) transition);
      }
    }
    return navigations;
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

  void setDiagram(Diagram diagram) {
    this.diagram = diagram;
  }
}
