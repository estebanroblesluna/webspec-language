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

import java.util.List;

import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;

/**
 * @author Jose Matias Rivero
 */
public interface CodeArtifactFactory {

  <T extends CodeArtifact> CodeFileList<T> fileList(CodeFile<T>... files);

  <T extends CodeArtifact> CodeBlock<T> block();

  <T extends CodeArtifact> CodeBlock<T> block(T... artifacts);

  <T extends CodeArtifact> CodeBlock<T> block(List<T> artifacts);

  CodeBlock<Line> lBlock(String... lines);

  <T extends CodeArtifact> IndentedBlock<T> indent(T artifact, Integer indent);

  Line line(String line);

  <T extends CodeArtifact> CodeFile<T> file(String filePath, T code);

  NullCode nullCode();

  <T1 extends CodeArtifact, T2 extends CodeArtifact> DelimitedList<T1, T2> list(T1 separator, T2... list);

  <T extends CodeArtifact> DelimitedList<Text, T> list(String separator, T... list);

  Text text(String string);

  <T extends CodeArtifact> CodeFile<T> newFile(String string, T content);

  CodeArtifact line();

  <T extends CodeArtifact> ConditionalBlock<T> iif(Boolean write, T content);

  CodeBlock<CodeArtifact> mixedBlock(Object... elements);

}
