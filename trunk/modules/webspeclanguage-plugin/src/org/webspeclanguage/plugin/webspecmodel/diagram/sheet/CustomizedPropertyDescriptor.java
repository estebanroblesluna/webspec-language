package org.webspeclanguage.plugin.webspecmodel.diagram.sheet;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

public class CustomizedPropertyDescriptor extends PropertyDescriptor {

	public CustomizedPropertyDescriptor(Object object,
			IItemPropertyDescriptor itemPropertyDescriptor) {
		super(object, itemPropertyDescriptor);
	}

	@Override
	public CellEditor createPropertyEditor(Composite composite) {
		Object genericFeature = itemPropertyDescriptor.getFeature(object);
		if (genericFeature instanceof EStructuralFeature) {
			final EStructuralFeature feature = (EStructuralFeature) genericFeature;
			final EClassifier eType = feature.getEType();
			if (feature.getName().toUpperCase().endsWith("FILE") && eType.getInstanceClass().equals(String.class)) {
				return new FileDialogCellEditor(composite);
			} else {
				return super.createPropertyEditor(composite);
			}
		} else {
			return super.createPropertyEditor(composite);
		}
	}
}
