package com.formation.computerdatabase.service;

import java.util.List;

import com.formation.computerdatabase.commons.Pageable;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;

public interface ComputerDatabaseService {
	List<Computer> retrieveAllComputers();
	Computer retrieveOneComputer(Long id);
	List<Company> retrieveAllCompanies();
	Company retrieveOneCompany(Long id);
	Pageable<Computer> retrievePage(Pageable<Computer> page);
	void saveComputer(Computer c);
	void saveCompany(Company c);
	void deleteComputers(List<Long> ids);
	void deleteComputer(Long id);
}