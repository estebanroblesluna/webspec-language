package webspecplugin.editor.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class StartSeleniumServerHandler extends AbstractHandler  {

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		SeleniumServer.safeStart();
		return event;
	}
}
