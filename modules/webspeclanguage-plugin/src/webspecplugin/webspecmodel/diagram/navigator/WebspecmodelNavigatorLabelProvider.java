package webspecplugin.webspecmodel.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import webspecplugin.webspecmodel.Diagram;
import webspecplugin.webspecmodel.ListOfContainer;
import webspecplugin.webspecmodel.PanelContainer;
import webspecplugin.webspecmodel.diagram.edit.parts.ButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ButtonNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.CheckBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.CheckBoxNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ComboBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ComboBoxNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.DiagramEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.InteractionEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.InteractionNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LabelEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LabelNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LinkEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.LinkNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListBoxEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListBoxNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.ListOfContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.NavigationEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.NavigationNamePreconditionActionsEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfArrayEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfArrayNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfNumbersEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfNumbersNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfStringsEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.OneOfStringsNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainer2EditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.PanelContainerEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RIAFeatureEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RIAFeatureNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RadioButtonEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RadioButtonNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RichBehaviorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.RichBehaviorNamePreconditionActionsEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.StringGeneratorEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.StringGeneratorNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.TextFieldEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.TextFieldNameEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.UniformNumberDistributionEditPart;
import webspecplugin.webspecmodel.diagram.edit.parts.UniformNumberDistributionNameEditPart;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelDiagramEditorPlugin;
import webspecplugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;
import webspecplugin.webspecmodel.diagram.providers.WebspecmodelElementTypes;
import webspecplugin.webspecmodel.diagram.providers.WebspecmodelParserProvider;

/**
 * @generated
 */
public class WebspecmodelNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider, ITreePathLabelProvider {

  /**
   * @generated
   */
  static {
    WebspecmodelDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
    WebspecmodelDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
  }

  /**
   * @generated
   */
  public void updateLabel(ViewerLabel label, TreePath elementPath) {
    Object element = elementPath.getLastSegment();
    if (element instanceof WebspecmodelNavigatorItem && !isOwnView(((WebspecmodelNavigatorItem) element).getView())) {
      return;
    }
    label.setText(getText(element));
    label.setImage(getImage(element));
  }

  /**
   * @generated
   */
  public Image getImage(Object element) {
    if (element instanceof WebspecmodelNavigatorGroup) {
      WebspecmodelNavigatorGroup group = (WebspecmodelNavigatorGroup) element;
      return WebspecmodelDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
    }

    if (element instanceof WebspecmodelNavigatorItem) {
      WebspecmodelNavigatorItem navigatorItem = (WebspecmodelNavigatorItem) element;
      if (!isOwnView(navigatorItem.getView())) {
        return super.getImage(element);
      }
      return getImage(navigatorItem.getView());
    }

    return super.getImage(element);
  }

  /**
   * @generated
   */
  public Image getImage(View view) {
    switch (WebspecmodelVisualIDRegistry.getVisualID(view)) {
    case DiagramEditPart.VISUAL_ID:
      return getImage("Navigator?Diagram?ar.edu.unlp.info.lifia.webspec?Diagram", WebspecmodelElementTypes.Diagram_1000); //$NON-NLS-1$
    case InteractionEditPart.VISUAL_ID:
      return getImage("Navigator?TopLevelNode?ar.edu.unlp.info.lifia.webspec?Interaction", WebspecmodelElementTypes.Interaction_2001); //$NON-NLS-1$
    case OneOfNumbersEditPart.VISUAL_ID:
      return getImage("Navigator?TopLevelNode?ar.edu.unlp.info.lifia.webspec?OneOfNumbers", WebspecmodelElementTypes.OneOfNumbers_2002); //$NON-NLS-1$
    case OneOfStringsEditPart.VISUAL_ID:
      return getImage("Navigator?TopLevelNode?ar.edu.unlp.info.lifia.webspec?OneOfStrings", WebspecmodelElementTypes.OneOfStrings_2003); //$NON-NLS-1$
    case UniformNumberDistributionEditPart.VISUAL_ID:
      return getImage(
              "Navigator?TopLevelNode?ar.edu.unlp.info.lifia.webspec?UniformNumberDistribution", WebspecmodelElementTypes.UniformNumberDistribution_2004); //$NON-NLS-1$
    case OneOfArrayEditPart.VISUAL_ID:
      return getImage("Navigator?TopLevelNode?ar.edu.unlp.info.lifia.webspec?OneOfArray", WebspecmodelElementTypes.OneOfArray_2005); //$NON-NLS-1$
    case StringGeneratorEditPart.VISUAL_ID:
      return getImage("Navigator?TopLevelNode?ar.edu.unlp.info.lifia.webspec?StringGenerator", WebspecmodelElementTypes.StringGenerator_2006); //$NON-NLS-1$
    case RIAFeatureEditPart.VISUAL_ID:
      return getImage("Navigator?TopLevelNode?ar.edu.unlp.info.lifia.webspec?RIAFeature", WebspecmodelElementTypes.RIAFeature_2007); //$NON-NLS-1$
    case PanelContainerEditPart.VISUAL_ID:
      return getImage("Navigator?Node?ar.edu.unlp.info.lifia.webspec?PanelContainer", WebspecmodelElementTypes.PanelContainer_3001); //$NON-NLS-1$
    case ButtonEditPart.VISUAL_ID:
      return getImage("Navigator?Node?ar.edu.unlp.info.lifia.webspec?Button", WebspecmodelElementTypes.Button_3002); //$NON-NLS-1$
    case CheckBoxEditPart.VISUAL_ID:
      return getImage("Navigator?Node?ar.edu.unlp.info.lifia.webspec?CheckBox", WebspecmodelElementTypes.CheckBox_3003); //$NON-NLS-1$
    case ComboBoxEditPart.VISUAL_ID:
      return getImage("Navigator?Node?ar.edu.unlp.info.lifia.webspec?ComboBox", WebspecmodelElementTypes.ComboBox_3004); //$NON-NLS-1$
    case LabelEditPart.VISUAL_ID:
      return getImage("Navigator?Node?ar.edu.unlp.info.lifia.webspec?Label", WebspecmodelElementTypes.Label_3005); //$NON-NLS-1$
    case LinkEditPart.VISUAL_ID:
      return getImage("Navigator?Node?ar.edu.unlp.info.lifia.webspec?Link", WebspecmodelElementTypes.Link_3006); //$NON-NLS-1$
    case ListBoxEditPart.VISUAL_ID:
      return getImage("Navigator?Node?ar.edu.unlp.info.lifia.webspec?ListBox", WebspecmodelElementTypes.ListBox_3007); //$NON-NLS-1$
    case RadioButtonEditPart.VISUAL_ID:
      return getImage("Navigator?Node?ar.edu.unlp.info.lifia.webspec?RadioButton", WebspecmodelElementTypes.RadioButton_3008); //$NON-NLS-1$
    case TextFieldEditPart.VISUAL_ID:
      return getImage("Navigator?Node?ar.edu.unlp.info.lifia.webspec?TextField", WebspecmodelElementTypes.TextField_3009); //$NON-NLS-1$
    case PanelContainer2EditPart.VISUAL_ID:
      return getImage("Navigator?Node?ar.edu.unlp.info.lifia.webspec?PanelContainer", WebspecmodelElementTypes.PanelContainer_3010); //$NON-NLS-1$
    case ListOfContainerEditPart.VISUAL_ID:
      return getImage("Navigator?Node?ar.edu.unlp.info.lifia.webspec?ListOfContainer", WebspecmodelElementTypes.ListOfContainer_3011); //$NON-NLS-1$
    case NavigationEditPart.VISUAL_ID:
      return getImage("Navigator?Link?ar.edu.unlp.info.lifia.webspec?Navigation", WebspecmodelElementTypes.Navigation_4001); //$NON-NLS-1$
    case RichBehaviorEditPart.VISUAL_ID:
      return getImage("Navigator?Link?ar.edu.unlp.info.lifia.webspec?RichBehavior", WebspecmodelElementTypes.RichBehavior_4002); //$NON-NLS-1$
    }
    return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
  }

  /**
   * @generated
   */
  private Image getImage(String key, IElementType elementType) {
    ImageRegistry imageRegistry = WebspecmodelDiagramEditorPlugin.getInstance().getImageRegistry();
    Image image = imageRegistry.get(key);
    if (image == null && elementType != null && WebspecmodelElementTypes.isKnownElementType(elementType)) {
      image = WebspecmodelElementTypes.getImage(elementType);
      imageRegistry.put(key, image);
    }

    if (image == null) {
      image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
      imageRegistry.put(key, image);
    }
    return image;
  }

  /**
   * @generated
   */
  public String getText(Object element) {
    if (element instanceof WebspecmodelNavigatorGroup) {
      WebspecmodelNavigatorGroup group = (WebspecmodelNavigatorGroup) element;
      return group.getGroupName();
    }

    if (element instanceof WebspecmodelNavigatorItem) {
      WebspecmodelNavigatorItem navigatorItem = (WebspecmodelNavigatorItem) element;
      if (!isOwnView(navigatorItem.getView())) {
        return null;
      }
      return getText(navigatorItem.getView());
    }

    return super.getText(element);
  }

  /**
   * @generated
   */
  public String getText(View view) {
    if (view.getElement() != null && view.getElement().eIsProxy()) {
      return getUnresolvedDomainElementProxyText(view);
    }
    switch (WebspecmodelVisualIDRegistry.getVisualID(view)) {
    case DiagramEditPart.VISUAL_ID:
      return getDiagram_1000Text(view);
    case InteractionEditPart.VISUAL_ID:
      return getInteraction_2001Text(view);
    case OneOfNumbersEditPart.VISUAL_ID:
      return getOneOfNumbers_2002Text(view);
    case OneOfStringsEditPart.VISUAL_ID:
      return getOneOfStrings_2003Text(view);
    case UniformNumberDistributionEditPart.VISUAL_ID:
      return getUniformNumberDistribution_2004Text(view);
    case OneOfArrayEditPart.VISUAL_ID:
      return getOneOfArray_2005Text(view);
    case StringGeneratorEditPart.VISUAL_ID:
      return getStringGenerator_2006Text(view);
    case RIAFeatureEditPart.VISUAL_ID:
      return getRIAFeature_2007Text(view);
    case PanelContainerEditPart.VISUAL_ID:
      return getPanelContainer_3001Text(view);
    case ButtonEditPart.VISUAL_ID:
      return getButton_3002Text(view);
    case CheckBoxEditPart.VISUAL_ID:
      return getCheckBox_3003Text(view);
    case ComboBoxEditPart.VISUAL_ID:
      return getComboBox_3004Text(view);
    case LabelEditPart.VISUAL_ID:
      return getLabel_3005Text(view);
    case LinkEditPart.VISUAL_ID:
      return getLink_3006Text(view);
    case ListBoxEditPart.VISUAL_ID:
      return getListBox_3007Text(view);
    case RadioButtonEditPart.VISUAL_ID:
      return getRadioButton_3008Text(view);
    case TextFieldEditPart.VISUAL_ID:
      return getTextField_3009Text(view);
    case PanelContainer2EditPart.VISUAL_ID:
      return getPanelContainer_3010Text(view);
    case ListOfContainerEditPart.VISUAL_ID:
      return getListOfContainer_3011Text(view);
    case NavigationEditPart.VISUAL_ID:
      return getNavigation_4001Text(view);
    case RichBehaviorEditPart.VISUAL_ID:
      return getRichBehavior_4002Text(view);
    }
    return getUnknownElementText(view);
  }

  /**
   * @generated
   */
  private String getDiagram_1000Text(View view) {
    Diagram domainModelElement = (Diagram) view.getElement();
    if (domainModelElement != null) {
      return domainModelElement.getName();
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 1000); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getInteraction_2001Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.Interaction_2001, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(InteractionNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5009); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getOneOfNumbers_2002Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.OneOfNumbers_2002, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(OneOfNumbersNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5010); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getOneOfStrings_2003Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.OneOfStrings_2003, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(OneOfStringsNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5011); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getUniformNumberDistribution_2004Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.UniformNumberDistribution_2004, view.getElement() != null ? view
            .getElement() : view, WebspecmodelVisualIDRegistry.getType(UniformNumberDistributionNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5012); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getOneOfArray_2005Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.OneOfArray_2005, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(OneOfArrayNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5013); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getStringGenerator_2006Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.StringGenerator_2006, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(StringGeneratorNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5014); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getRIAFeature_2007Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.RIAFeature_2007, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(RIAFeatureNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5015); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getPanelContainer_3001Text(View view) {
    PanelContainer domainModelElement = (PanelContainer) view.getElement();
    if (domainModelElement != null) {
      return domainModelElement.getName();
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3001); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getButton_3002Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.Button_3002, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(ButtonNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5001); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getCheckBox_3003Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.CheckBox_3003, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(CheckBoxNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5002); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getComboBox_3004Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.ComboBox_3004, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(ComboBoxNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5003); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getLabel_3005Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.Label_3005, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(LabelNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5004); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getLink_3006Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.Link_3006, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(LinkNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5005); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getListBox_3007Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.ListBox_3007, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(ListBoxNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5006); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getRadioButton_3008Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.RadioButton_3008, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(RadioButtonNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5007); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getTextField_3009Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.TextField_3009, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(TextFieldNameEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5008); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getPanelContainer_3010Text(View view) {
    PanelContainer domainModelElement = (PanelContainer) view.getElement();
    if (domainModelElement != null) {
      return domainModelElement.getName();
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3010); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getListOfContainer_3011Text(View view) {
    ListOfContainer domainModelElement = (ListOfContainer) view.getElement();
    if (domainModelElement != null) {
      return domainModelElement.getName();
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3011); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getNavigation_4001Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.Navigation_4001, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(NavigationNamePreconditionActionsEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 6001); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getRichBehavior_4002Text(View view) {
    IParser parser = WebspecmodelParserProvider.getParser(WebspecmodelElementTypes.RichBehavior_4002, view.getElement() != null ? view.getElement() : view,
            WebspecmodelVisualIDRegistry.getType(RichBehaviorNamePreconditionActionsEditPart.VISUAL_ID));
    if (parser != null) {
      return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
    } else {
      WebspecmodelDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 6002); //$NON-NLS-1$
      return ""; //$NON-NLS-1$
    }
  }

  /**
   * @generated
   */
  private String getUnknownElementText(View view) {
    return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
  }

  /**
   * @generated
   */
  private String getUnresolvedDomainElementProxyText(View view) {
    return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
  }

  /**
   * @generated
   */
  public void init(ICommonContentExtensionSite aConfig) {
  }

  /**
   * @generated
   */
  public void restoreState(IMemento aMemento) {
  }

  /**
   * @generated
   */
  public void saveState(IMemento aMemento) {
  }

  /**
   * @generated
   */
  public String getDescription(Object anElement) {
    return null;
  }

  /**
   * @generated
   */
  private boolean isOwnView(View view) {
    return DiagramEditPart.MODEL_ID.equals(WebspecmodelVisualIDRegistry.getModelID(view));
  }

}
