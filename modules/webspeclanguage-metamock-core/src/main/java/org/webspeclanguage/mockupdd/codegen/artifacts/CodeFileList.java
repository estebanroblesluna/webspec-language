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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeWriter;

/**
 * @author Jose Matias Rivero
 */
public class CodeFileList<T extends CodeArtifact> implements CodeArtifact {

  private List<CodeFile<T>> files;

  public CodeFileList(List<CodeFile<T>> files) {
    super();
    this.setFiles(new ArrayList<CodeFile<T>>(files));
  }

  public final Boolean hasContentToWrite() {
    for (CodeFile<T> f : this.getFiles()) {
      if (f.hasContentToWrite()) {
        return true;
      }
    }
    return false;
  }

  public final void writeOn(CodeWriter w) {
    for (CodeFile<T> f : this.getFiles()) {
      f.writeOn(w);
    }
  }

  private void setFiles(List<CodeFile<T>> files) {
    this.files = files;
  }

  private List<CodeFile<T>> getFiles() {
    return files;
  }

  public final void add(CodeFile<T> file) {
    this.getFiles().add(file);
  }

  public final void setFolderPathForFiles(String folderPath) {
    for (CodeFile<T> f : this.getFiles()) {
      f.setFolderPath(folderPath);
    }
  }

  public void addAll(CodeFile<T>... files) {
    this.addAll(Arrays.asList(files));
  }

  public void addAll(List<CodeFile<T>> files) {
    for (CodeFile<T> file : files) {
      this.getFiles().add(file);
    }
  }

  public void combine(CodeFileList<T> codeFileList) {
    this.addAll(codeFileList.getFiles());
  }

}
