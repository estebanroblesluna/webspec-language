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
package org.webspeclanguage.metamock.translator.balsamiq;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.webspeclanguage.metamock.model.Button;
import org.webspeclanguage.metamock.model.ComboBox;
import org.webspeclanguage.metamock.model.DatePicker;
import org.webspeclanguage.metamock.model.Image;
import org.webspeclanguage.metamock.model.Label;
import org.webspeclanguage.metamock.model.MetaMockFactory;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.Panel;
import org.webspeclanguage.metamock.model.SelectableList;
import org.webspeclanguage.metamock.model.Table;
import org.webspeclanguage.metamock.model.TextBox;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.translator.DefaultMetaMockControlGroupImpl;
import org.webspeclanguage.metamock.translator.MetaMockControlGroup;
import org.webspeclanguage.metamock.translator.MetaMockControlParser;

/**
 * {@link MetaMockControlParser} implementation for parsing mockup controls from Balsamiq files
 * 
 * @author Jose Matias Rivero
 */
public class BalsamiqControlParser implements MetaMockControlParser<File> {

	private static final String CONTROL_PROPERTIES = "controlProperties";
  private MetaMockFactory factory;
	private File currentFile;

	public BalsamiqControlParser(MetaMockFactory factory) {
		this.setFactory(factory);
	}

	private void setFactory(MetaMockFactory factory) {
		this.factory = factory;
	}

	private void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
	}

	private File getCurrentFile() {
		return currentFile;
	}

	private MetaMockFactory getFactory() {
		return factory;
	}

	private String getControlId(Element e) {
		return e.getAttributeValue("controlID");
	}

	private UIControl parseElement(Element e) {
		String controlType = e.getAttribute("controlTypeID").getValue();
		if (controlType.equals("com.balsamiq.mockups::Button")) { 
			return this.parseButton(e);
		}
		if (controlType.equals("com.balsamiq.mockups::TextInput")
				|| controlType.equals("com.balsamiq.mockups::SearchBox")) {
			return this.parseTextBox(e);
		};
		if (controlType.equals("com.balsamiq.mockups::TextArea")) {
			return this.parseTextArea(e);
		}
		if (controlType.equals("com.balsamiq.mockups::NumericStepper")) {
			return this.parseNumericSpinner(e);
		}
		if (controlType.equals("com.balsamiq.mockups::Label")) {
			return this.parseLabel(e);
		};
		if (controlType.equals("com.balsamiq.mockups::Canvas")) {
			return this.parsePanel(e);
		}
		if (controlType.equals("com.balsamiq.mockups::DateChooser")) {
			return this.parseDatePicker(e);
		}
		if (controlType.equals("com.balsamiq.mockups::List")) {
			return this.parseList(e);
		}
		if (controlType.equals("com.balsamiq.mockups::CheckBoxGroup")) {
			return this.parseSelectableList(e);
		}
		if (controlType.equals("com.balsamiq.mockups::ComboBox")) {
			return this.parseComboBox(e);
		}
		if (controlType.equals("com.balsamiq.mockups::DataGrid")) {
			return this.parseTable(e);
		}
		if (controlType.equals("com.balsamiq.mockups::Image")
				|| controlType.equals("com.balsamiq.mockups::LineChart")
				|| controlType.equals("com.balsamiq.mockups::PieChart")) {
			return this.parseImage(e);
		}
		if (controlType.equals("com.balsamiq.mockups::CheckBox")) {
			return this.parseCheckBox(e);
		}
		if (controlType.equals("com.balsamiq.mockups::RadioButton")) {
			return this.parseRadioButton(e);
		}
		if (controlType.equals("com.balsamiq.mockups::TitleWindow")) {
			return this.parsePage(e);
		}
		if (controlType.equals("com.balsamiq.mockups::Link")) {
			return this.parseLink(e);
		}
		if (controlType.equals("com.balsamiq.mockups::CallOut")) {
			return this.parseAnnotation(e);
		}
		return null;
	}

	private UIControl parseNumericSpinner(Element e) {
		return this.getFactory().createNumericSpinner(this.getControlId(e),
				this.getX(e), this.getY(e), this.getWidth(e),
				this.getHeight(e), null, null);
	}

	private UIControl parseTextArea(Element e) {
		return this.getFactory()
				.createTextArea(this.getControlId(e), this.getX(e),
						this.getY(e), this.getWidth(e), this.getHeight(e));
	}

	private SelectableList parseSelectableList(Element e) {
		return this.getFactory().createSelectableList(this.getControlId(e),
				this.getX(e), this.getY(e), this.getWidth(e),
				this.getHeight(e), false);
	}

	private UIControl parseAnnotation(Element e) {
		return this.getFactory().createAnnotation(
				this.getControlId(e),
				this.getX(e),
				this.getY(e),
				this.getWidth(e),
				this.getHeight(e),
				null,
				this.urlDecode(this.getChildText(e)));
	}

	private UIControl parseLink(Element e) {
		return this.getFactory().createLink(
				this.getControlId(e),
				this.getX(e),
				this.getY(e),
				this.getWidth(e),
				this.getHeight(e),
				this.urlDecode(this.getChildText(e)));
	}

	private UIControl parseRadioButton(Element e) {
		return this.getFactory().createRadioButton(
				this.getControlId(e),
				this.getX(e),
				this.getY(e),
				this.getWidth(e),
				this.getHeight(e),
				this.urlDecode(this.getChildText(e)));
	}

	private UIControl parseCheckBox(Element e) {
		return this.getFactory().createCheckBox(
				this.getControlId(e),
				this.getX(e),
				this.getY(e),
				this.getWidth(e),
				this.getHeight(e),
				this.urlDecode(this.getChildText(e)));
	}

	private int getX(Element e) {
		return Integer.parseInt(e.getAttribute("x").getValue());
	}

	private int getY(Element e) {
		return Integer.parseInt(e.getAttribute("y").getValue());
	}

	private int getWidth(Element e) {
		Integer width = Integer.parseInt(e.getAttribute("w").getValue());
		if (width == -1) {
			width = Integer.parseInt(e.getAttribute("measuredW").getValue());
		}
		return width;
	}

	private int getHeight(Element e) {
		Integer height = Integer.parseInt(e.getAttribute("h").getValue());
		if (height == -1) {
			height = Integer.parseInt(e.getAttribute("measuredH").getValue());
		}
		return height;
	}

	public final Button parseButton(Element e) {
		return this.getFactory().createButton(
				this.getControlId(e),
				this.getX(e),
				this.getY(e),
				this.getWidth(e),
				this.getHeight(e),
				this.urlDecode(this.getChildText(e)));
	}

	public final Panel parsePanel(Element e) {
		return this.getFactory().createPanel(this.getControlId(e),
				this.getX(e), this.getY(e), this.getWidth(e),
				this.getHeight(e),
				this.getCurrentFile().getName().split("\\.")[0]);
	}

	public final Page parsePage(Element e) {
		return this.getFactory().createPage(
				this.getControlId(e),
				this.getX(e),
				this.getY(e),
				this.getWidth(e),
				this.getHeight(e),
				this.urlDecode(this.getChildText(e)),
				this.getCurrentFile().getName().split("\\.")[0]);
	}

	public final TextBox parseTextBox(Element e) {
		return this.getFactory()
				.createTextBox(this.getControlId(e), this.getX(e),
						this.getY(e), this.getWidth(e), this.getHeight(e));
	}

	public final Label parseLabel(Element e) {
		return this.getFactory().createLabel(
				this.getControlId(e),
				this.getX(e),
				this.getY(e),
				this.getWidth(e),
				this.getHeight(e),
				this.urlDecode(this.getChildText(e)));
	}

	public final DatePicker parseDatePicker(Element e) {
		return this.getFactory()
				.createDatePicker(this.getControlId(e), this.getX(e),
						this.getY(e), this.getWidth(e), this.getHeight(e));
	}

	public final Image parseImage(Element e) {
		String src = null;
		if (e.getChild(CONTROL_PROPERTIES) != null) {
			src = e.getChild(CONTROL_PROPERTIES).getAttributeValue("src");
		}
		if (src == null) {
			src = "";
		} else {
			src = this.urlDecode(src);
		}
		return this.getFactory().createImage(this.getControlId(e),
				this.getX(e), this.getY(e), this.getWidth(e),
				this.getHeight(e), src);
	}

	public final org.webspeclanguage.metamock.model.List parseList(Element e) {
		return this.getFactory().createList(this.getControlId(e), this.getX(e),
				this.getY(e), this.getWidth(e), this.getHeight(e));
	}

	public final ComboBox parseComboBox(Element e) {
		return this.getFactory()
				.createComboBox(this.getControlId(e), this.getX(e),
						this.getY(e), this.getWidth(e), this.getHeight(e));
	}

	private UIControl parseTable(Element e) {
		Table t = this.getFactory()
				.createTable(this.getControlId(e), this.getX(e), this.getY(e),
						this.getWidth(e), this.getHeight(e));
		String[] cols = this.urlDecode(
				this.getChildText(e)).split(
				"\n")[0].split(",");
		for (String col : cols) {
			// create columns, removing "^" and "\r" marks
			t.addColumn(this.getFactory().createTableColumn(
					col.trim().replaceAll("(\\^|\\\\r)", "")));
		}
		return t;
	}

	private String getChildText(Element e) {
		return e.getChild(CONTROL_PROPERTIES).getChildText("text");
	}

	private String urlDecode(String urlEncodedString) {
		try {
			return URLDecoder.decode(urlEncodedString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public final Collection<MetaMockControlGroup> parseControls(File source) {
		DefaultMetaMockControlGroupImpl group = new DefaultMetaMockControlGroupImpl(
				new ArrayList<UIControl>());
		try {
			SAXBuilder builder = new SAXBuilder();
			this.setCurrentFile(source);
			Document d = builder.build(source);
			for (Object e : d.getRootElement().getChild("controls")
					.getChildren()) {
				UIControl c = this.parseElement((Element) e);
				this.addFriendlyIdIfPresent(c, (Element) e);
				if (c != null) {
					group.addControl(c);
					c = this.customData2Annotation((Element) e, c);
					if (c != null) {
						group.addControl(c);
					}
				}
			}
			return new ArrayList<MetaMockControlGroup>(Arrays
					.asList(new MetaMockControlGroup[] { group }));
		} catch (Exception e) { }
		return new ArrayList<MetaMockControlGroup>();
	}

	private void addFriendlyIdIfPresent(UIControl c, Element e) {
		Element idElem = e.getChild(CONTROL_PROPERTIES);
		if (idElem != null) {
			idElem = idElem.getChild("customID");
		}
		if (idElem != null) {
			c.setFriendlyId(idElem.getText());
		}
	}

	private UIControl customData2Annotation(Element e, UIControl c) {
		Element data = e.getChild(CONTROL_PROPERTIES);
		if (data != null) {
			data = data.getChild("customData");
		}
		if (data != null) {
		  if (data.getText().trim().length() > 0) {
  			return this.getFactory().createAnnotation(
  					c.getControlId() + "Annotation", c.getX(), c.getY(),
  					c.getWidth(), c.getHeight(), c,
  					this.urlDecode(this.urlDecode(data.getText())));
		  }
		}
		return null;
	}

}
