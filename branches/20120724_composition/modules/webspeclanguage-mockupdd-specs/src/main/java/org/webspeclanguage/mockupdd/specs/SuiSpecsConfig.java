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
package org.webspeclanguage.mockupdd.specs;

import org.webspeclanguage.mockupdd.specs.data.DataSpecFactory;
import org.webspeclanguage.mockupdd.specs.data.impl.DataSpecFactoryImpl;
import org.webspeclanguage.mockupdd.specs.hypertext.HypertextSpecFactory;
import org.webspeclanguage.mockupdd.specs.hypertext.impl.HypertextSpecFactoryImpl;


/**
 * @author Jose Matias Rivero
 */
public class SuiSpecsConfig {

  private static SuiSpecsConfig instance;
  
  public static SuiSpecsConfig getInstance() {
    if (instance == null) {
      instance = new SuiSpecsConfig(new HypertextSpecFactoryImpl(), new DataSpecFactoryImpl());
    }
    return instance;
  }
  
  
  public SuiSpecsConfig(HypertextSpecFactory hypertextSpecFactory, DataSpecFactory dataSpecFactory) {
    super();
    this.hypertextSpecFactory = hypertextSpecFactory;
    this.dataSpecFactory = dataSpecFactory;
  }


  private HypertextSpecFactory hypertextSpecFactory;
  private DataSpecFactory dataSpecFactory;
  
  public HypertextSpecFactory getHypertextSpecFactory() {
    return hypertextSpecFactory;
  }
  
  public DataSpecFactory getDataSpecFactory() {
    return dataSpecFactory;
  }

}
