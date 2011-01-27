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
package org.webspeclanguage.metamock.utils;

/**
 * @author Jose Matias Rivero
 */
@SuppressWarnings("rawtypes")
public class MaxHolder<ObjectType, ValueType extends Comparable> {

  private ObjectType maxObject;
  private ValueType maxValue;
  
  public MaxHolder() {
    super();
  }
  
  @SuppressWarnings("unchecked")
  public void store(ObjectType object, ValueType value) {
    if (this.getMaxValue() == null) {
      this.setElements(object, value);
    } else {
      if (value.compareTo(this.getMaxValue()) > 0) {
        this.setElements(object, value);
      }  
    }
    
  }

  private void setElements(ObjectType object, ValueType value) {
    this.setMaxValue(value);
    this.setMaxObject(object);
  }

  private void setMaxObject(ObjectType maxObject) {
    this.maxObject = maxObject;
  }

  public ObjectType getMaxObject() {
    return maxObject;
  }

  private void setMaxValue(ValueType maxValue) {
    this.maxValue = maxValue;
  }

  public ValueType getMaxValue() {
    return maxValue;
  }
  
}
