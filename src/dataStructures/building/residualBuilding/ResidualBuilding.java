package dataStructures.building.residualBuilding;

import java.util.List;

import model.behaviors.transport.ITransportBehavior;
import dataStructures.building.Building;
import dataStructures.city.City;
import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.goods.GoodStock;
import dataStructures.person.Person;
import dataStructures.person.SocialStatus;

public abstract class ResidualBuilding extends Building {

	private Person owner;

	public ResidualBuilding(City city, Person owner, List<Person> personsInvolved, GoodStock goodStock, ITransportBehavior transportBehavior) throws CapacityUnderflowException, CapacityOverflowException {
		super(city, personsInvolved, goodStock, transportBehavior);
		this.owner = owner;
	}

	public abstract SocialStatus getSocialStatus();

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

}
