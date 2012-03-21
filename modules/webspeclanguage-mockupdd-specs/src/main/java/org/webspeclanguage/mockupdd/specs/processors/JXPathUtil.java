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
package org.webspeclanguage.mockupdd.specs.processors;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.Pointer;

/**
 * @author Jose Matias Rivero
 */
public class JXPathUtil {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static Collection getValues(Object o, String xpathQuery, String relativeQuery) {
    JXPathContext context = JXPathContext.newContext(o);
    Collection coll = new HashSet();
    for (Iterator iter = context.iteratePointers(xpathQuery); iter.hasNext();) {
      Pointer empPtr = (Pointer) iter.next();
      coll.add(context.getRelativeContext(empPtr).getValue(relativeQuery));
    }
    return coll;
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static Collection getValuesFromCollection(Collection collection, String xpathQuery, String relativeQuery) {
    Collection coll = new HashSet();
    for (Object o : collection) {
      coll.addAll(getValues(o, xpathQuery, relativeQuery));
    }
    return coll;
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static Map getValues(Object o, String xpathQuery, String keyRelativeQuery, String valueRelativeQuery) {
    JXPathContext context = JXPathContext.newContext(o);
    Map map = new HashMap();
    for (Iterator iter = context.iteratePointers(xpathQuery); iter.hasNext();) {
      Pointer empPtr = (Pointer) iter.next();
      map.put(
          context.getRelativeContext(empPtr).getValue(keyRelativeQuery),
          context.getRelativeContext(empPtr).getValue(valueRelativeQuery));
    }
    return map;
  }

}
