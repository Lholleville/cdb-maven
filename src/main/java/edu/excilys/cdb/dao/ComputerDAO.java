package main.java.edu.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import main.java.edu.excilys.cdb.model.Company;
import main.java.edu.excilys.cdb.model.Computer;

/**
 * @author holle
 */
public class ComputerDAO {

	private Connection connect = ConnectionMYSQL.getInstance();
	
	/*SQL QUERIES*/
	
	private final String SQL_FIND_COMPUTER = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?";
	private final String SQL_FIND_ALL_COMPUTER = "SELECT id, name, introduced, discontinued, company_id FROM computer";
	private final String SQL_CREATE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES(?, ?, ?, ?)";
	private final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	private final String SQL_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private final String SQL_FIND_LAST_COMPUTER_ID = "SELECT id, name, introduced, discontinued, company_id FROM computer ORDER BY id DESC LIMIT 1";
	
	public Optional<Computer> find(long id) {	
		Computer computer = null;
		try {
			PreparedStatement stmt = this.connect.prepareStatement(SQL_FIND_COMPUTER);
			stmt.setLong(1, id);
			ResultSet result = stmt.executeQuery();
			
			if(result.first()) {
				computer = this.map(result);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(computer);
	}

	public ArrayList<Computer> list() {
		
		ArrayList<Computer> list = new ArrayList<Computer>();
		try {
			PreparedStatement stmt = this.connect.prepareStatement(SQL_FIND_ALL_COMPUTER);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				list.add(this.map(result));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Computer create(String name, LocalDate introduced, LocalDate discontinued, long company) {
		
		Computer computer = new Computer();
		try {
			PreparedStatement prepare = this.connect
                    .prepareStatement(SQL_CREATE_COMPUTER);
					prepare.setString(1, name);
					prepare.setTimestamp(2, Timestamp.valueOf(introduced.atStartOfDay()));
					prepare.setTimestamp(3, Timestamp.valueOf(discontinued.atStartOfDay()));
					prepare.setLong(4, company);
					prepare.executeUpdate();
			//get last computer of computers' list.
			PreparedStatement stmtComputer = this.connect.prepareStatement(SQL_FIND_LAST_COMPUTER_ID);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}	

	public Computer update(long id, String name, LocalDate introduced, LocalDate discontinued, long companyId) {
		
		Optional<Computer> optComputer = this.find(id);
		if(optComputer.isPresent()) {
			Computer computer = optComputer.get();
			
			String nameSQL = (!name.equals(computer.getName()) && !name.isEmpty()) ? name : computer.getName();
			
			LocalDate introducedSQL = (introduced != null) ? ((computer.getIntroductionDate() != null ) ? ((introduced.compareTo(computer.getIntroductionDate()) != 0) ? introduced : computer.getIntroductionDate()) : introduced) : computer.getIntroductionDate();
		
			LocalDate discontinuedSQL = (discontinued != null) ? ((computer.getDiscontinuedDate() != null) ? ((discontinued.compareTo(computer.getDiscontinuedDate()) != 0) ? discontinued : computer.getIntroductionDate()) : discontinued) : computer.getDiscontinuedDate();
					
			long companyIdSQL = (companyId != computer.getCompany().getId() && companyId != 0) ? companyId : computer.getCompany().getId();
					
			try {
				PreparedStatement prepare = this.connect
	                    .prepareStatement(SQL_UPDATE_COMPUTER);
						prepare.setString(1, nameSQL);
						prepare.setTimestamp(2, (introducedSQL != null) ? Timestamp.valueOf(introducedSQL.atStartOfDay()) : null);
						prepare.setTimestamp(3, (discontinuedSQL != null) ? Timestamp.valueOf(discontinuedSQL.atStartOfDay()) : null);
						prepare.setLong(4, companyIdSQL);
						prepare.setLong(5, id);
						prepare.executeUpdate();
					
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		return optComputer.get();
	}

	public Computer delete(long id) {
		Optional<Computer> computer = find(id);
		if(computer.isPresent()) {
			try {
				PreparedStatement prepare = this.connect
	                    .prepareStatement(SQL_DELETE_COMPUTER);
				prepare.setLong(1, id);
				prepare.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return computer.get();
	}

	private Computer map(ResultSet r) {	
		Computer computer = new Computer();
		try {
			Timestamp intro = r.getTimestamp("introduced");
			Timestamp disco = r.getTimestamp("discontinued");
			Optional<Company> company = new CompanyDAO().find(r.getLong("company_id"));
			computer = new Computer(
					r.getLong("id"), 
			    	r.getString("name"),
			    	(intro != null) ? intro.toLocalDateTime().toLocalDate() : null,
			    	(disco != null) ? disco.toLocalDateTime().toLocalDate() : null,
			    	company.isPresent() ? company.get() : null
			    );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
	}
}
