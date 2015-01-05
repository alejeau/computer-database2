package com.formation.computerdatabase.commons;

import java.util.List;

public class Pageable<T> {
	
	private List<T> computers;
	private int page = 1;
	private int size = 10;

	public Pageable(int page, int size) {
		super();
		this.page = page;
		this.size = size;
	}
	
	/*--------------------------------------------------------------
     * Getter/Setter
     --------------------------------------------------------------*/
	public List<T> getComputers() {
		return computers;
	}
	public void setComputers(List<T> computers) {
		this.computers = computers;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

}
