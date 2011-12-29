package org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.Page;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.Link;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.TransportLink;
import org.webspeclanguage.mockupdd.specs.SuiSpecsConfig;
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;

public class NavigationSpec2IntraNavigationUnitToUnit {

	private NavigationSpec navigationSpec;
	private TransportLink link;
	private HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel;	
	
	public NavigationSpec2IntraNavigationUnitToUnit(NavigationSpec navigationSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
		super();
		this.navigationSpec = navigationSpec;
		this.setHypertextSpec2WebMLWebModel(hypertextSpec2WebMLWebModel);
	}
	
	public void transform() {
		WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
	    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
	    WMTransformationFacade wmTransformationFacade = WMTransformationFacade.getWMTransformationFacade();
	    WMTransformationFactory wmTransformationFactory = wmTransformationFacade.getWMTransformationFactory();
	    
	    Page fromPage;
	    
	    
	    Page toPage = wmTransformationFactory.getPage(this.getNavigationSpec().getTo()); 
	    
		this.setLink(webFactory.createTransportLink(fromPage.getName() + "to" + toPage.getName(), true, fromPage, toPage));

		
	}

	public NavigationSpec getNavigationSpec() {
		return navigationSpec;
	}

	public void setNavigationSpec(NavigationSpec navigationSpec) {
		this.navigationSpec = navigationSpec;
	}

	public TransportLink getLink() {
		return link;
	}

	public void setLink(TransportLink link) {
		this.link = link;
	}

	public void setHypertextSpec2WebMLWebModel(
			HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
		this.hypertextSpec2WebMLWebModel = hypertextSpec2WebMLWebModel;
	}

	public HypertextSpec2WebMLWebModel getHypertextSpec2WebMLWebModel() {
		return hypertextSpec2WebMLWebModel;
	}
}
