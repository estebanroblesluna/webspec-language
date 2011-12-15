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
package org.webspeclanguage.mockupdd.codegen.generator.facade;

import java.io.FileWriter;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Collection;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.webspeclanguage.mockupdd.codegen.artifacts.CodeFileList;
import org.webspeclanguage.mockupdd.codegen.extjs.ExtJsGenerator;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeWriter;
import org.webspeclanguage.mockupdd.codegen.framework.core.DefaultCodeWriter;
import org.webspeclanguage.mockupdd.codegen.generator.FolderMockupCollector;
import org.webspeclanguage.mockupdd.codegen.generator.MockupCodeGenerator;
import org.webspeclanguage.mockupdd.codegen.generator.RegularExpressionFileFilter;
import org.webspeclanguage.mockupdd.codegen.web.MockupHtmlListGenerator;
import org.webspeclanguage.mockupdd.codegen.xml.DocumentFile;
import org.webspeclanguage.mockupdd.codegen.xml.MockupXmlGenerator;
import org.webspeclanguage.mockupdd.config.SuiDefaultConfig;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.translator.annotation.WidgetAnnotationParser;
import org.webspeclanguage.mockupdd.translator.balsamiq.BalsamiqWidgetParser;

/**
 * @author Jose Matias Rivero
 */
public class SuiCodeGenerationFacade {

  private static SuiCodeGenerationFacade instance;
  
  public static SuiCodeGenerationFacade getInstance() {
    if (instance == null) {
      instance = new SuiCodeGenerationFacade();      
    }
    return instance;
  }
  
  private SuiCodeGenerationFacade() {
  }

  public SuiModel balsamiq2ExtJs(String balsamiqMockupsFolder, String destinationFolder, String mockupListFolder, String mockupLocationPath, String extjsLibraryPath) throws Exception {
    CodeWriter cw = this.createCodeWriter();
    MockupCodeGenerator<String, CodeFileList<CodeArtifact>> codegen = this.createBalsamiq2ExtJsCodegen(balsamiqMockupsFolder, extjsLibraryPath);
    CodeFileList<CodeArtifact> artifacts = codegen.generateCodeArtifacts();
    artifacts.setFolderPathForFiles(destinationFolder);
    artifacts.writeOn(cw);
    
    artifacts = codegen.generateArtifacts(new MockupHtmlListGenerator(mockupLocationPath));
    artifacts.setFolderPathForFiles(mockupListFolder);
    artifacts.writeOn(cw);
    
    cw.flush();
    return codegen.getTranslatedModel();
  }
  
  public SuiModel balsamiq2Xml(String balsamiqMockupsFolder, String destinationFolder) throws Exception {
      MockupCodeGenerator<String, Collection<DocumentFile>> codegen = this.createBalsamiq2XmlCodegen(balsamiqMockupsFolder);
      Collection<DocumentFile> artifacts = codegen.generateCodeArtifacts();
      XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
      for (DocumentFile df : artifacts) {
        Writer w = new FileWriter(destinationFolder + "/" + df.getFilename());
        xo.output(df.getDocument(), w);
        w.close();
      }
      return codegen.getTranslatedModel();
  }
  
  private CodeWriter createCodeWriter() {
    return new DefaultCodeWriter();
  }
  
  public MockupCodeGenerator<String, CodeFileList<CodeArtifact>> createBalsamiq2ExtJsCodegen(String balsamiqMockupsFolder, String extjsLibraryPath) {
    return new MockupCodeGenerator<String, CodeFileList<CodeArtifact>>(
      this.getFactory(),
      new BalsamiqWidgetParser(this.getFactory()),
      this.getAnnotationParser(),
      new FolderMockupCollector(balsamiqMockupsFolder, new RegularExpressionFileFilter(".*\\.bmml")),
      new ExtJsGenerator(extjsLibraryPath));
  }

  private WidgetAnnotationParser getAnnotationParser() {
    return SuiDefaultConfig.getInstance().getAnnotationParser();
  }
  
  public MockupCodeGenerator<String, Collection<DocumentFile>> createBalsamiq2XmlCodegen(String balsamiqMockupsFolder) {
    return new MockupCodeGenerator<String, Collection<DocumentFile>>(
      this.getFactory(),
      new BalsamiqWidgetParser(this.getFactory()),
      this.getAnnotationParser(),
      new FolderMockupCollector(balsamiqMockupsFolder, new RegularExpressionFileFilter(".*\\.bmml")),
      new MockupXmlGenerator());
  }

  private SuiFactory getFactory() {
    return SuiDefaultConfig.getInstance().getFactory();
  }
}
