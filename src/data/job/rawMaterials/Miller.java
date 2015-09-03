package data.job.rawMaterials;

import data.job.Job;
import data.job.Jobtype;
import data.person.Person;

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
