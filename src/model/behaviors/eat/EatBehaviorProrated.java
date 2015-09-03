package model.behaviors.eat;

import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.exceptions.HungerException;
import dataStructures.goods.Good;
import dataStructures.goods.GoodClass;
import dataStructures.goods.GoodTools;
import dataStructures.person.Person;

public class EatBehaviorProrated implements IEatBehavior {

	@Override
	public void execute(Person person) throws HungerException {
		double foodAmount = 0;
		for (Good good : person.getResidualBuilding().getGoodStock().getStock().keySet())
			if (GoodTools.getGoodClass(good).equals(GoodClass.Food))
				foodAmount += person.getResidualBuilding().getGoodStock().getStock().get(good);

		double ratioOfStockToEat = foodAmount / person.getDemandDaily().getFoodDemandDaily();

		if (ratioOfStockToEat < 1) {
			for (Good good : person.getResidualBuilding().getGoodStock().getStock().keySet())
				if (GoodTools.getGoodClass(good).equals(GoodClass.Food))
					person.getResidualBuilding().getGoodStock().getStock().put(good, 0.0);

			// suffering pain...
			ratioOfStockToEat = Math.pow(ratioOfStockToEat, 0.25);
			person.setHealth(person.getHealth() - (1-ratioOfStockToEat));

			System.out.println("Person " + person + " needs to go to bed hungry.");
//			throw new HungerException(person);
		} else {
			ratioOfStockToEat = 1 / ratioOfStockToEat;
			for (Good good : person.getResidualBuilding().getGoodStock().getStock().keySet())
				if (GoodTools.getGoodClass(good).equals(GoodClass.Food))
					try {
						person.getResidualBuilding().getGoodStock().removeGood(good, person.getResidualBuilding().getGoodStock().getStock().get(good) * (1.0 / ratioOfStockToEat));
					} catch (CapacityUnderflowException e) {
						e.printStackTrace();
					}

			person.setHealth(Math.min(1.0, person.getHealth() + 0.05));
		}
	}
}
