package org.webspeclanguage.webtest.generator.watir.java;

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
import org.webspeclanguage.webtest.base.WebTest;
import org.webspeclanguage.webtest.base.WebTestGenerator;
import org.webspeclanguage.webtest.base.WebTestItem;
import org.webspeclanguage.webtest.base.WebTestItemVisitor;
import org.webspeclanguage.webtest.base.WebTestSuite;
import org.webspeclanguage.webtest.base.WebTestVisitor;

public class WatirRubyWebTestGenerator implements WebTestGenerator {

	private static final String BROWSER_OBJ = "$browser.";

	private ClassBuilder classBuilder;

	private WatirRubyExpressionGenerator expressionGenerator;

	//private HashMap<ExpressionType, String> expressionTypeToJavaMapping;

	private Map<String, ExpressionType> alreadyDefinedVariables;

	public WatirRubyWebTestGenerator(){
		this.alreadyDefinedVariables = new HashMap<String, ExpressionType>();

		this.classBuilder = new ClassBuilder(); 
		this.expressionGenerator = new WatirRubyExpressionGenerator();
		//this.expressionTypeToJavaMapping = new HashMap<ExpressionType, String>();
	}

	public String generateTest(WebTest webTest) {
		return this.getClassCode(webTest);
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
		String importString = "rubygems,watir,test/unit";

		this.classBuilder.startClass(importString, "TC_" + aWebTest.getName(), "Test::Unit::TestCase");

		classBuilder.startMethod("setup");

		classBuilder.setMethodStatement("$browser = Watir::Browser.new()");

		for (WebTestItem item : aWebTest.getSetUpItems()) {
			this.computeStatementsFor(item);
		}
		this.classBuilder.endMethod();

	}

	public void generateSimpleWebTest(SimpleWebTest simpleWebTest) {
		this.startClassFor(simpleWebTest);
		this.startMethodFor(simpleWebTest);
		this.computeMethodFor(simpleWebTest);
		this.endMethod();
		this.endClass();
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

	private void startMethodFor(SimpleWebTest simpleWebTest) {
		classBuilder.startMethod("test" + simpleWebTest.getName());
	}

	//  private void startClassFor(SimpleWebTest simpleWebTest) {
	//    this.classBuilder.startClass("rubygems,watir,test/unit", "TC_" + simpleWebTest.getName(), "Test::Unit::TestCase");
	//  }


	protected String generateStatementFor(Expression expression) {
		return expressionGenerator.generate(expression);
	}

	private final class WebTestItemGenerator implements WebTestItemVisitor {



		public Object visitWebAssertTitle(WebAssertTitle assertTitle) {
			Expression title = assertTitle.getTitle();
			String assertStatement = generateStatementFor(title);
			String statement = "assert(" + assertStatement + ", " + BROWSER_OBJ + "title())";
			classBuilder.addStatementAndNewLine(statement);
			return null;
		}

		public Object visitWebAssertExpression(WebAssertExpression webAssertExpression) {
			String assertStatement = generateStatementFor(webAssertExpression.getExpression());
			String statement = "assert(" + assertStatement + ")";
			classBuilder.addStatementAndNewLine(statement);
			return null;
		}

		public Object visitWebCreateVariableFromExpression(WebCreateVariableFromExpression webCreateVariableFromExpression) {
			ExpressionType expressionType = webCreateVariableFromExpression.getType();
			//String typeAsJavaString = expressionTypeToJavaMapping.get(expressionType);
			String expressionAsJavaString = generateStatementFor(webCreateVariableFromExpression.getExpression());
			String variableName = webCreateVariableFromExpression.getVariableName();
			String statement = null;

			if (alreadyDefinedVariables.containsKey(variableName)) {
				if (alreadyDefinedVariables.get(variableName).equals(expressionType)) {
					statement = variableName + " = " + expressionAsJavaString + "";
				} else {
					throw new IllegalStateException("Variable " + variableName + " is redefined with a different type");
				}

			} else {
				statement = variableName + " = " + expressionAsJavaString + "";
				alreadyDefinedVariables.put(variableName, expressionType);
			}

			classBuilder.addStatementAndNewLine(statement);
			return null;
		}

		public Object visitWebOpenUrl(WebOpenUrl webOpenUrl) {

			String statement = BROWSER_OBJ + "goto(" + "\"" + webOpenUrl.getUrl() + "\"" + ")";
			classBuilder.addStatementAndNewLine(statement);

			return null;
		}

		public Object visitWebExpression(WebExpression webExpression) {
			String statement = generateStatementFor(webExpression.getExpression());//+ ";";
			classBuilder.addStatementAndNewLine(statement);
			return null;
		}

		public Object visitWebWaitPageToLoad(WebWaitPageToLoad webWaitPageToLoad) {
			String statement = BROWSER_OBJ + "wait()";
			classBuilder.addStatementAndNewLine(statement);
			return null;
		}
	}

	public void setStopPolicy() {
		// TODO Auto-generated method stub

	}

	public void setPackageName(String string) {
		// TODO Auto-generated method stub

	}

}
