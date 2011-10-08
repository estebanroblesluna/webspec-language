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
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModel;
import org.webspeclanguage.mockupdd.codegen.webml.webmodel.WebModelVisitor;


/**
 * @author Franco Giacosa
 */
public class MainXmlGenerator {
  private String projectName;
  private String versionFolder;
  private Logger logger = Logger.getLogger(CreatorWrapper.class);

  public void mapModels(DataModel dataModel,WebModel webModel){
    this.createVersionFolder();
    
    DotProjectWritter dotProject = new DotProjectWritter(this.getVersionFolder());
    dotProject.generateDotProjectFile(this.getProjectName());
    
    ModelWrWritter modelWr = new ModelWrWritter(this.getVersionFolder());
    modelWr.generateModelWrFile();
    
    String modelsFolderPath = this.getVersionFolder() + "Model/";
    File newFolder= new File(modelsFolderPath); 
    newFolder.mkdir();
    
    DataModelVisitor visitor = new DataModelWriter(modelsFolderPath);
    dataModel.accept(visitor);
            
    WebModelVisitor visitor1 = new WebModelWriter(modelsFolderPath);
    webModel.accept(visitor1);


  }
  public void createVersionFolder(){
    String xmlVersionFile = "src/main/java/org/webspeclanguage/mockupdd/codegen/webml/xmlgeneration/webratioprojects/WebRatioXMLProjectVersion.xml";    
    String versionNumber = this.getVersionNumber(xmlVersionFile);
    
    
    String folderPath = "src/main/java/org/webspeclanguage/mockupdd/codegen/webml/xmlgeneration/webratioprojects/XMLWebRatioProject" + versionNumber + "/";
    File newFolder= new File(folderPath); 
    newFolder.mkdir();
    
    this.setVersionFolder(folderPath);
    this.setProjectName("XMLWebRatioProject" + versionNumber);
    
  }

  
  public String getVersionNumber(String xmlVersionFile){
    
    try
    {  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ( );
       Document documento = null;
       DocumentBuilder builder = factory.newDocumentBuilder();
       documento = builder.parse( new File(xmlVersionFile) );
       Node nodoRaiz = documento.getFirstChild();
       NamedNodeMap atributos = nodoRaiz.getAttributes(  );
       Node unAtributo = atributos.getNamedItem( "version" );
       String versionNumber = unAtributo.getNodeValue();
             
       Integer intVersion = Integer.parseInt(versionNumber) + 1;
       unAtributo.setNodeValue(intVersion.toString());
       TransformerFactory transformerFactory = TransformerFactory.newInstance();
        
       Transformer transformer = transformerFactory.newTransformer();
        
       DOMSource source = new DOMSource(documento);
        
       StreamResult result =  new StreamResult(new File(xmlVersionFile));
       transformer.transform(source, result);
       
       return versionNumber;
    }
    catch (Exception spe)
    {
      this.getLogger().info(spe.toString());
       return "unknow Version";
    }
        
  }
  
  private String getProjectName() {
    return projectName;
  }
  
  private void setProjectName(String projectName) {
    this.projectName = projectName;
  }
  
  private String getVersionFolder() {
    return versionFolder;
  }
  
  private void setVersionFolder(String versionFolder) {
    this.versionFolder = versionFolder;
  }
  
  public Logger getLogger() {
    return logger;
  }
  
  public void setLogger(Logger logger) {
    this.logger = logger;
  }
}
