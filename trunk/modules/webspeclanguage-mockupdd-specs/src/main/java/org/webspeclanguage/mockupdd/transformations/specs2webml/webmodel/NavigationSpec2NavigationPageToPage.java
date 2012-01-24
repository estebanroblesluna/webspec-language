package org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.Page;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.NormalLink;
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;

public class NavigationSpec2NavigationPageToPage {

	private NavigationSpec navigationSpec;
	private NormalLink link;
	private HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel;	
	
	public NavigationSpec2NavigationPageToPage(NavigationSpec navigationSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
		super();
		this.navigationSpec = navigationSpec;
		this.setHypertextSpec2WebMLWebModel(hypertextSpec2WebMLWebModel);
	}
	
	public void transform() {
		WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
	    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
	    Page fromPage = this.getHypertextSpec2WebMLWebModel().getPage(this.getHypertextSpec2WebMLWebModel().getSuiSpecsInferenceState().getPageByWidget(this.getNavigationSpec().getTrigger()));  	    
	    Page toPage = this.getHypertextSpec2WebMLWebModel().getPage(this.getNavigationSpec().getTo());
		this.setLink(webFactory.createNormalLink(fromPage.getName() + "to" + toPage.getName(), true, fromPage, toPage));
	}

	public NavigationSpec getNavigationSpec() {
		return navigationSpec;
	}

	public void setNavigationSpec(NavigationSpec navigationSpec) {
		this.navigationSpec = navigationSpec;
	}

	public NormalLink getLink() {
		return link;
	}

	public void setLink(NormalLink link) {
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
