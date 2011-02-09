package org.webspeclanguage.api.utils;

import org.webspeclanguage.impl.widget.Button;
import org.webspeclanguage.impl.widget.CheckBox;
import org.webspeclanguage.impl.widget.ComboBox;
import org.webspeclanguage.impl.widget.Label;
import org.webspeclanguage.impl.widget.Link;
import org.webspeclanguage.impl.widget.ListBox;
import org.webspeclanguage.impl.widget.ListOfContainer;
import org.webspeclanguage.impl.widget.Panel;
import org.webspeclanguage.impl.widget.RadioButton;
import org.webspeclanguage.impl.widget.TextField;
/**
 * 
 * A visitor Interface for pattern visitor
 * 
 * @author Matias Urbieta
 *
 */
public interface WidgetVisitor {

	public void visit(TextField textField) ;

	public void visit(Button button);

	public void visit(CheckBox checkBox);

	public void visit(ComboBox comboBox);

	public void visit(Link link);

	public void visit(ListBox listBox);

	public void visit(ListOfContainer listOfContainer);

	public void visit(RadioButton radioButton);

	public void visit(Label label);

	public void visit(Panel panel);
		
	

}
