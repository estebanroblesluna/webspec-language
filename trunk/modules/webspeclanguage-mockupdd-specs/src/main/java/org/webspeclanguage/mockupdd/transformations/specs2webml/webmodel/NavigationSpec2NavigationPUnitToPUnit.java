package org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.NormalLink;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.ContentUnit;
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ObjectTransferSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.impl.ObjectTransferSpecImpl;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Page;

public class NavigationSpec2NavigationPUnitToPUnit {

	private NavigationSpec navigationSpec;
	private NormalLink link;
	private HypertextSpecs2WebMLWebModel hypertextSpec2WebMLWebModel;	
	
	public NavigationSpec2NavigationPUnitToPUnit(NavigationSpec navigationSpec, HypertextSpecs2WebMLWebModel hypertextSpec2WebMLWebModel) {
		super();
		this.navigationSpec = navigationSpec;
		this.setHypertextSpec2WebMLWebModel(hypertextSpec2WebMLWebModel);
	}
	
	public void transform() {
		WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
		WebModelFactory webFactory = webModelFacade.getWebModelFactory();

		//For now is just 1 ObjectTransfer 
		ObjectTransferSpecImpl objT = (ObjectTransferSpecImpl)this.getNavigationSpec().getTransfers().toArray()[0];
		
		ContentUnit fromCU = this.getHypertextSpec2WebMLWebModel().findContentUnit((CompositeWidget)objT.getFrom());

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
			HypertextSpecs2WebMLWebModel hypertextSpec2WebMLWebModel) {
		this.hypertextSpec2WebMLWebModel = hypertextSpec2WebMLWebModel;
	}

	public HypertextSpecs2WebMLWebModel getHypertextSpec2WebMLWebModel() {
		return hypertextSpec2WebMLWebModel;
	}
}
