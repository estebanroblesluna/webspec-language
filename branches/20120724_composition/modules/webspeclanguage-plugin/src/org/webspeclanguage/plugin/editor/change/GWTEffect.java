package org.webspeclanguage.plugin.editor.change;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

public class GWTEffect implements EffectHandler, ChangeItemVisitor {

  private IPackageFragment packageFragment;
  
  public GWTEffect(IPackageFragment packageFragment) {
    this.packageFragment = packageFragment;
  }
  
  @Override
  public void handle(ChangeObject changeObject) {
    for (ChangeItem item : changeObject.getItems()) {
      item.accept(this);
    }
  }

  @Override
  public void visitNewInteraction(NewInteraction newInteraction) {
    try {
      //create the compilation unit
      String className = newInteraction.getName() + "View";
      //if the compilation unit does not exists then add the class, constructor which calls the initializeComponentMethod()
      String contents = "package " + this.packageFragment.getElementName() +";\n"
      + "\n"
      + "import com.google.gwt.user.client.ui.VerticalPanel;\n"
      + "\n"
      + "public class " + className + " extends VerticalPanel {\n"
      + "\n"
      + "\tpublic " + className + "() {\n"
      + "\t\tthis.initializeComponent();\n"
      + "\t}\n"
      + "\n"
      + "\tpublic void initializeComponent() {\n"
      + "\n"
      + "\t}\n"
      + "}";

      this.packageFragment.createCompilationUnit(className + ".java", contents, false, null);
    } catch (JavaModelException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void visitNewWidget(NewWidget newWidget) {
    //get the class
    String className = newWidget.getInteractionName() + "View";
    ICompilationUnit compilationUnit = this.packageFragment.getCompilationUnit(className + ".java");
    
    //add the new instance variable
    IType javaClass = compilationUnit.getType(className);
    String gwtClass = this.getGWTClassFor(newWidget.getType());
    
    try {
      compilationUnit.createImport(this.getImportFor(gwtClass), null, Flags.AccDefault, null);
    } catch (JavaModelException e1) {
      e1.printStackTrace();
    }

    String fieldContents = "private " + gwtClass + " " + newWidget.getName() + ";";
    try {
      javaClass.createField(fieldContents, null, false, null);
    } catch (JavaModelException e) {
      e.printStackTrace();
    }
    
    //add the initializer for the instance variable
    try {
      IMethod method = javaClass.getMethod("initializeComponent", new String[] { });
      int openIndex = method.getSource().indexOf("{");
      int closeIndex = method.getSource().lastIndexOf("}");
      String oldSource = method.getSource().substring(openIndex + 1, closeIndex).trim();
      String newSource = (oldSource.isEmpty() ? "" : "\t\t") 
        + oldSource 
        + (oldSource.isEmpty() ? "" : "\n") 
        +  "\t\tthis." 
        + newWidget.getName() 
        + " = new " 
        + gwtClass 
        + "();\n";
      String contents = "\tpublic void initializeComponent() {\n"
        + newSource
        + "\t}\n";
      method.delete(true, null);
      javaClass.createMethod(contents, null, true, null);
    } catch (JavaModelException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void visitSetValue(SetValue setValue) {
    if (setValue.getObjectPath().indexOf(".") == -1) {
      //an interaction/transition/generator has changed
      String className = setValue.getObjectPath() + "View";
      ICompilationUnit compilationUnit = this.packageFragment.getCompilationUnit(className + ".java");
      if (compilationUnit != null && setValue.getFeature().equals("name")) {
        //then we rename it
        try {
          compilationUnit.rename(setValue.getNewValue() + "View.java", true, null);
        } catch (JavaModelException e) {
          e.printStackTrace();
        }
      }
    } else {
      //a widget has changed we assume for now no composition
      String widgetName = setValue.getObjectPath().substring(setValue.getObjectPath().lastIndexOf(".") + 1);
      //skip the root
      if (!widgetName.equals("null")) {
        String interactionName = setValue.getObjectPath().substring(0, setValue.getObjectPath().indexOf("."));
        String className = interactionName + "View";
        ICompilationUnit compilationUnit = this.packageFragment.getCompilationUnit(className + ".java");
        IType javaClass = compilationUnit.getType(className);
        try {
          javaClass.getField(widgetName).rename(setValue.getNewValue(), true, null);
        } catch (JavaModelException e) {
          e.printStackTrace();
        }
        
        //replace all the occurrences of this.oldWidgetName with this.newWidgetName
        try {
          String newSource = javaClass.getSource().replaceAll("this." + widgetName, "this." + setValue.getNewValue());
          javaClass.delete(true, null);
          compilationUnit.createType(newSource, null, true, null);
        } catch (JavaModelException e) {
          e.printStackTrace();
        }
      }
    }
  }


  private String getImportFor(String gwtClass) {
    if (gwtClass.equals("Button")) { return "com.google.gwt.user.client.ui.Button"; }
    if (gwtClass.equals("CheckBox")) { return "com.google.gwt.user.client.ui.CheckBox"; }
    if (gwtClass.equals("ComboBox")) { return "com.google.gwt.user.client.ui.ListBox"; }
    if (gwtClass.equals("Label")) { return "com.google.gwt.user.client.ui.Label"; }
    if (gwtClass.equals("ListBox")) { return "com.google.gwt.user.client.ui.ListBox"; }
    if (gwtClass.equals("VerticalPanel")) { return "com.google.gwt.user.client.ui.VerticalPanel"; }
    if (gwtClass.equals("RadioButton")) { return "com.google.gwt.user.client.ui.RadioButton"; }
    if (gwtClass.equals("TextBox")) { return "com.google.gwt.user.client.ui.TextBox"; }
    return "UnknownImport";
  }

  private String getGWTClassFor(String type) {
    if (type.equals("Button")) { return "Button"; }
    if (type.equals("CheckBox")) { return "CheckBox"; }
    if (type.equals("ComboBox")) { return "ComboBox"; }
    if (type.equals("Label")) { return "Label"; }
    if (type.equals("Link")) { return "Label"; }
    if (type.equals("ListBox")) { return "ListBox"; }
    if (type.equals("ListOfContainer")) { return "VerticalPanel"; }
    if (type.equals("PanelContainer")) { return "VerticalPanel"; }
    if (type.equals("RadioButton")) { return "RadioButton"; }
    if (type.equals("TextField")) { return "TextBox"; }
    return "UnknownType";
  }

  @Override
  public void visitNewGenerator(NewGenerator newGenerator) {
    //NOT HANDLED SO DO NOTHING
  }


  @Override
  public void visitNewTransition(NewTransition newTransition) {
    //NOT HANDLED SO DO NOTHING
  }
}
