package dataStructures.goods;

import dataStructures.exceptions.CapacityOverflowException;

public class GoodStockFactory {
	public static GoodStock createGoodStock(double amountOfGoodsEach) {
		GoodStock goodStock = new GoodStock(amountOfGoodsEach * Good.values().length * 2);
		for (Good good : Good.values()) {
			try {
				goodStock.addGood(good, amountOfGoodsEach);
			} catch (CapacityOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return goodStock;
	}
}
