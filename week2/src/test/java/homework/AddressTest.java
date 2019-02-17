package homework;

import static org.junit.jupiter.api.Assertions.*;
import homework.Address;
import org.junit.jupiter.api.Test;

class AddressTest {

	@Test
	public void fullnameTest() {
		Address name = new Address();
		assertEquals("Brandon Lee Graham", name.fullName("Brandon","Lee", "Graham"));
		
	}

	@Test
	public void noMiddleNameTest() {
		Address name = new Address();
		assertEquals("Carson Wentz", name.fullName("Carson","", "Wentz"));
	}

	@Test
	public void forenameOnlyTest() {
		Address name = new Address();
		assertEquals("David", name.fullName("David","", ""));
	}
	
	@Test
	public void middlenameOnlyTest() {
		Address name = new Address();
		assertEquals("Lane", name.fullName("","Lane", ""));
	}
	
	@Test
	public void lastnameOnlyTest() {
		Address name = new Address();
		assertEquals("Johnson", name.fullName("","", "Johnson"));
	}
	
	@Test
	public void nullForenameOnlyTest() {
		Address name = new Address();
		assertEquals("Jones", name.fullName(null,"", "Jones"));
	}
	
	@Test
	public void firstAndMiddleOnlyTest() {
		Address name = new Address();
		assertEquals("Jake Elliott", name.fullName("Jake","Elliott", ""));
	}
	
	@Test
	public void middleAndLastOnlyTest() {
		Address name = new Address();
		assertEquals("Zach Ertz", name.fullName("","Zach", "Ertz"));
	}
	
	@Test
	public void trailingWhiteSpaceTest() {
		Address name = new Address();
		assertEquals("Malcolm Jenkins", name.fullName("","Malcolm", "Jenkins "));
	}
	
	@Test
	public void leadingWhiteSpaceTest() {
		Address name = new Address();
		assertEquals("Jordan Hicks", name.fullName(""," Jordan", "Hicks"));
	}
	
	@Test
	public void whitespaceMiddleNameTest() {
		Address name = new Address();
		assertEquals("Nelson Agholor", name.fullName("Nelson"," ", "Agholor"));
	}
	
	@Test
	public void excessWhiteSpaceTest() {
		Address name = new Address();
		assertEquals("Jason Peters", name.fullName(" "," Jason ", " Peters "));
	}
	
	@Test
	public void multipleMiddleNameTest() {
		Address name = new Address();
		assertEquals("Donnell Laray Jordan Pomphrey", name.fullName("Donnell","Laray Jordan", "Pomphrey"));
	}
	
	@Test
	public void multipleSurNameTest() {
		Address name = new Address();
		assertEquals("Burnell Michael Wallace III", name.fullName("Burnell", "Michael", "Wallace III"));
	}
	
	@Test
	public void apostropheTest() {
		Address name = new Address();
		assertEquals("Cre'Von Lashaad LeBlanc", name.fullName("Cre'Von","Lashaad", "LeBlanc"));
	}
	
	@Test
	public void doubleBarrelTest() {
		Address name = new Address();
		assertEquals("Kamu Grugier-Hill", name.fullName("Kamu","", "Grugier-Hill"));
	}
}
