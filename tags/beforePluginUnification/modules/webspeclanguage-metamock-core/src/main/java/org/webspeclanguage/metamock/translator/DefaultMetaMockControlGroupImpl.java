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

import java.util.Collection;

import org.webspeclanguage.metamock.model.UIControl;


/**
 * Default {@link MetaMockControlGroup} implementation
 * 
 * @author Jose Matias Rivero
 */
public class DefaultMetaMockControlGroupImpl implements
		MetaMockControlGroup {

	private Collection<UIControl> controls;


	public DefaultMetaMockControlGroupImpl(Collection<UIControl> controls) {
		super();
		this.setControls(controls);
	}

	private void setControls(Collection<UIControl> controls) {
		this.controls = controls;
	}

	public Collection<UIControl> getControls() {
		return controls;
	}

	public void addControl(UIControl c) {
		this.getControls().add(c);
	}

}
