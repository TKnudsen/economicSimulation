package data.building;

import java.util.List;
import java.util.Map;

import model.behaviors.transport.ITransportBehavior;
import data.city.City;
import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;
import data.goods.Demand;
import data.goods.Good;
import data.goods.GoodStock;
import data.person.Person;

public abstract class Building {

	protected BuildingType buildingType;
	protected Map<Good, Double> requiredGoodsForBuilding;
	protected double buildingCosts;
	protected GoodStock goodStock;
	private City city;
	private List<Person> personsInvolved;

	public Building(City city, List<Person> personsInvolved, GoodStock goodStock, ITransportBehavior transportBehavior) throws CapacityUnderflowException, CapacityOverflowException {
		this.city = city;
		this.personsInvolved = personsInvolved;
		initializeBuildingType();
		initializeGoodStock();
		initializeRequiredGoodsForBuilding();
		initializeBuildingCosts();

		for (Good good : requiredGoodsForBuilding.keySet())
			transportBehavior.checkTransport(good, requiredGoodsForBuilding.get(good), goodStock, this.getGoodStock());
		for (Good good : requiredGoodsForBuilding.keySet())
			transportBehavior.transport(good, requiredGoodsForBuilding.get(good), goodStock, this.getGoodStock());

		city.addBuilding(this);
	}

	protected void initializeGoodStock() {
		this.goodStock = new GoodStock(BuildingTools.getGoodStockCapacity(getBuildingType()));
	}

	public abstract Demand predictDemandDaily(Person person);

	protected abstract void initializeBuildingType();

	protected void initializeRequiredGoodsForBuilding() {
		requiredGoodsForBuilding = BuildingTools.getRequiredGoodsForBuilding(getBuildingType());
	}

	protected void initializeBuildingCosts() {
		buildingCosts = BuildingTools.getBuildingCosts(getBuildingType());
	}

	public void increaseCapacity(double additionalCapacity) {
		goodStock.increaseCapacity(additionalCapacity);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	// ////////////////////////////////
	public GoodStock getGoodStock() {
		return goodStock;
	}

	public void setGoodStock(GoodStock goodStock) {
		this.goodStock = goodStock;
	}

	public Map<Good, Double> getRequiredGoodsForBuilding() {
		return requiredGoodsForBuilding;
	}

	public double getBuildingCosts() {
		return buildingCosts;
	}

	public City getCity() {
		return city;
	}

	public List<Person> getPersonsInvolved() {
		return personsInvolved;
	}

	public void setPersonsInvolved(List<Person> personsInvolved) {
		this.personsInvolved = personsInvolved;
	}

	public BuildingType getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}
}
