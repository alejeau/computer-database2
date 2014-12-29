package com.formation.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.formation.computerdatabase.exception.PersistenceException;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;

public enum EntityManagerFactory {

	INSTANCE;

	/*--------------------------------------------------------------
	 * Attributes
	 --------------------------------------------------------------*/
	@SuppressWarnings("rawtypes")
	private Map<String, Dao> daos;
	private Properties properties;

	/*--------------------------------------------------------------
	 * Private constructor
	 --------------------------------------------------------------*/
	private EntityManagerFactory() {

		// Retrieve connection properties
		properties = new Properties();
		try {
			properties.load(EntityManagerFactory.class.getClassLoader().getResourceAsStream("/dbconf.properties"));
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load db configuration properties file: ", e);
		}

		// Load JDBC Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load MySQL Driver: ", e);
		}

		// Init DAOs
		daos = new HashMap<>();
		daos.put(CompanyDao.KEY, new CompanyDaoImpl());
		daos.put(ComputerDao.KEY, new ComputerDaoImpl());
	}

	/*--------------------------------------------------------------
	 * Getters
	 --------------------------------------------------------------*/
	@SuppressWarnings("rawtypes")
	public Dao getDao(String key) {
		return daos.get(key);
	}

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"),
					properties.getProperty("db.password"));
		} catch (SQLException e) {
			throw new PersistenceException("Couldn't connect to jdbc server: ", e);
		}
	}

	public void closeConnection(Connection conn, Statement stmt) {
		closeConnection(conn, stmt, null);
	}

	public void closeConnection(Connection conn, Statement stmt, ResultSet rs) {

		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new PersistenceException("Couldn't close ResultSet: ", e);
		}

		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			throw new PersistenceException("Couldn't close Statement: ", e);
		}

		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			throw new PersistenceException("Couldn't close Connection: ", e);
		}
	}
}
