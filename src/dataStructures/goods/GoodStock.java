package dataStructures.goods;

import java.util.HashMap;
import java.util.Map;

import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;

public class GoodStock {

	private double capacity;
	private Map<Good, Double> stock;

	public GoodStock(double capacity) {
		this.capacity = capacity;
		stock = new HashMap<Good, Double>();
	}

	public GoodStock(double capacity, HashMap<Good, Double> stock) {
		this.capacity = capacity;
		this.stock = stock;
	}

	// ///////////////
	public void addGood(Good good, double amount) throws CapacityOverflowException {
		double testCapacity = getGoodStockAmount() + amount;
		if (testCapacity > capacity)
			throw new CapacityOverflowException(testCapacity, getCapacity());

		if (stock.get(good) == null)
			stock.put(good, amount);
		else
			stock.put(good, stock.get(good) + amount);
	}

	public void removeGood(Good good, double amount) throws CapacityUnderflowException {
		if (stock.get(good) == null)
			throw new CapacityUnderflowException(-amount);

		double testCapacity = stock.get(good) - amount;
		if (testCapacity < 0)
			throw new CapacityUnderflowException(testCapacity);

		stock.put(good, stock.get(good) - amount);
	}

	private double getGoodStockAmount() {
		double d = 0;
		for (Good good : stock.keySet())
			d += stock.get(good);
		return d;
	}

	@Override
	public String toString() {
		String s = "GoodStock: max capacity: " + capacity + " \n";
		for (Good good : stock.keySet())
			s += good.toString() + ": " + stock.get(good) + "\n";
		return s;
	}

	public double getRemainingCapacity() {
		return capacity - getGoodStockAmount();
	}

	public Map<Good, Double> getStock() {
		return stock;
	}

	public double getStock(Good good) {
		if (stock.get(good) == null)
			return 0;
		return stock.get(good);
	}

	public void setStock(Map<Good, Double> stock) {
		this.stock = stock;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) throws CapacityOverflowException {
		double testCapacity = getGoodStockAmount() + this.capacity;
		if (testCapacity > capacity)
			throw new CapacityOverflowException(testCapacity, getCapacity());

		this.capacity = capacity;
	}

	public void increaseCapacity(double additionalCapacity) {
		this.capacity += additionalCapacity;
	}
}
