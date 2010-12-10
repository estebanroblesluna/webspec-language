package org.webspeclanguage.plugin.webspecmodel.diagram.part;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.webspeclanguage.plugin.webspecmodel.WebspecmodelPackage;


/**
 * @generated
 */
public class WebspecmodelDomainModelElementTester extends PropertyTester {

  /**
   * @generated
   */
  public boolean test(Object receiver, String method, Object[] args,
      Object expectedValue) {
    if (false == receiver instanceof EObject) {
      return false;
    }
    EObject eObject = (EObject) receiver;
    EClass eClass = eObject.eClass();
    if (eClass == WebspecmodelPackage.eINSTANCE.getDiagram()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getInteraction()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getTransition()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getWidget()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getGenerator()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getButton()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getTextField()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getRadioButton()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getListBox()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getComboBox()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getCheckBox()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getContainer()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getOneOfStrings()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getOneOfNumbers()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getUniformNumberDistribution()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getLabel()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getOneOfArray()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getStringGenerator()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getLink()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getListOfContainer()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getPanelContainer()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getNavigation()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getRichBehavior()) {
      return true;
    }
    if (eClass == WebspecmodelPackage.eINSTANCE.getRIAFeature()) {
      return true;
    }
    return false;
  }

}
