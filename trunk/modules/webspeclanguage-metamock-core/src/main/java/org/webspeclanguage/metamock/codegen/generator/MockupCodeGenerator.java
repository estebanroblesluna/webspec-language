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
package org.webspeclanguage.metamock.codegen.generator;

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.metamock.codegen.common.MetaMockCodeGenerator;
import org.webspeclanguage.metamock.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.metamock.codegen.framework.core.CodeWriter;
import org.webspeclanguage.metamock.model.MetaMockFactory;
import org.webspeclanguage.metamock.translator.MetaMockControlParser;
import org.webspeclanguage.metamock.translator.MetaMockProcessingEngine;
import org.webspeclanguage.metamock.translator.MetaMockTranslationException;
import org.webspeclanguage.metamock.translator.annotation.MetaMockControlAnnotationParser;

/**
 * @author Jose Matias Rivero
 */
public class MockupCodeGenerator<TMockupRepresentation, TGeneratedArtifact extends CodeArtifact> {

  private MockupCollector<TMockupRepresentation> mockupCollector;
  private MetaMockCodeGenerator<TGeneratedArtifact> codeGenerator;
  private MetaMockProcessingEngine<TMockupRepresentation> processingEngine;

  public MockupCodeGenerator(MetaMockFactory factory, MetaMockControlParser<TMockupRepresentation> controlParser,
          MetaMockControlAnnotationParser controlAnnotationParser, MockupCollector<TMockupRepresentation> mockupCollector,
          MetaMockCodeGenerator<TGeneratedArtifact> codeGenerator) {
    super();
    this.setMockupCollector(mockupCollector);
    this.setCodeGenerator(codeGenerator);
    this.setProcessingEngine(new MetaMockProcessingEngine<TMockupRepresentation>(controlParser, controlAnnotationParser, factory));
  }

  private void setMockupCollector(MockupCollector<TMockupRepresentation> mockupCollector) {
    this.mockupCollector = mockupCollector;
  }

  private MockupCollector<TMockupRepresentation> getMockupCollector() {
    return mockupCollector;
  }

  private void setCodeGenerator(MetaMockCodeGenerator<TGeneratedArtifact> codeGenerator) {
    this.codeGenerator = codeGenerator;
  }

  private MetaMockCodeGenerator<TGeneratedArtifact> getCodeGenerator() {
    return codeGenerator;
  }

  private void setProcessingEngine(MetaMockProcessingEngine<TMockupRepresentation> processingEngine) {
    this.processingEngine = processingEngine;
  }

  private MetaMockProcessingEngine<TMockupRepresentation> getProcessingEngine() {
    return processingEngine;
  }

  public List<TGeneratedArtifact> generateCodeArtifacts() {
    List<TGeneratedArtifact> artifacts = new ArrayList<TGeneratedArtifact>();
    try {
      List<Mockup<TMockupRepresentation>> mockups = this.getMockupCollector().collectMockups();
      for (Mockup<TMockupRepresentation> mockup : mockups) {
        artifacts.add(this.getCodeGenerator().generateFrom(this.getProcessingEngine().translateModelFrom(mockup)));
      }
    } catch (MetaMockTranslationException e) {
    }
    return artifacts;
  }

  public void generateAndWrite(CodeWriter cw) {
    for (TGeneratedArtifact codeArtifact : this.generateCodeArtifacts()) {
      codeArtifact.writeOn(cw);
    }
  }

}
