package data.building.workBuilding;

import java.util.List;
import java.util.Map;

import model.behaviors.transport.ITransportBehavior;
import data.building.Building;
import data.city.City;
import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;
import data.goods.Good;
import data.goods.GoodStock;
import data.person.Person;

public abstract class WorkBuilding extends Building {

	private Person owner;
	protected double baseProduction;

	public WorkBuilding(City city, Person owner, List<Person> personsInvolved, GoodStock goodStock, ITransportBehavior transportBehavior) throws CapacityUnderflowException, CapacityOverflowException {
		super(city, personsInvolved, goodStock, transportBehavior);
		this.owner = owner;
		owner.setWorkBuilding(this);
		initializeBaseProduction();
	}

	protected abstract void initializeBaseProduction();

	public abstract Good getGoodType();

	public abstract Map<Good, Double> getGoodConsumptionForOneUnit();

	public Map<Good, Double> getGoodConsumption(Person person) {
		double output = predictProductionQuantity(person);
		Map<Good, Double> goodConsumption = getGoodConsumptionForOneUnit();
		for (Good good : goodConsumption.keySet())
			goodConsumption.put(good, goodConsumption.get(good) * output);
		return goodConsumption;
	}

	public double predictProductionQuantity(Person person) {
		return person.getJobSkill(person.getJob().getJobType()) * baseProduction * 0.01;
	}

	public double predictProductionQuantity() {
		double quant = 0;
		for (Person p : getPersonsInvolved())
			quant += predictProductionQuantity(p);
		return quant;
	}

	public void process(Person person) {
		try {
			// calculate the number of units which will be produced.
			// variables: player skill and available resources.
			Map<Good, Double> goodConsumption = getGoodConsumption(person);
			double ratio = 1.0;
			for (Good good : goodConsumption.keySet())
				ratio = Math.min(ratio, (goodStock.getStock(good) / goodConsumption.get(good)));

			// remove resources
			for (Good good : goodConsumption.keySet())
				try {
					goodStock.removeGood(good, goodConsumption.get(good) * ratio);
				} catch (CapacityUnderflowException e) {
					e.printStackTrace();
				}

			// add product
			goodStock.addGood(getGoodType(), predictProductionQuantity(person) * ratio);

			// improve person skill/exp
			person.getJobSkills().addToJobSkill(person.getJob().getJobType(), gainWorkExperience(person.getJobSkill(person.getJob().getJobType())));
		} catch (CapacityOverflowException e) {
			// TODO we need some smart solution for overflows. maybe we can
			// notify the owner?!

			// V1 fill until full
			try {
				goodStock.addGood(getGoodType(), goodStock.getRemainingCapacity());
			} catch (CapacityOverflowException e1) {
				// this will never happen
				e1.printStackTrace();
			}
		}
	}

	private static double gainWorkExperience(double currentSkill) {
		double scale = 0.08 * currentSkill * 0.01 * currentSkill * 0.01 - 0.908 * currentSkill * 0.01 + 3.008;
		scale *= 0.0005 * scale;
		// System.out.println("A player increased the work exp from " +
		// currentSkill + " by " + scale);
		return scale;
	}

	public double getBaseProduction() {
		return baseProduction;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}
}
