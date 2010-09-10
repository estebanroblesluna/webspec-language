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
import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.base.WebSpecInteraction;
import org.webspeclanguage.base.WebSpecNavigation;
import org.webspeclanguage.base.WebSpecPath;
import org.webspeclanguage.base.WebSpecPathItem;
import org.webspeclanguage.base.WebSpecPathItemVisitor;
import org.webspeclanguage.base.WebSpecRichBehavior;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.ExpressionType;

/**
 * A typechecker for a {@link WebSpecDiagram}
 * 
 * @author Esteban Robles Luna
 */
public class WebSpecDiagramTypechecker {

  private ExpressionTypechecker expressionTypechecker;

  private WebSpecDiagram currentDiagram;
  private TypecheckingResult result;

  public TypecheckingResult typecheck(WebSpecDiagram webSpecDiagram) {
    this.currentDiagram = webSpecDiagram;
    this.expressionTypechecker = new ExpressionTypechecker(this.currentDiagram);

    this.result = new TypecheckingResult();

    List<WebSpecPath> paths = PathComputer.computePaths(webSpecDiagram);
    for (WebSpecPath path : paths) {
      this.typecheck(path);
    }

    return this.result;
  }

  private void typecheck(WebSpecPath path) {
    // add setup actions
    this.typecheck(this.currentDiagram.getActionsSetup());

    // typecheck every item
    for (WebSpecPathItem item : path.getItems()) {
      item.accept(new WebSpecPathItemVisitor() {
        public Object visitWebSpecInteraction(WebSpecInteraction interaction) {
          typecheck(interaction.getTitle(), ExpressionType.STRING);
          typecheck(interaction.getInvariant(), ExpressionType.BOOLEAN);
          return null;
        }

        public Object visitWebSpecNavigation(WebSpecNavigation navigation) {
          typecheck(navigation.getPrecondition(), ExpressionType.BOOLEAN);
          typecheck(navigation.getActions());
          return null;
        }

        public Object visitWebSpecRichBehavior(WebSpecRichBehavior richBehavior) {
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
