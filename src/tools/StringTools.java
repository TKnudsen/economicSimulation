package tools;

import java.util.Locale;

public class StringTools {
	public static String format(double value) {
		return String.format(Locale.GERMAN, "%1$,.2f", value);
	}
}
