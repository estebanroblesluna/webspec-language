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
package org.webspeclanguage.mockupdd.translator.annotation;

import net.sf.json.JSONObject;

import org.webspeclanguage.mockupdd.sui.model.Annotation;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.annotation.LayoutAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.RepetitionAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.SuiAnnotation;
import org.webspeclanguage.mockupdd.sui.model.annotation.TemplateInstantiationAnnotation;
import org.webspeclanguage.mockupdd.utils.DefaultSuiVisitor;
import org.webspeclanguage.mockupdd.utils.Null;

/**
 * {@link WidgetAnnotationParser} implementation for JSON-based annotations
 * 
 * @author Jose Matias Rivero
 */
public class JsonAnnotationParser extends DefaultSuiVisitor<Null> implements WidgetAnnotationParser {

  private SuiFactory factory;
  private Annotation currentAnnotation;
  private SuiAnnotation parsedAnnotation;
  private JSONObject currentJsonObject;

  public JsonAnnotationParser(SuiFactory factory) {
    super();
    this.setFactory(factory);
  }

  private void setFactory(SuiFactory factory) {
    this.factory = factory;
  }

  private SuiFactory getFactory() {
    return factory;
  }

  private void setCurrentAnnotation(Annotation currentAnnotation) {
    this.currentAnnotation = currentAnnotation;
  }

  private Annotation getCurrentAnnotation() {
    return currentAnnotation;
  }

  private void setParsedAnnotation(SuiAnnotation parsedAnnotation) {
    this.parsedAnnotation = parsedAnnotation;
  }

  private SuiAnnotation getParsedAnnotation() {
    return parsedAnnotation;
  }

  private void setCurrentJsonObject(JSONObject currentJsonObject) {
    this.currentJsonObject = currentJsonObject;
  }

  private JSONObject getCurrentJsonObject() {
    return currentJsonObject;
  }

  public SuiAnnotation parseAnnotation(Annotation a) {
    this.setCurrentAnnotation(a);
    JSONObject o = JSONObject.fromObject("{" + a.getContent() + "}");
    this.setCurrentJsonObject(o);
    this.setParsedAnnotation(this.getFactory().createWidgetAnnotation(a.getTargetElement(), this.getProperty(o, "id")));
    a.getTargetElement().accept(this);
    return this.getParsedAnnotation();
  }

  private LayoutAnnotation parseLayoutAnnotation(JSONObject o) {
    if (!o.containsKey("layout")) {
      return null;
    }
    if (o.get("layout").equals("gridbag")) {
      return this.getFactory().createGridBagLayoutAnnotation(this.getCurrentAnnotation().getTargetElement());
    } else if (o.get("layout").equals("vbox")) {
      return this.getFactory().createVerticalBoxLayoutAnnotation(this.getCurrentAnnotation().getTargetElement());
    }
    return null;

  }

  private String getProperty(JSONObject o, String key) {
    if (o.containsKey(key)) {
      return o.get(key).toString();
    }
    return null;
  }

  public Null getDefaultValue() {
    return Null.value();
  }

  public Null visitCompositeWidget(CompositeWidget compositeWidget) {
    this.setParsedAnnotation(this.getFactory().createCompositeWidgetAnnotation(this.getCurrentAnnotation().getTargetElement(),
            this.getProperty(this.getCurrentJsonObject(), "id"), this.parseTemplateInstantiationAnnotation(this.getCurrentJsonObject()),
            this.parseRepetitionAnnotation(this.getCurrentJsonObject()), this.parseLayoutAnnotation(this.getCurrentJsonObject())));
    return Null.value();
  }

  private RepetitionAnnotation parseRepetitionAnnotation(JSONObject o) {
    if (!o.containsKey("repetition")) {
      return null;
    } else if (o.getBoolean("repetition")) {
      return this.getFactory().createRepetitionAnnotation(this.getCurrentAnnotation().getTargetElement());
    } else {
      return null;
    }
  }

  private TemplateInstantiationAnnotation parseTemplateInstantiationAnnotation(JSONObject o) {
    if (o.containsKey("template")) {
      JSONObject p = o.getJSONObject("template");
      return this.getFactory().createTemplateInstantiationAnnotation(this.getCurrentAnnotation().getTargetElement(), this.getProperty(p, "templateId"),
              this.getProperty(p, "containerId"), this.getProperty(p, "placeholderId"));
    }
    return null;
  }

  public Null visitPage(Page page) {
    return this.visitCompositeWidget(page);
  }

  public Null visitPanel(Panel panel) {
    if (!this.tryToParseTemplateDefinition()) {
      this.visitCompositeWidget(panel);
    }
    return Null.value();
  }

  private boolean tryToParseTemplateDefinition() {
    if (this.getCurrentJsonObject().containsKey("templateId")) {
      this.setParsedAnnotation(this.getFactory().createTemplateAnnotation(this.getCurrentAnnotation().getTargetElement(),
              this.getProperty(this.getCurrentJsonObject(), "templateId")));
      return true;
    }
    return false;
  }

}
