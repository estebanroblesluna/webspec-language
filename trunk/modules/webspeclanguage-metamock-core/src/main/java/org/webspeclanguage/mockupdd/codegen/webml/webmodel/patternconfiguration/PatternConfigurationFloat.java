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
package org.webspeclanguage.mockupdd.codegen.webml.webmodel.patternconfiguration;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;

/**
 * @author Franco Giacosa
 */
public class PatternConfigurationFloat extends PatternConfiguration {

	private String type = "float";
	private Integer maxDecimal = 3;
	private Integer minDecimal = 0;
	private Integer minInteger = 1;
	private Boolean useThousandSeparator = false;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getMaxDecimal() {
		return maxDecimal;
	}
	public void setMaxDecimal(Integer maxDecimal) {
		this.maxDecimal = maxDecimal;
	}
	public Integer getMinDecimal() {
		return minDecimal;
	}
	public void setMinDecimal(Integer minDecimal) {
		this.minDecimal = minDecimal;
	}
	public Integer getMinInteger() {
		return minInteger;
	}
	public void setMinInteger(Integer minInteger) {
		this.minInteger = minInteger;
	}
	public Boolean getUseThousandSeparator() {
		return useThousandSeparator;
	}
	public void setUseThousandSeparator(Boolean useThousandSeparator) {
		this.useThousandSeparator = useThousandSeparator;
	}
	public void accept(WebModelVisitor visitor){
		visitor.visit(this);
	}
}
