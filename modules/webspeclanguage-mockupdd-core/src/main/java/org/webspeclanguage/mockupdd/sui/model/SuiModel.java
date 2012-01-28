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

import org.webspeclanguage.mockupdd.sui.model.impl.Template;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * Root element of a Sui model
 * 
 * @author Jose Matias Rivero
 */
public interface SuiModel {

  Collection<Page> getPages();

  SuiModelElement getWidgetById(String id);

  void registerWidget(Widget c);

  <T> T visit(SuiVisitor<T> metaMockVisitor);

  void addWidgetOutsidePages(Widget c);

  Collection<Widget> getWidgetsOutsidePages();

  Page getPageById(String pageId);

  void addPage(Page p);

  void addTemplate(Template template);

  Template getTemplate(String templateId, String containerId);
}
