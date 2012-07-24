package org.webspeclanguage.plugin.webspecmodel.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.ButtonNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.CheckBoxNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.ComboBoxNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.InteractionNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.LabelNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.LinkNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.ListBoxNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.NavigationNamePreconditionActionsEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.OneOfArrayNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.OneOfNumbersNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.OneOfStringsNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.RIAFeatureNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.RadioButtonNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.RichBehaviorNamePreconditionActionsEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.StringGeneratorNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.TextFieldNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.UniformNumberDistributionNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.parsers.MessageFormatParser;
import org.webspeclanguage.plugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;


/**
 * @generated
 */
public class WebspecmodelParserProvider extends AbstractProvider implements IParserProvider {

  /**
   * @generated
   */
  private IParser interactionName_5009Parser;

  /**
   * @generated
   */
  private IParser getInteractionName_5009Parser() {
    if (interactionName_5009Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getInteraction_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      interactionName_5009Parser = parser;
    }
    return interactionName_5009Parser;
  }

  /**
   * @generated
   */
  private IParser oneOfNumbersName_5010Parser;

  /**
   * @generated
   */
  private IParser getOneOfNumbersName_5010Parser() {
    if (oneOfNumbersName_5010Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getGenerator_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      oneOfNumbersName_5010Parser = parser;
    }
    return oneOfNumbersName_5010Parser;
  }

  /**
   * @generated
   */
  private IParser oneOfStringsName_5011Parser;

  /**
   * @generated
   */
  private IParser getOneOfStringsName_5011Parser() {
    if (oneOfStringsName_5011Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getGenerator_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      oneOfStringsName_5011Parser = parser;
    }
    return oneOfStringsName_5011Parser;
  }

  /**
   * @generated
   */
  private IParser uniformNumberDistributionName_5012Parser;

  /**
   * @generated
   */
  private IParser getUniformNumberDistributionName_5012Parser() {
    if (uniformNumberDistributionName_5012Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getGenerator_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      uniformNumberDistributionName_5012Parser = parser;
    }
    return uniformNumberDistributionName_5012Parser;
  }

  /**
   * @generated
   */
  private IParser oneOfArrayName_5013Parser;

  /**
   * @generated
   */
  private IParser getOneOfArrayName_5013Parser() {
    if (oneOfArrayName_5013Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getGenerator_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      oneOfArrayName_5013Parser = parser;
    }
    return oneOfArrayName_5013Parser;
  }

  /**
   * @generated
   */
  private IParser stringGeneratorName_5014Parser;

  /**
   * @generated
   */
  private IParser getStringGeneratorName_5014Parser() {
    if (stringGeneratorName_5014Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getGenerator_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      stringGeneratorName_5014Parser = parser;
    }
    return stringGeneratorName_5014Parser;
  }

  /**
   * @generated
   */
  private IParser rIAFeatureName_5015Parser;

  /**
   * @generated
   */
  private IParser getRIAFeatureName_5015Parser() {
    if (rIAFeatureName_5015Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getRIAFeature_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      rIAFeatureName_5015Parser = parser;
    }
    return rIAFeatureName_5015Parser;
  }

  /**
   * @generated
   */
  private IParser buttonName_5001Parser;

  /**
   * @generated
   */
  private IParser getButtonName_5001Parser() {
    if (buttonName_5001Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getWidget_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      buttonName_5001Parser = parser;
    }
    return buttonName_5001Parser;
  }

  /**
   * @generated
   */
  private IParser checkBoxName_5002Parser;

  /**
   * @generated
   */
  private IParser getCheckBoxName_5002Parser() {
    if (checkBoxName_5002Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getWidget_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      checkBoxName_5002Parser = parser;
    }
    return checkBoxName_5002Parser;
  }

  /**
   * @generated
   */
  private IParser comboBoxName_5003Parser;

  /**
   * @generated
   */
  private IParser getComboBoxName_5003Parser() {
    if (comboBoxName_5003Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getWidget_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      comboBoxName_5003Parser = parser;
    }
    return comboBoxName_5003Parser;
  }

  /**
   * @generated
   */
  private IParser labelName_5004Parser;

  /**
   * @generated
   */
  private IParser getLabelName_5004Parser() {
    if (labelName_5004Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getWidget_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      labelName_5004Parser = parser;
    }
    return labelName_5004Parser;
  }

  /**
   * @generated
   */
  private IParser linkName_5005Parser;

  /**
   * @generated
   */
  private IParser getLinkName_5005Parser() {
    if (linkName_5005Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getWidget_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      linkName_5005Parser = parser;
    }
    return linkName_5005Parser;
  }

  /**
   * @generated
   */
  private IParser listBoxName_5006Parser;

  /**
   * @generated
   */
  private IParser getListBoxName_5006Parser() {
    if (listBoxName_5006Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getWidget_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      listBoxName_5006Parser = parser;
    }
    return listBoxName_5006Parser;
  }

  /**
   * @generated
   */
  private IParser radioButtonName_5007Parser;

  /**
   * @generated
   */
  private IParser getRadioButtonName_5007Parser() {
    if (radioButtonName_5007Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getWidget_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      radioButtonName_5007Parser = parser;
    }
    return radioButtonName_5007Parser;
  }

  /**
   * @generated
   */
  private IParser textFieldName_5008Parser;

  /**
   * @generated
   */
  private IParser getTextFieldName_5008Parser() {
    if (textFieldName_5008Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getWidget_Name() };
      MessageFormatParser parser = new MessageFormatParser(features);
      textFieldName_5008Parser = parser;
    }
    return textFieldName_5008Parser;
  }

  /**
   * @generated
   */
  private IParser navigationNamePreconditionActions_6001Parser;

  /**
   * @generated
   */
  private IParser getNavigationNamePreconditionActions_6001Parser() {
    if (navigationNamePreconditionActions_6001Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getTransition_Name(),
          WebspecmodelPackage.eINSTANCE.getTransition_Precondition(), WebspecmodelPackage.eINSTANCE.getTransition_Actions() };
      MessageFormatParser parser = new MessageFormatParser(features);
      parser.setViewPattern("#{0}\n'{'{1}'}'\n{2}"); //$NON-NLS-1$
      parser.setEditorPattern("#{0}\n'{'{1}'}'\n{2}"); //$NON-NLS-1$
      parser.setEditPattern("#{0}\n'{'{1}'}'\n{2}"); //$NON-NLS-1$
      navigationNamePreconditionActions_6001Parser = parser;
    }
    return navigationNamePreconditionActions_6001Parser;
  }

  /**
   * @generated
   */
  private IParser richBehaviorNamePreconditionActions_6002Parser;

  /**
   * @generated
   */
  private IParser getRichBehaviorNamePreconditionActions_6002Parser() {
    if (richBehaviorNamePreconditionActions_6002Parser == null) {
      EAttribute[] features = new EAttribute[] { WebspecmodelPackage.eINSTANCE.getTransition_Name(),
          WebspecmodelPackage.eINSTANCE.getTransition_Precondition(), WebspecmodelPackage.eINSTANCE.getTransition_Actions() };
      MessageFormatParser parser = new MessageFormatParser(features);
      parser.setViewPattern("#{0}\n'{'{1}'}'\n{2}"); //$NON-NLS-1$
      parser.setEditorPattern("#{0}\n'{'{1}'}'\n{2}"); //$NON-NLS-1$
      parser.setEditPattern("#{0}\n'{'{1}'}'\n{2}"); //$NON-NLS-1$
      richBehaviorNamePreconditionActions_6002Parser = parser;
    }
    return richBehaviorNamePreconditionActions_6002Parser;
  }

  /**
   * @generated
   */
  protected IParser getParser(int visualID) {
    switch (visualID) {
    case InteractionNameEditPart.VISUAL_ID:
      return getInteractionName_5009Parser();
    case OneOfNumbersNameEditPart.VISUAL_ID:
      return getOneOfNumbersName_5010Parser();
    case OneOfStringsNameEditPart.VISUAL_ID:
      return getOneOfStringsName_5011Parser();
    case UniformNumberDistributionNameEditPart.VISUAL_ID:
      return getUniformNumberDistributionName_5012Parser();
    case OneOfArrayNameEditPart.VISUAL_ID:
      return getOneOfArrayName_5013Parser();
    case StringGeneratorNameEditPart.VISUAL_ID:
      return getStringGeneratorName_5014Parser();
    case RIAFeatureNameEditPart.VISUAL_ID:
      return getRIAFeatureName_5015Parser();
    case ButtonNameEditPart.VISUAL_ID:
      return getButtonName_5001Parser();
    case CheckBoxNameEditPart.VISUAL_ID:
      return getCheckBoxName_5002Parser();
    case ComboBoxNameEditPart.VISUAL_ID:
      return getComboBoxName_5003Parser();
    case LabelNameEditPart.VISUAL_ID:
      return getLabelName_5004Parser();
    case LinkNameEditPart.VISUAL_ID:
      return getLinkName_5005Parser();
    case ListBoxNameEditPart.VISUAL_ID:
      return getListBoxName_5006Parser();
    case RadioButtonNameEditPart.VISUAL_ID:
      return getRadioButtonName_5007Parser();
    case TextFieldNameEditPart.VISUAL_ID:
      return getTextFieldName_5008Parser();
    case NavigationNamePreconditionActionsEditPart.VISUAL_ID:
      return getNavigationNamePreconditionActions_6001Parser();
    case RichBehaviorNamePreconditionActionsEditPart.VISUAL_ID:
      return getRichBehaviorNamePreconditionActions_6002Parser();
    }
    return null;
  }

  /**
   * Utility method that consults ParserService
   * @generated
   */
  public static IParser getParser(IElementType type, EObject object, String parserHint) {
    return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
  }

  /**
   * @generated
   */
  public IParser getParser(IAdaptable hint) {
    String vid = (String) hint.getAdapter(String.class);
    if (vid != null) {
      return getParser(WebspecmodelVisualIDRegistry.getVisualID(vid));
    }
    View view = (View) hint.getAdapter(View.class);
    if (view != null) {
      return getParser(WebspecmodelVisualIDRegistry.getVisualID(view));
    }
    return null;
  }

  /**
   * @generated
   */
  public boolean provides(IOperation operation) {
    if (operation instanceof GetParserOperation) {
      IAdaptable hint = ((GetParserOperation) operation).getHint();
      if (WebspecmodelElementTypes.getElement(hint) == null) {
        return false;
      }
      return getParser(hint) != null;
    }
    return false;
  }

  /**
   * @generated
   */
  public static class HintAdapter extends ParserHintAdapter {

    /**
     * @generated
     */
    private final IElementType elementType;

    /**
     * @generated
     */
    public HintAdapter(IElementType type, EObject object, String parserHint) {
      super(object, parserHint);
      assert type != null;
      elementType = type;
    }

    /**
     * @generated
     */
    public Object getAdapter(Class adapter) {
      if (IElementType.class.equals(adapter)) {
        return elementType;
      }
      return super.getAdapter(adapter);
    }
  }

}
