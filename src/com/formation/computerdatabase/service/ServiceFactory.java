package com.formation.computerdatabase.service;

import java.util.HashMap;
import java.util.Map;

import com.formation.computerdatabase.service.impl.CompanyServiceImpl;
import com.formation.computerdatabase.service.impl.ComputerServiceImpl;


/**
 * This singleton class is used to store the services
 */
public enum ServiceFactory {
	INSTANCE;
	
	private Map<String, Service<?>> services;
	
	private ServiceFactory() {
		services = new HashMap<>();
		services.put(CompanyService.KEY, new CompanyServiceImpl());
		services.put(ComputerService.KEY, new ComputerServiceImpl());
	}
	
	/**
	 * Retrieve the Service associated with the key
	 * @param key
	 * @return {@link Service}
	 */
	public Service<?> getService(String key) {
		return services.get(key);
	}

}
