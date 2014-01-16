/**
 * 
 */
package de.htwg.se.battleship.model;

/**
 * The IShip interface contains all public methods for a ship implementation.
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public interface IShip {

    /**
     * Returns the owner (Player) of this Ship
     * 
     * @return Player instance
     */
    IPlayer getPlayer();

    /**
     * Get a single Cell instance from the Ship.
     * 
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @return Null/Cell instance
     */
    ICell getCell(final int x, final int y);
}
