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
import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.metamock.translator.MockupContainerInfo;

/**
 * @author Jose Matias Rivero
 */
public class FolderMockupCollector implements MockupCollector<File> {

  private String mockupSourceFolder;
  private FileFilter fileFilter;

  public FolderMockupCollector(String mockupSourceFolder, FileFilter fileFilter) {
    super();
    this.setMockupSourceFolder(mockupSourceFolder);
    this.setFileFilter(fileFilter);
  }

  public List<Mockup<File>> collectMockups() {
    File dir = new File(this.getMockupSourceFolder());
    if (!dir.isDirectory()) {
      return new ArrayList<Mockup<File>>();
    } else {
      List<Mockup<File>> mockups = new ArrayList<Mockup<File>>();
      for (File f : dir.listFiles(this.getFileFilter())) {
        mockups.add(new Mockup<File>(f, new MockupContainerInfo(f.getName(), "file://" + f.getAbsolutePath())));
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
