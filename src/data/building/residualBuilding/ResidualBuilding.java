package data.building.residualBuilding;

import java.util.List;

import model.behaviors.transport.ITransportBehavior;
import data.building.Building;
import data.city.City;
import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;
import data.goods.GoodStock;
import data.person.Person;
import data.person.SocialStatus;

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
