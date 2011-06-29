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
package org.webspeclanguage.mockupdd.translator;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.webspeclanguage.mockupdd.codegen.generator.Mockup;
import org.webspeclanguage.mockupdd.config.SuiDefaultConfig;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.translator.annotation.WidgetAnnotationParser;

/**
 * Facade class for mockup processing.
 * 
 * @author Jose Matias Rivero
 */
public class MockupProcessingEngine<TSource> extends SuiTranslator<TSource> {

  private WidgetParser<TSource> parser;
  private SuiFactory factory;

  public MockupProcessingEngine(WidgetParser<TSource> parser, WidgetAnnotationParser annotationParser, SuiFactory factory) {
    super();
    this.setParser(parser);
    this.setFactory(factory);
  }

  private void setParser(WidgetParser<TSource> parser) {
    this.parser = parser;
  }

  private WidgetParser<TSource> getParser() {
    return parser;
  }

  private void setFactory(SuiFactory factory) {
    this.factory = factory;
  }

  public SuiFactory getFactory() {
    return factory;
  }

  @Override
  public SuiModel getRawModel(Collection<Mockup<TSource>> mockups) throws SuiTranslationException, MockupSourceParsingException {
    SuiModel model = this.getFactory().createSuiModel();
    for (Mockup<TSource> mockup : mockups) {
      for (WidgetGroup g : this.getParser().parseWidgets(mockup.getRepresentation())) {
        this.preprocessModel(g, model, mockup.getContainerInfo());
      }
    }
    return model;
  }

  @Override
  public SuiModel applyAnnotationsAndInferLayouts(SuiModel model) {
    try {
      this.applyProcessors(SuiDefaultConfig.getInstance().getMockupPostProcessors(), null, model, null);
    } catch (SuiTranslationException e) {
      e.printStackTrace();
    }
    return model;
  }

  private void preprocessModel(WidgetGroup group, SuiModel model, MockupContainerInfo info) throws SuiTranslationException {
    this.applyProcessors(SuiDefaultConfig.getInstance().getMockupProcessors(), group, model, info);
  }

  private void applyProcessors(List<MockupProcessor> processors, WidgetGroup group, SuiModel model, MockupContainerInfo info) throws SuiTranslationException {
    Iterator<MockupProcessor> i = processors.iterator();
    while (i.hasNext()) {
      i.next().process(group, model, info, this);
    }
  }

}
