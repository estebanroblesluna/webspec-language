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

import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.impl.SuiFactoryImpl;
import org.webspeclanguage.mockupdd.translator.annotation.JsonAnnotationParser;
import org.webspeclanguage.mockupdd.translator.annotation.WidgetAnnotationParser;

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
  
  private SuiDefaultConfig() {
    this.setFactory(new SuiFactoryImpl());
    this.setAnnotationParser(new JsonAnnotationParser(this.getFactory()));
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
  
  
  
}
