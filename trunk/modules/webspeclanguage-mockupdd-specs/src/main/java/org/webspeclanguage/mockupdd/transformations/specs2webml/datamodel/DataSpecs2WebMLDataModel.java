package org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.EntityDecorator;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.data.AssociationSpec;
import org.webspeclanguage.mockupdd.specs.data.AttributeSpec;
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.specs.data.impl.DataSpecFacade;
import org.webspeclanguage.mockupdd.specs.data.impl.DataSpecFactoryImpl;

public class DataSpecs2WebMLDataModel {

	private DataSpecFacade dataSpecFacade = DataSpecFacade.getDataSpecFacade();
	private DataSpecFactoryImpl dataSpecFactory = (DataSpecFactoryImpl) getDataSpecFacade()
			.getDataSpecFactory();
	private DMTransformationFacade dataModelTransformationFacade = DMTransformationFacade
			.getDMTransformationFacade();
	private DMTransformationFactory dataModelTransformationFactory = getDataModelTransformationFacade()
			.getDMTransformationFactory();

	private SuiSpecsInferenceState suiSpecsInferenceState;

	private List<AttributeSpec2Attribute> attributeSpec2Attributes = new ArrayList<AttributeSpec2Attribute>();
	private List<AttributeTypeSpec2Type> attributeTypeSpec2Types = new ArrayList<AttributeTypeSpec2Type>();
	private List<AssociationSpec2Relationship> associationSpec2Relationships = new ArrayList<AssociationSpec2Relationship>();
	private List<ClassSpec2Entity> classSpec2Entitys = new ArrayList<ClassSpec2Entity>();

	public DataSpecs2WebMLDataModel(
			SuiSpecsInferenceState suiSpecsInferenceState) {
		this.setSuiSpecsInferenceState(suiSpecsInferenceState);
	}

	public void transform() {
		this.transformClass();
		this.transformAttributes();
		this.transformAssociations();
	}

	private void transformClass() {
		Iterator<ClassSpec> csIt = getDataSpecFactory().getClassSpecs().iterator();
		while (csIt.hasNext()) {
			ClassSpec cs = csIt.next();
			this.getClassSpec2Entitys().add(
					getDataModelTransformationFactory()
							.transformClassSpec2Entity(cs));
		}
	}

	private void transformAttributes() {
		Iterator<AttributeSpec> asIt = getDataSpecFactory().getAttributeSpecs()
				.iterator();
		while (asIt.hasNext()) {
			AttributeSpec as = asIt.next();
			AttributeSpec2Attribute attributeSpec2Attribute = getDataModelTransformationFactory().transformAttributeSpec2Attribute(as);
			
			this.getAttributeSpec2Attributes().add(attributeSpec2Attribute);
			this.getAttributeTypeSpec2Types().add(attributeSpec2Attribute.getAttributeTypeSpec2Type());
		}
	}

	private void transformAssociations() {
		Iterator<AssociationSpec> assIt = getDataSpecFactory().getAssociationSpecs()
				.iterator();

		while (assIt.hasNext()) {
			AssociationSpec ass = assIt.next();

			boolean ok = false;
			ClassSpec sourceCS = null;
			Iterator<ClassSpec> cs2It = getDataSpecFactory().getClassSpecs()
					.iterator();

			while (cs2It.hasNext()) {
				ClassSpec cs2 = cs2It.next();

				Iterator<AssociationSpec> sourceASIt = cs2.getAssociations()
						.iterator();
				while ((sourceASIt.hasNext()) && (!ok)) {
					AssociationSpec sourceAS = sourceASIt.next();
					if (sourceAS.equals(ass)) {
						ok = true;
					}
				}
				if (ok) {
					sourceCS = cs2;
				}
			}
			this.getAssociationSpec2Relationships().add(
					getDataModelTransformationFactory()
							.transformAssociationSpec2Relationship(ass,
									sourceCS,this));
		}
	}

	public void setSuiSpecsInferenceState(
			SuiSpecsInferenceState suiSpecsInferenceState) {
		this.suiSpecsInferenceState = suiSpecsInferenceState;
	}

	public SuiSpecsInferenceState getSuiSpecsInferenceState() {
		return suiSpecsInferenceState;
	}

	public List<AttributeSpec2Attribute> getAttributeSpec2Attributes() {
		return attributeSpec2Attributes;
	}

	public void setAttributeSpec2Attributes(
			List<AttributeSpec2Attribute> attributeSpec2Attributes) {
		this.attributeSpec2Attributes = attributeSpec2Attributes;
	}

	public List<AttributeTypeSpec2Type> getAttributeTypeSpec2Types() {
		return attributeTypeSpec2Types;
	}

	public void setAttributeTypeSpec2Types(
			List<AttributeTypeSpec2Type> attributeTypeSpec2Types) {
		this.attributeTypeSpec2Types = attributeTypeSpec2Types;
	}

	public List<AssociationSpec2Relationship> getAssociationSpec2Relationships() {
		return associationSpec2Relationships;
	}

	public void setAssociationSpec2Relationships(
			List<AssociationSpec2Relationship> associationSpec2Relationships) {
		this.associationSpec2Relationships = associationSpec2Relationships;
	}

	public List<ClassSpec2Entity> getClassSpec2Entitys() {
		return classSpec2Entitys;
	}

	public void setClassSpec2Entitys(List<ClassSpec2Entity> classSpec2Entitys) {
		this.classSpec2Entitys = classSpec2Entitys;
	}

	public EntityDecorator getEntity(String classSpecName) {

		EntityDecorator entityDecorator = null;

		Iterator<ClassSpec2Entity> iterator = this.getClassSpec2Entitys()
				.iterator();
		while (iterator.hasNext()) {
			ClassSpec2Entity classSpec2Entity = (ClassSpec2Entity) iterator
					.next();
			if (classSpec2Entity.getClassSpec().getName().equals(classSpecName)) {
				entityDecorator = classSpec2Entity.getEntity();
			}
		}
		return entityDecorator;
	}

	public void setDataSpecFactory(DataSpecFactoryImpl dataSpecFactory) {
		this.dataSpecFactory = dataSpecFactory;
	}

	public DataSpecFactoryImpl getDataSpecFactory() {
		return dataSpecFactory;
	}

	public void setDataSpecFacade(DataSpecFacade dataSpecFacade) {
		this.dataSpecFacade = dataSpecFacade;
	}

	public DataSpecFacade getDataSpecFacade() {
		return dataSpecFacade;
	}

	public void setDataModelTransformationFacade(
			DMTransformationFacade dataModelTransformationFacade) {
		this.dataModelTransformationFacade = dataModelTransformationFacade;
	}

	public DMTransformationFacade getDataModelTransformationFacade() {
		return dataModelTransformationFacade;
	}

	public void setDataModelTransformationFactory(
			DMTransformationFactory dataModelTransformationFactory) {
		this.dataModelTransformationFactory = dataModelTransformationFactory;
	}

	public DMTransformationFactory getDataModelTransformationFactory() {
		return dataModelTransformationFactory;
	}
}
