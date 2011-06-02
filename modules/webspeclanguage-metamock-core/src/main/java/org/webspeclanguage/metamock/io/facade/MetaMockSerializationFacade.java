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
package org.webspeclanguage.metamock.io.facade;

import java.util.Arrays;

import org.webspeclanguage.metamock.codegen.generator.Mockup;
import org.webspeclanguage.metamock.config.MetaMockDefaultConfig;
import org.webspeclanguage.metamock.io.MetaMockXmlSerializer;
import org.webspeclanguage.metamock.model.MetaMockFactory;
import org.webspeclanguage.metamock.translator.MetaMockProcessingEngine;
import org.webspeclanguage.metamock.translator.MetaMockTranslationException;
import org.webspeclanguage.metamock.translator.MockupContainerInfo;
import org.webspeclanguage.metamock.translator.MockupSourceParsingException;
import org.webspeclanguage.metamock.translator.annotation.MetaMockControlAnnotationParser;
import org.webspeclanguage.metamock.translator.balsamiq.BalsamiqControlParser;

/**
 * @author Jose Matias Rivero
 */
public class MetaMockSerializationFacade {

  private static MetaMockSerializationFacade instance;
  public static MetaMockSerializationFacade getInstance() {
    if (instance == null) {
      instance = new MetaMockSerializationFacade();
    }
    return instance;
  }
  
  private MetaMockXmlSerializer xmlSerializer;
  private MetaMockProcessingEngine<String> balsamiqToXmlEngine;
  
  private MetaMockSerializationFacade() {
    super();
    this.xmlSerializer = new MetaMockXmlSerializer();
    this.balsamiqToXmlEngine = new MetaMockProcessingEngine<String>(new BalsamiqControlParser(this.getFactory()), this.getAnnotationParser(), this.getFactory());   
  }

  private MetaMockControlAnnotationParser getAnnotationParser() {
    return MetaMockDefaultConfig.getInstance().getAnnotationParser();
  }

  private MetaMockFactory getFactory() {
    return MetaMockDefaultConfig.getInstance().getFactory();
  }

  public String balsamiqSourceToXml(String balsamiqSource) {
    try {
      return this.xmlSerializer.serialize(
        this.balsamiqToXmlEngine.translateModelFrom(
          Arrays.asList(new Mockup<String>(balsamiqSource, new MockupContainerInfo("web-service")))));
    } catch (MetaMockTranslationException e) {
      return this.createError("Error in translating mockup: " + e.getMessage());
    } catch (MockupSourceParsingException e) {
      return this.createError("Error in parsing mockup source: " + e.getCause().getMessage());
    }
  }

  private String createError(String string) {
    return "<error>" + string + "</error>";
  }
}
