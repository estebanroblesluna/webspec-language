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

import org.webspeclanguage.metamock.model.SuiModel;
import org.webspeclanguage.metamock.model.Widget;

/**
 * Exception expressing the collision of 2 widgets in some common space
 * 
 * @author Jose Matias Rivero
 */
public class CollidingWidgetsException extends
		SuiTranslationException {

	private static final long serialVersionUID = 1L;
  private Widget control1;
	private Widget control2;

	public CollidingWidgetsException(SuiModel model,
			Widget control1, Widget control2) {
		super(model);
		this.setControl1(control1);
		this.setControl2(control2);
	}

	private void setControl1(Widget control1) {
		this.control1 = control1;
	}

	private Widget getControl1() {
		return control1;
	}

	private void setControl2(Widget control2) {
		this.control2 = control2;
	}

	private Widget getControl2() {
		return control2;
	}
	
	@Override
	public String getMessage() {
		return "Collision detected in model elements [" + 
			this.getControl1() + "] and [" + this.getControl2() + "]";
	}

}
