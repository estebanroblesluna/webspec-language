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
package org.webspeclanguage.userstories.visitor;

import java.io.File;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.webspeclanguage.userstories.cropping.CroppingInfo;

/**
 * @author cristian.cianfagna
 */
public abstract class AbstractCroppingVisitor extends AbstractVisitor {

  private Map<String, CroppingInfo> croppingMap;
  private File diagramFile;

  public AbstractCroppingVisitor(Map<String, CroppingInfo> croppingMap, File diagramFile, MessageSource messageSource, Locale locale) {
    super(messageSource, locale);
    this.setCroppingMap(croppingMap);
    this.setDiagramFile(diagramFile);
  }
  
  protected Map<String, CroppingInfo> getCroppingMap() {
    return croppingMap;
  }
  
  private void setCroppingMap(Map<String, CroppingInfo> croppingMap) {
    this.croppingMap = croppingMap;
  }
  
  protected File getDiagramFile() {
    return diagramFile;
  }
  
  private void setDiagramFile(File diagramFile) {
    this.diagramFile = diagramFile;
  }

}
