package com.formation.computerdatabase.persistence;

import java.util.List;

import com.formation.computerdatabase.exception.DaoStubException;

/**
 * This generic interface provides the default methods for a DAO
 *
 * @param <T> the type of your DAO
 */
public interface Dao<T> {

	static final String KEY = "invalid";

	default void create(T o) {
		throw new DaoStubException();
	}
	default List<T> retrieveAll() {
		throw new DaoStubException();
	}

	default T retrieveOne(Long id) {
		throw new DaoStubException();
	}

	default void update(T o) {
		throw new DaoStubException();
	}

	default void delete(Long id) {
		throw new DaoStubException();
	}

	default void delete(List<Long> ids) {
		throw new DaoStubException();
	}

}
