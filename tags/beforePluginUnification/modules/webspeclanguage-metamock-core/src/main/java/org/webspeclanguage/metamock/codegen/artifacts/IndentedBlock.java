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

import org.webspeclanguage.metamock.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.metamock.codegen.framework.core.CodeWriter;

/**
 * @author Jose Matias Rivero
 */
public class IndentedBlock<T extends CodeArtifact> implements CodeArtifact {

	private T artifact;
	private Integer indent;
	
	public IndentedBlock(T artifact) {
		this(artifact, 1);
		this.setArtifact(artifact);
	}
	
	public IndentedBlock(T artifact, Integer indent) {
		super();
		this.setArtifact(artifact);
		this.setIndent(indent);
	}

	public void writeOn(CodeWriter w) {
		w.incrementIndent(this.getIndent());
		this.getArtifact().writeOn(w);
		w.decrementIndent(this.getIndent());
	}

	private void setArtifact(T artifact) {
		this.artifact = artifact;
	}

	private CodeArtifact getArtifact() {
		return artifact;
	}

	private void setIndent(Integer indent) {
		this.indent = indent;
	}

	private Integer getIndent() {
		return indent;
	}

	public Boolean hasContentToWrite() {
		return this.getArtifact().hasContentToWrite();
	}

}
