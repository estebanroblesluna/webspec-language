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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.Validate;
import org.webspeclanguage.exception.WebspecException;

/**
 * A resolver that injects childs into parents by invoking a specific method
 * 
 * @author Esteban Robles Luna
 */
public class MethodInvocationResolver implements ChildrenResolver {

  private String methodName;
  
  public MethodInvocationResolver(String methodName) {
    Validate.notNull(methodName);
    
    this.methodName = methodName;
  }

  
  /**
   * {@inheritDoc}
   */
  public void resolve(Object parent, Object child, Class<?> matchingCriteria) {
    try {
      Method method = parent.getClass().getMethod(this.methodName, matchingCriteria);
      method.invoke(parent, child);
    } catch (SecurityException e) {
      throw new WebspecException(e);
    } catch (NoSuchMethodException e) {
      throw new WebspecException(e);
    } catch (IllegalArgumentException e) {
      throw new WebspecException(e);
    } catch (IllegalAccessException e) {
      throw new WebspecException(e);
    } catch (InvocationTargetException e) {
      throw new WebspecException(e);
    }
  }
}
