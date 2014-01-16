/**
 * 
 */
package de.htwg.se.battleship.model;

/**
 * The IShip interface contains all public methods for a IShip implementation.
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public interface IShip {

    /**
     * Returns the owner (IPlayer) of this IShip
     * 
     * @return IPlayer instance
     */
    IPlayer getPlayer();

    /**
     * Get a single ICell instance from the IShip.
     * 
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @return Null/ICell instance
     */
    ICell getCell(final int x, final int y);

    /**
     * Returns the number of ICell from this IShip
     * @return int
     */
    int getNumberOfCells();
}
