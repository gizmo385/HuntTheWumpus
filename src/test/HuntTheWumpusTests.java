package test;

import static org.junit.Assert.*;
import model.Condition;
import model.Direction;
import model.Map;

import org.junit.Test;
/**
 * Some test for simple testing of the program in addition to 
 * testing while playing
 * 
 * @author Christopher Chapline
 * @author Christopher Toepfer
 * @author David Christy
 * @author James Fagan
 */

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
	
	/**
	 * A few tests using non-random maps to check for accurate status messages,
	 * test for when pit and wumpus are adjacent to each other, and to test
	 * wraparound for the hunter.
	 */
	@Test public void testRoomSets()
	{
		Map testMap = new Map(10, 10, 14, 4, 24);
		assertEquals(testMap.getStatus(), Condition.BLOOD.getStatus());
		testMap.fireArrow(Direction.UP);
		assertFalse(testMap.playing());
		assertEquals(testMap.getStatus(), "You killed the Wumpus! You are a true hero!");
		
		testMap = new Map(10, 10, 14, 3, 4);
		assertEquals(testMap.getStatus(), Condition.GOOP.getStatus());
		assertTrue(testMap.playing());
		testMap.move(Direction.LEFT);
		assertEquals(testMap.getStatus(), Condition.PIT.getStatus());
		assertFalse(testMap.playing());
		
		testMap = new Map(10, 10, 14, 3, 4);
		assertEquals(testMap.getStatus(), Condition.GOOP.getStatus());
		assertTrue(testMap.playing());
		testMap.move(Direction.DOWN);
		assertEquals(testMap.getStatus(), Condition.WUMPUS.getStatus());
		assertFalse(testMap.playing());
		
		testMap = new Map(10, 10, 3, 5, 94);
		assertEquals(testMap.getStatus(), Condition.EMPTY.getStatus());
		assertTrue(testMap.playing());
		testMap.move(Direction.DOWN);
		assertEquals(testMap.getStatus(), Condition.GOOP.getStatus());
		assertTrue(testMap.playing());
	}
}
