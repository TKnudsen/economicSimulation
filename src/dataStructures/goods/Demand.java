package dataStructures.goods;

public class Demand {

	private GoodStock goodDemand = new GoodStock(Double.MAX_VALUE);

	// private ResidualBuilding residualBuilding;
	// private SocialStatus socialStatus;

	public double getFoodDemandDaily() {
		double foodAmount = 0;
		for (Good good : goodDemand.getStock().keySet())
			if (GoodTools.getGoodClass(good).equals(GoodClass.Food))
				foodAmount += goodDemand.getStock().get(good);
		return foodAmount;
	}

	public GoodStock getGoodDemand() {
		return goodDemand;
	}

	public void setGoodDemand(GoodStock goodDemand) {
		this.goodDemand = goodDemand;
	}
}
