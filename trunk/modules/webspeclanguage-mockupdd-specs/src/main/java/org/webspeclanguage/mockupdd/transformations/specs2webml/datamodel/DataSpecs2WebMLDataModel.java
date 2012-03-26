package org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModel;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Entity;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.EntityDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Relationship;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.RelationshipDecorator;
import org.webspeclanguage.mockupdd.specs.SuiSpecsConfig;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.data.AssociationSpec;
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
	private DataModel dataModel;
	
	public DataSpecs2WebMLDataModel(SuiSpecsInferenceState suiSpecsInferenceState) {
		this.setSuiSpecsInferenceState(suiSpecsInferenceState);
	}

	public void transform() {
		this.transformClass();
		this.transformAssociations();
		this.setDataModel(this.createDataModel()); 
	}

	private DataModel createDataModel() {
	    DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
	    DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();
	    
	    Map<String,Entity> entityMap = new HashMap<String,Entity>();
	    Map<String,Relationship> relationshipsMap = new HashMap<String,Relationship>();
	    for(EntityDecorator entD: dataModelFacade.getEntitys().values()){
	    	entityMap.put(entD.getId(), entD.getEntity());    	
	    	
	    	for(RelationshipDecorator rD: entD.getRelationships().values()){
	    		relationshipsMap.put(rD.getId(),rD.getRelationship());
	    	}	    	
	    }	    	  
	    return dataFactory.createDataModel(entityMap, relationshipsMap);	    		
	}

	private void transformClass() {
				
		for (ClassSpec cs : this.getSuiSpecsInferenceState().getClassSpecs()){
			this.getClassSpec2Entitys().add(getDMTFactory().transformClassSpec2Entity(cs));
		}	
	}

	private void transformAssociations() {
		for(AssociationSpec ass : getDataSpecFactory().getAssociationSpecs()){
			boolean ok = false;
			ClassSpec sourceCS = null;
			
			for(ClassSpec cs2 : getDataSpecFactory().getClassSpecs()){
				Iterator<AssociationSpec> sourceASIt = cs2.getAssociations().iterator();
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
			AssociationSpec2Relationship as2r = this.getDMTFactory().transformAssociationSpec2Relationship(ass,sourceCS,this);
			this.getAssociationSpec2Relationships().add(as2r);
		}
	}

	public EntityDecorator getEntity(String classSpecName) {
		EntityDecorator entityDecorator = null;
		for(ClassSpec2Entity classSpec2Entity : this.getClassSpec2Entitys()){
			if (classSpec2Entity.getClassSpec().getName().equals(classSpecName)) {
				entityDecorator = classSpec2Entity.getEntity();
			}
		}
		return entityDecorator;
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

	public void setDataModelTransformationFacade(DMTransformationFacade dataModelTransformationFacade) {
		this.dataModelTransformationFacade = dataModelTransformationFacade;
	}

	public DMTransformationFacade getDataModelTransformationFacade() {
		return dataModelTransformationFacade;
	}

	public void setDMTFactory(DMTransformationFactory dataMTFactory) {
		this.dataModelTransformationFactory = dataMTFactory;
	}

	public DMTransformationFactory getDMTFactory() {
		return dataModelTransformationFactory;
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	public DataModel getDataModel() {
		return dataModel;
	}
}
