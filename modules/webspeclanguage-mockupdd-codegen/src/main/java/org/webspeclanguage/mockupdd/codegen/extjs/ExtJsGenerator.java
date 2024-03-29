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
package org.webspeclanguage.mockupdd.codegen.extjs;

import org.webspeclanguage.mockupdd.codegen.artifacts.Code;
import org.webspeclanguage.mockupdd.codegen.artifacts.CodeFile;
import org.webspeclanguage.mockupdd.codegen.artifacts.CodeFileList;
import org.webspeclanguage.mockupdd.codegen.common.SuiCodeGenerator;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;

/**
 * @author Jose Matias Rivero
 */
public class ExtJsGenerator implements
		SuiCodeGenerator<CodeFileList<CodeArtifact>> {

  private String libraryPath;
  
  public ExtJsGenerator() {
    this("..");
  }
  
  public ExtJsGenerator(String libraryPath) {
    super();
    this.libraryPath = libraryPath;
  }

  @SuppressWarnings("unchecked")
  public CodeFileList<CodeArtifact> generateFrom(SuiModel m) {
	  CodeFileList<CodeArtifact> fileList = Code.fileList();
	  for (Page p: m.getPages()) {
  		fileList.combine(generatePage(p));
	  }
	  return fileList;
	}

  @SuppressWarnings("unchecked")
  private CodeFileList<CodeArtifact> generatePage(Page p) {
    String title = CodegenUtil.convertToIdentifier(p.getTitle());
    String escapedTitle = CodegenUtil.escapeExcludingBlanks(p.getTitle());
    CodeFile<CodeArtifact> jsFile = new ExtJsJavaScriptGenerator().generateFrom(p);
    CodeFile<CodeArtifact> cssFile = new ExtJsCssGenerator().generateFrom(p);
    return Code.fileList(
      jsFile,
      cssFile,
      Code.file(title + ".html",
        (CodeArtifact)
    	  Code.mixedBlock(
    		  "<!doctype html>",
    	    "<html>",
    		  "  <head>",
    		  "    <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">",
    		  "    <title>" + escapedTitle + "</title>",
          "    <script type=\"text/javascript\" src=\"" + this.libraryPath + "/extjs/adapter/ext/ext-base-debug.js\"></script>",
          "    <script type=\"text/javascript\" src=\"" + this.libraryPath + "/extjs/ext-all-debug.js\"></script>",
          "    <link rel=\"stylesheet\" type=\"text/css\" href=\"" + this.libraryPath + "/extjs/resources/css/ext-all.css\"></link>",
          "    <link rel=\"stylesheet\" type=\"text/css\" href=\"" + this.libraryPath + "/extjs/ux/css/ux-all.css\"></link>",
          "    <link rel=\"stylesheet\" type=\"text/css\" href=\"" + this.libraryPath + "/style/custom.css\"></link>",
          "    <link rel=\"stylesheet\" type=\"text/css\" href=\"" + title + ".css\"></link>",
          "    <script type=\"text/javascript\" src=\"" + this.libraryPath + "/extjs/ux/ux-all.js\"></script>",
          "    <script type=\"text/javascript\" src=\"" + this.libraryPath + "/extjs/ux/css/ux-all.css\"></script>",
          "    <script type=\"text/javascript\" src=\"" + jsFile.getFilePath() + "\"></script>",
          "    <script type=\"text/javascript\" src=\"" + this.libraryPath + "/scripts/sampleStore.js\"></script>",
          "  </head>",
    		  "  <body>",
    		  Code.indent(new ExtJsHtmlGenerator().generateFor(p), 2),
    			"  </body>",
    			"</html>"))	
    );
  }

}
