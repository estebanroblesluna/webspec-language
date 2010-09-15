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
package org.webspeclanguage.expression.conjunctivenormalform;

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.expression.base.AndExpression;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.ImpliesExpression;
import org.webspeclanguage.expression.base.NotExpression;
import org.webspeclanguage.expression.base.OrExpression;

/**
 * A convertor to transform an {@link Expression} into a CNF-form
 * 
 * @author Esteban Robles Luna
 */
public class ExpressionConvertorToConjunctiveNormalForm {

  public Expression convert(Expression expression) {
    Expression withoutImplies = this.removeImplies(expression);
    Expression notInwards = this.moveNotInwards(withoutImplies);
    return this.distributeAndOverOrs(notInwards);
  }
  
  public List<Expression> convertAndObtainDisjunctions(Expression expression) {
    Expression cnfExpression = this.convert(expression);
    List<Expression> disjunctions = new ArrayList<Expression>();
    this.addDisjunctionsTo(cnfExpression, disjunctions);
    return disjunctions;
  }

  private void addDisjunctionsTo(Expression expression, List<Expression> disjunctions) {
    if (expression instanceof AndExpression) {
      AndExpression andExpression = (AndExpression) expression;
      this.addDisjunctionsTo(andExpression.getOp1(), disjunctions);
      this.addDisjunctionsTo(andExpression.getOp2(), disjunctions);
    } else {
      disjunctions.add(expression);
    }
  }

  private Expression distributeAndOverOrs(Expression expression) {
    return (new AndOverOrsDistributor()).clone(expression);
  }

  private Expression moveNotInwards(Expression expression) {
    return (new ClonerVisitor() {
      @Override
      public Object visitNotExpression(NotExpression notExpression) {
        if (notExpression.getExpression() instanceof AndExpression) {
          AndExpression innerExpression = (AndExpression) notExpression.getExpression();
          return new OrExpression(
              new NotExpression(this.clone(innerExpression.getOp1())),
              new NotExpression(this.clone(innerExpression.getOp2()))
          );
        } else if (notExpression.getExpression() instanceof OrExpression) {
          OrExpression innerExpression = (OrExpression) notExpression.getExpression();
          return new AndExpression(
              new NotExpression(this.clone(innerExpression.getOp1())),
              new NotExpression(this.clone(innerExpression.getOp2()))
          );
        } else {
          return super.visitNotExpression(notExpression);
        }
      }
    }).clone(expression);
  }

  private Expression removeImplies(Expression expression) {
    return (new ClonerVisitor() {
      @Override
      public Object visitImpliesExpression(ImpliesExpression impliesExpression) {
        return new OrExpression(
            new NotExpression(this.clone(impliesExpression.getOp1())),
            this.clone(impliesExpression.getOp2())
        );
      }
    }).clone(expression);
  }
  
  private final class AndOverOrsDistributor extends ClonerVisitor {

    @Override
    public Object visitOrExpression(OrExpression expression) {
      if (expression.getOp1() instanceof AndExpression) {
        AndExpression innerExpression = (AndExpression) expression.getOp1();
        return new AndExpression(
            new OrExpression(
                this.clone(innerExpression.getOp1()),
                this.clone(expression.getOp2())
            ),
            new OrExpression(
                this.clone(innerExpression.getOp2()),
                this.clone(expression.getOp2())
            )
        );
      } else if (expression.getOp2() instanceof AndExpression) {
        AndExpression innerExpression = (AndExpression) expression.getOp2();
        return new AndExpression(
            new OrExpression(
                this.clone(expression.getOp1()),
                this.clone(innerExpression.getOp1())
            ),
            new OrExpression(
                this.clone(expression.getOp1()),
                this.clone(innerExpression.getOp2())
            )
        );
      } else {
        return super.visitOrExpression(expression);
      }
    }
  }
}
