/**
 * 
 */
package de.htwg.se.battleship.model;

/**
 * The IPlayer interface contains all public methods for a player
 * implementation.
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public interface IPlayer {

    /**
     * Returns the Player name
     * 
     * @return Player name
     */
    String getName();

    /**
     * Add new Ship instance to Player and set Player at Ship (1:n relationship)
     * 
     * @param ship Instance of Ship
     */
    void addShip(final IShip ship);

    /**
     * Returns true, when Player has already an instance of this Ship.
     * 
     * @param ship Instance of Ship
     * @return True/False
     */
    boolean containsShip(final IShip ship);

    /**
     * Set relation between Grid and Player
     * 
     * @param grid Instance of Grid
     */
    void setGrid(final IGrid grid);

    /**
     * Returns an instance of the player's grid
     * 
     * @return Instance of Grid
     */
    IGrid getGrid();
}
