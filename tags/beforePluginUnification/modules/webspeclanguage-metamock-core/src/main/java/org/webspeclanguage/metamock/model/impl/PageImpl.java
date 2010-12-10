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
package org.webspeclanguage.metamock.model.impl;

import java.util.HashSet;
import java.util.Set;

import org.webspeclanguage.metamock.model.Annotation;
import org.webspeclanguage.metamock.model.CompositeControl;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Default implementation of {@link Page}
 * 
 * @author Jose Matias Rivero
 */
public class PageImpl extends CompositeControlImpl implements Page {

  private String title;
  private Set<Annotation> annotations;

  public PageImpl(String controlID, Integer x, Integer y, Integer width, Integer height, String title, String containerId) {
    super(controlID, x, y, width, height, containerId);
    this.setTitle(title);
    this.setAnnotations(new HashSet<Annotation>());
  }

  public String getTitle() {
    return title;
  }

  private void setTitle(String title) {
    this.title = title;
  }

  private void setAnnotations(Set<Annotation> annotations) {
    this.annotations = annotations;
  }

  public Set<Annotation> getAnnotations() {
    return annotations;
  }

  public <T> T visit(MetaMockVisitor<T> v) {
    return v.visitPage(this);
  }

  public Page getPage() {
    return this;
  }

  @Override
  public String toString() {
    return this.getTitle();
  }

  @Override
  public Boolean equalInContent(UIControl control) {
    PageImpl other = (PageImpl) control;
    return this.getTitle().equals(other.getTitle());
  }

  @Override
  protected CompositeControl createNewInstance() {
    return new PageImpl(this.getControlId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getTitle(), this.getContainerId());
  }

}
