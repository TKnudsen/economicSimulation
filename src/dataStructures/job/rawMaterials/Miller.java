package dataStructures.job.rawMaterials;

import dataStructures.job.Job;
import dataStructures.job.Jobtype;
import dataStructures.person.Person;

public class Miller extends Job {

	public Miller(Person person) {
		super(person);
	}

	@Override
	protected void initializeJobType() {
		this.jobtype = Jobtype.Miller;
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
