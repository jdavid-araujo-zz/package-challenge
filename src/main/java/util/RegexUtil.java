package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	public static final String NUMBERS = "\\d+\\.?\\d*";
	
	public static Matcher getNumberByRegex(String number) {
		 Pattern p = Pattern.compile(NUMBERS);
		 
		 Matcher m = p.matcher(number);
		 
		 return m;
	}
}
