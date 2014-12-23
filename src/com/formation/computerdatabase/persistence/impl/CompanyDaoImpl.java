package com.formation.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.exception.PersistenceException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.EntityManagerFactory;
import com.formation.computerdatabase.persistence.mapper.RowMapper;

public class CompanyDaoImpl implements CompanyDao {

	protected static class CompanyRowMapper implements RowMapper<Company> {

		@Override
		public Company mapRow(ResultSet rs) throws SQLException {
			return mapRow(rs, "");
		}

		@Override
		public Company mapRow(ResultSet rs, String prefix) throws SQLException {
			if (prefix == null) {
				throw new IllegalArgumentException("prefix cannot be null");
			}
			Company company = new Company();
			if (rs == null || !rs.next()) {
				return company;
			}
			company.setId(rs.getLong(prefix + "ID"));
			company.setName(rs.getString(prefix + "NAME"));
			return company;
		}

		@Override
		public List<Company> mapRows(ResultSet rs) throws SQLException {
			return mapRows(rs, "");
		}

		@Override
		public List<Company> mapRows(ResultSet rs, String prefix) throws SQLException {
			if (prefix == null) {
				throw new IllegalArgumentException("prefix cannot be null");
			}
			List<Company> companies = new ArrayList<Company>();
			if (rs == null || !rs.next()) {
				return companies;
			}
			do {
				companies.add(mapRow(rs, prefix));
			} while (rs.next());
			return companies;
		}

	}

	private static final String RETRIEVE_ONE = "select * from computer where id = ?;";

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
			company = getRowMapper().mapRow(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			EntityManagerFactory.INSTANCE.closeConnection(conn, stmt, rs);
		}
		return company;
	}
	
	private static final String RETRIEVE_ALL = "select * from computer;";

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
			companies = getRowMapper().mapRows(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage(), e);
		} finally {
			EntityManagerFactory.INSTANCE.closeConnection(conn, stmt, rs);
		}
		return companies;
	}

	@Override
	public RowMapper<Company> getRowMapper() {
		return new CompanyRowMapper();
	}
}
