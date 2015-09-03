package data.person;

import java.util.HashMap;
import java.util.Map;

import data.job.JobTools;
import data.job.Jobtype;

public class JobSkillsFactory {
	public static JobSkills createJobSkills(Talents talents) {
		Map<Jobtype, Double> skills = new HashMap<Jobtype, Double>();

		for (Jobtype jobtype : Jobtype.values()) {

			Talents talentRelevances = JobTools.getTalentsRelevance(jobtype);

			double talent = 0;
			double weightSum = 0;
			for (Talent t : talentRelevances.getTalents().keySet()) {
				talent += talents.getTalent(t) * talentRelevances.getTalent(t);
				weightSum += talentRelevances.getTalent(t);
			}
			talent /= weightSum;
			skills.put(jobtype, talent);
		}
		return new JobSkills(skills);
	}
}
