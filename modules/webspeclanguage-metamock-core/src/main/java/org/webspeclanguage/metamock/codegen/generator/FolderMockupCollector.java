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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.webspeclanguage.metamock.translator.MockupContainerInfo;

/**
 * @author Jose Matias Rivero
 */
public class FolderMockupCollector implements MockupCollector<String> {

  private String mockupSourceFolder;
  private FileFilter fileFilter;

  public FolderMockupCollector(String mockupSourceFolder, FileFilter fileFilter) {
    super();
    this.setMockupSourceFolder(mockupSourceFolder);
    this.setFileFilter(fileFilter);
  }

  public List<Mockup<String>> collectMockups() {
    File dir = new File(this.getMockupSourceFolder());
    if (!dir.isDirectory()) {
      return new ArrayList<Mockup<String>>();
    } else {
      List<Mockup<String>> mockups = new ArrayList<Mockup<String>>();
      for (File f : dir.listFiles(this.getFileFilter())) {
        try {
          mockups.add(new Mockup<String>(FileUtils.readFileToString(f), new MockupContainerInfo(f.getName().split("\\.")[0], "file://" + f.getAbsolutePath())));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return mockups;
    }
  }

  private void setMockupSourceFolder(String sourceMockupFolder) {
    this.mockupSourceFolder = sourceMockupFolder;
  }

  private String getMockupSourceFolder() {
    return mockupSourceFolder;
  }

  private void setFileFilter(FileFilter fileFilter) {
    this.fileFilter = fileFilter;
  }

  private FileFilter getFileFilter() {
    return fileFilter;
  }

}
