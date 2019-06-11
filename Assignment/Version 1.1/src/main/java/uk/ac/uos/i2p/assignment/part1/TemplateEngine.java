package uk.ac.uos.i2p.assignment.part1;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"WeakerAccess", "DuplicateExpressions"})
public class TemplateEngine implements TemplateProcessor {

    private Map<String, String> loadedTemplates;
    private boolean templateLoaded;

    public TemplateEngine() {

        this.loadedTemplates = new HashMap<>();
    }

    public String getTemplate(String name) {

        return loadedTemplates.get(name);
    }

    public void setTemplateLoaded(boolean setting) {

        templateLoaded = setting;
    }

    @Override
    public void loadTemplate(String name, String template) {

        loadedTemplates.put(name.toLowerCase(), template);
    }

    @Override
    public void loadTemplates(Map<String, String> templates) {

        loadedTemplates.putAll(templates);
    }

    @Override
    public String expandTemplate(String templateName, Map<String, Object> context) {
        String expandedTemplate = null;

        if (loadedTemplates.keySet().contains(templateName)) {
            String templateText = getTemplate(templateName);
            if (templateText.isEmpty()) {
                setTemplateLoaded(false);
                expandedTemplate = "Error! Template " + templateName + " does not contain any text";
            } else {
                setTemplateLoaded(true);
                int i = templateText.indexOf("${");
                int end = templateText.indexOf('}');
                if (i == -1 && end == -1) {
                    return templateText; // no placeholders in text
                }
                while (i != -1) {
                    String expression = templateText.substring(i + 2, end);
                    templateText = (templateText.substring(0, i) + expandExpression(expression, context)
                            + templateText.substring(end + 1, templateText.length())).trim();
                    i = templateText.indexOf("${");
                    end = templateText.indexOf('}', i);
                    expandedTemplate = templateText;
                }
            }
        } else {
            setTemplateLoaded(false);
            expandedTemplate = "Error! Template " + templateName + " is not loaded";
        }
        return expandedTemplate;

    }

    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public String expandExpression(String expression, Map<String, Object> context) {

        if (expression.charAt(0) == '\'' && expression.charAt(expression.length() - 1) == '\'') {
            // Return the literal text
            return expression.substring(1, expression.length() - 1);
        } else if (expression.charAt(0) == '@') {
            // Return the value of loading and expanding the named template using the
            // current context
            return expandTemplate(expression.substring(1, expression.length()), context);
        } else if (expression.charAt(0) == '(' && expression.charAt(expression.length() - 1) == ')') {
            // Handle conditionals
            int indexQuestionMark = expression.indexOf('?');
            int indexColon = expression.indexOf(':');
            int indexAsterix = expression.indexOf('*');
            int indexSlash = expression.indexOf('/');

            if (indexAsterix > 0) {
                // evaluate name*template
                String key = expression.substring(1, indexAsterix);
                String nestedTemplate = expression.substring(indexAsterix + 1, expression.length() - 1);
                String delimiter = " ";
                if (indexSlash > 0) {
                    // evaluate name*template/expression
                    nestedTemplate = expression.substring(indexAsterix + 1, indexSlash);
                    String delimiterExpression = expression.substring(indexSlash + 1, expression.length() - 1);
                    delimiter = expandExpression(delimiterExpression, context);
                }
                if (context.get(key) instanceof Collection<?>) {
                    List<Character> value = (List<Character>) context.get(key);
                    StringBuilder returnedExpression = new StringBuilder();
                    for (int count = 0; count < value.size(); count++) {
                        context.put("this", value.get(count));
                        String nestedTemplateExpansion = expandTemplate(nestedTemplate, context);
                        if (!templateLoaded) {
                            returnedExpression.append(nestedTemplateExpansion);
                            break;
                        }
                        if (count == (value.size() - 1)) {
                            returnedExpression.append(nestedTemplateExpansion);
                        } else {
                            returnedExpression.append(nestedTemplateExpansion);
                            returnedExpression.append(delimiter);
                        }
                    }
                    return returnedExpression.toString();
                } else {
                    return "";
                }
            } else if (indexQuestionMark > 0 && indexColon == -1) {
                // evaluate name?expression
                String key = expression.substring(1, indexQuestionMark);
                boolean booleanValue = checkBooleanValue(key, context);
                if (context.get(key) == null || !booleanValue) {
                    return "";
                } else {
                    return expandExpression(expression.substring(indexQuestionMark + 1, expression.length() - 1),
                            context);
                }
            } else if (indexQuestionMark == -1 && indexColon > 0) {
                // evaluate name:expression
                String key = expression.substring(1, indexColon);
                boolean booleanValue = checkBooleanValue(key, context);
                if (context.get(key) == null || !booleanValue) {
                    return expandExpression(expression.substring(indexColon + 1, expression.length() - 1), context);
                } else {
                    return expandExpression(key, context);
                }
            } else if (indexQuestionMark > 0 && indexColon > 0) {
                // evaluate name?exp1:exp2
                String key = expression.substring(1, indexQuestionMark);
                boolean booleanValue = checkBooleanValue(key, context);
                if (context.get(key) == null || !booleanValue) {
                    return expandExpression(expression.substring(indexColon + 1, expression.length() - 1), context);
                } else {
                    return expandExpression(expression.substring(indexQuestionMark + 1, indexColon), context);
                }
            } else {
                return "";
            }
        } else if (context.containsKey(expression)) {
            // Return the String value of key name from the context, (or “null” if it is
            // null)
            if (context.get(expression) != null) {
                return context.get(expression).toString();
            } else {
                return "null";
            }
        } else {
            return "";
        }
    }

    public boolean checkBooleanValue(String key, Map<String, Object> context) {
        Object value = context.get(key);
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else {
            return true;
        }

    }
}
