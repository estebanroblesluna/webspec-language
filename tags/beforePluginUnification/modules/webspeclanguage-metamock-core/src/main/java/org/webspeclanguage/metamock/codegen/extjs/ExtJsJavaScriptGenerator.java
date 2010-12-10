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

import java.util.List;

import org.webspeclanguage.metamock.codegen.artifacts.Code;
import org.webspeclanguage.metamock.codegen.artifacts.CodeBlock;
import org.webspeclanguage.metamock.codegen.artifacts.CodeFile;
import org.webspeclanguage.metamock.codegen.artifacts.Line;
import org.webspeclanguage.metamock.codegen.common.DefaultMetaMockControlGenerator;
import org.webspeclanguage.metamock.codegen.common.MetaMockPageCodeGenerator;
import org.webspeclanguage.metamock.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.metamock.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.metamock.model.Button;
import org.webspeclanguage.metamock.model.CheckBox;
import org.webspeclanguage.metamock.model.ComboBox;
import org.webspeclanguage.metamock.model.DatePicker;
import org.webspeclanguage.metamock.model.Image;
import org.webspeclanguage.metamock.model.Label;
import org.webspeclanguage.metamock.model.Link;
import org.webspeclanguage.metamock.model.MetaMockElement;
import org.webspeclanguage.metamock.model.NumericSpinner;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.Panel;
import org.webspeclanguage.metamock.model.RadioButton;
import org.webspeclanguage.metamock.model.SelectableList;
import org.webspeclanguage.metamock.model.Table;
import org.webspeclanguage.metamock.model.TextArea;
import org.webspeclanguage.metamock.model.TextBox;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.layout.GridBagLayout;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutCell;
import org.webspeclanguage.metamock.model.layout.GridBagLayoutVisitor;
import org.webspeclanguage.metamock.utils.MetaMockVisitor;

/**
 * @author Jose Matias Rivero
 */
public class ExtJsJavaScriptGenerator extends 
    DefaultMetaMockControlGenerator<CodeArtifact> implements 
		MetaMockVisitor<CodeArtifact>,
		GridBagLayoutVisitor<CodeArtifact, CodeArtifact>,
    MetaMockPageCodeGenerator<CodeFile<CodeArtifact>> {

	public CodeFile<CodeArtifact> generateFrom(Page p) {
		String pageIdentifier = CodegenUtil.convertToIdentifier(p.getTitle());
		String escapedTitle = CodegenUtil.escapeExcludingBlanks(p.getTitle());
		GridBagLayout t = (GridBagLayout) p.getLayout();
		List<CodeArtifact> ctrls = t.visitByRows(this);
		
		return
  		Code.file(pageIdentifier + ".js",
  		  (CodeArtifact)
    		Code.mixedBlock(
      			"Ext.onReady(function() {",
      			Code.indent(Code.block(ctrls), 1),
      			"	new Ext.Panel({",
      			"		contentEl: \"main\",",
      			"		frame: \"true\",",
      			"		renderTo: Ext.getBody(),",
      			"		title: '" + escapedTitle + "'",
      			"	});", 
      			"});"
    			)
  		);
	}

	public CodeBlock<CodeArtifact> visitButton(Button button) {
		return 
			this.extjsControl(button, "Button", Code.lBlock(
				"text: \"" + CodegenUtil.escape(button.getText()) + "\""
			));
	}

	public CodeBlock<CodeArtifact> visitComboBox(ComboBox comboBox) {
		return 
			this.extjsControl(comboBox, "form.ComboBox", Code.lBlock(
				"store: sampleStore,",
				"mode: \"local\",",
				"valueField: \"id\",",
				"displayField: \"value\""
			));
	}

	public CodeBlock<CodeArtifact> visitDatePicker(DatePicker datePicker) {
		return 
			this.extjsControl(datePicker, "DatePicker", Code.lBlock());
	}

	public CodeBlock<CodeArtifact> visitLabel(Label label) {
		return 
			this.extjsControl(label, "form.Label", Code.lBlock(
				"text: \"" + label.getText() + "\""
		));
	}
	
	

	public CodeBlock<CodeArtifact> visitList(org.webspeclanguage.metamock.model.List list) {
		return 
			this.extjsControl(list, "list.ListView", Code.lBlock(
		    	"store: sampleStore,",
		    	"hideHeaders: true,",
		    	"multiSelect: true,",
		    	"reserveScrollOffset: true,",
		    	"width: 300,",
		    	"columns: [{",
		    	"	dataIndex: \"value\"",
	    		"}]"));
	}

	public CodeBlock<CodeArtifact> visitPage(Page page) {
		return Code.block(); 
	}

	public CodeBlock<CodeArtifact> visitPanel(Panel panel) {
		CodeBlock<CodeArtifact> cb = Code.block();
		cb.add(Code.line("var " + this.getId(panel) + 
				" = Ext.DomHelper.append(\"" + this.getContainerId(panel) + "\", " +
			  "{" +
				"id: \"" + this.getId(panel) + "\", " +
				"tag:\"div\", " +
				"href: \"#\"" +
			"})"));
		for (MetaMockElement c : panel.getControls()) {
			cb.add(c.visit(this));
		}
		return cb;
	}

	public CodeBlock<CodeArtifact> visitTextBox(TextBox textBox) {
		return 
			this.extjsControl(textBox, "form.TextField", Code.lBlock());
	}
	
	public CodeBlock<CodeArtifact> visitTable(Table table) {
		return
			this.extjsControl(table, "grid.GridPanel", Code.lBlock(
        "store: sampleStore,",
        "colModel: new Ext.grid.ColumnModel({",
        "	columns: [",
        "		{header: \"col1\", dataIndex: \"id\", resizable: false},",
        "		{header: \"col2\", dataIndex: \"value\", resizable: false},",
        "	]",
        "}),",
        "sm: new Ext.grid.RowSelectionModel({singleSelect:true}),",
        "height: 100"
			));
	}
	
  public CodeArtifact visitNumericSpinner(NumericSpinner numericSpinner) {
    return
      this.extjsControl(numericSpinner, "ux.form.SpinnerField", Code.lBlock());
  }

  @Override
  public CodeArtifact visitSelectableList(SelectableList selectableList) {
    return 
      this.visitList(selectableList);
  }

  @Override
  public CodeArtifact visitTextArea(TextArea textArea) {
    return
      this.extjsControl(textArea, "form.TextArea", Code.lBlock());
  }

  public CodeBlock<CodeArtifact> visitCheckBox(CheckBox checkBox) {
		return
			this.extjsControl(checkBox, "form.Checkbox", Code.lBlock(
			  "boxLabel: \"" + checkBox.getText() + "\""
			));
	}
	
	public CodeBlock<CodeArtifact> visitRadioButton(RadioButton radioButton) {
		return
		this.extjsControl(radioButton, "form.Radio", Code.lBlock(
			    "boxLabel: \"" + radioButton.getText() + "\""
			));
	}

	private CodeBlock<CodeArtifact> extjsControl(UIControl c, String controlName, CodeBlock<Line> lBlock) {
		return Code.mixedBlock(
      "var " + this.getId(c) + " = new Ext." + controlName + "({",
      Code.indent(Code.mixedBlock(
        "renderTo: \"" + this.getContainerId(c) + "\",",
        "id: \"" + this.getId(c) + "\"" + 
        (lBlock.hasContentToWrite() ? "," : ""),
        lBlock
      ), 1),
      "});");
	}

	public CodeArtifact visitCell(GridBagLayoutCell c) {
		if (c.hasControl()) {
			return c.getControl().visit(this);
		} else {
			return Code.nullCode();
		}
	}

	public CodeArtifact visitRow(Integer columnIndex,
			List<CodeArtifact> visitedRowContent) {
		return Code.block(visitedRowContent);
	}

	public CodeArtifact visitImage(Image image) {
		return 
		Code.line("var " + this.getId(image) + 
				" = Ext.DomHelper.append(\"" + this.getContainerId(image) + "\", " +
			"{" +
				"id: \"" + this.getId(image) + "\", " +
				"tag:\"img\", " +
				"src: \"" + image.getImageUrl() + "\", " +
			"})");
	}

	public CodeArtifact visitLink(Link link) {
		return 
			Code.line("var " + this.getId(link) + 
					" = Ext.DomHelper.append(\"" + this.getContainerId(link) + "\", " +
				"{" +
					"id: \"" + this.getId(link) + "\", " +
					"tag:\"a\", " +
					"href: \"#\", " +
					"html:\"" + link.getText() + "\"" +
				"})");
	}
	
	private String getId(UIControl c) {
		return "ctl" + c.getControlId() + "control";	
	}
	
	private String getContainerId(UIControl c) {
		return "ctl" + c.getControlId() + "-container";	
	}

	public CodeArtifact getDefault() {
		return Code.nullCode();
	}

}
