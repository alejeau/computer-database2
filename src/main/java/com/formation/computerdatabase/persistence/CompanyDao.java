package com.formation.computerdatabase.persistence;

import com.formation.computerdatabase.model.Company;
/**
 * This interface extends the default DAO Interface with the type Company
 */
public interface CompanyDao extends Dao<Company> {
	static final String KEY = "companyDao";

}
