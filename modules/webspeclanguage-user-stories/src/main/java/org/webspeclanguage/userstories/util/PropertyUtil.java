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
package org.webspeclanguage.userstories.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Utility class to hold custom properties.
 * 
 * @author cristian.cianfagna
 */
public class PropertyUtil extends PropertyPlaceholderConfigurer {

  private static Map<String, String> properties;
  private int springSystemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;

  @Override
  public void setSystemPropertiesMode(int systemPropertiesMode) {
    super.setSystemPropertiesMode(systemPropertiesMode);
    springSystemPropertiesMode = systemPropertiesMode;
  }

  @Override
  protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
    super.processProperties(beanFactory, props);
    properties = new HashMap<String, String>();
    for (Object key : props.keySet()) {
      String keyStr = key.toString();
      String valueStr = resolvePlaceholder(keyStr, props, springSystemPropertiesMode);
      properties.put(keyStr, valueStr);
    }
  }

  public static String getProperty(String name) {
    if (properties.get(name) != null)
      return properties.get(name).toString();
    return null;
  }
}
