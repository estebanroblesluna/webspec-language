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
import java.util.List;

import org.webspeclanguage.mockupdd.sui.model.layout.Layout;

/**
 * Abstract interface containing common features present in composite widgets
 * 
 * @author Jose Matias Rivero
 */
public interface CompositeWidget extends Widget, DataBoundWidget {

  Collection<Widget> getWidgets();

  Layout getLayout();

  void addChild(Widget c);
  
  void addAll(Collection<Widget> widgets);

  void removeChild(Widget c);

  void setLayout(Layout layout);

  Widget getWidgetById(String id);

  void setWidgets(Collection<Widget> widgets);

  String getContainerId();

  void removeAllChildren();
  
  void replaceWidget(Widget widgetToReplace, Widget replacingWidget);

}
