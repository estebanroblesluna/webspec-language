package webspecplugin.editor.initialize;

import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ClasspathVariableInitializer;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

public class ClasspathVariableInitializer1 extends ClasspathVariableInitializer {

	@Override
	public void initialize(String variable) {
		try {
			JavaCore.setClasspathVariable(variable, new Path("./"), null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
