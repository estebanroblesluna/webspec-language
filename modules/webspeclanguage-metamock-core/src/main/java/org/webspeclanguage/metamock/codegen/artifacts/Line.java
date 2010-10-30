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
public class Line implements AppendableCodeArtifact {

	private String text;
	private Integer indent;

	public Line(String text) {
		super();
		this.setIndent(0);
		this.setText(text);
	}

	public Line(String text, Integer indent) {
		this(text);
		this.setIndent(indent);
	}

	private void setText(String text) {
		this.text = text;
	}

	private String getText() {
		return text;
	}

	private void setIndent(Integer indent) {
		this.indent = indent;
	}

	private Integer getIndent() {
		return indent;
	}

	public void writeOn(CodeWriter w) {
		for (Integer i = 0; i < this.getIndent(); i++) {
			w.incrementIndent();
		}
		w.writeLine(text);
		for (Integer i = 0; i < this.getIndent(); i++) {
			w.decrementIndent();		
		}
		
	}

	public void append(String string) {
		this.setText(this.getText() + string);	
	}

	public Boolean hasContentToWrite() {
		return true;
	}
	
}
