package dataStructures.job.animalhunting;

import dataStructures.job.Job;
import dataStructures.job.Jobtype;
import dataStructures.person.Person;

public class Fisher extends Job {

	public Fisher(Person person) {
		super(person);
	}

	@Override
	protected void initializeJobType() {
		this.jobtype = Jobtype.Fisher;
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
