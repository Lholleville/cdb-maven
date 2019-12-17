package test.java.edu.excilys.cdb.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;

import junit.framework.TestCase;
import main.java.edu.excilys.cdb.dao.ComputerDAO;
import main.java.edu.excilys.cdb.model.Computer;

public class ComputerDAOTest extends TestCase{
	
	public static final Computer VALID_COMPUTER;
	static {
		VALID_COMPUTER = new Computer();
		VALID_COMPUTER.setId(1);
		VALID_COMPUTER.setName("MacBook Pro 15.4 inch");
		VALID_COMPUTER.setIntroductionDate(null);
		VALID_COMPUTER.setDiscontinuedDate(null);
		VALID_COMPUTER.setCompany(CompanyDAOTest.VALID_COMPANY);
}
	public ComputerDAOTest(String testMethodName) {
		super(testMethodName);
	}
	
	
	@Test
	public void testFindComputer() throws Exception{
		Optional<Computer> computerOptional = new ComputerDAO().find(1);
		assertTrue(computerOptional.isPresent());
		Computer computer = computerOptional.get();
		assertTrue(computer instanceof Computer);
		assertEquals(VALID_COMPUTER.toString(), computer.toString());
	}
	
	@Test
	public void testFindNullComputer() throws Exception{
		Optional<Computer> computerOptional = new ComputerDAO().find(10000);
		assertFalse(computerOptional.isPresent());
	}
	
	@Test
	public void testListComputer() throws Exception{
		ArrayList<Computer> list = new ComputerDAO().list();
		assertTrue(list.size() >= 300);
	}
}
