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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.webspeclanguage.metamock.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.metamock.codegen.framework.core.CodeWriter;

/**
 * @author Jose Matias Rivero
 */
public class CodeBlock<T extends CodeArtifact> implements CodeArtifact {

  private List<T> content;

  public CodeBlock() {
    super();
    this.setContent(new ArrayList<T>());
  }

  public CodeBlock(List<T> artifacts) {
    this();
    this.setContent(artifacts);
  }

  public CodeBlock(T... artifacts) {
    this(new ArrayList<T>(Arrays.asList(artifacts)));
  }

  @SuppressWarnings("unchecked")
  public CodeBlock(String... lines) {
    this();
    for (String line : lines) {
      this.getContent().add((T) new Line(line));
    }
  }

  protected final void setContent(List<T> content) {
    this.content = content;
  }

  public final List<T> getContent() {
    return content;
  }

  public final void writeOn(CodeWriter w) {
    for (CodeArtifact a : this.getContent()) {
      a.writeOn(w);
    }
  }

  public final CodeBlock<T> add(T artifact) {
    this.getContent().add(artifact);
    return this;
  }

  @SuppressWarnings("unchecked")
  public CodeBlock<T> addLine(String text) {
    this.add((T) new Line(text));
    return this;
  }

  public final void addLines(String... lines) {
    for (String line : lines) {
      this.addLine(line);
    }
  }

  public final CodeArtifact addAll(List<T> artifacts) {
    for (T a : artifacts) {
      this.add(a);
    }
    return this;
  }

  public final void remove(T a) {
    this.getContent().remove(a);
  }

  public final T getLast() {
    return this.getContent().get(this.getContent().size() - 1);
  }

  public final Boolean hasContentToWrite() {
    for (CodeArtifact c : this.getContent()) {
      if (c.hasContentToWrite()) {
        return true;
      }
    }
    return false;
  }

  public final void addBefore(T artifact, T before) {
    this.getContent().add(this.getContent().indexOf(before) + 1, artifact);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    for (T artifact : this.getContent()) {
      sb.append(artifact.toString());
    }
    return sb.toString();
  }
  
  

}
