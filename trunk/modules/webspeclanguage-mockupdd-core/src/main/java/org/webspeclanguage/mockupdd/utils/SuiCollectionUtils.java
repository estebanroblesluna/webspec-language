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
package org.webspeclanguage.mockupdd.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jos� Mat�as Rivero
 */
public class SuiCollectionUtils {

  public static <K, V> Map<K, V> index(Collection<V> collection, Indexer<K, V> indexer) {
    Map<K, V> map = new HashMap<K, V>();
    for (V object : collection) {
      map.put(indexer.getIndex(object), object);
    }
    return map;
  }
  
  public interface Indexer<K, V> {
    
    public K getIndex(V object);
    
  }
  
}
