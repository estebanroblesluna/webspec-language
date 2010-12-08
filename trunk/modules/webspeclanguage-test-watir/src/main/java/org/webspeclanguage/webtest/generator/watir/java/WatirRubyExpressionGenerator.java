package org.webspeclanguage.webtest.generator.watir.java;

import java.nio.channels.UnresolvedAddressException;
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

public class WatirRubyExpressionGenerator {

  private Set<String> specialFunctionCalls;
  
  private static final String BROWSER_OBJ = "$browser.";
  
  public WatirRubyExpressionGenerator() {
    this.setSpecialFunctionCalls(new HashSet<String>());
    this.configureSpecialFunctionCalls();
  }

  private void configureSpecialFunctionCalls() {
    this.getSpecialFunctionCalls().add("isTextPresent");
    this.getSpecialFunctionCalls().add("click");
    this.getSpecialFunctionCalls().add("typeKeys");
    this.getSpecialFunctionCalls().add("type");
    this.getSpecialFunctionCalls().add("select");
    this.getSpecialFunctionCalls().add("open");
  }

  public String generate(Expression expression) {
    ExpressionVisitor elem = new ExpressionGenerator();
    return (String) expression.accept(elem);
  }
  
  private void setSpecialFunctionCalls(Set<String> specialFunctionCalls) {
    this.specialFunctionCalls = specialFunctionCalls;
  }
  private Set<String> getSpecialFunctionCalls() {
    return specialFunctionCalls;
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
      
      //return op1 + "." + op + "(" + op2 + ")";
      return op1 + op + op2;
    }
    
    public Object visitAddExpression(AddExpression expression) {
      return this.visitNumberMessageSend(expression, " + ");
    }
    
    public Object visitAndExpression(AndExpression and) {
      return this.visitBinaryExpression(and, "and");
    }
        
    public Object visitConcatExpression(ConcatExpression concat) {
      return this.visitBinaryExpression(concat, "+");
    }
    
    public Object visitBooleanConstant(BooleanConstant booleanConstant) {
      return booleanConstant.getConstant().toString();
    }
    
    public Object visitDivExpression(DivExpression expression) {
      return this.visitNumberMessageSend(expression, " / ");
    }
    
    public Object visitEqualsExpression(EqualsExpression equalsExpression) {
      return this.visitMessageSend(equalsExpression, "eql?");
    }
    
    public Object visitGreaterEqualExpression(GreaterEqualExpression expression) {
      return this.visitMessageSend(expression," >= ");
    }
    
    public Object visitGreaterExpression(GreaterExpression expression) {
      return this.visitMessageSend(expression, " > ");
    }
    
    public Object visitLowerEqualExpression(LowerEqualExpression expression) {
      return this.visitMessageSend(expression, " <= ");
    }
    
    public Object visitLowerExpression(LowerExpression expression) {
      return this.visitMessageSend(expression, " < ");
    }
    
    public Object visitMulExpression(MulExpression expression) {
      return this.visitNumberMessageSend(expression, " * ");
    }
    
    public Object visitNotEqualsExpression(NotEqualsExpression expression) {
      return "" + this.visitMessageSend(expression, " != ") + "";
    }
    
    public Object visitNotExpression(NotExpression notExpression) {
      String op = generate(notExpression.getExpression());
      
      op = notExpression.getExpression().isConstant() ? op : "(" + op + ")";
      
      return "!" + op;
    }
    
    public Object visitNumberConstant(NumberConstant integerConstant) {
      return 
        "" 
          + integerConstant.getConstant().toString();
    }
    
    public Object visitOrExpression(OrExpression expression) {
      return this.visitBinaryExpression(expression, "or");
    }
    
    public Object visitStringConstant(StringConstant stringConstant) {
      return "\"" + stringConstant.getConstant() + "\"";
    }
    
    public Object visitSubExpression(SubExpression expression) {
      return this.visitNumberMessageSend(expression, " - ");
    }
    
    public Object visitVariableValue(VariableValue variableValue) {
      return variableValue.getVariableName();
    }
    
    public Object visitWidgetPropertyReference(WidgetPropertyReference widgetPropertyReference) {
    	return BROWSER_OBJ + this.convertArguments(widgetPropertyReference.getPreferedLocation()) + ".text";
    	
//      return "$browser.element_by_xpath(" + "\""
//             + widgetPropertyReference.getPreferedLocation() + "\"" + ")" 
//          //+ widgetPropertyReference.getPreferedLocation() + "@"
//          //+ widgetPropertyReference.getPropertyName() + "\"" + ").text"
//             + "." + widgetPropertyReference.getPropertyName();
    }
    
    public Object visitWidgetReference(WidgetReference widgetReference) {
		return BROWSER_OBJ + this.convertArguments(widgetReference.getPreferedLocation()) + ".text";
    	//      return "$browser.element_by_xpath("+"\"" 
//             + widgetReference.getPreferedLocation() + "\"" + ").text";
    }
    
    public Object visitGeneratorExpression(GeneratorExpression generatorExpression) {
      throw new WebspecException(generatorExpression.getGeneratorName() + " should be concrete");
    }
    
    private String convertArguments(String expr){
      
      String what, how, locator;
      
      if (expr.contains("@")){
        what = expr.substring(2, expr.indexOf("["));
        
        if (what.equals("input")){
          what = "text_field";
        }  
        
        how = expr.substring(expr.indexOf("@")+1, expr.indexOf("="));
        if (!expr.substring(expr.indexOf("]")+1).equals("")){
          how = "xpath";
        }
        how = ":" + how + ", ";
        
        if (!how.equals("xpath")){
          locator = expr.substring(expr.indexOf("=")+2, expr.indexOf("]")-1);
        }
        else{
          locator = expr;
        }
      
      }
      else{
        what = "element_by_xpath"; 
        how = "";
        if (expr.contains("="))
        	locator = "//*[@" + expr.substring(0, expr.indexOf("=")) + "='" + expr.substring(expr.indexOf("=")+1, expr.length()) + "']";
        else
        	locator = expr;
      }
        
      return what +  "(" + how + "\"" + locator + "\")" ;
    }
    
    private String getArguments(List<Expression> expressions) {
      String arguments = "";
      for (Expression exp : expressions) {
        String expS = null;
        if (exp instanceof WidgetReference) {
          
          String expr = ((WidgetReference) exp).getPreferedLocation();
          expS =  this.convertArguments(expr);
         
        } else if (exp instanceof WidgetPropertyReference) {
//          String expr = this.convertArguments(expr);
        	
          String expr  = "\"" + ((WidgetPropertyReference) exp).getPreferedLocation()
        + "@" + ((WidgetPropertyReference) exp).getPropertyName()
        + "\"";
          expS = expr;
          
        }
        else {
          expS = generate(exp);
        }
        
        arguments = arguments + expS;
      }
      //arguments = arguments.substring(0, arguments.length() - 2);
      return arguments;
    }
    
    public Object visitFunctionCallExpression(FunctionCallExpression functionCallExpression) {
      
      String str = "";
        
      String arguments = getArguments(functionCallExpression.getArguments());
      if (!arguments.equals("null")){
        // get the function to generate the Watir statements
        String function = functionCallExpression.getFunctionName().toString();
        
        // generate starting string for statement
        str = BROWSER_OBJ + "";
        
        if (function.equals("type")){
          // generates the Watir statement for set
          //str = str + arguments.substring(0, arguments.lastIndexOf(")")+1) + ".set " + arguments.substring(arguments.indexOf(")")+1);
          str = str + arguments.substring(0,arguments.indexOf(")")+1) + ".set " + arguments.substring(arguments.indexOf(")")+1);
        }
        else{
          
         if (function.equals("click")){
           // generates the Watir statement for clic
           str = str + arguments + ".click";//arguments.substring(0, arguments.indexOf("#")) + ".click";
         }
         else{
           if (function.equals("isTextPresent")){
             str = str + "contains_text " + arguments;
           }
         }
        } 
      }
       
//      String arguments = getArguments(functionCallExpression.getArguments());

      //      if (getSpecialFunctionCalls().contains(
//          functionCallExpression.getFunctionName())) {
//        return "selenium." + functionCallExpression.getFunctionName() + "("
//            + arguments + ")";
//      } else {
//        return "selenium.execute(\""
//            + functionCallExpression.getFunctionName() + "\", " + arguments
//            + ")";
//      }
      return str;
    }
    
    public Object visitNativeFunctionCallExpression(
        NativeFunctionCallExpression functionCallExpression) {
      
      String arguments = getArguments(functionCallExpression.getArguments());

      return "self." + functionCallExpression.getFunctionName() + "("
          + arguments + ")";
    }
    
    public Object visitToBooleanFunctionCallExpression(
        ToBooleanFunctionCallExpression functionCallExpression) {
      return ""
        + "" 
        + getArguments(functionCallExpression.getArguments()) 
        + ".to_s";
    }
    
    public Object visitToNumberFunctionCallExpression(ToNumberFunctionCallExpression functionCallExpression) {
      return "" + getArguments(functionCallExpression.getArguments()) + ".to_i";
    }
    
    public Object visitToStringFunctionCallExpression(ToStringFunctionCallExpression functionCallExpression) {
      return "" + getArguments(functionCallExpression.getArguments()) + ".to_s";
    }
    
    public Object visitImpliesExpression(ImpliesExpression impliesExpression) {
      return this.visitOrExpression(impliesExpression.getEquivalentOrExpression());
    }
    
    public Object visitArrayExpression(ArrayExpression arrayExpression) {
      throw new UnresolvedAddressException();
    }
    
    public Object visitArrayAccessExpression(ArrayAccessExpression arrayAccessExpression) {
      throw new UnresolvedAddressException();
    }
    
    public Object visitInteractionPropertyExpression(InteractionPropertyExpression interactionPropertyExpression) {
      // TODO Auto-generated method stub
      String element = interactionPropertyExpression.getInteraction().getRoot().getPreferedLocation();      
      return "element_by_xpath(\"//*[@" + element.substring(0, element.indexOf("=")) +   "='" + interactionPropertyExpression.getProperty() +"']\")";
    }
  }
  
}
