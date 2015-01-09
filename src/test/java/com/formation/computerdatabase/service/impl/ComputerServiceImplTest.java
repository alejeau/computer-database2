package com.formation.computerdatabase.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.ComputerDao;

public class ComputerServiceImplTest {
	
	@Mock
	private ComputerDao computerDaoMock;

	@InjectMocks
	private ComputerServiceImpl computerService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	} 

	@Test
	public void retrieveList() {

		List<Computer> computersMock = new ArrayList<Computer>();
		computersMock.add(Computer.builder("mockComputer")
				.introduced("1991-01-12")
				.discontinued("1992-03-12")
				.company(new Company(3L, "mockCompany"))
				.id(12L).build());
		computersMock.add(Computer.builder("mockComputer2")
				.introduced("1991-01-12")
				.discontinued("1992-03-12")
				.company(new Company(3L, "mockCompany"))
				.id(12L).build());
		Mockito.when(computerDaoMock.retrieveAll()).thenReturn(computersMock);

		computerService = new ComputerServiceImpl(computerDaoMock);
		List<Computer> computers = computerService.retrieveAll();

		Assert.assertEquals(computers, computersMock);
	}

	@Test
	public void retrieveOne() {

		Computer computerMock= Computer.builder("mockComputer")
				.introduced("1991-01-12")
				.discontinued("1992-03-12")
				.company(new Company(3L, "mockCompany"))
				.id(12L).build();
		Mockito.when(computerDaoMock.retrieveOne(12L)).thenReturn(computerMock);

		computerService = new ComputerServiceImpl(computerDaoMock);
		Computer computer = computerService.retrieveOne(12L);

		Assert.assertEquals(computerMock, computer);
	}

}
