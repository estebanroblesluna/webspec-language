package org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel;

import java.util.List;
import java.util.ArrayList;

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.RelationshipDecorator;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.RelationshipRole;
import org.webspeclanguage.mockupdd.specs.data.AssociationSpec;
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;

public class AssociationSpec2Relationship {

	private AssociationSpec associationSpec;
	private RelationshipDecorator relationshipDecorator;

	public AssociationSpec2Relationship(AssociationSpec associationSpec) {
		super();
		this.associationSpec = associationSpec;
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

		this.setRelationshipDecorator(dataFactory.createRelationshipDecorator(dataFactory
				.createRelationship(this.getAssociationSpec()
						.getAssociationName(), (DMTransformationFacade
						.getDMTransformationFacade()
						.getDMTransformationFactory()
						.getEntity(associationSource.getName())).getEntity(),
						(DMTransformationFacade.getDMTransformationFacade()
								.getDMTransformationFactory().getEntity(this
								.getAssociationSpec().getDestinationClass()
								.getName())).getEntity(), relationshipRoleList)));

	}

	public String getCardinality() {
		String cardinality = "1";
		switch (this.getAssociationSpec().getMaximumCardinality()) {
		case ONE:
			cardinality = "1";
		case MANY:
			cardinality = "N";
		}
		return cardinality;
	}

}
