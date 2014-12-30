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

public class CompanyDaoImpl implements CompanyDao {

	private RowMapper<Company> companyRowMapper = new CompanyRowMapper();

	private static final String RETRIEVE_ONE = "select ca.id, ca.name from company ca where ca.id = ?;";

	@Override
	public Company retrieveOne(Long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Company company = null;

		try {
			conn = EntityManagerFactory.INSTANCE.getConnection();
			stmt = conn.prepareStatement(RETRIEVE_ONE);
			stmt.setLong(1, id);
			rs = stmt.executeQuery(RETRIEVE_ONE);
			company = companyRowMapper.mapRow(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			EntityManagerFactory.INSTANCE.closeConnection(conn, stmt, rs);
		}
		return company;
	}

	private static final String RETRIEVE_ALL = "select ca.id, ca.name from company ca;";

	@Override
	public List<Company> retrieveAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Company> companies = null;

		try {
			conn = EntityManagerFactory.INSTANCE.getConnection();
			stmt = conn.prepareStatement(RETRIEVE_ALL);
			rs = stmt.executeQuery(RETRIEVE_ALL);
			companies = companyRowMapper.mapRows(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			EntityManagerFactory.INSTANCE.closeConnection(conn, stmt, rs);
		}
		return companies;
	}

}
