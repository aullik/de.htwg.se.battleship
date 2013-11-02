/**
 *
 */
package de.htwg.se.battleship.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contains all data for an Player
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class Player {

    private final String name;
    private final List<Ship> ships;
    private final Map<String, Cell> cells;

    /**
     * Create a new Player instance with name and Map for Cell instances
     * @param name Player name
     */
    public Player(final String name) {
        this.name = name;
        this.ships = new ArrayList<>();
        this.cells = new HashMap<>();
    }

    /**
     * Returns the Player name
     * @return Player name
     */
    public String getName() {
        return name;
    }

    /**
     * Add new Ship instance to Player and set Player at Ship (1:n relationship)
     * @param ship Instance of Ship
     */
    public void addShip(final Ship ship) {
        if (containsShip(ship)) {
            return;
        }

        ships.add(ship);
        ship.setPlayer(this);
    }

    /**
     * Returns true, when Player has already an instance of this Ship.
     * @param ship Instance of Ship
     * @return True/False
     */
    public boolean containsShip(final Ship ship) {
        return ships.contains(ship);
    }

    /**
     * Add new Cell instance to Player and add Player to Ship (n:m relationship)
     * @param cell Instance of Cell
     */
    public void addCell(final Cell cell) {
        if (cells.containsKey(cell.getKey())) {
            return;
        }

        cells.put(cell.getKey(), cell);
        cell.addPlayer(this);
    }

    /**
     * Get a single Cell instance from the Player.
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @return Null/Cell instance
     */
    public Cell getCell(final int x, final int y) {
        String key = Cell.createKey(x, y);
        if (cells.containsKey(key)) {
            return cells.get(key);
        }
        return null;
    }
}
