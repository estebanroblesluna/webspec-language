package org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel;

import java.util.ArrayList;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.SiteView;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModel;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFacade;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelFactory;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.ContentUnit;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.OperationUnit;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.hypertext.DeleteActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.HypertextSpecFactory;
import org.webspeclanguage.mockupdd.specs.hypertext.InputPanelSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.PanelClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.RepetitionClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SaveActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SelectableRepetitionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.impl.HypertextSpecFacade;
import org.webspeclanguage.mockupdd.specs.hypertext.impl.HypertextSpecFactoryImpl;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.SimpleWidget;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DataSpecs2WebMLDataModel;

public class HypertextSpec2WebMLWebModel {
	
	private DataSpecs2WebMLDataModel dataSpecs2WebMLDataModel;
	private HypertextSpecFacade hypertextSpecFacade = HypertextSpecFacade
			.getHypertextSpecFacade();
	private HypertextSpecFactory hypertextSpecFactory = (HypertextSpecFactoryImpl) getHypertextSpecFacade()
			.getHypertextSpecFactory();
	private WMTransformationFacade webModelTransformationFacade = WMTransformationFacade
			.getWMTransformationFacade();
	private WMTransformationFactory webModelTransformationFactory = getWebModelTransformationFacade()
			.getWMTransformationFactory();
	private SuiSpecsInferenceState suiSpecsInferenceState;
	
	private WebModelFacade webModelFacade = WebModelFacade.getWebModelFacade();
	private WebModelFactory webModelFactory = getWebModelFacade().getWebModelFactory(); 
	private java.util.List<Spec2ContentUnit> specs2CompositeUnits = new ArrayList<Spec2ContentUnit>();
	private java.util.List<SelectableRepetitionSpec2MultiChoiceIU> selectableRepetitionSpec2MultiChoiceIUs = new ArrayList<SelectableRepetitionSpec2MultiChoiceIU>();
	private java.util.List<RepetitionClassMappingSpec2IndexUnit> repetitionClassMappingSpec2IndexUnits = new ArrayList<RepetitionClassMappingSpec2IndexUnit>();
	private java.util.List<PanelClassMappingSpec2DataUnit> panelClassMapping2DataUnits = new ArrayList<PanelClassMappingSpec2DataUnit>();
	private java.util.List<InputPanelSpec2EntryUnit> inputPanelSpec2EntryUnits = new ArrayList<InputPanelSpec2EntryUnit>();
	private java.util.List<SUIPage2Page> suiPage2Pages = new ArrayList<SUIPage2Page>();
	private java.util.List<SaveActionSpec2CreateUnit> saveActionSpec2CreateUnits = new ArrayList<SaveActionSpec2CreateUnit>();
	private java.util.List<DeleteActionSpec2DeleteUnit> deleteActionSpec2DeleteUnits = new ArrayList<DeleteActionSpec2DeleteUnit>();
	private java.util.List<NavigationSpec2NavigationPageToPage> navigationSpec2NavigationPageToPageList = new ArrayList<NavigationSpec2NavigationPageToPage>();
	private java.util.List<NavigationSpec2IntraNavigationUnitToUnit> navigationSpec2IntraNavigationUnitToUnitList = new ArrayList<NavigationSpec2IntraNavigationUnitToUnit>();
	private java.util.List<NavigationSpec2NavigationPUnitToPUnit> navigationSpec2NavigationPUnitToPUnitList = new ArrayList<NavigationSpec2NavigationPUnitToPUnit>();
	private WebModel webModel;
	
	public HypertextSpec2WebMLWebModel(SuiSpecsInferenceState suiSpecsInferenceState,DataSpecs2WebMLDataModel dataSpecs2WebMLDataModel) {
		this.setSuiSpecsInferenceState(suiSpecsInferenceState);
		this.setDataSpecs2WebMLDataModel(dataSpecs2WebMLDataModel);
	}

	public void transform() {
		this.setWebModel(this.createWebModel());
		this.transformPage();
		this.transformPanelClassMapping();
		this.transformRepetitionClassMapping();
		this.transformSeletableRepetitionSpec();
		this.transformInputPanelSpec();
		this.transformSaveAction();
		this.transformDeleteAction();
		
		this.transformPUnit2PUnitNavigationSpec();
		this.transformUnit2UnitNavigationSpec();
		this.transformPage2PageNavigationSpec();
	}

	private WebModel createWebModel() {   
	    SiteView st = this.getWebModelFactory().createSiteView("SiteView1", true);
	    WebModel wModel = getWebModelFactory().createWebModel(st);
		return wModel;
	}

	public void transformPage(){
		for(org.webspeclanguage.mockupdd.sui.model.Page suiPage : this.getSuiSpecsInferenceState().getModel().getPages()){
			this.getSuiPage2Pages().add(
					this.getWebModelTransformationFactory().transformSUIPage2Page(suiPage,this));
		}
	}
	
	public void transformPage2PageNavigationSpec() {
		for(NavigationSpec nav:this.getSuiSpecsInferenceState().getNavigationSpecs()){
			if ((nav.getTransfers().isEmpty()) && (nav.getTo() != null)) {
				this.getNavigationSpec2NavigationPageToPageList().add(this.getWebModelTransformationFactory()
						.transformNavigationSpec2NavigationPageToPage(nav,this));
			}
		}
	}

	public void transformPUnit2PUnitNavigationSpec() {
		for(NavigationSpec nav : this.getSuiSpecsInferenceState().getNavigationSpecs()){
			if ((!nav.getTransfers().isEmpty()) && (nav.getTo() != null)) {
				this.getNavigationSpec2NavigationPUnitToPUnitList().add(this.getWebModelTransformationFactory()
						.transformNavigationSpec2NavigationPUnitToPUnit(nav,this));
			}
		}		
	}

	public void transformUnit2UnitNavigationSpec() {

		for(PanelClassMappingSpec classMappingSpec: this.getSuiSpecsInferenceState().getPanelClassMappingSpecs()){
			if (classMappingSpec.getDataSource() != null) {
				this.getNavigationSpec2IntraNavigationUnitToUnitList().add(this.getWebModelTransformationFactory()
						.transformNavigationSpec2IntraNavigationUnitToUnit(classMappingSpec,this));
			}
		}
		for(RepetitionClassMappingSpec classMappingSpec:  this.getSuiSpecsInferenceState().getRepetitionClassMappingSpecs()){
			if (classMappingSpec.getDataSource() != null) {
				this.getNavigationSpec2IntraNavigationUnitToUnitList().add(this.getWebModelTransformationFactory()
						.transformNavigationSpec2IntraNavigationUnitToUnit(classMappingSpec,this));
			}
		}

		for(SelectableRepetitionSpec classMappingSpec: this.getSuiSpecsInferenceState().getSelectableRepetitionSpecs()){
			if (classMappingSpec.getDataSource() != null) {
				this.getNavigationSpec2IntraNavigationUnitToUnitList().add(this.getWebModelTransformationFactory()
						.transformNavigationSpec2IntraNavigationUnitToUnit(classMappingSpec,this));
			}
		}

		for(InputPanelSpec classMappingSpec: this.getSuiSpecsInferenceState().getInputPanelSpecs()){
			if (classMappingSpec.getDataSource() != null) {
				this.getNavigationSpec2IntraNavigationUnitToUnitList().add(this.getWebModelTransformationFactory()
						.transformNavigationSpec2IntraNavigationUnitToUnit(classMappingSpec,this));
			}
		}
			
	}

	public void transformSeletableRepetitionSpec(){				
		for(SelectableRepetitionSpec selectableSpec : this.getSuiSpecsInferenceState().getSelectableRepetitionSpecs()){
			this.addSelectableRepetitionSpec2MultiChoiceIU(
					this.getWebModelTransformationFactory().transformSelectableRepetitionSpec2MultiChoiceIU(selectableSpec,this));
		}		
	}
	
	public void transformRepetitionClassMapping() {
		for(RepetitionClassMappingSpec repClass:this.getSuiSpecsInferenceState().getRepetitionClassMappingSpecs()){
			this.addRepetitionClassMappingSpec2IndexUnit(
					this.getWebModelTransformationFactory().transformRepetitionClassMappingSpec2IndexUnit(
							repClass,this));
		}
	}

	public void transformInputPanelSpec() {
		for(InputPanelSpec inputPanelSpec:this.getSuiSpecsInferenceState().getInputPanelSpecs()){
			this.addInputPanelSpec2EntryUnit(
					this.getWebModelTransformationFactory()
							.transformInputPanelSpec2EntryUnit(inputPanelSpec,this));
		}
	}

	public void transformPanelClassMapping() {
		for(PanelClassMappingSpec panelClass:this.getSuiSpecsInferenceState().getPanelClassMappingSpecs()){
			this.addPanelClassMapping2DataUnit(
					this.getWebModelTransformationFactory().transformPanelClassMappingSpec2DataUnit(panelClass,this));
		}
	}

	public void transformDeleteAction() {
		for(DeleteActionSpec deleteActionSpec:this.getSuiSpecsInferenceState().getDeleteActionSpecs()){
			this.getDeleteActionSpec2DeleteUnits().add(
					this.getWebModelTransformationFactory()
							.transformDeleteActionSpec2DeleteUnit(deleteActionSpec,this));
		}
	}

	public void transformSaveAction() {
		for(SaveActionSpec saveActionSpec:this.getSuiSpecsInferenceState().getSaveActionSpecs()){
			this.getSaveActionSpec2CreateUnits()
					.add(this.getWebModelTransformationFactory()
							.transformSaveActionSpec2CreateUnit(saveActionSpec,this));
		}
	}

	public void setHypertextSpecFactory(HypertextSpecFactoryImpl hypertextSpecFactory) {
		this.hypertextSpecFactory = hypertextSpecFactory;
	}

	public HypertextSpecFactory getHypertextSpecFactory() {
		return hypertextSpecFactory;
	}

	public void setWebModelTransformationFactory(WMTransformationFactory webModelTransformationFactory) {
		this.webModelTransformationFactory = webModelTransformationFactory;
	}

	public WMTransformationFactory getWebModelTransformationFactory() {
		return webModelTransformationFactory;
	}

	public void setWebModelTransformationFacade(WMTransformationFacade webModelTransformationFacade) {
		this.webModelTransformationFacade = webModelTransformationFacade;
	}

	public WMTransformationFacade getWebModelTransformationFacade() {
		return webModelTransformationFacade;
	}

	public void setHypertextSpecFacade(HypertextSpecFacade hypertextSpecFacade) {
		this.hypertextSpecFacade = hypertextSpecFacade;
	}

	public HypertextSpecFacade getHypertextSpecFacade() {
		return hypertextSpecFacade;
	}

	public void setSuiSpecsInferenceState(SuiSpecsInferenceState suiSpecsInferenceState) {
		this.suiSpecsInferenceState = suiSpecsInferenceState;
	}

	public SuiSpecsInferenceState getSuiSpecsInferenceState() {
		return suiSpecsInferenceState;
	}

	public java.util.List<SelectableRepetitionSpec2MultiChoiceIU> getSelectableRepetitionSpec2MultiChoiceIUs() {
		return selectableRepetitionSpec2MultiChoiceIUs;
	}

	public void setSelectableRepetitionSpec2MultiChoiceIUs(
			java.util.List<SelectableRepetitionSpec2MultiChoiceIU> selectableRepetitionSpec2MultiChoiceIUs) {
		this.selectableRepetitionSpec2MultiChoiceIUs = selectableRepetitionSpec2MultiChoiceIUs;
	}

	public java.util.List<RepetitionClassMappingSpec2IndexUnit> getRepetitionClassMappingSpec2IndexUnits() {
		return repetitionClassMappingSpec2IndexUnits;
	}

	public void setRepetitionClassMappingSpec2IndexUnits(
			java.util.List<RepetitionClassMappingSpec2IndexUnit> repetitionClassMappingSpec2IndexUnits) {
		this.repetitionClassMappingSpec2IndexUnits = repetitionClassMappingSpec2IndexUnits;
	}

	public java.util.List<PanelClassMappingSpec2DataUnit> getPanelClassMapping2DataUnits() {
		return panelClassMapping2DataUnits;
	}

	public void setPanelClassMapping2DataUnits(
			java.util.List<PanelClassMappingSpec2DataUnit> panelClassMapping2DataUnits) {
		this.panelClassMapping2DataUnits = panelClassMapping2DataUnits;
	}

	public java.util.List<SUIPage2Page> getSuiPage2Pages() {
		return suiPage2Pages;
	}

	public void setSuiPage2Pages(java.util.List<SUIPage2Page> suiPage2Pages) {
		this.suiPage2Pages = suiPage2Pages;
	}

	public java.util.List<InputPanelSpec2EntryUnit> getInputPanelSpec2EntryUnits() {
		return inputPanelSpec2EntryUnits;
	}

	public void setInputPanelSpec2EntryUnits(
			java.util.List<InputPanelSpec2EntryUnit> inputPanelSpec2EntryUnits) {
		this.inputPanelSpec2EntryUnits = inputPanelSpec2EntryUnits;
	}

	public java.util.List<SaveActionSpec2CreateUnit> getSaveActionSpec2CreateUnits() {
		return saveActionSpec2CreateUnits;
	}

	public void setSaveActionSpec2CreateUnits(
			java.util.List<SaveActionSpec2CreateUnit> saveActionSpec2CreateUnits) {
		this.saveActionSpec2CreateUnits = saveActionSpec2CreateUnits;
	}

	public java.util.List<DeleteActionSpec2DeleteUnit> getDeleteActionSpec2DeleteUnits() {
		return deleteActionSpec2DeleteUnits;
	}

	public void setDeleteActionSpec2DeleteUnits(
			java.util.List<DeleteActionSpec2DeleteUnit> deleteActionSpec2DeleteUnits) {
		this.deleteActionSpec2DeleteUnits = deleteActionSpec2DeleteUnits;
	}

	public void setNavigationSpec2NavigationPageToPageList(
			java.util.List<NavigationSpec2NavigationPageToPage> navigationSpec2NavigationPageToPageList) {
		this.navigationSpec2NavigationPageToPageList = navigationSpec2NavigationPageToPageList;
	}

	public java.util.List<NavigationSpec2NavigationPageToPage> getNavigationSpec2NavigationPageToPageList() {
		return navigationSpec2NavigationPageToPageList;
	}

	public void setNavigationSpec2IntraNavigationUnitToUnitList(
			java.util.List<NavigationSpec2IntraNavigationUnitToUnit> navigationSpec2IntraNavigationUnitToUnitList) {
		this.navigationSpec2IntraNavigationUnitToUnitList = navigationSpec2IntraNavigationUnitToUnitList;
	}

	public java.util.List<NavigationSpec2IntraNavigationUnitToUnit> getNavigationSpec2IntraNavigationUnitToUnitList() {
		return navigationSpec2IntraNavigationUnitToUnitList;
	}

	public void setNavigationSpec2NavigationPUnitToPUnitList(
			java.util.List<NavigationSpec2NavigationPUnitToPUnit> navigationSpec2NavigationPUnitToPUnitList) {
		this.navigationSpec2NavigationPUnitToPUnitList = navigationSpec2NavigationPUnitToPUnitList;
	}

	public java.util.List<NavigationSpec2NavigationPUnitToPUnit> getNavigationSpec2NavigationPUnitToPUnitList() {
		return navigationSpec2NavigationPUnitToPUnitList;
	}

	public void setDataSpecs2WebMLDataModel(DataSpecs2WebMLDataModel dataSpecs2WebMLDataModel) {
		this.dataSpecs2WebMLDataModel = dataSpecs2WebMLDataModel;
	}

	public DataSpecs2WebMLDataModel getDataSpecs2WebMLDataModel() {
		return dataSpecs2WebMLDataModel;
	}

	public void setWebModel(WebModel webModel) {
		this.webModel = webModel;
	}

	public WebModel getWebModel() {
		return webModel;
	}

	public org.webspeclanguage.mockupdd.codegen.webml.webmodel.Page getPage(org.webspeclanguage.mockupdd.sui.model.Page to) {

		org.webspeclanguage.mockupdd.codegen.webml.webmodel.Page webMLPage = null;
		for(SUIPage2Page suiPage2Page: this.getSuiPage2Pages()){
			if (suiPage2Page.getSuiPage().equals(to)) {
				webMLPage = suiPage2Page.getWebmlPage();
			}
		}
		
		try{
			if(webMLPage == null){
				throw new Exception();
			}			
		}catch (Exception exc){
			System.out.print("HypertextSpec2WebMlWebModel.getPage could not find a WebMLPage for a SuiPage");
			
		}
		return webMLPage;
	}
	
	public void addContentUnitToPage(Widget widget, ContentUnit contentUnit){
	    org.webspeclanguage.mockupdd.sui.model.Page suiPage = this.getSuiSpecsInferenceState().getPageByWidget(widget);
	    org.webspeclanguage.mockupdd.codegen.webml.webmodel.Page webMLPage = this.getPage(suiPage);
	    webMLPage.addContentUnit(contentUnit);
	}

	public void addOperationUnit(OperationUnit operationUnit, CompositeWidget widget) {
		this.getWebModel().getHomeSiteView().addOperationUnit(operationUnit);
		ContentUnit contentUnit = this.findContentUnit(widget);
		this.getWebModelFactory().createNormalLink(contentUnit.getName() + "to" + operationUnit.getName(), true, contentUnit, operationUnit);
	}
		
	public ContentUnit findContentUnit(CompositeWidget widget) {
		ContentUnit contentUnit = null;
		for(Spec2ContentUnit cUnit: this.getSpecs2CompositeUnits()){
			if(cUnit.getSpec().getWidget().equals(widget)){
				contentUnit = cUnit.getContentUnit();
			}
		}
				
		try{
			if(contentUnit == null){
				throw new Exception();
			}			
		}catch (Exception exc){
			System.out.print("HypertextSpec2WebMlWebModel.findContentUnit could not find a ContentUnit");
			
		}
		
		return contentUnit;
	}

	private void addSelectableRepetitionSpec2MultiChoiceIU(
			SelectableRepetitionSpec2MultiChoiceIU transformSelectableRepetitionSpec2MultiChoiceIU) {
		this.getSelectableRepetitionSpec2MultiChoiceIUs().add(transformSelectableRepetitionSpec2MultiChoiceIU);
		this.getSpecs2CompositeUnits().add(transformSelectableRepetitionSpec2MultiChoiceIU);
	}

	private void addRepetitionClassMappingSpec2IndexUnit(
			RepetitionClassMappingSpec2IndexUnit transformRepetitionClassMappingSpec2IndexUnit) {
		this.getRepetitionClassMappingSpec2IndexUnits().add(transformRepetitionClassMappingSpec2IndexUnit);
		this.getSpecs2CompositeUnits().add(transformRepetitionClassMappingSpec2IndexUnit);
	}

	private void addInputPanelSpec2EntryUnit(
			InputPanelSpec2EntryUnit transformInputPanelSpec2EntryUnit) {
		this.getInputPanelSpec2EntryUnits().add(transformInputPanelSpec2EntryUnit);
		this.getSpecs2CompositeUnits().add(transformInputPanelSpec2EntryUnit);
		
	}

	private void addPanelClassMapping2DataUnit(
			PanelClassMappingSpec2DataUnit transformPanelClassMappingSpec2DataUnit) {
		this.getPanelClassMapping2DataUnits().add(transformPanelClassMappingSpec2DataUnit);
		this.getSpecs2CompositeUnits().add(transformPanelClassMappingSpec2DataUnit);		
	}
	
	public void setWebModelFacade(WebModelFacade webModelFacade) {
		this.webModelFacade = webModelFacade;
	}

	public WebModelFacade getWebModelFacade() {
		return webModelFacade;
	}

	public void setWebModelFactory(WebModelFactory webModelFactory) {
		this.webModelFactory = webModelFactory;
	}

	public WebModelFactory getWebModelFactory() {
		return webModelFactory;
	}

	public void setSpecs2CompositeUnits(java.util.List<Spec2ContentUnit> specs2CompositeUnits) {
		this.specs2CompositeUnits = specs2CompositeUnits;
	}

	public java.util.List<Spec2ContentUnit> getSpecs2CompositeUnits() {
		return specs2CompositeUnits;
	}

}
