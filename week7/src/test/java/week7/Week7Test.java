package week7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Week7Test {

	@Test
	void testDrumName() {
		Drum drum = new Drum();
		String expected = "Drum";
		String actual = drum.name();
		assertEquals(expected, actual);
	}
	
	@Test
	void testBasilBrush() {
		Drum drum = new Drum();
		String expected = "Boom! Boom!";
		String actual = drum.sound();
		assertEquals(expected, actual);
	}
	
	@Test
	void testTrumpetName() {
		Trumpet trumpet = new Trumpet();
		String expected = "Trumpet";
		String actual = trumpet.name();
		assertEquals(expected, actual);
	}
	
	@Test
	void testTrumpetSound() {
		Trumpet trumpet = new Trumpet();
		String expected = "Toot! Toot!";
		String actual = trumpet.sound();
		assertEquals(expected, actual);
	}
	
	@Test
	void testTriangleName() {
		Triangle triangle = new Triangle();
		String expected = "Triangle";
		String actual = triangle.name();
		assertEquals(expected, actual);
	}
	
	@Test
	void testTriangleSound() {
		Triangle triangle = new Triangle();
		String expected = "Ding!";
		String actual = triangle.sound();
		assertEquals(expected, actual);
	}
	
	@Test
	void testMusicMan() {
		MusicMan musicman = new MusicMan();  // creates a MusicMan object that allows you to use the add & play methods
		musicman.add(new Drum()); // adds a new drum object to the MusicMan object
		musicman.play();  //plays the MusicMan object 
	}
	
	@Test
	void testParade() {
		Parade parade = new Parade();
		parade.add(new Drum());
		parade.add(new Drum());
		parade.add(new Triangle());
		parade.add(new Triangle());
		parade.add(new Triangle());
		parade.add(new Triangle());
		parade.add(new Triangle());
		parade.add(new Trumpet());
		parade.add(new Trumpet());
		parade.add(new Trumpet());
		parade.add(new Trumpet());
		parade.march();
	}

}
