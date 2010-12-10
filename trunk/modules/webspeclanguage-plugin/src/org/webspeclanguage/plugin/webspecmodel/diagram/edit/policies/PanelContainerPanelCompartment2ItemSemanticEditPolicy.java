package org.webspeclanguage.plugin.webspecmodel.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.ButtonCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.CheckBoxCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.ComboBoxCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.LabelCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.LinkCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.ListBoxCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.ListOfContainerCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.PanelContainer2CreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.RadioButtonCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.commands.TextFieldCreateCommand;
import org.webspeclanguage.plugin.webspecmodel.diagram.providers.WebspecmodelElementTypes;


/**
 * @generated
 */
public class PanelContainerPanelCompartment2ItemSemanticEditPolicy extends WebspecmodelBaseItemSemanticEditPolicy {

  /**
   * @generated
   */
  public PanelContainerPanelCompartment2ItemSemanticEditPolicy() {
    super(WebspecmodelElementTypes.PanelContainer_3010);
  }

  /**
   * @generated
   */
  protected Command getCreateCommand(CreateElementRequest req) {
    if (WebspecmodelElementTypes.Button_3002 == req.getElementType()) {
      return getGEFWrapper(new ButtonCreateCommand(req));
    }
    if (WebspecmodelElementTypes.CheckBox_3003 == req.getElementType()) {
      return getGEFWrapper(new CheckBoxCreateCommand(req));
    }
    if (WebspecmodelElementTypes.ComboBox_3004 == req.getElementType()) {
      return getGEFWrapper(new ComboBoxCreateCommand(req));
    }
    if (WebspecmodelElementTypes.Label_3005 == req.getElementType()) {
      return getGEFWrapper(new LabelCreateCommand(req));
    }
    if (WebspecmodelElementTypes.Link_3006 == req.getElementType()) {
      return getGEFWrapper(new LinkCreateCommand(req));
    }
    if (WebspecmodelElementTypes.ListBox_3007 == req.getElementType()) {
      return getGEFWrapper(new ListBoxCreateCommand(req));
    }
    if (WebspecmodelElementTypes.RadioButton_3008 == req.getElementType()) {
      return getGEFWrapper(new RadioButtonCreateCommand(req));
    }
    if (WebspecmodelElementTypes.TextField_3009 == req.getElementType()) {
      return getGEFWrapper(new TextFieldCreateCommand(req));
    }
    if (WebspecmodelElementTypes.PanelContainer_3010 == req.getElementType()) {
      return getGEFWrapper(new PanelContainer2CreateCommand(req));
    }
    if (WebspecmodelElementTypes.ListOfContainer_3011 == req.getElementType()) {
      return getGEFWrapper(new ListOfContainerCreateCommand(req));
    }
    return super.getCreateCommand(req);
  }

}
