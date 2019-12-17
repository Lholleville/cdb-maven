package main.java.edu.excilys.cdb.ui;

import java.util.Scanner;

import main.java.edu.excilys.cdb.service.CompanyService;
import main.java.edu.excilys.cdb.service.ComputerService;

public class Console {
	private static Scanner scan = new Scanner(System.in);
	
	private static void menuConsole() {
		System.out.println(UImessage.MSG_1.getMessage() );
		System.out.println(UImessage.MSG_2.getMessage() );
		System.out.println(UImessage.MSG_3.getMessage() );
		System.out.println(UImessage.MSG_4.getMessage() );
		System.out.println(UImessage.MSG_5.getMessage() );
		System.out.println(UImessage.MSG_6.getMessage()  + "\n");
		System.out.print(UImessage.MSG_KEY.getMessage());
	}
	
	public static void displayCli() {
		String choice = "";
		System.out.println(UImessage.MSG_INTRO.getMessage() + "\n");
		do {
			menuConsole();
			choice = scan.nextLine().trim();
			switch(choice) {
			case "1" :
				CompanyService.findAllCompanies();
				break;
			case "2" :
				ComputerService.findAllComputers();
				break;
			case "3" :
				ConsoleAction.findComputer();
				break;
			case "4" :
				ConsoleAction.createComputer();
				break;
			case "5" : 
				ConsoleAction.updateComputer();
				break;
			case "6" :
				ConsoleAction.deleteComputer();
				break;
			default :
				System.out.println(UImessage.MSG_WRONG_KEY.getMessage());
				break;
			}
		} while(!choice.equals("0"));
		
		scan.close();
	}
}
