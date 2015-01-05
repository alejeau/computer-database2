package com.formation.computerdatabase.model;

import java.util.Date;

import com.formation.computerdatabase.util.DateUtil;

public class Computer {

	/*--------------------------------------------------------------
	 * Attributes
	 --------------------------------------------------------------*/
	private Long id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Company company;

	/*--------------------------------------------------------------
	 * Constructors
	 --------------------------------------------------------------*/

	public Computer() {
	}

	public Computer(String name) {
		super();
		this.name = name;
	}

	public Computer(Long id, String name, Date introduced, Date discontinued, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	/*--------------------------------------------------------------
	 * toString/hashCode/equals
	 --------------------------------------------------------------*/

	@Override
	public String toString() {
            return new StringBuilder("Computer [id=").append(id)
                             .append(", name=").append(name)
                             .append(", introduced=").append(introduced)
                             .append(", discontinued=").append(discontinued)
                             .append(", company=").append(company)
                             .append("]").toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (this.id == other.getId())
			return true;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/*--------------------------------------------------------------
	 * Getter/Setter
	 --------------------------------------------------------------*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id != null)
			this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = DateUtil.stringToDate(introduced);
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}	

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = DateUtil.stringToDate(discontinued);
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	/*--------------------------------------------------------------
	 * Builder
	 --------------------------------------------------------------*/

	public static Builder builder(String name) {
		return new Builder(name);
	}

	public static class Builder {
		private Computer c;

		private Builder(String name) {
			c = new Computer(name);
		}

		public Builder id(Long id) {
			c.id = id;
			return this;
		}

		public Builder name(String name) {
			c.name = name;
			return this;
		}

		public Builder introduced(String introduced) {			
			c.introduced = DateUtil.stringToDate(introduced);
			return this;
		}

		public Builder introduced(Date introduced) {
			c.introduced = introduced;
			return this;
		}

		public Builder discontinued(String discontinued) {
			c.introduced = DateUtil.stringToDate(discontinued);
			return this;
		}

		public Builder discontinued(Date discontinued) {
			c.discontinued = discontinued;
			return this;
		}

		public Builder company(Company company) {
			c.company = company;
			return this;
		}

		public Computer build() {
			return c;
		}
	}

}
