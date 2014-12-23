package com.formation.computerdatabase.ui;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.service.ComputerDatabaseService;
import com.formation.computerdatabase.service.ServiceFactory;

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
		Scanner scanner = new Scanner(System.in);

		String input = null;

		do {
			System.out.println("Enter computer name: ");
			input = scanner.next().trim();
		} while (input.isEmpty());

		Computer.Builder b = Computer.builder(input);

		Pattern p = Pattern.compile("^[1-2][0-9]{3}-([0][1-9]|1[0-2])-([0-2][1-9]|3[0-1])$");

		do {
			System.out.println("Enter introduced date (yyyy-mm-dd) or leave empty: ");
			input = scanner.next().trim();
		} while (!input.isEmpty() && !p.matcher(input).find());

		if (!input.isEmpty()) {
			b.introduced(input);
		}

		do {
			System.out.println("Enter discontinued date (yyyy-mm-dd) or leave empty: ");
			input = scanner.next().trim();
		} while (!input.isEmpty() && !p.matcher(input).find());

		if (!input.isEmpty()) {
			b.discontinued(input);
		}

		p = Pattern.compile("^[0-9]+$");

		do {
			System.out.println("Enter company id or leave empty: ");
			input = scanner.next().trim();
		} while (!input.isEmpty() && !p.matcher(input).find());

		if (!input.isEmpty()) {
			b.company(service.retrieveOneCompany(Long.parseLong(input)));
		}

		scanner.close();

		Computer c = b.build();
		service.saveComputer(c);
		System.out.println("Computer created with id:" + c.getId());
	}

	public static void main(String[] args) {
		ConsoleClient c = new ConsoleClient();
	}

}
