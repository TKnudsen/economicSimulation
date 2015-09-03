package model.behaviors.buy;

import dataStructures.building.publicBuilding.Market;
import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.goods.Good;
import dataStructures.person.Person;

public class BuyBehaviorInstant implements IBuyBehavior {

	private Market market;
	private Person person;

	public BuyBehaviorInstant(Market market, Person person) {
		this.market = market;
		this.person = person;
	}

	@Override
	public void execute() throws CapacityUnderflowException, CapacityOverflowException {

		for (Good good : person.getDemandDaily().getGoodDemand().getStock().keySet()) {
			if (person.getDemandDaily().getGoodDemand().getStock(good) > 0) {
				double demand = person.getDemandDaily().getGoodDemand().getStock(good);
				// TODO: what to do with homeless people?
				if (person.getResidualBuilding() != null)
					demand -= person.getResidualBuilding().getGoodStock().getStock(good);
				demand -= person.getInventory().getStock(good);
				if (demand > 0)
					if (person.getMoney() > 0)
						market.buyFromMarket(good, demand, person, person.getInventory());
			}
		}
	}
}
