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
package org.webspeclanguage.expression.typechecker;

import java.util.List;

import org.webspeclanguage.action.Action;
import org.webspeclanguage.action.ActionVisitor;
import org.webspeclanguage.action.ExpressionAction;
import org.webspeclanguage.action.LetVariable;
import org.webspeclanguage.base.PathComputer;
import org.webspeclanguage.base.Diagram;
import org.webspeclanguage.base.Interaction;
import org.webspeclanguage.base.Navigation;
import org.webspeclanguage.base.Path;
import org.webspeclanguage.base.PathItem;
import org.webspeclanguage.base.PathItemVisitor;
import org.webspeclanguage.base.RichBehavior;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.ExpressionType;

/**
 * A typechecker for a {@link Diagram}
 * 
 * @author Esteban Robles Luna
 */
public class DiagramTypechecker {

  private ExpressionTypechecker expressionTypechecker;

  private Diagram currentDiagram;
  private TypecheckingResult result;

  public TypecheckingResult typecheck(Diagram webSpecDiagram) {
    this.currentDiagram = webSpecDiagram;
    this.expressionTypechecker = new ExpressionTypechecker(this.currentDiagram);

    this.result = new TypecheckingResult();

    List<Path> paths = PathComputer.computePaths(webSpecDiagram);
    for (Path path : paths) {
      this.typecheck(path);
    }

    return this.result;
  }

  private void typecheck(Path path) {
    // add setup actions
    this.typecheck(this.currentDiagram.getActionsSetup());

    // typecheck every item
    for (PathItem item : path.getItems()) {
      item.accept(new PathItemVisitor() {
        public Object visitInteraction(Interaction interaction) {
          typecheck(interaction.getTitle(), ExpressionType.STRING);
          typecheck(interaction.getInvariant(), ExpressionType.BOOLEAN);
          return null;
        }

        public Object visitNavigation(Navigation navigation) {
          typecheck(navigation.getPrecondition(), ExpressionType.BOOLEAN);
          typecheck(navigation.getActions());
          return null;
        }

        public Object visitRichBehavior(RichBehavior richBehavior) {
          typecheck(richBehavior.getPrecondition(), ExpressionType.BOOLEAN);
          typecheck(richBehavior.getActions());
          return null;
        }
      });
    }
  }

  private void typecheck(List<Action> actions) {
    for (Action action : actions) {
      action.accept(new ActionVisitor() {
        public Object visitExpressionAction(ExpressionAction expressionAction) {
          typecheck(expressionAction.getExpression());
          return null;
        }

        public Object visitLetVariable(LetVariable letVariable) {
          if (letVariable.getType() != null) {
            typecheck(letVariable.getExpression(), letVariable.getType());
            expressionTypechecker.set(letVariable.getVariableName(),
                letVariable.getType());
          } else {
            typecheck(letVariable.getExpression(), expressionTypechecker
                .getType(letVariable.getVariableName()));
          }
          return null;
        }
      });
    }
  }

  private void typecheck(Expression expression, ExpressionType expectedType) {
    if (expression != null) {
      try {
        ExpressionType actualType = this.expressionTypechecker
            .typecheck(expression);
        if (!actualType.equals(expectedType)) {
          this.result.addError(new TypecheckException(expression, actualType,
              expectedType));
        }
      } catch (Exception e) {
        this.addError(e);
      }
    }
  }

  private ExpressionType typecheck(Expression expression) {
    try {
      if (expression != null) {
        return this.expressionTypechecker.typecheck(expression);
      } else {
        return ExpressionType.UNKNOWN;
      }
    } catch (Exception e) {
      this.addError(e);
      return ExpressionType.UNKNOWN;
    }
  }

  private void addError(Exception e) {
    this.result.addError(e);
  }
}
