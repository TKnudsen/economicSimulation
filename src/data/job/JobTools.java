package data.job;

import java.util.HashMap;
import java.util.Map;

import data.building.BuildingType;
import data.goods.Good;
import data.person.Talent;
import data.person.Talents;

public class JobTools {

	private static Map<Jobtype, Talents> talentsRelevancesForJob = new HashMap<Jobtype, Talents>();

	public static Talents getTalentsRelevance(Jobtype jobtype) {
		if (talentsRelevancesForJob.get(jobtype) == null)
			initializeForJob(jobtype);

		return talentsRelevancesForJob.get(jobtype);
	}

	private static void initializeForJob(Jobtype jobtype) {
		Map<Talent, Double> talentsRelevance = new HashMap<Talent, Double>();
		switch (jobtype) {
		case Fisher:
			talentsRelevance.put(Talent.Intelligence, 0.25);
			talentsRelevance.put(Talent.Strength, 0.75);
			talentsRelevance.put(Talent.Dexterity, 0.8);
			talentsRelevance.put(Talent.Leadership, 0.1);
			talentsRelevance.put(Talent.Creativity, 0.2);
			talentsRelevance.put(Talent.Bravery, 0.8);
			talentsRelevance.put(Talent.Diligence, 0.7); // Sorgfalt, Fleiﬂ
			talentsRelevance.put(Talent.Loyalty, 0.33);
			talentsRelevance.put(Talent.Belief, 0.4);
			talentsRelevance.put(Talent.Sincerity, 0.25);
			talentsRelevancesForJob.put(jobtype, new Talents(talentsRelevance));
			break;
		case PotatoFarmer:
			talentsRelevance.put(Talent.Intelligence, 0.1);
			talentsRelevance.put(Talent.Strength, 0.75);
			talentsRelevance.put(Talent.Dexterity, 0.4);
			talentsRelevance.put(Talent.Leadership, 0.1);
			talentsRelevance.put(Talent.Creativity, 0.0);
			talentsRelevance.put(Talent.Bravery, 0.1);
			talentsRelevance.put(Talent.Diligence, 0.7); // Sorgfalt, Fleiﬂ
			talentsRelevance.put(Talent.Loyalty, 0.33);
			talentsRelevance.put(Talent.Belief, 0.25);
			talentsRelevance.put(Talent.Sincerity, 0.25);
			talentsRelevancesForJob.put(jobtype, new Talents(talentsRelevance));
			break;
		case CornFarmer:
			talentsRelevance.put(Talent.Intelligence, 0.15);
			talentsRelevance.put(Talent.Strength, 0.75);
			talentsRelevance.put(Talent.Dexterity, 0.4);
			talentsRelevance.put(Talent.Leadership, 0.1);
			talentsRelevance.put(Talent.Creativity, 0.0);
			talentsRelevance.put(Talent.Bravery, 0.1);
			talentsRelevance.put(Talent.Diligence, 0.7); // Sorgfalt, Fleiﬂ
			talentsRelevance.put(Talent.Loyalty, 0.33);
			talentsRelevance.put(Talent.Belief, 0.25);
			talentsRelevance.put(Talent.Sincerity, 0.2);
			talentsRelevancesForJob.put(jobtype, new Talents(talentsRelevance));
			break;
		case GrainFarmer:
			talentsRelevance.put(Talent.Intelligence, 0.15);
			talentsRelevance.put(Talent.Strength, 0.75);
			talentsRelevance.put(Talent.Dexterity, 0.4);
			talentsRelevance.put(Talent.Leadership, 0.1);
			talentsRelevance.put(Talent.Creativity, 0.0);
			talentsRelevance.put(Talent.Bravery, 0.1);
			talentsRelevance.put(Talent.Diligence, 0.75); // Sorgfalt, Fleiﬂ
			talentsRelevance.put(Talent.Loyalty, 0.33);
			talentsRelevance.put(Talent.Belief, 0.25);
			talentsRelevance.put(Talent.Sincerity, 0.2);
			talentsRelevancesForJob.put(jobtype, new Talents(talentsRelevance));
			break;
		case Miller:
			talentsRelevance.put(Talent.Intelligence, 0.15);
			talentsRelevance.put(Talent.Strength, 0.75);
			talentsRelevance.put(Talent.Dexterity, 0.45);
			talentsRelevance.put(Talent.Leadership, 0.0);
			talentsRelevance.put(Talent.Creativity, 0.0);
			talentsRelevance.put(Talent.Bravery, 0.1);
			talentsRelevance.put(Talent.Diligence, 0.75); // Sorgfalt, Fleiﬂ
			talentsRelevance.put(Talent.Loyalty, 0.30);
			talentsRelevance.put(Talent.Belief, 0.20);
			talentsRelevance.put(Talent.Sincerity, 0.2);
			talentsRelevancesForJob.put(jobtype, new Talents(talentsRelevance));
			break;
		case WoodCutter:
			talentsRelevance.put(Talent.Intelligence, 0.1);
			talentsRelevance.put(Talent.Strength, 1.0);
			talentsRelevance.put(Talent.Dexterity, 0.3);
			talentsRelevance.put(Talent.Leadership, 0.1);
			talentsRelevance.put(Talent.Creativity, 0.0);
			talentsRelevance.put(Talent.Bravery, 0.5);
			talentsRelevance.put(Talent.Diligence, 0.7); // Sorgfalt, Fleiﬂ
			talentsRelevance.put(Talent.Loyalty, 0.33);
			talentsRelevance.put(Talent.Belief, 0.1);
			talentsRelevance.put(Talent.Sincerity, 0.2);
			talentsRelevancesForJob.put(jobtype, new Talents(talentsRelevance));
			break;
		case Carpenter:
			talentsRelevance.put(Talent.Intelligence, 0.4);
			talentsRelevance.put(Talent.Strength, 0.6);
			talentsRelevance.put(Talent.Dexterity, 0.6);
			talentsRelevance.put(Talent.Leadership, 0.2);
			talentsRelevance.put(Talent.Creativity, 0.3);
			talentsRelevance.put(Talent.Bravery, 0.2);
			talentsRelevance.put(Talent.Diligence, 0.7); // Sorgfalt, Fleiﬂ
			talentsRelevance.put(Talent.Loyalty, 0.33);
			talentsRelevance.put(Talent.Belief, 0.25);
			talentsRelevance.put(Talent.Sincerity, 0.3);
			talentsRelevancesForJob.put(jobtype, new Talents(talentsRelevance));
			break;
		default:
			throw new IllegalArgumentException("Job Type (class) not Found in JobTools.initializeForJob(Jobtype jobtype)");
		}
	}

	public static BuildingType lookupBuildingType(Jobtype jobtype) {
		switch (jobtype) {
		case Fisher:
			return BuildingType.FishingLodge;
		case PotatoFarmer:
			return BuildingType.PotatoFarm;
		case CornFarmer:
			return BuildingType.CornFarm;
		case GrainFarmer:
			return BuildingType.GrainFarm;
		case Miller:
			return BuildingType.Mill;
		case WoodCutter:
			return BuildingType.WoodCuttersHouse;
		case Carpenter:
			return BuildingType.LumberMill;
		default:
			throw new IllegalArgumentException("JobTools.getBuildingType(Jobtype jobtype): JobType not found.");
		}
	}

	public static Good lookupGoodType(Jobtype jobtype) {
		switch (jobtype) {
		case Fisher:
			return Good.Fish;
		case PotatoFarmer:
			return Good.Potato;
		case CornFarmer:
			return Good.Corn;
		case GrainFarmer:
			return Good.Grain;
		case Miller:
			return Good.Flour;
		case WoodCutter:
			return Good.Wood;
		case Carpenter:
			return Good.Lumber;
		default:
			throw new IllegalArgumentException("JobTools.lookupGoodType(Jobtype jobtype): JobType not found.");
		}
	}
}
