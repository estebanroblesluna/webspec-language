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
package org.webspeclanguage.mockupdd.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.webspeclanguage.mockupdd.sui.model.Annotation;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Link;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SelectionWidget;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.TriggerWidget;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.impl.SuiFactoryImpl;
import org.webspeclanguage.mockupdd.sui.model.layout.LayoutFactory;
import org.webspeclanguage.mockupdd.sui.model.layout.impl.ScanBasedGridBagLayoutFactory;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameter;
import org.webspeclanguage.mockupdd.sui.model.tags.TagSet;
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
 * @author Jose Matias Rivero
 */
public class SuiDefaultConfig {

  private static SuiDefaultConfig instance;
  public static SuiDefaultConfig getInstance() {
    if (instance == null) {
      instance = new SuiDefaultConfig();
    }
    return instance;
  }

  private SuiFactory factory;
  private WidgetAnnotationParser annotationParser;
  private Map<String, TagSet> tagSetsByName;
  private List<MockupProcessor> mockupProcessors;
  private List<MockupProcessor> mockupPostProcessors;
  private LayoutFactory defaultLayoutFactory;
  
  private SuiDefaultConfig() {
    this.setFactory(new SuiFactoryImpl());
    this.setAnnotationParser(new JsonAnnotationParser(this.getFactory()));
    this.setMockupProcessors(this.initializeMockupProcessors());
    this.setMockupPostProcessors(this.initializeMockupPostProcessors());
    this.initializeTagSets();
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
            new RepetitionDetection(new DefaultRepetitionDetectorImpl(this.getFactory()))            
    );
  }

  @SuppressWarnings("unchecked")
  private void initializeTagSets() {
    this.tagSetsByName = new HashMap<String, TagSet>();
    this.tagSetsByName.put("Nav", 
      this.getFactory().createTagSet("Nav",
              this.getFactory().createTag("Link", 
                      Arrays.asList(this.getFactory().createTagParameter("nodeId")),
                      Button.class, Link.class),
              this.getFactory().createTag("Node", 
                      Arrays.asList(this.getFactory().createTagParameter("nodeId")),
                      Page.class),
              this.getFactory().createTag("Select", 
                      Arrays.asList(new TagParameter[]{}),
                      SelectionWidget.class), 
              this.getFactory().createTag("Transfer", 
                      Arrays.asList(this.getFactory().createTagParameter("types")),
                      TriggerWidget.class),
              this.getFactory().createTag("Action", 
                      Arrays.asList(this.getFactory().createTagParameter("actionDescription")),
                      TriggerWidget.class)
      ));
    this.tagSetsByName.put("Data", 
      this.getFactory().createTagSet("Data",
              this.getFactory().createTag("Data", 
                      Arrays.asList(this.getFactory().createTagParameter("typeId")),
                      Widget.class),
              this.getFactory().createTag("Save", 
                      Arrays.asList(this.getFactory().createTagParameter("types")),
                      TriggerWidget.class),
              this.getFactory().createTag("Delete", 
                      Arrays.asList(this.getFactory().createTagParameter("types")),
                      TriggerWidget.class),
              this.getFactory().createTag("Associate", 
                      Arrays.asList(this.getFactory().createTagParameter("type1"), this.getFactory().createTagParameter("type2")),
                      TriggerWidget.class),
              this.getFactory().createTag("Dissociate", 
                      Arrays.asList(this.getFactory().createTagParameter("type1"), this.getFactory().createTagParameter("type2")),
                      TriggerWidget.class),
              this.getFactory().createTag("Query", 
                      Arrays.asList(this.getFactory().createTagParameter("type"), this.getFactory().createTagParameter("queryDescription")),
                      CompositeWidget.class)
    ));
  }

  private void setFactory(SuiFactory factory) {
    this.factory = factory;
  }

  public SuiFactory getFactory() {
    return factory;
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

  public TagSet getTagSetByName(String tagSetName) {
    return this.tagSetsByName.get(tagSetName);
  }
  
  @SuppressWarnings("unchecked")
  public Class<? extends Widget>[] getExcludedWidgets() {
    return new Class[]{Annotation.class};
  }

}
