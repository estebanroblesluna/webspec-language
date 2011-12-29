package org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel;

import java.util.ArrayList;
import java.util.Iterator;

import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.hypertext.DeleteActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.InputPanelSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.NavigationSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.PanelClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.RepetitionClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SaveActionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.SelectableRepetitionSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.impl.HypertextSpecFacade;
import org.webspeclanguage.mockupdd.specs.hypertext.impl.HypertextSpecFactoryImpl;
import org.webspeclanguage.mockupdd.transformations.specs2webml.datamodel.DataSpecs2WebMLDataModel;

public class HypertextSpec2WebMLWebModel {
	
	private DataSpecs2WebMLDataModel dataSpecs2WebMLDataModel;
	private HypertextSpecFacade hypertextSpecFacade = HypertextSpecFacade
			.getHypertextSpecFacade();
	private HypertextSpecFactoryImpl hypertextSpecFactory = (HypertextSpecFactoryImpl) getHypertextSpecFacade()
			.getHypertextSpecFactory();
	private WMTransformationFacade webModelTransformationFacade = WMTransformationFacade
			.getWMTransformationFacade();
	private WMTransformationFactory webModelTransformationFactory = getWebModelTransformationFacade()
			.getWMTransformationFactory();
	private SuiSpecsInferenceState suiSpecsInferenceState;

	private java.util.List<SelectableRepetitionSpec2MultiChoiceIU> selectableRepetitionSpec2MultiChoiceIUs = new ArrayList<SelectableRepetitionSpec2MultiChoiceIU>();
	private java.util.List<RepetitionClassMappingSpec2IndexUnit> repetitionClassMappingSpec2IndexUnits = new ArrayList<RepetitionClassMappingSpec2IndexUnit>();
	private java.util.List<PanelClassMappingSpec2DataUnit> panelClassMapping2DataUnits = new ArrayList<PanelClassMappingSpec2DataUnit>();
	private java.util.List<SUIPage2Page> suiPage2Pages = new ArrayList<SUIPage2Page>();
	private java.util.List<InputPanelSpec2EntryUnit> inputPanelSpec2EntryUnits = new ArrayList<InputPanelSpec2EntryUnit>();
	private java.util.List<SaveActionSpec2CreateUnit> saveActionSpec2CreateUnits = new ArrayList<SaveActionSpec2CreateUnit>();
	private java.util.List<DeleteActionSpec2DeleteUnit> deleteActionSpec2DeleteUnits = new ArrayList<DeleteActionSpec2DeleteUnit>();
	private java.util.List<NavigationSpec2NavigationPageToPage> navigationSpec2NavigationPageToPageList = new ArrayList<NavigationSpec2NavigationPageToPage>();
	private java.util.List<NavigationSpec2IntraNavigationUnitToUnit> navigationSpec2IntraNavigationUnitToUnitList = new ArrayList<NavigationSpec2IntraNavigationUnitToUnit>();
	private java.util.List<NavigationSpec2NavigationPUnitToPUnit> navigationSpec2NavigationPUnitToPUnitList = new ArrayList<NavigationSpec2NavigationPUnitToPUnit>();

	public HypertextSpec2WebMLWebModel(
			SuiSpecsInferenceState suiSpecsInferenceState,
			DataSpecs2WebMLDataModel dataSpecs2WebMLDataModel) {
		this.setSuiSpecsInferenceState(suiSpecsInferenceState);
		this.setDataSpecs2WebMLDataModel(dataSpecs2WebMLDataModel);
	}

	public void transform() {
		this.transformPage();
		this.transformPanelClassMapping();
		this.transformRepetitionClassMapping();
		this.transformSelectableRepetition();
		this.transformInputPanelSpec();
		this.transformSeletableRepetitionSpec();
		this.transformSaveAction();
		this.transformDeleteAction();
		this.transformPUnit2PUnitNavigationSpec();
		this.transformUnit2UnitNavigationSpec();
		this.transformPage2PageNavigationSpec();
	}

	public void transformSeletableRepetitionSpec(){
		Iterator<SelectableRepetitionSpec> srIt = this.getHypertextSpecFactory().getSelectableRepetitionSpecs().iterator();
		while(srIt.hasNext()){
			SelectableRepetitionSpec selectableSpec = srIt.next();
			this.getSelectableRepetitionSpec2MultiChoiceIUs().add(this.getWebModelTransformationFactory().transformSelectableRepetitionSpec2MultiChoiceIU(selectableSpec,this));
		}
	}
	public void transformPage() {
		Iterator<org.webspeclanguage.mockupdd.sui.model.Page> pIt = this.getSuiSpecsInferenceState().getModel().getPages().iterator();
		while(pIt.hasNext()){
			org.webspeclanguage.mockupdd.sui.model.Page suiPage = pIt.next();
			this.getSuiPage2Pages().add(this.getWebModelTransformationFactory().transformSUIPage2Page(suiPage,this));
		}
	}

	public void transformPUnit2PUnitNavigationSpec() {
		Iterator<NavigationSpec> navIt = this.getHypertextSpecFactory()
				.getNavigationSpecs().iterator();
		while (navIt.hasNext()) {
			NavigationSpec nav = navIt.next();
			if ((nav.getTo() != null) && (!nav.getTransfers().isEmpty())) {
				this.getNavigationSpec2NavigationPUnitToPUnitList().add(this.getWebModelTransformationFactory()
						.transformNavigationSpec2NavigationPUnitToPUnit(nav,this));
			}
		}
	}

	public void transformUnit2UnitNavigationSpec() {
		Iterator<NavigationSpec> navIt = this.getHypertextSpecFactory()
				.getNavigationSpecs().iterator();
		while (navIt.hasNext()) {
			NavigationSpec nav = navIt.next();
			if (nav.getTo() == null) {
				this.getNavigationSpec2IntraNavigationUnitToUnitList().add(this.getWebModelTransformationFactory()
						.transformNavigationSpec2IntraNavigationUnitToUnit(nav,this));
			}
		}
	}

	public void transformPage2PageNavigationSpec() {
		Iterator<NavigationSpec> navIt = this.getHypertextSpecFactory()
				.getNavigationSpecs().iterator();
		while (navIt.hasNext()) {
			NavigationSpec nav = navIt.next();
			if (nav.getTransfers().isEmpty()) {
				this.getNavigationSpec2NavigationPageToPageList().add(this.getWebModelTransformationFactory()
						.transformNavigationSpec2NavigationPageToPage(nav,this));
			}
		}
	}

	public void transformSelectableRepetition() {
		Iterator<SelectableRepetitionSpec> srIt = this
				.getHypertextSpecFactory().getSelectableRepetitionSpecs()
				.iterator();
		while (srIt.hasNext()) {
			SelectableRepetitionSpec selectableRepetitionSpec = srIt.next();
			this.getWebModelTransformationFactory()
					.transformRepetitionClassMappingSpec2IndexUnit(
							selectableRepetitionSpec,this);
		}
	}

	public void transformDeleteAction() {
		Iterator<DeleteActionSpec> dIt = this.getHypertextSpecFactory()
				.getDeleteActionSpecs().iterator();
		while (dIt.hasNext()) {
			DeleteActionSpec deleteActionSpec = dIt.next();
			this.getDeleteActionSpec2DeleteUnits().add(
					this.getWebModelTransformationFactory()
							.transformDeleteActionSpec2DeleteUnit(deleteActionSpec,this));
		}
	}

	public void transformSaveAction() {
		Iterator<SaveActionSpec> sIt = this.getHypertextSpecFactory()
				.getSaveActionSpecs().iterator();
		while (sIt.hasNext()) {
			SaveActionSpec saveActionSpec = sIt.next();
			this.getSaveActionSpec2CreateUnits()
					.add(this.getWebModelTransformationFactory()
							.transformSaveActionSpec2CreateUnit(saveActionSpec,this));
		}
	}

	public void transformInputPanelSpec() {
		Iterator<InputPanelSpec> ipIt = this.getHypertextSpecFactory()
				.getInputPanelSpecs().iterator();
		while (ipIt.hasNext()) {
			InputPanelSpec inputPanelSpec = ipIt.next();
			this.getInputPanelSpec2EntryUnits().add(
					this.getWebModelTransformationFactory()
							.transformInputPanelSpec2EntryUnit(inputPanelSpec,this));
		}
	}

	public void transformPanelClassMapping() {
		Iterator<PanelClassMappingSpec> pIt = this.getHypertextSpecFactory()
				.getPanelClassMappingSpecs().iterator();
		while (pIt.hasNext()) {
			PanelClassMappingSpec panelClass = pIt.next();
			this.getPanelClassMapping2DataUnits()
					.add(this
							.getWebModelTransformationFactory()
							.transformPanelClassMappingSpec2DataUnit(panelClass,this));
		}
	}

	public void transformRepetitionClassMapping() {
		Iterator<RepetitionClassMappingSpec> rIt = this
				.getHypertextSpecFactory().getRepetitionClassMappingSpecs()
				.iterator();
		while (rIt.hasNext()) {
			RepetitionClassMappingSpec repClass = rIt.next();
			this.getRepetitionClassMappingSpec2IndexUnits().add(
					this.getWebModelTransformationFactory()
							.transformRepetitionClassMappingSpec2IndexUnit(
									repClass,this));
		}
	}

	public void setHypertextSpecFactory(
			HypertextSpecFactoryImpl hypertextSpecFactory) {
		this.hypertextSpecFactory = hypertextSpecFactory;
	}

	public HypertextSpecFactoryImpl getHypertextSpecFactory() {
		return hypertextSpecFactory;
	}

	public void setWebModelTransformationFactory(
			WMTransformationFactory webModelTransformationFactory) {
		this.webModelTransformationFactory = webModelTransformationFactory;
	}

	public WMTransformationFactory getWebModelTransformationFactory() {
		return webModelTransformationFactory;
	}

	public void setWebModelTransformationFacade(
			WMTransformationFacade webModelTransformationFacade) {
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

	public void setSuiSpecsInferenceState(
			SuiSpecsInferenceState suiSpecsInferenceState) {
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

	public org.webspeclanguage.mockupdd.codegen.webml.webmodel.Page getPage(
			org.webspeclanguage.mockupdd.sui.model.Page to) {

		org.webspeclanguage.mockupdd.codegen.webml.webmodel.Page webMLPage = null;

		Iterator<SUIPage2Page> suiPageIt = this.getSuiPage2Pages().iterator();
		while (suiPageIt.hasNext()) {
			SUIPage2Page suiPage2Page = suiPageIt.next();
			if (suiPage2Page.getSuiPage().equals(to)) {
				webMLPage = suiPage2Page.getWebmlPage();
			}
		}

		return webMLPage;
	}

	public void setDataSpecs2WebMLDataModel(DataSpecs2WebMLDataModel dataSpecs2WebMLDataModel) {
		this.dataSpecs2WebMLDataModel = dataSpecs2WebMLDataModel;
	}

	public DataSpecs2WebMLDataModel getDataSpecs2WebMLDataModel() {
		return dataSpecs2WebMLDataModel;
	}

}
