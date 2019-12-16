package main.java.edu.excilys.cdb.ui;

public enum UImessage {
	
	MSG_INTRO("~~~~~~~~~~~~~~~ CDB PROJECT - NOV 2019  ~~~~~~~~~~~~~~~", 0),
	MSG_1("1 - List all the companies", 1), 
	MSG_2("2 - List all the computers", 2), 
	MSG_3("3 - Show a computer's details", 3), 
	MSG_3_1("Please enter the id of the computer you want to display: ", 0),
	MSG_4("4 - Create a new computer", 4), 
	MSG_4_1("Name: ", 0),
	MSG_4_2("Introduction date (Format : yyyy-mm-dd ): ", 0),
	MSG_4_3("Discontinuation date (Format : yyyy-mm-dd ): ", 0),
	MSG_4_4("Enter company ID (0 for none): ", 0),
	MSG_4_5(" added to database", 0),
	MSG_5("5 - Update an existing computer", 5), 
	MSG_5_1("Computer to edit (id): " , 0), 
	MSG_5_2("Let empty to not edit a field", 0),
	MSG_6("6 - Delete a computer /!\\", 6),
	MSG_6_1("Computer to delete (id): " , 0), 
	MSG_6_2("Confirm delete ? (y/n): " , 0),
	MSG_6_3(" has been removed from our records", 0),
	MSG_6_ERR("Please, y or n : ", 0),
	MSG_KEY("Choose an action by pressing the corresponding key : ", 0),
	MSG_WRONG_KEY("Please, choose an other action.", 0),
	;
	
	private String message;
	private int choice = 0;
	
	private UImessage(String message, int choice){
		this.message = message;
		this.choice = choice;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}
	
	
}
