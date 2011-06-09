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

import java.util.Arrays;
import java.util.List;

import org.webspeclanguage.metamock.model.SuiTestCase;
import org.webspeclanguage.metamock.model.Widget;

/**
 * @author Jose Matias Rivero
 */
public class ControlListTest extends SuiTestCase {

  private ControlList controlList;
  private List<Widget> controls;

  public void setUp() throws Exception {
    super.setUp();
    this.controls = Arrays.asList(
      (Widget)
      this.getFactory().createButton("b1", 10, 10, 20, 20, "button 1"),
      this.getFactory().createButton("b2", 40, 40, 30, 30, "button 2")
    );
  }
  
  public void testControlList1() {
    this.controlList = new ControlList();
    for (Widget c : this.controls)
      this.controlList.addControl(c);
    this.assertControlInclusion(controls);   
  }
  
  public void testControlList2() {
    this.controlList = new ControlList(this.controls);
    this.assertControlInclusion(controls);   
  }

  private void assertControlInclusion(List<Widget> controlsToAssert) {
    for (Widget c : controlsToAssert)
      assertTrue(this.controlList.getControls().contains(c));
  }
}
