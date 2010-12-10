package org.webspeclanguage.plugin.emf2core;

import java.util.List;

import org.webspeclanguage.api.Diagram;

public class SuccessTransformation implements TransformationResult {

	private Diagram webSpecDiagram;
	private List<String> warnings;
	
	public SuccessTransformation(Diagram webSpecDiagram, List<String> warnings) {
		this.setWebSpecDiagram(webSpecDiagram);
		this.setWarnings(warnings);
	}


	public void accept(TransformationResultVisitor visitor) {
		visitor.visitSuccessTransformation(this);
	}

	public Diagram getWebSpecDiagram() {
		return webSpecDiagram;
	}

	private void setWebSpecDiagram(Diagram webSpecDiagram) {
		this.webSpecDiagram = webSpecDiagram;
	}


	public List<String> getWarnings() {
		return warnings;
	}


	private void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
}
