package com.formation.computerdatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RowMapper<T> {

	T mapRow(ResultSet rs) throws SQLException;

	T mapRow(ResultSet rs, String prefix) throws SQLException;

	List<T> mapRows(ResultSet rs) throws SQLException;

	List<T> mapRows(ResultSet rs, String prefix) throws SQLException;

}
