package com.formation.computerdatabase.persistence.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;

public class ComputerRowMapperTest {
	
	@Test
	public void rowMapperSingleComputer() throws SQLException {
		
		final ResultSet rsMock = Mockito.mock(ResultSet.class);
		Mockito.when(rsMock.getString("id")).thenReturn("12");
		Mockito.when(rsMock.getString("name")).thenReturn("mockComputer");
		Mockito.when(rsMock.getString("introduced")).thenReturn("1991-01-12");
		Mockito.when(rsMock.getString("discontinued")).thenReturn("1992-03-12");
		Mockito.when(rsMock.getString("company_id")).thenReturn("3");
		Mockito.when(rsMock.next()).thenReturn(true).thenReturn(false);
		
		ComputerRowMapper computerRowMapper = new ComputerRowMapper();
		Computer computerMock = computerRowMapper.mapRow(rsMock);
		
		Computer.Builder computerBuilder = Computer.builder("mockComputer");
		Company company = new Company();
		company.setId(3L);
		computerBuilder.introduced("1991-01-12").discontinued("1992-03-12").company(company).id(3L);
			
		Assert.assertEquals(computerBuilder, computerMock);
		
	}	
	
	@Test
	public void rowMapperListComputer() throws SQLException {
		
		final ResultSet rsMock = Mockito.mock(ResultSet.class);
		Mockito.when(rsMock.getString("id")).thenReturn("12").thenReturn("24");
		Mockito.when(rsMock.getString("name")).thenReturn("mockComputer").thenReturn("mockComputer2");
		Mockito.when(rsMock.getString("introduced")).thenReturn("1991-01-12").thenReturn("2000-01-12");
		Mockito.when(rsMock.getString("discontinued")).thenReturn("1992-03-12").thenReturn("2002-03-12");
		Mockito.when(rsMock.getString("company_id")).thenReturn("3").thenReturn("4");
		Mockito.when(rsMock.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		
		ComputerRowMapper computerRowMapper = new ComputerRowMapper();
		Computer computerMock = computerRowMapper.mapRow(rsMock);
		
		Computer.Builder computerBuilder = Computer.builder("mockComputer");
		Company company = new Company();
		company.setId(3L);
		computerBuilder.introduced("1991-01-12").discontinued("1992-03-12").company(company).id(3L);
			
		Assert.assertEquals(computerBuilder, computerMock);
		
	}
	

}
