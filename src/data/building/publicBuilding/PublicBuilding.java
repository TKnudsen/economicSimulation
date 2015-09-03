package data.building.publicBuilding;

import java.util.List;

import model.behaviors.transport.ITransportBehavior;
import data.building.Building;
import data.city.City;
import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;
import data.goods.GoodStock;
import data.person.Person;

public abstract class PublicBuilding extends Building {

	public PublicBuilding(City city, List<Person> personsInvolved, GoodStock goodStock, ITransportBehavior transportBehavior) throws CapacityUnderflowException, CapacityOverflowException {
		super(city, personsInvolved, goodStock, transportBehavior);
	}

}
