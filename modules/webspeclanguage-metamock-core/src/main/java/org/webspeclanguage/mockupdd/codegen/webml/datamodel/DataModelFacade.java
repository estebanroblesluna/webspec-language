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

package org.webspeclanguage.mockupdd.codegen.webml.datamodel;

import java.util.*;

/**
 * @author Franco Giacosa
 */
public final class DataModelFacade {
  
  private DataModelFactory dataModelFactory;
  private Map<String,EntityDecorator> entitys;
  private static DataModelFacade dataModelFacade;

  private DataModelFacade() {
    DataModelIds dataModelIds = new DataModelIds();
    dataModelFactory = new DataModelFactoryImpl(dataModelIds);
    entitys = new HashMap<String,EntityDecorator>();
  }
  public static DataModelFacade getDataModelFacade() {
    if(dataModelFacade==null){  
      dataModelFacade = new DataModelFacade();
    }  
    return dataModelFacade;
  }
  
  public DataModelFactory getDataModelFactory() {
    return dataModelFactory;
  }
  
  public Map<String,EntityDecorator> getEntitys(){
    return this.entitys;
  }
  public void addEntity(EntityDecorator entity){
    this.getEntitys().put(entity.getId(), entity);
  }
}
