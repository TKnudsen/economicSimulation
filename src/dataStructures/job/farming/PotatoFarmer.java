package dataStructures.job.farming;

import dataStructures.job.Job;
import dataStructures.job.Jobtype;
import dataStructures.person.Person;

public class PotatoFarmer extends Job {

	public PotatoFarmer(Person person) {
		super(person);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initializeJobType() {
		this.jobtype = Jobtype.PotatoFarmer;
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
