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

import java.util.List;

import org.webspeclanguage.mockupdd.codegen.artifacts.Code;
import org.webspeclanguage.mockupdd.codegen.artifacts.CodeBlock;
import org.webspeclanguage.mockupdd.codegen.artifacts.CodeFile;
import org.webspeclanguage.mockupdd.codegen.artifacts.Line;
import org.webspeclanguage.mockupdd.codegen.common.DefaultWidgetGenerator;
import org.webspeclanguage.mockupdd.codegen.common.PageCodeGenerator;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodeArtifact;
import org.webspeclanguage.mockupdd.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.CheckBox;
import org.webspeclanguage.mockupdd.sui.model.ComboBox;
import org.webspeclanguage.mockupdd.sui.model.DatePicker;
import org.webspeclanguage.mockupdd.sui.model.Image;
import org.webspeclanguage.mockupdd.sui.model.Label;
import org.webspeclanguage.mockupdd.sui.model.Link;
import org.webspeclanguage.mockupdd.sui.model.NumericSpinner;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.RadioButton;
import org.webspeclanguage.mockupdd.sui.model.SelectableList;
import org.webspeclanguage.mockupdd.sui.model.SuiModelElement;
import org.webspeclanguage.mockupdd.sui.model.Table;
import org.webspeclanguage.mockupdd.sui.model.TextArea;
import org.webspeclanguage.mockupdd.sui.model.TextBox;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.AbsoluteLayoutInfo;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayout;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutCell;
import org.webspeclanguage.mockupdd.sui.model.layout.GridBagLayoutVisitor;
import org.webspeclanguage.mockupdd.utils.SuiVisitor;

/**
 * @author Jose Matias Rivero
 */
public class ExtJsJavaScriptGenerator extends 
    DefaultWidgetGenerator<CodeArtifact> implements 
		SuiVisitor<CodeArtifact>,
		GridBagLayoutVisitor<CodeArtifact, CodeArtifact>,
    PageCodeGenerator<CodeFile<CodeArtifact>>{

  private MainPanelAttributesGenerator mainPanelAttributesGenerator;
  
	public ExtJsJavaScriptGenerator() {
    super();
    this.mainPanelAttributesGenerator = new MainPanelAttributesGenerator();
  }

  public CodeFile<CodeArtifact> generateFrom(Page p) {
		String pageIdentifier = CodegenUtil.convertToIdentifier(p.getTitle());
		String escapedTitle = CodegenUtil.escapeExcludingBlanks(p.getTitle());
		CodeArtifact ctrls = p.getLayout().accept(this);
		this.mainPanelAttributesGenerator.setWidget(p);
		return
  		Code.file(pageIdentifier + ".js",
  		  (CodeArtifact)
    		Code.mixedBlock(
      			"Ext.onReady(function() {",
      			Code.indent(Code.block(ctrls), 1),
      			"   new Ext.Panel({",
      			"      contentEl: 'main',",
      			"      frame: 'true',",
      			"      renderTo: Ext.getBody(),",
      			"      title: '" + escapedTitle + "'",
      			p.accept(this.mainPanelAttributesGenerator),
      			"   });", 
      			"});"
    			)
  		);
	}

	public CodeBlock<CodeArtifact> visitButton(Button button) {
		return 
			this.extjsWidget(button, "Button", Code.lBlock(
				"text: \"" + CodegenUtil.escapeExcludingBlanks(button.getText()) + "\""
			));
	}

	public CodeBlock<CodeArtifact> visitComboBox(ComboBox comboBox) {
		return 
			this.extjsWidget(comboBox, "form.ComboBox", Code.lBlock(
				"store: sampleStore,",
				"mode: \"local\",",
				"valueField: \"id\",",
				"displayField: \"value\""
			));
	}

	public CodeBlock<CodeArtifact> visitDatePicker(DatePicker datePicker) {
		return 
			this.extjsWidget(datePicker, "form.DateField", Code.lBlock());
	}

	public CodeBlock<CodeArtifact> visitLabel(Label label) {
		return 
			this.extjsWidget(label, "form.Label", Code.lBlock(
				"text: \"" + label.getText() + "\""
		));
	}
	
	

	public CodeBlock<CodeArtifact> visitList(org.webspeclanguage.mockupdd.sui.model.List list) {
		return 
			this.extjsWidget(list, "list.ListView", Code.lBlock(
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
		for (SuiModelElement c : panel.getWidgets()) {
			cb.add(c.accept(this));
		}
		return cb;
	}

	public CodeBlock<CodeArtifact> visitTextBox(TextBox textBox) {
		return 
			this.extjsWidget(textBox, "form.TextField", Code.lBlock(
        "width: " + textBox.getWidth()
			));
	}
	
	public CodeBlock<CodeArtifact> visitTable(Table table) {
		return
			this.extjsWidget(table, "grid.GridPanel", Code.lBlock(
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
      this.extjsWidget(numericSpinner, "ux.form.SpinnerField", Code.lBlock());
  }

  @Override
  public CodeArtifact visitSelectableList(SelectableList selectableList) {
    return 
      this.visitList(selectableList);
  }

  @Override
  public CodeArtifact visitTextArea(TextArea textArea) {
    return
      this.extjsWidget(textArea, "form.TextArea", Code.lBlock());
  }

  public CodeBlock<CodeArtifact> visitCheckBox(CheckBox checkBox) {
		return
			this.extjsWidget(checkBox, "form.Checkbox", Code.lBlock(
			  "boxLabel: \"" + checkBox.getText() + "\""
			));
	}
	
	public CodeBlock<CodeArtifact> visitRadioButton(RadioButton radioButton) {
		return
		this.extjsWidget(radioButton, "form.Radio", Code.lBlock(
			    "boxLabel: \"" + radioButton.getText() + "\""
			));
	}

	private CodeBlock<CodeArtifact> extjsWidget(Widget c, String widgetName, CodeBlock<Line> lBlock) {
		return Code.mixedBlock(
      "var " + this.getId(c) + " = new Ext." + widgetName + "({",
      Code.indent(Code.mixedBlock(
        "renderTo: \"" + this.getContainerId(c) + "\",",
        "id: \"" + this.getId(c) + "\"" + 
        (lBlock.hasContentToWrite() ? "," : ""),
        lBlock
      ), 1),
      "});");
	}

	public CodeArtifact visitCell(GridBagLayoutCell c) {
		if (c.hasWidget()) {
			return c.getWidget().accept(this);
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
	
	@Override
  public CodeArtifact visitGridBagLayout(GridBagLayout gbl) {
    return Code.block(gbl.visitByRows(this));
  }

  @Override
  public CodeArtifact visitAbsoluteLayout(AbsoluteLayout absoluteLayout) {
    CodeBlock<CodeArtifact> cb = Code.block();
    for (Widget c : absoluteLayout.getWidgets()) {
      cb.add(c.accept(this));
    }
    return cb;
  }
  
  @Override
  public CodeArtifact visitAbsoluteLayoutInfo(AbsoluteLayoutInfo ali) {
    return ali.getWidget().accept(this);
  }

  private String getId(Widget c) {
		return "ctl" + c.getWidgetId() + "widget";	
	}
	
	private String getContainerId(Widget c) {
		return "ctl" + c.getWidgetId() + "-container";	
	}

	public CodeArtifact getDefault() {
		return Code.nullCode();
	}
	
	private class MainPanelAttributesGenerator extends DefaultWidgetGenerator<CodeArtifact> {

	  private Widget widget;
	  
	  public MainPanelAttributesGenerator(Widget widget) {
      super();
      this.widget = widget;
    }
	  
	  public MainPanelAttributesGenerator() {
	    this(null);
	  }
	  
	  public void setWidget(Widget widget) {
	    this.widget = widget;
	  }

    @Override
    public CodeArtifact getDefault() {
      return Code.block();
    }

    @Override
    public CodeArtifact visitAbsoluteLayout(AbsoluteLayout absoluteLayout) {
      AbsoluteLayoutInfo info = absoluteLayout.getInfoForWidget(this.widget);
      return Code.mixedBlock(
        "      layout: 'absolute',",
        "      width: " + info.getWidth() + ",",
        "      height: " + info.getHeight()
      );
    }

	}

}
