package edu.excilys.cdb.ui;

import edu.excilys.cdb.controller.Controller;
/**
 * Mother class of all application's View
 * @author holle
 *
 */
public class View {
	
	protected String message;
	protected Controller controller;
	
	public View(Controller controller) {
		this.controller = controller;
	}
	
	public View(Controller controller, String message) {
		this.controller = controller;
		this.message = message;
	}
	/**
	 * return a new View
	 * @return View
	 */
	public View restartView() {
		return this;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	
}
