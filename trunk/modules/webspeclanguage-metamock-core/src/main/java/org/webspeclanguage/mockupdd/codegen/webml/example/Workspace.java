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
package org.webspeclanguage.mockupdd.codegen.webml.example;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.links.*;

/**
 * @author Franco Giacosa
 */
public class Workspace {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebModelFactory factory = new WebModelFactory();
		EntryUnit entryUnit1 = factory.createEntryUnit("entryunit1", null);
		EntryUnit entryUnit2 = factory.createEntryUnit("entryunit2", null);
		Page page1 = factory.createPage("page1", true, true);
    Page page4 = factory.createPage("page4", true, true);

		System.out.println(page1.getClass().getSimpleName());
		if(page1.getClass().getSimpleName().equalsIgnoreCase("page")){
		    System.out.print("hola");
		}
		
		NormalLink link = factory.createNormalLink("link", true, page1, entryUnit1);
		System.out.println(link.toString());
    System.out.println(link.getFrom().getId());
    System.out.println(link.getTo().getId());
	 		
	}

}
