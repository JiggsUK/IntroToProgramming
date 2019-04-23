package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import uk.ac.uos.i2p.assignment.part1.TemplateEngine;

class Part1Tests {
	
//	@Test
//	void testLoadSingleTemplate() throws IOException {
//		TemplateEngine test = new TemplateEngine();
//		test.loadTemplate("welcome", "Dear ${name}, welcome to the jungle.");
//		String expected = "Dear ${name}, welcome to the jungle.";
//		String actual = test.templates.get("welcome"); 
//		System.out.println(actual);
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	void testLoadTwoTemplates() throws IOException {
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("welcome", "Dear ${name}, welcome to the jungle.");
//		testTemplateMap.put("sacked", "Sorry, you're fired!");
//		test.loadTemplates(testTemplateMap);
//
//		String expected = "{sacked=Sorry, you're fired!, welcome=Dear ${name}, welcome to the jungle.}";
//		String actual = test.templates.toString();
////		System.out.println(actual);
//		assertEquals(expected, actual);
//		testTemplateMap.clear();
//	}
//	
//	@Test
//	void testLoadSixTemplates() throws IOException {
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle.");
//		testTemplateMap.put("two", "Two is the lonliest number");
//		testTemplateMap.put("three", "Three, is the magic number");
//		testTemplateMap.put("four", "Sorry, you're fired!");
//		testTemplateMap.put("five", "Once I caught a fish alive");
//		testTemplateMap.put("six", "Schix goes here");
//		test.loadTemplates(testTemplateMap);
//
//		String expected = "{six=Schix goes here, four=Sorry, you're fired!, one=Dear ${name}, welcome to the jungle., two=Two is the lonliest number, three=Three, is the magic number, five=Once I caught a fish alive}";
//		String actual = test.templates.toString();
////		System.out.println(actual);
//		assertEquals(expected, actual);
//		testTemplateMap.clear();
//	}
//	
//	@Test
//	void testExpandTemplateFindsTemplateInStore() throws IOException { //TODO rename tests
//		System.out.println("\n---- start testExpandTemplateFindsTemplateInStore ----");
//		TemplateEngine test = new TemplateEngine();
//
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle.");
//		testTemplateMap.put("two", "Two is not the ${thing} number");
//		test.loadTemplates(testTemplateMap);
//		
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//		testContextMap.put("thing", "lonliest");
//
//		String expected = "Two is not the lonliest number";
//		String actual = test.expandTemplate("two", testContextMap);
//		System.out.println("\n---- end testExpandTemplateFindsTemplateInStore ----");
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testExpandTemplateReplaceText() throws IOException {
//		System.out.println("\n---- start testExpandTemplateReplaceText ----");
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//
//		//setup
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("feck", "Two is the ${thing} number");
//		test.loadTemplates(testTemplateMap);
//		testContextMap.put("thing", (Object) "lonliest");
////		System.out.println("context map = " + testContextMap.toString());
//		
//		//test
//		String expected = "Two is the lonliest number";
//		String actual = test.expandTemplate("feck", testContextMap);
//		assertEquals(expected, actual);
//		System.out.println("\n---- end testExpandTemplateReplaceText ----");
//	}
//	
//	@Test
//	void testExpandTemplateMultipleTemplatesOneContext() throws IOException {
//		System.out.println("\n---- start testExpandTemplateMultipleTemplatesOneContext ----");
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//
//		//setup
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("two", "Two is the ${thing} number");
//		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle.");
//		testTemplateMap.put("three", "Three, is the magic ${item}");
//		testTemplateMap.put("four", "Sorry ${name}, you're fired!");
//		testTemplateMap.put("five", "Once I caught a fish ${life status}");
//		testTemplateMap.put("six", "Schix goes ${here}");
//		test.loadTemplates(testTemplateMap);
//		testContextMap.put("name", (Object) "Jim");
////		System.out.println("context map = " + testContextMap.toString());
//		
//		//test
//		String expected = "Dear Jim, welcome to the jungle.";
//		String actual = test.expandTemplate("one", testContextMap);
//		System.out.println("\n---- end testExpandTemplateMultipleTemplatesOneContext ----");
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testExpandTemplateTemplateNotLoaded() throws IOException {
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		testTemplateMap.put("two", "Two is the ${thing} number");
//		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle.");
//		testTemplateMap.put("three", "Three, is the magic ${item}");
//		testTemplateMap.put("four", "Sorry ${name}, you're fired!");
//		testTemplateMap.put("five", "Once I caught a fish ${life status}");
//		testTemplateMap.put("six", "Schix goes ${here}");
//		
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//		testContextMap.put("name", "Jim");
//		
//		System.out.println("\n---- start testExpandTemplateTemplateNotLoaded ----");
//		
//		TemplateEngine test = new TemplateEngine();
//		test.loadTemplates(testTemplateMap);
//		//test
//		String expected = "Template not loaded.";
//		String actual = test.expandTemplate("bingo", testContextMap);
//		System.out.println("\n---- end testExpandTemplateTemplateNotLoaded ----");
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testExpandTemplateVersion3ReturnTwoExpressions() throws IOException {
//		System.out.println("\n---- start testExpandTemplateVersion3ReturnTwoExpressions ----");
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//
//		//setup
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("two", "Two is the ${thing} number");
//		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle.");
//		testTemplateMap.put("three", "Three, is the magic ${item}");
//		testTemplateMap.put("four", "Sorry ${name}, you're fired!");
//		testTemplateMap.put("five", "Once I caught a fish ${life status}, then I let ${blah} go again");
//		testTemplateMap.put("six", "Schix goes ${here}");
//		test.loadTemplates(testTemplateMap);
//		testContextMap.put("life status", "alive");
//		testContextMap.put("blah", "it");
////		System.out.println("context map = " + testContextMap.toString());
//		
//		//test
//		String expected = "Once I caught a fish alive, then I let it go again";
//		String actual = test.expandTemplate("five", testContextMap);
//		System.out.println("\n---- end testExpandTemplateVersion3ReturnTwoExpressions ----");
//		assertEquals(expected, actual);
//	}
//	
//	//------------  Different Expression Tests  -----------------------
//	// ${name} => Return the String value of key name from the context, (or “null” if it is null)
//	@Test
//	void testStringValueOfContextKey() throws IOException {
//		System.out.println("\n---- start testStringValueOfContextKey ----");
//		//setup		
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		testTemplateMap.put("one", "Hello ${name}, welcome to the jungle.");
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//		testContextMap.put("name", "DeSean");
//
//		TemplateEngine test = new TemplateEngine();
//		test.loadTemplates(testTemplateMap);
////		System.out.println("context map = " + testContextMap.toString());
//		
//		//test
//		String expected = "Hello DeSean, welcome to the jungle.";
//		String actual = test.expandTemplate("one", testContextMap);
//		System.out.println("\n---- end testStringValueOfContextKey ----");
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testExpandExpression2True() throws IOException {
//		System.out.println("\n---- start testExpandExpression2True ----");
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//
//		//setup
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle. You will ${(status?bitten)}");
//		
//		test.loadTemplates(testTemplateMap);
//		testContextMap.put("name", "Jack");
//		testContextMap.put("status", true);
//		testContextMap.put("bitten", "be bitten by a unicorn");
//		
////		System.out.println("context map = " + testContextMap.toString());
//		
//		//test
//		String expected = "Dear Jack, welcome to the jungle. You will be bitten by a unicorn";
//		String actual = test.expandTemplate("one", testContextMap);
//		System.out.println("\n---- end testExpandExpression2True ----");
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testExpandExpression2False() throws IOException {
//		System.out.println("\n---- start testExpandExpression2False ----");
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//
//		//setup
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle. You will ${(status?survive)}");
//		
//		test.loadTemplates(testTemplateMap);
//		testContextMap.put("name", "Jack");
//		testContextMap.put("status", false);
//		testContextMap.put("survive", "not break anything....hopefully");
////		System.out.println("context map = " + testContextMap.toString());
//		
//		//test
//		String expected = "Dear Jack, welcome to the jungle. You will null";
//		String actual = test.expandTemplate("one", testContextMap);
//		System.out.println("\n---- end testExpandExpression2False ----");
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testExpandExpression3True() throws IOException {
//		System.out.println("\n---- start testExpandExpression3True ----");
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//
//		//setup
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("one", "You will ${(status:survive)}");
//		
//		test.loadTemplates(testTemplateMap);
//		testContextMap.put("name", "Jack");
//		testContextMap.put("status", true);
////		System.out.println("context map = " + testContextMap.toString());
//		
//		//test
//		String expected = "You will";
//		String actual = test.expandTemplate("one", testContextMap);
//		System.out.println("\n---- end testExpandExpression3True ----");
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testExpandExpression3False() throws IOException {
//		System.out.println("\n---- start testExpandExpression3False ----");
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//
//		//setup
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("one", "You will ${(status:survive)}");
//		
//		test.loadTemplates(testTemplateMap);
//		testContextMap.put("name", "Jack");
//		testContextMap.put("status", false);
//		testContextMap.put("survive", "survive");
////		System.out.println("context map = " + testContextMap.toString());
//		
//		//test
//		String expected = "You will survive";
//		String actual = test.expandTemplate("one", testContextMap);
//		System.out.println("\n---- end testExpandExpression3False ----");
//		assertEquals(expected, actual);
//	}
//	
//	// If value of key name from the context is not null and not false, return the value of exp1 otherwise return the value of exp2
//	@Test
//	void testExpandExpression4True() throws IOException {
//		System.out.println("\n <<< *** START testExpandExpression4True *** >>>");
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//
//		//setup
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle. You will ${(status?survive:die a horrible death)}");
//		
//		test.loadTemplates(testTemplateMap);
//		testContextMap.put("name", "Bobby");
//		testContextMap.put("status", true);
//		testContextMap.put("survive", "get lucky, you son of a gun");
////		System.out.println("context map = " + testContextMap.toString());
//		
//		//test
//		String expected = "Dear Bobby, welcome to the jungle. You will get lucky, you son of a gun";
//		String actual = test.expandTemplate("one", testContextMap);
//		System.out.println("\n <<< *** END testExpandExpression4True *** >>>");
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testExpandExpression4False() throws IOException {
//		System.out.println("\n <<< *** START testExpandExpression4False *** >>>");
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//
//		//setup
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("one", "Dear ${name}, welcome to the jungle. You will ${(status?survive:unlucky)}");
//		
//		test.loadTemplates(testTemplateMap);
//		testContextMap.put("name", "Bobby");
//		testContextMap.put("status", false);
//		testContextMap.put("unlucky", "die a horrible death");
////		System.out.println("context map = " + testContextMap.toString());
//		
//		//test
//		String expected = "Dear Bobby, welcome to the jungle. You will die a horrible death";
//		String actual = test.expandTemplate("one", testContextMap);
//		System.out.println("\n <<< *** END testExpandExpression4False *** >>>");
//		assertEquals(expected, actual);
//	}
//	
//	
//	@Test
//	void testExpandExpression4DoubleNestedExpressionsTrue() throws IOException {
//		System.out.println("\n <<< *** START testExpandExpression4DoubleNestedExpressionsTrue *** >>>");
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//
//		//setup
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("one", "You will ${(status?lucky:(unlucky?a little:a lot))}");
//		
//		test.loadTemplates(testTemplateMap);
//		testContextMap.put("name", "Bobby");
//		testContextMap.put("status", false);
//		testContextMap.put("lucky", "live a long life");
//		testContextMap.put("unlucky", true);
//		testContextMap.put("a little", "get out with a scrape or two");
//		testContextMap.put("a lot", "die a horrible death");
//		
//		//test
//		String expected = "You will get out with a scrape or two";
//		String actual = test.expandTemplate("one", testContextMap);
//		System.out.println("\n <<< *** END testExpandExpression4DoubleNestedExpressionsTrue *** >>>");
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testExpandExpressionStrignLiteral() throws IOException {
//		System.out.println("\n <<< *** START testExpandExpressionStrignLiteral *** >>>");
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//
//		//setup
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("one", "You will be ${`(sorry)`}");
//		
//		test.loadTemplates(testTemplateMap);
//		testContextMap.put("name", "Bobby");
//		testContextMap.put("status", true);
//		testContextMap.put("lucky", "live a long life");
//		testContextMap.put("unlucky", "die a horrible death");
////		System.out.println("context map = " + testContextMap.toString());
//		
//		//test
//		String expected = "You will be (sorry)";
//		String actual = test.expandTemplate("one", testContextMap);
//		System.out.println("\n <<< *** END testExpandExpressionStrignLiteral *** >>>");
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testExpandExpression4NestedExpressionsFalse() throws IOException {
//		System.out.println("\n <<< *** START testExpandExpression4NestedExpressionsFalse *** >>>");
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//
//		//setup
//		TemplateEngine test = new TemplateEngine();
//		testTemplateMap.put("one", "You will ${(status?lucky:(unlucky?a little:a lot))}");
//		
//		test.loadTemplates(testTemplateMap);
//		testContextMap.put("name", "Bobby");
//		testContextMap.put("status", false);
//		testContextMap.put("lucky", "live a long life");
//		testContextMap.put("unlucky", false);
//		testContextMap.put("a little", "get out with a scrape or two");
//		testContextMap.put("a lot", "die a horrible death");
//		
////		System.out.println("context map = " + testContextMap.toString());
//		
//		//test
//		String expected = "You will die a horrible death";
//		String actual = test.expandTemplate("one", testContextMap);
//		System.out.println("\n <<< *** END testExpandExpression4NestedExpressionsFalse *** >>>");
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testMissingContextValues() throws IOException {
//		TemplateEngine test = new TemplateEngine();
//
//		Map<String, String> testTemplateMap = new HashMap<String, String>();
//		testTemplateMap.put("test", "${name} is ${age} years old.");
//		test.loadTemplates(testTemplateMap);
//		
//		Map<String, Object> testContextMap = new HashMap<String, Object>();
//		testContextMap.put("name", "bob");
//		String expected = "bob is null years old.";
//		String actual = test.expandTemplate("test", testContextMap);
//
//		assertEquals(expected, actual);
//	}
	
	@Test
	void testExpression5() throws IOException {
		TemplateEngine test = new TemplateEngine();

		Map<String, String> testTemplateMap = new HashMap<String, String>();
		testTemplateMap.put("test", "The letters are ${(letters*L)}.");
		testTemplateMap.put("L", "Letter = ${this}");
		test.loadTemplates(testTemplateMap);
		
		Map<String, Object> testContextMap = new HashMap<String, Object>();
		testContextMap.put("letters", "['A', 'D', 'X']");
		String expected = "bob is null years old.";
		String actual = test.expandTemplate("test", testContextMap);

		assertEquals(expected, actual);
	}
}
