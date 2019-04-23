package uk.ac.uos.i2p.assignment.part2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import uk.ac.uos.i2p.assignment.part1.TemplateEngine;

public class FileManager {

	private static String templateName;
	private static String templateText;
	private static String sourceFolder;
	private static String destinationFolder;
	private static String templatesFolder;
	private static TemplateEngine templateEngine = new TemplateEngine();
	private static String saveFileName;
	private static String destinationPath; 
	
	public static void main(String[] args) throws IOException {

		sourceFolder = args[0];
		destinationFolder = args[1];
		templatesFolder = args[2];

		File templates = new File(templatesFolder); // new file object of template folder
		File source = new File(sourceFolder);
		File destination = new File(destinationFolder);
		
		processTemplateFolder(templates); // load them into template processor
		processFolders(source,destination);
	}

	// ===== deal with templates ======
	public static void processTemplateFolder(File folder) throws IOException {
		for (File f : folder.listFiles()) {
			loadTemplates(f);
		}
	}

	public static void loadTemplates(File file) throws IOException {
		templateName = file.getName();
		String filepath = (file.getPath());

		List<String> fileContents = Files.readAllLines(Paths.get(filepath));
		StringBuilder fileOutput = new StringBuilder();

		for (String line : fileContents) {
			fileOutput.append(line);
		}
		templateText = fileOutput.toString();
		templateEngine.loadTemplate(templateName, templateText);
	}

	// ===== deal with context/ expand expression =====

	public static void processFolders(File source, File destination) throws IOException {
		/*
		 * checks to run: 
		 * 		if folder exists in destination 
		 * 		is folder 
		 * 		is file 
		 * if directory: 
		 * 		duplicate folder to destination and process again 
		 * if file: 
		 * 		run processfile(file)
		 */	
		
		
		for (File item : source.listFiles()) {
			saveFileName = item.getName();
			destinationPath = destination + File.separator + saveFileName;
			if (item.isDirectory()) {
				Boolean createDir = new File(destinationPath).mkdir(); // if false i.e. not created directory, what do?
				File newPath = new File(destinationPath);
				processFolders(item, newPath);
				
			} else if (item.isFile()) {
				// exists check?
				 saveFile(processFiles(item));
			}
		}
	}

	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public static String processFiles(File file) throws IOException {
	/*
	 * checks to run: if it exists?, can be read
	 * 
	 * Context map = getContextDetails(discovered_context_file); 
	 * Template name = get value of $template in context map 
	 * Result = expandTemplate(template name, context map); 
	 * saveFile(result, discovered_context_file.getName())
	 */
		 
		 Properties contextFile = new Properties();
		 
		 if (file.canRead()) {
			InputStream in = new FileInputStream(file);
			contextFile.load(in);
		 }

		 
		 String templateName = contextFile.getProperty("$template").toLowerCase();
		 Map<String, Object> context = new HashMap(contextFile);
		 
		 for (String key : context.keySet()) {
			 String textvalue = context.get(key).toString();
			 Boolean booleanValue = Boolean.valueOf(textvalue);
			 Boolean falseValue = textvalue.equalsIgnoreCase("false");
	
			 
			 if (booleanValue == true) {
				 context.put(key, booleanValue);
			 } else if (falseValue == true) {
				 context.put(key, false);
			 }
		 }
		 
		 String result = templateEngine.expandTemplate(templateName, context);
		 return result;
	 }

	 public static void saveFile(String textToSave) throws IOException { 
	/*
	 * checks to run: if it already exists - overwrite or not? if space to write
	 * Create a file in the Destination directory and save result to it
	 */
		 String removeExtension = FilenameUtils.removeExtension(destinationPath);
		 Path destination = Paths.get(removeExtension);
		 BufferedWriter writer = Files.newBufferedWriter(destination);
		 writer.write(textToSave);
		 writer.close();
		 
	 }
}
