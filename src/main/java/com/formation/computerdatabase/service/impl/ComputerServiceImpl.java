package com.formation.computerdatabase.service.impl;

import java.util.List;

import com.formation.computerdatabase.commons.Pageable;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.persistence.EntityManagerFactory;
import com.formation.computerdatabase.service.ComputerService;

/**
 * This class is the implementation of the interface {@link ComputerService}
 */
public class ComputerServiceImpl implements ComputerService {

	private ComputerDao computerDao;

	public ComputerServiceImpl() {
		computerDao = (ComputerDao) EntityManagerFactory.INSTANCE.getDao(ComputerDao.KEY);
	}
	
	/**
	 * Create or update a {@link Computer}
	 */
	public void save(Computer c) {
		if (c == null) {
			throw new IllegalArgumentException("Computer cannot be null");
		}
		if (c.getId() != null) {
			computerDao.update(c);
		} else {
			computerDao.create(c);			
		}
	}

	/**
	 * Get all Computers
	 * @return List of {@link Computer}
	 */
	@Override
	public List<Computer> retrieveAll() {
		return computerDao.retrieveAll();
	}

	/**
	 * Retrieve the Computer associated to this id
	 * @return {@link Computer}
	 */
	@Override
	public Computer retrieveOne(Long id) {
		return computerDao.retrieveOne(id);
	}

	/**
	 * Delete the Computer associated to this id
	 */
	public void delete(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		computerDao.delete(id);
	}
	
	/**
	 * Delete all Computers associated to this List of id
	 */
	public void delete(List<Long> ids) {
		if(ids == null || ids.isEmpty()) {
			throw new IllegalArgumentException("Ids cannot be null or empty");
		}
		computerDao.delete(ids);
	}
	
	/**
	 * Retrieve the List of {@link Computer} for a Pageable
	 * @param page
	 * @return {@link Pageable}
	 */
	@Override
	public Pageable<Computer> retrievePage(Pageable<Computer> page) {
		page.setComputers(computerDao.retrieveComputersWithOffsetAndLimit(page.getPage(),page.getSize()));
		
		return page;
	}


}
