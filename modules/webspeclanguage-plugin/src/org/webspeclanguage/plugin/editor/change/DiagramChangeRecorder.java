package org.webspeclanguage.plugin.editor.change;

import java.lang.reflect.Method;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.webspeclanguage.plugin.webspecmodel.Button;
import org.webspeclanguage.plugin.webspecmodel.CheckBox;
import org.webspeclanguage.plugin.webspecmodel.ComboBox;
import org.webspeclanguage.plugin.webspecmodel.Container;
import org.webspeclanguage.plugin.webspecmodel.Diagram;
import org.webspeclanguage.plugin.webspecmodel.Generator;
import org.webspeclanguage.plugin.webspecmodel.Interaction;
import org.webspeclanguage.plugin.webspecmodel.Label;
import org.webspeclanguage.plugin.webspecmodel.Link;
import org.webspeclanguage.plugin.webspecmodel.ListBox;
import org.webspeclanguage.plugin.webspecmodel.ListOfContainer;
import org.webspeclanguage.plugin.webspecmodel.PanelContainer;
import org.webspeclanguage.plugin.webspecmodel.RadioButton;
import org.webspeclanguage.plugin.webspecmodel.TextField;
import org.webspeclanguage.plugin.webspecmodel.Transition;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage;
import org.webspeclanguage.plugin.webspecmodel.Widget;


public class DiagramChangeRecorder {

  private Diagram diagram;
  private ChangeObject changeObject;
  private boolean recording;
  
  private int interactionsCount;
  private int transitionsCount;
  private int generatorsCount;
  private int widgetsCount;
  
  private Adapter adapter = new AdapterImpl() {
    public void notifyChanged(Notification notification) {
      newChange(notification);
    }
  };
  
  public DiagramChangeRecorder(Diagram diagram) {
    this.diagram = diagram;
    this.recording = false;
    this.interactionsCount = 0;
    this.transitionsCount = 0;
    this.generatorsCount = 0;
    this.widgetsCount = 0;
  }

  public void startRecording() {
    if (!this.recording) {
      this.addListeners();
      this.changeObject = new ChangeObject();
      this.recording = true;
    }
  }

  public ChangeObject stopRecording() {
    if (this.recording) {
      this.removeListeners();
      this.recording = false;
      return this.changeObject;
    } else {
      throw new IllegalStateException("Not recording");
    }
  }
  
  private void newChange(Notification notification) {
    if (notification.getNotifier() instanceof Diagram) {
      this.newChangeOfDiagram(notification);
    } else if (notification.getNotifier() instanceof Transition) {
      this.newChangeOfTransition(notification);
    } else if (notification.getNotifier() instanceof Interaction) {
      this.newChangeOfInteraction(notification);
    } else if (notification.getNotifier() instanceof Generator) {
      this.newChangeOfGenerator(notification);
    } else if (notification.getNotifier() instanceof Widget) {
      this.newChangeOfWidget(notification);
    }
  }
  
  private void newChangeOfInteraction(Notification notification) {
    if (notification.getEventType() == Notification.SET) {
      recordSetNotification(notification);
      if (notification.getFeature().equals(WebspecmodelPackage.eINSTANCE.getInteraction_Root())) {
        this.addListener((Container) notification.getNewValue());
      }
      return;
    }
  }

  private void recordSetNotification(Notification notification) {
    String feature = ((ENamedElement) notification.getFeature()).getName();
    String object = this.getObjectPath(notification.getNotifier());
    String newValue = this.getObjectPath(notification.getNewValue());
    if (feature.equals("name") && notification.getOldStringValue() != null) {
      //use the previous name this time
      String oldName = notification.getOldStringValue();
      int lastIndex = object.lastIndexOf(".");
      if (lastIndex == -1) {
        object = oldName;
      } else {
        object = object.substring(0, lastIndex) + "." + oldName;
      }
    }
    this.recordChange(new SetValue(object, feature, newValue));
  }

  private String getObjectPath(Object newValue) {
    if (newValue == null) {
      return "";
    } if (!(newValue instanceof EObject)) {
      return newValue.toString();
    } else {
      EObject object = (EObject) newValue;
      if (object instanceof Diagram) {
        return "";
      } else {
        try {
          Method method = newValue.getClass().getMethod("getName");
          String name = (String) method.invoke(newValue);
          String parentName = this.getObjectPath(object.eContainer());
          if (parentName != "") {
            return parentName + "." + name;
          } else {
            return name;
          }
        } catch (Exception e) {
          throw new RuntimeException("Unexpected error while obtaining object path", e);
        }
      }
    }
  }

  private void newChangeOfWidget(Notification notification) {
    if (notification.getEventType() == Notification.ADD 
        && notification.getFeature().equals(WebspecmodelPackage.eINSTANCE.getContainer_Widgets())) {
      Widget widget = (Widget) notification.getNewValue();
      EObject currentEObject = widget.eContainer();
      while (!(currentEObject instanceof Interaction)) {
        currentEObject = currentEObject.eContainer();
      }
      Interaction interaction = (Interaction) currentEObject;
      this.widgetsCount++;
      widget.setName("Widget" + this.widgetsCount);
      Container container = (Container) notification.getNotifier();
      String type = this.resolveTypeOf(widget);
      this.recordChange(new NewWidget(widget.getName(), container.getName(), type, interaction.getName()));
      this.addListener(widget);
      return;
    }
    
    if (notification.getEventType() == Notification.SET) {
      recordSetNotification(notification);
      return;
    }
  }

  private String resolveTypeOf(Widget widget) {
    if (widget instanceof Button) { return "Button"; }
    if (widget instanceof CheckBox) { return "CheckBox"; }
    if (widget instanceof ComboBox) { return "ComboBox"; }
    if (widget instanceof Label) { return "Label"; }
    if (widget instanceof Link) { return "Link"; }
    if (widget instanceof ListBox) { return "ListBox"; }
    if (widget instanceof ListOfContainer) { return "ListOfContainer"; }
    if (widget instanceof PanelContainer) { return "PanelContainer"; }
    if (widget instanceof RadioButton) { return "RadioButton"; }
    if (widget instanceof TextField) { return "TextField"; }
    return "UnknownType";
  }

  private void newChangeOfGenerator(Notification notification) {
    if (notification.getEventType() == Notification.SET) {
      recordSetNotification(notification);
    }
  }

  private void newChangeOfTransition(Notification notification) {
    if (notification.getEventType() == Notification.SET) {
      recordSetNotification(notification);
    }
  }

  private void newChangeOfDiagram(Notification notification) {
    if (notification.getEventType() == Notification.ADD 
        && notification.getFeature().equals(WebspecmodelPackage.eINSTANCE.getDiagram_Interactions())) {
      Interaction interaction = (Interaction) notification.getNewValue();
      this.interactionsCount++;
      interaction.setName("Interaction" + this.interactionsCount);
      this.recordChange(new NewInteraction(interaction.getName()));
      this.addListener(interaction);
      return;
    }

    if (notification.getEventType() == Notification.ADD 
        && notification.getFeature().equals(WebspecmodelPackage.eINSTANCE.getDiagram_Generators())) {
      Generator generator = (Generator) notification.getNewValue();
      this.generatorsCount++;
      generator.setName("Generator" + this.generatorsCount);
      this.recordChange(new NewGenerator(generator.getName()));
      this.addListener(generator);
      return;
    }
    
    if (notification.getEventType() == Notification.ADD 
        && notification.getFeature().equals(WebspecmodelPackage.eINSTANCE.getDiagram_Transitions())) {
      Transition transition = (Transition) notification.getNewValue();
      this.transitionsCount++;
      transition.setName("Transition" + this.transitionsCount);
      this.recordChange(new NewTransition(transition.getName()));
      this.addListener(transition);
      return;
    }
    
    if (notification.getEventType() == Notification.SET) {
      this.recordSetNotification(notification);
      return;
    }
  }

  private void recordChange(ChangeItem changeItem) {
    this.changeObject.add(changeItem);
  }

  private void addListeners() {
    diagram.eAdapters().add(adapter);

    //generators
    for (Object o : diagram.getGenerators()) {
      Generator generator = (Generator) o;
      addListener(generator);
    }

    //interactions
    for (Object o : diagram.getInteractions()) {
      Interaction interaction = (Interaction) o;
      addListener(interaction);
    }

    //navigations
    for (Object o : diagram.getTransitions()) {
      Transition transition = (Transition) o;
      addListener(transition);
    }
  }

  private void addListener(Transition transition) {
    transition.eAdapters().add(adapter);
  }

  private void addListener(Interaction interaction) {
    interaction.eAdapters().add(adapter);
    if (interaction.getRoot() != null) {
      this.addListener(interaction.getRoot());
    }
  }

  private void addListener(Generator generator) {
    generator.eAdapters().add(adapter);
  }

  private void addListener(Container container) {
    container.eAdapters().add(adapter);
    for (Object o : container.getWidgets()) {
      Widget widget = (Widget) o;
      if (widget instanceof Container) {
        this.addListener((Container) widget);
      } else {
        addListener(widget);
      }
    }
  }

  private void addListener(Widget widget) {
    widget.eAdapters().add(adapter);
  }

  private void removeListeners() {
    diagram.eAdapters().remove(adapter);

    //generators
    for (Object o : diagram.getGenerators()) {
      Generator generator = (Generator) o;
      generator.eAdapters().remove(adapter);
    }

    //interactions
    for (Object o : diagram.getInteractions()) {
      Interaction interaction = (Interaction) o;
      interaction.eAdapters().remove(adapter);
      this.removeListenerToWidgets(interaction.getRoot());
    }

    //navigations
    for (Object o : diagram.getTransitions()) {
      Transition transition = (Transition) o;
      transition.eAdapters().remove(adapter);
    }
  }

  private void removeListenerToWidgets(Container container) {
    container.eAdapters().remove(adapter);
    for (Object o : container.getWidgets()) {
      Widget widget = (Widget) o;
      if (widget instanceof Container) {
        this.removeListenerToWidgets((Container) widget);
      } else {
        widget.eAdapters().remove(adapter);
      }
    }
  }
}
