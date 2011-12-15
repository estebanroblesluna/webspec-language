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
package org.webspeclanguage.mockupdd.translator;

import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.Widget;

/**
 * Exception expressing the collision of 2 widgets in some common space
 * 
 * @author Jose Matias Rivero
 */
public class CollidingWidgetsException extends
		SuiTranslationException {

	private static final long serialVersionUID = 1L;
  private Widget widget1;
	private Widget widget2;

	public CollidingWidgetsException(SuiModel model,
			Widget widget1, Widget widget2) {
		super(model);
		this.setWidget1(widget1);
		this.setWidget2(widget2);
	}

	private void setWidget1(Widget widget1) {
		this.widget1 = widget1;
	}

	private Widget getWidget1() {
		return widget1;
	}

	private void setWidget2(Widget widget2) {
		this.widget2 = widget2;
	}

	private Widget getWidget2() {
		return widget2;
	}
	
	@Override
	public String getMessage() {
		return "Collision detected in model elements [" + 
			this.getWidget1() + "] and [" + this.getWidget2() + "]";
	}

}
