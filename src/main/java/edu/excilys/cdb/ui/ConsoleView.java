package main.java.edu.excilys.cdb.ui;

import java.util.Scanner;

import main.java.edu.excilys.cdb.controller.ViewController;
/**
 * 
 * @author holle
 * Class for the view in the console.
 */
public class ConsoleView extends View{
	
	private ViewController controller;
	
	/**
	 * View constructor, with no message.
	 * @param ViewController controller
	 */
	public ConsoleView(ViewController controller) {
		super(controller);
		this.controller = controller;
		this.displayMenu();
	}
	
	/**
	 * View constructor, with message.
	 * @param ViewController controller
	 * @param String message
	 */
	public ConsoleView(ViewController controller, String message) {
		super(controller, message);
		this.controller = controller;
		this.displayMenu();
	}
	
	/**
	 * the function displays the menu. User makes a choice which is sent to the controller
	 */
	private void displayMenu() {
		if(this.message != null) {
			System.out.println(this.getMessage());
		}
		System.out.println(UImessage.MSG_INTRO.getMessage() + "\n");
		System.out.println(UImessage.MSG_1.getMessage() );
		System.out.println(UImessage.MSG_2.getMessage() );
		System.out.println(UImessage.MSG_3.getMessage() );
		System.out.println(UImessage.MSG_4.getMessage() );
		System.out.println(UImessage.MSG_5.getMessage() );
		System.out.println(UImessage.MSG_6.getMessage()  + "\n");
		System.out.print(UImessage.MSG_KEY.getMessage());
		
		Scanner sc = new Scanner(System.in);
		int choice = (sc.hasNextInt()) ? sc.nextInt() : 0;
		this.controller.executeAction(choice);
	}
	
}
