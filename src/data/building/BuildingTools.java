package data.building;

import java.util.HashMap;
import java.util.Map;

import data.goods.Good;
import data.job.JobTools;
import data.job.Jobtype;
import data.person.SocialStatus;

public class BuildingTools {

	public static double getBuildingCosts(BuildingType buildingType) {
		switch (buildingType) {
		case Market:
			return 0;
		case PioneersHouse:
			return 0;
		case SettlersHouse:
			return 500;
		case FishingLodge:
			return 250;
		case PotatoFarm:
			return 250;
		case CornFarm:
			return 250;
		case GrainFarm:
			return 250;
		case Mill:
			return 250;
		case WoodCuttersHouse:
			return 250;
		case LumberMill:
			return 500;
		default:
			throw new IllegalArgumentException("BuildingTools.getBuildingCosts() caused an exception.");
		}
	}

	public static double getBuildingCosts(Jobtype jobtype) {
		BuildingType buildingType = JobTools.lookupBuildingType(jobtype);
		return getBuildingCosts(buildingType);
	}

	public static double getBuildingCosts(SocialStatus socialStatus) {
		switch (socialStatus) {
		case Pioneer:
			return getBuildingCosts(BuildingType.PioneersHouse);
		case Settler:
			return getBuildingCosts(BuildingType.SettlersHouse);
		default:
			throw new IllegalArgumentException("BuildingTools.getBuildingCosts() caused an exception.");
		}
	}

	public static double getGoodStockCapacity(BuildingType buildingType) {
		switch (buildingType) {
		case Market:
			return 1000;
		case PioneersHouse:
			return 50;
		case SettlersHouse:
			return 100;
		case FishingLodge:
			return 50;
		case CornFarm:
			return 50;
		case GrainFarm:
			return 50;
		case Mill:
			return 50;
		case PotatoFarm:
			return 50;
		case WoodCuttersHouse:
			return 50;
		case LumberMill:
			return 50;
		default:
			throw new IllegalArgumentException("BuildingTools.getGoodStockCapacity() caused an exception.");
		}
	}

	public static Map<Good, Double> getRequiredGoodsForBuilding(BuildingType buildingType) {
		Map<Good, Double> requiredGoodsForBuilding = new HashMap<Good, Double>();

		switch (buildingType) {
		case Market:
			break;
		case PioneersHouse:
			break;
		case SettlersHouse:
			requiredGoodsForBuilding.put(Good.Wood, 5.0);
			requiredGoodsForBuilding.put(Good.Lumber, 5.0);
			break;
		case FishingLodge:
			requiredGoodsForBuilding.put(Good.Lumber, 5.0);
			requiredGoodsForBuilding.put(Good.Tools, 1.0);
			break;
		case PotatoFarm:
			requiredGoodsForBuilding.put(Good.Lumber, 5.0);
			requiredGoodsForBuilding.put(Good.Tools, 1.0);
			break;
		case CornFarm:
			requiredGoodsForBuilding.put(Good.Lumber, 5.0);
			requiredGoodsForBuilding.put(Good.Tools, 1.0);
			break;
		case GrainFarm:
			requiredGoodsForBuilding.put(Good.Lumber, 5.0);
			requiredGoodsForBuilding.put(Good.Tools, 1.0);
			break;
		case Mill:
			requiredGoodsForBuilding.put(Good.Lumber, 5.0);
			requiredGoodsForBuilding.put(Good.Tools, 1.0);
			break;
		case WoodCuttersHouse:
			requiredGoodsForBuilding.put(Good.Lumber, 5.0);
			requiredGoodsForBuilding.put(Good.Tools, 2.0);
			break;
		case LumberMill:
			requiredGoodsForBuilding.put(Good.Wood, 10.0);
			requiredGoodsForBuilding.put(Good.Tools, 2.0);
			break;
		default:
			throw new IllegalArgumentException("BuildingTools.getRequiredGoodsForBuilding(BuildingType buildingType): BuildingType not accepted.");
		}

		return requiredGoodsForBuilding;
	}

	public static Map<Good, Double> getRequiredGoodsForBuilding(Jobtype jobtype) {
		BuildingType buildingType = JobTools.lookupBuildingType(jobtype);
		return getRequiredGoodsForBuilding(buildingType);
	}

	public static Map<Good, Double> getRequiredGoodsForBuilding(SocialStatus socialStatus) {
		Map<Good, Double> requiredGoodsForBuilding = new HashMap<Good, Double>();

		switch (socialStatus) {
		case Pioneer:
			// requiredGoodsForBuilding.put(Good.Wood, 50.0);
			break;
		case Settler:
			requiredGoodsForBuilding.put(Good.Wood, 50.0);
			requiredGoodsForBuilding.put(Good.Lumber, 50.0);
			break;
		default:
			throw new IllegalArgumentException("BuildingTools.getRequiredGoodsForBuilding() caused an exception.");
		}

		return requiredGoodsForBuilding;
	}
}
