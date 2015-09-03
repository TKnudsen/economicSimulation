package data.job;

import data.job.animalhunting.Fisher;
import data.job.farming.CornFarmer;
import data.job.farming.GrainFarmer;
import data.job.farming.PotatoFarmer;
import data.job.rawMaterials.Miller;
import data.job.rawMaterials.WoodCutter;
import data.job.woodcraft.Carpenter;
import data.person.Person;

public class JobFactory {
	public static Job createJob(Jobtype jobtype, Person person) {

		Job job = null;

		switch (jobtype) {
		case Fisher:
			job = new Fisher(person);
			break;
		case PotatoFarmer:
			job = new PotatoFarmer(person);
			break;
		case CornFarmer:
			job = new CornFarmer(person);
			break;
		case GrainFarmer:
			job = new GrainFarmer(person);
			break;
		case Miller:
			job = new Miller(person);
			break;
		case WoodCutter:
			job = new WoodCutter(person);
			break;
		case Carpenter:
			job = new Carpenter(person);
			break;
		default:
			throw new IllegalArgumentException("JobType not found.");
		}

		System.out.println("A person has started a new job: (" + job + ").");
		return job;
	}
}
