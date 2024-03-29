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
package org.webspeclanguage.userstories.cropping;

/**
 * Class that holds information to perform a cropping over the diagram image.
 * 
 * @author cristian.cianfagna
 */
public class CroppingInfo {

  private int x;
  private int y;
  private int width;
  private int height;

  public CroppingInfo(int x, int y, int width, int height) {
    this.setHeight(height);
    this.setWidth(width);
    this.setX(x);
    this.setY(y);
  }

  public int getWidth() {
    return width;
  }

  private void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  private void setHeight(int height) {
    this.height = height;
  }

  public int getX() {
    return x;
  }

  private void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  private void setY(int y) {
    this.y = y;
  }
}
