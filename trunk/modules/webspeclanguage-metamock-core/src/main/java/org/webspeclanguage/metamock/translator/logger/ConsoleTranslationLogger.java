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
package org.webspeclanguage.metamock.translator.logger;

import org.webspeclanguage.metamock.model.layout.GridBagLayoutException;
import org.webspeclanguage.metamock.translator.SuiTranslationException;
import org.webspeclanguage.metamock.translator.MockupSourceParsingException;

/**
 * @author Jose Matias Rivero
 */
public class ConsoleTranslationLogger implements MockupProcessingLogger {

  public void logTranslationException(SuiTranslationException exception) {
    System.out.println("Translation exception: " + exception.getMessage());
  }

  public void logGridBagLayoutException(GridBagLayoutException e) {
    System.out.println("GridBagLayoutException: " + e.getMessage());
  }

  public void logSourceParsingException(MockupSourceParsingException e) {
    System.out.println("MockupSourceParsingException: " + e.getMessage());
  }

}
