package com.formation.computerdatabase.persistence.impl;

import org.junit.Assert;
import org.junit.Test;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.EntityManagerFactoryTest;

public class CompanyDaoImplTest {
	
	private final CompanyDao companyDaoTest = (CompanyDao) EntityManagerFactoryTest.INSTANCE.getDao(CompanyDao.KEY);
	
	@Test
	public void retrieveOne() {
		Company companyTest = companyDaoTest.retrieveOne(1L);
		
		Assert.assertEquals(companyTest, new Company(1L, "Apple Inc."));
	}
	
	@Test
	public void retrieveSome() {
		Company companyTest = companyDaoTest.retrieveOne(1L);
		
		Assert.assertEquals(companyTest, new Company(1L, "Apple Inc."));
	}

}
