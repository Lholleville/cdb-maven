package edu.excilys.cdb;

import edu.excilys.cdb.controller.ViewController;
import edu.excilys.cdb.ui.ConsoleView;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ViewController controller = new ViewController();
		ConsoleView view = new ConsoleView(controller);
    }
}
