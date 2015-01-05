package com.formation.computerdatabase.service;

import com.formation.computerdatabase.commons.Pageable;
import com.formation.computerdatabase.exception.ServiceStubException;
import com.formation.computerdatabase.model.Computer;

/**
 * This interface extends the default Service Interface with the type Computer
 */
public interface ComputerService extends Service<Computer> {
	static final String KEY = "computerService";

	default Pageable<Computer> retrievePage(Pageable<Computer> page) {
		throw new ServiceStubException();	
	}
}
