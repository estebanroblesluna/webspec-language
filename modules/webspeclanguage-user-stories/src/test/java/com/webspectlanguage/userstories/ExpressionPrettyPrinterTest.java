package com.webspectlanguage.userstories;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.webspeclanguage.impl.expression.core.EqualsExpression;
import org.webspeclanguage.impl.expression.core.OrExpression;
import org.webspeclanguage.impl.expression.core.StringConstant;
import org.webspeclanguage.userstories.util.ExpressionPrettyPrinter;


public class ExpressionPrettyPrinterTest {
	
	@Test
	public void print() {
		ExpressionPrettyPrinter prettyPrinter = new ExpressionPrettyPrinter(new MessageSourceMock(), new Locale("es", "ES"));
		OrExpression and = new OrExpression(new EqualsExpression(
				new StringConstant("A"), new StringConstant("A")
		), new EqualsExpression(
				new StringConstant("B"), new StringConstant("B")
		));
		System.out.println(prettyPrinter.prettyPrint(and));
		System.out.println(StringEscapeUtils.unescapeHtml(new MessageSourceMock().getMessage("userstory.exp.pretty.printer.interaction", null, null)));
		System.out.println(StringEscapeUtils.unescapeHtml("oraci—n"));
		System.out.println(StringEscapeUtils.escapeHtml("oraci—n"));
		System.out.println(StringEscapeUtils.unescapeHtml((StringEscapeUtils.escapeHtml("oraci—n"))));
	}
	

	private static class MessageSourceMock implements MessageSource {
		private Map<String, String> messages;
		
		public MessageSourceMock() {
			this.initialize();
		}
		
		private void initialize() {
			Map<String, String> messages = new HashMap<String, String>();
			messages.put("userstory.exp.pretty.printer.and","y");
			messages.put("userstory.exp.pretty.printer.array.access","acceso en arreglo");
			messages.put("userstory.exp.pretty.printer.array","arreglo");
			messages.put("userstory.exp.pretty.printer.no.precondition","no hay precondici&oacute;n");
			messages.put("userstory.exp.pretty.printer.precondition.false","precondici&oacute;n falsa");
			messages.put("userstory.exp.pretty.printer.generator","generador");
			messages.put("userstory.exp.pretty.printer.or","o");
			messages.put("userstory.exp.pretty.printer.widget","Widget");
			messages.put("userstory.exp.pretty.printer.property","propiedad");
			messages.put("userstory.exp.pretty.printer.interaction","Interacci&oacute;n");
			this.setMessages(messages);
		}

		public String getMessage(MessageSourceResolvable resolvable,
				Locale locale) throws NoSuchMessageException {
			return resolvable.getDefaultMessage();
		}

		public String getMessage(String code, Object[] args, Locale locale)
				throws NoSuchMessageException {
			return this.getMessate(code);
		}

		public String getMessage(String code, Object[] args,
				String defaultMessage, Locale locale) {
			return this.getMessate(code);
		}
		private String getMessate(String code) {
			return this.getMessages().get(code);
		}

		private Map<String, String> getMessages() {
			return messages;
		}

		private void setMessages(Map<String, String> messages) {
			this.messages = messages;
		}
		
	}
}
