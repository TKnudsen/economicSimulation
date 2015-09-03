package dataStructures.building.workBuilding.rawMaterials;

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

public class Mill extends WorkBuilding {

	public Mill(City city, Person owner, List<Person> personsInvolved, GoodStock goodStock, ITransportBehavior transportBehavior) throws CapacityUnderflowException, CapacityOverflowException {
		super(city, owner, personsInvolved, goodStock, transportBehavior);
	}

	@Override
	protected void initializeBaseProduction() {
		baseProduction = 6;
	}

	@Override
	public Good getGoodType() {
		return Good.Flour;
	}

	@Override
	public Map<Good, Double> getGoodConsumptionForOneUnit() {
		Map<Good, Double> ret = new HashMap<Good, Double>();
		ret.put(Good.Grain, 1.0);
		return ret;
	}

	@Override
	public Demand predictDemandDaily(Person person) {
		Demand demand = new Demand();
		try {
			demand.getGoodDemand().addGood(Good.Grain, predictProductionQuantity(person));
		} catch (CapacityOverflowException e) {
			e.printStackTrace();
		}
		return demand;
	}

	@Override
	protected void initializeBuildingType() {
		buildingType = BuildingType.Mill;
	}
}
