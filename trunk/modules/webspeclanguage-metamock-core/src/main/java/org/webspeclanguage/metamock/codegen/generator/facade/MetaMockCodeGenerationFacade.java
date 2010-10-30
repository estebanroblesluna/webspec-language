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

import java.io.File;
import java.util.List;

import org.webspeclanguage.metamock.codegen.artifacts.CodeFileList;
import org.webspeclanguage.metamock.codegen.extjs.ExtJsGenerator;
import org.webspeclanguage.metamock.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.metamock.codegen.framework.core.CodeWriter;
import org.webspeclanguage.metamock.codegen.framework.core.DefaultCodeWriter;
import org.webspeclanguage.metamock.codegen.generator.FolderMockupCollector;
import org.webspeclanguage.metamock.codegen.generator.MockupCodeGenerator;
import org.webspeclanguage.metamock.codegen.generator.RegularExpressionFileFilter;
import org.webspeclanguage.metamock.model.MetaMockFactory;
import org.webspeclanguage.metamock.model.impl.MetaMockFactoryImpl;
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
  
  public MetaMockCodeGenerationFacade() {
    super();
    this.setMetaMockFactory(new MetaMockFactoryImpl());
  }

  public void balsamiq2ExtJs(String balsamiqMockupsFolder, String destinationFolder) {
    CodeWriter cw = this.createCodeWriter();
    List<CodeFileList<CodeArtifact>> artifacts = this.createBalsamiq2ExtJsCodegen(balsamiqMockupsFolder).generateCodeArtifacts();
    for (CodeFileList<CodeArtifact> ca : artifacts) {
      ca.setFolderPathForFiles(destinationFolder);
      ca.writeOn(cw);
    }  
    cw.flush();
  }

  private DefaultCodeWriter createCodeWriter() {
    return new DefaultCodeWriter();
  }
  
  public MockupCodeGenerator<File, CodeFileList<CodeArtifact>> createBalsamiq2ExtJsCodegen(String balsamiqMockupsFolder) {
    return new MockupCodeGenerator<File, CodeFileList<CodeArtifact>>(
      this.getMetaMockFactory(),
      new BalsamiqControlParser(this.getMetaMockFactory()),
      new MetaMockJsonAnnotationParser(this.getMetaMockFactory()),
      new FolderMockupCollector(balsamiqMockupsFolder, new RegularExpressionFileFilter(".*\\.bmml")),
      new ExtJsGenerator());
  }

  private void setMetaMockFactory(MetaMockFactory factory) {
    this.metaMockFactory = factory;
  }

  private MetaMockFactory getMetaMockFactory() {
    return metaMockFactory;
  }
}
