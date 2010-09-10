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
package org.webspeclanguage.base;

import java.util.List;

import org.webspeclanguage.base.PathComputer;
import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.base.WebSpecFactory;
import org.webspeclanguage.base.WebSpecInteraction;
import org.webspeclanguage.base.WebSpecPath;

import junit.framework.TestCase;

/**
 * @author Esteban Robles Luna
 */
public class PathComputerTestCase extends TestCase {

  private PathComputer pathComputer;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.pathComputer = new PathComputer(2);
  }

  public void testPathComputing() {
    WebSpecDiagram diagram = WebSpecFactory.getCycleExample();
    List<WebSpecPath> paths = this.pathComputer.computePathsFor(diagram);
    assertEquals(3, paths.size());

    WebSpecPath path1 = paths.get(0);
    WebSpecPath path2 = paths.get(1);
    WebSpecPath path3 = paths.get(2);

    // path1
    assertEquals("i1", path1.getInteraction(0).getName());
    assertEquals(path1.getInteraction(0), path1.getNavigation(0).getFrom());
    assertEquals(path1.getInteraction(1), path1.getNavigation(0).getTo());

    assertEquals("i2", path1.getInteraction(1).getName());
    assertEquals(path1.getInteraction(1), path1.getNavigation(1).getFrom());
    assertEquals(path1.getInteraction(2), path1.getNavigation(1).getTo());

    assertEquals("i1", path1.getInteraction(2).getName());
    assertEquals(path1.getInteraction(2), path1.getNavigation(2).getFrom());
    assertEquals(path1.getInteraction(3), path1.getNavigation(2).getTo());

    assertEquals("i2", path1.getInteraction(3).getName());
    assertEquals(path1.getInteraction(3), path1.getNavigation(3).getFrom());
    assertEquals(path1.getInteraction(4), path1.getNavigation(3).getTo());

    assertEquals("i1", path1.getInteraction(4).getName());

    // path2
    assertEquals("i1", path2.getInteraction(0).getName());
    assertEquals(path2.getInteraction(0), path2.getNavigation(0).getFrom());
    assertEquals(path2.getInteraction(1), path2.getNavigation(0).getTo());

    assertEquals("i2", path2.getInteraction(1).getName());
    assertEquals(path2.getInteraction(1), path2.getNavigation(1).getFrom());
    assertEquals(path2.getInteraction(2), path2.getNavigation(1).getTo());

    assertEquals("i1", path2.getInteraction(2).getName());
    assertEquals(path2.getInteraction(2), path2.getNavigation(2).getFrom());
    assertEquals(path2.getInteraction(3), path2.getNavigation(2).getTo());

    assertEquals("i3", path2.getInteraction(3).getName());

    // path3
    assertEquals("i1", path3.getInteraction(0).getName());
    assertEquals(path3.getInteraction(0), path3.getNavigation(0).getFrom());
    assertEquals(path3.getInteraction(1), path3.getNavigation(0).getTo());

    assertEquals("i3", path3.getInteraction(1).getName());
  }

  public void testComputePathsFor() {
    WebSpecDiagram diagram = new WebSpecDiagram("a");

    WebSpecInteraction i1 = new WebSpecInteraction("i1", diagram);
    WebSpecInteraction i2 = new WebSpecInteraction("i2", diagram);
    WebSpecInteraction i3 = new WebSpecInteraction("i3", diagram);
    WebSpecInteraction i4 = new WebSpecInteraction("i4", diagram);

    i1.setLocation("http://www.google.com");
    diagram.setStartingInteraction(i1);

    i1.navigateTo(i2);
    i1.navigateTo(i3);
    i3.navigateTo(i4);
    i4.navigateTo(i1);

    List<WebSpecPath> paths = this.pathComputer.computePathsFor(diagram);
    assertEquals(3, paths.size());

    // 1-2
    WebSpecPath path1 = paths.get(0);
    assertEquals(3, path1.length());
    assertEquals("i1", path1.getInteraction(0).getName());
    assertEquals("i2", path1.getInteraction(1).getName());

    // 1-3-4-1-2
    WebSpecPath path2 = paths.get(1);
    assertEquals(9, path2.length());
    assertEquals("i1", path2.getInteraction(0).getName());
    assertEquals("i3", path2.getInteraction(1).getName());
    assertEquals("i4", path2.getInteraction(2).getName());
    assertEquals("i1", path2.getInteraction(3).getName());
    assertEquals("i2", path2.getInteraction(4).getName());

    // 1-3-4-1-3-4-1
    WebSpecPath path3 = paths.get(2);
    assertEquals(13, path3.length());
    assertEquals("i1", path3.getInteraction(0).getName());
    assertEquals("i3", path3.getInteraction(1).getName());
    assertEquals("i4", path3.getInteraction(2).getName());
    assertEquals("i1", path3.getInteraction(3).getName());
    assertEquals("i3", path3.getInteraction(4).getName());
    assertEquals("i4", path3.getInteraction(5).getName());
    assertEquals("i1", path3.getInteraction(6).getName());
  }
}
