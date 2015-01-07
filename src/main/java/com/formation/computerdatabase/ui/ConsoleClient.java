package com.formation.computerdatabase.ui;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.formation.computerdatabase.commons.Pageable;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.service.CompanyService;
import com.formation.computerdatabase.service.ComputerService;
import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.util.ScannerUtil;

public class ConsoleClient {
	
	private boolean continueApplication = true;

	private final Scanner scanner = new Scanner(System.in);

	private ComputerService computerService;
	private CompanyService companyService;

	public ConsoleClient() {
		computerService = (ComputerService) ServiceFactory.INSTANCE.getService(ComputerService.KEY);
		companyService = (CompanyService) ServiceFactory.INSTANCE.getService(CompanyService.KEY);
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
		offset = ScannerUtil.getNextLong("Select the page:");
		size = ScannerUtil.getNextLong("Select the size:");
		Pageable<Computer> page = new Pageable<Computer>(offset, size);
		page = computerService.retrievePage(page);
		System.out.println("Show computers:");
		System.out.println("ID | NAME");
		System.out.println("--------------------------");
		List<Computer> computers = page.getComputers();
		for (Computer c : computers) {
			System.out.println(new StringBuilder().append(c.getId()).append(" - ").append(c.getName()).toString());
		}
		ScannerUtil.waitNextLine();
	}
	
	private void showAllComputers() {
		List<Computer> computers = computerService.retrieveAll();
		System.out.println("Show computers:");
		System.out.println("ID | NAME");
		System.out.println("--------------------------");
		for (Computer c : computers) {
			System.out.println(new StringBuilder().append(c.getId()).append(" - ").append(c.getName()).toString());
		}
		ScannerUtil.waitNextLine();
	}

	private void showAllCompanies() {
		List<Company> companies = companyService.retrieveAll();
		System.out.println("Show companies:");
		System.out.println("ID | NAME");
		System.out.println("--------------------------");
		for (Company c : companies) {
			System.out.println(new StringBuilder().append(c.getId()).append(" - ").append(c.getName()).toString());
		}
		ScannerUtil.waitNextLine();
	}

	private void showComputer() {
		System.out.println("Enter computer id or exit: ");
		Long input = ScannerUtil.getNextLong();
		if(input != null ) {
			System.out.println(computerService.retrieveOne(input).toString());
			ScannerUtil.waitNextLine();
		}		
	}

	private void addComputer() {
		System.out.println("Add computer:");
		String intr = null;
		String input = null;

		Computer.Builder b = Computer.builder(ScannerUtil.getName("Enter computer name: "));

		Pattern pattern = Pattern.compile("^[1-2][0-9]{3}-([0][1-9]|1[0-2])-([0-2][1-9]|3[0-1])$");

		intr = ScannerUtil.getNextDate("Enter introduced date (yyyy-mm-dd) or leave empty: "
				, pattern);
		b.introduced(intr);
		
		if (intr != null) {
				input = ScannerUtil.getNextDate("Enter discontinued date (yyyy-mm-dd) after " + intr + " or leave empty: "
						, pattern
						, intr);
			b.discontinued(input);			
		} else {			
			input = ScannerUtil.getNextDate("Enter discontinued date (yyyy-mm-dd) or leave empty: "
					, pattern);
			b.discontinued(input);			
		}

		System.out.println("Enter company id or leave empty:");
		Long result = ScannerUtil.getNextLong();
		if(result != null) {
			b.company(companyService.retrieveOne(result));
		}

		Computer c = b.build();
		computerService.save(c);
		System.out.println("Computer created with id:" + c.getId());
		ScannerUtil.waitNextLine();
	}

	public void editComputer() {
		System.out.println("Computer edition:");
		String input = null;

		System.out.println("Enter computer id or leave empty to exit: ");

		Long result = ScannerUtil.getNextLong();
		if(result != null) {
			Computer c = computerService.retrieveOne(result);

			System.out.println(c.toString());
			
			if("y".equals(ScannerUtil.getName("Do you wish to change the name? (y or n)")) ) {
				c.setName(ScannerUtil.getName("Enter computer name: "));
			}
	
			Pattern pattern = Pattern.compile("^[1-2][0-9]{3}-([0][1-9]|1[0-2])-([0-2][1-9]|3[0-1])$");
			
			if("y".equals(ScannerUtil.getName("Do you wish to change the introducted date? (y or n)"))) {
				input = ScannerUtil.getNextDate("Enter introduced date (yyyy-mm-dd) or leave empty: "
						, pattern);
				c.setIntroduced(input);
			}
			
			if("y".equals(ScannerUtil.getName("Do you wish to change the discontinued date? (y or n)"))) {
				
				if(c.getIntroduced() != null ) {
						input = ScannerUtil.getNextDate("Enter discontinued date (yyyy-mm-dd) " + c.getIntroduced() + " or leave empty: "
								, pattern);
						
					c.setDiscontinued(input);
				} else {
					input = ScannerUtil.getNextDate("Enter discontinued date (yyyy-mm-dd) or leave empty: "
							, pattern);
					c.setDiscontinued(input);			
				}
			}
			
			if("y".equals(ScannerUtil.getName("Do you wish to change the campany id? (y or n)"))) {
				c.setCompany(companyService.retrieveOne(ScannerUtil.getNextLong()));
			}
			
			computerService.save(c);
			System.out.println("Computer edited.");
			ScannerUtil.waitNextLine();
		}

	}

	public void deleteComputer() {
		System.out.println("Computer to delete:");
		Long input = null;

		System.out.println("Enter computer id or leave empty to exit: "); 
		input = ScannerUtil.getNextLong();
		if(input != null) {
			computerService.delete(input);
		}

		System.out.println(input +" is deleted.");
		ScannerUtil.waitNextLine();
	}

	private void exitApplication() {
		scanner.close();
		this.continueApplication = false;
		System.out.println("The application was correctly closed.");
	}


	public static void main(String[] args) {
		ConsoleClient c = new ConsoleClient();
		while(c.continueApplication) {
			c.showMenu();
		}
		
	}
}
