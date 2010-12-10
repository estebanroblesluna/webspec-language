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

import java.util.Collection;

import org.webspeclanguage.metamock.model.MetaMockModel;
import org.webspeclanguage.metamock.model.MetaMockTestCase;
import org.webspeclanguage.metamock.translator.annotation.MetaMockControlAnnotationParser;
import org.webspeclanguage.metamock.utils.Null;
import org.webspeclanguage.metamock.utils.Pair;

/**
 * @author Jose Matias Rivero
 */
public abstract class MetaMockProcessingEngineTestCase extends MetaMockTestCase {

  private MetaMockProcessingEngine<Null> engine;

  private void setEngine(MetaMockProcessingEngine<Null> engine) {
    this.engine = engine;
  }

  private MetaMockProcessingEngine<Null> getEngine() {
    return engine;
  }

  protected void initializeProcessingEngine(Collection<MetaMockControlGroup> groups) {
    Pair<MetaMockControlParser<Null>, MetaMockControlAnnotationParser> p = this.initializeProcessingEngineWithoutReplay(groups);
    replay(p.getObject1());
    replay(p.getObject2());
  }

  @SuppressWarnings("unchecked")
  protected Pair<MetaMockControlParser<Null>, MetaMockControlAnnotationParser> initializeProcessingEngineWithoutReplay(Collection<MetaMockControlGroup> groups) {
    MetaMockControlParser<Null> cp = createMock(MetaMockControlParser.class);
    MetaMockControlAnnotationParser cap = createMock(MetaMockControlAnnotationParser.class);
    this.setEngine(new MetaMockProcessingEngine<Null>(cp, cap, this.getFactory()));
    expect(cp.parseControls(Null.value())).andStubReturn(groups);
    return new Pair<MetaMockControlParser<Null>, MetaMockControlAnnotationParser>(cp, cap);
  }

  protected MetaMockModel getRawModel() throws MetaMockTranslationException {
    MockupContainerInfo info = createMock(MockupContainerInfo.class);
    expect(info.getName()).andStubReturn("containerName");
    replay(info);
    return this.getEngine().getRawModel(Null.value(), info);
  }

}
