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

import java.util.ArrayList;
import java.util.List;

/**
 * A class that computes paths in the diagram following a DFS algorithm.
 * To allow cycles and ensure algorithm termination we specify how many
 * cycles are allow in the path to be valid.
 * 
 * @author Esteban Robles Luna
 */
public class PathComputer {

  private int cyclesAllowed;

  public static List<WebSpecPath> computePaths(WebSpecDiagram diagram) {
    return new PathComputer(diagram.getCyclesAllowed())
        .computePathsFor(diagram);
  }

  public PathComputer(int cyclesAllowed) {
    this.cyclesAllowed = cyclesAllowed;
  }

  public List<WebSpecPath> computePathsFor(WebSpecDiagram diagram) {
    List<WebSpecPath> paths = new ArrayList<WebSpecPath>();
    WebSpecInteraction startInteraction = diagram.getStartingInteraction();
    WebSpecPath currentPath = new WebSpecPath(startInteraction);

    this.basicComputePathsFrom(startInteraction, currentPath, paths);
    return paths;
  }

  private void basicComputePathsFrom(WebSpecInteraction interaction,
      WebSpecPath currentPath, List<WebSpecPath> paths) {
    if (interaction.getForwardTransitions().isEmpty()) {
      paths.add(currentPath);
    } else {
      if (currentPath.hasCycles() && this.isAllowCycles()
          && currentPath.getCyclesCount() == this.getCyclesAllowed()) {
        paths.add(currentPath);
      } else {
        if (!currentPath.hasCycles()
            || (currentPath.hasCycles() 
                && this.isAllowCycles() 
                && currentPath.getCyclesCount() < this.getCyclesAllowed())) {
          for (WebSpecTransition forwardTransition : interaction.getForwardTransitions()) {
            WebSpecInteraction to = forwardTransition.getTo();
            WebSpecPath newPath = currentPath.extendWith(forwardTransition, to);
            this.basicComputePathsFrom(to, newPath, paths);
          }
        }
      }
    }
  }

  private boolean isAllowCycles() {
    return this.getCyclesAllowed() > 0;
  }

  private int getCyclesAllowed() {
    return cyclesAllowed;
  }
}