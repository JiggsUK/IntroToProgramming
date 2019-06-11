package test;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import uk.ac.uos.i2p.assignment.part1.TemplateEngine;

class Part1Tests {

	@Test
	void testLoadSingleTemplate() {
		TemplateEngine test = new TemplateEngine();
		test.loadTemplate("welcome", "Dear ${name}, welcome to the jungle.");
		String expected = "Dear ${name}, welcome to the jungle.";
		String actual = test.getTemplate("welcome");
		assertEquals(expected, actual);
	}

	@Test
	void testLoadTemplatesWithMap() {
		Map<String, String> testTemplateMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("welcome", "Dear ${name}, welcome to the jungle.");
		testTemplateMap.put("sacked", "Sorry, you're fired!");
		test.loadTemplates(testTemplateMap);
		String expected = "Sorry, you're fired!";
		String actual = test.getTemplate("sacked");
		assertEquals(expected, actual);
		String expected2 = "Dear ${name}, welcome to the jungle.";
		String actual2 = test.getTemplate("welcome");
		assertEquals(expected2, actual2);
	}

	@Test
	void testLoadTemplatesWithNoTemplate() {
		Map<String, String> testTemplateMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle.");
		testTemplateMap.put("two", "Two is the lonliest number");
		testTemplateMap.put("three", null);
		test.loadTemplates(testTemplateMap);
		assertEquals(null, test.getTemplate("three"));
	}
	
	@Test
	void testExpandValidTemplateWithNoText() {
		Map<String, String> testTemplateMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle.");
		testTemplateMap.put("two", "Two is the lonliest number");
		testTemplateMap.put("three", "");
		test.loadTemplates(testTemplateMap);
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("thing", "lonliest");
		assertEquals("Error! Template three does not contain any text", test.expandTemplate("three", testContextMap));
	}

	@Test
	void testExpandTemplateReplaceText()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("number", "Two is the ${thing} number");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("thing", "lonliest");
		String expected = "Two is the lonliest number";
		String actual = test.expandTemplate("number", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpandTemplateTemplateNotLoaded()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		testTemplateMap.put("two", "Two is the ${thing} number");
		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle.");
		testTemplateMap.put("three", "Three, is the magic ${item}");
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("name", "Jim");
		TemplateEngine test = new TemplateEngine();
		test.loadTemplates(testTemplateMap);
		String expected = "Error! Template bingo is not loaded";
		String actual = test.expandTemplate("bingo", testContextMap);
		assertEquals(expected, actual);
	}
	
	@Test
	void testExpandTemplateNoPlaceholders()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		testTemplateMap.put("two", "Two is the lonliest number");
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("name", "Jim");
		TemplateEngine test = new TemplateEngine();
		test.loadTemplates(testTemplateMap);
		String expected = "Two is the lonliest number";
		String actual = test.expandTemplate("two", testContextMap);
		assertEquals(expected, actual);
	}

	// --------------------- Various Expression Tests ---------------------

	@Test
	void testExpression1ReturnStringValueOfKeyFromContext()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		testTemplateMap.put("one", "Hello ${name}, welcome to the jungle.");
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("name", "DeSean");
		TemplateEngine test = new TemplateEngine();
		test.loadTemplates(testTemplateMap);
		String expected = "Hello DeSean, welcome to the jungle.";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpression1ReturnNull()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		testTemplateMap.put("one", "Hello ${name}, welcome to the jungle.");
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("name", null);
		TemplateEngine test = new TemplateEngine();
		test.loadTemplates(testTemplateMap);
		String expected = "Hello null, welcome to the jungle.";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpression1MissingContextValues()  {
		TemplateEngine test = new TemplateEngine();
		Map<String, String> testTemplateMap = new HashMap<>();
		testTemplateMap.put("test", "${name} is ${age} years old.");
		test.loadTemplates(testTemplateMap);
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("name", "bob");
		String expected = "bob is  years old.";
		String actual = test.expandTemplate("test", testContextMap);
		assertEquals(expected, actual);
	}

	// If value of key name from the context is not null and not false, return the
	// value of expression
	@Test
	void testExpandExpression2NotFalseOrNullByTrueBoolean()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "Welcome to the jungle. You will ${(status?bitten)}");
		testContextMap.put("name", "Jack");
		testContextMap.put("status", true);
		testContextMap.put("bitten", "be bitten by a unicorn");
		test.loadTemplates(testTemplateMap);
		String expected = "Welcome to the jungle. You will be bitten by a unicorn";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpandExpression2NotFalseOrNullByStringExpression()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "Welcome to the jungle. You will ${(status?bitten)}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("name", "Jack");
		testContextMap.put("status", "'bitten'");
		testContextMap.put("bitten", "be bitten by a unicorn");
		String expected = "Welcome to the jungle. You will be bitten by a unicorn";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpandExpression2IsFalse()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle. You will ${(status?survive)}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("name", "Jack");
		testContextMap.put("status", false);
		testContextMap.put("survive", "not break anything....hopefully");
		String expected = "Dear Jack, welcome to the jungle. You will";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	// If value of key name from the context is null or is false, return the value
	// of expression
	@Test
	void testExpandExpression3IsNull()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "You will ${(status:survive)}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("status", null);
		testContextMap.put("survive", "live a long life");
		String expected = "You will live a long life";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpandExpression3IsFalse()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "You will ${(status:'survive')}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("status", false);
		String expected = "You will survive";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpandExpression3IsTrue()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "You will ${(status:survive)}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("status", true);
		testContextMap.put("survive", "succeed at polo");
		String expected = "You will true";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpandExpression3IsInteger()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "You will ${(status:survive)}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("status", 56553);
		testContextMap.put("survive", "succeed at polo");
		String expected = "You will 56553";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	// If value of key name from the context is not null and not false, return the
	// value of exp1 otherwise return the value of exp2
	@Test
	void testExpandExpression4Return1stExpValueIsTrue()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "You like ${(choice?mystical:normal)}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("name", "Bobby");
		testContextMap.put("choice", true);
		testContextMap.put("mystical", "fluffy unicorns");
		testContextMap.put("normal", "red pandas");
		String expected = "You like fluffy unicorns";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpandExpression4Return1stExpValueIsInteger()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "You like ${(choice?mystical:normal)}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("name", "Bobby");
		testContextMap.put("choice", 98556);
		testContextMap.put("mystical", "fluffy unicorns");
		testContextMap.put("normal", "red pandas");
		String expected = "You like fluffy unicorns";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpandExpression4Return2ndExpValueIsFalse()  {
		// if team choice = null or false, return other
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "Your choice of team to support is ${(team choice?cowboys:other)}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("name", "Bobby");
		testContextMap.put("team choice", false);
		testContextMap.put("cowboys", "diabolical");
		testContextMap.put("other", "alright, I guess");
		String expected = "Your choice of team to support is alright, I guess";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpandExpression4Return2ndExpValueIsNull()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "You choice of team to support is ${(team choice?cowboys:browns)}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("name", "Bobby");
		testContextMap.put("team choice", null);
		testContextMap.put("cowboys", "diabolical");
		testContextMap.put("browns", "alright, I guess");
		String expected = "You choice of team to support is alright, I guess";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpandExpression4NestedExpressionIsTrue()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "You will ${(status?lucky:(unlucky?a little:a lot))}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("name", "Bobby");
		testContextMap.put("status", false);
		testContextMap.put("unlucky", true);
		testContextMap.put("a little", "get out with a scrape or two");
		String expected = "You will get out with a scrape or two";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpandExpression4NestedExpressionIsFalse()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "You will ${(status?lucky:(unlucky?a little:a lot))}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("name", "Bobby");
		testContextMap.put("status", false);
		testContextMap.put("unlucky", false);
		testContextMap.put("a little", "get out with a scrape or two");
		testContextMap.put("a lot", "die a horrible death");
		String expected = "You will die a horrible death";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	// If value of key name is Iterable or is an array expand the named template for
	// each element in order
	@Test
	void testExpression5IsIterable()  {
		TemplateEngine test = new TemplateEngine();
		Map<String, String> testTemplateMap = new HashMap<>();
		testTemplateMap.put("test", "The letters are ${(letters*L)}.");
		testTemplateMap.put("L", "Letter = ${this}");
		test.loadTemplates(testTemplateMap);
		List<Character> testList = new ArrayList<>();
		testList.add('A');
		testList.add('B');
		testList.add('F');
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("letters", testList);
		String expected = "The letters are Letter = A Letter = B Letter = F.";
		String actual = test.expandTemplate("test", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpression5NotIterable()  {
		TemplateEngine test = new TemplateEngine();
		Map<String, String> testTemplateMap = new HashMap<>();
		testTemplateMap.put("test", "The letters are ${(letters*L)}.");
		testTemplateMap.put("L", "Letter = ${this}");
		test.loadTemplates(testTemplateMap);
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("letters", "uh oh");
		String expected = "The letters are .";
		String actual = test.expandTemplate("test", testContextMap);
		assertEquals(expected, actual);
	}

	// If value of key name is Iterable or is an array expand the named template for
	// each element in order, separated by the value of expression
	@Test
	void testExpression6And9StringLiteralDelimiter()  {
		TemplateEngine test = new TemplateEngine();
		Map<String, String> testTemplateMap = new HashMap<>();
		testTemplateMap.put("test", "The letters are ${(letters*L/',')}.");
		testTemplateMap.put("L", "Letter = ${this}");
		test.loadTemplates(testTemplateMap);
		List<Character> testList = new ArrayList<>();
		testList.add('A');
		testList.add('B');
		testList.add('F');
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("letters", testList);
		String expected = "The letters are Letter = A,Letter = B,Letter = F.";
		String actual = test.expandTemplate("test", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpression6And9ExpressionDelimiter()  {
		TemplateEngine test = new TemplateEngine();
		Map<String, String> testTemplateMap = new HashMap<>();
		testTemplateMap.put("test", "The letters are ${(letters*L/(delimiter?choice))}.");
		testTemplateMap.put("L", "Letter = ${this}");
		test.loadTemplates(testTemplateMap);
		List<Character> testList = new ArrayList<>();
		testList.add('A');
		testList.add('B');
		testList.add('F');
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("letters", testList);
		testContextMap.put("delimiter", true);
		testContextMap.put("choice", "__");
		String expected = "The letters are Letter = A__Letter = B__Letter = F.";
		String actual = test.expandTemplate("test", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpression6NoTemplateLoadedFail()  {
		TemplateEngine test = new TemplateEngine();
		Map<String, String> testTemplateMap = new HashMap<>();
		testTemplateMap.put("test", "The letters are ${(letters*L/',')}.");
		test.loadTemplates(testTemplateMap);
		List<Character> testList = new ArrayList<>();
		testList.add('A');
		testList.add('B');
		testList.add('F');
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("letters", testList);
		String expected = "The letters are Error! Template L is not loaded."; // ?
		String actual = test.expandTemplate("test", testContextMap);
		assertEquals(expected, actual);
	}

	// Return the value of loading and expanding the named template using the
	// current context
	@Test
	void testExpression7ReturnTemplteUsingCurrentContext()  {
		TemplateEngine test = new TemplateEngine();
		Map<String, String> testTemplateMap = new HashMap<>();
		testTemplateMap.put("fruit", "The ${fruit} is ${@quality}.");
		testTemplateMap.put("quality", "${(ripe?'juicy':'hard')}");
		test.loadTemplates(testTemplateMap);
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("fruit", "apple");
		testContextMap.put("ripe", true);
		String expected = "The apple is juicy."; 
		String actual = test.expandTemplate("fruit", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpression7NoLoadedTemplate()  {
		TemplateEngine test = new TemplateEngine();
		Map<String, String> testTemplateMap = new HashMap<>();
		testTemplateMap.put("fruit", "The ${fruit} is ${@quality}.");
		test.loadTemplates(testTemplateMap);
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("fruit", "apple");
		String expected = "The apple is Error! Template quality is not loaded."; 
		String actual = test.expandTemplate("fruit", testContextMap);
		assertEquals(expected, actual);
	}

	// Return the literal text
	@Test
	void testExpression8ReturnStringLiteral()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "You will be ${'promoted'}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("name", "Bobby");
		String expected = "You will be promoted";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}

	@Test
	void testExpression8ReturnANumber()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "The Dallas Cowboys are ${'rubbish'} and will score ${'0'} points!");
		test.loadTemplates(testTemplateMap);
		String expected = "The Dallas Cowboys are rubbish and will score 0 points!";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}
	
	@Test
	void testMultipleDifferentExpressions()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "You will ${(status?lucky:(unlucky?a little:a lot))}. That was a ${(close?'close shave!')} Next time, be ${scold}.");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("name", "Bobby");
		testContextMap.put("status", false);
		testContextMap.put("lucky", "live a long life");
		testContextMap.put("unlucky", true);
		testContextMap.put("a little", "get out with a scrape or two");
		testContextMap.put("a lot", "die a horrible death");
		testContextMap.put("scold", "careful");
		testContextMap.put("close", true);
		String expected = "You will get out with a scrape or two. That was a close shave! Next time, be careful.";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}
	
	@Test
	void testExpressionAsContextKey()  {
		Map<String, String> testTemplateMap = new HashMap<>();
		Map<String, Object> testContextMap = new HashMap<>();
		TemplateEngine test = new TemplateEngine();
		testTemplateMap.put("one", "${'hello'}");
		test.loadTemplates(testTemplateMap);
		testContextMap.put("'hello'", "goodbye");
		String expected = "hello";
		String actual = test.expandTemplate("one", testContextMap);
		assertEquals(expected, actual);
	}
}