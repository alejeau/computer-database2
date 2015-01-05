package com.formation.computerdatabase.persistence.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.mapper.RowMapper;

/**
 * This class is the implementation of RowMapper with the type Company
 */
public class CompanyRowMapper implements RowMapper<Company> {
	/**
	 * Maps a {@link ResultSet} to a Company
	 * @param rs
	 * @return {@link Company}
	 * @throws SQLException
	 */
	@Override
	public Company mapRow(ResultSet rs) throws SQLException {
		if (rs == null || !rs.next()) {
			return new Company();
		}
		return companyMapper(rs);
	}
	
	/**
	 * Maps a {@link ResultSet} to a List of Company
	 * @param rs
	 * @return List of {@link Company}
	 * @throws SQLException
	 */
	@Override
	public List<Company> mapRows(ResultSet rs) throws SQLException {
		List<Company> companies = new ArrayList<Company>();
		if (rs == null || !rs.next()) {
			return companies;
		}
		do {
			companies.add(companyMapper(rs));
		} while (rs.next());
		return companies;
	}
	
	private Company companyMapper(ResultSet rs) throws SQLException {
		Company company = new Company();

		company.setId(rs.getLong(COMPANY_PREFIX + "id"));
		company.setName(rs.getString(COMPANY_PREFIX + "name"));
		return company;
	}

}
