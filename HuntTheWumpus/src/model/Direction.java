package model;

public enum Direction {
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
