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
package org.webspeclanguage.metamock.codegen.artifacts;

import java.util.Arrays;
import java.util.List;

import org.webspeclanguage.metamock.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.metamock.codegen.framework.core.CodeWriter;

/**
 * @author Jose Matias Rivero
 */
public class DelimitedList<TSep extends CodeArtifact, TList extends CodeArtifact> implements CodeArtifact {

  private CodeArtifact separator;
  private List<TList> list;

  public DelimitedList(TSep separator, TList... list) {
    super();
    this.setSeparator(separator);
    this.setList(Arrays.asList(list));
  }

  public static <T extends CodeArtifact> DelimitedList<Text, T> list(String textSeparator, T[] list) {
    return new DelimitedList<Text, T>(new Text(textSeparator), list);
  }

  private void setSeparator(CodeArtifact separator) {
    this.separator = separator;
  }

  private CodeArtifact getSeparator() {
    return separator;
  }

  private void setList(List<TList> list) {
    this.list = list;
  }

  private List<TList> getList() {
    return list;
  }

  public void writeOn(CodeWriter w) {
    Boolean first = true;
    for (CodeArtifact c : this.getList()) {
      if (!c.hasContentToWrite()) {
        continue;
      }
      if (first) {
        first = false;
      } else {
        this.getSeparator().writeOn(w);
      }
      c.writeOn(w);
    }

  }

  public Boolean hasContentToWrite() {
    for (CodeArtifact c : this.getList()) {
      if (c.hasContentToWrite()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    Boolean first = true;
    for (CodeArtifact c : this.getList()) {
      if (!c.hasContentToWrite()) {
        continue;
      }
      if (first) {
        first = false;
      } else {
        sb.append(this.getSeparator());
      }
      sb.append(c.toString());
    }
    return sb.toString();
  }

  
}
