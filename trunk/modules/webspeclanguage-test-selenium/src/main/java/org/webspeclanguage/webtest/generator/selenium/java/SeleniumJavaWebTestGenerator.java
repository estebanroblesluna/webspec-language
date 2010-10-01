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

import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.core.ExpressionType;
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
public class SeleniumJavaWebTestGenerator implements WebTestGenerator {

  private ClassBuilder classBuilder;
  private String packageName;
  private SeleniumJavaExpressionGenerator expressionGenerator;

  private Map<ExpressionType, String> expressionTypeToJavaMapping;
  private NavigationStopGenerationPolicy stopPolicy;
  private String baseClass;

  private Map<String, ExpressionType> alreadyDefinedVariables;

  public SeleniumJavaWebTestGenerator() {
    this.alreadyDefinedVariables = new HashMap<String, ExpressionType>();
    this.classBuilder = new ClassBuilder();
    this.expressionGenerator = new SeleniumJavaExpressionGenerator();
    this.expressionTypeToJavaMapping = new HashMap<ExpressionType, String>();

    this.packageName = "";
    this.baseClass = "com.thoughtworks.selenium.SeleneseTestCase";
    this.stopPolicy = new ThreadSleepGenerationPolicy();

    this.configureExpressionTypeMapping();
  }

  private void configureExpressionTypeMapping() {
    this.expressionTypeToJavaMapping.put(ExpressionType.STRING, "String");
    this.expressionTypeToJavaMapping.put(ExpressionType.NUMBER, "BigDecimal");
    this.expressionTypeToJavaMapping.put(ExpressionType.BOOLEAN, "boolean");
  }

  /**
   * {@inheritDoc}
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
    return this.classBuilder.getClassCode();
  }

  public void generateSimpleWebTest(SimpleWebTest simpleWebTest) {
    this.startClassFor(simpleWebTest);
    this.startMethodFor(simpleWebTest);
    this.computeMethodFor(simpleWebTest);
    this.endMethod();
    this.endClass();
  }

  public void generateWebTestSuite(WebTestSuite webTestSuite) {
    this.startClassFor(webTestSuite);
    for (WebTest webTest : webTestSuite) {
      this.basicGenerateTest(webTest);
    }
    this.endClass();
  }

  private void basicGenerateTest(WebTest webTest) {
    this.alreadyDefinedVariables.clear();

    webTest.accept(new WebTestVisitor() {

      public Object visitSimpleWebTest(SimpleWebTest simpleWebTest) {
        startMethodFor(simpleWebTest);
        computeMethodFor(simpleWebTest);
        endMethod();
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

    this.classBuilder.startClass(this.getPackageName(), importString, aWebTest.getName() + "TestCase", "extends " + this.getSimpleNameOfBaseClass());

    this.classBuilder.startMethod("setUp", "Exception");
    this.classBuilder.addStatementAndNewLine("super.setUp();");
    for (WebTestItem item : aWebTest.getSetUpItems()) {
      this.computeStatementsFor(item);
    }
    this.classBuilder.endMethod();
  }

  private void startMethodFor(SimpleWebTest simpleWebTest) {
    this.classBuilder.startMethod("test" + simpleWebTest.getName(), "Exception");
  }

  private void endClass() {
    this.classBuilder.endClass();
  }

  private void endMethod() {
    this.classBuilder.endMethod();
  }

  private void computeMethodFor(SimpleWebTest simpleWebTest) {
    for (WebTestItem item : simpleWebTest.getItems()) {
      this.computeStatementsFor(item);
    }
  }

  private void computeStatementsFor(WebTestItem item) {
    item.accept(new WebTestItemGenerator());
  }

  protected String generateStatementFor(Expression expression) {
    return expressionGenerator.generate(expression);
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
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
    return this.getBaseClass().substring(lastIndex + 1, this.getBaseClass().length());
  }

  public String getBaseClass() {
    return baseClass;
  }

  public void setBaseClass(String baseClass) {
    this.baseClass = baseClass;
  }
  
  private final class WebTestItemGenerator implements WebTestItemVisitor {

    public Object visitWebAssertTitle(WebAssertTitle assertTitle) {
      String assertStatement = generateStatementFor(assertTitle.getTitle());
      String statement = "assertEquals(" + assertStatement + ", selenium.getTitle());";
      classBuilder.addStatementAndNewLine(statement);
      return null;
    }
    
    public Object visitWebAssertExpression(WebAssertExpression webAssertExpression) {
      String assertStatement = generateStatementFor(webAssertExpression.getExpression());
      String statement = "assertTrue(" + assertStatement + ");";
      classBuilder.addStatementAndNewLine(statement);
      return null;
    }
    
    public Object visitWebCreateVariableFromExpression(WebCreateVariableFromExpression webCreateVariableFromExpression) {
      ExpressionType expressionType = webCreateVariableFromExpression.getType();
      String typeAsJavaString = expressionTypeToJavaMapping.get(expressionType);
      String expressionAsJavaString = generateStatementFor(webCreateVariableFromExpression.getExpression());
      String variableName = webCreateVariableFromExpression.getVariableName();
      String statement = null;

      if (alreadyDefinedVariables.containsKey(variableName)) {
        if (alreadyDefinedVariables.get(variableName).equals(expressionType)) {
          statement = variableName + " = " + expressionAsJavaString + ";";
        } else {
          throw new IllegalStateException("Variable " + variableName + " is redefined with a different type");
        }

      } else {
        statement = typeAsJavaString + " " + variableName + " = " + expressionAsJavaString + ";";
        alreadyDefinedVariables.put(variableName, expressionType);
      }

      classBuilder.addStatementAndNewLine(statement);
      return null;
    }
    
    public Object visitWebOpenUrl(WebOpenUrl webOpenUrl) {
      String statement = "selenium.open(" + "\"" + webOpenUrl.getUrl() + "\"" + ");";
      classBuilder.addStatementAndNewLine(statement);
      return null;
    }
    
    public Object visitWebExpression(WebExpression webExpression) {
      String statement = generateStatementFor(webExpression.getExpression()) + ";";
      classBuilder.addStatementAndNewLine(statement);
      return null;
    }
    
    public Object visitWebWaitPageToLoad(WebWaitPageToLoad webWaitPageToLoad) {
      String statement = stopPolicy.generateStopStatement() + ";";
      classBuilder.addStatementAndNewLine(statement);
      return null;
    }
  }
}
