package model.behaviors.work;

import java.util.Map;

import data.building.BuildingFactory;
import data.building.BuildingTools;
import data.city.City;
import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;
import data.exceptions.GoodStockTooSmallException;
import data.exceptions.MoneyTooSmallException;
import data.goods.Good;
import data.goods.GoodTools;
import data.job.Job;
import data.job.JobFactory;
import data.job.JobTools;
import data.job.Jobtype;
import data.person.Person;

public class WorkBehaviorIdentifyNewJob implements IWorkBehavior {

	private Person person;
	private City city;

	private boolean considerMarketPrices = false;

	public WorkBehaviorIdentifyNewJob(Person person, City city, boolean considerMarketPrices) {
		this.considerMarketPrices = considerMarketPrices;
		this.person = person;
		this.city = city;
	}

	@Override
	public void execute() {

		Jobtype jobtype = null;
		if (!considerMarketPrices)
			jobtype = person.getJobSkills().getMaximumJobSkill();
		else {
			double max = Double.MIN_VALUE;
			for (Jobtype job : person.getJobSkills().getJobSkills().keySet()) {
				double priceRatio = city.getMarket().getSellPrice(JobTools.lookupGoodType(job)) / GoodTools.getReferencePrice(JobTools.lookupGoodType(job));
				if (person.getJobSkills().getJobSkills().get(job) * priceRatio > max) {
					jobtype = job;
					max = person.getJobSkills().getJobSkills().get(job) * priceRatio;
				}
			}
		}

		Job job = JobFactory.createJob(jobtype, person);

		// gather resources for a work building
		Map<Good, Double> requiredGoodsForBuilding = BuildingTools.getRequiredGoodsForBuilding(jobtype);

		for (Good good : requiredGoodsForBuilding.keySet())
			try {
				person.getInventory().addGood(good, person.getResidualBuilding().getGoodStock().getStock(good));
			} catch (CapacityOverflowException e2) {
				System.out.println(e2.getMessage());
			}

		for (Good good : requiredGoodsForBuilding.keySet())
			if (requiredGoodsForBuilding.get(good) > person.getInventory().getStock(good))
				try {
					city.getMarket().buyFromMarket(good, requiredGoodsForBuilding.get(good) - person.getInventory().getStock(good), person, person.getInventory());
				} catch (CapacityUnderflowException | CapacityOverflowException e) {
					// e.printStackTrace();
					System.out.println(e.getMessage());
				}
		
		try {
			BuildingFactory.createWorkBuilding(city, jobtype, person, person.getInventory());
			person.setJob(job);
		} catch (GoodStockTooSmallException | MoneyTooSmallException | CapacityUnderflowException | CapacityOverflowException e) {
			e.printStackTrace();
		}
	}

	public boolean isConsiderMarketPrices() {
		return considerMarketPrices;
	}

	public void setConsiderMarketPrices(boolean considerMarketPrices) {
		this.considerMarketPrices = considerMarketPrices;
	}
}
