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

import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.impl.core.Path;
import org.webspeclanguage.impl.core.PathComputer;
import org.webspeclanguage.impl.core.WebSpecFactory;

/**
 * @author Esteban Robles Luna
 */
public class PathTestCase extends TestCase {

  public void testToString() {
    PathComputer pathComputer = new PathComputer(2);
    Diagram diagram = WebSpecFactory.getCycleExample();
    List<Path> paths = pathComputer.computePathsFor(diagram);

    assertEquals("i1_i2_i1_i2_i1", paths.get(0).toString());
    assertEquals("i1_i2_i1_i3", paths.get(1).toString());
    assertEquals("i1_i3", paths.get(2).toString());
  }

  public void testContains() {
    PathComputer pathComputer = new PathComputer(2);
    Diagram diagram = WebSpecFactory.getCycleExample();
    List<Path> paths = pathComputer.computePathsFor(diagram);

    assertTrue(paths.get(0).contains(diagram.getInteractionNamed("i1")));
    assertTrue(paths.get(0).contains(diagram.getInteractionNamed("i2")));

    assertTrue(paths.get(1).contains(diagram.getInteractionNamed("i1")));
    assertTrue(paths.get(1).contains(diagram.getInteractionNamed("i2")));
    assertTrue(paths.get(1).contains(diagram.getInteractionNamed("i3")));

    assertTrue(paths.get(2).contains(diagram.getInteractionNamed("i1")));
    assertTrue(paths.get(2).contains(diagram.getInteractionNamed("i3")));
  }
}
