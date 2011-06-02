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
package org.webspeclanguage.metamock.codegen.generator.facade;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.webspeclanguage.metamock.codegen.artifacts.CodeFileList;
import org.webspeclanguage.metamock.codegen.extjs.ExtJsGenerator;
import org.webspeclanguage.metamock.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.metamock.codegen.framework.core.CodeWriter;
import org.webspeclanguage.metamock.codegen.framework.core.DefaultCodeWriter;
import org.webspeclanguage.metamock.codegen.generator.FolderMockupCollector;
import org.webspeclanguage.metamock.codegen.generator.MockupCodeGenerator;
import org.webspeclanguage.metamock.codegen.generator.RegularExpressionFileFilter;
import org.webspeclanguage.metamock.codegen.web.MockupHtmlListGenerator;
import org.webspeclanguage.metamock.codegen.xml.DocumentFile;
import org.webspeclanguage.metamock.codegen.xml.MockupXmlGenerator;
import org.webspeclanguage.metamock.config.MetaMockDefaultConfig;
import org.webspeclanguage.metamock.model.MetaMockFactory;
import org.webspeclanguage.metamock.translator.annotation.MetaMockControlAnnotationParser;
import org.webspeclanguage.metamock.translator.annotation.MetaMockJsonAnnotationParser;
import org.webspeclanguage.metamock.translator.balsamiq.BalsamiqControlParser;

/**
 * @author Jose Matias Rivero
 */
public class MetaMockCodeGenerationFacade {

  private MetaMockFactory metaMockFactory;
  private static MetaMockCodeGenerationFacade instance;
  
  public static MetaMockCodeGenerationFacade getInstance() {
    if (instance == null) {
      instance = new MetaMockCodeGenerationFacade();      
    }
    return instance;
  }
  
  private MetaMockCodeGenerationFacade() {
  }

  public void balsamiq2ExtJs(String balsamiqMockupsFolder, String destinationFolder, String mockupListFolder, String mockupLocationPath) {
    CodeWriter cw = this.createCodeWriter();
    MockupCodeGenerator<String, CodeFileList<CodeArtifact>> codegen = this.createBalsamiq2ExtJsCodegen(balsamiqMockupsFolder);
    CodeFileList<CodeArtifact> artifacts = codegen.generateCodeArtifacts();
    artifacts.setFolderPathForFiles(destinationFolder);
    artifacts.writeOn(cw);
    
    artifacts = codegen.generateArtifacts(new MockupHtmlListGenerator(mockupLocationPath));
    artifacts.setFolderPathForFiles(mockupListFolder);
    artifacts.writeOn(cw);
    
    cw.flush();
  }
  
  public void balsamiq2Xml(String balsamiqMockupsFolder, String destinationFolder) {
    try {
      MockupCodeGenerator<String, Collection<DocumentFile>> codegen = this.createBalsamiq2XmlCodegen(balsamiqMockupsFolder);
      Collection<DocumentFile> artifacts = codegen.generateCodeArtifacts();
      XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
      for (DocumentFile df : artifacts) {
        Writer w = new FileWriter(destinationFolder + "/" + df.getFilename());
        xo.output(df.getDocument(), w);
        w.close();
      }
    } catch (IOException e) { }
  }

  private CodeWriter createCodeWriter() {
    return new DefaultCodeWriter();
  }
  
  public MockupCodeGenerator<String, CodeFileList<CodeArtifact>> createBalsamiq2ExtJsCodegen(String balsamiqMockupsFolder) {
    return new MockupCodeGenerator<String, CodeFileList<CodeArtifact>>(
      this.getMetaMockFactory(),
      new BalsamiqControlParser(this.getMetaMockFactory()),
      this.getAnnotationParser(),
      new FolderMockupCollector(balsamiqMockupsFolder, new RegularExpressionFileFilter(".*\\.bmml")),
      new ExtJsGenerator());
  }

  private MetaMockControlAnnotationParser getAnnotationParser() {
    return MetaMockDefaultConfig.getInstance().getAnnotationParser();
  }
  
  public MockupCodeGenerator<String, Collection<DocumentFile>> createBalsamiq2XmlCodegen(String balsamiqMockupsFolder) {
    return new MockupCodeGenerator<String, Collection<DocumentFile>>(
      this.getMetaMockFactory(),
      new BalsamiqControlParser(this.getMetaMockFactory()),
      this.getAnnotationParser(),
      new FolderMockupCollector(balsamiqMockupsFolder, new RegularExpressionFileFilter(".*\\.bmml")),
      new MockupXmlGenerator());
  }

  private MetaMockFactory getMetaMockFactory() {
    return MetaMockDefaultConfig.getInstance().getFactory();
  }
}
