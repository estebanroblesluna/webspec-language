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

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.patternconfiguration.*;
import java.util.*;

/**
 * @author Franco Giacosa
 */
public class Locale implements WebModelElement{

	private String id = "lcl1";
	private String country = "US";
	private String language="en";
	private HashMap<String,PatternConfiguration> patternsConfiguration = new HashMap<String,PatternConfiguration>();
	
	public Locale (){
		this.patternsConfiguration.put("boolean", new PatternConfigurationBoolean());
		this.patternsConfiguration.put("decimal", new PatternConfigurationDecimal());
		this.patternsConfiguration.put("date", new PatternConfigurationDate());
		this.patternsConfiguration.put("float", new PatternConfigurationFloat());
		this.patternsConfiguration.put("integer", new PatternConfigurationInteger());
		this.patternsConfiguration.put("time", new PatternConfigurationTime());
		this.patternsConfiguration.put("timestamp", new PatternConfigurationTimeStamp());
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public HashMap<String, PatternConfiguration> getPatternsConfiguration() {
		return patternsConfiguration;
	}
	public void setPatternsConfiguration(HashMap<String, PatternConfiguration> patternsConfiguration) {
		this.patternsConfiguration = patternsConfiguration;
	}
	public void accept(WebModelVisitor visitor) {
		visitor.visit(this);
	}
	
}
