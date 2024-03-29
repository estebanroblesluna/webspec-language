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
import org.webspeclanguage.mockupdd.codegen.artifacts.CodeBlock;
import org.webspeclanguage.mockupdd.codegen.artifacts.CodeFile;
import org.webspeclanguage.mockupdd.codegen.common.DefaultWidgetGenerator;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayoutInfo;

/**
 * @author Jose Matias Rivero
 */
public class ExtJsCssGenerator extends DefaultWidgetGenerator<CodeArtifact> {

  public CodeFile<CodeArtifact> generateFrom(Page page) {
    return 
      Code.file(CodegenUtil.convertToIdentifier(page.getTitle()) + ".css",
        page.accept(this)
      );
  }

  @Override
  public CodeArtifact visitAbsoluteLayout(AbsoluteLayout absoluteLayout) {
    CodeBlock<CodeArtifact> cb = new CodeBlock<CodeArtifact>();
    for (AbsoluteLayoutInfo ali : absoluteLayout.getAllLayoutInfo()) {
      cb.add(ali.accept(this));
    }
    return cb;
  }

  @Override
  public CodeArtifact visitAbsoluteLayoutInfo(AbsoluteLayoutInfo ali) {
    return Code.mixedBlock(
      "#ctl" + ali.getWidget().getWidgetId() + "-container {",
      "    position: fixed;",
      "    left: " + ali.getX() + "px;",
      "    top: " + ali.getY() + "px;",
      "    width: " + ali.getWidth() + "px;",
      "    height: " + ali.getHeight() + "px;",
      "}",
      ali.getWidget().accept(this));
  }

  @Override
  public CodeArtifact getDefault() {
    return Code.nullCode();
  }

  @Override
  public CodeArtifact visitPage(Page page) {
    return Code.block(
            page.getLayout().accept(new PageCssGenerator(page)),
            page.getLayout().accept(this));
  }

  @Override
  public CodeArtifact visitPanel(Panel panel) {
    return panel.getLayout().accept(this);
  }
  
  private class PageCssGenerator extends DefaultWidgetGenerator<CodeArtifact> {

    private Page page;
    
    public PageCssGenerator(Page page) {
      super();
      this.page = page;
    }

    @Override
    public CodeArtifact visitAbsoluteLayout(AbsoluteLayout al) {
      return Code.mixedBlock(
        "body {",
        "    left: " + this.page.getX() + "px;",
        "    top: " + this.page.getY() + "px;",
        "    position: fixed;",
        "}");
    }
    
    @Override
    public CodeArtifact getDefault() {
      return Code.nullCode();
    }
    
  }
  
}
