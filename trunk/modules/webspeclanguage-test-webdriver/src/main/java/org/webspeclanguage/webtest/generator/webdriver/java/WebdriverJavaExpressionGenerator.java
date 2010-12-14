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
package org.webspeclanguage.webtest.generator.webdriver.java;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.webspeclanguage.impl.exception.WebspecException;
import org.webspeclanguage.impl.expression.core.AddExpression;
import org.webspeclanguage.impl.expression.core.AndExpression;
import org.webspeclanguage.impl.expression.core.ArrayAccessExpression;
import org.webspeclanguage.impl.expression.core.ArrayExpression;
import org.webspeclanguage.impl.expression.core.BinaryExpression;
import org.webspeclanguage.impl.expression.core.BooleanConstant;
import org.webspeclanguage.impl.expression.core.ConcatExpression;
import org.webspeclanguage.impl.expression.core.DivExpression;
import org.webspeclanguage.impl.expression.core.EqualsExpression;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.core.ExpressionVisitor;
import org.webspeclanguage.impl.expression.core.FunctionCallExpression;
import org.webspeclanguage.impl.expression.core.GeneratorExpression;
import org.webspeclanguage.impl.expression.core.GreaterEqualExpression;
import org.webspeclanguage.impl.expression.core.GreaterExpression;
import org.webspeclanguage.impl.expression.core.ImpliesExpression;
import org.webspeclanguage.impl.expression.core.InteractionPropertyExpression;
import org.webspeclanguage.impl.expression.core.LowerEqualExpression;
import org.webspeclanguage.impl.expression.core.LowerExpression;
import org.webspeclanguage.impl.expression.core.MulExpression;
import org.webspeclanguage.impl.expression.core.NativeFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.NotEqualsExpression;
import org.webspeclanguage.impl.expression.core.NotExpression;
import org.webspeclanguage.impl.expression.core.NumberConstant;
import org.webspeclanguage.impl.expression.core.OrExpression;
import org.webspeclanguage.impl.expression.core.StringConstant;
import org.webspeclanguage.impl.expression.core.SubExpression;
import org.webspeclanguage.impl.expression.core.ToBooleanFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.ToNumberFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.ToStringFunctionCallExpression;
import org.webspeclanguage.impl.expression.core.VariableValue;
import org.webspeclanguage.impl.expression.core.WidgetPropertyReference;
import org.webspeclanguage.impl.expression.core.WidgetReference;
import org.webspeclanguage.webtest.generator.webdriver.*;

/**
 * An {@link Expression} writer
 * 
 * @author Gonzalo Testa
 */
public class WebdriverJavaExpressionGenerator {

  private static final String COMPARE_TO = "compareTo";
  private Set<String> specialFunctionCalls;

  public WebdriverJavaExpressionGenerator() {
    this.setSpecialFunctionCalls(new HashSet<String>());
    this.configureSpecialFunctionCalls();
  }

  private void configureSpecialFunctionCalls() {
    this.getSpecialFunctionCalls().add("isTextPresent");
    this.getSpecialFunctionCalls().add("click");
//    this.getSpecialFunctionCalls().add("typeKeys");
    this.getSpecialFunctionCalls().add("type");
//    this.getSpecialFunctionCalls().add("select");
//    this.getSpecialFunctionCalls().add("open");
  }

  public String generate(Expression expression) {
    return (String) expression.accept(new ExpressionGenerator());
  }

  private Set<String> getSpecialFunctionCalls() {
    return specialFunctionCalls;
  }

  private void setSpecialFunctionCalls(Set<String> specialFunctionCalls) {
    this.specialFunctionCalls = specialFunctionCalls;
  }
  
  private final class ExpressionGenerator implements ExpressionVisitor {

    private String visitBinaryExpression(BinaryExpression binaryExpression, String op) {
      String op1 = generate(binaryExpression.getOp1());
      String op2 = generate(binaryExpression.getOp2());

      op1 = binaryExpression.getOp1().isConstant() ? op1 : "(" + op1 + ")";
      op2 = binaryExpression.getOp2().isConstant() ? op2 : "(" + op2 + ")";

      return op1 + " " + op + " " + op2;
    }
    
    private String visitMessageSend(BinaryExpression binaryExpression, String op) {
      String op1 = generate(binaryExpression.getOp1());
      String op2 = generate(binaryExpression.getOp2());

      return op1 + "." + op + "(" + op2 + ")";
    }
    
    private String visitNumberMessageSend(BinaryExpression binaryExpression, String op) {
      String op1 = generate(binaryExpression.getOp1());
      String op2 = generate(binaryExpression.getOp2());

      return op1 + "." + op + "(" + op2 + ")";
    }
    
    public Object visitAddExpression(AddExpression expression) {
      return this.visitNumberMessageSend(expression, "add");
    }
    
    public Object visitAndExpression(AndExpression and) {
      return this.visitBinaryExpression(and, "&&");
    }
    
    public Object visitConcatExpression(ConcatExpression concat) {
      return this.visitBinaryExpression(concat, "+");
    }
    
    public Object visitBooleanConstant(BooleanConstant booleanConstant) {
      return booleanConstant.getConstant().toString();
    }
    
    public Object visitDivExpression(DivExpression expression) {
      return this.visitNumberMessageSend(expression, "divide");
    }
    
    public Object visitEqualsExpression(EqualsExpression equalsExpression) {
      return this.visitMessageSend(equalsExpression, "equals");
    }
    
    public Object visitGreaterEqualExpression(GreaterEqualExpression expression) {
      return this.visitMessageSend(expression, COMPARE_TO) + " >= 0";
    }
    
    public Object visitGreaterExpression(GreaterExpression expression) {
      return this.visitMessageSend(expression, COMPARE_TO) + " > 0";
    }
    
    public Object visitLowerEqualExpression(LowerEqualExpression expression) {
      return this.visitMessageSend(expression, COMPARE_TO) + " <= 0";
    }
    
    public Object visitLowerExpression(LowerExpression expression) {
      return this.visitMessageSend(expression, COMPARE_TO) + " < 0";
    }
    
    public Object visitMulExpression(MulExpression expression) {
      return this.visitNumberMessageSend(expression, "multiply");
    }
    
    public Object visitNotEqualsExpression(NotEqualsExpression expression) {
      return "!(" + this.visitMessageSend(expression, "equals") + ")";
    }
    
    public Object visitNotExpression(NotExpression notExpression) {
      String op = generate(notExpression.getExpression());
      
      op = notExpression.getExpression().isConstant() ? op : "(" + op + ")";
      
      return "!" + op;
    }
    
    public Object visitNumberConstant(NumberConstant integerConstant) {
      return 
        "new BigDecimal(\"" 
          + integerConstant.getConstant().toString()
          + "\")";
    }
    
    public Object visitOrExpression(OrExpression expression) {
      return this.visitBinaryExpression(expression, "||");
    }
    
    public Object visitStringConstant(StringConstant stringConstant) {
      return "\"" + stringConstant.getConstant() + "\"";
    }
    
    public Object visitSubExpression(SubExpression expression) {
      return this.visitNumberMessageSend(expression, "subtract");
    }
    
    public Object visitVariableValue(VariableValue variableValue) {
      return variableValue.getVariableName();
    }
    
    private String convertArguments(String expr){

  	  String how, locator;

  	  if (expr.contains("@")){

  		  how = expr.substring(expr.indexOf("@")+1, expr.indexOf("="));
  		  if (!expr.substring(expr.indexOf("]")+1).equals("")){
  			  how = "xpath";
  		  }

  		  if (!how.equals("xpath")){
  			  locator = expr.substring(expr.indexOf("=")+2, expr.indexOf("]")-1);
  		  }
  		  else{
  			  locator = expr;
  		  }

  	  }
  	  else{
  		  
  		  how = "";
  		  
  		  if (expr.contains("//")) //(expr.contains("=") || 
  		  	how = "xpath";
  		  else{
  			  if (expr.contains("=")) {
  				  how = expr.substring(0, expr.indexOf("="));
  				  
  			  }
  		  }	  
  		  
  		  if (how.equals("xpath") && expr.contains("=")){
  			  locator = "//*[@" + expr.substring(0, expr.indexOf("=")) + "='" + expr.substring(expr.indexOf("=")+1, expr.length()) + "']";
  		  }
  		  else
  			  if (how.isEmpty())
  				  locator = expr;
  			  else
  				  locator = expr.substring(expr.indexOf("=")+1);
  	  }
  	  if (how.isEmpty())
  		  return locator;
  	  else
  		  return "By." + how + "(\"" + locator + "\")";
  		
    }

    
    public Object visitWidgetPropertyReference(WidgetPropertyReference widgetPropertyReference) {
      if (widgetPropertyReference.getPropertyName().equals("text")) {
        return "driver.findElement(" + this.convertArguments(widgetPropertyReference.getPreferedLocation()) + ").getText()"; 
      } else {
        return "driver.findElement("
          + this.convertArguments(widgetPropertyReference.getPreferedLocation()) + ").getText()";
      }
    }
    
    public Object visitWidgetReference(WidgetReference widgetReference) {
      return "driver.findElement("
          + this.convertArguments(widgetReference.getPreferedLocation()) + ").getText()";
    }
    
    public Object visitGeneratorExpression(GeneratorExpression generatorExpression) {
      throw new WebspecException(generatorExpression.getGeneratorName() + " should be concrete");
    }
    
    private String getArguments(List<Expression> expressions) {
      String arguments = "";
      for (Expression exp : expressions) {
        String expS = null;
        if (exp instanceof WidgetReference) {
          expS = "" + this.convertArguments(((WidgetReference) exp).getPreferedLocation()) + "";
        } else if (exp instanceof WidgetPropertyReference) {
        	expS = this.convertArguments(((WidgetPropertyReference) exp).getPreferedLocation());
//          expS = "\"" + ((WidgetPropertyReference) exp).getPreferedLocation()
//              + "@" + ((WidgetPropertyReference) exp).getPropertyName()
//              + "\"";
        } else {
          expS = generate(exp);
        }
        arguments = arguments + expS + ", ";
      }
      arguments = arguments.substring(0, arguments.length() - 2);
      return arguments;
    }
    
    public Object visitFunctionCallExpression(FunctionCallExpression functionCallExpression) {
      
      String arguments = getArguments(functionCallExpression.getArguments());

      String function = functionCallExpression.getFunctionName();
      
      if (getSpecialFunctionCalls().contains(
          functionCallExpression.getFunctionName())) {
    	  
    	if (arguments.contains("By")){
    		if (function.equals("type"))
    			return "driver.findElement(" 
    					+ arguments.substring(0, arguments.lastIndexOf(")")+1)
    					+ ").sendKeys(" + arguments.substring(arguments.lastIndexOf(")")+3)
    					+ ")";
    		else
    			return "driver.findElement(" + arguments + ")." + function + "()";
    	}
    	else{
    		if (!arguments.contains("null")){
	    		if (function.contains("isTextPresent"))
	    			return "driver.findElement(By.xpath(\"//Body\")).getText().contains(" + arguments + ")";
	    		else
	    			return "Pending definition of" + arguments + "for function: " + function;
    		}
    		return "";
    	}
    	  
    	  
        
        
        
      } else {
        return "selenium.execute(\""
            + functionCallExpression.getFunctionName() + "\", " + arguments
            + ")";
      }
    }
    
    public Object visitNativeFunctionCallExpression(
        NativeFunctionCallExpression functionCallExpression) {
      
      String arguments = getArguments(functionCallExpression.getArguments());

      return "this." + functionCallExpression.getFunctionName() + "("
          + arguments + ")";
    }
    
    public Object visitToBooleanFunctionCallExpression(
        ToBooleanFunctionCallExpression functionCallExpression) {
      return "Boolean.valueOf("
        + "(" 
        + getArguments(functionCallExpression.getArguments()) 
        + ").toString())";
    }
    
    public Object visitToNumberFunctionCallExpression(ToNumberFunctionCallExpression functionCallExpression) {
      return "new BigDecimal(" + getArguments(functionCallExpression.getArguments()) + ")";
    }
    
    public Object visitToStringFunctionCallExpression(ToStringFunctionCallExpression functionCallExpression) {
      return "(" + getArguments(functionCallExpression.getArguments()) + ").toString()";
    }
    
    public Object visitImpliesExpression(ImpliesExpression impliesExpression) {
      return this.visitOrExpression(impliesExpression.getEquivalentOrExpression());
    }
    
    public Object visitArrayExpression(ArrayExpression arrayExpression) {
      throw new UnresolvedExpressionException(arrayExpression);
    }
    
    public Object visitArrayAccessExpression(ArrayAccessExpression arrayAccessExpression) {
      throw new UnresolvedExpressionException(arrayAccessExpression);
    }
    
    public Object visitInteractionPropertyExpression(InteractionPropertyExpression interactionPropertyExpression) {
      // TODO Auto-generated method stub
      return null;
    }
  }
}
