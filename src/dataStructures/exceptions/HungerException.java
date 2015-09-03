package dataStructures.exceptions;

import dataStructures.person.Person;

public class HungerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7175520208876978297L;

	public HungerException() {
		super("A person needs to go to bed hungry.");
	}

	public HungerException(Person person) {
		super("Person " + person + " needs to go to bed hungry.");
	}
}
