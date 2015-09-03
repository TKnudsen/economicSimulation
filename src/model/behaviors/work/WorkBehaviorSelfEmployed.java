package model.behaviors.work;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.behaviors.buy.BuyBehaviorInstantForASetOfGoods;
import model.behaviors.buy.IBuyBehavior;
import model.behaviors.sell.ISellBehavior;
import model.behaviors.sell.SellBehaviorInstantForASetOfGoods;
import data.building.workBuilding.WorkBuilding;
import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;
import data.goods.Good;
import data.person.Person;

public class WorkBehaviorSelfEmployed implements IWorkBehavior {

	Person person;
	WorkBuilding workBuilding;
	IBuyBehavior buyBehavior;
	ISellBehavior sellBehavior;

	public WorkBehaviorSelfEmployed(Person person, WorkBuilding workBuilding) {
		this.person = person;
		this.workBuilding = workBuilding;

		double output = workBuilding.predictProductionQuantity();
		Map<Good, Double> goodConsumption = workBuilding.getGoodConsumptionForOneUnit();
		for (Good good : goodConsumption.keySet())
			goodConsumption.put(good, goodConsumption.get(good) * output);
		this.buyBehavior = new BuyBehaviorInstantForASetOfGoods(workBuilding.getCity().getMarket(), person, workBuilding.getGoodStock(), goodConsumption);

		Set<Good> goodsToSell = new HashSet<Good>();
		goodsToSell.add(workBuilding.getGoodType());
		this.sellBehavior = new SellBehaviorInstantForASetOfGoods(workBuilding.getCity().getMarket(), person, workBuilding.getGoodStock(), goodsToSell);
	}

	@Override
	public void execute() {
		try {
			double output = workBuilding.predictProductionQuantity();
			Map<Good, Double> goodConsumption = workBuilding.getGoodConsumptionForOneUnit();
			for (Good good : goodConsumption.keySet())
				goodConsumption.put(good, goodConsumption.get(good) * output);

			((BuyBehaviorInstantForASetOfGoods) buyBehavior).setGoodsToBuy(goodConsumption);

			buyBehavior.execute();
		} catch (CapacityUnderflowException | CapacityOverflowException e) {
			e.printStackTrace();
		}

		workBuilding.process(person);

		sellBehavior.execute();
	}

}
