package model;

public class Room {

	private boolean isVisible;

	private Condition condition;

	public Room()
	{
		isVisible = false;
		condition = Condition.EMPTY;
	}
	
	/**
	 * @return the isVisible
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * @param isVisible
	 *            the isVisible to set
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isEmpty() {
		return this.condition == Condition.EMPTY;
	}

	public void setCondition(Condition condition) {
		if (this.condition == Condition.BLOOD && condition == Condition.SLIME) {
			this.condition = Condition.GOOP;
		} else if (this.condition == Condition.SLIME
				&& condition == Condition.BLOOD) {
			this.condition = Condition.GOOP;
		} else {
			this.condition = condition;
		}
	}

	public Condition getCondition() {
		return this.condition;
	}

	public boolean hasWumpus() {
		return getCondition()==Condition.WUMPUS;
	}
	
	public String toString(){
		
		if(!isVisible)
			return "[X]";
		return "[" + condition.getRepresentation() + "]";
		
	}

}

