public class Map {
	private Space[][] rooms;
	private Hunter hunter;

	public Map(int rows, int cols) {
		rooms = new Space[10][10];
		for (int r = 0; r < rooms.length; r++) {
			for (int c = 0; c < rooms[0].length; c++) {
				rooms[r][c] = new Space();
			}
		}
		
		int wumpusLocation = 0;
		int pitLocation = 0;
		/*
		 * This while loop generates random locations for the 
		 * wumpus, and pit. If the random values repeat,
		 * it tries again.
		 */
		while (wumpusLocation == pitLocation)
		{
			wumpusLocation = (int) (Math.random() * rows * cols);
			pitLocation = (int) (Math.random() * rows * cols);
		}
		
		this.placeWumpus(wumpusLocation);
		this.placePit(pitLocation);
		
		while(true)
		{	
			int hunterLocation = (int) (Math.random() * rows * cols);
			if(rooms[hunterLocation/cols][hunterLocation%cols].isEmpty())
			{
				this.placeHunter(hunterLocation);
				hunter = new Hunter(hunterLocation/cols,hunterLocation%cols);
				break;
			}	
		}
	}

	
	/*
	 * This method will place the pit at the given location.
	 * It will also put slime in the adjacent rooms.
	 * The row value is the location divided by the number
	 * of columns, and the col value is the location
	 * mod the number of columns.
	 */
	private void placePit(int pitLocation) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * This method will place the wumpus at the given location.
	 * It will also put blood in the adjacent rooms.
	 * The row value is the location divided by the number
	 * of columns, and the col value is the location
	 * mod the number of columns.
	 */
	private void placeWumpus(int wumpusLocation) {
		// TODO Auto-generated method stub
		
	}

	
	/*
	 * This method will place the hunter at the given location.
	 * The row value is the location divided by the number
	 * of columns, and the col value is the location
	 * mod the number of columns.
	 */
	private void placeHunter(int hunterLocation) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Depending on the value of dir, this method
	 * moves the hunter to a room adjacent to its current
	 * room. It updates the Hunter object as well.
	 */
	public void Move(Direction dir)
	{
		// TODO Write this code
	}
	
	public String toString() {
		String mapDisplay = "";
		for (int r = 0; r < rooms.length; r++) {
			for (int c = 0; c < rooms[0].length; c++) {
				mapDisplay += "[" + rooms[r][c].toString() + "] ";
			}
			mapDisplay += System.getProperty("line.separator");
		}
		return mapDisplay;
	}
}
