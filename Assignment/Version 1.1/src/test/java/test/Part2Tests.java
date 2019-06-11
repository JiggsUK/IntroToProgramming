package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import uk.ac.uos.i2p.assignment.part2.FileManager;

class Part2Tests {
	
	@Test
	void testProcessTemplateFolderValidFolder() throws IOException {
		FileManager testManager = new FileManager();
		File tempFolder = new File("testdata\\Templates");
		testManager.processTemplateFolder(tempFolder);
		String actual = testManager.getTemplateEntry("long service award");
		assertEquals("In recognition of your ${years} years of service, we would like to award you a prize of ${item}.", actual);
	}
	
	@Test
	void testProcessTemplateFolderInvalidFolder() throws IOException {
		FileManager testManager = new FileManager();
		File tempFolder = new File("testdata\\T-plates");
		testManager.processTemplateFolder(tempFolder);
		// Expected to print: "Templates Folder does not exist. Please check the filepath and try again.
	}
	
	@Test
	void testProcessFolderOutputFileToDestination() throws IOException {
		FileManager testManager = new FileManager();
		File sourceFolder = new File("testdata\\Source");
		File destinationFolder = new File("testdata\\Destination");
		testManager.processFolders(sourceFolder, destinationFolder);
		File testFolder = new File("testdata/Destination/People/welcome.html");
		assertTrue(testFolder.exists());
	}
	
	@Test
	void testProcessFolderOutputFolderToDestination() throws IOException {
		FileManager testManager = new FileManager();
		File sourceFolder = new File("testdata\\Source");
		File destinationFolder = new File("testdata\\Destination");
		testManager.processFolders(sourceFolder, destinationFolder);
		File testFolder = new File("testdata/Destination/People/Contractors");
		assertTrue(testFolder.exists());
	}
	
	@Test
	void testProcessFileValid() throws IOException {
		FileManager testManager = new FileManager();
		File loadT = new File("testdata\\Templates\\welcome");
		testManager.loadTemplates(loadT);
		File welcomeProp = new File("testdata\\Source\\People\\Welcome.html.properties");
		String actual = testManager.processFile(welcomeProp);
		String expected = "Dear Daisy, welcome to the company.";
		assertEquals(expected, actual);		
	}
	
	@Test
	void testProcessFileNoTemplateLoaded() throws IOException  {
		FileManager testManager = new FileManager();
		File welcomeProp = new File("testdata\\Source\\People\\Welcome.html.properties");
		testManager.processFile(welcomeProp);
		String actual = testManager.processFile(welcomeProp);
		String expected = "Error! Template welcome is not loaded";
		assertEquals(expected, actual);
	}
	
	@Test
	void testProcessFileNoTemplateRequested() throws IOException  {
		FileManager testManager = new FileManager();
		File noRequestProp = new File("testdata/Source/Test - no $template value.html.properties");
		testManager.processFile(noRequestProp);
		String actual = testManager.processFile(noRequestProp);
		String expected = "Error! No template has been requested. Please check the file and try again.";
		assertEquals(expected, actual);
	}
	
	@Test
	void testProcessFileNoTemplateKey() throws IOException  {
		FileManager testManager = new FileManager();
		File noTemplateKeyProp = new File("testdata/Source/Test - no $template in file.html.properties");
		testManager.processFile(noTemplateKeyProp);
		String actual = testManager.processFile(noTemplateKeyProp);
		String expected = "Cannot find '$template' in the file.";
		assertEquals(expected, actual);
	}

	@Test
	void testReplaceBooleanValuesTrueValue() {
		FileManager testManager = new FileManager();
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("fruit", "pineapple");
		testContextMap.put("ripe", "true");
		Boolean expected = (Boolean) testManager.replaceBooleanValues(testContextMap).get("ripe");
		assertTrue(expected);
	}
	
	@Test
	void testReplaceBooleanValuesFalseValue() {
		FileManager testManager = new FileManager();
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("veg", "courgette");
		testContextMap.put("ripe", "false");
		Boolean expected = (Boolean) testManager.replaceBooleanValues(testContextMap).get("ripe");
		assertFalse(expected);
	}
	
	@Test
	void testReplaceBooleanValuesNoBooleanValues() {
		FileManager testManager = new FileManager();
		Map<String, Object> testContextMap = new HashMap<>();
		testContextMap.put("fruit", "apple");
		testContextMap.put("ripe", "yes");
		String expected = "{fruit=apple, ripe=yes}";
		assertEquals(expected, testManager.replaceBooleanValues(testContextMap).toString());
	}
	
	@Test
	void testCreateNewMap() {
		FileManager testManager = new FileManager();
		Properties testProp = new Properties();
		testProp.setProperty("vegetable", "carrot");
		
		Map<String, Object> expected = new HashMap<>();
		expected.put("vegetable", "carrot");
		assertEquals(expected, testManager.createContextMap(testProp));
	}
}