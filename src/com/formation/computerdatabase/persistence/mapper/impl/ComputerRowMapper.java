package com.formation.computerdatabase.persistence.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.mapper.RowMapper;

public class ComputerRowMapper implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet rs) throws SQLException {
		Computer computer = new Computer();
		if (rs == null || !rs.next()) {
			return computer;
		}
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

	@Override
	public List<Computer> mapRows(ResultSet rs) throws SQLException {
		List<Computer> computers = new ArrayList<Computer>();
		if (rs == null || !rs.next()) {
			return computers;
		}
		do {
			Computer computer = new Computer();
			Company company = new Company();

			computer.setId(rs.getLong(COMPUTER_PREFIX + "id"));
			computer.setName(rs.getString(COMPUTER_PREFIX + "name"));
			computer.setIntroduced(rs.getDate(COMPUTER_PREFIX + "introduced"));
			computer.setDiscontinued(rs.getDate(COMPUTER_PREFIX + "discontinued"));
			company.setId(rs.getLong(COMPANY_PREFIX + "id"));
			company.setName(rs.getString(COMPANY_PREFIX + "name"));
			computer.setCompany(company);
			computers.add(computer);
		} while (rs.next());
		return computers;
	}

}