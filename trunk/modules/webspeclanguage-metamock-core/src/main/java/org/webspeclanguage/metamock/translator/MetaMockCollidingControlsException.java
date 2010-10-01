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

import org.webspeclanguage.metamock.model.MetaMockModel;
import org.webspeclanguage.metamock.model.UIControl;

/**
 * Exception expressing the collision of 2 widgets in some common space
 * 
 * @author Jose Matias Rivero
 */
public class MetaMockCollidingControlsException extends
		MetaMockTranslationException {

	private static final long serialVersionUID = 1L;
  private UIControl control1;
	private UIControl control2;

	public MetaMockCollidingControlsException(MetaMockModel model,
			UIControl control1, UIControl control2) {
		super(model);
		this.setControl1(control1);
		this.setControl2(control2);
	}

	private void setControl1(UIControl control1) {
		this.control1 = control1;
	}

	private UIControl getControl1() {
		return control1;
	}

	private void setControl2(UIControl control2) {
		this.control2 = control2;
	}

	private UIControl getControl2() {
		return control2;
	}
	
	@Override
	public String getMessage() {
		return "Collision detected in model elements [" + 
			this.getControl1() + "] and [" + this.getControl2() + "]";
	}

}
