package dataStructures.job.farming;

import dataStructures.job.Job;
import dataStructures.job.Jobtype;
import dataStructures.person.Person;

public class GrainFarmer extends Job {

	public GrainFarmer(Person person) {
		super(person);
	}

	@Override
	protected void initializeJobType() {
		this.jobtype = Jobtype.GrainFarmer;
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
