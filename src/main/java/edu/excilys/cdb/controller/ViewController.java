package main.java.edu.excilys.cdb.controller;


import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;
import main.java.edu.excilys.cdb.dao.CompanyDAO;
import main.java.edu.excilys.cdb.dao.ComputerDAO;
import main.java.edu.excilys.cdb.model.*;
import main.java.edu.excilys.cdb.ui.ConsoleView;
import main.java.edu.excilys.cdb.ui.UImessage;


/**
 * @author holle
 *
 */
public class ViewController extends Controller{
	
	private String nameEntry;
	private LocalDate introductionEntry;
	private LocalDate discontinuedEntry;
	private long companyIdEntry;
	
	/**
	 * this function manage the user choice of action
	 * @param choice 
	 */
	public void executeAction(int choice) {
		switch(choice) {
			case 1 :
				this.UIlistCompanies();
				this.finish();
				break;
			case 2 :
				this.UIlistComputers();
				this.finish();
				break;
			case 3 :
				this.UIshowComputerDetails();
				break;
			case 4 :
				this.UIcreateComputer();
				break;
			case 5 : 
				this.UIupdateComputer();
				break;
			case 6 :
				this.UIdeleteComputer();
				break;
			default :
				this.restartUI(UImessage.MSG_WRONG_KEY.getMessage());
				break;
		}
	}
	
	/**
	 * this function allows the user to list all the companies of the db
	 */
	private void UIlistCompanies() {
		for(Company companies : new CompanyDAO().list()) {
			System.out.println(companies.toString());
 		}
	}
	
	/**
	 * this function allows the user to list all the computers of the db
	 */
	private void UIlistComputers() {
		for(Computer computers : new ComputerDAO().list()) {
			System.out.println(computers.toString());
		}
	}
	/**
	 * this function allows the user to display all detailed information about a computer
	 */
	private void UIshowComputerDetails() {
		System.out.println(UImessage.MSG_3_1.getMessage());
		Scanner idComputer = new Scanner(System.in);
		long id = (idComputer.hasNextLong()) ? idComputer.nextLong() : 0;
		Optional<Computer> fComp = new ComputerDAO().find(id);
		this.restartUI((fComp.isPresent()) ? fComp.get().toString() : "No computer found with id " + id);
	}
	
	/**
	 * this function allows the user to create a computer
	 */
	private void UIcreateComputer() {
		this.setComputerInfo();
		Computer computer = new ComputerDAO().create(this.nameEntry, this.introductionEntry, this.discontinuedEntry, this.companyIdEntry);
		this.restartUI(computer.getName() + UImessage.MSG_4_5.getMessage());
	}
	
	/**
	 * this function allows the user to update a computer
	 */
	private void UIupdateComputer() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println(UImessage.MSG_5_1.getMessage());
		long id = scan.nextLong();
		scan.nextLine();
		
		Optional<Computer> computer = new ComputerDAO().find(id);
		if(computer.isPresent()) { 
			System.out.println(computer.get().toString());
			this.setComputerInfo();
			Computer comp  = new ComputerDAO().update(id, this.nameEntry, this.introductionEntry, this.discontinuedEntry, this.companyIdEntry);
			this.restartUI("Computer : " + comp.getName() + " modified");
		}else {
			this.restartUI("No computer to update");
		}	
	}
	
	/**
	 * this function allows the user to delete a computer with confirmation. 
	 */
	private void UIdeleteComputer() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println(UImessage.MSG_6_1.getMessage());
		long id = (scan.hasNextLong()) ? scan.nextLong() : 0;
		
		Optional<Computer> computer = new ComputerDAO().find(id);
		if(!computer.isPresent()) {
			this.restartUI("No computer found with id " + id);
		}
		next();
		System.out.println(UImessage.MSG_6_2.getMessage() + computer.get().getName());
		
		String choice = scan.nextLine();
		while(!choice.equals("y") && !choice.equals("n")) {
			System.out.println(UImessage.MSG_6_ERR.getMessage());
			choice = scan.nextLine();
		}
		if(choice.equals("y")) {
			Computer comp = new ComputerDAO().delete(id);
		}else {
			this.restartUI("Delete computer has been cancelled");
		}
		
		this.restartUI(computer.get().getName() + UImessage.MSG_6_3.getMessage());
	}
	
	/**
	 * this function is useful for creating and editing a computer, ask the user to enter values in the field. 
	 */
	private void setComputerInfo() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print(UImessage.MSG_4_1.getMessage());
		this.nameEntry = scan.nextLine();
		
		System.out.print(UImessage.MSG_4_2.getMessage());
		this.introductionEntry = (!scan.nextLine().isEmpty() ? LocalDate.parse(scan.nextLine()) : null);
		
		System.out.print(UImessage.MSG_4_3.getMessage());
		this.discontinuedEntry = (!scan.nextLine().isEmpty() ? LocalDate.parse(scan.nextLine()) : null);
		
		this.UIlistCompanies();
		System.out.print(UImessage.MSG_4_4.getMessage());		
		this.companyIdEntry = (scan.hasNextLong()) ? scan.nextLong() : 0;
		next();
	}
	
	/**
	 * Restart the UI and allow to set custom message.
	 * @param message
	 * @return ConsoleView
	 */
	private ConsoleView restartUI(String message) {
		this.clearConsole();
		return new ConsoleView(this, message);
	}
	
	/**
	 * Uses next() function and reset the UI with no message.
	 */
	private void finish() {
		next();
		this.restartUI(null);
	}
	
	/**
	 * this is an utility function for user guidance. 
	 */
	private void next() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Press Enter to continue.");
		scan.nextLine();
	}
	
	/**
	 * this function add some blank lines to simulate clear console function
	 */
	private void clearConsole() {
		for (int i = 0; i < 20; ++i) System.out.println();
	}
}
