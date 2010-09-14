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
package org.webspeclanguage.webtest.generator.selenium.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.webspeclanguage.expression.base.ConstantExpression;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.ExpressionType;
import org.webspeclanguage.expression.typechecker.ExpressionTypechecker;
import org.webspeclanguage.webtest.action.WebCreateVariableFromExpression;
import org.webspeclanguage.webtest.action.WebExpression;
import org.webspeclanguage.webtest.action.WebOpenUrl;
import org.webspeclanguage.webtest.action.WebWaitPageToLoad;
import org.webspeclanguage.webtest.assertion.WebAssertExpression;
import org.webspeclanguage.webtest.assertion.WebAssertTitle;
import org.webspeclanguage.webtest.base.SimpleWebTest;
import org.webspeclanguage.webtest.base.WebTestGenerator;
import org.webspeclanguage.webtest.base.WebTest;
import org.webspeclanguage.webtest.base.WebTestItem;
import org.webspeclanguage.webtest.base.WebTestItemVisitor;
import org.webspeclanguage.webtest.base.WebTestSuite;
import org.webspeclanguage.webtest.base.WebTestVisitor;

/**
 * A {@link WebTest} writer
 * 
 * @author Esteban Robles Luna
 */
@SuppressWarnings("unchecked")
public class SeleniumJavaWebTestGenerator implements WebTestGenerator {

  private ClassBuilder classBuilder;
  private String packageName;
  private SeleniumJavaExpressionGenerator expressionGenerator;

  private Map<ExpressionType, String> expressionTypeToJavaMapping;
  private SimpleWebTest simpleWebTest;
  private NavigationStopGenerationPolicy stopPolicy;
  private String baseClass;
  private ExpressionTypechecker typechecker;
  
  private Map<String, ExpressionType> alreadyDefinedVariables;

  public SeleniumJavaWebTestGenerator() {
    this.alreadyDefinedVariables = new HashMap<String, ExpressionType>();
    this.setClassBuilder(new ClassBuilder());
    this.setTypechecker(new ExpressionTypechecker(null));
    this.setPackageName("");
    this.setBaseClass("com.thoughtworks.selenium.SeleneseTestCase");
    this.setExpressionGenerator(new SeleniumJavaExpressionGenerator(this));

    this.setExpressionTypeToJavaMapping(new HashMap<ExpressionType, String>());

    this.configureExpressionTypeMapping();
    this.setStopPolicy(new ThreadSleepGenerationPolicy());
  }

  private void configureExpressionTypeMapping() {
    this.getExpressionTypeToJavaMapping().put(ExpressionType.STRING, "String");
    this.getExpressionTypeToJavaMapping().put(ExpressionType.NUMBER, "BigDecimal");
    this.getExpressionTypeToJavaMapping().put(ExpressionType.BOOLEAN, "boolean");
  }

  /**
   * {@inheritDoc}}
   */
  public String generateTest(WebTest webTest) {
    return this.getClassCode(webTest);
  }

  public void generateTestFor(WebTest aWebTest, String filename) throws IOException {
    String classCode = this.getClassCode(aWebTest);
    FileWriter fstream = new FileWriter(filename);
    BufferedWriter out = new BufferedWriter(fstream);
    out.write(classCode);
    out.close();
  }

  public String getClassCode(WebTest aWebTest) {
    this.alreadyDefinedVariables.clear();
    
    aWebTest.accept(new WebTestVisitor() {
      public Object visitSimpleWebTest(SimpleWebTest simpleWebTest) {
        generateSimpleWebTest(simpleWebTest);
        return null;
      }

      public Object visitWebTestSuite(WebTestSuite webTestSuite) {
        generateWebTestSuite(webTestSuite);
        return null;
      }
    });
    return this.getClassBuilder().getClassCode();
  }

  public void generateSimpleWebTest(SimpleWebTest simpleWebTest) {
    this.setSimpleWebTest(simpleWebTest);
    this.startClassFor(simpleWebTest);
    this.startMethodFor(simpleWebTest);
    this.computeMethodFor(simpleWebTest);
    this.endMethod();
    this.endClass();
    this.setSimpleWebTest(null);
  }

  public void generateWebTestSuite(WebTestSuite webTestSuite) {
    this.startClassFor(webTestSuite);
    for (WebTest webTest : webTestSuite) {
      this.basicGenerateTest(webTest);
    }
    this.endClass();
  }

  private void basicGenerateTest(WebTest webTest) {
    webTest.accept(new WebTestVisitor() {
      public Object visitSimpleWebTest(SimpleWebTest simpleWebTest) {
        setSimpleWebTest(simpleWebTest);
        startMethodFor(simpleWebTest);
        computeMethodFor(simpleWebTest);
        endMethod();
        setSimpleWebTest(null);
        return null;
      }

      public Object visitWebTestSuite(WebTestSuite webTestSuite) {
        for (WebTest webTest : webTestSuite) {
          basicGenerateTest(webTest);
        }
        return null;
      }
    });
  }

  private void startClassFor(WebTest aWebTest) {
    String importString = "import java.math.BigDecimal;\n";

    if (!this.getPackageName().equals(this.getPackageOfBaseClass())) {
      importString += "import " + this.getBaseClass() + ";\n";
    }

    this.getClassBuilder().startClass(this.getPackageName(), importString,
        aWebTest.getName() + "TestCase",
        "extends " + this.getSimpleNameOfBaseClass());

    this.getClassBuilder().startMethod("setUp", "Exception");
    this.getClassBuilder().addStatementAndNewLine("super.setUp();");
    for (WebTestItem item : aWebTest.getSetUpItems()) {
      this.computeStatementsFor(item);
    }
    this.getClassBuilder().endMethod();
  }

  private void startMethodFor(SimpleWebTest simpleWebTest) {
    this.getClassBuilder().startMethod("test" + simpleWebTest.getName(),
        "Exception");
  }

  private void endClass() {
    this.getClassBuilder().endClass();
  }

  private void endMethod() {
    this.getClassBuilder().endMethod();
  }

  private void computeMethodFor(SimpleWebTest simpleWebTest) {
    for (WebTestItem item : simpleWebTest.getItems()) {
      this.computeStatementsFor(item);
    }
  }

  private void computeStatementsFor(WebTestItem item) {
    item.accept(new WebTestItemVisitor() {
      public Object visitWebAssertTitle(WebAssertTitle assertTitle) {
        String assertStatement = generateStatementFor(assertTitle.getTitle());
        String statement = 
          "assertEquals("
            + assertStatement
            + ", selenium.getTitle());";
        getClassBuilder().addStatementAndNewLine(statement);
        return null;
      }

      public Object visitWebAssertExpression(WebAssertExpression webAssertExpression) {
        String assertStatement = generateStatementFor(webAssertExpression.getExpression());
        String statement = 
          "assertTrue("
            + assertStatement 
            + ");";
        getClassBuilder().addStatementAndNewLine(statement);
        return null;
      }

      public Object visitWebCreateVariableFromExpression(WebCreateVariableFromExpression webCreateVariableFromExpression) {
        ExpressionType expressionType = webCreateVariableFromExpression.getType();
        String typeAsJavaString = getExpressionTypeToJavaMapping().get(expressionType);
        String expressionAsJavaString = generateStatementFor(webCreateVariableFromExpression.getExpression());
        String variableName = webCreateVariableFromExpression.getVariableName();
        String statement = null;

        if (alreadyDefinedVariables.containsKey(variableName)) {
          if (alreadyDefinedVariables.get(variableName).equals(expressionType)) {
            statement = 
              variableName
              + " = "
              + expressionAsJavaString
              + ";";
          } else {
            throw new IllegalStateException("Variable " 
                    + variableName
                    + " is redefined with a different type");
          }
          
        } else {
          statement = 
            typeAsJavaString
              + " "
              + variableName
              + " = "
              + expressionAsJavaString
              + ";";
          alreadyDefinedVariables.put(variableName, expressionType);
        }
        
        getClassBuilder().addStatementAndNewLine(statement);
        return null;
      }

      public Object visitWebOpenUrl(WebOpenUrl webOpenUrl) {
        String statement = 
          "selenium.open(" 
            + "\"" 
            + webOpenUrl.getUrl()
            + "\""
            + ");";
        getClassBuilder().addStatementAndNewLine(statement);
        return null;
      }

      public Object visitWebExpression(WebExpression webExpression) {
        String statement = 
          generateStatementFor(webExpression.getExpression())
            + ";";
        getClassBuilder().addStatementAndNewLine(statement);
        return null;
      }

      public Object visitWebWaitPageToLoad(WebWaitPageToLoad webWaitPageToLoad) {
        String statement = getStopPolicy().generateStopStatement() + ";";
        getClassBuilder().addStatementAndNewLine(statement);
        return null;
      }
    });
  }

  protected String generateStatementFor(Expression expression) {
    if (expression.isConstant()) {
      ConstantExpression constant = (ConstantExpression) expression;
      return stringValueOf(constant);
    } else {
      return getExpressionGenerator().generate(expression);
    }
  }

  protected String stringValueOf(ConstantExpression constant) {
    if (this.getTypechecker().typecheck(constant).equals(ExpressionType.STRING)) {
      return "\"" + constant.getConstant() + "\"";
    } else if (this.getTypechecker().typecheck(constant).equals(
        ExpressionType.NUMBER)) {
      return "new BigDecimal(\"" + constant.getConstant() + "\")";
    } else if (this.getTypechecker().typecheck(constant).equals(
        ExpressionType.BOOLEAN)) {
      return constant.getConstant().toString();
    }
    return "";
  }

  private ClassBuilder getClassBuilder() {
    return classBuilder;
  }

  private void setClassBuilder(ClassBuilder classBuilder) {
    this.classBuilder = classBuilder;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  private Map<ExpressionType, String> getExpressionTypeToJavaMapping() {
    return expressionTypeToJavaMapping;
  }

  private void setExpressionTypeToJavaMapping(
      Map<ExpressionType, String> expressionTypeToJavaMapping) {
    this.expressionTypeToJavaMapping = expressionTypeToJavaMapping;
  }

  private SeleniumJavaExpressionGenerator getExpressionGenerator() {
    return expressionGenerator;
  }

  private SimpleWebTest getSimpleWebTest() {
    return simpleWebTest;
  }

  private void setSimpleWebTest(SimpleWebTest simpleWebTest) {
    this.simpleWebTest = simpleWebTest;
  }

  private NavigationStopGenerationPolicy getStopPolicy() {
    return stopPolicy;
  }

  public void setStopPolicy(NavigationStopGenerationPolicy stopPolicy) {
    this.stopPolicy = stopPolicy;
  }

  public String getPackageOfBaseClass() {
    int lastIndex = this.getBaseClass().lastIndexOf(".");
    return this.getBaseClass().substring(0, lastIndex);
  }

  public String getSimpleNameOfBaseClass() {
    int lastIndex = this.getBaseClass().lastIndexOf(".");
    return this.getBaseClass().substring(lastIndex + 1,
        this.getBaseClass().length());
  }

  public String getBaseClass() {
    return baseClass;
  }

  public void setBaseClass(String baseClass) {
    this.baseClass = baseClass;
  }

  ExpressionTypechecker getTypechecker() {
    return typechecker;
  }

  private void setTypechecker(ExpressionTypechecker typechecker) {
    this.typechecker = typechecker;
  }

  private void setExpressionGenerator(
      SeleniumJavaExpressionGenerator expressionGenerator) {
    this.expressionGenerator = expressionGenerator;
  }
}
