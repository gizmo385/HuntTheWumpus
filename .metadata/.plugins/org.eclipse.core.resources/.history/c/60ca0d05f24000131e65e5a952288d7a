import java.util.Scanner;

/**
 * Plays the game in a console view
 */
public class Main {
	
	private Scanner scan = new Scanner( System.in );
	private final String invalidMoveMessage = "Please enter a valid command (up, down, left, right, fire)";

	public static void main( String[] args ) {
		
	
		while( true ) {
			Map gameMap = new Map( 10, 10 );
			
			while( gameMap.playing() ) {
				System.out.println( gameMap.toString() );
				System.out.println();
				
				System.out.println( "Please select a move: up, down, left, right, fire, new, quit" );
				String move = Scanner.nextLine();
				
				if( move.length() > 0 ) {
					parseCommand( move );
				}
				else {
					System.out.println( invalidMoveMessage );
				}
			}
			
			//TODO: Handle end of game (when playing() == false)
		}
	}
	
	/**
	 * Parses user input the ensure that the move made by the user is valid
	 */
	private void parseCommand( String move ) {
		char moveCharacter = move.toLowerCase().trim().charAt(0);
		
		//If the user wants to fire
		if( moveCharacter == 'f' ) {
			System.out.println( "Please select a direction to fire your arrow (or quit to cancel): up, down, left, right" );
			char directionSelection = Scanner.nextLine().trim().toLowerCase().charAt(0);
			
			//Make sure the input isn't to quit firing
			if( directionSelection != 'q' ) {
				Direction firingDirection = Direction.parseMovementDirection( directionSelection );
				
				if( firingDirection != null ) {
					this.gameMap.fire( firingDirection );
				}
				
				else {
					System.out.println( "Please enter a valid firing direction!" );
				}
			}
			
			else {
				return;
			}
		}
		
		//User wants to quit, exit console
		else if( moveCharacter == 'q' ) {
			System.exit(0);
		}
		
		//Reinitialize map
		else if( moveCharacter == 'n' ) {
			this.gameMap = new Map( 10, 10 );
			return;
		}
		
		//Attempt to parse for a movement direction
		else {
			Direction d = Direction.parseMovementDirection( moveCharacter );
			
			//Ensure direction is valid
			if( d != null ) {
				this.gameMap.move( d );
			}
			
			else {
				System.out.println( invalidMoveMessage );
			}
		}
	}
}
