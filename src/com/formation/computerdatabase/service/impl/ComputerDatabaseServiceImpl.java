package com.formation.computerdatabase.service.impl;

import java.util.List;

import com.formation.computerdatabase.commons.Pageable;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.persistence.EntityManagerFactory;
import com.formation.computerdatabase.service.ComputerDatabaseService;

public enum ComputerDatabaseServiceImpl implements ComputerDatabaseService {
	INSTANCE;
	
	private EntityManagerFactory emf;
	
	private CompanyDao companyDao;
	private ComputerDao computerDao;
	
	private ComputerDatabaseServiceImpl() {
		emf = EntityManagerFactory.INSTANCE;
		
		computerDao = (ComputerDao) emf.getDao(ComputerDao.KEY);
		companyDao 	= (CompanyDao) emf.getDao(CompanyDao.KEY);
	}

	@Override
	public List<Computer> retrieveAllComputers() {
		return computerDao.retrieveAll();
	}

	@Override
	public Computer retrieveOneComputer(Long id) {
		return computerDao.retrieveOne(id);
	}

	@Override
	public List<Company> retrieveAllCompanies() {
		return companyDao.retrieveAll();
	}

	@Override
	public Company retrieveOneCompany(Long id) {
		return companyDao.retrieveOne(id);
	}
	
	@Override
	public Pageable<Computer> retrievePage(Pageable<Computer> page) {
		page.setComputers(computerDao.retrieveComputersWithOffsetAndLimit(page.getPage(),page.getSize()));
		
		return page;
	}

	@Override
	public void saveComputer(Computer c) {
		if (c == null) {
			throw new IllegalArgumentException("Computer cannot be null");
		}
		if (c.getId() != null) {
			computerDao.update(c);
		} else {
			computerDao.create(c);			
		}
	}

	@Override
	public void saveCompany(Company c) {
		if (c == null) {
			throw new IllegalArgumentException("Computer cannot be null");
		}
		if (c.getId() != null) {
			companyDao.update(c);
		} else {
			companyDao.create(c);
		}
	}

	@Override
	public void deleteComputers(List<Long> ids) {
		if(ids == null || ids.isEmpty()) {
			throw new IllegalArgumentException("Ids cannot be null or empty");
		}
		computerDao.delete(ids);
	}
	
	@Override
	public void deleteComputer(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		computerDao.delete(id);
	}
	
}
