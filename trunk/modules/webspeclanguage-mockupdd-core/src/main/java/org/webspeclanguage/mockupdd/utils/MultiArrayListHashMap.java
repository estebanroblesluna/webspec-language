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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author José Matías Rivero
 */
public class MultiArrayListHashMap<K, V> implements MultiListMap<K, V> {

  private HashMap<K, List<V>> map;
  
  public MultiArrayListHashMap() {
    super();
    this.map = new HashMap<K, List<V>>();
  }

  @Override
  public void put(K key, V value) {
    if (!this.map.containsKey(key)) {
      this.map.put(key, new ArrayList<V>());
    }
    this.map.get(key).add(value);
  }
  
  @Override
  public void putOnce(K key, V value) {
    if (!this.map.containsKey(key)) {
      this.map.put(key, new ArrayList<V>());
    }
    if (!this.map.get(key).contains(value)) {
      this.map.get(key).add(value);
    }
  }

  @Override
  public List<V> get(K key) {
    return this.map.get(key);
  }

  @Override
  public boolean remove(K key, V value) {
    if (!this.map.containsKey(key)) {
      return false;
    }
    return this.map.get(key).remove(value);
  }

  @Override
  public boolean containsKey(K key) {
    return this.map.containsKey(key);
  }

  @Override
  public boolean contains(K key, V value) {
    if (!this.map.containsKey(key)) {
      return false;
    }
    return this.map.get(key).contains(value);
  }

}
