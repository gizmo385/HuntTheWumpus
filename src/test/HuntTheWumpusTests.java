package test;

import static org.junit.Assert.*;
import model.Condition;
import model.Direction;

import org.junit.Test;

public class HuntTheWumpusTests {

	/**
	 * Test methods for the Direction enumeration
	 */
	@Test public void testDirection() {
		//Invalid letter
		assertNull( Direction.parseMovementDirection( 'a' ) );
		
		//Lowercase letters
		assertEquals( Direction.DOWN, Direction.parseMovementDirection('d' ) );
		assertEquals( Direction.UP, Direction.parseMovementDirection('u' ) );
		assertEquals( Direction.RIGHT, Direction.parseMovementDirection('r' ) );
		assertEquals( Direction.LEFT, Direction.parseMovementDirection('l' ) );
		
		//Uppercase letters
		assertEquals( Direction.DOWN, Direction.parseMovementDirection('D' ) );
		assertEquals( Direction.UP, Direction.parseMovementDirection('U' ) );
		assertEquals( Direction.RIGHT, Direction.parseMovementDirection('R' ) );
		assertEquals( Direction.LEFT, Direction.parseMovementDirection('L' ) );
		
		//DiffX & DiffY
		assertEquals( 0, Direction.LEFT.getDiffX() );
		assertEquals( -1, Direction.LEFT.getDiffY() );
	}
	
	/**
	 * Test methods for the Condition enumeration
	 */
	@Test public void testCondition() {
		assertEquals( 'B', Condition.BLOOD.getRepresentation() );
		assertEquals( "The wumpus ate you.", Condition.WUMPUS.getStatus() );
	}
}
