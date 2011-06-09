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
package org.webspeclanguage.metamock.translator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.webspeclanguage.metamock.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.metamock.codegen.generator.Mockup;
import org.webspeclanguage.metamock.model.Annotation;
import org.webspeclanguage.metamock.model.CompositeWidget;
import org.webspeclanguage.metamock.model.SuiModelElement;
import org.webspeclanguage.metamock.model.SuiFactory;
import org.webspeclanguage.metamock.model.SuiModel;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.Panel;
import org.webspeclanguage.metamock.model.Repetition;
import org.webspeclanguage.metamock.model.SimpleControl;
import org.webspeclanguage.metamock.model.Widget;
import org.webspeclanguage.metamock.model.annotation.SuiAnnotation;
import org.webspeclanguage.metamock.model.layout.LayoutFactory;
import org.webspeclanguage.metamock.model.layout.impl.ScanBasedGridBagLayoutFactory;
import org.webspeclanguage.metamock.translator.annotation.AnnotationProcessor;
import org.webspeclanguage.metamock.translator.annotation.WidgetAnnotationParser;
import org.webspeclanguage.metamock.utils.SuiUtil;
import org.webspeclanguage.metamock.utils.Rectangle;


/**
 * Facade class for mockup processing.
 * 
 * @author Jose Matias Rivero
 */
public class MockupProcessingEngine<TSource> extends
		SuiTranslator<TSource> {

	private WidgetParser<TSource> parser;
	private WidgetAnnotationParser annotationParser;
	private SuiFactory factory;
	private AnnotationProcessor annotationProcessor;
	private AnnotationProcessor annotationPreprocessor;
	private AnnotationProcessor annotationPostProcessor;
	private RepetitionDetector repetitionDetector;
	private Collection<SuiAnnotation> parsedAnnotations;
	private LayoutFactory defaultLayoutFactory;

	public MockupProcessingEngine(
			WidgetParser<TSource> parser,
			WidgetAnnotationParser annotationParser,
			SuiFactory factory) {
		super();
		this.setParser(parser);
		this.setFactory(factory);
		this.setAnnotationParser(annotationParser);
		this.setAnnotationPreprocessor(
				new AnnotationProcessor(
						new DefaultAnnotationPreprocessor(factory, 
								new RepetitionDetector(factory))));
		this.setAnnotationProcessor(
				new AnnotationProcessor(
						new MainAnnotationInterpreter(factory)));
		this.setAnnotationPostProcessor(
				new AnnotationProcessor(
						new TemplateAnnotationInterpreter()));
		this.setDefaultLayoutFactory(new ScanBasedGridBagLayoutFactory());
		this.setRepetitionDetector(new RepetitionDetector(factory));
	}

	private void setParser(WidgetParser<TSource> parser) {
		this.parser = parser;
	}

	private WidgetParser<TSource> getParser() {
		return parser;
	}

	private void setFactory(SuiFactory factory) {
		this.factory = factory;
	}

	private SuiFactory getFactory() {
		return factory;
	}

	private void setAnnotationProcessor(AnnotationProcessor annotationInterpreter) {
		this.annotationProcessor = annotationInterpreter;
	}

	private AnnotationProcessor getAnnotationProcessor() {
		return annotationProcessor;
	}

	private void setAnnotationParser(WidgetAnnotationParser annotationParser) {
		this.annotationParser = annotationParser;
	}

	private WidgetAnnotationParser getAnnotationParser() {
		return annotationParser;
	}
	
	private void setAnnotationPreprocessor(AnnotationProcessor annotationPreprocessor) {
		this.annotationPreprocessor = annotationPreprocessor;
	}

	private AnnotationProcessor getAnnotationPreprocessor() {
		return annotationPreprocessor;
	}

	private void setAnnotationPostProcessor(AnnotationProcessor annotationPostProcessor) {
		this.annotationPostProcessor = annotationPostProcessor;
	}

	private AnnotationProcessor getAnnotationPostProcessor() {
		return annotationPostProcessor;
	}

	private void setParsedAnnotations(Collection<SuiAnnotation> parsedAnnotations) {
		this.parsedAnnotations = parsedAnnotations;
	}

	private Collection<SuiAnnotation> getParsedAnnotations() {
		return parsedAnnotations;
	}

	private void setDefaultLayoutFactory(LayoutFactory defaultLayoutFactory) {
    this.defaultLayoutFactory = defaultLayoutFactory;
  }
	
  private LayoutFactory getDefaultLayoutFactory() {
    return this.defaultLayoutFactory;
  }

  private void setRepetitionDetector(RepetitionDetector repetitionDetector) {
    this.repetitionDetector = repetitionDetector;
  }

  private RepetitionDetector getRepetitionDetector() {
    return repetitionDetector;
  }

  @Override
	public SuiModel getRawModel(Collection<Mockup<TSource>> mockups) throws SuiTranslationException, MockupSourceParsingException {
		SuiModel model = this.getFactory().createSuiModel();
		for (Mockup<TSource> mockup : mockups) {
  		for (WidgetGroup g : this.getParser().parseControls(mockup.getRepresentation())) {
  			this.preprocessModel(g, model, mockup.getContainerInfo());
  		}
		}
		return model;
	}

	@Override
	public SuiModel applyAnnotationsAndInferLayouts(SuiModel model) {
		this.processAnnotations(model);
		this.createDefaultLayouts(model);
		this.postProcessAnnotations(model);
		return model;
	}

	private void preprocessModel(WidgetGroup group,
			SuiModel model, MockupContainerInfo info)
			throws SuiTranslationException {
		this.detectCollisions(group.getControls(), model);
		this.createPageIfNone(group.getControls(), info);
		this.createControlHierarchy(group.getControls(), info);
		this.assignExcludedControlsToModel(group, model);
		this.addPagesToModel(group.getControls(), model, info);
		this.associateAnnotations(group);
		this.setParsedAnnotations(this.parseAnnotations(model));
		this.registerControls(model);
		this.detectRepetitions(model);
		this.preprocessAnnotations(model);
	}

	@SuppressWarnings("unchecked")
  private void detectRepetitions(SuiModel model) {
	  for (Page page : model.getPages()) {
	    for (Widget panel :
	      SuiUtil.filterControlsByType(SuiUtil.getAllControlsRecursively(page), Panel.class)) {
	      Repetition r = this.getRepetitionDetector().detectRepetition((Panel) panel);
	      if (r != null) {
	        panel.getParent().replaceControl(panel, r);
	      }
	    }
	  }
  }

  @SuppressWarnings("unchecked")
  private Collection<SuiAnnotation> parseAnnotations(SuiModel model) {
		List<SuiAnnotation> annotations = new ArrayList<SuiAnnotation>();
		for (Widget c : 
				SuiUtil.filterControlsByType(model.getControlsOutsidePages(), Annotation.class)) {
			annotations.add(
					this.getAnnotationParser().parseAnnotation((Annotation) c));
		}
		return annotations;
	}

	@SuppressWarnings("unchecked")
  private void assignExcludedControlsToModel(WidgetGroup group,
			SuiModel model) {
		Collection<Widget> excludedControls = 
			this.filterNonHierarchicalControls(group.getControls());
		for (Widget c : SuiUtil.filterControlsByType(
				group.getControls(), CompositeWidget.class)) {
			if (c.getPage() == null) {
				excludedControls.add(c);
			}
		}
		for (Widget c : excludedControls) {
			model.addControlOutsidePages(c);
		}
	}

	private void detectCollisions(Collection<Widget> controls, SuiModel model) 
			throws SuiTranslationException {
		for (Widget c : controls) {
			for (Widget c2 : SuiUtil.getCollidingControls(controls, c)) {
				if (this.controlIsIncludedIn(c, c2)) {
					continue;
				}
				if (this.controlIsIncludedIn(c2, c)) {
					continue;				
				}
				if (this.oneOfControlsIsAnAnnotation(c, c2)) {
					continue;
				}
				throw new CollidingWidgetsException(model, c, c2);
			}
		}
	}

	private boolean oneOfControlsIsAnAnnotation(Widget c, Widget c2) {
		return (
			SuiUtil.controlIsKindOf(c, Annotation.class) &&
			!SuiUtil.controlIsKindOf(c2, Annotation.class)
		) || (
			SuiUtil.controlIsKindOf(c2, Annotation.class) &&
			!SuiUtil.controlIsKindOf(c, Annotation.class)	
		);
	}

	private boolean controlIsIncludedIn(Widget c, Widget c2) {
		if (!SuiUtil.controlIsKindOf(c2, CompositeWidget.class)) {
			return false;
		}
		return SuiUtil.isIncludedIn(c, c2);
	}

	private void preprocessAnnotations(SuiModel model) {
		this.getAnnotationPreprocessor().processAnnotations(
				this.getParsedAnnotations(), model);
	}
	
	private void processAnnotations(SuiModel model) {
		this.getAnnotationProcessor().processAnnotations(
				this.getParsedAnnotations(), model);	
	}
	
	private void postProcessAnnotations(SuiModel model) {
		this.getAnnotationPostProcessor().processAnnotations(
				this.getParsedAnnotations(), model);
	}

	@SuppressWarnings("unchecked")
  private void associateAnnotations(WidgetGroup group) {
		for (Widget c : 
				SuiUtil.filterControlsByType(group.getControls(), Annotation.class)) {
			Annotation a = (Annotation) c;
			a.setTargetElement(
				// chooses the inner-depth control
				SuiUtil.sortControls(
						SuiUtil.getCollidingControls(group.getControls(), a),
						new Comparator<Widget>() {
							public int compare(Widget c1, Widget c2) {
								 return SuiUtil.getParents(c2).size() -
								 	SuiUtil.getParents(c1).size();
							}
						}).get(0));
		}
	}

	@SuppressWarnings("unchecked")
  private void createDefaultLayouts(SuiModel model) {
		Collection<Widget> controls = new ArrayList<Widget>();
		for (Page p : model.getPages()) {
			controls.addAll(SuiUtil.getAllControlsRecursively(p));
			controls.add(p);
		}
		controls.addAll(model.getControlsOutsidePages());
		for (SuiModelElement c : 
				SuiUtil.filterControlsByType(controls, CompositeWidget.class)) {
			CompositeWidget cc = (CompositeWidget) c;
			if (cc.getLayout() == null) {
				cc.setLayout(this.getDefaultLayoutFactory().createLayout(cc.getControls()));
			}
		}
	}

	private void registerControls(SuiModel model) {
		for (Page p : model.getPages()) {
			for (Widget c : p.getControls()) {
				this.registerControlIn(c, model);
			}
		}
	}

	private void registerControlIn(Widget c, SuiModel model) {
		if (SuiUtil.controlIsKindOf(c, CompositeWidget.class)) {
			this.registerCompositeControlIn((CompositeWidget) c, model);
		}
		else if (SuiUtil.controlIsKindOf(c, SimpleControl.class)) {
			this.registerSimpleControlIn((SimpleControl) c, model);
		}
	}

	private void registerSimpleControlIn(SimpleControl c, SuiModel model) {
		model.registerControl(c);
	}

	private void registerCompositeControlIn(CompositeWidget c,
			SuiModel model) {
		for (Widget child : c.getControls()) {
			this.registerControlIn(child, model);
		}
	}

	@SuppressWarnings("unchecked")
  private void addPagesToModel(Collection<Widget> controls,
			SuiModel model, MockupContainerInfo info) {
		for (SuiModelElement c : SuiUtil.filterControlsByType(controls,
				Page.class)) {
			Page p = (Page) c;
			// adds the container name in order to differentiate
			// between pages in different containers with the same
			// id
			p.setControlId(info.getName() + "-" + CodegenUtil.convertToIdentifier(p.getTitle()));
			model.addPage(p);
		}
	}

	@SuppressWarnings("unchecked")
  private void createControlHierarchy(Collection<Widget> controls, MockupContainerInfo info) {
		Collection<Widget> compositeControls = SuiUtil
				.filterControlsByType(controls, CompositeWidget.class);
		compositeControls = 
			this.excludeNonHierarchicalControls(compositeControls);
		for (Widget c :this.excludeNonHierarchicalControls(controls)) {
			CompositeWidget parent = (CompositeWidget) SuiUtil
					.getParentControlFromCollection(c, compositeControls);
			if (parent != null) {
				parent.addChild(c);
			}
		}
	}

  @SuppressWarnings("unchecked")
  private void createPageIfNone(Collection<Widget> controls, MockupContainerInfo info) {
    if (SuiUtil.filterControlsByType(controls, Page.class).size() == 0) {
      Rectangle r = SuiUtil.getSurroundingRectangle(controls, 1);
      Page p = this.getFactory().createPage(info.getName(), r.getLeft(), r.getTop(), r.getWidth(), r.getHeight(), CodegenUtil.camelCaseToSpaces(info.getName()), info.getName());
      controls.add(p);
    }
  }

  private Collection<Widget> excludeNonHierarchicalControls(Collection<Widget> controls) {
		return SuiUtil.excludeControlTypes(controls,
				this.controlsExcludedFromHierarchyComposition());
	}
	
  private Collection<Widget> filterNonHierarchicalControls(Collection<Widget> controls) {
		return SuiUtil.filterControlsByType(controls,
				this.controlsExcludedFromHierarchyComposition());
	}
	
	@SuppressWarnings("unchecked")
  private Class<? extends Widget>[] controlsExcludedFromHierarchyComposition() {
		return new Class[]{ Annotation.class };
	}

}
