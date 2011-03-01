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
package org.webspeclanguage.metamock.codegen.factory;

import java.io.File;
import java.io.FileFilter;

import junit.framework.TestCase;

import org.webspeclanguage.metamock.codegen.generator.facade.MetaMockCodeGenerationFacade;
import org.webspeclanguage.metamock.translator.logger.ConsoleMetaMockTranslationLogger;

/**
 * @author Jose Matias Rivero
 */
public class MetaMockCodeGeneratorFactoryTest extends TestCase {

  private static String getSourceFolder() {
    return "src/test/resources/org/webspeclanguage/metamock/codegen/factory/";
  }
  
  private static String getDestinationFolder() {
    //return "src/test/resources/org/webspeclanguage/metamock/codegen/factory/dest/"; 
    return "../webspeclanguage-webapp/src/main/webapp/codegen/";
  }
  
  @Override
  public void setUp() {
    
  }
  
  public void testBalsamiq2ExtJs() {
    this.cleanFolder("extjs/pages");
    MetaMockCodeGenerationFacade.getInstance().balsamiq2ExtJs(
      getSourceFolder() + "balsamiq",
      getDestinationFolder() + "extjs/pages");
    this.assertFiles("extjs/pages/newEvent.js", "extjs/pages/inviteFriendsToEvent.js",
      "extjs/pages/taskManager.js", "extjs/pages/newEvent.html", "extjs/pages/inviteFriendsToEvent.html",
      "extjs/pages/taskManager.html", "extjs/pages/editAlbum.html", "extjs/pages/editAlbum.js");
  }
  
  public void assertFiles(String... filenames) {
    for (String filename : filenames) {
      assertTrue(new File(getDestinationFolder() + filename).exists());
    }
  }
  
  public void assertNoFiles(String folder) {
    assertEquals(0, new File(getDestinationFolder() + folder).listFiles(new HiddenFileFilter()).length);
  }
  
  private void cleanFolder(String folder) {
    File destFolder = new File(getDestinationFolder() + folder);
    for (File file : destFolder.listFiles()) {
      if (file.isFile()) {
        file.delete();
      }
    }
    this.assertNoFiles(folder);
  }
  
  private class HiddenFileFilter implements FileFilter {

    public boolean accept(File pathname) {
      return !pathname.isHidden();
    }
    
  }
}
