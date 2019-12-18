package main.java.edu.excilys.cdb.service;

import main.java.edu.excilys.cdb.dao.CompanyDAO;
import main.java.edu.excilys.cdb.model.Company;

public class CompanyService {
	
	private static CompanyDAO companyDAO = CompanyDAO.getInstance();
	
	private CompanyService() {};
	
	private static CompanyService companyService = null;
	
	public static CompanyService getInstance() {
		if (companyService == null) {
			companyService = new CompanyService();
		}
		return companyService;
	}
	
	public static void findAllCompanies() {
        for(Company company : companyDAO.list()) {
            System.out.println(company.toString());
        }
	}
}
