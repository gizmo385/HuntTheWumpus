package View;
import java.util.Scanner;

import model.Direction;
import model.GenerationStrategy;
import model.Map;

/**
 * Plays the game in a console view
 * 
 * @author Christopher Chapline
 * @author Christopher Toepfer
 * @author David Christy
 * @author James Fagan
 */
public class ConsoleMain {
	
	static private Scanner scan = new Scanner( System.in );
	static private final String invalidMoveMessage = "Please enter a valid command (up, down, left, right, fire)";
	static private GenerationStrategy gs = new GenerationStrategy( 1, 1, 2, 10, 10 );
	static private Map gameMap = new Map( gs );

	public static void main( String[] args ) {
		while( true ) {
			while( gameMap.playing() ) {
				printGameStatus();
				
				System.out.println( "Please select a move: up, down, left, right, fire, new, quit" );
				String move = scan.nextLine();
				
				if( move.length() > 0 ) {
					parseCommand( move );
				}
				else {
					System.out.println( invalidMoveMessage );
				}
			}
			printGameStatus();
			
			startNewGame();
		}
	}
	
	/**
	 * Prints the map and game status
	 */
	private static void printGameStatus() {
		System.out.println( gameMap.toString() );
		System.out.println( gameMap.getStatus() );
		System.out.println();
	}
	
	/**
	 * Asks the user if they want to start a new game
	 */
	private static void startNewGame() {
		System.out.println( "Play again? (Y/N)" );
		String playAgain = scan.nextLine().trim().toLowerCase();
			
		if( playAgain.length() > 0 ) {
			char answer = playAgain.charAt(0);
				
			switch( answer ) {
				case 'y':
					gameMap = new Map( gs );
					break;
				case 'n':
					System.out.println("Goodbye!");
					System.exit(0);
				default:
					System.out.println( "Please enter a valid answer! (Y/N)" );
					startNewGame();
					break;
			}
		}
	}
	
	/**
	 * Parses user input the ensure that the move made by the user is valid
	 */
	private static void parseCommand( String move ) {
		char moveCharacter = move.toLowerCase().trim().charAt(0);
		
		//If the user wants to fire
		if( moveCharacter == 'f' ) {
			System.out.println( "Please select a direction to fire your arrow (or quit to cancel): up, down, left, right" );
			char directionSelection = scan.nextLine().trim().toLowerCase().charAt(0);
			
			//Make sure the input isn't to quit firing
			if( directionSelection != 'q' ) {
				Direction firingDirection = Direction.parseMovementDirection( directionSelection );
				
				if( firingDirection != null ) {
					gameMap.fireArrow( firingDirection );
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
			System.out.println("Goodbye!");
			System.exit(0);
		}
		
		//Reinitialize map
		else if( moveCharacter == 'n' ) {
			gameMap = new Map( gs );
			return;
		}
		
		//Attempt to parse for a movement direction
		else {
			Direction d = Direction.parseMovementDirection( moveCharacter );
			
			//Ensure direction is valid
			if( d != null ) {
				gameMap.move( d );
			}
			
			else {
				System.out.println( invalidMoveMessage );
			}
		}
	}
}
