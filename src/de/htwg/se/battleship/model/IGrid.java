/**
 * 
 */
package de.htwg.se.battleship.model;

/**
 * The IGrid interface contains all public methods for a grid implementation.
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public interface IGrid {

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
    ICell getCell(final int x, final int y);

    /**
     * Get the owner/player of this grid.
     * 
     * @return Player instance
     */
    IPlayer getPlayer();
}
