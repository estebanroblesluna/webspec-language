package org.webspeclanguage.plugin.editor.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class StopSeleniumServerHandler extends AbstractHandler  {

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		SeleniumServer.safeStop();
		return event;
	}
}
