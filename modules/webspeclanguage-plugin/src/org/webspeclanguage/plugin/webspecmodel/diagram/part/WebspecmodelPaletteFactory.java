package org.webspeclanguage.plugin.webspecmodel.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.webspeclanguage.plugin.webspecmodel.diagram.providers.WebspecmodelElementTypes;


/**
 * @generated
 */
public class WebspecmodelPaletteFactory {

  /**
   * @generated
   */
  public void fillPalette(PaletteRoot paletteRoot) {
    paletteRoot.add(createDefault1Group());
    paletteRoot.add(createCommon2Group());
    paletteRoot.add(createWidgets3Group());
    paletteRoot.add(createGenerators4Group());
  }

  /**
   * Creates "Default" palette tool group
   * @generated
   */
  private PaletteContainer createDefault1Group() {
    PaletteGroup paletteContainer = new PaletteGroup(Messages.Default1Group_title);
    paletteContainer.setId("createDefault1Group"); //$NON-NLS-1$
    paletteContainer.setDescription(Messages.Default1Group_desc);
    paletteContainer.add(new PaletteSeparator());
    paletteContainer.add(new PaletteSeparator());
    return paletteContainer;
  }

  /**
   * Creates "Common" palette tool group
   * @generated
   */
  private PaletteContainer createCommon2Group() {
    PaletteDrawer paletteContainer = new PaletteDrawer(Messages.Common2Group_title);
    paletteContainer.setId("createCommon2Group"); //$NON-NLS-1$
    paletteContainer.add(createInteraction1CreationTool());
    paletteContainer.add(createNavigation2CreationTool());
    paletteContainer.add(createRichbehavior3CreationTool());
    paletteContainer.add(createRIAfeature4CreationTool());
    return paletteContainer;
  }

  /**
   * Creates "Widgets" palette tool group
   * @generated
   */
  private PaletteContainer createWidgets3Group() {
    PaletteDrawer paletteContainer = new PaletteDrawer(Messages.Widgets3Group_title);
    paletteContainer.setId("createWidgets3Group"); //$NON-NLS-1$
    paletteContainer.add(createButton1CreationTool());
    paletteContainer.add(createCheckBox2CreationTool());
    paletteContainer.add(createComboBox3CreationTool());
    paletteContainer.add(createLabel4CreationTool());
    paletteContainer.add(createLink5CreationTool());
    paletteContainer.add(createListBox6CreationTool());
    paletteContainer.add(createListcontainer7CreationTool());
    paletteContainer.add(createPanel8CreationTool());
    paletteContainer.add(createRadioButton9CreationTool());
    paletteContainer.add(createTextField10CreationTool());
    return paletteContainer;
  }

  /**
   * Creates "Generators" palette tool group
   * @generated
   */
  private PaletteContainer createGenerators4Group() {
    PaletteDrawer paletteContainer = new PaletteDrawer(Messages.Generators4Group_title);
    paletteContainer.setId("createGenerators4Group"); //$NON-NLS-1$
    paletteContainer.add(createOneofstrings1CreationTool());
    paletteContainer.add(createOneofnumbers2CreationTool());
    paletteContainer.add(createUniformnumber3CreationTool());
    paletteContainer.add(createArraygenerator4CreationTool());
    paletteContainer.add(createStringgenerator5CreationTool());
    return paletteContainer;
  }

  /**
   * @generated
   */
  private ToolEntry createInteraction1CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.Interaction_2001);
    NodeToolEntry entry = new NodeToolEntry(Messages.Interaction1CreationTool_title, Messages.Interaction1CreationTool_desc, types);
    entry.setId("createInteraction1CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.Interaction_2001));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createNavigation2CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.Navigation_4001);
    LinkToolEntry entry = new LinkToolEntry(Messages.Navigation2CreationTool_title, Messages.Navigation2CreationTool_desc, types);
    entry.setId("createNavigation2CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.Navigation_4001));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createRichbehavior3CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.RichBehavior_4002);
    LinkToolEntry entry = new LinkToolEntry(Messages.Richbehavior3CreationTool_title, Messages.Richbehavior3CreationTool_desc, types);
    entry.setId("createRichbehavior3CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.RichBehavior_4002));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createRIAfeature4CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.RIAFeature_2007);
    NodeToolEntry entry = new NodeToolEntry(Messages.RIAfeature4CreationTool_title, Messages.RIAfeature4CreationTool_desc, types);
    entry.setId("createRIAfeature4CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.RIAFeature_2007));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createButton1CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.Button_3002);
    NodeToolEntry entry = new NodeToolEntry(Messages.Button1CreationTool_title, Messages.Button1CreationTool_desc, types);
    entry.setId("createButton1CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.Button_3002));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createCheckBox2CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.CheckBox_3003);
    NodeToolEntry entry = new NodeToolEntry(Messages.CheckBox2CreationTool_title, Messages.CheckBox2CreationTool_desc, types);
    entry.setId("createCheckBox2CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.CheckBox_3003));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createComboBox3CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.ComboBox_3004);
    NodeToolEntry entry = new NodeToolEntry(Messages.ComboBox3CreationTool_title, Messages.ComboBox3CreationTool_desc, types);
    entry.setId("createComboBox3CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.ComboBox_3004));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createLabel4CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.Label_3005);
    NodeToolEntry entry = new NodeToolEntry(Messages.Label4CreationTool_title, Messages.Label4CreationTool_desc, types);
    entry.setId("createLabel4CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.Label_3005));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createLink5CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.Link_3006);
    NodeToolEntry entry = new NodeToolEntry(Messages.Link5CreationTool_title, Messages.Link5CreationTool_desc, types);
    entry.setId("createLink5CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.Link_3006));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createListBox6CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.ListBox_3007);
    NodeToolEntry entry = new NodeToolEntry(Messages.ListBox6CreationTool_title, Messages.ListBox6CreationTool_desc, types);
    entry.setId("createListBox6CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.ListBox_3007));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createListcontainer7CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.ListOfContainer_3011);
    NodeToolEntry entry = new NodeToolEntry(Messages.Listcontainer7CreationTool_title, Messages.Listcontainer7CreationTool_desc, types);
    entry.setId("createListcontainer7CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.ListOfContainer_3011));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createPanel8CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
    types.add(WebspecmodelElementTypes.PanelContainer_3001);
    types.add(WebspecmodelElementTypes.PanelContainer_3010);
    NodeToolEntry entry = new NodeToolEntry(Messages.Panel8CreationTool_title, Messages.Panel8CreationTool_desc, types);
    entry.setId("createPanel8CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.PanelContainer_3001));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createRadioButton9CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.RadioButton_3008);
    NodeToolEntry entry = new NodeToolEntry(Messages.RadioButton9CreationTool_title, Messages.RadioButton9CreationTool_desc, types);
    entry.setId("createRadioButton9CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.RadioButton_3008));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createTextField10CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.TextField_3009);
    NodeToolEntry entry = new NodeToolEntry(Messages.TextField10CreationTool_title, Messages.TextField10CreationTool_desc, types);
    entry.setId("createTextField10CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.TextField_3009));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createOneofstrings1CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.OneOfStrings_2003);
    NodeToolEntry entry = new NodeToolEntry(Messages.Oneofstrings1CreationTool_title, Messages.Oneofstrings1CreationTool_desc, types);
    entry.setId("createOneofstrings1CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.OneOfStrings_2003));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createOneofnumbers2CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.OneOfNumbers_2002);
    NodeToolEntry entry = new NodeToolEntry(Messages.Oneofnumbers2CreationTool_title, Messages.Oneofnumbers2CreationTool_desc, types);
    entry.setId("createOneofnumbers2CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.OneOfNumbers_2002));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createUniformnumber3CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.UniformNumberDistribution_2004);
    NodeToolEntry entry = new NodeToolEntry(Messages.Uniformnumber3CreationTool_title, Messages.Uniformnumber3CreationTool_desc, types);
    entry.setId("createUniformnumber3CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.UniformNumberDistribution_2004));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createArraygenerator4CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.OneOfArray_2005);
    NodeToolEntry entry = new NodeToolEntry(Messages.Arraygenerator4CreationTool_title, Messages.Arraygenerator4CreationTool_desc, types);
    entry.setId("createArraygenerator4CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.OneOfArray_2005));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private ToolEntry createStringgenerator5CreationTool() {
    List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
    types.add(WebspecmodelElementTypes.StringGenerator_2006);
    NodeToolEntry entry = new NodeToolEntry(Messages.Stringgenerator5CreationTool_title, Messages.Stringgenerator5CreationTool_desc, types);
    entry.setId("createStringgenerator5CreationTool"); //$NON-NLS-1$
    entry.setSmallIcon(WebspecmodelElementTypes.getImageDescriptor(WebspecmodelElementTypes.StringGenerator_2006));
    entry.setLargeIcon(entry.getSmallIcon());
    return entry;
  }

  /**
   * @generated
   */
  private static class NodeToolEntry extends ToolEntry {

    /**
     * @generated
     */
    private final List elementTypes;

    /**
     * @generated
     */
    private NodeToolEntry(String title, String description, List elementTypes) {
      super(title, description, null, null);
      this.elementTypes = elementTypes;
    }

    /**
     * @generated
     */
    public Tool createTool() {
      Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
      tool.setProperties(getToolProperties());
      return tool;
    }
  }

  /**
   * @generated
   */
  private static class LinkToolEntry extends ToolEntry {

    /**
     * @generated
     */
    private final List relationshipTypes;

    /**
     * @generated
     */
    private LinkToolEntry(String title, String description, List relationshipTypes) {
      super(title, description, null, null);
      this.relationshipTypes = relationshipTypes;
    }

    /**
     * @generated
     */
    public Tool createTool() {
      Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
      tool.setProperties(getToolProperties());
      return tool;
    }
  }
}
