package model.behaviors.buy;

import data.building.publicBuilding.Market;
import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;
import data.goods.Good;
import data.person.Person;

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
