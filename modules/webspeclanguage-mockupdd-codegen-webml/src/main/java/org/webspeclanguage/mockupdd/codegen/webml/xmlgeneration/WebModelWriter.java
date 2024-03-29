/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.webspeclanguage.mockupdd.codegen.webml.xmlgeneration;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.NormalField;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.field.SelectionField;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.patternconfiguration.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;
import java.io.File;
import java.util.Stack;
/**
 * @author Franco Giacosa
 */
public class WebModelWriter extends WebModelConcreteVisitor {

  private Stack<CreatorWrapper> files = new Stack<CreatorWrapper>();
  private String rootPath;
  
  public WebModelWriter(String rootPath){
    this.rootPath = rootPath;
  }
  public Stack<CreatorWrapper> getFile() {
    return files;
  }
  
  public void visit(WebModel webModel) {
    String folderPath = this.getRootPath() + "WebModel" + "/";
    File newFolder= new File(folderPath); 
    newFolder.mkdir();
    CreatorWrapper fileCreator = new CreatorWrapper(folderPath, "Properties.wr");
    this.getFile().push(fileCreator);
    
    this.beginWebModel(webModel);
    super.visit(webModel);
    this.endWebModel();
  }
  public void visit(SiteView siteView){
    String folderPath = this.getFile().peek().getFolderPath() + siteView.getId() + "/";
    
    File newFolder= new File(folderPath); 
    newFolder.mkdir();
    
    CreatorWrapper fileCreator = new CreatorWrapper(folderPath, "Properties.wr");
    this.getFile().push(fileCreator);
    
    this.beginSiteView(siteView);
    super.visit(siteView);
    this.endSiteView(siteView);
  }
  public void visit(Page page){
    String folderPath = this.getFile().peek().getFolderPath();
    CreatorWrapper fileCreator = new CreatorWrapper(folderPath, page.getId() + ".wr");
    this.getFile().push(fileCreator);
    
    this.beginPage(page);
    super.visit(page);
    this.endPage(page);
  }
  public void visit(Locale locale) {
    this.beginLocale(locale);
    super.visit(locale);
    this.endLocale();
  }
  public void visit(PatternConfigurationBoolean patternConfigurationBoolean) {
    this.beginPatternConfigurationBoolean(patternConfigurationBoolean);
    super.visit(patternConfigurationBoolean);
  }
  public void visit(PatternConfigurationDecimal patternConfigurationDecimal) {
    this.beginPatternConfigurationDecimal(patternConfigurationDecimal);
    super.visit(patternConfigurationDecimal);
  }
  public void visit(PatternConfigurationDate patternConfigurationDate) {
    this.beginPatternConfigurationDate(patternConfigurationDate);
    super.visit(patternConfigurationDate);
  }
  public void visit(PatternConfigurationFloat patternConfigurationFloat) {
    this.beginPatternConfigurationFloat(patternConfigurationFloat);
    super.visit(patternConfigurationFloat);
  }
  public void visit(PatternConfigurationInteger patternConfigurationInteger) {
    this.beginPatternConfigurationInteger(patternConfigurationInteger);
    super.visit(patternConfigurationInteger);
  }
  public void visit(PatternConfigurationTime patternConfigurationTime) {
    this.beginPatternConfigurationTime(patternConfigurationTime);
    super.visit(patternConfigurationTime);
  }
  public void visit(PatternConfigurationTimeStamp patternConfigurationTimeStamp) {
    this.beginPatternConfigurationTimeStamp(patternConfigurationTimeStamp);
    super.visit(patternConfigurationTimeStamp);
  }
  public void visit(IndexUnit indexUnit){
    this.beginIndexUnit(indexUnit);
    super.visit(indexUnit);
    this.endIndexUnit(indexUnit);
  }
  public void visit(MultiChoiceIndexUnit multiChoiceIndexUnit){
    this.beginMultiChoiceIndexUnit(multiChoiceIndexUnit);
    super.visit(multiChoiceIndexUnit);
    this.endMultiChoiceIndexUnit(multiChoiceIndexUnit);
  }
  public void visit(SelectorUnit selectorUnit){
    this.beginSelectorUnit(selectorUnit);
    super.visit(selectorUnit);
    this.endSelectorUnit(selectorUnit);
  }
  public void visit(DataUnit dataUnit){
    this.beginDataUnit(dataUnit);
    super.visit(dataUnit);
    this.endDataUnit();
  }
  public void visit(EntryUnit entryUnit){
    this.beginEntryUnit(entryUnit);
    super.visit(entryUnit);
    this.endEntryUnit(entryUnit);
  }
  public void visit(AutomaticLink automaticLink){
    this.beginAutomaticLink(automaticLink);
    super.visit(automaticLink);
    this.endAutomaticLink(automaticLink);
  }
  public void visit(KOLink koLink){
    this.beginKOLink(koLink);
    super.visit(koLink);
    this.endKOLink(koLink);
  }
  public void visit(OKLink okLink){
    this.beginOKLink(okLink);
    super.visit(okLink);
    this.endOKLink(okLink);
  }
  public void visit(NormalLink normalLink){
    this.beginNormalLink(normalLink);
    super.visit(normalLink);
    this.endNormalLink(normalLink);
  }
  public void visit(TransportLink transportLink){
    this.beginTransportLink(transportLink);
    super.visit(transportLink);
    this.endTransportLink(transportLink);
  }
  public void visit(ParameterCoupling parameterCoupling){
    this.beginParameterCoupling(parameterCoupling);
    super.visit(parameterCoupling);
  }
  public void visit(NormalField normalField){
    this.beginNormalField(normalField);
    super.visit(normalField);
  }
  public void visit(SelectionField selectionField){
    this.beginSelectionField(selectionField);
    super.visit(selectionField);
  }
  public void visit(AttributeParameter attributeParameter){
    this.beginAttributeParameter(attributeParameter);
  }
  private void beginAttributeParameter(AttributeParameter attributeParameter) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString(attributeParameter.getAttribute().getName());
  }
  public void visit(CurrentOIDParameter currentOIDParameter){
    this.beginCurrentOIDParameter(currentOIDParameter);
  }
  private void beginCurrentOIDParameter(CurrentOIDParameter currentOIDParameter) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString("current " + currentOIDParameter.getCurrentOID().getName());
  }
  public void visit(KeyConditionParameter keyConditionParameter){
    this.beginKeyConditionParameter(keyConditionParameter);
  }
  private void beginKeyConditionParameter(KeyConditionParameter keyConditionParameter) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString(keyConditionParameter.getKeyCondition().getName() + " [" + keyConditionParameter.getKeyCondition().getKey().getName() + "]");
  }
  public void visit(NormalFieldParameter normalFieldParameter){
    this.beginNormalFieldParameter(normalFieldParameter);
  }
  private void beginNormalFieldParameter(NormalFieldParameter normalFieldParameter) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString(normalFieldParameter.getField().getName());
  }
  public void visit(RelationshipParameter relationshipParameter){
    this.beginRelationshipParameter(relationshipParameter);
  }
  private void beginRelationshipParameter(RelationshipParameter relationshipParameter) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString(relationshipParameter.getRelationship().getName());
  }
  public void visit(SelectionFieldLabelParameter selectionFieldParameter){
    this.beginSelectionFieldLabelParameter(selectionFieldParameter);
  }
  private void beginSelectionFieldLabelParameter(SelectionFieldLabelParameter selectionFieldParameter) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString(selectionFieldParameter.getSelectionField().getName());
  }
  public void visit(SelectionFieldOutputParameter selectionFieldParameter){
    this.beginSelectionFieldOutputParameter(selectionFieldParameter);
  }
  private void beginSelectionFieldOutputParameter(SelectionFieldOutputParameter selectionFieldParameter) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString(selectionFieldParameter.getSelectionField().getName());
  }
  public void visit(SelectionFieldPreselectionParameter selectionFieldParameter){
    this.beginSelectionFieldPreselectionParameter(selectionFieldParameter);
  }
  private void beginSelectionFieldPreselectionParameter(SelectionFieldPreselectionParameter selectionFieldParameter) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString(selectionFieldParameter.getSelectionField().getName());
  }
  public void visit(DefaultUnitParameter defaultUnitParameter){
    this.beginDefaultUnitParameter(defaultUnitParameter);
  }
  private void beginDefaultUnitParameter(DefaultUnitParameter defaultUnitParameter) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString(defaultUnitParameter.getId());
  }
  public void visit(OutputSelectionFieldParameter outputSelectionFieldParameter){
    this.beginOutputSelectionFieldParameter(outputSelectionFieldParameter);
  }
  private void beginOutputSelectionFieldParameter(OutputSelectionFieldParameter outputSelectionFieldParameter) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString(outputSelectionFieldParameter.getSelectionField().getName());
  }
  private void beginSelectionField(SelectionField selectionField) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<SelectionField ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(selectionField.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(selectionField.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("type=\"");
    fileCreatorWrapper.appendString(selectionField.getType().getTypeName());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString("/>");
    fileCreatorWrapper.newLine();
  }
  private void beginNormalField(NormalField normalField) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<Field ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(normalField.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(normalField.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("type=\"");
    fileCreatorWrapper.appendString(normalField.getType().getTypeName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("modifiable=\"");
    fileCreatorWrapper.appendString(normalField.getModifiable().toString());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("preloaded=\"");
    fileCreatorWrapper.appendString(normalField.getPreloaded().toString());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString("/>");
    fileCreatorWrapper.newLine();
  }
  private void beginParameterCoupling(ParameterCoupling parameterCoupling) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<LinkParameter ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(parameterCoupling.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    parameterCoupling.getSourceParameter().accept(this);
    fileCreatorWrapper.appendString("_");
    parameterCoupling.getTargetParameter().accept(this);
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("source=\"");
    fileCreatorWrapper.appendString(parameterCoupling.getSourceParameter().getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("target=\"");
    fileCreatorWrapper.appendString(parameterCoupling.getTargetParameter().getId());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString("/>");
    fileCreatorWrapper.newLine();
  }
  private void beginTransportLink(TransportLink transportLink) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<Link ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(transportLink.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(transportLink.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("to=\"");
    fileCreatorWrapper.appendString(transportLink.getTo().getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("type=\"");
    fileCreatorWrapper.appendString("transport");
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("validate=\"");
    fileCreatorWrapper.appendString("true");
    if(transportLink.getAutomaticCoupling()){
      fileCreatorWrapper.appendString("\" ");
      fileCreatorWrapper.appendString("automaticCoupling=\"");
      fileCreatorWrapper.appendString(transportLink.getAutomaticCoupling().toString());
      fileCreatorWrapper.appendString("\"");
      fileCreatorWrapper.appendString("/>");
    }
    else{      
      fileCreatorWrapper.appendString("\">");
    }
    
    fileCreatorWrapper.newLine();
  }
  private void endTransportLink(TransportLink transportLink) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    if(!transportLink.getAutomaticCoupling()){
      fileCreatorWrapper.appendString("</Link>");
      fileCreatorWrapper.newLine();
    }
    
  }
  private void beginNormalLink(NormalLink normalLink) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<Link ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(normalLink.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(normalLink.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("to=\"");
    fileCreatorWrapper.appendString(normalLink.getTo().getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("type=\"");
    fileCreatorWrapper.appendString("normal");
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("validate=\"");
    fileCreatorWrapper.appendString("true");
    if(normalLink.getAutomaticCoupling()){
      fileCreatorWrapper.appendString("\" ");
      fileCreatorWrapper.appendString("automaticCoupling=\"");
      fileCreatorWrapper.appendString(normalLink.getAutomaticCoupling().toString());
      fileCreatorWrapper.appendString("\"");
      fileCreatorWrapper.appendString("/>");
    }
    else{
      fileCreatorWrapper.appendString("\">");
    }
    
    fileCreatorWrapper.newLine();
  }
  private void endNormalLink(NormalLink normalLink) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    if(!normalLink.getAutomaticCoupling()){
      fileCreatorWrapper.appendString("</Link>");
      fileCreatorWrapper.newLine();
    }
  }
  private void beginOKLink(OKLink okLink) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<OKLink ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(okLink.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(okLink.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("to=\"");
    fileCreatorWrapper.appendString(okLink.getTo().getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("validate=\"");
    fileCreatorWrapper.appendString("true");
    if(okLink.getAutomaticCoupling()){
      fileCreatorWrapper.appendString("\" ");
      fileCreatorWrapper.appendString("automaticCoupling=\"");
      fileCreatorWrapper.appendString(okLink.getAutomaticCoupling().toString());
      fileCreatorWrapper.appendString("\"");
      fileCreatorWrapper.appendString("/>");
    }
    else{
      fileCreatorWrapper.appendString("\">");
    }
    fileCreatorWrapper.newLine();
  }
  private void endOKLink(OKLink okLink) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    if(!okLink.getAutomaticCoupling()){
      fileCreatorWrapper.appendString("</OKLink>");
      fileCreatorWrapper.newLine();
    }
  }
  private void beginKOLink(KOLink koLink) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<KOLink ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(koLink.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(koLink.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("to=\"");
    fileCreatorWrapper.appendString(koLink.getTo().getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("validate=\"");
    fileCreatorWrapper.appendString("true");
    if(koLink.getAutomaticCoupling()){
      fileCreatorWrapper.appendString("\" ");
      fileCreatorWrapper.appendString("automaticCoupling=\"");
      fileCreatorWrapper.appendString(koLink.getAutomaticCoupling().toString());
      fileCreatorWrapper.appendString("\"");
      fileCreatorWrapper.appendString("/>");
    }
    else{
      fileCreatorWrapper.appendString("\">");
    }
    fileCreatorWrapper.newLine();
  }
  private void endKOLink(KOLink koLink) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    if(!koLink.getAutomaticCoupling()){
      fileCreatorWrapper.appendString("</KOLink>");
      fileCreatorWrapper.newLine();
    }
  }
  private void beginAutomaticLink(AutomaticLink automaticLink) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<Link ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(automaticLink.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(automaticLink.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("to=\"");
    fileCreatorWrapper.appendString(automaticLink.getTo().getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("type=\"");
    fileCreatorWrapper.appendString("automatic");
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("validate=\"");
    fileCreatorWrapper.appendString("true");
    if(automaticLink.getAutomaticCoupling()){
      fileCreatorWrapper.appendString("\" ");
      fileCreatorWrapper.appendString("automaticCoupling=\"");
      fileCreatorWrapper.appendString(automaticLink.getAutomaticCoupling().toString());
      fileCreatorWrapper.appendString("\"");
      fileCreatorWrapper.appendString("/>");
    }
    else{
      fileCreatorWrapper.appendString("\">");
    }
    fileCreatorWrapper.newLine();

  }
  private void endAutomaticLink(AutomaticLink automaticLink) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    if(!automaticLink.getAutomaticCoupling()){
      fileCreatorWrapper.appendString("</Link>");
      fileCreatorWrapper.newLine();
    }  
  }
  public void visit(MultiEntryUnit multiEntryUnit){
    this.beginMultiEntryUnit(multiEntryUnit);
    super.visit(multiEntryUnit);
    this.endMultiEntryUnit(multiEntryUnit);
  }
  private void beginMultiEntryUnit(MultiEntryUnit multiEntryUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<MultiEntryUnit ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(multiEntryUnit.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(multiEntryUnit.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("minLength=\"");
    fileCreatorWrapper.appendString(multiEntryUnit.getMinLength());
    if((multiEntryUnit.getFields().isEmpty()) & (multiEntryUnit.getLinks().isEmpty())){
      fileCreatorWrapper.appendString("\"/>");
    }
    else{
      fileCreatorWrapper.appendString("\">");
    }
   
    fileCreatorWrapper.newLine();
  }
  private void endMultiEntryUnit(MultiEntryUnit multiEntryUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    
    if((!multiEntryUnit.getFields().isEmpty()) | (!multiEntryUnit.getLinks().isEmpty())){
      fileCreatorWrapper.appendString("</MultiEntryUnit>");
    }
    fileCreatorWrapper.newLine();
  }
  private void beginEntryUnit(EntryUnit entryUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<EntryUnit ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(entryUnit.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(entryUnit.getName());
    if((entryUnit.getFields().isEmpty()) & (entryUnit.getLinks().isEmpty())){
      fileCreatorWrapper.appendString("\"/>");
    }
    else{
      fileCreatorWrapper.appendString("\">");
    }

    fileCreatorWrapper.newLine();
  }
  private void endEntryUnit(EntryUnit entryUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    
    if((!entryUnit.getFields().isEmpty()) | (!entryUnit.getLinks().isEmpty())){
      fileCreatorWrapper.appendString("</EntryUnit>");
    }
    fileCreatorWrapper.newLine();
  }
  private void beginDataUnit(DataUnit dataUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<DataUnit ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(dataUnit.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(dataUnit.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("entity=\"");
    fileCreatorWrapper.appendString(dataUnit.getEntity().getId());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString(">");
    fileCreatorWrapper.newLine();
  }
  private void endDataUnit() {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    
    fileCreatorWrapper.appendString("</DataUnit>");
    fileCreatorWrapper.newLine();
  }
  private void beginSelectorUnit(SelectorUnit selectorUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<SelectorUnit ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(selectorUnit.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(selectorUnit.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("entity=\"");
    fileCreatorWrapper.appendString(selectorUnit.getEntity().getId());
    fileCreatorWrapper.appendString("\"");
    if(!selectorUnit.getLinks().isEmpty())
    {
      fileCreatorWrapper.appendString(">");
      fileCreatorWrapper.newLine();
    }
  }
  private void endSelectorUnit(SelectorUnit selectorUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    if(!selectorUnit.getLinks().isEmpty()){
      fileCreatorWrapper.appendString("</SelectorUnit>");
    }
    else{
      fileCreatorWrapper.appendString("/>");
    }
    
    fileCreatorWrapper.newLine();
  }
  private void beginMultiChoiceIndexUnit(MultiChoiceIndexUnit multiChoiceIndexUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<MultiChoiceIndexUnit ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(multiChoiceIndexUnit.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(multiChoiceIndexUnit.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("entity=\"");
    fileCreatorWrapper.appendString(multiChoiceIndexUnit.getEntity().getId());
    fileCreatorWrapper.appendString("\"");
    if(!multiChoiceIndexUnit.getLinks().isEmpty())
    {
      fileCreatorWrapper.appendString(">");
      fileCreatorWrapper.newLine();
    }
  }
  private void endMultiChoiceIndexUnit(MultiChoiceIndexUnit multiChoiceIndexUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    if(!multiChoiceIndexUnit.getLinks().isEmpty()){
      fileCreatorWrapper.appendString("</MultiChoiceIndexUnit>");
    }
    else{
      fileCreatorWrapper.appendString("/>");
    }
    
    fileCreatorWrapper.newLine();
  }
  private void beginIndexUnit(IndexUnit indexUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<IndexUnit ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(indexUnit.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(indexUnit.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("entity=\"");
    fileCreatorWrapper.appendString(indexUnit.getEntity().getId());
    fileCreatorWrapper.appendString("\"");
    if(!indexUnit.getLinks().isEmpty())
    {
      fileCreatorWrapper.appendString(">");
      fileCreatorWrapper.newLine();
    }
    
  }
  private void endIndexUnit(IndexUnit indexUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    if(!indexUnit.getLinks().isEmpty()){
      fileCreatorWrapper.appendString("</IndexUnit>");
    }
    else{
      fileCreatorWrapper.appendString("/>");
    }
    
    fileCreatorWrapper.newLine();
  }
  public void visit(CreateUnit createUnit){
    this.beginCreateUnit(createUnit);
    super.visit(createUnit);
    this.endCreateUnit(createUnit);
  }
  public void visit(DeleteUnit deleteUnit){
    this.beginDeleteUnit(deleteUnit);
    super.visit(deleteUnit);
    this.endDeleteUnit();
  }
  public void visit(ModifyUnit modifyUnit){
    this.beginModifyUnit(modifyUnit);
    super.visit(modifyUnit);
    this.endModifyUnit();
  }
  public void visit(Selector selector){
    this.beginSelector(selector);
    super.visit(selector);
    this.endSelector(selector);
  }
  public void visit(KeyCondition keyCondition){
    this.beginKeyCondition(keyCondition);
    super.visit(keyCondition);
  }
  private void beginKeyCondition(KeyCondition keyCondition) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<KeyCondition ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(keyCondition.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(keyCondition.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("predicate=\"");
    fileCreatorWrapper.appendString(keyCondition.getPredicate());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("implied=\"");
    fileCreatorWrapper.appendString(keyCondition.getImplied().toString());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString("/>");
    fileCreatorWrapper.newLine();
  }
  private void beginSelector(Selector selector) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    
    fileCreatorWrapper.appendString("<Selector ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(selector.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("defaultPolicy=\"");
    fileCreatorWrapper.appendString(selector.getDefaultPolicy());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("booleanOperator=\"");
    fileCreatorWrapper.appendString(selector.getBooleanOperator());
    fileCreatorWrapper.appendString("\"");
    if(!selector.getKeyConditions().isEmpty()){
      fileCreatorWrapper.appendString(">");
      fileCreatorWrapper.newLine();
    }

  }
  private void endSelector(Selector selector) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    
    if(!selector.getKeyConditions().isEmpty()){
       fileCreatorWrapper.appendString("</Selector>");
    }
    else{
      fileCreatorWrapper.appendString("/>");
    }
    
    fileCreatorWrapper.newLine();
    
  }
  private void beginModifyUnit(ModifyUnit modifyUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    
    fileCreatorWrapper.appendString("<ModifyUnit ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(modifyUnit.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(modifyUnit.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("entity=\"");
    fileCreatorWrapper.appendString(modifyUnit.getEntity().getId());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString(">");
    fileCreatorWrapper.newLine();
  }
  private void endModifyUnit() {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    
    fileCreatorWrapper.appendString("</ModifyUnit>");
    fileCreatorWrapper.newLine();
  }
  private void beginDeleteUnit(DeleteUnit deleteUnit) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<DeleteUnit ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(deleteUnit.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(deleteUnit.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("entity=\"");
    fileCreatorWrapper.appendString(deleteUnit.getEntity().getId());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString(">");
    fileCreatorWrapper.newLine(); 
  }
  private void endDeleteUnit() {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString("</DeleteUnit>");
    fileCreatorWrapper.newLine();
  }
  private void beginCreateUnit(CreateUnit createUnit){
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<CreateUnit ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(createUnit.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(createUnit.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("entity=\"");
    fileCreatorWrapper.appendString(createUnit.getEntity().getId());
    fileCreatorWrapper.appendString("\"");
    if(!createUnit.getLinks().isEmpty()){
      fileCreatorWrapper.appendString(">");
      fileCreatorWrapper.newLine();
    }
  }
  private void endCreateUnit(CreateUnit createUnit){
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    if(!createUnit.getLinks().isEmpty()){
      fileCreatorWrapper.appendString("</CreateUnit>");
    }
    else{
      fileCreatorWrapper.appendString("/>");
    }
    fileCreatorWrapper.newLine();

  }
  private void beginSiteView(SiteView siteView) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString("<SiteView ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(siteView.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(siteView.getName());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("homePage=\"");
    if(siteView.getHomePage() != null){
      fileCreatorWrapper.appendString(siteView.getHomePage().getId());
    }
    else{
      fileCreatorWrapper.appendString("");
    }
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString(">");
    fileCreatorWrapper.newLine();
    if(siteView.getOperationUnits().isEmpty()){
      fileCreatorWrapper.appendString("<OperationUnits />");
    }
    else{
      fileCreatorWrapper.appendString("<OperationUnits>");
    }
    fileCreatorWrapper.newLine();
  }
  private void endSiteView(SiteView siteView) {
    CreatorWrapper fileCreatorWrapper = this.getFile().pop();
    if(!siteView.getOperationUnits().isEmpty()){
      fileCreatorWrapper.appendString("</OperationUnits>");
      fileCreatorWrapper.newLine();
    }
    fileCreatorWrapper.appendString("</SiteView>");
    fileCreatorWrapper.newLine();
    fileCreatorWrapper.closeFile();

  }
  private void beginPatternConfigurationBoolean(PatternConfigurationBoolean patternConfigurationBoolean) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<PatternConfiguration ");
    fileCreatorWrapper.appendString("type=\"");
    fileCreatorWrapper.appendString(patternConfigurationBoolean.getType());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("pattern=\"");
    fileCreatorWrapper.appendString(patternConfigurationBoolean.getPattern());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString("/>");
    fileCreatorWrapper.newLine();
  }
  private void beginPatternConfigurationDecimal(PatternConfigurationDecimal patternConfigurationDecimal) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<PatternConfiguration ");
    fileCreatorWrapper.appendString("type=\"");
    fileCreatorWrapper.appendString(patternConfigurationDecimal.getType());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("maxDecimal=\"");
    fileCreatorWrapper.appendString(patternConfigurationDecimal.getMaxDecimal().toString());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("minDecimal=\"");
    fileCreatorWrapper.appendString(patternConfigurationDecimal.getMinDecimal().toString());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("minIngeter=\"");
    fileCreatorWrapper.appendString(patternConfigurationDecimal.getMinInteger().toString());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("useThousandSeparator=\"");
    fileCreatorWrapper.appendString(patternConfigurationDecimal.getUseThousandSeparator().toString());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString("/>");
    fileCreatorWrapper.newLine();
  } 
  private void beginPatternConfigurationDate(PatternConfigurationDate patternConfigurationDate) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<PatternConfiguration ");
    fileCreatorWrapper.appendString("type=\"");
    fileCreatorWrapper.appendString(patternConfigurationDate.getType());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("pattern=\"");
    fileCreatorWrapper.appendString(patternConfigurationDate.getPattern());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString("/>");
    fileCreatorWrapper.newLine();
  }  
  private void beginPatternConfigurationFloat(PatternConfigurationFloat patternConfigurationFloat) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<PatternConfiguration ");
    fileCreatorWrapper.appendString("type=\"");
    fileCreatorWrapper.appendString(patternConfigurationFloat.getType());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("maxDecimal=\"");
    fileCreatorWrapper.appendString(patternConfigurationFloat.getMaxDecimal().toString());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("minDecimal=\"");
    fileCreatorWrapper.appendString(patternConfigurationFloat.getMinDecimal().toString());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("minIngeter=\"");
    fileCreatorWrapper.appendString(patternConfigurationFloat.getMinInteger().toString());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("useThousandSeparator=\"");
    fileCreatorWrapper.appendString(patternConfigurationFloat.getUseThousandSeparator().toString());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString("/>");
    fileCreatorWrapper.newLine();
  }
  private void beginPatternConfigurationInteger(PatternConfigurationInteger patternConfigurationInteger) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<PatternConfiguration ");
    fileCreatorWrapper.appendString("type=\"");
    fileCreatorWrapper.appendString(patternConfigurationInteger.getType());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("minIngeter=\"");
    fileCreatorWrapper.appendString(patternConfigurationInteger.getMinInteger().toString());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("useThousandSeparator=\"");
    fileCreatorWrapper.appendString(patternConfigurationInteger.getUseThousandSeparator().toString());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString("/>");
    fileCreatorWrapper.newLine();
  }
  private void beginPatternConfigurationTime(PatternConfigurationTime patternConfigurationTime) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<PatternConfiguration ");
    fileCreatorWrapper.appendString("type=\"");
    fileCreatorWrapper.appendString(patternConfigurationTime.getType());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("pattern=\"");
    fileCreatorWrapper.appendString(patternConfigurationTime.getPattern());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString("/>");
    fileCreatorWrapper.newLine();
  }
  private void beginPatternConfigurationTimeStamp(PatternConfigurationTimeStamp patternConfigurationTimeStamp) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<PatternConfiguration ");
    fileCreatorWrapper.appendString("type=\"");
    fileCreatorWrapper.appendString(patternConfigurationTimeStamp.getType());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("pattern=\"");
    fileCreatorWrapper.appendString(patternConfigurationTimeStamp.getPattern());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString("/>");
    fileCreatorWrapper.newLine();
  }
  private void beginLocale(Locale locale) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<Locale ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(locale.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("country=\"");
    fileCreatorWrapper.appendString(locale.getCountry());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("language=\"");
    fileCreatorWrapper.appendString(locale.getLanguage());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString(">");
    fileCreatorWrapper.newLine();
  }
  private void endLocale() {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString("</Locale>");
    fileCreatorWrapper.newLine();
  }
  private void beginWebModel(WebModel webModel) {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString("<WebModel ");
    fileCreatorWrapper.appendString("defaultLocale=\"");
    fileCreatorWrapper.appendString(webModel.getDefaultLocale());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("homeSiteView=\"");
    fileCreatorWrapper.appendString(webModel.getHomeSiteView().getId());
    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString(">");
    fileCreatorWrapper.newLine();
  }
  private void endWebModel() {
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();
    fileCreatorWrapper.appendString("</WebModel>");
    fileCreatorWrapper.newLine();
    fileCreatorWrapper.closeFile();
  }
  public void beginPage(Page page){
    CreatorWrapper fileCreatorWrapper = this.getFile().peek();

    fileCreatorWrapper.appendString("<Page ");
    fileCreatorWrapper.appendString("id=\"");
    fileCreatorWrapper.appendString(page.getId());
    fileCreatorWrapper.appendString("\" ");
    fileCreatorWrapper.appendString("name=\"");
    fileCreatorWrapper.appendString(page.getName());
    if(page.getLandmark()){
      fileCreatorWrapper.appendString("\" ");
      fileCreatorWrapper.appendString("landmark=\"");
      fileCreatorWrapper.appendString(page.getLandmark().toString());
    }
    if(!page.getLinks().isEmpty()){
      fileCreatorWrapper.appendString("\" ");
      fileCreatorWrapper.appendString("linkOrder=\"");
      fileCreatorWrapper.appendString(page.getLinkOrderStringMode());
    }

    fileCreatorWrapper.appendString("\"");
    fileCreatorWrapper.appendString(">");
    fileCreatorWrapper.newLine(); 

    if(page.getContentUnits().isEmpty()){
      fileCreatorWrapper.appendString("<ContentUnits />");
    }
    else{
      fileCreatorWrapper.appendString("<ContentUnits>");
    }
    
    fileCreatorWrapper.newLine();
  }
  private void endPage(Page page) {
    CreatorWrapper fileCreatorWrapper = this.getFile().pop();
    if(!page.getContentUnits().isEmpty()){
      fileCreatorWrapper.appendString("</ContentUnits>");
      fileCreatorWrapper.newLine();
    }
    fileCreatorWrapper.appendString("</Page>");
    fileCreatorWrapper.newLine();
    fileCreatorWrapper.closeFile();
  }
  
  public String getRootPath() {
    return rootPath;
  }
}
