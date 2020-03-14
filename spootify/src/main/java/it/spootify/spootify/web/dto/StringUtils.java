package it.spootify.spootify.web.dto;

public class StringUtils {
	
	public static boolean isNotBlank(String s) {
		if(s==null) {
			return false;
		}
		String[]a = s.split(" ");
		if(a[0].equals("")) {
			return false;
		}
		return true;
	}
	public static boolean isBlank(String s) {
		if(s==null) {
			return true;
		}
		String[]a = s.split(" ");
		if(a[0].equals("")) {
			return true;
		}
		return false;
	}
	
}
