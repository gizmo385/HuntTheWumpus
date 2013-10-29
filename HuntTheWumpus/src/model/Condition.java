package model;

public enum Condition {

	BLOOD('B'), SLIME('B'), GOOP('B'), PIT('B'), WUMPUS('B'), EMPTY(' ');
	
	private char representation;
	
	private Condition(char representation){
		this.representation = representation;
	}

	/**
	 * @return the representation
	 */
	public char getRepresentation() {
		return representation;
	}

}
