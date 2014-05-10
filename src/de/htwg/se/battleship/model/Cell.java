/**
 * 
 */
package de.htwg.se.battleship.model;

/**
 * The Cell interface defines the public methods for any cell implementation.
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public interface Cell {

    /**
     * Returns the x-coordinate of this Cell.
     * 
     * @return X-Coordinate
     */
    int getX();

    /**
     * Returns the y-coordinate of this Cell.
     * 
     * @return Y-Coordinate
     */
    int getY();

    /**
     * Return the key for Cell identification
     * 
     * @return Key
     */
    String getKey();

    /**
     * Returns Grid instance.
     * 
     * @return Grid instance
     */
    Grid getGrid();

    /**
     * Set relationship between Ship and cell (n:1 relationship)
     * 
     * @param ship Instance of a ship
     */
    void setShip(final Ship ship);

    /**
     * Returns true, when Cell has already an instance of this Ship.
     * 
     * @return Instance of ship
     */
    Ship getShip();

    /**
     * Returns true, when the Player has made nothing with this Cell.
     * 
     * @return Boolean
     */
    boolean isNormal();

    /**
     * Returns true, when the Player has hit a ship on this Cell.
     * 
     * @return Boolean
     */
    boolean isHit();

    /**
     * Returns true, when the Player has made previously a shot on this Cell.
     * 
     * @return Boolean
     */
    boolean isShot();

    /**
     * Set status of this Cell to HIT (Player has hit a ship on this Cell).
     */
    void setToHit();

    /**
     * Set status of this Cell to SHOT (Player has made a shot on this Cell).
     */
    void setToShot();
}
