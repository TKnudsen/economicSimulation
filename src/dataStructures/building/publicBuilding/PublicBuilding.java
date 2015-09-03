package dataStructures.building.publicBuilding;

import java.util.List;

import model.behaviors.transport.ITransportBehavior;
import dataStructures.building.Building;
import dataStructures.city.City;
import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.goods.GoodStock;
import dataStructures.person.Person;

public abstract class PublicBuilding extends Building {

	public PublicBuilding(City city, List<Person> personsInvolved, GoodStock goodStock, ITransportBehavior transportBehavior) throws CapacityUnderflowException, CapacityOverflowException {
		super(city, personsInvolved, goodStock, transportBehavior);
	}

}
