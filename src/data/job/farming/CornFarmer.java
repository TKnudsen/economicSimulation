package data.job.farming;

import data.job.Job;
import data.job.Jobtype;
import data.person.Person;

public class CornFarmer extends Job {

	public CornFarmer(Person person) {
		super(person);
	}

	@Override
	protected void initializeJobType() {
		this.jobtype = Jobtype.CornFarmer;
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
