package model.behaviors.eat;

import data.exceptions.HungerException;
import data.person.Person;

public interface IEatBehavior {

	public void execute(Person person) throws HungerException;

}
