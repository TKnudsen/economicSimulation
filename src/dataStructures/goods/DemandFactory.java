package dataStructures.goods;

import dataStructures.building.Building;
import dataStructures.city.City;
import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.person.Person;

public class DemandFactory {
	public static Demand calculateDemandDailyForPerson(Person person) {
		Demand demand = new Demand();

		try {
			demand.getGoodDemand().addGood(Good.Fish, 0.5);
			demand.getGoodDemand().addGood(Good.Potato, 0.5);
			demand.getGoodDemand().addGood(Good.Corn, 0.5);
			demand.getGoodDemand().addGood(Good.Wood, 0.25);
		} catch (CapacityOverflowException e) {
			e.printStackTrace();
		}

		return demand;
	}

	public static Demand calculateDemandDailyForBuilding(Building building) {
		Demand demand = new Demand();

		if (building.getPersonsInvolved() != null)
			for (Person p : building.getPersonsInvolved()) {
				Demand d = building.predictDemandDaily(p);
				for (Good good : d.getGoodDemand().getStock().keySet())
					try {
						demand.getGoodDemand().addGood(good, demand.getGoodDemand().getStock(good) + d.getGoodDemand().getStock(good));
					} catch (CapacityOverflowException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		return demand;
	}

	public static Demand calculateDemandDailyForCity(City city) {

		Demand demand = new Demand();

		for (Person p : city.getPersons()) {
			Demand d = calculateDemandDailyForPerson(p);
			for (Good good : d.getGoodDemand().getStock().keySet())
				try {
					demand.getGoodDemand().addGood(good, demand.getGoodDemand().getStock(good) + d.getGoodDemand().getStock(good));
				} catch (CapacityOverflowException e) {
					e.printStackTrace();
				}
		}

		for (Building b : city.getBuildings()) {
			Demand d = calculateDemandDailyForBuilding(b);
			for (Good good : d.getGoodDemand().getStock().keySet())
				try {
					demand.getGoodDemand().addGood(good, demand.getGoodDemand().getStock(good) + d.getGoodDemand().getStock(good));
				} catch (CapacityOverflowException e) {
					e.printStackTrace();
				}
		}

		return demand;
	}
}
