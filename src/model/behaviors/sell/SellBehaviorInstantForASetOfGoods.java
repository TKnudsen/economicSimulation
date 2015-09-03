package model.behaviors.sell;

import java.util.Set;

import dataStructures.building.Building;
import dataStructures.building.publicBuilding.Market;
import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.goods.Good;
import dataStructures.goods.GoodStock;
import dataStructures.person.Person;

public class SellBehaviorInstantForASetOfGoods implements ISellBehavior {

	private Market market;
	private Person person;
	private GoodStock goodStock;
	private Set<Good> goodsToSell;

	public SellBehaviorInstantForASetOfGoods(Market market, Person person, GoodStock goodStock, Set<Good> goodsToSell) {
		this.market = market;
		this.person = person;
		this.goodStock = goodStock;
		this.goodsToSell = goodsToSell;
	}

	@Override
	public void execute() {
		for (Good good : goodsToSell)
			if (goodStock.getStock(good) > 0)
				try {
					market.sellToMarket(good, goodStock.getStock(good), person, goodStock);
				} catch (CapacityUnderflowException | CapacityOverflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
