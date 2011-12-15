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

import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;


/**
 * @author Franco Giacosa
 */
public class CreatorWrapper {
  
  private FileWriter outputFile;
  private String folderPath;
  private Logger logger = Logger.getLogger(CreatorWrapper.class);

  public CreatorWrapper(String folderPath, String fileName){
    this.folderPath = folderPath;
    
    this.getLogger().info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    this.getLogger().info("FILE " + fileName + " CREATED AT " + this.folderPath + fileName);
    this.getLogger().info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");

    try {
      this.outputFile = new FileWriter(this.folderPath + fileName);
      
    } catch (IOException e) {
      this.getLogger().info("error creating the file");
      this.getLogger().info(e);
    }
  }
  public void newLine(){
    try {
      System.out.println("");
      this.getOutputFile().write("\r\n");
    } catch (IOException e) {
      this.getLogger().info("error newLine method");
      this.getLogger().info(e);
    }
  }
  public void appendString(String appendString){
    try {
      System.out.print(appendString);
      this.getOutputFile().write(appendString);
    } catch (IOException e) {
      this.getLogger().info("error appendString");
      this.getLogger().info(e);
    }
  }
  public void closeFile(){
    try {
      this.getLogger().info("-----------------------------------------------------");
      this.getLogger().info("FILE CLOSE");
      this.getLogger().info("-----------------------------------------------------");
      this.getOutputFile().close();
    } catch (IOException e) {
      this.getLogger().info("error closeFile");
      this.getLogger().info(e);
    }
  }
  
  public FileWriter getOutputFile() {
    return outputFile;
  }
  
  public String getFolderPath() {
    return folderPath;
  }
  
  public Logger getLogger() {
    return logger;
  }
  
  public void setLogger(Logger logger) {
    this.logger = logger;
  }

}
