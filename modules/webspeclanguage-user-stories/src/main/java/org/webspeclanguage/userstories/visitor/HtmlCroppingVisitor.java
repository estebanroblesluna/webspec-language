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
package org.webspeclanguage.userstories.visitor;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.NamedObject;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.OperationReference;
import org.webspeclanguage.api.PathItemVisitor;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.impl.HtmlStrategyConstants;

/**
 * @author cristian.cianfagna
 */
public class HtmlCroppingVisitor implements PathItemVisitor,
		HtmlStrategyConstants {

	private String croppingServiceURL;
	private String diagramId;
	private Map<String, CroppingInfo> croppingMap;

	public HtmlCroppingVisitor(String croppingServiceURL, String diagramId,
			Map<String, CroppingInfo> croppingMap) {
		this.setCroppingMap(croppingMap);
		this.setCroppingServiceURL(croppingServiceURL);
		this.setDiagramId(diagramId);
	}

	public Object visitInteraction(Interaction interaction) {
		return this.getURL(interaction);
	}

	public Object visitNavigation(Navigation navigation) {
		return this.getURL(navigation);
	}

	public Object visitOperationReference(OperationReference operationReference) {
		return "";
	}

	public Object visitRichBehavior(RichBehavior richBehavior) {
		return this.getURL(richBehavior);
	}

	private String getURL(NamedObject namedObject) {
		//THIS IS AN EXAMPLE http://localhost:8080/service/projects/diagram/{diagramId}/image/{x}/{y}/{width}/{height}
		CroppingInfo croppingInfo = this.getCroppingMap().get(namedObject.getName());
		String url = StringUtils.replace(this.getCroppingServiceURL(), DIAGRAM_PLACEHOLDER, this.getDiagramId());
		url = StringUtils.replace(url, X_PLACEHOLDER, String.valueOf(croppingInfo.getX()));
		url = StringUtils.replace(url, Y_PLACEHOLDER, String.valueOf(croppingInfo.getY()));
		url = StringUtils.replace(url, WIDTH_PLACEHOLDER, String.valueOf(croppingInfo.getWidth()));
		url = StringUtils.replace(url, HEIGHT_PLACEHOLDER, String.valueOf(croppingInfo.getHeight()));
		return url;
	}

	public String getCroppingServiceURL() {
		return croppingServiceURL;
	}

	public void setCroppingServiceURL(String croppingServiceURL) {
		this.croppingServiceURL = croppingServiceURL;
	}

	public String getDiagramId() {
		return diagramId;
	}

	public void setDiagramId(String diagramId) {
		this.diagramId = diagramId;
	}

	public Map<String, CroppingInfo> getCroppingMap() {
		return croppingMap;
	}

	public void setCroppingMap(Map<String, CroppingInfo> croppingMap) {
		this.croppingMap = croppingMap;
	}

}
