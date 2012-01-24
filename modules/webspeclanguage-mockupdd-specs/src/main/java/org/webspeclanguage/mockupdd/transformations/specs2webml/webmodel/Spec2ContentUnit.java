package org.webspeclanguage.mockupdd.transformations.specs2webml.webmodel;

import org.webspeclanguage.mockupdd.codegen.webml.webmodel.unit.ContentUnit;
import org.webspeclanguage.mockupdd.specs.hypertext.ClassMappingSpec;

public interface Spec2ContentUnit {

	
	ClassMappingSpec getSpec();
	
	ContentUnit getContentUnit();
}
