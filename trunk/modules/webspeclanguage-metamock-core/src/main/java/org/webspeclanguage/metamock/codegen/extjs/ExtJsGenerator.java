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
package org.webspeclanguage.metamock.codegen.extjs;

import org.webspeclanguage.metamock.codegen.artifacts.Code;
import org.webspeclanguage.metamock.codegen.artifacts.CodeFileList;
import org.webspeclanguage.metamock.codegen.artifacts.CodeFile;
import org.webspeclanguage.metamock.codegen.common.MetaMockCodeGenerator;
import org.webspeclanguage.metamock.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.metamock.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.metamock.model.MetaMockModel;
import org.webspeclanguage.metamock.model.Page;

/**
 * @author Jose Matias Rivero
 */
public class ExtJsGenerator implements
		MetaMockCodeGenerator<CodeFileList<CodeArtifact>> {

  @SuppressWarnings("unchecked")
  public CodeFileList<CodeArtifact> generateFrom(MetaMockModel m) {
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
          "    <script type=\"text/javascript\" src=\"../extjs/adapter/ext/ext-base-debug.js\"></script>",
          "    <script type=\"text/javascript\" src=\"../extjs/ext-all-debug.js\"></script>",
          "    <link rel=\"stylesheet\" type=\"text/css\" href=\"../extjs/resources/css/ext-all.css\"></link>",
          "    <link rel=\"stylesheet\" type=\"text/css\" href=\"../extjs/ux/css/ux-all.css\"></link>",
          "    <link rel=\"stylesheet\" type=\"text/css\" href=\"../style/custom.css\"></link>",
          "    <link rel=\"stylesheet\" type=\"text/css\" href=\"" + title + ".css\"></link>",
          "    <script type=\"text/javascript\" src=\"../extjs/ux/ux-all.js\"></script>",
          "    <script type=\"text/javascript\" src=\"../extjs/ux/css/ux-all.css\"></script>",
          "    <script type=\"text/javascript\" src=\"" + jsFile.getFilePath() + "\"></script>",
          "    <script type=\"text/javascript\" src=\"../scripts/sampleStore.js\"></script>",
          "  </head>",
    		  "  <body>",
    		  Code.indent(new ExtJsHtmlGenerator().generateFor(p), 2),
    			"  </body>",
    			"</html>"))	
    );
  }

}
