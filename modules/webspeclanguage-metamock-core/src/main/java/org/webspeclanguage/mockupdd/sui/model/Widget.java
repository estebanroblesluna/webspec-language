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
package org.webspeclanguage.mockupdd.sui.model;

import java.util.Collection;

import org.webspeclanguage.mockupdd.sui.model.impl.tags.TagApplicationImpl;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;

/**
 * Abstract interface containing all the common features present in a widget
 * 
 * @author Jose Matias Rivero
 */
public abstract interface Widget extends SuiModelElement {

  String getWidgetId();

  void setWidgetId(String id);
  
  String getFriendlyId();

  void setFriendlyId(String id);
  
  Integer getHeight();
  
  void setHeight(Integer height);

  Integer getWidth();
  
  void setWidth(Integer width);

  Integer getX();
  
  void setX(Integer x);

  Integer getY();
  
  void setY(Integer y);

  CompositeWidget getParent();

  void setParent(CompositeWidget c);

  Page getPage();

  /**
   * checks if this and <code>widget</code> have the same properties (e.g.,
   * label, inner composition, etc)
   * 
   * @param widget
   * @return true if this is graphically equal to <code>widget</code>
   */
  Boolean equalInContent(Widget widget);

  /**
   * checks if this <code>widget</code> is the same component, even in
   * different versions of the model
   * 
   * @param parent
   * @return true if this is the same widget than <code>widget</code>
   */
  Boolean isTheSameAs(CompositeWidget parent);
  
  Collection <TagApplication> getAppliedTags();

  void addTagApplication(TagApplication tagApplication) throws TagApplicationException;

}
