package model.behaviors.market;

import data.city.City;
import data.goods.Good;
import data.goods.GoodTools;

public class MarketPriceBehaviorCitySizeDependent implements IMarketPriceBehavior {

	private City city;

	public MarketPriceBehaviorCitySizeDependent(City city) {
		this.city = city;
	}

	@Override
	public double getPrice(Good good) {
		// preserve a certain amount of every good (for every person
		// and building)
		double money = city.getPersons().size() + city.getBuildings().size();
		money *= 100;
		double ref = GoodTools.getReferencePrice(good);
		double nr = money / ref;
		double ratio = 0;
		if (city.getMarket() != null)
			ratio = city.getMarket().getGoodStock().getStock(good) / nr;

		ratio = Math.min(10.0, ratio);
		ratio = Math.max(0.0, ratio);

		// ratio <=1
		if (ratio < 1)
			ratio = 2.267 * Math.exp(-0.81 * ratio);
		else if (ratio > 1)
			ratio = 0.006 * ratio * ratio - 0.124 * ratio + 1.112;

		return GoodTools.getReferencePrice(good) * ratio;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
