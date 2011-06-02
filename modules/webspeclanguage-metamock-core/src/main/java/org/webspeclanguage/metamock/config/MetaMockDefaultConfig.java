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
package org.webspeclanguage.metamock.config;

import org.webspeclanguage.metamock.model.MetaMockFactory;
import org.webspeclanguage.metamock.model.impl.MetaMockFactoryImpl;
import org.webspeclanguage.metamock.translator.annotation.MetaMockControlAnnotationParser;
import org.webspeclanguage.metamock.translator.annotation.MetaMockJsonAnnotationParser;

/**
 * @author Jose Matias Rivero
 */
public class MetaMockDefaultConfig {

  private static MetaMockDefaultConfig instance;
  public static MetaMockDefaultConfig getInstance() {
    if (instance == null) {
      instance = new MetaMockDefaultConfig();
    }
    return instance;
  }

  private MetaMockFactory factory;
  private MetaMockControlAnnotationParser annotationParser;
  
  private MetaMockDefaultConfig() {
    this.setFactory(new MetaMockFactoryImpl());
    this.setAnnotationParser(new MetaMockJsonAnnotationParser(this.getFactory()));
  }

  private void setFactory(MetaMockFactory factory) {
    this.factory = factory;
  }

  public MetaMockFactory getFactory() {
    return factory;
  }

  private void setAnnotationParser(MetaMockControlAnnotationParser annotationParser) {
    this.annotationParser = annotationParser;
  }

  public MetaMockControlAnnotationParser getAnnotationParser() {
    return annotationParser;
  }
  
  
  
}
