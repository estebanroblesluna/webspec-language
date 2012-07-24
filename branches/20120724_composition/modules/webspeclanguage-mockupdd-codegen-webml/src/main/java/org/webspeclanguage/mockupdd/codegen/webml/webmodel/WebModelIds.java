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
package org.webspeclanguage.mockupdd.codegen.webml.webmodel;

/**
 * @author Franco Giacosa
 */
public class WebModelIds {

  
    private Integer siteViewId = 1;
    private Integer pageId = 1;
    private Integer linkId = 1;
    private Integer createUnitId = 1;
    private Integer dataUnitId = 1;
    private Integer deleteUnitId = 1;
    private Integer entryUnitId = 1;
    private Integer indexUnitId = 1;
    private Integer modifyUnitId = 1;
    private Integer multiChoiceIndexUnitId = 1;
    private Integer multiEntryUnitId = 1;
    private Integer selectorUnitId = 1;
    private Integer parameterCouplingId = 1;
    private Integer selectorId = 1;
    private Integer keyConditionId = 1;
    private Integer keyConditionName = 1;
    private Integer normalFieldId = 1;
    private Integer selectionFieldId = 1;
    
  public WebModelIds() {
      super();
  }
 
	public String getSiteViewId() {
		return "sv" + (siteViewId ++).toString();
	}
	public String getPageId() {
		return "page" + (pageId ++).toString();
	}
	public String getLinkId() {
		return "ln" + (linkId ++).toString();
	}
	public String getCreateUnitId() {
		return "cru" + (createUnitId ++).toString();
	}
	public String getDataUnitId() {
		return "dau" + (dataUnitId ++).toString();
	}
	public String getDeleteUnitId() {
		return "dlu" + (deleteUnitId ++).toString();
	}
	public String getEntryUnitId() {
		return "enu" + (entryUnitId ++).toString();
	}
	public String getIndexUnitId() {
		return "inu" + (indexUnitId ++).toString();
	}
	public String getModifyUnitId() {
		return "mfu" + (modifyUnitId ++).toString();
	}
	public String getMultiChoiceIndexUnitId() {
		return "mciu" + (multiChoiceIndexUnitId ++).toString();
	}
	public String getMultiEntryUnitId() {
		return "meu" + (multiEntryUnitId ++).toString();
	}
	public String getSelectorUnitId() {
		return "seu" + (selectorUnitId ++).toString();
	}
	public String getParameterCouplingId() {
		return "par" + (parameterCouplingId ++).toString();
	}
	public String getSelectorId(){
	  return "su" + (selectorId ++).toString();
	}
	public String getKeyConditionId(){
	  return "kcond" + (keyConditionId ++).toString();
	}
	public String getKeyConditionName(){
	  return "KeyCondition" + (keyConditionName ++).toString();
	}
	public String getNormalFieldId(){
	  return "fld" + (normalFieldId ++).toString();
	}
	public String getSelectionFieldId(){
	  return "sfld" + (selectionFieldId ++).toString();
  }
	
}
