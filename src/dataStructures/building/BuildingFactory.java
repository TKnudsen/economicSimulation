package dataStructures.building;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dataStructures.building.residualBuilding.PioneersHouse;
import dataStructures.building.residualBuilding.ResidualBuilding;
import dataStructures.building.residualBuilding.SettlersHouse;
import dataStructures.building.workBuilding.WorkBuilding;
import dataStructures.building.workBuilding.animalHunting.FishingLodge;
import dataStructures.building.workBuilding.farming.CornFarm;
import dataStructures.building.workBuilding.farming.GrainFarm;
import dataStructures.building.workBuilding.farming.PotatoFarm;
import dataStructures.building.workBuilding.rawMaterials.Mill;
import dataStructures.building.workBuilding.rawMaterials.WoodCuttersHut;
import dataStructures.building.workBuilding.woodcraft.LumberMill;
import dataStructures.city.City;
import dataStructures.exceptions.CapacityOverflowException;
import dataStructures.exceptions.CapacityUnderflowException;
import dataStructures.exceptions.GoodStockTooSmallException;
import dataStructures.exceptions.MoneyTooSmallException;
import dataStructures.goods.Good;
import dataStructures.goods.GoodStock;
import dataStructures.job.Jobtype;
import dataStructures.person.Person;
import dataStructures.person.SocialStatus;

public class BuildingFactory {

	public static ResidualBuilding createResidualBuilding(SocialStatus socialStatus, City city, Person person, GoodStock goodStock) throws GoodStockTooSmallException, MoneyTooSmallException, CapacityUnderflowException, CapacityOverflowException {
		List<Person> involved = new ArrayList<Person>();
		involved.add(person);
		return createResidualBuilding(socialStatus, city, person, goodStock, involved);
	}

	public static ResidualBuilding createResidualBuilding(SocialStatus socialStatus, City city, Person person, GoodStock goodStock, List<Person> personsInvolved) throws GoodStockTooSmallException, MoneyTooSmallException, CapacityUnderflowException, CapacityOverflowException {

		Map<Good, Double> requiredGoodsForBuilding = BuildingTools.getRequiredGoodsForBuilding(socialStatus);

		// check resources
		if (BuildingTools.getBuildingCosts(socialStatus) > person.getMoney())
			throw new MoneyTooSmallException();

		for (Good good : requiredGoodsForBuilding.keySet())
			if (requiredGoodsForBuilding.get(good) > goodStock.getStock(good))
				throw new GoodStockTooSmallException("BuildingFactory.createResidualBuilding");

		// // subtract resources - is covered by the transport behavior
		// for (Good good : requiredGoodsForBuilding.keySet())
		// try {
		// goodStock.removeGood(good, requiredGoodsForBuilding.get(good));
		// } catch (CapacityUnderflowException e) {
		// e.printStackTrace();
		// }

		person.removeMoney(BuildingTools.getBuildingCosts(socialStatus));

		// build building
		ResidualBuilding residualBuilding = null;

		switch (socialStatus) {
		case Pioneer:
			residualBuilding = new PioneersHouse(city, person, personsInvolved, person.getInventory(), person.getTransportBehavior());
			person.setResidualBuilding(residualBuilding);
			break;
		case Settler:
			residualBuilding = new SettlersHouse(city, person, personsInvolved, person.getInventory(), person.getTransportBehavior());
			break;
		default:
			throw new IllegalArgumentException("BuildingFactory.getRequiredGoodsForBuilding() caused an exception.");
		}

		System.out.println("A person has built a new residual building: (" + residualBuilding + ").");
		return residualBuilding;
	}

	public static void upgradeResidualBuilding(ResidualBuilding residualBuilding) throws GoodStockTooSmallException, MoneyTooSmallException, CapacityUnderflowException, CapacityOverflowException {
		GoodStock goodStock = residualBuilding.getGoodStock();
		residualBuilding = createResidualBuilding(lookupNextSocialStatus(residualBuilding.getSocialStatus()), residualBuilding.getCity(), residualBuilding.getOwner(), residualBuilding.getGoodStock(), residualBuilding.getPersonsInvolved());
		for (Good good : goodStock.getStock().keySet())
			try {
				residualBuilding.getGoodStock().addGood(good, residualBuilding.getGoodStock().getStock(good));
			} catch (CapacityOverflowException e) {
				e.printStackTrace();
			}
	}

	private static SocialStatus lookupNextSocialStatus(SocialStatus socialStatus) {
		switch (socialStatus) {
		case Pioneer:
			return SocialStatus.Settler;
		case Settler:
			return SocialStatus.Citizen;
		case Citizen:
			return SocialStatus.Merchant;
		case Merchant:
			return SocialStatus.Aristocrat;
		default:
			throw new IllegalArgumentException("BuildingFactory.lookupNextSocialStatus(): SocialStatus not available.");
		}
	}

	public static WorkBuilding createWorkBuilding(City city, Jobtype jobtype, Person person, GoodStock goodStock) throws GoodStockTooSmallException, MoneyTooSmallException, CapacityUnderflowException, CapacityOverflowException {
		List<Person> involved = new ArrayList<Person>();
		involved.add(person);
		return createWorkBuilding(city, jobtype, person, goodStock, involved);
	}

	public static WorkBuilding createWorkBuilding(City city, Jobtype jobtype, Person person, GoodStock goodStock, List<Person> personsInvolved) throws GoodStockTooSmallException, MoneyTooSmallException, CapacityUnderflowException, CapacityOverflowException {

		Map<Good, Double> requiredGoodsForBuilding = BuildingTools.getRequiredGoodsForBuilding(jobtype);

		// check resources
		if (BuildingTools.getBuildingCosts(jobtype) > person.getMoney())
			throw new MoneyTooSmallException();

		for (Good good : requiredGoodsForBuilding.keySet())
			if (requiredGoodsForBuilding.get(good) > goodStock.getStock(good))
				throw new GoodStockTooSmallException("BuildingFactory.createWorkBuilding");

		// // subtract resources - is done by the transportbehavior
		// for (Good good : requiredGoodsForBuilding.keySet())
		// try {
		// goodStock.removeGood(good, requiredGoodsForBuilding.get(good));
		// } catch (CapacityUnderflowException e) {
		// e.printStackTrace();
		// }

		person.removeMoney(BuildingTools.getBuildingCosts(jobtype));

		// build building
		WorkBuilding workBuilding = null;

		switch (jobtype) {
		case Fisher:
			workBuilding = new FishingLodge(city, person, personsInvolved, person.getInventory(), person.getTransportBehavior());
			break;
		case PotatoFarmer:
			workBuilding = new PotatoFarm(city, person, personsInvolved, person.getInventory(), person.getTransportBehavior());
			break;
		case CornFarmer:
			workBuilding = new CornFarm(city, person, personsInvolved, person.getInventory(), person.getTransportBehavior());
			break;
		case GrainFarmer:
			workBuilding = new GrainFarm(city, person, personsInvolved, person.getInventory(), person.getTransportBehavior());
			break;
		case Miller:
			workBuilding = new Mill(city, person, personsInvolved, person.getInventory(), person.getTransportBehavior());
			break;
		case WoodCutter:
			workBuilding = new WoodCuttersHut(city, person, personsInvolved, person.getInventory(), person.getTransportBehavior());
			break;
		case Carpenter:
			workBuilding = new LumberMill(city, person, personsInvolved, person.getInventory(), person.getTransportBehavior());
			break;
		default:
			throw new IllegalArgumentException("JobType not found.");
		}

		System.out.println("A person has built a new work building: (" + workBuilding + ").");

		return workBuilding;
	}

}
