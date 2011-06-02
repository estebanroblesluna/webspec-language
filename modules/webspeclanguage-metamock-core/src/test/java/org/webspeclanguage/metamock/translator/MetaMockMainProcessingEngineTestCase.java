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
package org.webspeclanguage.metamock.translator;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.Arrays;
import java.util.Collection;

import org.webspeclanguage.metamock.model.Annotation;
import org.webspeclanguage.metamock.model.Button;
import org.webspeclanguage.metamock.model.Label;
import org.webspeclanguage.metamock.model.MetaMockModel;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.Panel;
import org.webspeclanguage.metamock.model.Repetition;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.translator.annotation.MetaMockControlAnnotationParser;
import org.webspeclanguage.metamock.utils.MetaMockUtil;
import org.webspeclanguage.metamock.utils.Null;
import org.webspeclanguage.metamock.utils.Pair;

/**
 * @author Jose Matias Rivero
 */
public class MetaMockMainProcessingEngineTestCase extends MetaMockProcessingEngineTestCase {

	public void testPageInclusion() throws Exception {
		Page p;
		Button b1;
		Panel pnl;
		Button b2;
		this.initializeProcessingEngine(Arrays.asList(
			this.getFactory().createControlGroup(Arrays.asList(	
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
		assertTrue(pnl.getControls().contains(b1));
		assertSame(b1.getParent(), pnl);
	}
	
	public void testCollisionDetection() throws Exception {
		this.initializeProcessingEngine(Arrays.asList(
			this.getFactory().createControlGroup(Arrays.asList(	
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
		} catch (MetaMockCollidingControlsException e) { }
	}
	
	@SuppressWarnings("unchecked")
  public void testRepetitionDetection() throws Exception{
		Page page;
		Button button;
		Label label;
		Pair<MetaMockControlParser<Null>, MetaMockControlAnnotationParser> p = 
			this.initializeProcessingEngineWithoutReplay(Arrays.asList(
  			this.getFactory().createControlGroup(Arrays.asList(	
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
		
		MetaMockModel m = this.getRawModel();
		assertEquals(m.getPages().size(), 1);
		assertEquals(0, MetaMockUtil.filterControlsByType(
		        m.getPages().iterator().next().getControls(), Panel.class).size());
		assertEquals(1, MetaMockUtil.filterControlsByType(
			m.getPages().iterator().next().getControls(), Repetition.class).size());
		Repetition r = (Repetition) MetaMockUtil.filterControlsByType(
				m.getPages().iterator().next().getControls(), Repetition.class)
					.iterator().next();
		assertEquals(r.getControls().size(), 2);
		Collection<UIControl> repetitionButtons = MetaMockUtil.filterControlsByType(
		        r.getControls(), Button.class);
		Collection<UIControl> repetitionLabels = MetaMockUtil.filterControlsByType(
            r.getControls(), Label.class);
		assertEquals(repetitionButtons.size(), 1);
		assertEquals(repetitionLabels.size(), 1);
		// by convention, the control preserved when a Panel is converted into a Repetition
		// is the located near the upper left corner
		assertEquals((Button)repetitionButtons.iterator().next(), button);
		assertEquals((Label)repetitionLabels.iterator().next(), label);
	}
	
	public void testAnnotationAssociation() throws Exception {
		Button b;
		Annotation a;
		Pair<MetaMockControlParser<Null>, MetaMockControlAnnotationParser> p = 
			this.initializeProcessingEngineWithoutReplay(Arrays.asList(
				this.getFactory().createControlGroup(Arrays.asList(	
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
			.andReturn(this.getFactory().createControlAnnotation(b, "b1"));
		replay(p.getObject1(), p.getObject2());
		
		MetaMockModel m = this.getRawModel();
		assertEquals(m.getPages().size(), 1);
		assertEquals(m.getControlsOutsidePages().size(), 1);
		assertSame(m.getControlsOutsidePages().iterator().next(), a);
		assertSame(a.getTargetElement(), b);
	}
	
	 public void testImplicitPageCreation() throws Exception {
	    Button b1;
	    Panel pnl;
	    Button b2;
	    this.initializeProcessingEngine(Arrays.asList(
	      this.getFactory().createControlGroup(Arrays.asList( 
	        pnl = this.getFactory()
	          .createPanel("pnl1", 50, 50, 200, 200, "cid"),
	        b1 = this.getFactory()
	          .createButton("b1", 100, 100, 50, 50, "button1"),
	        b2 = this.getFactory()
	          .createButton("b2", 300, 300, 50, 50, "button2")
	      ))));
	    MetaMockModel m = this.getRawModel();
	    assertEquals(1, m.getPages().size());
	    Page p = m.getPages().iterator().next();
	    assertSame(b1.getPage(), p);  
	    assertSame(b2.getPage(), p);
	    assertSame(b2.getPage(), b2.getParent()); 
	    assertSame(pnl.getPage(), p);
	    assertTrue(pnl.getControls().contains(b1));
	    assertSame(b1.getParent(), pnl);
	  }
	
}
