package edu.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Optional;

import edu.excilys.cdb.model.Company;
import edu.excilys.cdb.model.Computer;

public class CompanyDAO{

	private Connection connect = ConnectionMYSQL.getInstance();
	
	private final String SQL_FIND_ALL_COMPANIES = "SELECT id, name FROM company";
	private final String SQL_FIND_COMPANY = "SELECT id, name FROM company WHERE id = ?";
	
	public ArrayList<Company> list(){
		ArrayList<Company> list = new ArrayList<Company>();
		
		try {
			PreparedStatement stmt = this.connect.prepareStatement(SQL_FIND_ALL_COMPANIES);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				list.add(this.map(result));
			}
			//ConnectionMYSQL.close(result, stmt, this.connect);
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}

	public Optional<Company> find(long id) {
		Company company = new Company();
		try {
			PreparedStatement stmt = this.connect.prepareStatement(SQL_FIND_COMPANY);
			stmt.setLong(1, id);
			ResultSet result = stmt.executeQuery();
			if(result.first()) {
				company = this.map(result);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(company);
	}
	
	private Company map(ResultSet r) {
		Company company = new Company();
		try {
			company = new Company(r.getLong("id"), r.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
	
}
