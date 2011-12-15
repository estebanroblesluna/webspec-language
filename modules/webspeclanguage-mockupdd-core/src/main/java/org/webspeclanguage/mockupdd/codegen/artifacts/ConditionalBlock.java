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
package org.webspeclanguage.mockupdd.codegen.artifacts;

import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeWriter;

/**
 * @author Jose Matias Rivero
 */
public class ConditionalBlock<T extends CodeArtifact> implements CodeArtifact {

	private Boolean write;
	private CodeArtifact content;

	public ConditionalBlock(Boolean write, CodeArtifact content) {
		super();
		this.setWrite(write);
		this.setContent(content);
	}

	public Boolean hasContentToWrite() {
		if (this.getWrite() == false) {
			return false;
		}
		return this.getContent().hasContentToWrite();	
	}

	public void writeOn(CodeWriter w) {
		if (this.getWrite()) {
			this.getContent().writeOn(w);
		}
	}

	private void setWrite(Boolean write) {
		this.write = write;
	}

	private Boolean getWrite() {
		return write;
	}

	private void setContent(CodeArtifact content) {
		this.content = content;
	}

	private CodeArtifact getContent() {
		return content;
	}

  @Override
  public String toString() {
    if (this.getWrite()) {
      return this.getContent().toString();
    }
    return "";
  }
	

}
