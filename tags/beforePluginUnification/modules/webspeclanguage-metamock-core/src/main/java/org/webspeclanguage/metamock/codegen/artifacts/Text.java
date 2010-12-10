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
package org.webspeclanguage.metamock.codegen.artifacts;

import org.webspeclanguage.metamock.codegen.framework.core.CodeWriter;
/**
 * @author Jose Matias Rivero
 */
public class Text implements AppendableCodeArtifact {

	private String text;

	public Text(String text) {
		super();
		this.setText(text);
	}

	public final void setText(String text) {
		this.text = text;
	}

	public final String getText() {
		return text;
	}

	public final void writeOn(CodeWriter w) {
		w.write(this.getText());
	}
	
	public final void append(String string) {
		this.setText(this.getText() + string);
	}

	public final Boolean hasContentToWrite() {
		return this.getText().length() > 0;
	}
	
}
