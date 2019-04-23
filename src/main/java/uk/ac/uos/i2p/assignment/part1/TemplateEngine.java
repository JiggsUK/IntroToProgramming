package uk.ac.uos.i2p.assignment.part1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TemplateEngine implements TemplateProcessor {

	public Map<String, String> templates;

	public TemplateEngine() {
		templates = new HashMap<String, String>();
	}

	@Override
	public void loadTemplate(String name, String template) {
		templates.put(name.toLowerCase(), template);
	}

	@Override
	public void loadTemplates(Map<String, String> templates) {
		List<String> keys = new ArrayList<>(templates.keySet());
		for (String key : keys) {
			loadTemplate(key, templates.get(key));
		}
	}

	@Override
	public String expandTemplate(String templateName, Map<String, Object> context) {
		String expandedTemplate = null;
		Set<String> loadedTemplateKeys = templates.keySet();
		boolean isTemplateLoaded = loadedTemplateKeys.contains(templateName.toLowerCase());
		System.out.println(templateName);

		if (isTemplateLoaded) {
			String templateText = templates.get(templateName);

			int i = templateText.indexOf("${");
			int end = (templateText.indexOf('}'));
			int count = 0;

			while (i != -1) {
//				System.out.println("Start = " + i);
//				System.out.println("End = " + end);
				count++;
				String expression = templateText.substring(i + 2, end);
//				System.out.println("Thing before = " + templateText.substring(0, i));
//				System.out.println("Expression = " + expression);
//				System.out.println("Expanded Expression = " + expandExpression(expression, context));
//				System.out.println("Thing after = " + templateText.substring(end + 1, templateText.length()));
//				System.out.println(expression);
				templateText = (templateText.substring(0, i) + expandExpression(expression, context)
						+ templateText.substring(end + 1, templateText.length())).trim();
//				System.out.println("\nRun " + count + " = " + templateText + "\n");
				// iterate
				i = templateText.indexOf("${");
				end = templateText.indexOf('}', i);
//				System.out.println(i);
//				System.out.println(end);
				expandedTemplate = templateText;
			}

		} else {
			expandedTemplate = "Template not loaded.";
		}
		System.out.println(expandedTemplate);
		return expandedTemplate;

	}

	public String expandExpression(String expression, Map<String, Object> context) {

		if (context.containsKey(expression)) {
			// Return the String value of key name from the context, (or “null” if it is
			// null)
			return context.get(expression).toString();
		
//		} else if (expression.contains("this")) {
//			ArrayList<char> values = new ArrayList<char>(context.get(expression));
//			return null;

		} else if (expression.charAt(0) == '\'' && expression.charAt(expression.length() - 1) == '\'') {
			// Return the literal text
			return expression.substring(1, expression.length() - 1);

		} else if (expression.charAt(0) == '(' && expression.charAt(expression.length() - 1) == ')') {

			// Handle conditionals
			int indexQuestionMark = expression.indexOf("?");
			int indexColon = expression.indexOf(":");

			if (indexQuestionMark > 0 && indexColon == -1) {
				// evaluate name?expression
				String key = expression.substring(1, indexQuestionMark);
				boolean value = (boolean) context.get(key);
//				System.out.println("value = " + value);
				
				if (value) {
					return expandExpression(expression.substring(indexQuestionMark + 1, expression.length() - 1),
							context);
				} else {
					return null;
				}

			} else if (indexQuestionMark == -1 && indexColon > 0) {
				// evaluate name:expression
				String key = expression.substring(1, indexColon);
				boolean value = (boolean) context.get(key);
				if (!value) {
					// should be the
					return expandExpression(expression.substring(indexColon + 1, expression.length() - 1), context);
				} else {
					return "";
				}
				
//			} else if (expression.contains("*")) {
//				// evaluate name*expression
//				/*
//				 * name = context key
//				 * check if context value starts and ends with []
//				 */
//				int indexAsterix = expression.indexOf("*");
//				String key = expression.substring(1, indexAsterix);
//				String value = context.get(key).toString();
//				if (value != null) {
//					return expandTemplate(expression.substring(indexAsterix + 1, expression.length() - 1), context);
//				} else {
//					return "null";
//				}
			} else {
				// evaluate name?exp1:exp2
				String key = expression.substring(1, indexQuestionMark);
				boolean value = (boolean) context.get(key);
				if (value) {
					return expandExpression(expression.substring(indexQuestionMark + 1, indexColon), context);
				} else {
					return expandExpression(expression.substring(indexColon + 1, expression.length() - 1), context);
				}
			}
		} else if (expression.charAt(0) == '@') {
			// Return the value of loading and expanding the named template using the
			// current context
			return expandTemplate(expression.substring(1, expression.length() - 1), context);
			
		
//		} else if (1==1) {
			
		} else {
			return "null";
		}
	}
}
