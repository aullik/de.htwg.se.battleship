/**
 * 
 */
package de.htwg.se.battleship.model;

import java.util.List;

/**
 * The IShip interface contains all public methods for a IShip implementation.
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public interface Ship {

    /**
     * Returns the owner (IPlayer) of this IShip
     * 
     * @return IPlayer instance
     */
    Player getPlayer();

    /**
     * Get a single ICell instance from the IShip.
     * 
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @return Null/ICell instance
     */
    Cell getCell(final int x, final int y);

    /**
     * Returns the number of ICell from this IShip
     * @return int
     */
    int getNumberOfCells();

    /**
     * Returns List of ICell.
     * @return List<ICell>
     */
    List<Cell> getCells();
}
