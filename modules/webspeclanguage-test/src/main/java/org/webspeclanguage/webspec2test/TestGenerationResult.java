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
package org.webspeclanguage.webspec2test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.impl.core.Path;
import org.webspeclanguage.webtest.base.SimpleWebTest;
import org.webspeclanguage.webtest.base.WebTestSuite;

/**
 * A result obtained from applying a test generation
 * process
 * 
 * @author Esteban Robles Luna
 */
public class TestGenerationResult {

  private String name;
  private WebTestSuite testSuite;
  private List<SimpleWebTest> tests;
  private Map<Path, SimpleWebTest> generatedTests;
  
  public TestGenerationResult(String name) {
    Validate.notNull(name);
    
    this.name = name;
    this.setGeneratedTests(new HashMap<Path, SimpleWebTest>());
    this.setTests(new ArrayList<SimpleWebTest>());
  }

  protected void addTest(Path path, SimpleWebTest aT) {
    this.getTests().add(aT);
    this.generatedTests.put(path, aT);
  }

  public int getSize() {
    return this.getTests().size();
  }

  public SimpleWebTest getTest(int i) {
    return this.getTests().get(i);
  }

  protected List<SimpleWebTest> getTests() {
    return this.tests;
  }

  public Set<Path> getPaths() {
    return this.generatedTests.keySet();
  }

  private void setGeneratedTests(Map<Path, SimpleWebTest> generatedTs) {
    this.generatedTests = generatedTs;
  }

  private void setTests(List<SimpleWebTest> tests) {
    this.tests = tests;
  }

  protected String getName() {
    return name;
  }

  public WebTestSuite getTestSuite() {
    if (this.testSuite == null) {
      this.testSuite = new WebTestSuite(this.getName());
      for (SimpleWebTest test : this.getTests()) {
        this.testSuite.addTest(test);
      }
      this.testSuite.addSetUpItem(this.getTest(0).getSetUpItems().get(0));
    }
    return testSuite;
  }
}
