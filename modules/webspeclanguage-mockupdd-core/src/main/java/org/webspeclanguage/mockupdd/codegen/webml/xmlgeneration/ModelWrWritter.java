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


/**
 * @author Franco Giacosa
 */
public class ModelWrWritter {
  private CreatorWrapper file;
  
  public ModelWrWritter(String rootPath){
    this.file = new CreatorWrapper(rootPath, "Model.wr");
  }
  
   
  public void generateModelWrFile(){
    this.getFile().appendString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    this.getFile().newLine();
    this.getFile().appendString("<?webml version=\"4.1.2\"?>");
    this.getFile().newLine();
    this.getFile().appendString("<WebProject xmlns:gr=\"");
    this.getFile().appendString("http://www.webratio.com/2006/WebML/Graph");
    this.getFile().appendString("\" ");
    this.getFile().appendString("outputPath=\"");
    this.getFile().appendString("${webapps_loc}/${project_name}");
    this.getFile().appendString("\" ");
    this.getFile().appendString("gr:showUnitContent=\"");
    this.getFile().appendString("true");
    this.getFile().appendString("\" ");
    this.getFile().appendString("gr:showTooltip=\"");
    this.getFile().appendString("true");
    this.getFile().appendString("\" ");
    this.getFile().appendString("httpPort=\"");
    this.getFile().appendString("8080");
    this.getFile().appendString("\" ");
    this.getFile().appendString("httpsPort=\"");
    this.getFile().appendString("8443");
    this.getFile().appendString("\" ");
    this.getFile().appendString("jobGroupName=\"");
    this.getFile().appendString("WEBRATIO");
    this.getFile().appendString("\" ");
    this.getFile().appendString("sample=\"");
    this.getFile().appendString("true");
    this.getFile().appendString("\">");
    this.getFile().newLine();
    this.getFile().appendString("<ServiceDataProviders/>");
    this.getFile().newLine();
    this.getFile().appendString("</WebProject>");
    this.getFile().newLine();
    this.getFile().closeFile();
  }
    
  private CreatorWrapper getFile() {
    return file;
  }
}
