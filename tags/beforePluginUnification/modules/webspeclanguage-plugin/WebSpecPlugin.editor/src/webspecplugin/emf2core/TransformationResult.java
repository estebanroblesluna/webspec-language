package webspecplugin.emf2core;

public interface TransformationResult {

	void accept(TransformationResultVisitor visitor);
}
