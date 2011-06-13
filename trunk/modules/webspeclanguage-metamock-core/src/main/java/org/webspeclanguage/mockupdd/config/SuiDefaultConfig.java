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
package org.webspeclanguage.mockupdd.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.webspeclanguage.mockupdd.sui.model.Button;
import org.webspeclanguage.mockupdd.sui.model.Link;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.impl.SuiFactoryImpl;
import org.webspeclanguage.mockupdd.sui.model.tags.TagSet;
import org.webspeclanguage.mockupdd.translator.annotation.JsonAnnotationParser;
import org.webspeclanguage.mockupdd.translator.annotation.WidgetAnnotationParser;

/**
 * @author Jose Matias Rivero
 */
public class SuiDefaultConfig {

  private static SuiDefaultConfig instance;
  public static SuiDefaultConfig getInstance() {
    if (instance == null) {
      instance = new SuiDefaultConfig();
    }
    return instance;
  }

  private SuiFactory factory;
  private WidgetAnnotationParser annotationParser;
  private Map<String, TagSet> tagSetsByName;
  
  private SuiDefaultConfig() {
    this.setFactory(new SuiFactoryImpl());
    this.setAnnotationParser(new JsonAnnotationParser(this.getFactory()));
    this.initializeTagSets();
  }

  @SuppressWarnings("unchecked")
  private void initializeTagSets() {
    this.tagSetsByName = new HashMap<String, TagSet>();
    this.tagSetsByName.put("Nav", 
      this.getFactory().createTagSet("Nav",
              this.getFactory().createTag("Link", 
                      Arrays.asList(this.getFactory().createTagParameter("nodeId")),
                      Button.class, Link.class),
              this.getFactory().createTag("Node", 
                      Arrays.asList(this.getFactory().createTagParameter("nodeId")),
                      Page.class) 
      ));
    this.tagSetsByName.put("Data", 
      this.getFactory().createTagSet("Data",
              this.getFactory().createTag("Data", 
                      Arrays.asList(this.getFactory().createTagParameter("typeId")),
                      Widget.class) 
    ));
  }

  private void setFactory(SuiFactory factory) {
    this.factory = factory;
  }

  public SuiFactory getFactory() {
    return factory;
  }

  private void setAnnotationParser(WidgetAnnotationParser annotationParser) {
    this.annotationParser = annotationParser;
  }

  public WidgetAnnotationParser getAnnotationParser() {
    return annotationParser;
  }
  
  public TagSet getTagSetByName(String tagSetName) {
    return this.tagSetsByName.get(tagSetName);
  }

}
