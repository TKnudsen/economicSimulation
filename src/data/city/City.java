package data.city;

import java.util.ArrayList;
import java.util.List;

import data.building.Building;
import data.building.publicBuilding.Market;
import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;
import data.goods.GoodStockFactory;
import data.person.Person;
import data.person.PersonFactory;

public class City {

	private List<Person> persons;
	private List<Building> buildings;
	private Market market;
	private double money;

	public City(double money) {
		this.persons = new ArrayList<Person>();
		this.buildings = new ArrayList<Building>();
		this.money = money;

		try {
			initialize();
		} catch (CapacityUnderflowException | CapacityOverflowException e) {
			e.printStackTrace();
		}
	}

	public City(List<Person> persons, List<Building> buildings, double money) {
		this.persons = persons;
		this.buildings = buildings;
		this.money = money;

		try {
			initialize();
		} catch (CapacityUnderflowException | CapacityOverflowException e) {
			e.printStackTrace();
		}
	}

	private void initialize() throws CapacityUnderflowException, CapacityOverflowException {
		market = new Market(this, null, GoodStockFactory.createGoodStock(100));
		try {
			market.getGoodStock().setCapacity(5000.0);
		} catch (CapacityOverflowException e) {
			e.printStackTrace();
		}
	}

	public void executeDay() {

		for (Person p : persons)
			p.simulateDay();
	}

	public void addPerson() {
		persons.add(PersonFactory.createPerson(this, 1000));
	}

	public void addPerson(double money) {
		persons.add(PersonFactory.createPerson(this, money));
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ", Persons #: " + persons.size() + ", " + persons;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public void addMoney(double amount) {
		this.money += amount;
	}

	public void removeMoney(double amount) {
		// if (this.money < amount)
		// throw new MoneyTooSmallException();
		// else
		this.money -= amount;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public void addBuilding(Building building) {
		buildings.add(building);
	}

}
