package com.formation.computerdatabase.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public final class ScannerUtil {
	
	private static final Scanner SCANNER = new Scanner(System.in);
	
	private ScannerUtil() {
	}
	

	public static String getName(String command) {
		String input = null;
		
		System.out.println(command);
		while(SCANNER.hasNextLine()) {
			input = SCANNER.nextLine();
			if(input.length() != 0) {
				break;
			}
		}
		return input;
	}
	
	
	/**
	 * can not leave the method if the line is empty
	 * @param command
	 * @return
	 */
	public static Integer getNextLong(String command) {
		System.out.println(command);
		String input = null;
		
		while(SCANNER.hasNextLine()) {
			input = SCANNER.nextLine();
			if(Pattern.compile("\\d+").matcher(input).find()) {
				return Integer.parseInt(input);
			}
		}
		return null;
	}
	
	
	/**
	 * return a null object if line empty
	 * @param command
	 * @return
	 */
	public static Long getNextLong() {
		String input = null;
		
		while(SCANNER.hasNextLine()) {
			input = SCANNER.nextLine();
			if(input.length() == 0) {
				break;
			}
			if(Pattern.compile("\\d+").matcher(input).find()) {
				return Long.parseLong(input);
			}
		}
		
		return null;
	}
	
	public static String getNextDate(String command, Pattern pattern) {

		String input = null;
		System.out.println(command);
		
		while(SCANNER.hasNextLine()) {
			input = SCANNER.nextLine();
			if(input.length() == 0) {
				break;
			}
			if(pattern.matcher(input).find()) {
				return input;
			}
		}
		return null;
	}
	
	public static String getNextDate(String command, Pattern pattern, String first) {

		String input = null;
		System.out.println(command);
		
		while(SCANNER.hasNextLine()) {
			input = SCANNER.nextLine();
			if(input.length() == 0) {
				break;
			}
			
			/* if input older as first then input.compareTo(first) > 0 */
			if(pattern.matcher(input).find() && input.compareTo(first) > 0) {
				return input;
			}
		}
		return null;
	}
	
	public static void waitNextLine() {
		System.out.println("Press enter to return to the main menu...");
		SCANNER.nextLine();		
	}
}
