package model;

/**
 * Reprents a room in the game map
 * 
 * @author Christopher Chapline
 * @author Christopher Toepfer
 * @author David Christy
 * @author James Fagan
 */
public class Room {

	private boolean isVisible, hasHunter;

	private Condition condition;

	/**
	 * Creates a room that is not visible and empty by default
	 */
	public Room()
	{
		isVisible = false;
		condition = Condition.EMPTY;
	}
	
	/**
	 * @return Whether or not this room can be displayed
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * @param isVisible Wehther or not this room can be displayed
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 * @return True if the room is empty (no slime, wumpus, goop, etc). False otherwise
	 */
	public boolean isEmpty() {
		return this.condition == Condition.EMPTY;
	}

	/**
	 * Sets the condition of the room
	 * @param condition The new condition to set
	 */
	public void setCondition(Condition condition) {
		//If already wumpus or pit, don't change
		if(this.condition == Condition.PIT || this.condition == Condition.WUMPUS)
			return;
		//Check to see if the room is going to have blood AND slime, which yields goop
		if (this.condition == Condition.BLOOD && condition == Condition.SLIME) {
			this.condition = Condition.GOOP;
		} else if (this.condition == Condition.SLIME
				&& condition == Condition.BLOOD) {
			this.condition = Condition.GOOP;
		} else {
			this.condition = condition;
		}
	}

	/**
	 * @return The condition of the room
	 */
	public Condition getCondition() {
		return this.condition;
	}

	/**
	 * @return True if the wumpus is in this room, false otherwise
	 */
	public boolean hasWumpus() {
		return getCondition()==Condition.WUMPUS;
	}
	
	/**
	 * A string representation of this room.
	 * 
	 * <p>[ + 'Condition-Representation' + ]</p>
	 */
	public String toString(){
		
		if(!isVisible)
			return "[X]";
		return "[" + condition.getRepresentation() + "]";
		
	}
	
	public boolean hasHunter() {
		return this.hasHunter;
	}
	
	public void setHasHunter( boolean hasHunter ) {
		this.hasHunter = hasHunter;
	}

	/**
	 * resets the room
	 */
	public void reset()
	{
		this.condition = Condition.EMPTY;
		this.hasHunter = false;
		this.isVisible = false;
	}
}

