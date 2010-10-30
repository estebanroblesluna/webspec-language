package org.webspeclanguage.metamock.codegen.factory;

import java.io.File;
import java.io.FileFilter;

import org.webspeclanguage.metamock.codegen.generator.facade.MetaMockCodeGenerationFacade;

import junit.framework.TestCase;

/**
 * @author Jose Matias Rivero
 */
public class MetaMockCodeGeneratorFactoryTest extends TestCase {

  private static String getSourceFolder() {
    return "src/test/resources/org/webspeclanguage/metamock/codegen/factory/";
  }
  
  private static String getDestinationFolder() {
    return "src/test/resources/org/webspeclanguage/metamock/codegen/factory/dest/"; 
    //return "../webspeclanguage-webapp/src/main/webapp/codegen/";
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
      "extjs/pages/taskManager.html");
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
