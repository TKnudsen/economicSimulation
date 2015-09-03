package dataStructures.person;

import java.util.HashMap;
import java.util.Map;

public class TalentsFactory {
	public static Talents createTalents() {
		Map<Talent, Double> talents = new HashMap<Talent, Double>();
		for (Talent talent : Talent.values()) {
			double value = 100;
			double var = Math.random();
			var = (Math.signum(Math.random() - 0.5) * var * var) * 100;
			talents.put(talent, value + var);
		}

		Talents ret = new Talents(talents);
		return ret;
	}
}