
public class Hunter {
	
	private Directions hunterDirection;
	private int xCoordinate;
	private int yCoordinate;
	private boolean hasArrow;
	

	Hunter(Directions hunterDirection, int xCoord, int yCoord) {
		this.hunterDirection = hunterDirection;
		this.xCoordinate = xCoord;
		this.yCoordinate = yCoord;
		this.hasArrow = true;
	}
	private void setNewHunterDirection(Directions hunterDirection) {
		this.hunterDirection = hunterDirection;
	}
	
	private void setXCoordinate(int xCoord) {
		this.xCoordinate = xCoord;
	}
	
	private void setYCoordinate(int yCoord) {
		this.yCoordinate = yCoord;
	}
	
	public boolean getHasArrow() {
		return hasArrow;
	}
	
	public Directions getHunterDirection() {
		return hunterDirection;
	}
	
	public int getXCoordinate(int xCoord) {
		return xCoordinate;
	}
	
	public int getYCoordinate(int yCoord) {
		return yCoordinate;
	}
	
}
