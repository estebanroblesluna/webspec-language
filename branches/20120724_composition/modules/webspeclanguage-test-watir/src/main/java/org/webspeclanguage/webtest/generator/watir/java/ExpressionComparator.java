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
package org.webspeclanguage.webtest.generator.watir.java;

/**
 * Expression comparator class, used to process the string received from elements.
 * It also implements the classes needed to generate the final locator and method called.
 * 
 * @author Gonzalo G. Testa
 *
 */
public class ExpressionComparator {

  String locator;
  
  String expr;

  ExpressionElement element;
  
  public ExpressionComparator(String expression) {
    expr = expression;
  }

  public String locators() {
    if (expr.contains("@")){
      element = new ExpressionElementSimple(expr);
    }
    else{
      element = new ExpressionElementXpath(expr);
    }
    
    return element.locator();
  }

  
  protected abstract class ExpressionElement {
    String what;
    String how;
    String locator;
    
    public ExpressionElement() {
      what = new String();
      how = new String();
      locator = new String();
    }

    abstract String locator();
    
    protected String returnExpression() {
      return what +  "(" + how + "\"" + locator + "\")" ;
    }
  }
  
  public class ExpressionElementSimple extends ExpressionElement {
    
    public ExpressionElementSimple(String expr) {
      super();
      
      what = expr.substring(2, expr.indexOf("["));

      if (what.equals("input")){
        what = "text_field";
      } 
    }

    @Override
    String locator() {
      
      how = expr.substring(expr.indexOf("@")+1, expr.indexOf("="));
      
      if (!expr.substring(expr.indexOf("]")+1).equals("")){
        how = "xpath";
      }
      how = ":" + how + ", ";

      if (!how.equals("xpath")){
        locator = "" + expr.substring(0, expr.indexOf("=")) + "='" + expr.substring(expr.indexOf("=")+1, expr.length()); //+ //"']";
      }
      else{
        locator = expr;
      }
      
      return this.returnExpression();
    }

    
  }
  
  public class ExpressionElementXpath extends ExpressionElement{
    
    public ExpressionElementXpath(String expr) {
      super();
      what = "element_by_xpath";
    }

    @Override
    String locator() {
      if (expr.contains("="))
        locator = "//*[@" + expr.substring(0, expr.indexOf("=")) + "='" + expr.substring(expr.indexOf("=")+1, expr.length()) + "']";
      else
        locator = expr;
      
      return this.returnExpression();
    }
  }
}