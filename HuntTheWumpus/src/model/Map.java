public class Map {
        private Room[][] rooms;
        private Hunter hunter;
        
        private final int ROWS;
        private final int COLS;

        public Map(int rows, int cols) {
                this.ROWS = rows;
                this.COLS = cols;
        
                rooms = new Room[ROWS][COLS];
                for (int r = 0; r < rooms.length; r++) {
                        for (int c = 0; c < rooms[0].length; c++) {
                                rooms[r][c] = new Room();
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
                
                //Additions by Chris C.
                //Calculate new location
                int newX = this.hunter.getX() + dir.getDiffX();
                int newY = this.hunter.getY() + dir.getDiffY();
                
                //Verify valid X location
                if( newX >= this.COLS ) {
                        newX = 0;
                }
                
                else if( newX < 0 ) {
                        newX = this.COLS;
                }
                
                //Verify valid Y location
                if( newY >= this.ROWS ) {
                        newY = 0;
                }
                
                else if( newY < 0 ) {
                        newY = this.ROWS;
                }
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
        
        /**
         * The different directions possible for movement
         */
        private Enum Direction {
                UP(0, -1), DOWN(0, 1), RIGHT(1,0), LEFT(-1,0);
                
                private int diffX, diffY;
                
                private Direction( int diffX, int diffY ) {
                        this.diffX = diffX;
                        this.diffY = diffY;
                }
                
                public int getDiffX() {
                        return this.diffX;
                }
                
                public int getDiffY() {
                        return this.diffY;
                }
        }
}