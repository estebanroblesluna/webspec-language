package org.webspeclanguage.plugin.webspecmodel.diagram.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * @generated
 */
public class WebspecmodelDomainNavigatorItem extends PlatformObject {

  /**
   * @generated
   */
  static {
    final Class[] supportedTypes = new Class[] { EObject.class, IPropertySource.class };
    Platform.getAdapterManager().registerAdapters(new IAdapterFactory() {

      public Object getAdapter(Object adaptableObject, Class adapterType) {
        if (adaptableObject instanceof org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelDomainNavigatorItem) {
          org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelDomainNavigatorItem domainNavigatorItem = (org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelDomainNavigatorItem) adaptableObject;
          EObject eObject = domainNavigatorItem.getEObject();
          if (adapterType == EObject.class) {
            return eObject;
          }
          if (adapterType == IPropertySource.class) {
            return domainNavigatorItem.getPropertySourceProvider().getPropertySource(eObject);
          }
        }

        return null;
      }

      public Class[] getAdapterList() {
        return supportedTypes;
      }
    }, org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelDomainNavigatorItem.class);
  }

  /**
   * @generated
   */
  private Object myParent;

  /**
   * @generated
   */
  private EObject myEObject;

  /**
   * @generated
   */
  private IPropertySourceProvider myPropertySourceProvider;

  /**
   * @generated
   */
  public WebspecmodelDomainNavigatorItem(EObject eObject, Object parent, IPropertySourceProvider propertySourceProvider) {
    myParent = parent;
    myEObject = eObject;
    myPropertySourceProvider = propertySourceProvider;
  }

  /**
   * @generated
   */
  public Object getParent() {
    return myParent;
  }

  /**
   * @generated
   */
  public EObject getEObject() {
    return myEObject;
  }

  /**
   * @generated
   */
  public IPropertySourceProvider getPropertySourceProvider() {
    return myPropertySourceProvider;
  }

  /**
   * @generated
   */
  public boolean equals(Object obj) {
    if (obj instanceof org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelDomainNavigatorItem) {
      return EcoreUtil.getURI(getEObject()).equals(
              EcoreUtil.getURI(((org.webspeclanguage.plugin.webspecmodel.diagram.navigator.WebspecmodelDomainNavigatorItem) obj).getEObject()));
    }
    return super.equals(obj);
  }

  /**
   * @generated
   */
  public int hashCode() {
    return EcoreUtil.getURI(getEObject()).hashCode();
  }

}
