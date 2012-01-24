package org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.NormalLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.ContentUnit;
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ObjectTransferSpec;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Page;

public class NavigationSpec2NavigationPUnitToPUnit {

	private NavigationSpec navigationSpec;
	private NormalLink link;
	private HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel;	
	
	public NavigationSpec2NavigationPUnitToPUnit(NavigationSpec navigationSpec, HypertextSpec2WebMLWebModel hypertextSpec2WebMLWebModel) {
		super();
		this.navigationSpec = navigationSpec;
		this.setHypertextSpec2WebMLWebModel(hypertextSpec2WebMLWebModel);
	}
	
	public void transform() {
		WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
	    WebModelFactory webFactory = webModelFacade.getWebModelFactory();
	    CompositeWidget cw = this.getNavigationSpec().getTrigger().getParent();
	    ContentUnit fromCU = this.getHypertextSpec2WebMLWebModel().findContentUnit(cw);
	    
	    Page toPage = this.getNavigationSpec().getTo();
	    ContentUnit toCU = null;
	    
	    for(ObjectTransferSpec obj : this.getNavigationSpec().getTransfers()){
	    	Page objToPage = this.getHypertextSpec2WebMLWebModel().getSuiSpecsInferenceState().getPageByWidget(obj.getTo());
	    	if(objToPage.equals(toPage)){
	    		toCU = this.getHypertextSpec2WebMLWebModel().findContentUnit((CompositeWidget)obj.getTo());
	    	}
	    }   	    
		this.setLink(webFactory.createNormalLink(fromCU.getName() + "to" + toCU.getName(), true, fromCU, toCU));
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
