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
package org.webspeclanguage.mockupdd.sui.model.layout.impl;

import java.util.Collection;

import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.Layout;
import org.webspeclanguage.mockupdd.sui.model.layout.LayoutFactory;

/**
 * Implementation of {@link LayoutFactory} for {@link GridBagLayout}
 * 
 * @author Jose Matias Rivero
 */
public class GridBagLayoutFactory implements LayoutFactory {

  public Layout createLayout(Collection<Widget> widgets) {
    return new RecursiveGridBagLayoutFactory().createLayout(widgets);
  }

}
