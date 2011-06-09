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
package org.webspeclanguage.metamock.translator;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.webspeclanguage.metamock.codegen.generator.Mockup;
import org.webspeclanguage.metamock.model.SuiModel;
import org.webspeclanguage.metamock.model.SuiTestCase;
import org.webspeclanguage.metamock.translator.annotation.WidgetAnnotationParser;
import org.webspeclanguage.metamock.utils.Null;
import org.webspeclanguage.metamock.utils.Pair;

/**
 * @author Jose Matias Rivero
 */
public abstract class SuiProcessingEngineTestCase extends SuiTestCase {

  private MockupProcessingEngine<Null> engine;
  private Mockup<Null> mockup; 
  
  private void setEngine(MockupProcessingEngine<Null> engine) {
    this.engine = engine;
  }

  private MockupProcessingEngine<Null> getEngine() {
    return engine;
  }

  private void setMockup(Mockup<Null> mockup) {
    this.mockup = mockup;
  }

  private Mockup<Null> getMockup() {
    return mockup;
  }

  protected void initializeProcessingEngine(Collection<WidgetGroup> groups) throws Exception {
    Pair<WidgetParser<Null>, WidgetAnnotationParser> p = this.initializeProcessingEngineWithoutReplay(groups);
    replay(p.getObject1());
    replay(p.getObject2());
  }

  @SuppressWarnings("unchecked")
  protected Pair<WidgetParser<Null>, WidgetAnnotationParser> initializeProcessingEngineWithoutReplay(Collection<WidgetGroup> groups) throws Exception {
    this.setMockup(new Mockup<Null>(Null.value(), (new MockupContainerInfo("containerInfo"))));
    WidgetParser<Null> cp = createMock(WidgetParser.class);
    WidgetAnnotationParser cap = createMock(WidgetAnnotationParser.class);
    this.setEngine(new MockupProcessingEngine<Null>(cp, cap, this.getFactory()));
    expect(cp.parseControls(Null.value())).andStubReturn(groups);
    return new Pair<WidgetParser<Null>, WidgetAnnotationParser>(cp, cap);
  }

  protected SuiModel getRawModel() throws SuiTranslationException, MockupSourceParsingException {
    return this.getEngine().getRawModel(Arrays.asList(this.getMockup()));
  }

}
