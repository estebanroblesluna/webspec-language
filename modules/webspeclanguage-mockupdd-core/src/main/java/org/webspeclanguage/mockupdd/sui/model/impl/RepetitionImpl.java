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
package org.webspeclanguage.mockupdd.sui.model.impl;

import java.util.Collection;

import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * Default implementation of {@link Repetition}
 * 
 * @author Jose Matias Rivero
 */
public class RepetitionImpl extends CompositeWidgetImpl implements Repetition {

  private Integer rows;
  private Integer columns;

  public RepetitionImpl(String widgetID, Integer x, Integer y, Integer width, Integer height, Collection<Widget> widgets, Integer rows, Integer colums,
          String containerId) {
    super(widgetID, x, y, width, height, widgets, containerId);
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

  protected CompositeWidget createNewInstance() {
    return new RepetitionImpl(this.getWidgetId(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getWidgets(), this.getRows(), this
            .getColumns(), this.getContainerId());
  }

  public <T> T accept(SuiVisitor<T> v) {
    return v.visitRepetition(this);
  }

}
