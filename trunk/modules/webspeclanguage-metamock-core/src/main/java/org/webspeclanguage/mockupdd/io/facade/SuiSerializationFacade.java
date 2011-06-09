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
package org.webspeclanguage.mockupdd.io.facade;

import java.util.Arrays;

import org.webspeclanguage.mockupdd.codegen.generator.Mockup;
import org.webspeclanguage.mockupdd.config.SuiDefaultConfig;
import org.webspeclanguage.mockupdd.io.SuiXmlSerializer;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.translator.MockupContainerInfo;
import org.webspeclanguage.mockupdd.translator.MockupProcessingEngine;
import org.webspeclanguage.mockupdd.translator.MockupSourceParsingException;
import org.webspeclanguage.mockupdd.translator.SuiTranslationException;
import org.webspeclanguage.mockupdd.translator.annotation.WidgetAnnotationParser;
import org.webspeclanguage.mockupdd.translator.balsamiq.BalsamiqWidgetParser;

/**
 * @author Jose Matias Rivero
 */
public class SuiSerializationFacade {

  private static SuiSerializationFacade instance;
  public static SuiSerializationFacade getInstance() {
    if (instance == null) {
      instance = new SuiSerializationFacade();
    }
    return instance;
  }
  
  private SuiXmlSerializer xmlSerializer;
  private MockupProcessingEngine<String> balsamiqToXmlEngine;
  
  private SuiSerializationFacade() {
    super();
    this.xmlSerializer = new SuiXmlSerializer();
    this.balsamiqToXmlEngine = new MockupProcessingEngine<String>(new BalsamiqWidgetParser(this.getFactory()), this.getAnnotationParser(), this.getFactory());   
  }

  private WidgetAnnotationParser getAnnotationParser() {
    return SuiDefaultConfig.getInstance().getAnnotationParser();
  }

  private SuiFactory getFactory() {
    return SuiDefaultConfig.getInstance().getFactory();
  }

  public String balsamiqSourceToXml(String balsamiqSource) {
    try {
      return this.xmlSerializer.serialize(
        this.balsamiqToXmlEngine.translateModelFrom(
          Arrays.asList(new Mockup<String>(balsamiqSource, new MockupContainerInfo("web-service")))));
    } catch (SuiTranslationException e) {
      return this.createError("Error in translating mockup: " + e.getMessage());
    } catch (MockupSourceParsingException e) {
      return this.createError("Error in parsing mockup source: " + e.getCause().getMessage());
    }
  }

  private String createError(String string) {
    return "<error>" + string + "</error>";
  }
}
