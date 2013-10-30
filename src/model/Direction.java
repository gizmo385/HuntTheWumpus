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
                
                /**
                 * Parses a movement direction to get the associated Direction constant
                 */
                public static Direction parseMovementDirection( char c ) {
                        c = Character.toLowerCase(c);
                        
                        if( c == 'u' ) {
                            return UP;
                        }
                        
                        else if( c == 'd' ) {
                            return DOWN;
                        }
                        
                        else if( c == 'l' ) {
                            return LEFT;
                        }
                        
                        else if( c == 'r' ) {
                            return RIGHT;
                        }
                        
                        return null;
                }
        }
