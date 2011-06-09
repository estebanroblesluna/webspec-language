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

import java.util.ArrayList;
import java.util.Collection;

import org.webspeclanguage.metamock.model.Widget;

/**
 * Default {@link WidgetGroup} implementation
 * 
 * @author Jose Matias Rivero
 */
public class DefaultWidgetGroupImpl implements
		WidgetGroup {

	private Collection<Widget> controls;


	public DefaultWidgetGroupImpl(Collection<Widget> controls) {
		super();
		this.setControls(new ArrayList<Widget>(controls));
	}

	private void setControls(Collection<Widget> controls) {
		this.controls = controls;
	}

	public Collection<Widget> getControls() {
		return controls;
	}

	public void addControl(Widget c) {
		this.getControls().add(c);
	}

}
