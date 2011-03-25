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
package org.webspeclanguage.metamock.model.impl;

import java.util.Collection;

import org.webspeclanguage.metamock.model.CompositeControl;
import org.webspeclanguage.metamock.model.Repetition;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * Default implementation of {@link Repetition}
 * 
 * @author Jose Matias Rivero
 */
public class RepetitionImpl extends CompositeControlImpl implements Repetition {

  private Integer rows;
  private Integer columns;

  public RepetitionImpl(String controlID, Integer x, Integer y, Integer width, Integer height, Collection<UIControl> controls, Integer rows, Integer colums,
          String containerId) {
    super(controlID, x, y, width, height, controls, containerId);
    this.setRows(rows);
    this.setColumns(colums);
  }

  private void setRows(Integer rows) {
    this.rows = rows;
  }

  public Integer getRows() {
    return rows;
  }

  private void setColumns(Integer colums) {
    this.columns = colums;
  }

  public Integer getColumns() {
    return columns;
  }

  protected CompositeControl createNewInstance() {
    return new RepetitionImpl(this.getControlId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getControls(), this.getRows(), this
            .getColumns(), this.getContainerId());
  }

  public <T> T accept(MetaMockVisitor<T> v) {
    return v.visitRepetition(this);
  }

}
