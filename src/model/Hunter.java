package model;

/**
 * Manages info about the hunter - the player's avatar in the game
 * 
 * @author Christopher Chapline
 * @author Christopher Toepfer
 * @author David Christy
 * @author James Fagan
 */
public class Hunter {
	
	private Direction hunterDirection;
	private int xCoordinate;
	private int yCoordinate;
	private boolean hasArrow;
	

	/**
	 * Creates a hunter with an initial (x,y) position on the board
	 * @param xCoord The hunter's x-coordinate
	 * @param yCoord The hunter's y-coordinate
	 */
	Hunter(int xCoord, int yCoord) {
		this.xCoordinate = xCoord;
		this.yCoordinate = yCoord;
		this.hasArrow = true;
	}
	
	/**
	 * Sets the direction that the hunter is facing
	 * @param hunterDirection The direction to face
	 */
	public void setNewHunterDirection(Direction hunterDirection) {
		this.hunterDirection = hunterDirection;
	}
	
	/**
	 * @param xCoord The new x-coordinate for the hunter
	 */
	public void setXCoordinate(int xCoord) {
		this.xCoordinate = xCoord;
	}
	
	/**
	 * @param yCoord The new y-coordinate for the hunter
	 */
	public void setYCoordinate(int yCoord) {
		this.yCoordinate = yCoord;
	}
	
	/**
	 * @return True if the hunter has not fired their arrow, false otherwise
	 */
	public boolean getHasArrow() {
		return hasArrow;
	}
	
	/**
	 * @return The direction that the hunter is facing
	 */
	public Direction getHunterDirection() {
		return hunterDirection;
	}
	
	/**
	 * @return The hunter's current x-coordinate
	 */
	public int getXCoordinate() {
		return xCoordinate;
	}
	
	/**
	 * @return The hunter's current y-coordinate
	 */
	public int getYCoordinate() {
		return yCoordinate;
	}
	
}
