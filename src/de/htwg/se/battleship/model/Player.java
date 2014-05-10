/**
 * 
 */
package de.htwg.se.battleship.model;

import java.util.List;

/**
 * The IPlayer interface contains all public methods for a player
 * implementation.
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public interface Player {

    /**
     * Returns the IPlayer name
     * 
     * @return IPlayer name
     */
    String getName();

    /**
     * Add new IShip instance to IPlayer and set IPlayer at IShip (1:n relationship)
     * 
     * @param ship Instance of IShip
     */
    void addShip(final Ship ship);

    /**
     * Returns true, when IPlayer has already an instance of this IShip.
     * 
     * @param ship Instance of IShip
     * @return True/False
     */
    boolean containsShip(final Ship ship);

    /**
     * Set relation between IGrid and IPlayer
     * 
     * @param grid Instance of IGrid
     */
    void setGrid(final Grid grid);

    /**
     * Returns an instance of the IPlayer IGrid
     * 
     * @return Instance of IGrid
     */
    Grid getGrid();

    /**
     * Return the number of fields for all IShip of an IPlayer
     * @return int
     */
    int getNumberOfShipCells();

    /**
     * Return his human status.
     * @return boolean
     */
    boolean isHuman();

    /**
     * Returns a list of IPlayer IShip
     * @return List<IShip>
     */
    List<Ship> getShips();
}
