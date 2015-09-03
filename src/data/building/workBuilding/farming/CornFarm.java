package data.building.workBuilding.farming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.behaviors.transport.ITransportBehavior;
import data.building.BuildingType;
import data.building.workBuilding.WorkBuilding;
import data.city.City;
import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;
import data.goods.Demand;
import data.goods.Good;
import data.goods.GoodStock;
import data.person.Person;

public class CornFarm extends WorkBuilding {

	public CornFarm(City city, Person owner, List<Person> personsInvolved, GoodStock goodStock, ITransportBehavior transportBehavior) throws CapacityUnderflowException, CapacityOverflowException {
		super(city, owner, personsInvolved, goodStock, transportBehavior);
	}

	@Override
	protected void initializeBaseProduction() {
		baseProduction = 3;
	}

	@Override
	public Good getGoodType() {
		return Good.Corn;
	}

	@Override
	public Demand predictDemandDaily(Person person) {
		Demand demand = new Demand();
		return demand;
	}

	@Override
	protected void initializeBuildingType() {
		buildingType = BuildingType.CornFarm;
	}

	@Override
	public Map<Good, Double> getGoodConsumptionForOneUnit() {
		return new HashMap<Good, Double>();
	}
}
