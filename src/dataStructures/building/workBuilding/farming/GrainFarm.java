package dataStructures.building.workBuilding.farming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.behaviors.transport.ITransportBehavior;
import dataStructures.building.BuildingType;
import dataStructures.building.workBuilding.WorkBuilding;
import dataStructures.city.City;
import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.goods.Demand;
import dataStructures.goods.Good;
import dataStructures.goods.GoodStock;
import dataStructures.person.Person;

public class GrainFarm extends WorkBuilding {

	public GrainFarm(City city, Person owner, List<Person> personsInvolved, GoodStock goodStock, ITransportBehavior transportBehavior) throws CapacityUnderflowException, CapacityOverflowException {
		super(city, owner, personsInvolved, goodStock, transportBehavior);
	}

	@Override
	protected void initializeBaseProduction() {
		baseProduction = 3;
	}

	@Override
	public Good getGoodType() {
		return Good.Grain;
	}

	@Override
	public Demand predictDemandDaily(Person person) {
		Demand demand = new Demand();
		return demand;
	}

	@Override
	protected void initializeBuildingType() {
		buildingType = BuildingType.GrainFarm;
	}

	@Override
	public Map<Good, Double> getGoodConsumptionForOneUnit() {
		return new HashMap<Good, Double>();
	}

}
