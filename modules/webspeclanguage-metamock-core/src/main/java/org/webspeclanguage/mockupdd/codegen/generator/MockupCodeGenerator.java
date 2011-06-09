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
package org.webspeclanguage.mockupdd.codegen.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.webspeclanguage.mockupdd.codegen.common.SuiCodeGenerator;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.translator.MockupProcessingEngine;
import org.webspeclanguage.mockupdd.translator.MockupSourceParsingException;
import org.webspeclanguage.mockupdd.translator.SuiTranslationException;
import org.webspeclanguage.mockupdd.translator.WidgetParser;
import org.webspeclanguage.mockupdd.translator.annotation.WidgetAnnotationParser;
import org.webspeclanguage.mockupdd.translator.logger.SuiLogging;

/**
 * @author Jose Matias Rivero
 */
public class MockupCodeGenerator<TMockupRepresentation, TGeneratedArtifact> {

  private MockupCollector<TMockupRepresentation> mockupCollector;
  private SuiCodeGenerator<TGeneratedArtifact> codeGenerator;
  private MockupProcessingEngine<TMockupRepresentation> processingEngine;
  private SuiModel translatedModel;

  public MockupCodeGenerator(SuiFactory factory, WidgetParser<TMockupRepresentation> widgetParser,
          WidgetAnnotationParser widgetAnnotationParser, MockupCollector<TMockupRepresentation> mockupCollector,
          SuiCodeGenerator<TGeneratedArtifact> codeGenerator) {
    super();
    this.setMockupCollector(mockupCollector);
    this.setCodeGenerator(codeGenerator);
    this.setProcessingEngine(new MockupProcessingEngine<TMockupRepresentation>(widgetParser, widgetAnnotationParser, factory));
  }

  private void setMockupCollector(MockupCollector<TMockupRepresentation> mockupCollector) {
    this.mockupCollector = mockupCollector;
  }

  private MockupCollector<TMockupRepresentation> getMockupCollector() {
    return mockupCollector;
  }

  private void setCodeGenerator(SuiCodeGenerator<TGeneratedArtifact> codeGenerator) {
    this.codeGenerator = codeGenerator;
  }

  private SuiCodeGenerator<TGeneratedArtifact> getCodeGenerator() {
    return codeGenerator;
  }

  private void setProcessingEngine(MockupProcessingEngine<TMockupRepresentation> processingEngine) {
    this.processingEngine = processingEngine;
  }

  private MockupProcessingEngine<TMockupRepresentation> getProcessingEngine() {
    return processingEngine;
  }
  
  private SuiModel getTranslatedModel() throws SuiTranslationException, MockupSourceParsingException {
    if (this.translatedModel == null) {
      List<Mockup<TMockupRepresentation>> mockups = this.getMockupCollector().collectMockups();
      translatedModel = this.getProcessingEngine().translateModelFrom(mockups);
    }
    return translatedModel;
  }

  public TGeneratedArtifact generateCodeArtifacts() {
    return this.generateArtifacts(this.getCodeGenerator());
  }
  
  public TGeneratedArtifact generateArtifacts(SuiCodeGenerator<TGeneratedArtifact> generator) {
    try {
        return generator.generateFrom(this.getTranslatedModel());
    } catch (SuiTranslationException e) {
      SuiLogging.getDefaultLogger().logTranslationException(e);
    } catch (MockupSourceParsingException e) {
      SuiLogging.getDefaultLogger().logSourceParsingException(e);
    }
    return null;
  }
  
  

}
