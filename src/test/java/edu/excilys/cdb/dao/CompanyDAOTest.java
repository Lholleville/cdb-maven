package test.java.edu.excilys.cdb.dao;

import java.util.ArrayList;
import java.util.Optional;
import org.junit.Test;

import junit.framework.TestCase;
import main.java.edu.excilys.cdb.dao.CompanyDAO;
import main.java.edu.excilys.cdb.model.Company;


public class CompanyDAOTest extends TestCase{
	
	public static final Company VALID_COMPANY;
	static {
		VALID_COMPANY = new Company();
		VALID_COMPANY.setId(1);
		VALID_COMPANY.setName("Apple Inc.");
	}
	
	public CompanyDAOTest(String testMethodName) {
	    super(testMethodName);
	}
	
	@Test
	public void testFindCompany() throws Exception{
		Optional<Company> companyOptional = CompanyDAO.getInstance().find(1);
		assertTrue(companyOptional.isPresent());
		Company company = companyOptional.get();
		assertTrue(company instanceof Company);
		assertEquals(VALID_COMPANY.toString(), company.toString());
	}
	
	@Test
	public void testFindNullCompany() throws Exception{
		Optional<Company> companyOptional = CompanyDAO.getInstance().find(10000);
		assertFalse(companyOptional.get().getId() != 0);
	}
	
	@Test
	public void testListCompany() throws Exception{
		ArrayList<Company> list = CompanyDAO.getInstance().list();
		assertTrue(list.size() >= 42);
	}
}
