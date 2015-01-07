package com.formation.computerdatabase.persistence.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.formation.computerdatabase.model.Company;

public class CompanyRowMapperTest {
	

	@Test
	public void rowMapperSingleComputer() throws SQLException {
		
		final ResultSet rsMock = Mockito.mock(ResultSet.class);
		Mockito.when(rsMock.getString("id")).thenReturn("12");
		Mockito.when(rsMock.getString("name")).thenReturn("mockCompany");
		Mockito.when(rsMock.next()).thenReturn(true).thenReturn(false);
		
		CompanyRowMapper companyRowMapper = new CompanyRowMapper();
		Company computerMock = companyRowMapper.mapRow(rsMock);
		
		Company company = new Company(3L, "mockCompany");
			
		Assert.assertEquals(company, computerMock);
		
	}
	
}
