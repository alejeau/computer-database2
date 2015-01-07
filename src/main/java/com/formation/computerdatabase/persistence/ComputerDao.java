package com.formation.computerdatabase.persistence;

import java.util.List;

import com.formation.computerdatabase.exception.DaoStubException;
import com.formation.computerdatabase.model.Computer;

/**
 * This interface extends the default DAO Interface with the type Computer
 */
public interface ComputerDao extends Dao<Computer> {
	static final String KEY = "computerDao";

	default List<Computer> retrieveComputersWithOffsetAndLimit(int page, int size) {
		throw new DaoStubException(); 
	}
}