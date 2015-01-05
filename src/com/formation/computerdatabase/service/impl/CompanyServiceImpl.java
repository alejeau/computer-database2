package com.formation.computerdatabase.service.impl;

import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.EntityManagerFactory;
import com.formation.computerdatabase.service.CompanyService;

/**
 * This class is the implementation of the interface {@link CompanyService}
 */
public class CompanyServiceImpl implements CompanyService {

	private CompanyDao companyDao;

	public CompanyServiceImpl() {
		companyDao = (CompanyDao) EntityManagerFactory.INSTANCE.getDao(CompanyDao.KEY);
	}
	
	/**
	 * Create or update a {@link Company}
	 */
	public void save(Company c) {
		if (c == null) {
			throw new IllegalArgumentException("Computer cannot be null");
		}
		if (c.getId() != null) {
			companyDao.update(c);
		} else {
			companyDao.create(c);			
		}
	}

	/**
	 * Get all Companies
	 * @return List of {@link Company}
	 */
	@Override
	public List<Company> retrieveAll() {
		return companyDao.retrieveAll();
	}
	
	/**
	 * Retrieve the Company associated to this id
	 * @return {@link Company}
	 */
	@Override
	public Company retrieveOne(Long id) {
		return companyDao.retrieveOne(id);
	}

}
