package com.formation.computerdatabase.persistence.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.formation.computerdatabase.model.Company;

public class CompanyRowMapperTest {
	

	@Test
	public void rowMapperSingleCompany() throws SQLException {
		
		final ResultSet rsMock = Mockito.mock(ResultSet.class);
		Mockito.when(rsMock.getLong("ca.id")).thenReturn(12L);
		Mockito.when(rsMock.getString("ca.name")).thenReturn("mockCompany");
		Mockito.when(rsMock.next()).thenReturn(true).thenReturn(false);
		
		CompanyRowMapper companyRowMapper = new CompanyRowMapper();
		Company computerMock = companyRowMapper.mapRow(rsMock);
			
		Assert.assertEquals(new Company(12L, "mockCompany"), computerMock);
		
	}
	
	@Test
	public void rowMapperListCompany() throws SQLException {
		
		final ResultSet rsMock = Mockito.mock(ResultSet.class);
		Mockito.when(rsMock.getLong("ca.id")).thenReturn(12L).thenReturn(24L);
		Mockito.when(rsMock.getString("ca.name")).thenReturn("mockCompany").thenReturn("mockCompany2");
		Mockito.when(rsMock.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		
		CompanyRowMapper companyRowMapper = new CompanyRowMapper();
		List<Company> computerMock = companyRowMapper.mapRows(rsMock);
			
		List<Company> companies = new ArrayList<Company>();
		companies.add(new Company(12L, "mockCompany"));
		companies.add(new Company(24L, "mockCompany2"));
		
		Assert.assertEquals(companies, computerMock);
		
	}
	
}
