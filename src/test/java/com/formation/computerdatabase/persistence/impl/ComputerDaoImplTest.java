package com.formation.computerdatabase.persistence.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.persistence.EntityManagerFactoryTest;
import com.formation.computerdatabase.util.DateUtil;
/**
 * ----> number 1 <---- is for lecture only
 * 
 * the rest can be edited
 * @author pif
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ComputerDaoImplTest {
	
	private final ComputerDao computerDaoTest = (ComputerDao) EntityManagerFactoryTest.INSTANCE.getDao(ComputerDao.KEY);
	
	private static Long idTest;

	@Before
	public void setup() {
	} 

	@Test
	public void retrieveOne() {
		Computer computerTest = computerDaoTest.retrieveOne(1L);
		
		String nullDate = null;
		
		Assert.assertEquals(computerTest, Computer.builder("MacBook Pro 15.4 inch")
					.introduced(DateUtil.stringToDate(nullDate))
					.discontinued(nullDate)
					.company(new Company(1L, "Apple Inc."))
					.id(1L).build());
	}
	
	@Test
	public void retrieveSome() {
		Computer computerTest = computerDaoTest.retrieveOne(1L);
		
		String nullDate = null;
		
		Assert.assertEquals(computerTest, Computer.builder("MacBook Pro 15.4 inch")
					.introduced(DateUtil.stringToDate(nullDate))
					.discontinued(nullDate)
					.company(new Company(1L, "Apple Inc."))
					.id(1L).build());
	}
	
	/**
	 * use retrieveOne() that is also tested
	 */
	@Test
	public void A_create() {
		String nullDate = null;
		
		Computer computerTest = Computer.builder("MacBook Test")
				.introduced(DateUtil.stringToDate(nullDate))
				.discontinued(nullDate)
				.company(new Company(1L, "Apple Inc.")).build();
		
		computerDaoTest.create(computerTest);		
		
		Computer computerRetrieve = computerDaoTest.retrieveOne(computerTest.getId());
		
		Assert.assertEquals(computerTest, computerRetrieve);
		
		idTest = computerTest.getId();
		
		System.out.println("computer id :" + idTest);
	}
	
	/**
	 * use retrieveOne() that is also tested
	 */
	@Test
	public void B_update() {
		Computer computerTest = Computer.builder("MacBook Test")
				.introduced(DateUtil.stringToDate("1990-01-01"))
				.discontinued("1999-01-02")
				.company(new Company(1L, "Apple Inc."))
				.id(idTest).build();
		
		System.out.println("computer id :" + idTest);
		
		computerDaoTest.update(computerTest);		
		
		Computer computerRetrieve = computerDaoTest.retrieveOne(computerTest.getId());
		
		Assert.assertEquals(computerTest, computerRetrieve);
	}
	
	
	/**
	 * use retrieveOne() that is also tested
	 */	
	@Test
	public void C_delete() {
		Computer computerTest = Computer.builder("MacBook Test")
				.introduced(DateUtil.stringToDate("1990-01-01"))
				.discontinued("1999-01-02")
				.company(new Company(1L, "Apple Inc.")).id(idTest).build();
		
		System.out.println("computer id :" + idTest);
		
		computerDaoTest.delete(computerTest.getId());		
		
		Computer computerRetrieve = computerDaoTest.retrieveOne(computerTest.getId());
		
		Assert.assertEquals(computerRetrieve, null);
	}
}
