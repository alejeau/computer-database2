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
 * It connects to a second database for test
 */
public enum EntityManagerFactoryTest {

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
	private EntityManagerFactoryTest() {
		// Retrieve connection properties
		properties = new Properties();
		try {
			properties.load(EntityManagerFactory.class.getClassLoader().getResourceAsStream("dbconf2.properties"));
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
			throw new PersistenceException("Couldn't connect to jdbc server: ", e);
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

