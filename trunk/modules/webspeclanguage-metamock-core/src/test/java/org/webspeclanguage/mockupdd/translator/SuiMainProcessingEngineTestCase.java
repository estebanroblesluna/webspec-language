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

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.Arrays;
import java.util.Collection;

import org.webspeclanguage.mockupdd.sui.model.Annotation;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.translator.CollidingWidgetsException;
import org.webspeclanguage.mockupdd.translator.WidgetParser;
import org.webspeclanguage.mockupdd.translator.annotation.WidgetAnnotationParser;
import org.webspeclanguage.mockupdd.utils.Null;
import org.webspeclanguage.mockupdd.utils.Pair;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author Jose Matias Rivero
 */
public class SuiMainProcessingEngineTestCase extends SuiProcessingEngineTestCase {

	public void testPageInclusion() throws Exception {
		Page p;
		Button b1;
		Panel pnl;
		Button b2;
		this.initializeProcessingEngine(Arrays.asList(
			this.getFactory().createWidgetGroup(Arrays.asList(	
				p = this.getFactory()
					.createPage("p1", 0, 0, 1000, 1000, "page1", "c1"),
				pnl = this.getFactory()
					.createPanel("pnl1", 50, 50, 200, 200, "cid"),
				b1 = this.getFactory()
					.createButton("b1", 100, 100, 50, 50, "button1"),
				b2 = this.getFactory()
					.createButton("b1", 300, 300, 50, 50, "button2")
			))));
		this.getRawModel();
		assertSame(b1.getPage(), p);	
		assertSame(b2.getPage(), p);
		assertSame(b2.getPage(), b2.getParent());	
		assertSame(pnl.getPage(), p);
		assertTrue(pnl.getWidgets().contains(b1));
		assertSame(b1.getParent(), pnl);
	}
	
	public void testCollisionDetection() throws Exception {
		this.initializeProcessingEngine(Arrays.asList(
			this.getFactory().createWidgetGroup(Arrays.asList(	
				this.getFactory()
					.createPage("p1", 0, 0, 1000, 1000, "page1", "c1"),
				this.getFactory()
					.createButton("b1", 50, 50, 200, 200, "button1"),
				this.getFactory()
					.createButton("b2", 100, 100, 50, 50, "button2")	
			))));
		try {
			this.getRawModel();
			fail();
		} catch (CollidingWidgetsException e) { }
	}
	
	@SuppressWarnings("unchecked")
  public void testRepetitionDetection() throws Exception{
		Page page;
		Button button;
		Label label;
		Pair<WidgetParser<Null>, WidgetAnnotationParser> p = 
			this.initializeProcessingEngineWithoutReplay(Arrays.asList(
  			this.getFactory().createWidgetGroup(Arrays.asList(	
  				page = this.getFactory()
  					.createPage("page1", 0, 0, 1000, 1000, "page1", "c1"),
  				this.getFactory()
            .createPanel("panel1", 10, 10, 980, 980, "c1"),
  				button = this.getFactory()
  					.createButton("b1", 50, 50, 50, 50, "button1"),
  				this.getFactory()
  					.createButton("b2", 150, 50, 50, 50, "button2"),
					this.getFactory()
            .createButton("b3", 250, 50, 50, 50, "button3"),
          label = this.getFactory()
            .createLabel("l1", 50, 150, 50, 50, "label1"),
          this.getFactory()
            .createLabel("l2", 150, 150, 50, 50, "label2"),
          this.getFactory()
            .createLabel("l3", 250, 150, 50, 50, "label3")
			))));
			
		replay(p.getObject1(), p.getObject2());
		
		SuiModel m = this.getRawModel();
		assertEquals(m.getPages().size(), 1);
		assertEquals(0, SuiUtil.filterWidgetsByType(
		        m.getPages().iterator().next().getWidgets(), Panel.class).size());
		assertEquals(1, SuiUtil.filterWidgetsByType(
			m.getPages().iterator().next().getWidgets(), Repetition.class).size());
		Repetition r = (Repetition) SuiUtil.filterWidgetsByType(
				m.getPages().iterator().next().getWidgets(), Repetition.class)
					.iterator().next();
		assertEquals(r.getWidgets().size(), 2);
		Collection<Widget> repetitionButtons = SuiUtil.filterWidgetsByType(
		        r.getWidgets(), Button.class);
		Collection<Widget> repetitionLabels = SuiUtil.filterWidgetsByType(
            r.getWidgets(), Label.class);
		assertEquals(repetitionButtons.size(), 1);
		assertEquals(repetitionLabels.size(), 1);
		// by convention, the widget preserved when a Panel is converted into a Repetition
		// is the located near the upper left corner
		assertEquals((Button)repetitionButtons.iterator().next(), button);
		assertEquals((Label)repetitionLabels.iterator().next(), label);
	}
	
	public void testAnnotationAssociation() throws Exception {
		Button b;
		Annotation a;
		Pair<WidgetParser<Null>, WidgetAnnotationParser> p = 
			this.initializeProcessingEngineWithoutReplay(Arrays.asList(
				this.getFactory().createWidgetGroup(Arrays.asList(	
					this.getFactory()
						.createPage("p1", 0, 0, 1000, 1000, "page1", "c1"),
					b = this.getFactory()
						.createButton("b1", 50, 50, 50, 50, "button1"),
					this.getFactory()
						.createButton("b2", 150, 150, 50, 50, "button2"),
					a = this.getFactory()
						.createAnnotation("a1", 48, 48, 10, 10, null, "")
				))));
		
		expect(p.getObject2().parseAnnotation(anyObject(Annotation.class)))
			.andReturn(this.getFactory().createWidgetAnnotation(b, "b1"));
		replay(p.getObject1(), p.getObject2());
		
		SuiModel m = this.getRawModel();
		assertEquals(m.getPages().size(), 1);
		assertEquals(m.getWidgetsOutsidePages().size(), 1);
		assertSame(m.getWidgetsOutsidePages().iterator().next(), a);
		assertSame(a.getTargetElement(), b);
	}
	
	 public void testImplicitPageCreation() throws Exception {
	    Button b1;
	    Panel pnl;
	    Button b2;
	    this.initializeProcessingEngine(Arrays.asList(
	      this.getFactory().createWidgetGroup(Arrays.asList( 
	        pnl = this.getFactory()
	          .createPanel("pnl1", 50, 50, 200, 200, "cid"),
	        b1 = this.getFactory()
	          .createButton("b1", 100, 100, 50, 50, "button1"),
	        b2 = this.getFactory()
	          .createButton("b2", 300, 300, 50, 50, "button2")
	      ))));
	    SuiModel m = this.getRawModel();
	    assertEquals(1, m.getPages().size());
	    Page p = m.getPages().iterator().next();
	    assertSame(b1.getPage(), p);  
	    assertSame(b2.getPage(), p);
	    assertSame(b2.getPage(), b2.getParent()); 
	    assertSame(pnl.getPage(), p);
	    assertTrue(pnl.getWidgets().contains(b1));
	    assertSame(b1.getParent(), pnl);
	  }
	
}
