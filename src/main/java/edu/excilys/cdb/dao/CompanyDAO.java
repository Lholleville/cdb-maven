package main.java.edu.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Optional;

import main.java.edu.excilys.cdb.model.Company;

public class CompanyDAO{

	private final String SQL_FIND_ALL_COMPANIES = "SELECT id, name FROM company";
	private final String SQL_FIND_COMPANY = "SELECT id, name FROM company WHERE id = ?";
	
	private static CompanyDAO companyDAO = null;
	private CompanyDAO() {}
	
	public static CompanyDAO getInstance() {
		if(companyDAO == null) {
			companyDAO = new CompanyDAO();
		}
		return companyDAO;
	}
	
	public ArrayList<Company> list(){
		ArrayList<Company> list = new ArrayList<Company>();
		
		try {
			Connection connect = ConnectionMYSQL.getInstance().connect(); 
			PreparedStatement stmt = connect.prepareStatement(SQL_FIND_ALL_COMPANIES);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				list.add(this.map(result.getLong("id"), result.getString("name")));
			}
			result.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionMYSQL.getInstance().disconnect();
		}		
		return list;
	}

	public Optional<Company> find(long id) {
		Company company = new Company();
		try {
			Connection connect = ConnectionMYSQL.getInstance().connect(); 
			PreparedStatement stmt = connect.prepareStatement(SQL_FIND_COMPANY);
			stmt.setLong(1, id);
			ResultSet result = stmt.executeQuery();
			if(result.first()) {
				company = this.map(result.getLong("id"), result.getString("name"));
			}
			result.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionMYSQL.getInstance().disconnect();
		}
		return Optional.ofNullable(company);
	}
	
	private Company map(long id, String name) {
		Company company = new Company();
		company = new Company(id,name);
		
		return company;
	}
}
