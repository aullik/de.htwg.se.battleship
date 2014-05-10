/**
 * 
 */
package de.htwg.se.battleship.model;

/**
 * The Grid interface contains all public methods for a grid implementation.
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public interface Grid {

    /**
     * Returns the size of the Grid instance
     * 
     * @return Number of cells for width/height
     */
    int getWidth();

    /**
     * Get a single Cell instance from the Grid.
     * 
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @return Null/Cell instance
     */
    Cell getCell(final int x, final int y);

    /**
     * Get the owner/player of this grid.
     * 
     * @return Player instance
     */
    Player getPlayer();
}
