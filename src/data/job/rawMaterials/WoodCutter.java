package data.job.rawMaterials;

import data.job.Job;
import data.job.Jobtype;
import data.person.Person;

public class WoodCutter extends Job{

	public WoodCutter(Person person) {
		super(person);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initializeJobType() {
		this.jobtype = Jobtype.WoodCutter;
	}

	@Override
	protected double calculateProductivity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
