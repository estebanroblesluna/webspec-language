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

import junit.framework.TestCase;

import org.webspeclanguage.impl.core.DiagramImpl;
import org.webspeclanguage.impl.core.InteractionImpl;
import org.webspeclanguage.impl.core.Path;
import org.webspeclanguage.impl.core.PathComputer;
import org.webspeclanguage.impl.core.WebSpecFactory;

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
    DiagramImpl diagram = WebSpecFactory.getCycleExample();
    List<Path> paths = this.pathComputer.computePathsFor(diagram);
    assertEquals(3, paths.size());

    Path path1 = paths.get(0);
    Path path2 = paths.get(1);
    Path path3 = paths.get(2);

    // path1
    assertEquals("i1", path1.getTransitionSource(0).getName());
    assertEquals(path1.getTransitionSource(0), path1.getTransition(0).getSource());
    assertEquals(path1.getTransitionSource(1), path1.getTransition(0).getTarget());

    assertEquals("i2", path1.getTransitionSource(1).getName());
    assertEquals(path1.getTransitionSource(1), path1.getTransition(1).getSource());
    assertEquals(path1.getTransitionSource(2), path1.getTransition(1).getTarget());

    assertEquals("i1", path1.getTransitionSource(2).getName());
    assertEquals(path1.getTransitionSource(2), path1.getTransition(2).getSource());
    assertEquals(path1.getTransitionSource(3), path1.getTransition(2).getTarget());

    assertEquals("i2", path1.getTransitionSource(3).getName());
    assertEquals(path1.getTransitionSource(3), path1.getTransition(3).getSource());
    assertEquals(path1.getTransitionSource(4), path1.getTransition(3).getTarget());

    assertEquals("i1", path1.getTransitionSource(4).getName());

    // path2
    assertEquals("i1", path2.getTransitionSource(0).getName());
    assertEquals(path2.getTransitionSource(0), path2.getTransition(0).getSource());
    assertEquals(path2.getTransitionSource(1), path2.getTransition(0).getTarget());

    assertEquals("i2", path2.getTransitionSource(1).getName());
    assertEquals(path2.getTransitionSource(1), path2.getTransition(1).getSource());
    assertEquals(path2.getTransitionSource(2), path2.getTransition(1).getTarget());

    assertEquals("i1", path2.getTransitionSource(2).getName());
    assertEquals(path2.getTransitionSource(2), path2.getTransition(2).getSource());
    assertEquals(path2.getTransitionSource(3), path2.getTransition(2).getTarget());

    assertEquals("i3", path2.getTransitionSource(3).getName());

    // path3
    assertEquals("i1", path3.getTransitionSource(0).getName());
    assertEquals(path3.getTransitionSource(0), path3.getTransition(0).getSource());
    assertEquals(path3.getTransitionSource(1), path3.getTransition(0).getTarget());

    assertEquals("i3", path3.getTransitionSource(1).getName());
  }

  public void testComputePathsFor() {
    DiagramImpl diagram = new DiagramImpl("a");

    InteractionImpl i1 = new InteractionImpl("i1");
    InteractionImpl i2 = new InteractionImpl("i2");
    InteractionImpl i3 = new InteractionImpl("i3");
    InteractionImpl i4 = new InteractionImpl("i4");

    i1.setLocation("http://www.google.com");
    diagram.setStartingInteraction(i1);

    i1.navigateTo(i2);
    i1.navigateTo(i3);
    i3.navigateTo(i4);
    i4.navigateTo(i1);

    List<Path> paths = this.pathComputer.computePathsFor(diagram);
    assertEquals(3, paths.size());

    // 1-2
    Path path1 = paths.get(0);
    assertEquals(3, path1.length());
    assertEquals("i1", path1.getTransitionSource(0).getName());
    assertEquals("i2", path1.getTransitionSource(1).getName());

    // 1-3-4-1-2
    Path path2 = paths.get(1);
    assertEquals(9, path2.length());
    assertEquals("i1", path2.getTransitionSource(0).getName());
    assertEquals("i3", path2.getTransitionSource(1).getName());
    assertEquals("i4", path2.getTransitionSource(2).getName());
    assertEquals("i1", path2.getTransitionSource(3).getName());
    assertEquals("i2", path2.getTransitionSource(4).getName());

    // 1-3-4-1-3-4-1
    Path path3 = paths.get(2);
    assertEquals(13, path3.length());
    assertEquals("i1", path3.getTransitionSource(0).getName());
    assertEquals("i3", path3.getTransitionSource(1).getName());
    assertEquals("i4", path3.getTransitionSource(2).getName());
    assertEquals("i1", path3.getTransitionSource(3).getName());
    assertEquals("i3", path3.getTransitionSource(4).getName());
    assertEquals("i4", path3.getTransitionSource(5).getName());
    assertEquals("i1", path3.getTransitionSource(6).getName());
  }
}
