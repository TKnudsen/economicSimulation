package dataStructures.building.publicBuilding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.behaviors.market.IMarketPriceBehavior;
import model.behaviors.market.MarketPriceBehaviorCitySizeDependent;
import model.behaviors.transport.TransportBehaviorInstant;
import tools.StringTools;
import dataStructures.building.BuildingType;
import dataStructures.city.City;
import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.exceptions.MoneyTooSmallException;
import dataStructures.goods.Demand;
import dataStructures.goods.Good;
import dataStructures.goods.GoodStock;
import dataStructures.goods.GoodTools;
import dataStructures.person.Person;

public class Market extends PublicBuilding {

	private Map<Good, Double> normalPrice;

	private IMarketPriceBehavior marketPriceBehavior;

	public Market(City city, List<Person> personsInvolved, GoodStock goodStock) throws CapacityUnderflowException, CapacityOverflowException {
		super(city, personsInvolved, goodStock, new TransportBehaviorInstant());
		this.goodStock = goodStock;

		// this.marketPriceBehavior = new
		// MarketPriceBehaviorCapacityDependent(this);
		// this.marketPriceBehavior = new MarketBehaviorDemandDependent(this,
		// DemandFactory.calculateDemandDailyForCity(city));
		this.marketPriceBehavior = new MarketPriceBehaviorCitySizeDependent(city);

		initializePrices();
	}

	private void initializePrices() {
		normalPrice = new HashMap<Good, Double>();
		for (Good good : Good.values())
			normalPrice.put(good, marketPriceBehavior.getPrice(good));
	}

	public double getBuyPrice(Good good) {
		return marketPriceBehavior.getPrice(good) * 0.9;
		// return normalPrice.get(good) * 0.9;
	}

	public double getSellPrice(Good good) {
		return marketPriceBehavior.getPrice(good) * 1.1;
		// return normalPrice.get(good) * 1.1;
	}

	public void sellToMarket(Good good, double amount, Person person, GoodStock goodStockSeller) throws CapacityUnderflowException, CapacityOverflowException {
		if (goodStock.getRemainingCapacity() < amount)
			throw new CapacityOverflowException(goodStock.getRemainingCapacity() + amount, goodStock.getCapacity());

		if (goodStockSeller.getStock(good) < amount)
			throw new CapacityUnderflowException(goodStockSeller.getStock(good) - amount);

		while (amount > 0) {
			// pricing
			double amountIter = Math.min(amount, 1.0);
			double priceForOneUnit = getBuyPrice(good) * amountIter;
			getCity().removeMoney(priceForOneUnit);
			person.setMoney(person.getMoney() + priceForOneUnit);

			// good transfer
			person.getTransportBehavior().transport(good, amountIter, goodStockSeller, goodStock);
			// goodStock.addGood(good, amountIter);
			// goodStockSeller.removeGood(good, amountIter);

			// adapt market price
			normalPrice.put(good, marketPriceBehavior.getPrice(good));

			amount -= amountIter;
		}
	}

	public void buyFromMarket(Good good, double amount, Person person, GoodStock goodStockBuyer) throws CapacityUnderflowException, CapacityOverflowException {
		if (goodStockBuyer.getRemainingCapacity() < amount)
			throw new CapacityOverflowException(goodStockBuyer.getRemainingCapacity() - amount, goodStockBuyer.getCapacity());

		if (goodStock.getStock(good) < amount)
			throw new CapacityUnderflowException(goodStock.getStock(good) - amount);

		while (amount > 0 && person.getMoney() > 0) {
			// pricing
			double amountIter = Math.min(amount, 1.0);
			double priceForOneUnit = getSellPrice(good) * amountIter;
			if (person.getMoney() < priceForOneUnit) {
				amountIter = person.getMoney() / getSellPrice(good);
				priceForOneUnit = person.getMoney();
			}

			getCity().addMoney(priceForOneUnit);
			try {
				person.removeMoney(priceForOneUnit);
			} catch (MoneyTooSmallException e) {
				e.printStackTrace();
			}

			// good transfer
			person.getTransportBehavior().transport(good, amountIter, goodStock, goodStockBuyer);
			// goodStockBuyer.addGood(good, amountIter);
			// goodStock.removeGood(good, amountIter);

			// adapt market price
			normalPrice.put(good, marketPriceBehavior.getPrice(good));

			amount -= amountIter;
		}
	}

	@Override
	public String toString() {
		String s = this.getClass().getSimpleName() + ", Money: " + getCity().getMoney() + "\n";
		s += "GoodStock: max capacity: " + StringTools.format(goodStock.getCapacity()) + ", capacity remaining: " + StringTools.format(goodStock.getRemainingCapacity()) + " \n";
		for (Good good : goodStock.getStock().keySet())
			s += good.toString() + ": " + StringTools.format(goodStock.getStock().get(good)) + "\tBuy: " + StringTools.format(getBuyPrice(good)) + "\tSell: " + StringTools.format(getSellPrice(good)) + "\tdefault: " + StringTools.format(GoodTools.getReferencePrice(good)) + "\n";
		return s;
	}

	@Override
	public Demand predictDemandDaily(Person person) {
		return new Demand();
	}

	@Override
	protected void initializeBuildingType() {
		buildingType = BuildingType.Market;
	}

	public IMarketPriceBehavior getMarketPriceBehavior() {
		return marketPriceBehavior;
	}

	public void setMarketPriceBehavior(IMarketPriceBehavior marketPriceBehavior) {
		this.marketPriceBehavior = marketPriceBehavior;
	}
}
