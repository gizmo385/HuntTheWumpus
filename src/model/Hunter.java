package model;

public class Hunter {
	
	private Direction hunterDirection;
	private int xCoordinate;
	private int yCoordinate;
	private boolean hasArrow;
	

	Hunter(int xCoord, int yCoord) {
		this.hunterDirection = hunterDirection;
		this.xCoordinate = xCoord;
		this.yCoordinate = yCoord;
		this.hasArrow = true;
	}
	private void setNewHunterDirection(Direction hunterDirection) {
		this.hunterDirection = hunterDirection;
	}
	
	public void setXCoordinate(int xCoord) {
		this.xCoordinate = xCoord;
	}
	
	public void setYCoordinate(int yCoord) {
		this.yCoordinate = yCoord;
	}
	
	public boolean getHasArrow() {
		return hasArrow;
	}
	
	public Direction getHunterDirection() {
		return hunterDirection;
	}
	
	public int getXCoordinate() {
		return xCoordinate;
	}
	
	public int getYCoordinate() {
		return yCoordinate;
	}
	
}
