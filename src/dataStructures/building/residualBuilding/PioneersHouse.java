package dataStructures.building.residualBuilding;

import java.util.List;

import model.behaviors.transport.ITransportBehavior;
import dataStructures.building.BuildingType;
import dataStructures.city.City;
import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.goods.Demand;
import dataStructures.goods.GoodStock;
import dataStructures.person.Person;
import dataStructures.person.SocialStatus;

public class PioneersHouse extends ResidualBuilding {

	public PioneersHouse(City city, Person owner, List<Person> personsInvolved, GoodStock goodStock, ITransportBehavior transportBehavior) throws CapacityUnderflowException, CapacityOverflowException {
		super(city, owner, personsInvolved, goodStock, transportBehavior);
	}

	@Override
	public Demand predictDemandDaily(Person person) {
		Demand demand = new Demand();
		return demand;
	}

	@Override
	public SocialStatus getSocialStatus() {
		return SocialStatus.Pioneer;
	}

	@Override
	protected void initializeBuildingType() {
		buildingType = BuildingType.PioneersHouse;
	}
}
