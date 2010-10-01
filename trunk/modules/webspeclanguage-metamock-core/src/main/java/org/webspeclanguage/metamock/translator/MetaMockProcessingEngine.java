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

import org.webspeclanguage.metamock.model.Annotation;
import org.webspeclanguage.metamock.model.CompositeControl;
import org.webspeclanguage.metamock.model.MetaMockElement;
import org.webspeclanguage.metamock.model.MetaMockFactory;
import org.webspeclanguage.metamock.model.MetaMockModel;
import org.webspeclanguage.metamock.model.Page;
import org.webspeclanguage.metamock.model.SimpleControl;
import org.webspeclanguage.metamock.model.UIControl;
import org.webspeclanguage.metamock.model.annotation.MetaMockAnnotation;
import org.webspeclanguage.metamock.model.layout.LayoutFactory;
import org.webspeclanguage.metamock.model.layout.impl.GridBagLayoutFactory;
import org.webspeclanguage.metamock.translator.annotation.AnnotationProcessor;
import org.webspeclanguage.metamock.translator.annotation.MetaMockControlAnnotationParser;
import org.webspeclanguage.metamock.utils.MetaMockUtil;


/**
 * Facade class for mockup processing.
 * 
 * @author Jose Matias Rivero
 */
public class MetaMockProcessingEngine<TSource> extends
		MetaMockTranslator<TSource> {

	private MetaMockControlParser<TSource> parser;
	private MetaMockControlAnnotationParser annotationParser;
	private MetaMockFactory factory;
	private AnnotationProcessor annotationProcessor;
	private AnnotationProcessor annotationPreprocessor;
	private AnnotationProcessor annotationPostProcessor;
	private Collection<MetaMockAnnotation> parsedAnnotations;

	public MetaMockProcessingEngine(
			MetaMockControlParser<TSource> parser,
			MetaMockControlAnnotationParser annotationParser,
			MetaMockFactory factory) {
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
	}

	private void setParser(MetaMockControlParser<TSource> parser) {
		this.parser = parser;
	}

	private MetaMockControlParser<TSource> getParser() {
		return parser;
	}

	private void setFactory(MetaMockFactory factory) {
		this.factory = factory;
	}

	private MetaMockFactory getFactory() {
		return factory;
	}

	private void setAnnotationProcessor(AnnotationProcessor annotationInterpreter) {
		this.annotationProcessor = annotationInterpreter;
	}

	private AnnotationProcessor getAnnotationProcessor() {
		return annotationProcessor;
	}

	private void setAnnotationParser(MetaMockControlAnnotationParser annotationParser) {
		this.annotationParser = annotationParser;
	}

	private MetaMockControlAnnotationParser getAnnotationParser() {
		return annotationParser;
	}
	
//	@Override
//	public MetaMockModel translateModelFrom(TSource source, 
//			MockupContainerInfo info) throws MetaMockTranslationException {
//		MetaMockModel model = this.getFactory().createMetaMockModel();
//		Collection<MetaMockControlGroup> gs = this.getParser().parseControls(source);
//		for (MetaMockControlGroup g : this.getParser().parseControls(source))
//			this.processControlGroup(g, model, info);
//		return model;
//	}

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

	private void setParsedAnnotations(Collection<MetaMockAnnotation> parsedAnnotations) {
		this.parsedAnnotations = parsedAnnotations;
	}

	private Collection<MetaMockAnnotation> getParsedAnnotations() {
		return parsedAnnotations;
	}

	@Override
	public MetaMockModel getRawModel(TSource source, 
			MockupContainerInfo info) throws MetaMockTranslationException {
		MetaMockModel model = this.getFactory().createMetaMockModel();
		for (MetaMockControlGroup g : this.getParser().parseControls(source)) {
			this.preprocessModel(g, model, info);
		}
		return model;
	}

	@Override
	public MetaMockModel applyAnnotationsAndInferLayouts(MetaMockModel model) {
		this.processAnnotations(model);
		this.createDefaultLayouts(model);
		this.postProcessAnnotations(model);
		return model;
	}

	private void preprocessModel(MetaMockControlGroup group,
			MetaMockModel model, MockupContainerInfo info)
			throws MetaMockTranslationException {
		this.detectCollisions(group.getControls(), model);
		this.createControlHierarchy(group.getControls());
		this.assignExcludedControlsToModel(group, model);
		this.addPagesToModel(group.getControls(), model, info);
		this.associateAnnotations(group);
		this.setParsedAnnotations(this.parseAnnotations(model));
		this.registerControls(model);
		this.preprocessAnnotations(model);
	}

	@SuppressWarnings("unchecked")
  private Collection<MetaMockAnnotation> parseAnnotations(MetaMockModel model) {
		List<MetaMockAnnotation> annotations = new ArrayList<MetaMockAnnotation>();
		for (UIControl c : 
				MetaMockUtil.filterControlsByType(model.getControlsOutsidePages(), Annotation.class)) {
			annotations.add(
					this.getAnnotationParser().parseAnnotation((Annotation) c));
		}
		return annotations;
	}

	@SuppressWarnings("unchecked")
  private void assignExcludedControlsToModel(MetaMockControlGroup group,
			MetaMockModel model) {
		Collection<UIControl> excludedControls = 
			this.filterNonHierarchicalControls(group.getControls());
		for (UIControl c : MetaMockUtil.filterControlsByType(
				group.getControls(), CompositeControl.class)) {
			if (c.getPage() == null) {
				excludedControls.add(c);
			}
		}
		for (UIControl c : excludedControls) {
			model.addControlOutsidePages(c);
		}
	}

	private void detectCollisions(Collection<UIControl> controls, MetaMockModel model) 
			throws MetaMockTranslationException {
		for (UIControl c : controls) {
			for (UIControl c2 : MetaMockUtil.getCollidingControls(controls, c)) {
				if (this.controlIsIncludedIn(c, c2)) {
					continue;
				}
				if (this.controlIsIncludedIn(c2, c)) {
					continue;				
				}
				if (this.oneOfControlsIsAnAnnotation(c, c2)) {
					continue;
				}
				throw new MetaMockCollidingControlsException(model, c, c2);
			}
		}
	}

	private boolean oneOfControlsIsAnAnnotation(UIControl c, UIControl c2) {
		return (
			MetaMockUtil.controlIsKindOf(c, Annotation.class) &&
			!MetaMockUtil.controlIsKindOf(c2, Annotation.class)
		) || (
			MetaMockUtil.controlIsKindOf(c2, Annotation.class) &&
			!MetaMockUtil.controlIsKindOf(c, Annotation.class)	
		);
	}

	private boolean controlIsIncludedIn(UIControl c, UIControl c2) {
		if (!MetaMockUtil.controlIsKindOf(c2, CompositeControl.class)) {
			return false;
		}
		return MetaMockUtil.isIncludedIn(c, c2);
	}

	private void preprocessAnnotations(MetaMockModel model) {
		this.getAnnotationPreprocessor().processAnnotations(
				this.getParsedAnnotations(), model);
	}
	
	private void processAnnotations(MetaMockModel model) {
		this.getAnnotationProcessor().processAnnotations(
				this.getParsedAnnotations(), model);	
	}
	
	private void postProcessAnnotations(MetaMockModel model) {
		this.getAnnotationPostProcessor().processAnnotations(
				this.getParsedAnnotations(), model);
	}

	@SuppressWarnings("unchecked")
  private void associateAnnotations(MetaMockControlGroup group) {
		for (UIControl c : 
				MetaMockUtil.filterControlsByType(group.getControls(), Annotation.class)) {
			Annotation a = (Annotation) c;
			a.setTargetElement(
				// chooses the inner-depth control
				MetaMockUtil.sortControls(
						MetaMockUtil.getCollidingControls(group.getControls(), a),
						new Comparator<UIControl>() {
							public int compare(UIControl c1, UIControl c2) {
								 return MetaMockUtil.getParents(c2).size() -
								 	MetaMockUtil.getParents(c1).size();
							}
						}).get(0));
		}
	}

	@SuppressWarnings("unchecked")
  private void createDefaultLayouts(MetaMockModel model) {
		Collection<UIControl> controls = new ArrayList<UIControl>();
		for (Page p : model.getPages()) {
			controls.addAll(MetaMockUtil.getAllControlsRecursively(p));
			controls.add(p);
		}
		controls.addAll(model.getControlsOutsidePages());
		for (MetaMockElement c : 
				MetaMockUtil.filterControlsByType(controls, CompositeControl.class)) {
			CompositeControl cc = (CompositeControl) c;
			if (cc.getLayout() == null) {
				cc.setLayout(this.getDefaultLayoutFactory().createLayout(cc.getControls()));
			}
		}
	}

	private void registerControls(MetaMockModel model) {
		for (Page p : model.getPages()) {
			for (UIControl c : p.getControls()) {
				this.registerControlIn(c, model);
			}
		}
	}

	private void registerControlIn(UIControl c, MetaMockModel model) {
		if (MetaMockUtil.controlIsKindOf(c, CompositeControl.class)) {
			this.registerCompositeControlIn((CompositeControl) c, model);
		}
		else if (MetaMockUtil.controlIsKindOf(c, SimpleControl.class)) {
			this.registerSimpleControlIn((SimpleControl) c, model);
		}
	}

	private void registerSimpleControlIn(SimpleControl c, MetaMockModel model) {
		model.registerControl(c);
	}

	private void registerCompositeControlIn(CompositeControl c,
			MetaMockModel model) {
		for (UIControl child : c.getControls()) {
			this.registerControlIn(child, model);
		}
	}

	@SuppressWarnings("unchecked")
  private void addPagesToModel(Collection<UIControl> controls,
			MetaMockModel model, MockupContainerInfo info) {
		for (MetaMockElement c : MetaMockUtil.filterControlsByType(controls,
				Page.class)) {
			Page p = (Page) c;
			// adds the container name in order to differentiate
			// between pages in different containers with the same
			// id
			p.setControlId(info.getName() + p.getControlId());
			model.addPage(p);
		}
	}

	@SuppressWarnings("unchecked")
  private void createControlHierarchy(Collection<UIControl> controls) {
		Collection<UIControl> compositeControls = MetaMockUtil
				.filterControlsByType(controls, CompositeControl.class);
		compositeControls = 
			this.excludeNonHierarchicalControls(compositeControls);
		for (UIControl c :this.excludeNonHierarchicalControls(controls)) {
			CompositeControl parent = (CompositeControl) MetaMockUtil
					.getParentControlFromCollection(c, compositeControls);
			if (parent != null) {
				parent.addChild(c);
			}
		}
	}

  private Collection<UIControl> excludeNonHierarchicalControls(Collection<UIControl> controls) {
		return MetaMockUtil.excludeControlTypes(controls,
				this.controlsExcludedFromHierarchyComposition());
	}
	
  private Collection<UIControl> filterNonHierarchicalControls(Collection<UIControl> controls) {
		return MetaMockUtil.filterControlsByType(controls,
				this.controlsExcludedFromHierarchyComposition());
	}
	
	@SuppressWarnings("unchecked")
  private Class<? extends UIControl>[] controlsExcludedFromHierarchyComposition() {
		return new Class[]{ Annotation.class };
	}
	
	private LayoutFactory getDefaultLayoutFactory() {
		return new GridBagLayoutFactory();
	}

}
