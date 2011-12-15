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
package org.webspeclanguage.mockupdd.codegen.extjs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.webspeclanguage.mockupdd.codegen.artifacts.Code;
import org.webspeclanguage.mockupdd.codegen.artifacts.CodeBlock;
import org.webspeclanguage.mockupdd.codegen.common.DefaultWidgetGenerator;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayoutInfo;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutCell;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutVisitor;

/**
 * Generates HTML content to be enriched with ExtJS library
 * 
 * @author Jose Matias Rivero
 */
public class ExtJsHtmlGenerator extends 
    DefaultWidgetGenerator<CodeArtifact> implements
		GridBagLayoutVisitor<CodeArtifact, CodeArtifact> {

	private List<Widget> processedWidgets;

	public ExtJsHtmlGenerator() {
		this.setProcessedWidgets(new ArrayList<Widget>());
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
		if (c.getWidget() != null) {
			if (!this.getProcessedWidgets().contains(c.getWidget())) {
				this.getProcessedWidgets().add(c.getWidget());
				return Code.mixedBlock(
						"<td id=\"ctl" + c.getWidget().getWidgetId() + "-container\" " +
							(c.getColumnSpan() > 1 ? "colspan=\""  + c.getColumnSpan() + "\" " : "") +
							(c.getRowSpan() > 1 ? "rowspan=\"" + c.getRowSpan() + "\" ": "")  + ">",
						Code.indent(c.getWidget().accept(this), 1),
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

	private void setProcessedWidgets(List<Widget> processedWidgets) {
		this.processedWidgets = processedWidgets;
	}

	
	private List<Widget> getProcessedWidgets() {
		return processedWidgets;
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
      "<div id=\"ctl" + ali.getWidget().getWidgetId() + "-container\">",
      Code.indent(ali.getWidget().accept(this), 1),
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
