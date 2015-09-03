package dataStructures.person;

import model.behaviors.buy.BuyBehaviorInstant;
import model.behaviors.buy.IBuyBehavior;
import model.behaviors.consume.ConsumeBehaviorBalanced;
import model.behaviors.consume.IConsumeBehavior;
import model.behaviors.eat.EatBehaviorProrated;
import model.behaviors.eat.IEatBehavior;
import model.behaviors.live.ILivingBehavior;
import model.behaviors.live.LivingBehaviorStatusOriented;
import model.behaviors.sell.ISellBehavior;
import model.behaviors.transport.ITransportBehavior;
import model.behaviors.transport.TransportBehaviorInstant;
import model.behaviors.work.IWorkBehavior;
import model.behaviors.work.WorkBehaviorIdentifyNewJob;
import model.behaviors.work.WorkBehaviorSelfEmployed;
import dataStructures.building.residualBuilding.ResidualBuilding;
import dataStructures.building.workBuilding.WorkBuilding;
import dataStructures.city.City;
import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.exceptions.HungerException;
import dataStructures.exceptions.MoneyTooSmallException;
import dataStructures.goods.Demand;
import dataStructures.goods.DemandFactory;
import dataStructures.goods.Good;
import dataStructures.goods.GoodStock;
import dataStructures.job.Job;
import dataStructures.job.Jobtype;
import dataStructures.publicActivity.PublicActivity;

public class Person {

	// constant variables
	private String firstName;
	private String surName;
	private Double dateOfBirth;
	private Talents talents;

	private City city;

	// dynamic variables
	private Demand demandDaily;
	// private double eatUnitsDaily = 2.0;
	// private double consumeUnitsDaily = 2.0;
	private double money;
	private SocialStatus socialStatus = SocialStatus.Pioneer;
	private double socialScore;
	private double health = 1.0;
	private ResidualBuilding residualBuilding;
	private PublicActivity publicActivity;
	private Job job;
	private WorkBuilding workBuilding;
	private JobSkills jobSkills;
	private GoodStock inventory = new GoodStock(100);

	// behaviors
	ILivingBehavior livingBehavior;
	IBuyBehavior buyBehavior;
	private ITransportBehavior transportBehavior;
	ISellBehavior sellBehavior;
	IEatBehavior eatBehavior;
	IWorkBehavior workBehavior;
	IConsumeBehavior consumeBehavior;

	public Person(City city) {
		this.city = city;
		initialize();
	}

	private void initialize() {
		talents = TalentsFactory.createTalents();
		jobSkills = JobSkillsFactory.createJobSkills(talents);

		livingBehavior = new LivingBehaviorStatusOriented(city, this);
		transportBehavior = new TransportBehaviorInstant();
		buyBehavior = new BuyBehaviorInstant(city.getMarket(), this);
		eatBehavior = new EatBehaviorProrated();
		workBehavior = new WorkBehaviorIdentifyNewJob(this, city, true);
		// sellBehavior = null;
		consumeBehavior = new ConsumeBehaviorBalanced();
	}

	public void simulateDay() {
		refreshDemand();

		livingBehavior.execute();

		try {
			buyBehavior.execute();
		} catch (CapacityUnderflowException | CapacityOverflowException e) {
			e.printStackTrace();
		}

		// introduce new behavior for storing goods between inventory and
		// ResidualBuilding?
		for (Good good : inventory.getStock().keySet())
			try {
				transportBehavior.transport(good, inventory.getStock(good), inventory, getResidualBuilding().getGoodStock());
			} catch (CapacityUnderflowException | CapacityOverflowException e1) {
				e1.printStackTrace();
			}
		
		try {
			eatBehavior.execute(this);
		} catch (HungerException e) {
			e.printStackTrace();
		}

		workBehavior.execute();
		if (workBehavior instanceof WorkBehaviorIdentifyNewJob && job != null && workBuilding != null) {
			workBehavior = new WorkBehaviorSelfEmployed(this, getWorkBuilding());
		}

		// if (sellBehavior != null)
		// sellBehavior.execute();

		consumeBehavior.execute();
	}

	@Override
	public String toString() {
		String s = this.getClass().getSimpleName();
		if (getResidualBuilding() != null)
			s += ", Habitat: " + getResidualBuilding().getClass().getSimpleName();
		if (getJob() != null)
			s += ",  Job: " + getJob().getClass().getSimpleName();
		return s;
	}

	private void refreshDemand() {
		demandDaily = DemandFactory.calculateDemandDailyForPerson(this);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public Double getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Double dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Talents getTalents() {
		return talents;
	}

	public void setTalents(Talents talents) {
		this.talents = talents;
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

	public void removeMoney(double amount) throws MoneyTooSmallException {
		if (this.money < amount)
			throw new MoneyTooSmallException();
		else
			this.money -= amount;
	}

	public SocialStatus getSocialStatus() {
		return socialStatus;
	}

	public void setSocialStatus(SocialStatus socialStatus) {
		this.socialStatus = socialStatus;
	}

	public double getSocialScore() {
		return socialScore;
	}

	public void setSocialScore(double socialScore) {
		this.socialScore = socialScore;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public ResidualBuilding getResidualBuilding() {
		return residualBuilding;
	}

	public void setResidualBuilding(ResidualBuilding residualBuilding) {
		this.residualBuilding = residualBuilding;
	}

	public PublicActivity getPublicActivity() {
		return publicActivity;
	}

	public void setPublicActivity(PublicActivity publicActivity) {
		this.publicActivity = publicActivity;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public JobSkills getJobSkills() {
		return jobSkills;
	}

	public Double getJobSkill(Jobtype jobtype) {
		return jobSkills.getJobSkill(jobtype);
	}

	public void setJobSkills(JobSkills jobSkills) {
		this.jobSkills = jobSkills;
	}

	public WorkBuilding getWorkBuilding() {
		return workBuilding;
	}

	public void setWorkBuilding(WorkBuilding workBuilding) {
		this.workBuilding = workBuilding;
	}

	public Demand getDemandDaily() {
		refreshDemand();
		return demandDaily;
	}

	public void setDemandDaily(Demand demandDaily) {
		this.demandDaily = demandDaily;
	}

	public GoodStock getInventory() {
		return inventory;
	}

	public void setInventory(GoodStock inventory) {
		this.inventory = inventory;
	}

	public ITransportBehavior getTransportBehavior() {
		return transportBehavior;
	}

	public void setTransportBehavior(ITransportBehavior transportBehavior) {
		this.transportBehavior = transportBehavior;
	}
}
