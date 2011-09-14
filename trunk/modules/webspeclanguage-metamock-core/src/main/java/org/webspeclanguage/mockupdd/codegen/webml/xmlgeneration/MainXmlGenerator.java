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
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.*;


/**
 * @author Franco Giacosa
 */
public class MainXmlGenerator {

  
  public void generateXMLDataModel(DataModel dataModel){
    String versionProjectFolder = this.createVersionFolder();
    String modelsFolder = versionProjectFolder + "/Model";
    File newModelsFolder = new File(modelsFolder);
    newModelsFolder.mkdir();
    String dataModelFolder = modelsFolder + "/DataModel";
    File newDataModelFolder = new File(dataModelFolder);
    newDataModelFolder.mkdir();
    
  }
  
  public String createVersionFolder(){
    String xmlVersionFile = "src/main/java/org/webspeclanguage/mockupdd/codegen/webml/xmlgeneration/webratioprojects/WebRatioXMLProjectVersion.xml";    
    String versionNumber = this.getVersionNumber(xmlVersionFile);
    
    
    String folderPath = "src/main/java/org/webspeclanguage/mockupdd/codegen/webml/xmlgeneration/webratioprojects/XMLWebRatioProject" + versionNumber;
    File newFolder= new File(folderPath); 
    
    newFolder.mkdir();
    
    return folderPath;
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
        System.out.print(spe.toString());
       return "unknow Version";
    }
        
  }
}
