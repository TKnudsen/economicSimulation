package dataStructures.job;

import dataStructures.job.animalhunting.Fisher;
import dataStructures.job.farming.CornFarmer;
import dataStructures.job.farming.GrainFarmer;
import dataStructures.job.farming.PotatoFarmer;
import dataStructures.job.rawMaterials.Miller;
import dataStructures.job.rawMaterials.WoodCutter;
import dataStructures.job.woodcraft.Carpenter;
import dataStructures.person.Person;

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
