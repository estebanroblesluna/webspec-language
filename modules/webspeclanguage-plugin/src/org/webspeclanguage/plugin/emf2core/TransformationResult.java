package org.webspeclanguage.plugin.emf2core;

public interface TransformationResult {

	void accept(TransformationResultVisitor visitor);
}
