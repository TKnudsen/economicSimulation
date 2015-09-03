package dataStructures.building.workBuilding.woodcraft;

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

public class LumberMill extends WorkBuilding {

	public LumberMill(City city, Person owner, List<Person> personsInvolved, GoodStock goodStock, ITransportBehavior transportBehavior) throws CapacityUnderflowException, CapacityOverflowException {
		super(city, owner, personsInvolved, goodStock, transportBehavior);
	}

	@Override
	protected void initializeBaseProduction() {
		baseProduction = 6;
	}

	@Override
	public Good getGoodType() {
		return Good.Lumber;
	}

	@Override
	public Demand predictDemandDaily(Person person) {
		Demand demand = new Demand();
		try {
			demand.getGoodDemand().addGood(Good.Wood, predictProductionQuantity(person));
		} catch (CapacityOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return demand;
	}

	@Override
	protected void initializeBuildingType() {
		buildingType = BuildingType.LumberMill;
	}

	@Override
	public Map<Good, Double> getGoodConsumptionForOneUnit() {
		Map<Good, Double> ret = new HashMap<Good, Double>();
		ret.put(Good.Wood, 1.0);
		return ret;
	}
}
