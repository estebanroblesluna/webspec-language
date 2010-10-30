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
package org.webspeclanguage.metamock.codegen.generator;

import java.io.File;
import java.io.FileFilter;

/**
 * @author Jose Matias Rivero
 */
public class RegularExpressionFileFilter implements FileFilter {

  private String regularExpression;

  public RegularExpressionFileFilter(String regularExpression) {
    super();
    this.setRegularExpression(regularExpression);
  }

  public boolean accept(File file) {
    return file.getName().matches(this.getRegularExpression());
  }

  private void setRegularExpression(String regularExpression) {
    this.regularExpression = regularExpression;
  }

  private String getRegularExpression() {
    return regularExpression;
  }

}
