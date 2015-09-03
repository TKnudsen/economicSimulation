package application.testing;

import model.behaviors.market.MarketBehaviorDemandDependent;
import data.city.City;
import data.goods.Demand;
import data.goods.DemandFactory;

public class V0_01 {

	public static void main(String[] args) {

		City city = new City(30000.0);

		city.addPerson(2000);
		for (int i = 0; i < 100; i++) {
			if (Math.random() > 0.75)
				city.addPerson(2000);

			if (city.getMarket().getMarketPriceBehavior() instanceof MarketBehaviorDemandDependent) {
				Demand d = DemandFactory.calculateDemandDailyForCity(city);
				((MarketBehaviorDemandDependent) city.getMarket().getMarketPriceBehavior()).setDemand(d);
			}

			city.executeDay();
			// System.out.println(city);
			System.out.println(city.getMarket());
		}
	}
}
