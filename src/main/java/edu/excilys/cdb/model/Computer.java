package main.java.edu.excilys.cdb.model;

import java.time.*;
import java.util.Date;
import java.util.Optional;

public class Computer {

	private long id;
	
	private String name;
	
	private LocalDate introductionDate;
	
	private LocalDate discontinuedDate;
	
	private Company company;
	
	public Computer() {}
	
	public Computer(long id, String name, LocalDate introductionDate, LocalDate discontinuedDate, Company company) {
		this.id = id;
		this.name = name;
		this.introductionDate = introductionDate;
		this.discontinuedDate = discontinuedDate;
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroductionDate() {
		return introductionDate;
	}

	public void setIntroductionDate(LocalDate introductionDate) {
		this.introductionDate = introductionDate;
	}

	public LocalDate getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(LocalDate discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}	
	
	public String toString() {
		return this.getId() 
				+ " " + this.getName() 
				+ " " + this.getIntroductionDate() 
				+ " " +  this.getDiscontinuedDate() 
				+ " " +  this.getCompany().getName();
	}
}
