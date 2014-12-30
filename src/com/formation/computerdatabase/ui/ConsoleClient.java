package com.formation.computerdatabase.ui;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.service.ComputerDatabaseService;
import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.util.DateUtil;

public class ConsoleClient {

	private ComputerDatabaseService service;

	public ConsoleClient() {
		service = ServiceFactory.INSTANCE.getService();
	}

	private void showMenu() {
		System.out.println("--------------------------");
		System.out.println("Main menu");
		System.out.println("--------------------------");
		System.out.println("a) List computers");
		System.out.println("b) List companies");
		System.out.println("c) Show computer details");
		System.out.println("d) Add computer");
		System.out.println("e) Edit computer");
		System.out.println("f) Delete computer");

		// Option regex
		Pattern p = Pattern.compile("^[a-f]$");

		Scanner scanner = new Scanner(System.in);
		String s = null;

		do {
			System.out.println("Choose an option (a-f):");
			s = scanner.next();
		} while (!p.matcher(s = s.toLowerCase()).find());

		switch (s) {
		case "a":
			showAllComputers();
			break;
		case "b":
			showAllCompanies();
			break;
		case "c":
			showComputer();
			break;
		case "d":
			addComputer();
			break;
		case "e":
			editComputer();
			break;
		case "f":
			deleteComputer();
			break;
		}
		scanner.close();
	}

	private void showAllComputers() {
		List<Computer> computers = service.retrieveAllComputers();
		System.out.println("Show computers:");
		System.out.println("ID | NAME");
		System.out.println("--------------------------");
		for (Computer c : computers) {
			System.out.println(new StringBuilder().append(c.getId()).append(" - ").append(c.getName()).toString());
		}
	}

	private void showAllCompanies() {
		List<Company> companies = service.retrieveAllCompanies();
		System.out.println("Show companies:");
		System.out.println("ID | NAME");
		System.out.println("--------------------------");
		for (Company c : companies) {
			System.out.println(new StringBuilder().append(c.getId()).append(" - ").append(c.getName()).toString());
		}
	}

	private void showComputer() {
		System.out.println("Computer details:");
		Scanner scanner = new Scanner(System.in);
		Long l = null;

		System.out.println("Enter computer id: ");
		l = scanner.nextLong();
		scanner.close();
		Computer c = service.retrieveOneComputer(l);

		System.out.println(c.toString());

	}

	private void addComputer() {
		System.out.println("Add computer:");
		String input = null;

		Computer.Builder b = Computer.builder(getInput("Enter computer name: "));

		Pattern p = Pattern.compile("^[1-2][0-9]{3}-([0][1-9]|1[0-2])-([0-2][1-9]|3[0-1])$");

		input = getInputWithPattern("Enter introduced date (yyyy-mm-dd) or leave empty: ", p);
		if (! input.isEmpty()) {
			b.introduced(input);
		}

		input = getInputWithPattern("Enter discontinued date (yyyy-mm-dd) or leave empty: ", p);
		if (!input.isEmpty()) {
			b.discontinued(input);
		}

		p = Pattern.compile("^[0-9]+$");

		input = getInputWithPattern("Enter company id or leave empty:", p);
		if (!input.isEmpty()) {
			b.company(service.retrieveOneCompany(Long.parseLong(input)));
		}

		Computer c = b.build();
		service.saveComputer(c);
		System.out.println("Computer created with id:" + c.getId());
	}

	public void editComputer() {
		System.out.println("Computer edition:");
		Scanner scanner = new Scanner(System.in);
		Long l = null;
		String input = null;

		System.out.println("Enter computer id: ");
		l = scanner.nextLong();
		scanner.close();
		Computer c = service.retrieveOneComputer(l);

		System.out.println(c.toString());
		
		if("y".equals(getInput("Do you wish to change the name? (y or n)"))) {
			c.setName(getInput("Enter computer name: "));
		}

		Pattern p = Pattern.compile("^[1-2][0-9]{3}-([0][1-9]|1[0-2])-([0-2][1-9]|3[0-1])$");
		
		if("y".equals(getInput("Do you wish to change the introducted date? (y or n)"))) {
			input = getInputWithPattern("Enter introduced date (yyyy-mm-dd) or leave empty: ", p);
			if (! input.isEmpty()) {
				c.setIntroduced(input);
			}
		}
		
		if("y".equals(getInput("Do you wish to change the discontinued date? (y or n)"))) {
			
			/* loop while the discontinued date is not older as the introducted date */
			do {
				input = getInputWithPattern("Enter discontinued date (yyyy-mm-dd) or leave empty: ", p);
			} while ( DateUtil.stringToDate(input).before(c.getIntroduced()));
			
			if (! input.isEmpty()) {
				c.setDiscontinued(input);
			}
		}
		
		service.saveComputer(c);
		System.out.println("Computer edited.");

	}

	public void deleteComputer() {
		System.out.println("Computer to delete:");
		Scanner scanner = new Scanner(System.in);
		Long l = null;

		System.out.println("Enter computer id: "); 
		l = scanner.nextLong();
		scanner.close();
		
		service.deleteComputer(l);

		System.out.println(l+" is deleted.");
	}
	
	private String getInput(String command) {
		final Scanner scanner = new Scanner(System.in);
		String input = null;

		do {
			System.out.println(command);
			input = scanner.next().trim();
		} while (input.isEmpty());
		scanner.close();
		return input;
	}
	
	private String getInputWithPattern(String command, Pattern pattern) {
		final Scanner scanner = new Scanner(System.in);
		String input = null;
		do {
			System.out.println("Enter introduced date (yyyy-mm-dd) or leave empty: ");
			input = scanner.next().trim();
		} while (!input.isEmpty() && !pattern.matcher(input).find());
		scanner.close();
		return input;
	}

	public static void main(String[] args) {
		ConsoleClient c = new ConsoleClient();
		
	}
}
