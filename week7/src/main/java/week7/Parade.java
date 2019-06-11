package week7;

import java.util.HashMap;
import java.util.Map;

public class Parade {
	private Map<String, Integer> parade;

	public Parade() {
		this.parade = new HashMap<String, Integer>();
	}

	void add(Instrument instrument) {
		Integer value = parade.get(instrument.name());
		if (value == null) {
			value = 1;
		}
		value += 1;
		parade.put(instrument.name(), value);
	}
	
	void march() {
		for (String i : parade.keySet()) {
			System.out.println(parade.get(i) + " " + i + "s in the big parade");
		}
	}
}
