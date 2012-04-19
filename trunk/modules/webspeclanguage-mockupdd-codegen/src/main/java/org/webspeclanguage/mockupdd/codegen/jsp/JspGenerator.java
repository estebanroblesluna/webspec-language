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
package org.webspeclanguage.mockupdd.codegen.jsp;

import org.webspeclanguage.mockupdd.codegen.artifacts.Code;
import org.webspeclanguage.mockupdd.codegen.artifacts.CodeFileList;
import org.webspeclanguage.mockupdd.codegen.common.SuiCodeGenerator;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;

/**
 * @author Soledad Palacios, Marga Kitagawa
 */
public class JspGenerator implements SuiCodeGenerator<CodeArtifact> {

  private String libraryPath;

  public JspGenerator() {
    this("..");
  }

  public JspGenerator(String libraryPath) {
    super();
    this.libraryPath = libraryPath;
  }

  @SuppressWarnings("unchecked")
  public CodeFileList<CodeArtifact> generateFrom(SuiModel m) {
    CodeFileList<CodeArtifact> fileList = Code.fileList();

    for (Page p : m.getPages()) {
      fileList.combine(generatePage(p));
    }
    return fileList;
  }

  @SuppressWarnings("unchecked")
  private CodeFileList<CodeArtifact> generatePage(Page p) {
    String title = CodegenUtil.convertToIdentifier(p.getTitle());
    String escapedTitle = CodegenUtil.escapeExcludingBlanks(p.getTitle());
    return Code.fileList(Code.file(title + ".jsp", (CodeArtifact) Code.mixedBlock(
            "<%@ page language=\"java\" contentType=\"text/html; charset=ISO-8859-1\" pageEncoding=\"ISO-8859-1\"%>", "<%@ page import=\"java.util.*\" %>",

            "<!doctype html>", "<html>", "  <head>", "    <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">", "    <title>"
                    + escapedTitle + "</title>", "  </head>", "  <body>", Code.indent(new JspBodyGenerator().generateFor(p), 2), "  </body>", "</html>")));
  }

}
