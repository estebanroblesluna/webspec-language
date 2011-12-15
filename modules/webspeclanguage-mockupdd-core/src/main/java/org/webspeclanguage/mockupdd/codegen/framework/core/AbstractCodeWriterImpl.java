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
package org.webspeclanguage.mockupdd.codegen.framework.core;

/**
 * @author Jose Matias Rivero
 */
public abstract class AbstractCodeWriterImpl implements CodeWriter {

	private Integer indent;
	private Boolean firstCharOfLine;

	public AbstractCodeWriterImpl() {
		super();
		this.setIndent(0);
		this.setFirstCharOfLine(true);
	}

	protected final void setIndent(Integer indent) {
		this.indent = indent;
	}

	protected final Integer getIndent() {
		return indent;
	}

	protected final void setFirstCharOfLine(Boolean firstCharOfLine) {
		this.firstCharOfLine = firstCharOfLine;
	}

	protected final Boolean getFirstCharOfLine() {
		return firstCharOfLine;
	}

	public final void writeLine(String text) {
		this.write(text);
		this.writeLine();
		this.setFirstCharOfLine(true);
	}

	public final void write(String text) {
		if (this.getFirstCharOfLine()) {
			this.writeIndent();
			this.setFirstCharOfLine(false);
		}
		this.writeText(text);
	}
	
	public final void incrementIndent() {
		this.setIndent(this.getIndent() + 1);
	}

	public final void decrementIndent() {
		this.setIndent(this.getIndent() - 1);
	}

	public final void incrementIndent(Integer ind) {
		for (Integer i = 0; i < ind; i++) {
			this.incrementIndent();
		}
	}

	public final void decrementIndent(Integer ind) {
		for (Integer i = 0; i < ind; i++) {
			this.decrementIndent();
		}
	}
	
	protected abstract void writeText(String text);
	
	protected abstract void writeLine();
	
	protected abstract void writeIndent();

}