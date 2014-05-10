/**
 * 
 */
package de.htwg.se.battleship.model;

import java.util.List;

/**
 * The Ship interface contains all public methods for a Ship implementation.
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public interface Ship {

    /**
     * Returns the owner (Player) of this Ship
     * 
     * @return Player instance
     */
    Player getPlayer();

    /**
     * Get a single Cell instance from the Ship.
     * 
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @return Null/Cell instance
     */
    Cell getCell(final int x, final int y);

    /**
     * Returns the number of Cell from this Ship
     * @return int
     */
    int getNumberOfCells();

    /**
     * Returns List of Cell.
     * @return List<Cell>
     */
    List<Cell> getCells();
}
