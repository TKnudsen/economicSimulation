package dataStructures.person;

import dataStructures.city.City;

public class PersonFactory {

	public static Person createPerson(City city) {
		Person person = new Person(city);
		return person;
	}

	public static Person createPerson(City city, double money) {
		Person person = new Person(city);
		person.setMoney(money);
		return person;
	}
}
