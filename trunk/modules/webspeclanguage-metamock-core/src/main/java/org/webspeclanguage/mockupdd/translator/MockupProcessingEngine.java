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
package org.webspeclanguage.mockupdd.translator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.webspeclanguage.mockupdd.codegen.framework.core.CodegenUtil;
import org.webspeclanguage.mockupdd.codegen.generator.Mockup;
import org.webspeclanguage.mockupdd.sui.model.Annotation;
import org.webspeclanguage.mockupdd.sui.model.CompositeWidget;
import org.webspeclanguage.mockupdd.sui.model.Page;
import org.webspeclanguage.mockupdd.sui.model.Panel;
import org.webspeclanguage.mockupdd.sui.model.Repetition;
import org.webspeclanguage.mockupdd.sui.model.SimpleWidget;
import org.webspeclanguage.mockupdd.sui.model.SuiFactory;
import org.webspeclanguage.mockupdd.sui.model.SuiModel;
import org.webspeclanguage.mockupdd.sui.model.SuiModelElement;
import org.webspeclanguage.mockupdd.sui.model.Widget;
import org.webspeclanguage.mockupdd.sui.model.annotation.SuiAnnotation;
import org.webspeclanguage.mockupdd.sui.model.layout.LayoutFactory;
import org.webspeclanguage.mockupdd.sui.model.layout.impl.ScanBasedGridBagLayoutFactory;
import org.webspeclanguage.mockupdd.translator.annotation.AnnotationProcessor;
import org.webspeclanguage.mockupdd.translator.annotation.WidgetAnnotationParser;
import org.webspeclanguage.mockupdd.utils.Rectangle;
import org.webspeclanguage.mockupdd.utils.SuiUtil;


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
  		for (WidgetGroup g : this.getParser().parseWidgets(mockup.getRepresentation())) {
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
		this.detectCollisions(group.getWidgets(), model);
		this.createPageIfNone(group.getWidgets(), info);
		this.createWidgetHierarchy(group.getWidgets(), info);
		this.assignExcludedWidgetsToModel(group, model);
		this.addPagesToModel(group.getWidgets(), model, info);
		this.associateAnnotations(group);
		this.setParsedAnnotations(this.parseAnnotations(model));
		this.registerWidgets(model);
		this.detectRepetitions(model);
		this.preprocessAnnotations(model);
	}

	@SuppressWarnings("unchecked")
  private void detectRepetitions(SuiModel model) {
	  for (Page page : model.getPages()) {
	    for (Widget panel :
	      SuiUtil.filterWidgetsByType(SuiUtil.getAllWidgetsRecursively(page), Panel.class)) {
	      Repetition r = this.getRepetitionDetector().detectRepetition((Panel) panel);
	      if (r != null) {
	        panel.getParent().replaceWidget(panel, r);
	      }
	    }
	  }
  }

  @SuppressWarnings("unchecked")
  private Collection<SuiAnnotation> parseAnnotations(SuiModel model) {
		List<SuiAnnotation> annotations = new ArrayList<SuiAnnotation>();
		for (Widget c : 
				SuiUtil.filterWidgetsByType(model.getWidgetsOutsidePages(), Annotation.class)) {
			annotations.add(
					this.getAnnotationParser().parseAnnotation((Annotation) c));
		}
		return annotations;
	}

	@SuppressWarnings("unchecked")
  private void assignExcludedWidgetsToModel(WidgetGroup group,
			SuiModel model) {
		Collection<Widget> excludedWidgets = 
			this.filterNonHierarchicalWidgets(group.getWidgets());
		for (Widget c : SuiUtil.filterWidgetsByType(
				group.getWidgets(), CompositeWidget.class)) {
			if (c.getPage() == null) {
				excludedWidgets.add(c);
			}
		}
		for (Widget c : excludedWidgets) {
			model.addWidgetOutsidePages(c);
		}
	}

	private void detectCollisions(Collection<Widget> widgets, SuiModel model) 
			throws SuiTranslationException {
		for (Widget c : widgets) {
			for (Widget c2 : SuiUtil.getCollidingWidgets(widgets, c)) {
				if (this.widgetIsIncludedIn(c, c2)) {
					continue;
				}
				if (this.widgetIsIncludedIn(c2, c)) {
					continue;				
				}
				if (this.oneOfWidgetsIsAnAnnotation(c, c2)) {
					continue;
				}
				throw new CollidingWidgetsException(model, c, c2);
			}
		}
	}

	private boolean oneOfWidgetsIsAnAnnotation(Widget c, Widget c2) {
		return (
			SuiUtil.widgetIsKindOf(c, Annotation.class) &&
			!SuiUtil.widgetIsKindOf(c2, Annotation.class)
		) || (
			SuiUtil.widgetIsKindOf(c2, Annotation.class) &&
			!SuiUtil.widgetIsKindOf(c, Annotation.class)	
		);
	}

	private boolean widgetIsIncludedIn(Widget c, Widget c2) {
		if (!SuiUtil.widgetIsKindOf(c2, CompositeWidget.class)) {
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
				SuiUtil.filterWidgetsByType(group.getWidgets(), Annotation.class)) {
			Annotation a = (Annotation) c;
			a.setTargetElement(
				// chooses the inner-depth widget
				SuiUtil.sortWidgets(
						SuiUtil.getCollidingWidgets(group.getWidgets(), a),
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
		Collection<Widget> widgets = new ArrayList<Widget>();
		for (Page p : model.getPages()) {
			widgets.addAll(SuiUtil.getAllWidgetsRecursively(p));
			widgets.add(p);
		}
		widgets.addAll(model.getWidgetsOutsidePages());
		for (SuiModelElement c : 
				SuiUtil.filterWidgetsByType(widgets, CompositeWidget.class)) {
			CompositeWidget cc = (CompositeWidget) c;
			if (cc.getLayout() == null) {
				cc.setLayout(this.getDefaultLayoutFactory().createLayout(cc.getWidgets()));
			}
		}
	}

	private void registerWidgets(SuiModel model) {
		for (Page p : model.getPages()) {
			for (Widget c : p.getWidgets()) {
				this.registerWidgetIn(c, model);
			}
		}
	}

	private void registerWidgetIn(Widget c, SuiModel model) {
		if (SuiUtil.widgetIsKindOf(c, CompositeWidget.class)) {
			this.registerCompositeWidgetIn((CompositeWidget) c, model);
		}
		else if (SuiUtil.widgetIsKindOf(c, SimpleWidget.class)) {
			this.registerSimpleWidgetIn((SimpleWidget) c, model);
		}
	}

	private void registerSimpleWidgetIn(SimpleWidget c, SuiModel model) {
		model.registerWidget(c);
	}

	private void registerCompositeWidgetIn(CompositeWidget c,
			SuiModel model) {
		for (Widget child : c.getWidgets()) {
			this.registerWidgetIn(child, model);
		}
	}

	@SuppressWarnings("unchecked")
  private void addPagesToModel(Collection<Widget> widgets,
			SuiModel model, MockupContainerInfo info) {
		for (SuiModelElement c : SuiUtil.filterWidgetsByType(widgets,
				Page.class)) {
			Page p = (Page) c;
			// adds the container name in order to differentiate
			// between pages in different containers with the same
			// id
			p.setWidgetId(info.getName() + "-" + CodegenUtil.convertToIdentifier(p.getTitle()));
			model.addPage(p);
		}
	}

	@SuppressWarnings("unchecked")
  private void createWidgetHierarchy(Collection<Widget> widgets, MockupContainerInfo info) {
		Collection<Widget> compositeWidgets = SuiUtil
				.filterWidgetsByType(widgets, CompositeWidget.class);
		compositeWidgets = 
			this.excludeNonHierarchicalWidgets(compositeWidgets);
		for (Widget c :this.excludeNonHierarchicalWidgets(widgets)) {
			CompositeWidget parent = (CompositeWidget) SuiUtil
					.getParentWidgetFromCollection(c, compositeWidgets);
			if (parent != null) {
				parent.addChild(c);
			}
		}
	}

  @SuppressWarnings("unchecked")
  private void createPageIfNone(Collection<Widget> widgets, MockupContainerInfo info) {
    if (SuiUtil.filterWidgetsByType(widgets, Page.class).size() == 0) {
      Rectangle r = SuiUtil.getSurroundingRectangle(widgets, 1);
      Page p = this.getFactory().createPage(info.getName(), r.getLeft(), r.getTop(), r.getWidth(), r.getHeight(), CodegenUtil.camelCaseToSpaces(info.getName()), info.getName());
      widgets.add(p);
    }
  }

  private Collection<Widget> excludeNonHierarchicalWidgets(Collection<Widget> widgets) {
		return SuiUtil.excludeWidgetTypes(widgets,
				this.widgetsExcludedFromHierarchyComposition());
	}
	
  private Collection<Widget> filterNonHierarchicalWidgets(Collection<Widget> widgets) {
		return SuiUtil.filterWidgetsByType(widgets,
				this.widgetsExcludedFromHierarchyComposition());
	}
	
	@SuppressWarnings("unchecked")
  private Class<? extends Widget>[] widgetsExcludedFromHierarchyComposition() {
		return new Class[]{ Annotation.class };
	}

}
