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
import org.webspeclanguage.mockupdd.sui.model.tags.TagApplication;
import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathNode;
import org.webspeclanguage.mockupdd.sui.model.tags.content.DataPathTagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.SimpleTagParameterValueContent;
import org.webspeclanguage.mockupdd.sui.model.tags.content.TagParameterValueContentVisitor;
import org.webspeclanguage.mockupdd.utils.SuiUtil;

/**
 * @author José Matías Rivero
 */
public class ClassAndAttributeSpecInferer implements SuiModelProcessor {

  private ClassSpecInfererVisitor classSpecVisitor = new ClassSpecInfererVisitor();
  private AssociationSpecVisitor associationSpecVisitor = new AssociationSpecVisitor();
  private AttributeSpecVisitor attributeSpecVisitor = new AttributeSpecVisitor();

  @Override
  public void process(SuiSpecsInferenceState specs) {
    this.classSpecVisitor.setState(specs);
    this.associationSpecVisitor.setState(specs);
    this.attributeSpecVisitor.setState(specs);
    Collection<TagApplication> dataTags = 
            specs.getTagIndexer().getTagApplicationsFor(SuiDefaultConfig.getInstance().getTag("Data", "Data"));
    // TODO validate that all data tags applied to CW must finish in a class name and not in an accessor
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
    for (TagApplication ta : dataTags) {
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

  private class ClassSpecInfererVisitor implements TagParameterValueContentVisitor<ClassSpec> {

    private SuiSpecsInferenceState state;

    @Override
    public ClassSpec visitSimpleTagParameterValueContent(SimpleTagParameterValueContent valueContent) {
      return createOrGetClassSpec(this.state, valueContent.getTextualRepresentation());
    }

    @Override
    public ClassSpec visitDataPathTagParameterValueContent(DataPathTagParameterValueContent valueContent) {
      return this.visitDataPathNode(valueContent.getRootNode());
    }

    @Override
    public ClassSpec visitDataPathNode(DataPathNode dataPathNode) {
      if (dataPathNode.getNextNode() != null) {
        dataPathNode.getNextNode().accept(this);
      }
      return createOrGetClassSpec(this.state, dataPathNode.getClassName()); 
    }

    void setState(SuiSpecsInferenceState state) {
      this.state = state;
    }

  }
  
  private class AssociationSpecVisitor implements TagParameterValueContentVisitor<ClassSpec> {

    private SuiSpecsInferenceState state;

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

    void setState(SuiSpecsInferenceState state) {
      this.state = state;
    }

  }
  
  private class AttributeSpecVisitor implements TagParameterValueContentVisitor<AttributeSpec> {

    private SuiSpecsInferenceState state;

    @Override
    public AttributeSpec visitSimpleTagParameterValueContent(SimpleTagParameterValueContent valueContent) {
      return null;
    }

    @Override
    public AttributeSpec visitDataPathTagParameterValueContent(DataPathTagParameterValueContent valueContent) {
      return this.visitDataPathNode(valueContent.getRootNode());
    }

    @Override
    public AttributeSpec visitDataPathNode(DataPathNode dataPathNode) {
      if (dataPathNode.getNextNode() != null) {
        return dataPathNode.getNextNode().accept(this);
      } else {
        ClassSpec classSpec = createOrGetClassSpec(this.state, dataPathNode.getClassName());
        AttributeSpec attributeSpec = SuiSpecsConfig.getInstance().getDataSpecFactory().createAttributeSpec(dataPathNode.getAccessorName(), AttributeTypeSpec.STRING);
        classSpec.addAttribute(attributeSpec);
        return attributeSpec;
      } 
    }

    void setState(SuiSpecsInferenceState state) {
      this.state = state;
    }

  }

}
