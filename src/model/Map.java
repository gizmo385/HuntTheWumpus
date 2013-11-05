package model;

import java.awt.Point;

/**
 * Manages info about the game board
 * 
 * @author Christopher Chapline
 * @author Christopher Toepfer
 * @author David Christy
 * @author James Fagan
 */
public class Map {
        private Room[][] rooms;
        private Hunter hunter;
        private String hunterStatus;
        private boolean isPlaying;

        private final int ROWS;
        private final int COLS;
        private int numberOfPits, slimeSpread, bloodSpread;

        /**
         * Creates a map of a certain size, places a wumpus and pit in random 
         * locations, and then places the player in a safe square
         * @param rows The number of rows on the map
         * @param cols The number of columns on the map
         */
        public Map( GenerationStrategy strategy ) {
                //Set gameplay variables
                isPlaying = true;
                this.ROWS = strategy.getRows();
                this.COLS = strategy.getColumns();
                this.numberOfPits = strategy.getNumberOfPits();
                this.slimeSpread = strategy.getSlimeSpreadDistance();
                this.bloodSpread = strategy.getBloodSpreadDistance();

                //Initialize all Rooms
                rooms = new Room[ROWS][COLS];
                for (int r = 0; r < rooms.length; r++) {
                        for (int c = 0; c < rooms[0].length; c++) {
                                rooms[r][c] = new Room();
                        }
                }

                //Place wumpus
                int wumpusLocation = (int) (Math.random() * this.ROWS * this.COLS);
                this.placeWumpus(wumpusLocation);

                //Place pits
                for( int i = 0; i < this.numberOfPits; i++ ) {
                        boolean successfulPitPlacement = false;
                        
                        do {
                                //Generate random location
                                int pitLocation = (int) (Math.random() * this.ROWS * this.COLS);
                                
                                //Get absolute location
                                int pitX = pitLocation / COLS;
                                int pitY = pitLocation % COLS;
                                
                                //Test location
                                if (this.rooms[pitX][pitY].isEmpty()) {
                                        placePit(pitLocation);
                                        successfulPitPlacement = true;
                                }
                        } while (!successfulPitPlacement);
                }

                //Place the hunter in a safe location
                while(true)
                {        
                        int hunterLocation = (int) (Math.random() * this.ROWS * this.COLS);
                        int hunterRow = hunterLocation/this.COLS;
                        int hunterCol = hunterLocation%this.COLS;
                        if(rooms[hunterRow][hunterCol].isEmpty())
                        {
                                hunter = new Hunter(hunterRow,hunterCol);
                                hunterStatus = rooms[hunterRow][hunterCol].getCondition().getStatus();
                                rooms[hunterRow][hunterCol].setVisible(true);
                                rooms[hunterRow][hunterCol].setHasHunter(true);
                                break;
                        }        
                }
        }

        /**
         * Creates a map of a certain size, places a wumpus, a pit,
         * and a hunter in specified locations.
         * @param rows The number of rows on the map
         * @param cols The number of columns on the map
         * @param wumpusLoc location of wumpus
         * @param pitLoc location of pit
         * @param hunterLoc location of hunter
         */
        public Map(int rows, int cols, int wumpusLoc, int pitLoc,int hunterLoc) 
        {
                isPlaying = true;
                this.ROWS = rows;
                this.COLS = cols;
                this.bloodSpread = 1;
                this.slimeSpread = 1;
                this.numberOfPits = 1;

                rooms = new Room[ROWS][COLS];
                for (int r = 0; r < rooms.length; r++) {
                        for (int c = 0; c < rooms[0].length; c++) {
                                rooms[r][c] = new Room();
                        }
                }

                this.placeWumpus(wumpusLoc);
                this.placePit(pitLoc);

                int hunterRow = hunterLoc/cols;
                int hunterCol = hunterLoc%cols;
                hunter = new Hunter(hunterRow,hunterCol);
                hunterStatus = rooms[hunterRow][hunterCol].getCondition().getStatus();
                rooms[hunterRow][hunterCol].setVisible(true);


        }


        /**
         * This method will place the pit at the given location.
         * It will also put slime in the adjacent rooms.
         * The row value is the location divided by the number
         * of columns, and the col value is the location
         * mod the number of columns.
         * 
         * @param The location of the pit to place
         */
        private void placePit(int pitLocation) {
                //Get absolute location
                int pitX = pitLocation/COLS;
                int pitY = pitLocation%COLS;

                //Places the pit
                rooms[pitX][pitY].setCondition(Condition.PIT);

                for( int i = 0; i < this.slimeSpread; i++ ) {
                        //Places slime in the adjacent pits
                        rooms[(pitX + i + 1) % ROWS][pitY].setCondition(Condition.SLIME);
                        rooms[(pitX + ROWS - i - 1) % ROWS][pitY].setCondition(Condition.SLIME);
                        rooms[pitX][(pitY + i + 1) % COLS].setCondition(Condition.SLIME);
                        rooms[pitX][(pitY + COLS - i - 1) % COLS].setCondition(Condition.SLIME);
                }
        }

        /**
         * This method will place the wumpus at the given location.
         * It will also put blood in the adjacent rooms.
         * The row value is the location divided by the number
         * of columns, and the col value is the location
         * mod the number of columns.
         * 
         * @param The location of the wumpus to place
         */
        private void placeWumpus(int wumpusLocation) {
                //Get absolute location
                int wumpusX = wumpusLocation/COLS;
                int wumpusY = wumpusLocation%COLS;

                //Places Wumpus
                rooms[wumpusX][wumpusY].setCondition(Condition.WUMPUS);

                for( int i = 0; i < this.bloodSpread; i++ ) {
                        //Place blood in adjacent rooms
                        rooms[(wumpusX + i + 1) % ROWS][wumpusY].setCondition(Condition.BLOOD);
                        rooms[(wumpusX + ROWS - i - 1) % ROWS][wumpusY].setCondition(Condition.BLOOD);
                        rooms[wumpusX][(wumpusY + i + 1) % COLS].setCondition(Condition.BLOOD);
                        rooms[wumpusX][(wumpusY + COLS - i - 1) % COLS].setCondition(Condition.BLOOD);
                }
        }

        /**
         * Depending on the value of dir, this method
         * moves the hunter to a room adjacent to its current
         * room. It updates the Hunter object as well.
         * 
         * @param dir The direction for the player to move in
         */
        public void move(Direction dir)
        {
                this.rooms[this.hunter.getXCoordinate()][this.hunter.getYCoordinate() ].setHasHunter( false );
                
                //Calculate new location
                int newX = this.hunter.getXCoordinate() + dir.getDiffX();
                int newY = this.hunter.getYCoordinate() + dir.getDiffY();

                //Verify valid X location
                if( newX >= this.COLS ) {
                        newX = 0;
                }

                else if( newX < 0 ) {
                        newX = this.COLS - 1;
                }

                //Verify valid Y location
                if( newY >= this.ROWS ) {
                        newY = 0;
                }

                else if( newY < 0 ) {
                        newY = this.ROWS - 1;
                }

                //Move the hunter
                hunter.setXCoordinate(newX);
                hunter.setYCoordinate(newY);
                rooms[newX][newY].setVisible(true);
                rooms[newX][newY].setHasHunter(true);
                hunterStatus = rooms[newX][newY].getCondition().getStatus();
                
                //Check end of game conditions
                if (rooms[newX][newY].getCondition() == Condition.WUMPUS || rooms[newX][newY].getCondition() == Condition.PIT)
                {        
                        isPlaying = false;
                        setMapToVisible();
                }
        }

        //TODO: Set back to private
        /**
         * Sets all rooms in the map to visible
         */
        public void setMapToVisible() {
                for(Room[] row : rooms)
                        for(Room room: row)
                                room.setVisible(true);
        }


        /**
         * Fires an arrow in the specified direction
         * 
         * @param dir The direction to fire in 
         * @see #fire(Direction)
         */
        public void fireArrow(Direction dir)
        {
                boolean successfulShot = fire(dir);
                if(successfulShot)
                        hunterStatus = "You killed the Wumpus! You are a true hero!";
                else
                        hunterStatus = "You missed with your one and only arrow and the Wumpus heard you. You were eaten.";
                isPlaying = false;
                setMapToVisible();
        }

        /** 
         * 
         * This function retrieves the coordinates of the hunter
         * and fires an arrow given it's position from the hunter
         * and travels until it hits a wall (edges of map)
         * 
         * @param dir The direction to fire in
         * @return Returns true if the wumpus was killed, false otherwise.
         */
        private boolean fire(Direction dir) {
                int xCoord = hunter.getXCoordinate();
                int yCoord = hunter.getYCoordinate();

                switch(dir) {
                case RIGHT:
                        for(int i=yCoord; i < COLS; i++) 
                                if(rooms[xCoord][i].hasWumpus())
                                        return true;
                        break;

                case DOWN:
                        for(int i=xCoord; i < ROWS; i++) 
                                if(rooms[i][yCoord].hasWumpus())
                                        return true;
                        break;

                case LEFT:
                        for(int i=yCoord; i > 0; i--) 
                                if(rooms[xCoord][i].hasWumpus())
                                        return true;
                        break;

                case UP:
                        for(int i=xCoord; i > 0; i--) 
                                if(rooms[i][yCoord].hasWumpus())
                                        return true;
                        break;

                default:
                        break;
                }
                return false;
        }

        /**
         * Returns the status of the hunter, which is a description of
         * the hunter's current situation.
         */
        public String getStatus()
        {
                return hunterStatus;
        }

        /**
         * Returns a string representation of map
         */
        public String toString() {
                String lineSep = System.lineSeparator();
                StringBuilder sb = new StringBuilder();

                for (int r = 0; r < rooms.length; r++) {
                        for (int c = 0; c < rooms[0].length; c++) {
                                if(r == hunter.getXCoordinate() && c == hunter.getYCoordinate())
                                        sb.append( "[O]" );
                                else
                                        sb.append( rooms[r][c].toString() );
                        }
                        sb.append( lineSep );
                }
                return sb.toString();
        }

        /**
         * @return True while the game is still being played
         */
        public boolean playing() {
                return isPlaying;
        }
        
        /**
         * @return The current location of the hunter
         */
        public Point getHunterLocation() {
                Point p = new Point( this.hunter.getXCoordinate(), this.hunter.getYCoordinate() );
                return p;
        }

        /**
         * @return The current map
         */
        public Room[][] getRooms() {
                return rooms;
        }


}