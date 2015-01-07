package com.formation.computerdatabase.persistence.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;

public class ComputerRowMapperTest {
	
	@Test
	public void rowMapperSingleComputer() throws SQLException {
		
		final ResultSet rsMock = Mockito.mock(ResultSet.class);
		Mockito.when(rsMock.getLong("cp.id")).thenReturn(12L);
		Mockito.when(rsMock.getString("cp.name")).thenReturn("mockComputer");
		Mockito.when(rsMock.getString("cp.introduced")).thenReturn("1991-01-12");
		Mockito.when(rsMock.getString("cp.discontinued")).thenReturn("1992-03-12");
		Mockito.when(rsMock.getLong("ca.id")).thenReturn(3L);
		Mockito.when(rsMock.getString("ca.name")).thenReturn("mockCompany");
		Mockito.when(rsMock.next()).thenReturn(true).thenReturn(false);
		
		Computer computerMock = new ComputerRowMapper().mapRow(rsMock);
		
		Company company = new Company();
		company.setId(3L);
			
		Assert.assertEquals(Computer.builder("mockComputer")
				.introduced("1991-01-12")
				.discontinued("1992-03-12")
				.company(new Company(3L, "mockCompany"))
				.id(12L).build(), computerMock);
		
	}	
	
	@Test
	public void rowMapperListComputer() throws SQLException {
		
		final ResultSet rsMock = Mockito.mock(ResultSet.class);
		Mockito.when(rsMock.getLong("cp.id")).thenReturn(12L).thenReturn(24L);
		Mockito.when(rsMock.getString("cp.name")).thenReturn("mockComputer").thenReturn("mockComputer2");
		Mockito.when(rsMock.getString("cp.introduced")).thenReturn("1991-01-12").thenReturn("2000-01-12");
		Mockito.when(rsMock.getString("cp.discontinued")).thenReturn("1992-03-12").thenReturn("2002-03-12");
		Mockito.when(rsMock.getLong("ca.id")).thenReturn(3L).thenReturn(4L);
		Mockito.when(rsMock.getString("ca.name")).thenReturn("mockCompany").thenReturn("mockCompany2");
		Mockito.when(rsMock.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		List<Computer> computerMock = (List<Computer>) new ComputerRowMapper().mapRows(rsMock);
		
		List<Computer> computerList = new ArrayList<Computer>();

		computerList.add(Computer.builder("mockComputer")
				.introduced("1991-01-12")
				.discontinued("1992-03-12")
				.company(new Company(3L, "mockCompany"))
				.id(12L).build());
		
		computerList.add(Computer.builder("mockComputer2")
				.introduced("2000-01-12")
				.discontinued("2002-03-12")
				.company(new Company(4L, "mockCompany2"))
				.id(24L).build());
			
		Assert.assertEquals(computerList, computerMock);
		
	}
}
