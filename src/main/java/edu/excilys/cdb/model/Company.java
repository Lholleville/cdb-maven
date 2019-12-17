package main.java.edu.excilys.cdb.model;

import java.util.ArrayList;

public class Company {
	
	private long id = 0;
	
	private String name = "";
	
	private ArrayList<Computer> listComputer = new ArrayList<Computer>();
	
	public Company() {}
	
	public Company(long id, String name) {
		this.id = id;
		this.name = name;
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

	public ArrayList<Computer> getListComputer() {
		return listComputer;
	}

	public void setListComputer(ArrayList<Computer> listComputer) {
		this.listComputer = listComputer;
	}
	
	public void addComputer(Computer computer) {
		this.listComputer.add(computer);
	}
	
	public Computer getComputer(int i) {
		return this.listComputer.get(i);
	}
	
	@Override
	public String toString() {
		return this.getId() + " " + this.getName(); 
	}

}
