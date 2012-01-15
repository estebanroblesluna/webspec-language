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

package org.webspeclanguage.mockupdd.specs.processors;

import org.junit.Ignore;
import org.webspeclanguage.mockupdd.specs.data.DataSpecFactory;
import org.webspeclanguage.mockupdd.specs.data.impl.DataSpecFactoryImpl;
import org.webspeclanguage.mockupdd.specs.hypertext.HypertextSpecFactory;
import org.webspeclanguage.mockupdd.specs.hypertext.impl.HypertextSpecFactoryImpl;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;

/**
 * @author José Matías Rivero
 */
@Ignore
public class SuiSpecsTestCase extends SuiTestCase {

  protected HypertextSpecFactory hypertextFactory;
  protected DataSpecFactory dataSpecFactory;
  
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.hypertextFactory = new HypertextSpecFactoryImpl();
    this.dataSpecFactory = new DataSpecFactoryImpl();
  }

  
  
}
