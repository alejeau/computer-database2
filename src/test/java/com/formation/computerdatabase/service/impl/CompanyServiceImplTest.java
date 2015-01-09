package com.formation.computerdatabase.service.impl;

import java.lang.reflect.Field;
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
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;

public class CompanyServiceImplTest {

	@Mock
	private CompanyDao companyDaoMock;

	@InjectMocks
	private CompanyServiceImpl companyServiceImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	} 

	@Test
	public void retrieveList() {

		List<Company> companysMock = new ArrayList<Company>();
		companysMock.add(new Company(3L, "mockCompany"));
		companysMock.add(new Company(4L, "mockCompany2"));
		Mockito.when(companyDaoMock.retrieveAll()).thenReturn(companysMock);

		companyServiceImpl = new CompanyServiceImpl();
		try {
			Field companyDaoField = CompanyServiceImpl.class.getDeclaredField("companyDao");
			companyDaoField.setAccessible(true);
			try {
				companyDaoField.set(companyServiceImpl, companyDaoMock);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				System.out.println("cannot change the value of companyDao inside companyServiceImpl");
			}
		} catch (NoSuchFieldException | SecurityException e) {
			System.out.println("companyDao not found in the fields of companyServiceImpl.");
		}
		
		List<Company> companys = companyServiceImpl.retrieveAll();

		Assert.assertEquals(companys, companysMock);
	}

	@Test
	public void retrieveOne() {

		companyDaoMock = Mockito.mock(CompanyDaoImpl.class);
		Company companyMock= new Company(3L, "mockCompany");

		Mockito.when(companyDaoMock.retrieveOne(12L)).thenReturn(companyMock);

		companyServiceImpl = new CompanyServiceImpl();
		try {
			Field companyDaoField = CompanyServiceImpl.class.getDeclaredField("companyDao");
			companyDaoField.setAccessible(true);
			try {
				companyDaoField.set(companyServiceImpl, companyDaoMock);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			System.out.println("companyDao not found in the fields of companyServiceImpl.");
		}	
		
		Company company = companyServiceImpl.retrieveOne(12L);

		Assert.assertEquals(companyMock, company);
	}


}
