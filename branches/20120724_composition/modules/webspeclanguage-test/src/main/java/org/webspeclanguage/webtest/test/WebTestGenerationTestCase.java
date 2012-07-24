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
package org.webspeclanguage.webtest.test;

import java.io.InputStream;
import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.io.WebSpecParser;
import org.webspeclanguage.webspec2test.TestGenerationResult;
import org.webspeclanguage.webspec2test.WebSpec2WebTestTransformation;
import org.webspeclanguage.webtest.base.WebTest;
import org.webspeclanguage.webtest.base.WebTestGenerator;
import org.webspeclanguage.webtest.base.WebTestSuite;


/**
 * A base class for testing test generation
 * 
 * @author Esteban Robles Luna
 */
public abstract class WebTestGenerationTestCase extends TestCase {

  private String expectedTest;
  
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		Method method = this.getClass().getDeclaredMethod(this.getName(), (Class<?>[])null);
		WebTestGeneration testGenerationAnnotation = method.getAnnotation(WebTestGeneration.class);
		if (testGenerationAnnotation != null) {
  		String filename = null;
  		
  		if (testGenerationAnnotation.expectedTestFilename().length > 0) {
  		  filename = testGenerationAnnotation.expectedTestFilename()[0];
  		} else {
  		  filename = this.getClass().getName().replace(".", "/")
        + "."
        + this.getName()
        + ".expectedTest";
  		}
  		
  		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      InputStream expectedTestAsStream = classLoader.getResourceAsStream(filename);
  		this.expectedTest = IOUtils.toString(expectedTestAsStream);
		}
	}

	protected void checkTestGeneration(WebTest webTest) {
	  String generatedTest = this.getTestGenerator().generateTest(webTest);
	  assertEquals(this.expectedTest, generatedTest);
	}
	
	protected void checkTestGenerationFromResource() {
    String filename = this.getClass().getName().replace(".", "/")
      + "."
      + this.getName()
      + ".xml";

    WebSpecParser parser = new WebSpecParser();
	  Diagram diagram = parser.parse(filename);
	  
	  WebSpec2WebTestTransformation transformation = new WebSpec2WebTestTransformation();
	  TestGenerationResult generationResult = transformation.transform(diagram);
	  WebTestSuite testSuite = generationResult.getTestSuite();
	  this.checkTestGeneration(testSuite);
  }
	
	protected abstract WebTestGenerator getTestGenerator();
}
