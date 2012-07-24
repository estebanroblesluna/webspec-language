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
package org.webspeclanguage.mockupdd.sui.model.impl;

import org.webspeclanguage.mockupdd.sui.model.Image;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * Default implementation of {@link Image}
 * 
 * @author Jose Matias Rivero
 */
public class ImageImpl extends SimpleWidgetImpl implements Image {

  private String imageUrl;

  public ImageImpl(String widgetID, Integer x, Integer y, Integer width, Integer height, String imageUrl) {
    super(widgetID, x, y, width, height);
    this.setImageUrl(imageUrl);
  }

  private void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitImage(this);
  }

  @Override
  public Boolean equalInContent(Widget widget) {
    Image other = (Image) widget;
    return super.equalInContent(other) && this.getImageUrl().equals(other.getImageUrl());
  }

  @Override
  public Widget copyConcreteWidget() {
    return new ImageImpl(this.getWidgetId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getImageUrl());
  }

}
