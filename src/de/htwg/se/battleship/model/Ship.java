/**
 *
 */
package de.htwg.se.battleship.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all data of a single Ship from a player
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class Ship {

    private final Map<String, Cell> cells;
    private Player player;

    /**
     * Create an instance of Ship and add a Map for Cell instances
     */
    public Ship() {
        this.cells = new HashMap<String, Cell>();
    }

    /**
     * Set a owner (Player) for this Ship and add the Ship to the Player (n:1
     * relationship)
     * 
     * @param player Instance of Player
     */
    public void setPlayer(final Player player) {
        this.player = player;
        player.addShip(this);
    }

    /**
     * Returns the owner (Player) of this Ship
     * 
     * @return Player instance
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Add Cell instance to Ship and add Ship to Cell (1:n relationship)
     * 
     * @param cell Instance of a Cell
     */
    public void addCell(final Cell cell) {
        if (!cells.containsKey(cell.getKey())) {
            cells.put(cell.getKey(), cell);
            cell.setShip(this);
        }
    }

    /**
     * Get a single Cell instance from the Ship.
     * 
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @return Null/Cell instance
     */
    public Cell getCell(final int x, final int y) {
        String key = Cell.createKey(x, y);
        Cell cell = null;

        if (cells.containsKey(key)) {
            cell = cells.get(key);
        }

        return cell;
    }
}
