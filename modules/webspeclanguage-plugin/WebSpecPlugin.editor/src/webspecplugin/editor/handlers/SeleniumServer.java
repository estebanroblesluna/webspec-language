package webspecplugin.editor.handlers;

import java.io.IOException;

public class SeleniumServer {

	private static Process thread;
	
	public static synchronized void safeStart() {
		if (!hasStarted()) {
			try {
				thread = Runtime.getRuntime().exec("java -jar  /home/esteban/.m2/repository/org/seleniumhq/selenium/server/selenium-server/1.0.1/selenium-server-1.0.1-standalone.jar");
				thread.exitValue();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	  
	public static synchronized void safeStop() {
		if (hasStarted()) {
			thread.destroy();
			thread = null;
		}
	}
	
	private static synchronized boolean hasStarted() {
		return thread != null;
	}
}
