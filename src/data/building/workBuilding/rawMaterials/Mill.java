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
