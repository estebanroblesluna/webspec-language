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

import junit.framework.TestCase;

/**
 * @author Jose Matias Rivero
 */
public class RectangleTest extends TestCase {

  private Rectangle rectangle;

  public void setUp() {
    this.rectangle = new Rectangle(100, 200, 300, 400);
  }
  
  public void testGetLeft() {
    assertEquals(100, (int)this.rectangle.getLeft());
  }

  public void testGetRight() {
    assertEquals(200, (int)this.rectangle.getRight());
  }

  public void testGetTop() {
    assertEquals(300, (int)this.rectangle.getTop());
  }

  public void testGetBottom() {
    assertEquals(400, (int)this.rectangle.getBottom());
  }

  public void testGetWidth() {
    assertEquals(100, (int)this.rectangle.getWidth());
  }

  public void testGetHeight() {
    assertEquals(100, (int)this.rectangle.getHeight());
  }

}
