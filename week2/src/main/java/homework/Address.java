package homework;

public class Address {
	public String fullName(String forename, String middleName, String surName) { 
		
		//remove any leading or trailing spaces on the name to ensure correct output
		String first = forename.trim();
		String middle = middleName.trim();
		String last = surName.trim();
		
		StringBuilder name = new StringBuilder();
		
		if (!first.isEmpty()) {
			name.append(first);
			if (!middle.isEmpty() || !last.isEmpty()) {   //only want to add a space if there is another name to output
				name.append(" ");	
			}
		}
		
		if (!middle.isEmpty()) {
			name.append(middle);
			if (!last.isEmpty()) {
				name.append(" ");	
			}
		}
		
		if (!last.isEmpty()) {
			name.append(last);
		}
		
		return "" + name;
	}
				
}

