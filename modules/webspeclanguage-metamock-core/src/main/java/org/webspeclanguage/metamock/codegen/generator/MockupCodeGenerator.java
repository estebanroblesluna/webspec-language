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
import java.util.Collection;
import java.util.List;

import org.webspeclanguage.metamock.codegen.common.MetaMockCodeGenerator;
import org.webspeclanguage.metamock.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.metamock.model.MetaMockFactory;
import org.webspeclanguage.metamock.model.MetaMockModel;
import org.webspeclanguage.metamock.translator.MetaMockControlParser;
import org.webspeclanguage.metamock.translator.MetaMockProcessingEngine;
import org.webspeclanguage.metamock.translator.MetaMockTranslationException;
import org.webspeclanguage.metamock.translator.MockupSourceParsingException;
import org.webspeclanguage.metamock.translator.annotation.MetaMockControlAnnotationParser;
import org.webspeclanguage.metamock.translator.logger.MetaMockLogging;

/**
 * @author Jose Matias Rivero
 */
public class MockupCodeGenerator<TMockupRepresentation, TGeneratedArtifact> {

  private MockupCollector<TMockupRepresentation> mockupCollector;
  private MetaMockCodeGenerator<TGeneratedArtifact> codeGenerator;
  private MetaMockProcessingEngine<TMockupRepresentation> processingEngine;
  private MetaMockModel translatedModel;

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
  
  private MetaMockModel getTranslatedModel() throws MetaMockTranslationException, MockupSourceParsingException {
    if (this.translatedModel == null) {
      List<Mockup<TMockupRepresentation>> mockups = this.getMockupCollector().collectMockups();
      translatedModel = this.getProcessingEngine().translateModelFrom(mockups);
    }
    return translatedModel;
  }

  public TGeneratedArtifact generateCodeArtifacts() {
    return this.generateArtifacts(this.getCodeGenerator());
  }
  
  public TGeneratedArtifact generateArtifacts(MetaMockCodeGenerator<TGeneratedArtifact> generator) {
    try {
        return generator.generateFrom(this.getTranslatedModel());
    } catch (MetaMockTranslationException e) {
      MetaMockLogging.getDefaultLogger().logTranslationException(e);
    } catch (MockupSourceParsingException e) {
      MetaMockLogging.getDefaultLogger().logSourceParsingException(e);
    }
    return null;
  }
  
  

}
