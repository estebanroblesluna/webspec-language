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
package org.webspeclanguage.metamock.model;

import junit.framework.TestCase;

import org.webspeclanguage.metamock.config.SuiDefaultConfig;
import org.webspeclanguage.metamock.model.impl.SuiFactoryImpl;

/**
 * @author Jose Matias Rivero
 */
public abstract class SuiTestCase extends TestCase {

  private SuiFactory factory;

  public SuiTestCase() {
    super();
  }

  protected void setFactory(SuiFactory factory) {
    this.factory = factory;
  }

  protected SuiFactory getFactory() {
    return factory;
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.setFactory(SuiDefaultConfig.getInstance().getFactory());
  }
  
}