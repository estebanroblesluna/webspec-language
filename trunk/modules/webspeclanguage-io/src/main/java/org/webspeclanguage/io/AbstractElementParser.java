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
package org.webspeclanguage.io;

import java.util.HashMap;
import java.util.Map;


/**
 * An abstract parser for XML elements
 * 
 * @author Esteban Robles Luna
 */
public abstract class AbstractElementParser implements ElementParser {

  private Map<Class<?>, ChildrenResolver> childrenResolvers;
  
  private Object result;
  
  public AbstractElementParser() {
    this.childrenResolvers = new HashMap<Class<?>, ChildrenResolver>();
  }
  
  /**
   * {@inheritDoc}
   */
  public void handleChild(Object result) {
    for (Class<?> theClass : this.childrenResolvers.keySet()) {
      if (theClass.isAssignableFrom(result.getClass())) {
        ChildrenResolver resolver = this.childrenResolvers.get(theClass);
        resolver.resolve(this.result, result, theClass);
      }
    }
  }
  
  /**
   * {@inheritDoc}
   */
  public Object getResult() {
    return this.result;
  }
  
  protected void registerChild(Class<?> theClass, String methodName) {
    this.childrenResolvers.put(theClass, new MethodInvocationResolver(methodName));
  }
  
  protected void setResult(Object result) {
    this.result = result;
  }
}
