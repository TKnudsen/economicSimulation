package model.behaviors.buy;

import java.util.Map;

import dataStructures.building.publicBuilding.Market;
import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.goods.Good;
import dataStructures.goods.GoodStock;
import dataStructures.person.Person;

public class BuyBehaviorInstantForASetOfGoods implements IBuyBehavior {

	private Market market;
	private Person person;
	private GoodStock goodStock;
	private Map<Good, Double> goodsToBuy;

	public BuyBehaviorInstantForASetOfGoods(Market market, Person person, GoodStock goodStock, Map<Good, Double> goodsToBuy) {
		this.market = market;
		this.person = person;
		this.goodStock = goodStock;
		this.goodsToBuy = goodsToBuy;
	}

	@Override
	public void execute() throws CapacityUnderflowException, CapacityOverflowException {
		for (Good good : goodsToBuy.keySet())
			if (goodsToBuy.get(good) > goodStock.getStock(good))
				market.buyFromMarket(good, goodsToBuy.get(good) - goodStock.getStock(good), person, goodStock);
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public GoodStock getGoodStock() {
		return goodStock;
	}

	public void setGoodStock(GoodStock goodStock) {
		this.goodStock = goodStock;
	}

	public Map<Good, Double> getGoodsToBuy() {
		return goodsToBuy;
	}

	public void setGoodsToBuy(Map<Good, Double> goodsToBuy) {
		this.goodsToBuy = goodsToBuy;
	}
}
