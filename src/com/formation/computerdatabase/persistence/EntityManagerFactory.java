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

/**
 * This singleton class is used to manage connection to the database and retrieve DAOs
 */
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
			properties.load(EntityManagerFactory.class.getClassLoader().getResourceAsStream("dbconf.properties"));
		} catch (Exception e) {
			String message = new StringBuilder("Couldn't load db configuration properties file: ").append(
					e.getMessage()).toString();
			System.err.println(message);
			throw new RuntimeException(message, e);
		}

		// Load JDBC Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			String message = new StringBuilder("Couldn't load db configuration properties file: ").append(
					e.getMessage()).toString();
			System.err.println(message);
			throw new RuntimeException(message, e);
		}

		// Init DAOs
		daos = new HashMap<>();
		daos.put(CompanyDao.KEY, new CompanyDaoImpl());
		daos.put(ComputerDao.KEY, new ComputerDaoImpl());
	}

	/*--------------------------------------------------------------
	 * Getters
	 --------------------------------------------------------------*/
	/**
	 * Retrieve the DAO associated to the given key
	 * @param the DAO Key
	 * @return the associated DAO
	 */
	@SuppressWarnings("rawtypes")
	public Dao getDao(String key) {
		return daos.get(key);
	}

	/**
	 * Retrieve the database connection 
	 * @return the connection
	 */
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"),
					properties.getProperty("db.password"));
		} catch (SQLException e) {
			String message = new StringBuilder("Couldn't load jdbc connection: ").append(e.getMessage()).toString();
			System.err.println(message);
			throw new PersistenceException(message, e);
		}
	}

	/**
	 * Close the given connection and statement
	 * @param connection
	 * @param statement
	 */
	public void closeConnection(Connection conn, Statement stmt) {
		closeConnection(conn, stmt, null);
	}

	/**
	 * Close the given connection, statement and result set
	 * @param connection
	 * @param statement
	 * @param resultSet
	 */
	public void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			String message = new StringBuilder("Couldn't close jdbc connection: ").append(e.getMessage()).toString();
			throw new PersistenceException(message, e);
		}
	}

}
