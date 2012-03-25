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
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Link;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SelectionWidget;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.TriggerWidget;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.impl.SuiFactoryImpl;
import org.webspeclanguage.mockupdd.sui.model.impl.tags.content.TagParameterValueContentParserImpl;
import org.webspeclanguage.mockupdd.sui.model.layout.LayoutFactory;
import org.webspeclanguage.mockupdd.sui.model.tags.Tag;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplicationException;
import org.webspeclanguage.mockupdd.sui.model.tags.TagParameter;
import org.webspeclanguage.mockupdd.sui.model.tags.TagSet;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContentParser;

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
  
  private Map<String, TagSet> tagSetsByName;

  private LayoutFactory defaultLayoutFactory;
  private TagParameterValueContentParser tagParameterValueContentParser;
  
  private SuiDefaultConfig() {
    this.setFactory(new SuiFactoryImpl());

    this.setTagParameterValueContentParser(new TagParameterValueContentParserImpl(this.getFactory()));
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
                      Page.class),
              this.getFactory().createTag("Select", 
                      Arrays.asList(new TagParameter[]{}),
                      SelectionWidget.class), 
              this.getFactory().createTag("Transfer", 
                      Arrays.asList(
                              this.getFactory().createTagParameter("sourceWidgetId"),
                              this.getFactory().createTagParameter("destWidgetId")),
                      TriggerWidget.class),
              this.getFactory().createTag("Action", 
                      Arrays.asList(this.getFactory().createTagParameter("actionDescription")),
                      TriggerWidget.class)
      ));
    this.tagSetsByName.put("Data", 
      this.getFactory().createTagSet("Data",
              this.getFactory().createTag("Data", 
                      Arrays.asList(this.getFactory().createTagParameter("typeId")),
                      Widget.class),
              this.getFactory().createTag("Save", 
                      Arrays.asList(this.getFactory().createTagParameter("types")),
                      TriggerWidget.class),
              this.getFactory().createTag("Delete", 
                      Arrays.asList(this.getFactory().createTagParameter("types")),
                      TriggerWidget.class),
              this.getFactory().createTag("Associate", 
                      Arrays.asList(this.getFactory().createTagParameter("type1"), this.getFactory().createTagParameter("type2")),
                      TriggerWidget.class),
              this.getFactory().createTag("Dissociate", 
                      Arrays.asList(this.getFactory().createTagParameter("type1"), this.getFactory().createTagParameter("type2")),
                      TriggerWidget.class),
              this.getFactory().createTag("Query", 
                      Arrays.asList(this.getFactory().createTagParameter("type"), this.getFactory().createTagParameter("queryDescription")),
                      CompositeWidget.class)
    ));
  }

  private void setFactory(SuiFactory factory) {
    this.factory = factory;
  }

  public SuiFactory getFactory() {
    return factory;
  }

  public TagSet getTagSetByName(String tagSetName) {
    return this.tagSetsByName.get(tagSetName);
  }
  
  public Tag getTag(String tagSetName, String tagName) {
    TagSet tagSet = this.getTagSetByName(tagSetName);
    if (tagSet == null) {
      return null;
    }
    return tagSet.getTagByName(tagName);
  }
  
  public TagApplication createTagApplication(Widget w, String tagSetName, String tagName, String... values) throws TagApplicationException {
    Tag t = this.getTag(tagSetName, tagName);
    return t.applyOverWithValues(w, Arrays.asList(values));
  }
  
  public TagParameterValueContentParser getTagParameterValueContentParser() {
    return this.tagParameterValueContentParser;
  }

  private void setTagParameterValueContentParser(TagParameterValueContentParser tagParameterValueContentParser) {
    this.tagParameterValueContentParser = tagParameterValueContentParser;
  }

}
