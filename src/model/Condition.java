package model;

public enum Condition {

	BLOOD('B', "There is blood on the ground."), 
	SLIME('S', "The ground is covered in green slime."),
	GOOP('G', "There is some kind of goopy substance on the ground."),
	PIT('P', "You fell into a pit and died."),
	WUMPUS('W', "The wumpus ate you."), 
	EMPTY(' ', "This room appears to be empty.");
	
	private char representation;
	private String status;
	
	private Condition(char representation, String status){
		this.representation = representation;
		this.status = status;
	}

	/**
	 * @return the representation
	 */
	public char getRepresentation() {
		return representation;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

}
