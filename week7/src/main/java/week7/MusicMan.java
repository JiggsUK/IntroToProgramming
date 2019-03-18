package week7;

import java.util.ArrayList;
import java.util.Collection;

public class MusicMan {

	private Collection<Instrument> instruments;
	
	public MusicMan() {
		this.instruments = new ArrayList<>();
	}
	
	void add(Instrument instrument) {
		instruments.add(instrument);
	}
	
	void play() {
		for (Instrument instrument : instruments) {
			System.out.println("I can play the " + instrument.name() +", " + "it goes " + instrument.sound());
			
		}
	}
}
