package com.formation.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.formation.computerdatabase.exception.PersistenceException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.EntityManagerFactory;
import com.formation.computerdatabase.persistence.mapper.RowMapper;
import com.formation.computerdatabase.persistence.mapper.impl.CompanyRowMapper;

/**
 * This class is the implementation of the interface {@link CompanyDao}
 */
public class CompanyDaoImpl implements CompanyDao {

	private RowMapper<Company> companyRowMapper = new CompanyRowMapper();

	private static final String RETRIEVE_ONE = "select ca.id, ca.name from company ca where ca.id = ?;";

	/**
	 * Retrieve one Company from its id
	 * use PreparedStatement to avoid SQL injection
	 * @return {@link Company}
	 */
	@Override
	public Company retrieveOne(Long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Company company = null;

		try {
			conn = EntityManagerFactory.INSTANCE.getConnection();
			stmt = conn.prepareStatement(RETRIEVE_ONE);
			System.out.println("id: "+id);
			if(id !=  null && id > 0) {
				stmt.setLong(1, id);
			} else {
				stmt.setNull(1, java.sql.Types.NULL);
			}
			rs = stmt.executeQuery();
			company = companyRowMapper.mapRow(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			EntityManagerFactory.INSTANCE.closeConnection(conn, stmt, rs);
		}
		return company;
	}

	private static final String RETRIEVE_ALL = "select ca.id, ca.name from company ca;";

	
	/**
	 * Retrieve all the Company from the database
	 * @return List of {@link Company}
	 */
	@Override
	public List<Company> retrieveAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Company> companies = null;

		try {
			conn = EntityManagerFactory.INSTANCE.getConnection();
			stmt = conn.prepareStatement(RETRIEVE_ALL);
			rs = stmt.executeQuery();
			companies = companyRowMapper.mapRows(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			EntityManagerFactory.INSTANCE.closeConnection(conn, stmt, rs);
		}
		return companies;
	}

}
