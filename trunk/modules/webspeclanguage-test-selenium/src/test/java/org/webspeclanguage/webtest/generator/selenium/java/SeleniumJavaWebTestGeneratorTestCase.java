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

import junit.framework.TestCase;

import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.base.WebSpecFactory;
import org.webspeclanguage.webspec2test.TestGenerationResult;
import org.webspeclanguage.webspec2test.WebSpec2WebTestTransformation;
import org.webspeclanguage.webtest.base.SimpleWebTest;

/**
 * @author Esteban Robles Luna
 */
public class SeleniumJavaWebTestGeneratorTestCase extends TestCase {

  private SeleniumJavaWebTestGenerator generator;
  private WebSpec2WebTestTransformation transformation;

  public void setUp() throws Exception {
    super.setUp();

    this.transformation = new WebSpec2WebTestTransformation();

    this.generator = new SeleniumJavaWebTestGenerator();
    this.generator.setPackageName("ar.lifia.info");
  }

  public void testGeneratedClass() {
    WebSpecDiagram webSpecDiagram = WebSpecFactory.getAmazonExample();
    TestGenerationResult result = this.transformation.transform(webSpecDiagram);
    assertEquals(1, result.getSize());
    SimpleWebTest test1 = result.getTest(0);
    String classCode = generator.getClassCode(test1);
    assertNotNull(classCode);
    assertFalse(classCode.isEmpty());
  }

  public void testGeneratedClass2() {
    WebSpecDiagram webSpecDiagram = WebSpecFactory.getAmazonExample2();
    TestGenerationResult result = this.transformation.transform(webSpecDiagram);
    assertEquals(2, result.getSize());

    SimpleWebTest test1 = result.getTest(0);
    String classCode1 = generator.getClassCode(test1);
    assertNotNull(classCode1);
    assertFalse(classCode1.isEmpty());

    SimpleWebTest test2 = result.getTest(1);
    String classCode2 = generator.getClassCode(test2);
    assertNotNull(classCode2);
    assertFalse(classCode2.isEmpty());
  }
}
