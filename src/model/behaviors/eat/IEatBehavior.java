package model.behaviors.eat;

import dataStructures.exceptions.HungerException;
import dataStructures.person.Person;

public interface IEatBehavior {

	public void execute(Person person) throws HungerException;

}
