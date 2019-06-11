package uk.ac.uos.i2p.assignment.part2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import uk.ac.uos.i2p.assignment.part1.TemplateEngine;

public class FileManager {

	private TemplateEngine templateEngine = new TemplateEngine();

	public FileManager() {
		this.templateEngine = new TemplateEngine();
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.out
					.println("Error: " + args.length + " arguments entered.\nPlease enter 3 arguments and try again.");
			System.exit(1);
		}
		File sourceFolder = new File(args[0]);
		File destinationFolder = new File(args[1]);
		File templateFolder = new File(args[2]);

		FileManager fileManager = new FileManager();
		fileManager.runLibrary(templateFolder, sourceFolder, destinationFolder);
	}
	
	public String getTemplateEntry(String name) {
		// used for testing mainly
		return templateEngine.getTemplate(name);
	}
	
	public void runLibrary(File templates, File source, File destination) throws IOException {
		processTemplateFolder(templates);
		processFolders(source, destination);
	}

	public void processTemplateFolder(File templateFolder) throws IOException {
		if (templateFolder.exists()) {
			for (File template : templateFolder.listFiles()) {
				loadTemplates(template);
			}
		} else {
			System.out.println("Templates Folder does not exist. Please check the filepath and try again.");
		}
	}

	public void loadTemplates(File templateFile) throws IOException {
		String templateName = templateFile.getName();
		Reader in = new FileReader(templateFile.getPath());
		String templateText = IOUtils.toString(in);
		templateEngine.loadTemplate(templateName, templateText);
	}

	public void processFolders(File source, File destination) throws IOException {
		for (File element : source.listFiles()) {
			File destinationFolder = new File(destination, element.getName());
			String saveLocation = destinationFolder.getPath();
			if (element.isDirectory()) {
				destinationFolder.mkdir();
				processFolders(element, destinationFolder);
			} else if (element.isFile()) {
				if (element.canRead()) {
					writeToFile(processFile(element), saveLocation);
					System.out.println(FilenameUtils.removeExtension(element.getName()) + " has been successfully written to the folder " + element.getParent());
				} else {
					System.out.println("Error: File cannot be read");
				}
			}
		}
	}

	public String processFile(File file) throws IOException {
		Properties contextFile = new Properties();

		if (file.canRead()) {
			Reader reader = new FileReader(file);
			contextFile.load(reader);
			if (contextFile.containsKey("$template")) {
				String requestedTemplate = contextFile.getProperty("$template").toLowerCase();
				if (requestedTemplate == null || requestedTemplate.isEmpty()) {
					return "Error! No template has been requested. Please check the file and try again.";
				}
				Map<String, Object> context = replaceBooleanValues(createContextMap(contextFile));
				return templateEngine.expandTemplate(requestedTemplate, context);
			} else {
				return "Cannot find \'$template\' in the file.";
			}
		} else {
			return "Error: File cannot be read";
		}
	}

	public Map<String, Object> createContextMap(Properties contextFile) {
		Map<String, Object> context = new HashMap<>();
		for (String item : contextFile.stringPropertyNames()) {
			Object value = contextFile.getProperty(item);
			context.put(item, value);
		}
		return context;
	}

	public Map<String, Object> replaceBooleanValues(Map<String, Object> context) {
		for (String key : context.keySet()) {
			String textvalue = context.get(key).toString();
			Boolean trueValueReplacement = Boolean.valueOf(textvalue);
			Boolean falseValueReplacement = textvalue.equalsIgnoreCase("false");

			if (trueValueReplacement) {
				context.put(key, trueValueReplacement);
			} else if (falseValueReplacement) {
				context.put(key, false);
			}
		}
		return context;
	}

	public void writeToFile(String textToSave, String saveLocation) throws IOException {
		saveLocation = FilenameUtils.removeExtension(saveLocation);
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(saveLocation));
		writer.write(textToSave);
		writer.close();
	}
}
