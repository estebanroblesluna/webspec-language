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
package org.webspeclanguage.mockupdd.codegen.web;

import org.webspeclanguage.mockupdd.codegen.artifacts.Code;
import org.webspeclanguage.mockupdd.codegen.artifacts.CodeBlock;
import org.webspeclanguage.mockupdd.codegen.artifacts.CodeFileList;
import org.webspeclanguage.mockupdd.codegen.common.SuiCodeGenerator;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;

/**
 * @author Jose Matias Rivero
 */
public class MockupHtmlListGenerator implements SuiCodeGenerator<CodeFileList<CodeArtifact>> {

  private String folder;

  public MockupHtmlListGenerator(String folder) {
    super();
    this.folder = folder;
  }

  public CodeFileList<CodeArtifact> generateFrom(SuiModel model) {
    return 
      Code.fileList(
        Code.file("mockupList.html", 
          (CodeArtifact)
          Code.mixedBlock(
            "<ul>",
            Code.indent(this.createList(model), 1),
            "</ul>")));
  }

  private CodeBlock<CodeArtifact> createList(SuiModel model) {
      CodeBlock<CodeArtifact> block = Code.block();
      for (Page p : model.getPages()) {
        block.add(Code.line(
          "<li><a href=\"" + 
              this.folder + "/" +  
              CodegenUtil.convertToIdentifier(p.getTitle()) + ".html"
          + "\">" + p.getTitle() + "</a></li>"));
      }
      return block;
  }

}
