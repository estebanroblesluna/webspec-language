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

package org.webspeclanguage.webtest.generator.watir.java;

import junit.framework.TestCase;

/**
 * Test Case for Ruby Class Builder class 
 * 
 * @author Gonzalo G. Testa
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
    classText.startClass("rubygems, watir", "New Test", ""); 
    classCode = "require \"rubygems\"\nrequire \"watir\"\n\nclass New_Test\n\n";
    assertEquals(classCode, classText.getClassCode());

  }

  public void testGeneratingMethod(){
    classText.startMethod("setVar");
    classCode = classCode + "\tdef setVar\n";
    assertEquals(classCode, classText.getClassCode());

  }

  public void testGeneratingMethodStatement(){
    classText.setMethodStatement("i = 0");
    classText.setMethodStatement("i = i + 1");
    classCode = classCode + "\t\ti = 0\n";
    classCode = classCode + "\t\ti = i + 1\n";
    assertEquals(classCode, classText.getClassCode());
  }

  public void testEndingMethod(){
    classText.endMethod();
    classCode = classCode + "\tend\n\n";
    assertEquals(classCode, classText.getClassCode());
  }

  public void testEndingClass(){
    classText.endClass();
    classCode = classCode + "end\n";
    assertEquals(classCode, classText.getClassCode());
  }

}
