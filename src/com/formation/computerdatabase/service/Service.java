package com.formation.computerdatabase.service;

import java.util.List;
import com.formation.computerdatabase.exception.ServiceStubException;

/**
 * This generic interface provides the default methods for a Service
 *
 * @param <T> the type of your Service
 */
public interface Service<T> {
	
	static final String KEY = "invalid";
	
	default void save(T o) {
		throw new ServiceStubException();
	}
	default List<T> retrieveAll() {
		throw new ServiceStubException();
	}
	default T retrieveOne(Long id) {
		throw new ServiceStubException();
	}
	default void delete(Long id) {
		throw new ServiceStubException();
	}
	default void delete(List<Long> ids) {
		throw new ServiceStubException();
	}

}