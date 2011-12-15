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
package org.webspeclanguage.mockupdd.codegen.artifacts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeWriter;

/**
 * @author Jose Matias Rivero
 */
public class CodeFile<T extends CodeArtifact> implements CodeArtifact {

  private T artifactToBind;
  private String filePath;
  private String folderPath;
  private Boolean writeOnce;

  public CodeFile(String filePath, T artifactToBind) {
    this(filePath, artifactToBind, false);
  }

  public CodeFile(String filePath, T artifactToBind, Boolean writeOnce) {
    super();
    this.setArtifactToBind(artifactToBind);
    this.setFilePath(filePath);
    this.setWriteOnce(writeOnce);
  }

  public void writeOn(CodeWriter w) {
    File file = new File(this.getFullPath());
    if (this.getWriteOnce() && file.exists()) {
      return;
    }
    try {
      w.setOutputStream(new FileOutputStream(file));
    } catch (IOException e) {
    }
    this.getArtifactToBind().writeOn(w);
  }

  private String getFullPath() {
    if (this.getFolderPath() != null) {
      return this.getFolderPath() + "/" + this.getFilePath();
    } else {
      return this.getFilePath();
    }
  }

  private void setArtifactToBind(T artifactToBind) {
    this.artifactToBind = artifactToBind;
  }

  private T getArtifactToBind() {
    return artifactToBind;
  }

  private void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getFilePath() {
    return filePath;
  }

  private void setWriteOnce(Boolean writeOnce) {
    this.writeOnce = writeOnce;
  }

  private Boolean getWriteOnce() {
    return writeOnce;
  }

  public final void setFolderPath(String fileFolder) {
    this.folderPath = fileFolder;
  }

  private String getFolderPath() {
    return folderPath;
  }

  public Boolean hasContentToWrite() {
    return true;
  }

  @Override
  public String toString() {
    return "File(" + this.getFilePath() + ")[" + this.getArtifactToBind() + "]";
  }
  
  

}
