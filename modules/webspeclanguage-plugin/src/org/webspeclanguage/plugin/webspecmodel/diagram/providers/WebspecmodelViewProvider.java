package org.webspeclanguage.plugin.webspecmodel.diagram.providers;

import java.util.ArrayList;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.providers.IViewProvider;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateEdgeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateNodeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewForKindOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewOperation;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.TitleStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.ButtonEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.ButtonNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.CheckBoxEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.CheckBoxNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.ComboBoxEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.ComboBoxNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.DiagramEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.InteractionEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.InteractionNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.LabelEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.LabelNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.LinkEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.LinkNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.ListBoxEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.ListBoxNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.ListOfContainerEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.ListOfContainerListOfCompartmentEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.NavigationEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.NavigationNamePreconditionActionsEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.OneOfArrayEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.OneOfArrayNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.OneOfNumbersEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.OneOfNumbersNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.OneOfStringsEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.OneOfStringsNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.PanelContainer2EditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.PanelContainerEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.PanelContainerPanelCompartment2EditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.PanelContainerPanelCompartmentEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.RIAFeatureEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.RIAFeatureNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.RadioButtonEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.RadioButtonNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.RichBehaviorEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.RichBehaviorNamePreconditionActionsEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.StringGeneratorEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.StringGeneratorNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.TextFieldEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.TextFieldNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.UniformNumberDistributionEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.edit.parts.UniformNumberDistributionNameEditPart;
import org.webspeclanguage.plugin.webspecmodel.diagram.part.WebspecmodelVisualIDRegistry;


/**
 * @generated
 */
public class WebspecmodelViewProvider extends AbstractProvider implements IViewProvider {

  /**
   * @generated
   */
  public final boolean provides(IOperation operation) {
    if (operation instanceof CreateViewForKindOperation) {
      return provides((CreateViewForKindOperation) operation);
    }
    assert operation instanceof CreateViewOperation;
    if (operation instanceof CreateDiagramViewOperation) {
      return provides((CreateDiagramViewOperation) operation);
    } else if (operation instanceof CreateEdgeViewOperation) {
      return provides((CreateEdgeViewOperation) operation);
    } else if (operation instanceof CreateNodeViewOperation) {
      return provides((CreateNodeViewOperation) operation);
    }
    return false;
  }

  /**
   * @generated
   */
  protected boolean provides(CreateViewForKindOperation op) {
    /*
     if (op.getViewKind() == Node.class)
     return getNodeViewClass(op.getSemanticAdapter(), op.getContainerView(), op.getSemanticHint()) != null;
     if (op.getViewKind() == Edge.class)
     return getEdgeViewClass(op.getSemanticAdapter(), op.getContainerView(), op.getSemanticHint()) != null;
     */
    return true;
  }

  /**
   * @generated
   */
  protected boolean provides(CreateDiagramViewOperation op) {
    return DiagramEditPart.MODEL_ID.equals(op.getSemanticHint())
            && WebspecmodelVisualIDRegistry.getDiagramVisualID(getSemanticElement(op.getSemanticAdapter())) != -1;
  }

  /**
   * @generated
   */
  protected boolean provides(CreateNodeViewOperation op) {
    if (op.getContainerView() == null) {
      return false;
    }
    IElementType elementType = getSemanticElementType(op.getSemanticAdapter());
    EObject domainElement = getSemanticElement(op.getSemanticAdapter());
    int visualID;
    if (op.getSemanticHint() == null) {
      // Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
      // In this situation there should be NO elementType, visualID will be determined
      // by VisualIDRegistry.getNodeVisualID() for domainElement.
      if (elementType != null || domainElement == null) {
        return false;
      }
      visualID = WebspecmodelVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement);
    } else {
      visualID = WebspecmodelVisualIDRegistry.getVisualID(op.getSemanticHint());
      if (elementType != null) {
        if (!WebspecmodelElementTypes.isKnownElementType(elementType) || (!(elementType instanceof IHintedType))) {
          return false; // foreign element type
        }
        String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
        if (!op.getSemanticHint().equals(elementTypeHint)) {
          return false; // if semantic hint is specified it should be the same as in element type
        }
        if (domainElement != null && visualID != WebspecmodelVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement)) {
          return false; // visual id for node EClass should match visual id from element type
        }
      } else {
        if (!DiagramEditPart.MODEL_ID.equals(WebspecmodelVisualIDRegistry.getModelID(op.getContainerView()))) {
          return false; // foreign diagram
        }
        switch (visualID) {
        case InteractionEditPart.VISUAL_ID:
        case OneOfNumbersEditPart.VISUAL_ID:
        case OneOfStringsEditPart.VISUAL_ID:
        case UniformNumberDistributionEditPart.VISUAL_ID:
        case OneOfArrayEditPart.VISUAL_ID:
        case StringGeneratorEditPart.VISUAL_ID:
        case RIAFeatureEditPart.VISUAL_ID:
        case PanelContainerEditPart.VISUAL_ID:
        case ButtonEditPart.VISUAL_ID:
        case CheckBoxEditPart.VISUAL_ID:
        case ComboBoxEditPart.VISUAL_ID:
        case LabelEditPart.VISUAL_ID:
        case LinkEditPart.VISUAL_ID:
        case ListBoxEditPart.VISUAL_ID:
        case RadioButtonEditPart.VISUAL_ID:
        case TextFieldEditPart.VISUAL_ID:
        case ListOfContainerEditPart.VISUAL_ID:
        case PanelContainer2EditPart.VISUAL_ID:
          if (domainElement == null || visualID != WebspecmodelVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement)) {
            return false; // visual id in semantic hint should match visual id for domain element
          }
          break;
        default:
          return false;
        }
      }
    }
    return InteractionEditPart.VISUAL_ID == visualID || OneOfNumbersEditPart.VISUAL_ID == visualID || OneOfStringsEditPart.VISUAL_ID == visualID
            || UniformNumberDistributionEditPart.VISUAL_ID == visualID || OneOfArrayEditPart.VISUAL_ID == visualID
            || StringGeneratorEditPart.VISUAL_ID == visualID || RIAFeatureEditPart.VISUAL_ID == visualID || PanelContainerEditPart.VISUAL_ID == visualID
            || ButtonEditPart.VISUAL_ID == visualID || CheckBoxEditPart.VISUAL_ID == visualID || ComboBoxEditPart.VISUAL_ID == visualID
            || LabelEditPart.VISUAL_ID == visualID || LinkEditPart.VISUAL_ID == visualID || ListBoxEditPart.VISUAL_ID == visualID
            || RadioButtonEditPart.VISUAL_ID == visualID || TextFieldEditPart.VISUAL_ID == visualID || PanelContainer2EditPart.VISUAL_ID == visualID
            || ListOfContainerEditPart.VISUAL_ID == visualID;
  }

  /**
   * @generated
   */
  protected boolean provides(CreateEdgeViewOperation op) {
    IElementType elementType = getSemanticElementType(op.getSemanticAdapter());
    if (!WebspecmodelElementTypes.isKnownElementType(elementType) || (!(elementType instanceof IHintedType))) {
      return false; // foreign element type
    }
    String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
    if (elementTypeHint == null || (op.getSemanticHint() != null && !elementTypeHint.equals(op.getSemanticHint()))) {
      return false; // our hint is visual id and must be specified, and it should be the same as in element type
    }
    int visualID = WebspecmodelVisualIDRegistry.getVisualID(elementTypeHint);
    EObject domainElement = getSemanticElement(op.getSemanticAdapter());
    if (domainElement != null && visualID != WebspecmodelVisualIDRegistry.getLinkWithClassVisualID(domainElement)) {
      return false; // visual id for link EClass should match visual id from element type
    }
    return true;
  }

  /**
   * @generated
   */
  public Diagram createDiagram(IAdaptable semanticAdapter, String diagramKind, PreferencesHint preferencesHint) {
    Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
    diagram.getStyles().add(NotationFactory.eINSTANCE.createDiagramStyle());
    diagram.setType(DiagramEditPart.MODEL_ID);
    diagram.setElement(getSemanticElement(semanticAdapter));
    diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
    return diagram;
  }

  /**
   * @generated
   */
  public Node createNode(IAdaptable semanticAdapter, View containerView, String semanticHint, int index, boolean persisted, PreferencesHint preferencesHint) {
    final EObject domainElement = getSemanticElement(semanticAdapter);
    final int visualID;
    if (semanticHint == null) {
      visualID = WebspecmodelVisualIDRegistry.getNodeVisualID(containerView, domainElement);
    } else {
      visualID = WebspecmodelVisualIDRegistry.getVisualID(semanticHint);
    }
    switch (visualID) {
    case InteractionEditPart.VISUAL_ID:
      return createInteraction_2001(domainElement, containerView, index, persisted, preferencesHint);
    case OneOfNumbersEditPart.VISUAL_ID:
      return createOneOfNumbers_2002(domainElement, containerView, index, persisted, preferencesHint);
    case OneOfStringsEditPart.VISUAL_ID:
      return createOneOfStrings_2003(domainElement, containerView, index, persisted, preferencesHint);
    case UniformNumberDistributionEditPart.VISUAL_ID:
      return createUniformNumberDistribution_2004(domainElement, containerView, index, persisted, preferencesHint);
    case OneOfArrayEditPart.VISUAL_ID:
      return createOneOfArray_2005(domainElement, containerView, index, persisted, preferencesHint);
    case StringGeneratorEditPart.VISUAL_ID:
      return createStringGenerator_2006(domainElement, containerView, index, persisted, preferencesHint);
    case RIAFeatureEditPart.VISUAL_ID:
      return createRIAFeature_2007(domainElement, containerView, index, persisted, preferencesHint);
    case PanelContainerEditPart.VISUAL_ID:
      return createPanelContainer_3001(domainElement, containerView, index, persisted, preferencesHint);
    case ButtonEditPart.VISUAL_ID:
      return createButton_3002(domainElement, containerView, index, persisted, preferencesHint);
    case CheckBoxEditPart.VISUAL_ID:
      return createCheckBox_3003(domainElement, containerView, index, persisted, preferencesHint);
    case ComboBoxEditPart.VISUAL_ID:
      return createComboBox_3004(domainElement, containerView, index, persisted, preferencesHint);
    case LabelEditPart.VISUAL_ID:
      return createLabel_3005(domainElement, containerView, index, persisted, preferencesHint);
    case LinkEditPart.VISUAL_ID:
      return createLink_3006(domainElement, containerView, index, persisted, preferencesHint);
    case ListBoxEditPart.VISUAL_ID:
      return createListBox_3007(domainElement, containerView, index, persisted, preferencesHint);
    case RadioButtonEditPart.VISUAL_ID:
      return createRadioButton_3008(domainElement, containerView, index, persisted, preferencesHint);
    case TextFieldEditPart.VISUAL_ID:
      return createTextField_3009(domainElement, containerView, index, persisted, preferencesHint);
    case PanelContainer2EditPart.VISUAL_ID:
      return createPanelContainer_3010(domainElement, containerView, index, persisted, preferencesHint);
    case ListOfContainerEditPart.VISUAL_ID:
      return createListOfContainer_3011(domainElement, containerView, index, persisted, preferencesHint);
    }
    // can't happen, provided #provides(CreateNodeViewOperation) is correct
    return null;
  }

  /**
   * @generated
   */
  public Edge createEdge(IAdaptable semanticAdapter, View containerView, String semanticHint, int index, boolean persisted, PreferencesHint preferencesHint) {
    IElementType elementType = getSemanticElementType(semanticAdapter);
    String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
    switch (WebspecmodelVisualIDRegistry.getVisualID(elementTypeHint)) {
    case NavigationEditPart.VISUAL_ID:
      return createNavigation_4001(getSemanticElement(semanticAdapter), containerView, index, persisted, preferencesHint);
    case RichBehaviorEditPart.VISUAL_ID:
      return createRichBehavior_4002(getSemanticElement(semanticAdapter), containerView, index, persisted, preferencesHint);
    }
    // can never happen, provided #provides(CreateEdgeViewOperation) is correct
    return null;
  }

  /**
   * @generated
   */
  public Node createInteraction_2001(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(InteractionEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    stampShortcut(containerView, node);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5009 = createLabel(node, WebspecmodelVisualIDRegistry.getType(InteractionNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createOneOfNumbers_2002(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(OneOfNumbersEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    stampShortcut(containerView, node);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5010 = createLabel(node, WebspecmodelVisualIDRegistry.getType(OneOfNumbersNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createOneOfStrings_2003(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(OneOfStringsEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    stampShortcut(containerView, node);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5011 = createLabel(node, WebspecmodelVisualIDRegistry.getType(OneOfStringsNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createUniformNumberDistribution_2004(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(UniformNumberDistributionEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    stampShortcut(containerView, node);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5012 = createLabel(node, WebspecmodelVisualIDRegistry.getType(UniformNumberDistributionNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createOneOfArray_2005(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(OneOfArrayEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    stampShortcut(containerView, node);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5013 = createLabel(node, WebspecmodelVisualIDRegistry.getType(OneOfArrayNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createStringGenerator_2006(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(StringGeneratorEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    stampShortcut(containerView, node);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5014 = createLabel(node, WebspecmodelVisualIDRegistry.getType(StringGeneratorNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createRIAFeature_2007(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(RIAFeatureEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    stampShortcut(containerView, node);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5015 = createLabel(node, WebspecmodelVisualIDRegistry.getType(RIAFeatureNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createPanelContainer_3001(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(PanelContainerEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    createCompartment(node, WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartmentEditPart.VISUAL_ID), true, false, false, false);
    return node;
  }

  /**
   * @generated
   */
  public Node createButton_3002(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(ButtonEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5001 = createLabel(node, WebspecmodelVisualIDRegistry.getType(ButtonNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createCheckBox_3003(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(CheckBoxEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5002 = createLabel(node, WebspecmodelVisualIDRegistry.getType(CheckBoxNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createComboBox_3004(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(ComboBoxEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5003 = createLabel(node, WebspecmodelVisualIDRegistry.getType(ComboBoxNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createLabel_3005(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(LabelEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5004 = createLabel(node, WebspecmodelVisualIDRegistry.getType(LabelNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createLink_3006(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5005 = createLabel(node, WebspecmodelVisualIDRegistry.getType(LinkNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createListBox_3007(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(ListBoxEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5006 = createLabel(node, WebspecmodelVisualIDRegistry.getType(ListBoxNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createRadioButton_3008(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(RadioButtonEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5007 = createLabel(node, WebspecmodelVisualIDRegistry.getType(RadioButtonNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createTextField_3009(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(TextFieldEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    Node label5008 = createLabel(node, WebspecmodelVisualIDRegistry.getType(TextFieldNameEditPart.VISUAL_ID));
    return node;
  }

  /**
   * @generated
   */
  public Node createPanelContainer_3010(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Shape node = NotationFactory.eINSTANCE.createShape();
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(PanelContainer2EditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    createCompartment(node, WebspecmodelVisualIDRegistry.getType(PanelContainerPanelCompartment2EditPart.VISUAL_ID), true, false, false, false);
    return node;
  }

  /**
   * @generated
   */
  public Node createListOfContainer_3011(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Node node = NotationFactory.eINSTANCE.createNode();
    node.getStyles().add(NotationFactory.eINSTANCE.createDescriptionStyle());
    node.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
    node.getStyles().add(NotationFactory.eINSTANCE.createFillStyle());
    node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
    node.setType(WebspecmodelVisualIDRegistry.getType(ListOfContainerEditPart.VISUAL_ID));
    ViewUtil.insertChildView(containerView, node, index, persisted);
    node.setElement(domainElement);
    // initializeFromPreferences 
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
    FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (nodeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      nodeFontStyle.setFontName(fontData.getName());
      nodeFontStyle.setFontHeight(fontData.getHeight());
      nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FILL_COLOR);
    ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
    createCompartment(node, WebspecmodelVisualIDRegistry.getType(ListOfContainerListOfCompartmentEditPart.VISUAL_ID), true, true, false, false);
    return node;
  }

  /**
   * @generated
   */
  public Edge createNavigation_4001(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Connector edge = NotationFactory.eINSTANCE.createConnector();
    edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
    RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
    ArrayList points = new ArrayList(2);
    points.add(new RelativeBendpoint());
    points.add(new RelativeBendpoint());
    bendpoints.setPoints(points);
    edge.setBendpoints(bendpoints);
    ViewUtil.insertChildView(containerView, edge, index, persisted);
    edge.setType(WebspecmodelVisualIDRegistry.getType(NavigationEditPart.VISUAL_ID));
    edge.setElement(domainElement);
    // initializePreferences
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

    org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_LINE_COLOR);
    ViewUtil.setStructuralFeatureValue(edge, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
    FontStyle edgeFontStyle = (FontStyle) edge.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (edgeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      edgeFontStyle.setFontName(fontData.getName());
      edgeFontStyle.setFontHeight(fontData.getHeight());
      edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    Routing routing = Routing.get(prefStore.getInt(IPreferenceConstants.PREF_LINE_STYLE));
    if (routing != null) {
      ViewUtil.setStructuralFeatureValue(edge, NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
    }
    Node label6001 = createLabel(edge, WebspecmodelVisualIDRegistry.getType(NavigationNamePreconditionActionsEditPart.VISUAL_ID));
    label6001.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
    Location location6001 = (Location) label6001.getLayoutConstraint();
    location6001.setX(0);
    location6001.setY(40);
    return edge;
  }

  /**
   * @generated
   */
  public Edge createRichBehavior_4002(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
    Edge edge = NotationFactory.eINSTANCE.createEdge();
    edge.getStyles().add(NotationFactory.eINSTANCE.createRoutingStyle());
    edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
    RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
    ArrayList points = new ArrayList(2);
    points.add(new RelativeBendpoint());
    points.add(new RelativeBendpoint());
    bendpoints.setPoints(points);
    edge.setBendpoints(bendpoints);
    ViewUtil.insertChildView(containerView, edge, index, persisted);
    edge.setType(WebspecmodelVisualIDRegistry.getType(RichBehaviorEditPart.VISUAL_ID));
    edge.setElement(domainElement);
    // initializePreferences
    final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
    FontStyle edgeFontStyle = (FontStyle) edge.getStyle(NotationPackage.Literals.FONT_STYLE);
    if (edgeFontStyle != null) {
      FontData fontData = PreferenceConverter.getFontData(prefStore, IPreferenceConstants.PREF_DEFAULT_FONT);
      edgeFontStyle.setFontName(fontData.getName());
      edgeFontStyle.setFontHeight(fontData.getHeight());
      edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
      edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
      org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
      edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
    }
    Routing routing = Routing.get(prefStore.getInt(IPreferenceConstants.PREF_LINE_STYLE));
    if (routing != null) {
      ViewUtil.setStructuralFeatureValue(edge, NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
    }
    Node label6002 = createLabel(edge, WebspecmodelVisualIDRegistry.getType(RichBehaviorNamePreconditionActionsEditPart.VISUAL_ID));
    label6002.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
    Location location6002 = (Location) label6002.getLayoutConstraint();
    location6002.setX(0);
    location6002.setY(40);
    return edge;
  }

  /**
   * @generated
   */
  private void stampShortcut(View containerView, Node target) {
    if (!DiagramEditPart.MODEL_ID.equals(WebspecmodelVisualIDRegistry.getModelID(containerView))) {
      EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
      shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
      shortcutAnnotation.getDetails().put("modelID", DiagramEditPart.MODEL_ID); //$NON-NLS-1$
      target.getEAnnotations().add(shortcutAnnotation);
    }
  }

  /**
   * @generated
   */
  private Node createLabel(View owner, String hint) {
    DecorationNode rv = NotationFactory.eINSTANCE.createDecorationNode();
    rv.setType(hint);
    ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
    return rv;
  }

  /**
   * @generated
   */
  private Node createCompartment(View owner, String hint, boolean canCollapse, boolean hasTitle, boolean canSort, boolean canFilter) {
    //SemanticListCompartment rv = NotationFactory.eINSTANCE.createSemanticListCompartment();
    //rv.setShowTitle(showTitle);
    //rv.setCollapsed(isCollapsed);
    Node rv;
    if (canCollapse) {
      rv = NotationFactory.eINSTANCE.createBasicCompartment();
    } else {
      rv = NotationFactory.eINSTANCE.createDecorationNode();
    }
    if (hasTitle) {
      TitleStyle ts = NotationFactory.eINSTANCE.createTitleStyle();
      ts.setShowTitle(true);
      rv.getStyles().add(ts);
    }
    if (canSort) {
      rv.getStyles().add(NotationFactory.eINSTANCE.createSortingStyle());
    }
    if (canFilter) {
      rv.getStyles().add(NotationFactory.eINSTANCE.createFilteringStyle());
    }
    rv.setType(hint);
    ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
    return rv;
  }

  /**
   * @generated
   */
  private EObject getSemanticElement(IAdaptable semanticAdapter) {
    if (semanticAdapter == null) {
      return null;
    }
    EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
    if (eObject != null) {
      return EMFCoreUtil.resolve(TransactionUtil.getEditingDomain(eObject), eObject);
    }
    return null;
  }

  /**
   * @generated
   */
  private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
    if (semanticAdapter == null) {
      return null;
    }
    return (IElementType) semanticAdapter.getAdapter(IElementType.class);
  }
}
