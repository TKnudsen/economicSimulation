package dataStructures.person;

import java.util.Map;

import dataStructures.job.Jobtype;

public class Talents {

	private Map<Talent, Double> talents;

	public Talents(Map<Talent, Double> talents) {
		this.talents = talents;
	}

	@Override
	public String toString() {
		String s = "Talents: \n";
		for (Talent talent : talents.keySet())
			s += talent.toString() + ": " + talents.get(talent) + "\n";
		return s;
	}
	
	public Map<Talent, Double> getTalents() {
		return talents;
	}

	public Double getTalent(Talent talent) throws IllegalArgumentException {
		if (talents.get(talent) == null)
			throw new IllegalArgumentException("Talent not available.");
		return talents.get(talent);
	}
}
