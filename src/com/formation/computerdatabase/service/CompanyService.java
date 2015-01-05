package com.formation.computerdatabase.service;

import com.formation.computerdatabase.model.Company;

/**
 * This interface extends the default Service Interface with the type Company
 */
public interface CompanyService extends Service<Company> {
	static final String KEY = "companyService";
}
