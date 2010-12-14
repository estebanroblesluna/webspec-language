package org.webspeclanguage.webtest.generator.webdriver.java;


import org.webspeclanguage.webtest.generator.webdriver.java.ClassBuilder;

import junit.framework.TestCase;

/**
 * @author Gonzalo Testa
 *
 */
public class ClassBuilderTestCase extends TestCase{
	public ClassBuilder classText;
	public static String classCode;

	
	public void setUp() throws Exception {
		super.setUp();
		classText = new ClassBuilder();
		classCode = "";
	}

	public void testGeneratedClass(){
		String packageName = "org.webspeclanguage.test.webdriver";
		
		String imports = "import org.openqa.selenium.By;\n";
		imports += "import org.openqa.selenium.WebDriver;\n";
		imports += "import org.openqa.selenium.RenderedWebElement;\n";
		imports += "import org.openqa.selenium.WebElement;\n";
		imports += "import org.openqa.selenium.firefox.FirefoxDriver;\n";
		
		classText.startClass(packageName, imports, "New Test", "extends TestCase");
		classCode = "package " + packageName + ";\n\n";
		classCode += "import org.openqa.selenium.By;\n";
		classCode += "import org.openqa.selenium.WebDriver;\n";
		classCode += "import org.openqa.selenium.RenderedWebElement;\n";
		classCode += "import org.openqa.selenium.WebElement;\n";
		classCode += "import org.openqa.selenium.firefox.FirefoxDriver;\n\n";
		classCode += "public class New_Test extends TestCase {\n\n";
//		classCode += "\tpublic void setUp {\n";
//		classCode += "\t\tWebDriver driver = new FirefoxDriver();\n";
//		classCode += "\t}\n";
		assertEquals(classCode, classText.getClassCode());
	}

	public void testGeneratingMethod(){
		classText.startMethod("setVar", "NullPointerException");
		classCode = classCode + "\tpublic void setVar() throws NullPointerException {\n";
		assertEquals(classCode, classText.getClassCode());

	}

	public void testGeneratingMethodStatement(){
		classText.addStatementAndNewLine("var i = 0;");
		classText.addStatementAndNewLine("i = i + 1;");
		classCode = classCode + "\t\tvar i = 0;\n";
		classCode = classCode + "\t\ti = i + 1;\n";
		assertEquals(classCode, classText.getClassCode());
	}

	public void testEndingMethod(){
		classText.endMethod();
		classCode = classCode + "\t}\n\n";
		assertEquals(classCode, classText.getClassCode());
	}

	public void testEndingClass(){
		classText.endClass();
		classCode = classCode + "}";
		assertEquals(classCode, classText.getClassCode());
	}

}
