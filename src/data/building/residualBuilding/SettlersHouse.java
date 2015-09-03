package data.building.residualBuilding;

import java.util.List;

import model.behaviors.transport.ITransportBehavior;
import data.building.BuildingType;
import data.city.City;
import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;
import data.goods.Demand;
import data.goods.GoodStock;
import data.person.Person;
import data.person.SocialStatus;

public class SettlersHouse extends ResidualBuilding {

	public SettlersHouse(City city, Person owner, List<Person> personsInvolved, GoodStock goodStock, ITransportBehavior transportBehavior) throws CapacityUnderflowException, CapacityOverflowException {
		super(city, owner, personsInvolved, goodStock, transportBehavior);
	}

	@Override
	public Demand predictDemandDaily(Person person) {
		Demand demand = new Demand();
		return demand;
	}

	@Override
	public SocialStatus getSocialStatus() {
		return SocialStatus.Settler;
	}

	@Override
	protected void initializeBuildingType() {
		buildingType = BuildingType.SettlersHouse;
	}
}
