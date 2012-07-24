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
package org.webspeclanguage.mockupdd.translator;

import java.util.Arrays;

import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Link;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SuiTestCase;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.translator.DefaultRepetitionDetectorImpl;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author Jose Matias Rivero
 */
public class RepetitionDetectorTestCase extends SuiTestCase {

  private static final int RANDOM_TOLERANCE_TEST_COUNT = 10;
  private static final int TOLERANCE = 10;
  private static final Integer MIN_CONTROLS = 2;
  private RepetitionDetector repetitionDetector;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.repetitionDetector = new DefaultRepetitionDetectorImpl(this.getFactory(), TOLERANCE, MIN_CONTROLS);
  }
  
  @SuppressWarnings("unchecked")
  public void testRepetitionDetection() {
    Repetition r = this.repetitionDetector.detectRepetition(
      this.createPanelWith(
      this.getFactory().createButton("b1", 20, 10, 10, 10, "button 1"),
      this.getFactory().createTextBox("t1", 50, 10, 20, 10),
      this.getFactory().createLink("l1", 20, 30, 10, 10, ""),
      this.getFactory().createLabel("l1", 40, 30, 10, 10, "label 1"),
      this.getFactory().createButton("b2", 20, 50, 10, 10, "button 1"),
      this.getFactory().createTextBox("t2", 50, 50, 20, 10),
      this.getFactory().createLink("l2", 20, 70, 10, 10, ""),
      this.getFactory().createLabel("l2", 40, 70, 10, 20, "label 1")        
    ));
    assertNotNull(r);
    assertEquals(4, r.getWidgets().size());
    assertEquals(1, SuiUtil.filterWidgetsByType(r.getWidgets(), Button.class).size());
    assertEquals(1, SuiUtil.filterWidgetsByType(r.getWidgets(), TextBox.class).size());
    assertEquals(1, SuiUtil.filterWidgetsByType(r.getWidgets(), Label.class).size());
    assertEquals(1, SuiUtil.filterWidgetsByType(r.getWidgets(), Link.class).size());
  }
  
  @SuppressWarnings("unchecked")
  public void testRepetitionDetectionTolerance() {
    for (int i = 0; i < RANDOM_TOLERANCE_TEST_COUNT; i++) {
      Panel p = this.createPanelWith(
        this.getFactory().createButton("b1", this.rnd(20), this.rnd(10), this.rnd(10), this.rnd(10), "button 1"),
        this.getFactory().createTextBox("t1", this.rnd(50), this.rnd(10), this.rnd(10), this.rnd(10)),
        this.getFactory().createLink("l1", this.rnd(20), this.rnd(30), this.rnd(10), this.rnd(10), ""),
        this.getFactory().createLabel("l1", this.rnd(40), this.rnd(30), this.rnd(10), this.rnd(10), "label 1"),
        this.getFactory().createButton("b2", this.rnd(20), this.rnd(50), this.rnd(10), this.rnd(10), "button 1"),
        this.getFactory().createTextBox("t2", this.rnd(50), this.rnd(50), this.rnd(10), this.rnd(10)),
        this.getFactory().createLink("l2", this.rnd(20), this.rnd(70), this.rnd(10), this.rnd(10), ""),
        this.getFactory().createLabel("l2", this.rnd(40), this.rnd(70), this.rnd(10), this.rnd(10), "label 1")        
      );
      Repetition r = this.repetitionDetector.detectRepetition(p);
      assertNotNull(r);
      assertEquals(4, r.getWidgets().size());
      assertEquals(1, SuiUtil.filterWidgetsByType(r.getWidgets(), Button.class).size());
      assertEquals(1, SuiUtil.filterWidgetsByType(r.getWidgets(), TextBox.class).size());
      assertEquals(1, SuiUtil.filterWidgetsByType(r.getWidgets(), Label.class).size());
      assertEquals(1, SuiUtil.filterWidgetsByType(r.getWidgets(), Link.class).size());
    }
  }
  
  private Integer rnd(Integer value) {
    int x = (int) Math.floor(value + (Math.random() * TOLERANCE / 2));
    return x;
  }

  private Panel createPanelWith(Widget... widgets) {
    Panel p = this.getFactory().createPanel("panel", 1, 1, 100, 100, "");
    p.addAll(Arrays.asList(widgets));
    return p;
  }
  
  public void testRepetitionNotDetected1() {
    assertNull(
      this.repetitionDetector.detectRepetition(
        this.createPanelWith(
        this.getFactory().createButton("b1", 20, 10, 10, 10, "button 1"),
        this.getFactory().createTextBox("t1", 50, 10, 20, 10),
        this.getFactory().createLink("l1", 20, 30, 10, 10, ""),
        this.getFactory().createLabel("l1", 40, 30, 10, 10, "label 1"),
        this.getFactory().createButton("b2", 20, 50, 10, 10, "button 1"),
        this.getFactory().createTextBox("t2", 50, 50, 20, 10),
        this.getFactory().createLink("l2", 20, 70, 10, 10, "")
      )));
  }
  
  public void testRepetitionNotDetected2() {
    assertNull(
      this.repetitionDetector.detectRepetition(
        this.createPanelWith(
        this.getFactory().createButton("b1", 20, 10, 10, 10, "button 1"),
        this.getFactory().createLink("l1", 20, 30, 10, 10, "")
      )));
  }
  
  
}
