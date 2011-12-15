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
package org.webspeclanguage.mockupdd.codegen.factory;

import java.io.File;
import java.io.FileFilter;

import junit.framework.TestCase;

import org.webspeclanguage.mockupdd.codegen.generator.facade.SuiCodeGenerationFacade;

/**
 * @author Jose Matias Rivero
 */
public class SuiCodeGeneratorFactoryTest extends TestCase {

  private static String getSourceFolder() {
    return "src/test/resources/org/webspeclanguage/mockupdd/codegen/factory/";
  }
  
  private static String getSiteFolder() {
    return "src/test/resources/org/webspeclanguage/mockupdd/codegen/factory/dest/";
  }
  
  private static String getDestinationFolder() {
    return "src/test/resources/org/webspeclanguage/mockupdd/codegen/factory/dest/"; 
  }
  
  @Override
  public void setUp() {
    
  }
  
  public void testBalsamiq2ExtJs() throws Exception {
    this.cleanFolder("extjs/pages");
    SuiCodeGenerationFacade.getInstance().balsamiq2ExtJs(
      getSourceFolder() + "balsamiq",
      getDestinationFolder() + "extjs/pages",
      getSiteFolder(),
      "../extjs/pages",
      "..");
    this.assertFiles("extjs/pages", "newEvent.js", "inviteFriendsToEvent.js",
            "taskManager.js", "newEvent.html", "inviteFriendsToEvent.html",
            "taskManager.html", "editAlbum.html", "editAlbum.js");
  }
  
  public void testBalsamiq2Xml() throws Exception {
    this.cleanFolder("xml/pages");
    SuiCodeGenerationFacade.getInstance().balsamiq2Xml(
      getSourceFolder() + "balsamiq",
      getDestinationFolder() + "xml/pages");
    this.assertFiles("xml/pages", "newEvent.xml", "inviteFriendsToEvent.xml",
      "taskManager.xml", "editAlbum.xml");
  }
  
  public void assertFiles(String folder, String... filenames) {
    for (String filename : filenames) {
      assertTrue(new File(getDestinationFolder() + folder + "/" + filename).exists());
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
