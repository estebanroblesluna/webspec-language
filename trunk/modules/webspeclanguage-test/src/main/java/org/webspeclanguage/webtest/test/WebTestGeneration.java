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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.webspeclanguage.webtest.base.WebTestGenerator;

/**
 * An annotation for marking a test of {@link WebTestGenerator}
 * 
 * Usage:
 * - Create a Y TestCase extending {@link WebTestGenerationTestCase}
 * - Create a method testXXX
 * - Create a file Y.testXXX.expectedTest
 * - Call checkTestGeneration with the WebTest instance to check that
 * the test generated is equals to the file Y.testXXX.expectedTest
 * 
 * @author Esteban Robles Luna
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface WebTestGeneration {

  public String[] expectedTestFilename() default {};
}
