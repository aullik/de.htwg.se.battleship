/**
 *
 */
package de.htwg.se.battleship.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all data for an Player
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public class Player {

    private final String name;
    private final List<Ship> ships;
    private Grid grid;

    /**
     * Create a new Player instance with name and Map for Cell instances
     * 
     * @param name Player name
     */
    public Player(final String name) {
        this.name = name;
        this.ships = new ArrayList<Ship>();
    }

    /**
     * Returns the Player name
     * 
     * @return Player name
     */
    public String getName() {
        return name;
    }

    /**
     * Add new Ship instance to Player and set Player at Ship (1:n relationship)
     * 
     * @param ship Instance of Ship
     */
    public void addShip(final Ship ship) {
        if (!containsShip(ship)) {
            ships.add(ship);
            ship.setPlayer(this);
        }
    }

    /**
     * Returns true, when Player has already an instance of this Ship.
     * 
     * @param ship Instance of Ship
     * @return True/False
     */
    public boolean containsShip(final Ship ship) {
        return ships.contains(ship);
    }

    /**
     * Set relation between Grid and Player
     * @param grid Instance of Grid
     */
    public void setGrid(final Grid grid) {
        this.grid = grid;
    }

    /**
     * Returns an instance of the player's grid
     * @return Instance of Grid
     */
    public Grid getGrid() {
        return grid;
    }
}
