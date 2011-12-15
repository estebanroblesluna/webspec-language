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
public class DotProjectWritter {

  private CreatorWrapper file;
  
  public DotProjectWritter(String rootPath){
    this.file = new CreatorWrapper(rootPath, ".project");
  }
  public void generateDotProjectFile(String projectName){
    this.getFile().appendString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    this.getFile().newLine();
    this.getFile().appendString("<projectDescription>");
    this.getFile().newLine();
    this.getFile().appendString("<name>" + projectName + "</name>");
    this.getFile().newLine();
    this.getFile().appendString("<comment></comment>");
    this.getFile().newLine();
    this.getFile().appendString("<projects>");
    this.getFile().newLine();
    this.getFile().appendString("</projects>");
    this.getFile().newLine();
    this.getFile().appendString("<buildSpec>");
    this.getFile().newLine();
    this.getFile().appendString("<buildCommand>");
    this.getFile().newLine();
    this.getFile().appendString("<name>com.webratio.ide.core.wrcorebuilder</name>");
    this.getFile().newLine();
    this.getFile().appendString("<arguments>");
    this.getFile().newLine();
    this.getFile().appendString("</arguments>");
    this.getFile().newLine();
    this.getFile().appendString("</buildCommand>");
    this.getFile().newLine();
    this.getFile().appendString("</buildSpec>");
    this.getFile().newLine();
    this.getFile().appendString("<natures>");
    this.getFile().newLine();
    this.getFile().appendString("<nature>com.webratio.ide.wrprojectnature</nature>");
    this.getFile().newLine();
    this.getFile().appendString("</natures>");
    this.getFile().newLine();
    this.getFile().appendString("</projectDescription>");
    this.getFile().newLine();
    this.getFile().closeFile();
  }
  
  private CreatorWrapper getFile() {
    return file;
  }
}
