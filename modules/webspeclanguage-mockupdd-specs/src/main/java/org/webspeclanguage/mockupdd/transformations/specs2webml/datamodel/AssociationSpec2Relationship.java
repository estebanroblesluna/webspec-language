package org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel;

import java.util.ArrayList;
import java.util.List;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.EntityDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Relationship;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.RelationshipDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.RelationshipRole;
import org.webspeclanguage.mockupdd.specs.data.AssociationSpec;
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;

public class AssociationSpec2Relationship {

	private AssociationSpec associationSpec;
	private RelationshipDecorator relationshipDecorator;
	private DataSpecs2WebMLDataModel dataSpecs2WebMLDataModel;
	
	public AssociationSpec2Relationship(AssociationSpec associationSpec, DataSpecs2WebMLDataModel dataSpecs2WebMLDataModel) {
		super();
		this.associationSpec = associationSpec;
		this.dataSpecs2WebMLDataModel = dataSpecs2WebMLDataModel;
	}

	public void setAssociationSpec(AssociationSpec associationSpec) {
		this.associationSpec = associationSpec;
	}

	public AssociationSpec getAssociationSpec() {
		return associationSpec;
	}

	public void setRelationshipDecorator(
			RelationshipDecorator relationshipDecorator) {
		this.relationshipDecorator = relationshipDecorator;
	}

	public RelationshipDecorator getRelationshipDecorator() {
		return relationshipDecorator;
	}

	public void transform(ClassSpec associationSource) {

		DataModelFacade dataModelFacade = DataModelFacade.getDataModelFacade();
		DataModelFactory dataFactory = dataModelFacade.getDataModelFactory();

		List<RelationshipRole> relationshipRoleList = new ArrayList<RelationshipRole>();

		relationshipRoleList.add(dataFactory.createRelationshipRole(this
				.getAssociationSpec().getDestinationClass().getName(),
				this.getCardinality()));
		
		String name = this.getAssociationSpec().getAssociationName();
		EntityDecorator sourceEntity = (this.getDataSpecs2WebMLDataModel().getEntity(associationSource.getName()));
		EntityDecorator targetEntity = (this.getDataSpecs2WebMLDataModel().getEntity(this.getAssociationSpec().getDestinationClass().getName()));
		
		Relationship relationship = dataFactory.createRelationship(name,sourceEntity.getEntity(),targetEntity.getEntity(), relationshipRoleList);
		RelationshipDecorator rd = dataFactory.createRelationshipDecorator(relationship);
		sourceEntity.addRelationship(rd);
		this.setRelationshipDecorator(rd);
	}

	public String getCardinality() {
		String cardinality = "1";
		switch (this.getAssociationSpec().getMaximumCardinality()) {
		case ONE:
			cardinality = "1";
			break;
		case MANY:
			cardinality = "N";
			break;
		}
		return cardinality;
	}

	public void setDataSpecs2WebMLDataModel(DataSpecs2WebMLDataModel dataSpecs2WebMLDataModel) {
		this.dataSpecs2WebMLDataModel = dataSpecs2WebMLDataModel;
	}

	public DataSpecs2WebMLDataModel getDataSpecs2WebMLDataModel() {
		return dataSpecs2WebMLDataModel;
	}

}
