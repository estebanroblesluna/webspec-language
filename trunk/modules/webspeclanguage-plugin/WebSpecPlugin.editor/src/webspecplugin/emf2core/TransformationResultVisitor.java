package webspecplugin.emf2core;

public interface TransformationResultVisitor {

	void visitSuccessTransformation(SuccessTransformation successTransformation);

	void visitTransformationErrors(TransformationErrors transformationErrors);
}
