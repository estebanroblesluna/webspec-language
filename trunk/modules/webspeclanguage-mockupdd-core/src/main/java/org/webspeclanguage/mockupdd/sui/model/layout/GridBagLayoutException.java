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
package org.webspeclanguage.mockupdd.sui.model.layout;

/**
 * An exception thrown when an invalid operation over a {@link GridBagLayout} is
 * invoked
 * 
 * @author Jose Matias Rivero
 */
public class GridBagLayoutException extends Exception {

  private static final long serialVersionUID = 1L;
  private GridBagLayout gridBagLayout;

  private void setGridBagLayout(GridBagLayout gridBagLayout) {
    this.gridBagLayout = gridBagLayout;
  }

  public GridBagLayout getGridBagLayout() {
    return gridBagLayout;
  }

  public GridBagLayoutException(String errorMessage, GridBagLayout gridBagLayout) {
    super(errorMessage);
    this.setGridBagLayout(gridBagLayout);
  }

}
