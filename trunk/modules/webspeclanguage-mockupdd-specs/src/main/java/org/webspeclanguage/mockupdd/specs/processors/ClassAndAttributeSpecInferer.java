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
package org.webspeclanguage.mockupdd.specs.processors;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.webspeclanguage.mockupdd.config.SuiDefaultConfig;
import org.webspeclanguage.mockupdd.specs.SuiSpecsConfig;
import org.webspeclanguage.mockupdd.specs.SuiSpecsInferenceState;
import org.webspeclanguage.mockupdd.specs.data.AttributeSpec;
import org.webspeclanguage.mockupdd.specs.data.AttributeTypeSpec;
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.specs.data.MaximumCardinality;
import org.webspeclanguage.mockupdd.specs.hypertext.AttributeMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.ClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.MappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.PanelClassMappingSpec;
import org.webspeclanguage.mockupdd.specs.hypertext.RepetitionClassMappingSpec;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SimpleWidget;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;
import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathNode;
import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathTagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.SimpleTagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContentVisitor;
import org.webspeclanguage.mockupdd.utils.DefaultSuiVisitor;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author Jose Matias Rivero
 */
public class ClassAndAttributeSpecInferer extends SuiModelProcessor {

  private ClassSpecInfererVisitor classSpecVisitor;
  private AssociationSpecVisitor associationSpecVisitor;
  private AttributeSpecVisitor attributeSpecVisitor;

  @Override
  public void process(SuiSpecsInferenceState specs) {
    this.classSpecVisitor = new ClassSpecInfererVisitor(specs);
    this.associationSpecVisitor = new AssociationSpecVisitor(specs);
    this.attributeSpecVisitor = new AttributeSpecVisitor(specs);
    Collection<TagApplication> dataTags = specs.getTagIndexer().getTagApplicationsFor(
            SuiDefaultConfig.getInstance().getTag("Data", "Data"));
    // TODO validate that all data tags applied to CW must finish in a class
    // name and not in an accessor
    // TODO validate that all data tags applied to SW must finish in an accessor
    inferClassSpecs(dataTags);
    inferAttributes(dataTags);
    inferAssociations(dataTags);
  }

  private void inferAttributes(Collection<TagApplication> dataTags) {
    ArrayList<TagApplication> swDataTags = new ArrayList<TagApplication>(dataTags);
    CollectionUtils.filter(swDataTags, new Predicate<TagApplication>() {

      @Override
      public boolean evaluate(TagApplication ta) {
        return !SuiUtil.isComposite(ta.getWidget());
      }

    });
    for (TagApplication ta : swDataTags) {
      this.attributeSpecVisitor.setCurrentTagApplication(ta);
      ta.getParameterValues().iterator().next().getValue().accept(this.attributeSpecVisitor);
    }

  }

  private void inferAssociations(Collection<TagApplication> dataTags) {
    // TODO manage repeated accessors and inconsistencies
    for (TagApplication ta : dataTags) {
      ta.getParameterValues().iterator().next().getValue().accept(this.associationSpecVisitor);
    }
  }

  private void inferClassSpecs(Collection<TagApplication> dataTags) {
    ArrayList<TagApplication> cwDataTags = new ArrayList<TagApplication>(dataTags);
    CollectionUtils.filter(cwDataTags, new Predicate<TagApplication>() {

      @Override
      public boolean evaluate(TagApplication ta) {
        return SuiUtil.isComposite(ta.getWidget());
      }

    });
    for (TagApplication ta : cwDataTags) {
      this.classSpecVisitor.setCurrentTagApplication(ta);
      ta.getParameterValues().iterator().next().getValue().accept(this.classSpecVisitor);
    }
  }

  private ClassSpec createOrGetClassSpec(SuiSpecsInferenceState state, String className) {
    ClassSpec spec = state.getClassSpecByName(className);
    if (spec == null) {
      spec = state.addClassSpec(SuiSpecsConfig.getInstance().getDataSpecFactory().createClassSpec(className));
    }
    return spec;
  }
  
  private <W extends Widget> void setDataSourceIfPresent(DataPathTagParameterValueContent valueContent, MappingSpec<W> mappingSpec, TagApplication ta, SuiSpecsInferenceState s) {
    // TODO Validate data source class equality
    if (valueContent.getWidgetId() != null) {
      Widget w = (Widget) ta.getWidget().getPage()
              .getWidgetById(valueContent.getWidgetId());
      if (w == null) {
        s.addError(new SuiModelProcessingError(ClassAndAttributeSpecInferer.this, 
                ta.getWidget(), "Widget \"" + valueContent.getWidgetId() + "\" not found"));
      } else {
        if (!SuiUtil.isComposite(w)) {
          s.addError(new SuiModelProcessingError(ClassAndAttributeSpecInferer.this, 
                  w, "Widget \"" + valueContent.getWidgetId() + "\" must be composite to be referenced as a data source from another"));
        } else {
          mappingSpec.setDataSource((CompositeWidget) w);
        }
      }
    }
  }

  private class ClassSpecInfererVisitor implements TagParameterValueContentVisitor<ClassSpec> {

    private SuiSpecsInferenceState state;
    private TagApplication currentTagApplication;
    private CreateSpecVisitor createSpecVisitor = new CreateSpecVisitor();

    public ClassSpecInfererVisitor(SuiSpecsInferenceState specs) {
      this.state = specs;
    }

    public void setCurrentTagApplication(TagApplication ta) {
      this.currentTagApplication = ta;
    }

    @Override
    public ClassSpec visitSimpleTagParameterValueContent(SimpleTagParameterValueContent valueContent) {
      ClassSpec cs = createOrGetClassSpec(this.state, valueContent.getTextualRepresentation());
      if (SuiUtil.hasInputWidgets((CompositeWidget) this.currentTagApplication.getWidget()) &&
              SuiUtil.widgetIsKindOf(this.currentTagApplication.getWidget(), Panel.class)) {
        this.state.addInputPanelSpec(SuiSpecsConfig.getInstance().getHypertextSpecFactory().createInputPanelSpec(
                (Panel) this.currentTagApplication.getWidget(), cs));
      } else {
        createSpecVisitor.setCurrentClassSpec(cs);
        createSpecVisitor.setState(this.state);
        this.currentTagApplication.getWidget().accept(createSpecVisitor);
      }
      
      return cs;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public ClassSpec visitDataPathTagParameterValueContent(DataPathTagParameterValueContent valueContent) {
      ClassSpec cs = this.visitDataPathNode(valueContent.getRootNode());
      createSpecVisitor.setCurrentClassSpec(cs);
      createSpecVisitor.setState(this.state);
      ClassMappingSpec classMappingSpec = this.currentTagApplication.getWidget().accept(createSpecVisitor);
      this.currentTagApplication.getWidget().accept(createSpecVisitor);
      setDataSourceIfPresent(valueContent, classMappingSpec, this.currentTagApplication, this.state);
      return cs;
    }
    
    @Override
    public ClassSpec visitDataPathNode(DataPathNode dataPathNode) {
      ClassSpec cs = createOrGetClassSpec(this.state, dataPathNode.getClassName());
      if (dataPathNode.getNextNode() != null) {
        return dataPathNode.getNextNode().accept(this);
      }
      return cs;
    }

  }

  private class AssociationSpecVisitor implements TagParameterValueContentVisitor<ClassSpec> {

    private SuiSpecsInferenceState state;

    public AssociationSpecVisitor(SuiSpecsInferenceState specs) {
      this.state = specs;
    }

    @Override
    public ClassSpec visitSimpleTagParameterValueContent(SimpleTagParameterValueContent valueContent) {
      return null;
    }

    @Override
    public ClassSpec visitDataPathTagParameterValueContent(DataPathTagParameterValueContent valueContent) {
      return this.visitDataPathNode(valueContent.getRootNode());
    }

    @Override
    public ClassSpec visitDataPathNode(DataPathNode dataPathNode) {
      if (dataPathNode.getNextNode() == null) {
        return null;
      }
      dataPathNode.getNextNode().accept(this);
      ClassSpec classSpec = createOrGetClassSpec(this.state, dataPathNode.getClassName());
      ClassSpec destClassSpec = createOrGetClassSpec(this.state, dataPathNode.getNextNode().getClassName());
      classSpec.addAssociation(SuiSpecsConfig.getInstance().getDataSpecFactory()
              .createAssociationSpec(destClassSpec, dataPathNode.getAccessorName(), MaximumCardinality.ONE));
      dataPathNode.getNextNode().accept(this);
      return classSpec;
    }

  }

  private class AttributeSpecVisitor implements TagParameterValueContentVisitor<AttributeSpec> {

    private SuiSpecsInferenceState state;
    private TagApplication currentTagApplication;

    public AttributeSpecVisitor(SuiSpecsInferenceState specs) {
      this.state = specs;
    }

    @Override
    public AttributeSpec visitSimpleTagParameterValueContent(SimpleTagParameterValueContent valueContent) {
      this.state.addError(new SuiModelProcessingError(ClassAndAttributeSpecInferer.this, this.currentTagApplication.getWidget(), 
              "Attribute missing in tag application: " + valueContent.getTextualRepresentation() + " over widget" +
              this.currentTagApplication.getWidget().getWidgetId()));
      return null;
    }

    @Override
    public AttributeSpec visitDataPathTagParameterValueContent(DataPathTagParameterValueContent valueContent) {
      AttributeSpec as = this.visitDataPathNode(valueContent.getRootNode());
      AttributeMappingSpec ams = SuiSpecsConfig.getInstance().getHypertextSpecFactory().createAttributeMappingSpec((SimpleWidget) this.currentTagApplication.getWidget(), as);
      this.state.addAttributeMappingSpec(ams);
      setDataSourceIfPresent(valueContent, ams, this.currentTagApplication, this.state);
      return as;
    }

    @Override
    public AttributeSpec visitDataPathNode(DataPathNode dataPathNode) {
      if (dataPathNode.getNextNode() != null) {
        return dataPathNode.getNextNode().accept(this);
      } else {
        ClassSpec classSpec = createOrGetClassSpec(this.state, dataPathNode.getClassName());
        AttributeSpec attributeSpec = SuiSpecsConfig.getInstance().getDataSpecFactory()
                .createAttributeSpec(dataPathNode.getAccessorName(), AttributeTypeSpec.STRING);
        classSpec.addAttribute(attributeSpec);
        return attributeSpec;
      }
    }
    
    public void setCurrentTagApplication(TagApplication currentTagApplication) {
      this.currentTagApplication = currentTagApplication;
    }

  }

  @SuppressWarnings("rawtypes")
  private class CreateSpecVisitor extends DefaultSuiVisitor<ClassMappingSpec> {

    private ClassSpec currentClassSpec;
    private SuiSpecsInferenceState state;
    
    @Override
    public ClassMappingSpec visitPanel(Panel panel) {
      PanelClassMappingSpec cs = SuiSpecsConfig.getInstance().getHypertextSpecFactory().createPanelClassMappingSpec(
              panel, this.currentClassSpec);
      this.state.addPanelClassMappingSpec(cs);
      return cs;
    }

    public void setState(SuiSpecsInferenceState state) {
      this.state = state;
    }

    @Override
    public ClassMappingSpec visitRepetition(Repetition repetition) {
      RepetitionClassMappingSpec cs = SuiSpecsConfig.getInstance().getHypertextSpecFactory().createRepetitionClassMappingSpec(
              repetition, this.currentClassSpec);
      this.state.addRepetitionClassMappingSpec(cs);
      return cs;
    }

    @Override
    public ClassMappingSpec getDefaultValue() {
      return null;
    }

    public void setCurrentClassSpec(ClassSpec currentClassSpec) {
      this.currentClassSpec = currentClassSpec;
    }

  }

}
