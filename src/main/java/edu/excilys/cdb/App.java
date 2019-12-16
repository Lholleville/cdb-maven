package main.java.edu.excilys.cdb;


import main.java.edu.excilys.cdb.controller.ViewController;
import main.java.edu.excilys.cdb.ui.ConsoleView;

public class App 
{
    public static void main(String[] args)
    {
    	ViewController controller = new ViewController();
		ConsoleView view = new ConsoleView(controller);
    }
}
