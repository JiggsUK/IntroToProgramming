package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import uk.ac.uos.i2p.assignment.part2.FileManager;

class Part2Tests {

//	@Test
//	void testProcessFolder() throws IOException {
//		FileManager test = new FileManager();
//		File source = new File("testdata\\Source");
//		File destination = new File("testdata\\Destination");
//		
//		test.processFolders(source, destination);
//		
//	}
	
	@Test
	void testProcessFiles() throws IOException {
		FileManager test = new FileManager();
		File context = new File("testdata\\Source\\People\\Employees\\Pay Rise.properties");
		File templates = new File("testdata\\Templates");
		test.processTemplateFolder(templates);
		test.processFiles(context);
		
	}
	
	

}
