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
     * Set a owner (Player) for this Ship and add the Ship to the Player (n:1
     * relationship)
     * 
     * @param player Instance of Player
     */
    void setPlayer(final IPlayer player);

    /**
     * Returns the owner (Player) of this Ship
     * 
     * @return Player instance
     */
    IPlayer getPlayer();

    /**
     * Add Cell instance to Ship and add Ship to Cell (1:n relationship)
     * 
     * @param cell Instance of a Cell
     */
    void addCell(final ICell cell);

    /**
     * Get a single Cell instance from the Ship.
     * 
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @return Null/Cell instance
     */
    ICell getCell(final int x, final int y);
}
