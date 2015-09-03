package model.behaviors.market;

import dataStructures.building.publicBuilding.Market;
import dataStructures.goods.Demand;
import dataStructures.goods.Good;
import dataStructures.goods.GoodTools;

public class MarketBehaviorDemandDependent implements IMarketPriceBehavior {

	private Market market;
	private Demand demand;

	public MarketBehaviorDemandDependent(Market market, Demand demand) {
		this.market = market;
		this.demand = demand;
	}

	public double getPrice(Good good) {
		double ratio = 0;
		if (demand.getGoodDemand().getStock(good) > 0)
			ratio = market.getGoodStock().getStock(good) / demand.getGoodDemand().getStock(good);

		ratio = Math.min(10.0, ratio);
		ratio = Math.max(0.0, ratio);

		// ratio <=1
		if (ratio < 1)
			ratio = 2.267 * Math.exp(-0.81 * ratio);
		else if (ratio > 1)
			ratio = 0.006 * ratio * ratio - 0.124 * ratio + 1.112;

		return GoodTools.getReferencePrice(good) * ratio;
	}

	public Demand getDemand() {
		return demand;
	}

	public void setDemand(Demand demand) {
		this.demand = demand;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

}
