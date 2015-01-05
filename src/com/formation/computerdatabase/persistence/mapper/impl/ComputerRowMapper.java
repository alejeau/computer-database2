package com.formation.computerdatabase.persistence.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.mapper.RowMapper;

/**
 * This class is the implementation of RowMapper with the type Company
 */
public class ComputerRowMapper implements RowMapper<Computer> {
	/**
	 * Maps a {@link ResultSet} to a Computer
	 * @param rs
	 * @return {@link Computer}
	 * @throws SQLException
	 */
	@Override
	public Computer mapRow(ResultSet rs) throws SQLException {
		if (rs == null || !rs.next()) {
			return new Computer();
		}
		return computerMapper(rs);
	}
	/**
	 * Maps a {@link ResultSet} to a List of Computer
	 * @param rs
	 * @return List of {@link Computer}
	 * @throws SQLException
	 */
	@Override
	public List<Computer> mapRows(ResultSet rs) throws SQLException {
		List<Computer> computers = new ArrayList<Computer>();
		if (rs == null || !rs.next()) {
			return computers;
		}
		do {
			computers.add(computerMapper(rs));
		} while (rs.next());
		return computers;
	}
	
	 /**
	  * 
	  * @param rs
	  * @return the next computer from the result set with the associated company
	  * @throws SQLException
	  */
	private Computer computerMapper(ResultSet rs) throws SQLException {
		Computer computer = new Computer();
		Company company = new Company();

		computer.setId(rs.getLong(COMPUTER_PREFIX + "id"));
		computer.setName(rs.getString(COMPUTER_PREFIX + "name"));
		computer.setIntroduced(rs.getDate(COMPUTER_PREFIX + "introduced"));
		computer.setDiscontinued(rs.getDate(COMPUTER_PREFIX + "discontinued"));
		company.setId(rs.getLong(COMPANY_PREFIX + "id"));
		company.setName(rs.getString(COMPANY_PREFIX + "name"));
		computer.setCompany(company);
		return computer;
	}

}