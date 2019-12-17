package main.java.edu.excilys.cdb.ui;

import java.time.LocalDate;
import java.util.Scanner;

import main.java.edu.excilys.cdb.service.CompanyService;
import main.java.edu.excilys.cdb.service.ComputerService;

public class ConsoleAction {
	
	private static Scanner scan = new Scanner(System.in);
	private static String nameEntry;
	private static LocalDate introductionEntry;
	private static LocalDate discontinuedEntry;
	private static long companyIdEntry;
	/**
	 * this function allows the user to display all detailed information about a computer
	 */
	public static void findComputer() {
		System.out.println(UImessage.MSG_3_1.getMessage());
		long id = (scan.hasNextLong()) ? scan.nextLong() : 0;
		ComputerService.findComputer(id);
	}
	
	/**
	 * this function allows the user to create a computer
	 */
	public static void createComputer() {
		setComputerInfo();
		ComputerService.createComputer(nameEntry, introductionEntry, discontinuedEntry, companyIdEntry);
		System.out.println(nameEntry + UImessage.MSG_4_5.getMessage());
	}
	
	/**
	 * this function allows the user to update a computer
	 */
	public static void updateComputer() {
		System.out.println(UImessage.MSG_5_1.getMessage());
		long id = scan.nextLong();
		scan.nextLine();
		setComputerInfo();
		ComputerService.updateComputer(id, nameEntry, introductionEntry, discontinuedEntry, companyIdEntry);
	}
	
	/**
	 * this function allows the user to delete a computer with confirmation. 
	 */
	public static void deleteComputer() {
		
		System.out.println(UImessage.MSG_6_1.getMessage());
		long id = (scan.hasNextLong()) ? scan.nextLong() : 0;
		ComputerService.findComputer(id);
		System.out.println(UImessage.MSG_6_2.getMessage());
		String choice = scan.nextLine();
		while(!choice.equals("y") && !choice.equals("n")) {
			System.out.println(UImessage.MSG_6_ERR.getMessage());
			choice = scan.nextLine();
		}
		if(choice.equals("y")) {
			ComputerService.deleteComputer(id);
		}else {
			System.out.println("Delete computer has been cancelled");
		}
		System.out.println(UImessage.MSG_6_3.getMessage());
	}
	
	/**
	 * this function is useful for creating and editing a computer, ask the user to enter values in the field. 
	 */
	private static void setComputerInfo() {
		
		System.out.print(UImessage.MSG_4_1);
		nameEntry = scan.nextLine();
		
		System.out.print(UImessage.MSG_4_2.getMessage());
		introductionEntry = (!scan.nextLine().isEmpty() ? LocalDate.parse(scan.nextLine()) : null);
		
		System.out.print(UImessage.MSG_4_3.getMessage());
		discontinuedEntry = (!scan.nextLine().isEmpty() ? LocalDate.parse(scan.nextLine()) : null);
		
		CompanyService.findAllCompanies();
		System.out.print(UImessage.MSG_4_4.getMessage());		
		companyIdEntry = (scan.hasNextLong()) ? scan.nextLong() : 0;
	}
}
