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
package org.webspeclanguage.metamock.codegen.extjs;

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.metamock.codegen.artifacts.Code;
import org.webspeclanguage.metamock.codegen.common.DefaultMetaMockControlGenerator;
import org.webspeclanguage.metamock.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.metamock.model.CompositeControl;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.Panel;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutCell;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutVisitor;

/**
 * @author Jose Matias Rivero
 */
public class ExtJsHtmlGenerator extends 
    DefaultMetaMockControlGenerator<CodeArtifact> implements
		GridBagLayoutVisitor<CodeArtifact, CodeArtifact> {

	private List<UIControl> processedControls;

	public ExtJsHtmlGenerator() {
		this.setProcessedControls(new ArrayList<UIControl>());
	}

	public CodeArtifact generateFor(Page page) {
		return this.generateTable(page, "main");
	}
	
	private CodeArtifact generateTable(CompositeControl c, String id) {
		return Code.mixedBlock(
				"<table" + ((id != null) ? " id=\"main\"" : "") + ">",
				Code.indent(Code.block(((GridBagLayout)c.getLayout()).visitByRows(this)), 1),
				"</table>"
		);
	}

	public CodeArtifact visitCell(GridBagLayoutCell c) {
		if (c.getControl() != null) {
			if (!this.getProcessedControls().contains(c.getControl())) {
				this.getProcessedControls().add(c.getControl());
				return Code.mixedBlock(
						"<td id=\"ctl" + c.getControl().getControlId() + "-container\" " +
							(c.getColumnSpan() > 1 ? "colspan=\""  + c.getColumnSpan() + "\" " : "") +
							(c.getRowSpan() > 1 ? "rowspan=\"" + c.getRowSpan() + "\" ": "")  + ">",
						Code.indent(c.getControl().visit(this), 1),
						"</td>");
			} else {
				return Code.line("");
			}
		} else {
			return Code.line("<td></td>");
		}
	}

	public CodeArtifact visitRow(Integer columnIndex,
			List<CodeArtifact> visitedRowContent) {
		return 
			Code.mixedBlock(
				"<tr>",
				Code.indent(Code.block(visitedRowContent), 1),
				"</tr>");
	}

	private void setProcessedControls(List<UIControl> processedControls) {
		this.processedControls = processedControls;
	}

	private List<UIControl> getProcessedControls() {
		return processedControls;
	}

	public CodeArtifact visitPanel(Panel panel) {
		return Code.indent(this.generateTable(panel, null), 1);
	}

	public CodeArtifact getDefault() {
		return Code.nullCode();
	}

}
