package org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.TransportLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.ContentUnit;
import org.webspeclanguage.mockupdd.specs.hypertext.ClassMappingSpec;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;

public class NavigationSpec2IntraNavigationUnitToUnit {

	private ClassMappingSpec<CompositeWidget> classMappingSpec;
	private TransportLink link;
	private HypertextSpecs2WebMLWebModel hypertextSpec2WebMLWebModel;	
	
	public NavigationSpec2IntraNavigationUnitToUnit(ClassMappingSpec<CompositeWidget> classMappingSpec, HypertextSpecs2WebMLWebModel hypertextSpec2WebMLWebModel) {
		super();
		this.classMappingSpec = classMappingSpec;
		this.setHypertextSpec2WebMLWebModel(hypertextSpec2WebMLWebModel);
	}
	
	public void transform() {
		WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
	    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
	    ContentUnit fromCU = this.getHypertextSpec2WebMLWebModel().findContentUnit((this.getClassMappingSpec().getDataSource()));
	    ContentUnit toCU = this.getHypertextSpec2WebMLWebModel().findContentUnit(this.getClassMappingSpec().getWidget());	   
		this.setLink(webFactory.createTransportLink(fromCU.getName() + "to" + toCU.getName(), true, fromCU, toCU));		
	}
	
	public ClassMappingSpec<CompositeWidget> getClassMappingSpec() {
		return classMappingSpec;
	}

	public void setClassMappingSpec(ClassMappingSpec<CompositeWidget> classMappingSpec) {
		this.classMappingSpec = classMappingSpec;
	}

	public TransportLink getLink() {
		return link;
	}

	public void setLink(TransportLink link) {
		this.link = link;
	}

	public void setHypertextSpec2WebMLWebModel(
			HypertextSpecs2WebMLWebModel hypertextSpec2WebMLWebModel) {
		this.hypertextSpec2WebMLWebModel = hypertextSpec2WebMLWebModel;
	}

	public HypertextSpecs2WebMLWebModel getHypertextSpec2WebMLWebModel() {
		return hypertextSpec2WebMLWebModel;
	}
}
