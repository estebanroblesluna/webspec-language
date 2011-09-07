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
package org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling;

/**
 * @author Franco Giacosa
 */
public class ParameterCoupling {

	private String id;
	private String name;
	private Boolean coupling = false;
	private Boolean passing = false;
	private Parameter sourceParameter;
	private Parameter targetParameter;
	
	public ParameterCoupling(String id, String name, Boolean coupling,
			Boolean passing, Parameter sourceParameter,
			Parameter targetParameter) {
		super();
		this.id = id;
		this.name = name;
		this.coupling = coupling;
		this.passing = passing;
		this.sourceParameter = sourceParameter;
		this.targetParameter = targetParameter;
	}
	public Boolean getCoupling() {
		return coupling;
	}
	public void setCoupling(Boolean coupling) {
		this.coupling = coupling;
	}
	public Boolean getPassing() {
		return passing;
	}
	public void setPassing(Boolean passing) {
		this.passing = passing;
	}
	public Parameter getSourceParameter() {
		return sourceParameter;
	}
	public void setSourceParameter(Parameter sourceParameter) {
		this.sourceParameter = sourceParameter;
	}
	public Parameter getTargetParameter() {
		return targetParameter;
	}
	public void setTargetParameter(Parameter targetParameter) {
		this.targetParameter = targetParameter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
