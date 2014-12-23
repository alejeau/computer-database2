package com.formation.computerdatabase.persistence;

import com.formation.computerdatabase.model.Computer;

public interface ComputerDao extends Dao<Computer> {
	static final String KEY = "computerDao";
}
