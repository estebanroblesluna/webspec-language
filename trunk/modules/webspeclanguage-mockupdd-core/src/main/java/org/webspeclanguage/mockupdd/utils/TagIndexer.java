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
package org.webspeclanguage.mockupdd.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.apache.commons.collections15.MultiMap;
import org.apache.commons.collections15.map.MultiKeyMap;
import org.apache.commons.collections15.multimap.MultiHashMap;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.Tag;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;

/**
 * @author José Matías Rivero
 */
public class TagIndexer {

  private MultiMap<Tag, TagApplication> tagApplicationByTag;
  private MultiKeyMap<Object, Collection<TagApplication>> tagApplicationByTagAndPage;
  
  public TagIndexer(SuiModel model) {
    this.tagApplicationByTag = new MultiHashMap<Tag, TagApplication>();
    this.tagApplicationByTagAndPage = new MultiKeyMap<Object, Collection<TagApplication>>();
    this.processTags(model);
  }

  private void processTags(SuiModel model) {
    Collection<Widget> widgets = new HashSet<Widget>();
    for (Page p : model.getPages()) {
      widgets.addAll(SuiUtil.getAllWidgetsRecursivelyIncludingParent(p));
    }
    for (Widget w : widgets) {
      if (!w.getAppliedTags().isEmpty()) {
        for (TagApplication ta : w.getAppliedTags()) {
          this.indexTagApplication(ta);
        }
      }
    }
  }

  private void indexTagApplication(TagApplication ta) {
    this.tagApplicationByTag.put(ta.getTag(), ta);
    indexTagAndPage(ta);
  }

  private void indexTagAndPage(TagApplication ta) {
    if (!this.tagApplicationByTagAndPage.containsKey(ta.getTag(), ta.getWidget().getPage())) {
      this.tagApplicationByTagAndPage.put(ta.getTag(), ta.getWidget().getPage(), new ArrayList<TagApplication>());
    }
    this.tagApplicationByTagAndPage.get(ta.getTag(), ta.getWidget().getPage()).add(ta);
  }
  
  public Collection<TagApplication> getTagApplicationsFor(Tag tag) {
    Collection<TagApplication> ta = this.tagApplicationByTag.get(tag);
    if (ta == null) {
      return Collections.emptySet();
    }
    return ta;
  }
  
  public Collection<TagApplication> getTagApplicationsFor(Tag tag, Page page) {
    Collection<TagApplication> tagApplications =  this.tagApplicationByTagAndPage.get(tag, page);
    if (tagApplications != null) {
      return tagApplications;
    }
    return Collections.emptyList();
  }
  
}
