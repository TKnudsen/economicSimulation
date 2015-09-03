package dataStructures.job;

import dataStructures.person.Person;

public abstract class Job {

	private Person person;
	protected Jobtype jobtype;

	// public static Talents talentsRelevance;

	@SuppressWarnings("unused")
	private Job() {

	}

	public Job(Person person) {
		this.person = person;
		// initializeTalentRelevance();
		initializeJobType();
	}

	// /**
	// * every job has individual talent requirements.
	// */
	// protected abstract void initializeTalentRelevance();

	protected abstract void initializeJobType();

	protected abstract double calculateProductivity();

	public abstract void execute();
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Jobtype getJobType() {
		return jobtype;
	}

}
