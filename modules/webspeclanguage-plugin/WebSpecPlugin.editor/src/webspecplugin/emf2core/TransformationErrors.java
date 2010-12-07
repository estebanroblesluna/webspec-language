package webspecplugin.emf2core;

import java.util.List;

public class TransformationErrors implements TransformationResult {

	private List<String> errors;
	private List<String> warnings;
	
	public TransformationErrors(List<String> errors, List<String> warnings) {
		this.setErrors(errors);
		this.setWarnings(warnings);
	}

	public void accept(TransformationResultVisitor visitor) {
		visitor.visitTransformationErrors(this);
	}
	
	public List<String> getErrors() {
		return errors;
	}

	private void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public List<String> getWarnings() {
		return warnings;
	}

	private void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
}
