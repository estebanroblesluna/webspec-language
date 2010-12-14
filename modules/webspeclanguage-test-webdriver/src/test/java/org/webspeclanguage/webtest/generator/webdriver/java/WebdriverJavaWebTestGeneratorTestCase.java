package org.webspeclanguage.webtest.generator.webdriver.java;

import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.impl.core.WebSpecFactory;
import org.webspeclanguage.webspec2test.TestGenerationResult;
import org.webspeclanguage.webspec2test.WebSpec2WebTestTransformation;
import org.webspeclanguage.webtest.base.SimpleWebTest;
import junit.framework.TestCase;


public class WebdriverJavaWebTestGeneratorTestCase extends TestCase{

  private WebdriverJavaWebTestGenerator generator;
  private WebSpec2WebTestTransformation transformation;  
  
  public void setUp() throws Exception {
    super.setUp();

    this.transformation = new WebSpec2WebTestTransformation();

    this.generator = new WebdriverJavaWebTestGenerator();
    
  }

  public void testGeneratedClass() {
    Diagram webSpecDiagram = WebSpecFactory.getAmazonExample();
    TestGenerationResult result = this.transformation.transform(webSpecDiagram);
    assertEquals(1, result.getSize());
    SimpleWebTest test1 = result.getTest(0);
    String classCode = generator.getClassCode(test1);
    assertNotNull(classCode);
    assertFalse(classCode.isEmpty());
    
//    System.out.println(classCode.toString());
  }
  
  public void testGeneratedClass2() {
    Diagram webSpecDiagram = WebSpecFactory.getAmazonExample2();
    TestGenerationResult result = this.transformation.transform(webSpecDiagram);
    assertEquals(2, result.getSize());

    SimpleWebTest test1 = result.getTest(0);
    String classCode1 = generator.getClassCode(test1);
    assertNotNull(classCode1);
    assertFalse(classCode1.isEmpty());

//    System.out.println(classCode1.toString());
    
    SimpleWebTest test2 = result.getTest(1);
    String classCode2 = generator.getClassCode(test2);
    assertNotNull(classCode2);
    assertFalse(classCode2.isEmpty());
    
//    System.out.println(classCode2.toString());
  }
  
}
