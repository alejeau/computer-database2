package com.formation.computerdatabase.service;

import com.formation.computerdatabase.service.impl.ComputerDatabaseServiceImpl;

public enum ServiceFactory {
	INSTANCE;
	
	ComputerDatabaseService computerDatabaseService;
	
	private ServiceFactory() {
		computerDatabaseService = ComputerDatabaseServiceImpl.INSTANCE;
	}
	
	public ComputerDatabaseService getService() {
		return computerDatabaseService;
	}

}
