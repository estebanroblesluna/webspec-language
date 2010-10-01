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
package org.webspeclanguage.metamock.model;

import java.util.Collection;

import org.webspeclanguage.metamock.translator.Template;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Root element of a MetaMock model
 * 
 * @author Jose Matias Rivero
 */
public interface MetaMockModel {

  Collection<Page> getPages();

  MetaMockElement getControlById(String id);

  void registerControl(UIControl c);

  <T> T visit(MetaMockVisitor<T> metaMockVisitor);

  void addControlOutsidePages(UIControl c);

  Collection<UIControl> getControlsOutsidePages();

  Page getPageById(String pageId);

  void addPage(Page p);

  void addTemplate(Template template);

  Template getTemplate(String templateId, String containerId);
}
