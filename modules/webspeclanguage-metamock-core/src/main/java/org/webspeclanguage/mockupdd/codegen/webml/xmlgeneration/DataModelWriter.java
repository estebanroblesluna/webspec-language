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

import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Attribute;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModel;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.DataModelConcreteVisitor;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Entity;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.Relationship;
import org.webspeclanguage.mockupdd.codegen.webml.datamodel.RelationshipRole;


/**
 * @author Franco Giacosa
 */
public class DataModelWriter extends DataModelConcreteVisitor {

  private String rootPath;
  private FileCreatorWrapper file;
  
  public DataModelWriter(String rootPath){
    this.rootPath = rootPath;
    String folderPath = this.getRootPath() + "DataModel" + "/";
    File newFolder= new File(folderPath); 
    newFolder.mkdir();
    this.file = new FileCreatorWrapper(folderPath, "Properties.wr");
  }
  public String getRootPath() {
    return rootPath;
  }
  public FileCreatorWrapper getFile(){
    return file;
  }
  public void visit(DataModel dataModel) {
    this.beginDataModel(dataModel);
    super.visit(dataModel);
    this.endDataModel(dataModel);
  }
  public void visit(Entity entity) {
    this.beginEntity(entity);
    super.visit(entity);
    this.endEntity(entity);
  }
  public void visit(Attribute attribute) {
    this.beginAttribute(attribute);
    super.visit(attribute);
    this.endAttribute(attribute);
  }
  public void visit(Relationship relationship) {
    this.beginRelationship(relationship);
    super.visit(relationship);
    this.endRelationship(relationship);
  }
  public void visit(RelationshipRole relationshipRole) {
    this.beginRelationshipRole(relationshipRole);
    super.visit(relationshipRole);
    this.endRelationshipRole(relationshipRole);
  }
  public void beginRelationshipRole(RelationshipRole relationshipRole){
    this.getFile().appendString("<RelationshipRole id=\"");
    this.getFile().appendString(relationshipRole.getId());
    this.getFile().appendString("\" name=\"");
    this.getFile().appendString(relationshipRole.getName());
    this.getFile().appendString("\" maxCard=\"");
    this.getFile().appendString(relationshipRole.getMaxCard());
    this.getFile().appendString("\"/>");
    this.getFile().newLine();
  }
  public void endRelationshipRole(RelationshipRole relationshipRole){

  }
  public void beginRelationship(Relationship relationship){
    this.getFile().appendString("<Relationship id=\"");
    this.getFile().appendString(relationship.getId());
    this.getFile().appendString("\" name=\"");
    this.getFile().appendString(relationship.getName());
    this.getFile().appendString("\" sourceEntity=\"");
    this.getFile().appendString(relationship.getSourceEntity().getId());
    this.getFile().appendString("\" targetEntity=\"");
    this.getFile().appendString(relationship.getTargetEntity().getId());
    this.getFile().appendString("\">");
    this.getFile().newLine();
  }
  public void endRelationship(Relationship relationship){
    this.getFile().appendString("</Relationship>");
    this.getFile().newLine();
  }
  public void beginAttribute(Attribute attribute){
    this.getFile().appendString("<Attribute name=\"");
    this.getFile().appendString(attribute.getName());
    this.getFile().appendString("\" id=\"");
    this.getFile().appendString(attribute.getId());
    this.getFile().appendString("\" type=\"");
    this.getFile().appendString(attribute.getAttributeType().getTypeName());
    this.getFile().appendString("\" key=\"");
    this.getFile().appendString(attribute.getKey().toString());
    this.getFile().appendString("\"/>");
    this.getFile().newLine();
  }
  public void endAttribute(Attribute attribute){
    //Not necesary to finish it
  }
  
  public void beginEntity(Entity entity){
    this.getFile().appendString("<Entity id=\"");
    this.getFile().appendString(entity.getId());
    this.getFile().appendString("\" name=\"");
    this.getFile().appendString(entity.getName());
    this.getFile().appendString("\" duration=\"");
    this.getFile().appendString(entity.getDuration());
    this.getFile().appendString("\" attributeOrder=\"\"");
    this.getFile().appendString(">");
    this.getFile().newLine();
  }
  public void endEntity(Entity entity){
    this.getFile().appendString("</Entity>");
    this.getFile().newLine();
  }
  public void beginDataModel(DataModel dataModel){
    this.getFile().appendString("<DataModel>");
    this.getFile().newLine();
    
  }
  public void endDataModel(DataModel dataModel){
    this.getFile().appendString("</DataModel>");
    this.getFile().closeFile();
  }
}
