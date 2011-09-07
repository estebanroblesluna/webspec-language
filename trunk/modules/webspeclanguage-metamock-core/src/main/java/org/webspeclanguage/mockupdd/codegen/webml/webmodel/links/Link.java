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
package org.webspeclanguage.mockupdd.codegen.webml.webmodel.links;

import java.util.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.coupling.*;

/**
 * @author Franco Giacosa
 */
public abstract class Link implements WebModelElement{

	private String id;
	private String name;
	private Boolean automaticCoupling = true;

	private ArrayList<ParameterCoupling> parameterCoupling = new ArrayList<ParameterCoupling>();

	public Link(String id, String name, Boolean automaticCoupling) {
		super();
		this.id = id;
		this.name = name;
		this.automaticCoupling = automaticCoupling;
	}
	public ArrayList<ParameterCoupling> getParameterCoupling() {
		return parameterCoupling;
	}

	public void setParameterCoupling(ArrayList<ParameterCoupling> parameterCoupling) {
		this.parameterCoupling = parameterCoupling;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getAutomaticCoupling() {
		return automaticCoupling;
	}

	public void setAutomaticCoupling(Boolean automaticCoupling) {
		this.automaticCoupling = automaticCoupling;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
