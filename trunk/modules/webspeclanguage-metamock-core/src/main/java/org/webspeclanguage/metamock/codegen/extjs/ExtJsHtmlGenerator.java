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
import java.util.Iterator;
import java.util.List;

import org.webspeclanguage.metamock.codegen.artifacts.Code;
import org.webspeclanguage.metamock.codegen.artifacts.CodeBlock;
import org.webspeclanguage.metamock.codegen.common.DefaultWidgetGenerator;
import org.webspeclanguage.metamock.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.Panel;
import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.model.layout.AbsoluteLayout;
import org.webspeclanguage.metamock.model.layout.AbsoluteLayoutInfo;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutCell;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutVisitor;

/**
 * Generates HTML content to be enriched with ExtJS library
 * 
 * @author Jose Matias Rivero
 */
public class ExtJsHtmlGenerator extends 
    DefaultWidgetGenerator<CodeArtifact> implements
		GridBagLayoutVisitor<CodeArtifact, CodeArtifact> {

	private List<Widget> processedControls;

	public ExtJsHtmlGenerator() {
		this.setProcessedControls(new ArrayList<Widget>());
	}

	public CodeArtifact generateFor(Page page) {
	  return Code.mixedBlock(
      "<div id=\"main\">",
      page.getLayout().accept(this),
      "</div>");
	}
	
	private CodeArtifact generateTable(GridBagLayout gbl) {
		return Code.mixedBlock(
				"<table>",
				Code.indent(Code.block((gbl).visitByRows(this)), 1),
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
						Code.indent(c.getControl().accept(this), 1),
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

	private void setProcessedControls(List<Widget> processedControls) {
		this.processedControls = processedControls;
	}

	
	private List<Widget> getProcessedControls() {
		return processedControls;
	}

	@Override
	public CodeArtifact visitPanel(Panel panel) {
		return Code.indent(panel.getLayout().accept(this), 1);
	}

	@Override
	public CodeArtifact getDefault() {
		return Code.nullCode();
	}

  @Override
  public CodeArtifact visitGridBagLayout(GridBagLayout gbl) {
    return this.generateTable(gbl);
  }

  @Override
  public CodeArtifact visitAbsoluteLayoutInfo(AbsoluteLayoutInfo ali) {
    return Code.mixedBlock(
      "<div id=\"ctl" + ali.getControl().getControlId() + "-container\">",
      Code.indent(ali.getControl().accept(this), 1),
      "</div>"
    );
  }

  @Override
  public CodeArtifact visitAbsoluteLayout(AbsoluteLayout absoluteLayout) {
    CodeBlock<CodeArtifact> cb = Code.block();
    for (AbsoluteLayoutInfo ali : absoluteLayout.getAllLayoutInfo()) {
      cb.add(ali.accept(this));
    }
    return cb;
  }

}
