package com.formation.computerdatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This generic interface provides method to convert database row(s) to model(s)
 * 
 * @param <T> the type of your model
 */
public interface RowMapper<T> {

	static final String COMPUTER_PREFIX = "cp.";
	static final String COMPANY_PREFIX = "ca.";

	T mapRow(ResultSet rs) throws SQLException;

	List<T> mapRows(ResultSet rs) throws SQLException;

}
