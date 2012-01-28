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

package org.webspeclanguage.mockupdd.translator.config;

import java.util.Arrays;
import java.util.List;

import org.webspeclanguage.mockupdd.config.SuiDefaultConfig;
import org.webspeclanguage.mockupdd.sui.model.Annotation;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.impl.ScanBasedGridBagLayoutFactory;
import org.webspeclanguage.mockupdd.translator.DefaultRepetitionDetectorImpl;
import org.webspeclanguage.mockupdd.translator.MockupProcessor;
import org.webspeclanguage.mockupdd.translator.annotation.JsonAnnotationParser;
import org.webspeclanguage.mockupdd.translator.annotation.WidgetAnnotationParser;
import org.webspeclanguage.mockupdd.translator.processors.AnnotationLinker;
import org.webspeclanguage.mockupdd.translator.processors.CollisionDetector;
import org.webspeclanguage.mockupdd.translator.processors.ExcludedWidgetCollector;
import org.webspeclanguage.mockupdd.translator.processors.HierarchiesDetector;
import org.webspeclanguage.mockupdd.translator.processors.LayoutInferrer;
import org.webspeclanguage.mockupdd.translator.processors.PageCollector;
import org.webspeclanguage.mockupdd.translator.processors.PageCreator;
import org.webspeclanguage.mockupdd.translator.processors.RepetitionDetection;
import org.webspeclanguage.mockupdd.translator.processors.WidgetRegistering;


/**
 * @author José Matías Rivero
 */
public class SuiTranslatorConfig {

  private static SuiTranslatorConfig instance;
  private WidgetAnnotationParser annotationParser;
  private List<MockupProcessor> mockupProcessors;
  private List<MockupProcessor> mockupPostProcessors;
  
  private SuiTranslatorConfig() {
    this.setAnnotationParser(new JsonAnnotationParser(SuiDefaultConfig.getInstance().getFactory()));
    this.setMockupProcessors(this.initializeMockupProcessors());
    this.setMockupPostProcessors(this.initializeMockupPostProcessors());
  }
  

  public static SuiTranslatorConfig getInstance() {
    if (instance == null) {
      instance = new SuiTranslatorConfig();
    }
    return instance;
  }
  
  private List<MockupProcessor> initializeMockupPostProcessors() {
    return Arrays.asList(
            (MockupProcessor)
            new LayoutInferrer(new ScanBasedGridBagLayoutFactory())
    );          
  }

  private List<MockupProcessor> initializeMockupProcessors() {
    return Arrays.asList(
            (MockupProcessor)
            new CollisionDetector(),
            new PageCreator(),
            new HierarchiesDetector(this.getExcludedWidgets()),
            new ExcludedWidgetCollector(this.getExcludedWidgets()),
            new PageCollector(),
            new AnnotationLinker(),
            new WidgetRegistering(),
            new RepetitionDetection(new DefaultRepetitionDetectorImpl(SuiDefaultConfig.getInstance().getFactory()))            
    );
  }
  
  private void setAnnotationParser(WidgetAnnotationParser annotationParser) {
    this.annotationParser = annotationParser;
  }

  public WidgetAnnotationParser getAnnotationParser() {
    return annotationParser;
  }
  
  private void setMockupProcessors(List<MockupProcessor> mockupProcessors) {
    this.mockupProcessors = mockupProcessors;
  }

  public List<MockupProcessor> getMockupProcessors() {
    return mockupProcessors;
  }

  private void setMockupPostProcessors(List<MockupProcessor> mockupPostProcessors) {
    this.mockupPostProcessors = mockupPostProcessors;
  }

  public List<MockupProcessor> getMockupPostProcessors() {
    return mockupPostProcessors;
  }
  
  @SuppressWarnings("unchecked")
  public Class<? extends Widget>[] getExcludedWidgets() {
    return new Class[]{Annotation.class};
  }


  
  
}
