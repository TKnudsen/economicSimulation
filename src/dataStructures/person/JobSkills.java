package dataStructures.person;

import java.util.Map;

import dataStructures.job.Job;
import dataStructures.job.Jobtype;

public class JobSkills {
	private Map<Jobtype, Double> skills;

	public JobSkills(Map<Jobtype, Double> skills) {
		this.skills = skills;
	}

	public void addToJobSkill(Jobtype jobtype, double value) {
		this.skills.put(jobtype, this.skills.get(jobtype) + value);
	}

	@Override
	public String toString() {
		String s = "JobSkills: \n";
		for (Jobtype job : skills.keySet())
			s += job.toString() + ": " + skills.get(job) + "\n";
		return s;
	}

	public Jobtype getMaximumJobSkill() {
		Jobtype jobtype = null;
		double max = Double.MIN_VALUE;
		for (Jobtype job : skills.keySet())
			if (skills.get(job) > max) {
				jobtype = job;
				max = skills.get(job);
			}
		return jobtype;
	}

	public Map<Jobtype, Double> getJobSkills() {
		return skills;
	}

	public Double getJobSkill(Jobtype job) throws IllegalArgumentException {
		if (skills.get(job) == null)
			throw new IllegalArgumentException("Talent not available.");
		return skills.get(job);
	}

	public Double getJobSkill(Job job) throws IllegalArgumentException {
		if (skills.get(job.getJobType()) == null)
			throw new IllegalArgumentException("Talent not available.");
		return skills.get(job.getJobType());
	}

	public Double getJobSkill(Class job) throws IllegalArgumentException, InstantiationException, IllegalAccessException {
		return getJobSkill((Job) job.newInstance());
	}

}
