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

/**
 * @author Jose Matias Rivero
 */
public final class Code {

  private Code() {
  }

  public static CodeBlock<Line> lBlock(String... lines) {
    return new CodeBlock<Line>(lines);
  }

  public static <T extends CodeArtifact> CodeFile<T> file(String filePath, T code) {
    return new CodeFile<T>(filePath, code);
  }

  public static Line line(String line) {
    return new Line(line);
  }

  public static <T extends CodeArtifact> CodeBlock<T> block(T... artifacts) {
    return new CodeBlock<T>(artifacts);
  }

  public static CodeBlock<CodeArtifact> block() {
    return new CodeBlock<CodeArtifact>();
  }

  public static <T extends CodeArtifact> IndentedBlock<T> indent(T artifact, Integer indent) {
    return new IndentedBlock<T>(artifact, indent);
  }

  public static NullCode nullCode() {
    return new NullCode();
  }

  public static <T1 extends CodeArtifact, T2 extends CodeArtifact> DelimitedList<T1, T2> list(T1 separator, T2... list) {
    return new DelimitedList<T1, T2>(separator, list);
  }

  public static <T extends CodeArtifact> DelimitedList<Text, T> list(String separator, T... list) {
    return new DelimitedList<Text, T>(new Text(separator), list);
  }

  public static Text text(String text) {
    return new Text(text);
  }

  public static <T extends CodeArtifact> CodeFile<T> newFile(String string, T content) {
    return new CodeFile<T>(string, content, true);
  }

  public static <T extends CodeArtifact> CodeBlock<T> block(List<T> artifacts) {
    return new CodeBlock<T>(artifacts);
  }

  public static CodeArtifact line() {
    return new Line("");
  }

  public static <T extends CodeArtifact> ConditionalBlock<T> iif(Boolean write, T content) {
    return new ConditionalBlock<T>(write, content);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static CodeBlock<CodeArtifact> mixedBlock(Object... elements) {
    CodeBlock<CodeArtifact> cb = new CodeBlock<CodeArtifact>();
    for (Object o : elements) {
      if (o instanceof CodeArtifact) {
        cb.add((CodeArtifact) o);
      } else if (o instanceof CodeArtifact[]) {
        cb.addAll(Arrays.asList((CodeArtifact[]) o));
      } else if (o instanceof List) {
        cb.addAll((List) o);
      } else {
        cb.add(new Line(o.toString()));
      }
    }
    return cb;
  }

  public static <T extends CodeArtifact> CodeFileList<T> fileList(CodeFile<T>... files) {
    return new CodeFileList<T>(Arrays.asList(files));
  }

}
