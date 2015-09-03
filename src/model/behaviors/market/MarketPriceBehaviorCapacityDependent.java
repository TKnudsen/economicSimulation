package model.behaviors.market;

import data.building.publicBuilding.Market;
import data.goods.Good;
import data.goods.GoodTools;

public class MarketPriceBehaviorCapacityDependent implements IMarketPriceBehavior {

	Market market;

	public MarketPriceBehaviorCapacityDependent(Market market) {
		super();
		this.market = market;
	}

	@Override
	public double getPrice(Good good) {

		// define max
		double targetStockPerGood = market.getGoodStock().getCapacity() / (double) Good.values().length;

		// calculate utilization. strategy: 100% targetStockPerc (ratio 0.5)
		// means ideal ~ reference price
		double ratio = market.getGoodStock().getStock(good) / targetStockPerGood;

		double value = GoodTools.getReferencePrice(good) * (0.006 * ratio * 100 * ratio * 100 - 2.676 * ratio * 100 + 299.1) * 0.01;
		value = (value + 3 * GoodTools.getReferencePrice(good)) * 0.25;
		return value;
	}

}
