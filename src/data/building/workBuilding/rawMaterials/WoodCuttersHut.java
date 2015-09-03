package data.building.workBuilding.rawMaterials;

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

public class WoodCuttersHut extends WorkBuilding {

	public WoodCuttersHut(City city, Person owner, List<Person> personsInvolved, GoodStock goodStock, ITransportBehavior transportBehavior) throws CapacityUnderflowException, CapacityOverflowException {
		super(city, owner, personsInvolved, goodStock, transportBehavior);
	}

	@Override
	protected void initializeBaseProduction() {
		baseProduction = 6;
	}

	@Override
	public Good getGoodType() {
		return Good.Wood;
	}

	@Override
	public Demand predictDemandDaily(Person person) {
		Demand demand = new Demand();
		return demand;
	}

	@Override
	protected void initializeBuildingType() {
		buildingType = BuildingType.WoodCuttersHouse;
	}

	@Override
	public Map<Good, Double> getGoodConsumptionForOneUnit() {
		return new HashMap<Good, Double>();
	}
}
