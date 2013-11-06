package model;

import java.io.Serializable;

/**
 * Allows for customizable map generation in Hunt the Wumpus, allowing or several game aspects to be changed
 * @author Christopher
 *
 */
public final class GenerationStrategy implements Serializable {

	private static final long serialVersionUID = 6777168229329387722L;
	private int numberOfPits, slimeSpreadDistance, bloodSpreadDistance, rows, columns;
	
	/**
	 * Creates a GenerationStrategy with all necessary generation information
	 * @param numberOfPits The number of slime pits to place on the map
	 * @param slimeSpreadDistance The number of adjacent rooms that will gain the slime effect from a pit
	 * @param bloodSpreadDistance The number of adjacent rooms that will gain the blood effect from a Wumpus
	 * @param rows The number of rows on the map
	 * @param columns The number of columns on the map
	 */
	public GenerationStrategy( int numberOfPits, int slimeSpreadDistance, int bloodSpreadDistance, int rows, int columns ) {
		this.numberOfPits = numberOfPits;
		this.slimeSpreadDistance = slimeSpreadDistance;
		this.bloodSpreadDistance = bloodSpreadDistance;
		this.rows = rows;
		this.columns = columns;
	}

	/**
	 * @return The number of slime pits that will be placed on the map
	 */
	public int getNumberOfPits() {
		return numberOfPits;
	}

	/**
	 * @return The number of adjacent rooms that will be given the SLIME condition when near a pit
	 */
	public int getSlimeSpreadDistance() {
		return slimeSpreadDistance;
	}

	/**
	 * @return The number of adjacent rooms that will be given the BLOOD condition when near a Wumpus
	 */
	public int getBloodSpreadDistance() {
		return bloodSpreadDistance;
	}

	/**
	 * @return The number of rows in the map
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return The number of colums in the map
	 */
	public int getColumns() {
		return columns;
	}

}
