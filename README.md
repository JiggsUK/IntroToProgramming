# Introduction to Programming Module (1st Year)
## Assignment

Using only the standard Java APIs, design and implement a Java library (consisting of one or more classes), one class of which must have a no-argument constructor, and implement the following interface:
```
package uk.ac.uos.i2p.assignment;
import java.util.Map;

public interface TemplateProcessor {
	void loadTemplate(String name, String template);
	void loadTemplates(Map<String, String> templates);
	String expandTemplate(String templateName, Map<String, Object> context);
}
```
The loadTemplate method should store a template in the template processor with the specified name, containing the specified template text.
The loadTemplates method should store a template in the template processor with the specified name, containing the specified template text for each entry in the map.
The expandTemplate method should look through the named template, find placeholders of the form ${expression} and replace them with the value of the specified expression. When all placeholders have been replaced, the method should return the expanded template.

Expression Table
No.	Expression	Result
1	name	Return the String value of key name from the context, (or “null” if it is null)
2	(name?expression)	If value of key name from the context is not null and not false, return the value of expression
3	(name:expression)	If value of key name from the context is null or is false, return the value of expression
4	(name?exp1:exp2)	If value of key name from the context is not null and not false, return the value of exp1 otherwise return the value of exp2
5	(name*template)	If value of key name is Iterable or is an array expand the named template for each element in order
6	(name*template/expression)	If value of key name is Iterable or is an array expand the named template for each element in order, separated by the value of expression
7	@template	Return the value of loading and expanding the named template using the current context
8	‘text’	Return the literal text
9	this	A pseudo context entry containing the current element when iterating
