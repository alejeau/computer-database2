package com.formation.computerdatabase.ui;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.formation.computerdatabase.commons.Pageable;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.service.ComputerDatabaseService;
import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.util.DateUtil;

public class ConsoleClient {
	
	private boolean continueApplication = true;

	private final Scanner scanner = new Scanner(System.in);

	private ComputerDatabaseService service;

	public ConsoleClient() {
		service = ServiceFactory.INSTANCE.getService();
	}

	private void showMenu() {
		System.out.println("--------------------------");
		System.out.println("Main menu");
		System.out.println("--------------------------");
		System.out.println("a) List computers");
		System.out.println("b) List of a range of computers");
		System.out.println("c) List companies");
		System.out.println("d) Show computer details");
		System.out.println("e) Add computer");
		System.out.println("f) Edit computer");
		System.out.println("g) Delete computer");
		System.out.println("g) Exit application");

		// Option regex
		Pattern p = Pattern.compile("^[a-h]$");
		String s = null;

		do {
			System.out.println("Choose an option (a-h):");
			s = scanner.next();
		} while (!p.matcher(s = s.toLowerCase()).find());

		switch (s) {
		case "a":
			showAllComputers();
			break;
		case "b":
			showRangeComputers();
			break;
		case "c":
			showAllCompanies();
			break;
		case "d":
			showComputer();
			break;
		case "e":
			addComputer();
			break;
		case "f":
			editComputer();
			break;
		case "g":
			deleteComputer();
			break;
		case "h":
			exitApplication();
			break;
		}
	}

	private void showRangeComputers() {
		int offset, size;
		offset = Integer.parseInt(this.getInput("Select the page:"));
		size = Integer.parseInt(this.getInput("Select the size:"));
		Pageable<Computer> page = new Pageable<Computer>(offset, size);
		page = service.retrievePage(page);
		System.out.println("Show computers:");
		System.out.println("ID | NAME");
		System.out.println("--------------------------");
		List<Computer> computers = page.getComputers();
		for (Computer c : computers) {
			System.out.println(new StringBuilder().append(c.getId()).append(" - ").append(c.getName()).toString());
		}
		this.anyKeyToMenu();
	}
	
	private void showAllComputers() {
		List<Computer> computers = service.retrieveAllComputers();
		System.out.println("Show computers:");
		System.out.println("ID | NAME");
		System.out.println("--------------------------");
		for (Computer c : computers) {
			System.out.println(new StringBuilder().append(c.getId()).append(" - ").append(c.getName()).toString());
		}
		this.anyKeyToMenu();
	}

	private void showAllCompanies() {
		List<Company> companies = service.retrieveAllCompanies();
		System.out.println("Show companies:");
		System.out.println("ID | NAME");
		System.out.println("--------------------------");
		for (Company c : companies) {
			System.out.println(new StringBuilder().append(c.getId()).append(" - ").append(c.getName()).toString());
		}
		this.anyKeyToMenu();
	}

	private void showComputer() {
		System.out.println("Computer details:");
		Long l = null;

		System.out.println("Enter computer id: ");
		l = getNextLong();
		Computer c = service.retrieveOneComputer(l);

		System.out.println(c.toString());
		this.anyKeyToMenu();

	}

	private void addComputer() {
		System.out.println("Add computer:");
		String intr = null;
		String input = null;

		Computer.Builder b = Computer.builder(getInput("Enter computer name: "));

		Pattern p = Pattern.compile("^[1-2][0-9]{3}-([0][1-9]|1[0-2])-([0-2][1-9]|3[0-1])$");

		intr = getInputWithPattern("Enter introduced date (yyyy-mm-dd) or leave empty: ", p);
		if (! intr.isEmpty()) {
			b.introduced(intr);
		}

		/* loop while the discontinued date is not older as the introducted date */
		do {
			input = getInputWithPattern("Enter discontinued date (yyyy-mm-dd) or leave empty: ", p);
		} while ( (intr != null ) && ( DateUtil.stringToDate(input).before(DateUtil.stringToDate(intr))));
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
		this.anyKeyToMenu();
	}

	public void editComputer() {
		System.out.println("Computer edition:");
		Long l = null;
		String input = null;

		System.out.println("Enter computer id: ");
		l = getNextLong();
		
		Computer c = service.retrieveOneComputer(l);

		System.out.println(c.toString());
		
		if("y".equals(this.getInput("Do you wish to change the name? (y or n)")) ) {
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
		
		if("y".equals(getInput("Do you wish to change the campany id? (y or n)"))) {
			l = scanner.nextLong();			
			c.setCompany(service.retrieveOneCompany(l));
		}
		
		service.saveComputer(c);
		System.out.println("Computer edited.");
		this.anyKeyToMenu();

	}

	public void deleteComputer() {
		System.out.println("Computer to delete:");
		Long l = null;

		System.out.println("Enter computer id: "); 
		l = getNextLong();
		
		service.deleteComputer(l);

		System.out.println(l+" is deleted.");
		this.anyKeyToMenu();
	}
	
	private String getInput(String command) {
		String input = null;

		do {
			System.out.println(command);
			input = scanner.next();
		} while (input.isEmpty());
		return input;
	}
	
	private String getInputWithPattern(String command, Pattern pattern) {
		System.out.println(command);
		scanner.next();
		String input = null;
		do {
			input = scanner.next();
		} while ( !pattern.matcher(input).find());
		return input;
	}

	private void exitApplication() {
		scanner.close();
		this.continueApplication = false;
		System.out.println("The application was correctly closed.");
	}

	/**
	 * 
	 *  Take the next long with validation on the nature of the return object.
	 */
	private long getNextLong() {
		do {
			if(scanner.hasNext()) {
				if(scanner.hasNextLong()) {
					break;
				} else {
					System.out.println("Please enter a valid number format.");
					/* avoid infinite loop by reading the syso above */
					scanner.next();
				}
			}
		} while(true);
		return scanner.nextLong();
	}

	private void anyKeyToMenu() {
		System.out.println("Press any key to return to the main menu...");
		String input = null;
		do {
			input = scanner.next();
		} while (input.isEmpty());
	}

	public static void main(String[] args) {
		ConsoleClient c = new ConsoleClient();
		while(c.continueApplication) {
			c.showMenu();
		}
	}
}
