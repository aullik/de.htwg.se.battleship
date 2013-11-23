/**
 *
 */
package de.htwg.se.battleship.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all data of a single Cell
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public class Cell {

    private final int x;
    private final int y;
    private final String key;
    private Grid grid;
    private final List<Ship> ships;

    /**
     * Create a new Cell instance with coordinates and initialize list for Ship
     * and Player.
     * 
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.key = Cell.createKey(x, y);
        this.ships = new ArrayList<Ship>();
    }

    /**
     * Returns the x-coordinate of this Cell.
     * 
     * @return X-Coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this Cell.
     * 
     * @return Y-Coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Return the key for Cell identification
     * 
     * @return Key
     */
    public String getKey() {
        return key;
    }

    /**
     * Set a Grid instance for this Cell and add Cell to Grid (n:1 relationship)
     * 
     * @param grid Instance of a Grid
     */
    public void setGrid(final Grid grid) {
        this.grid = grid;
        grid.addCell(this);
    }

    /**
     * Returns Grid instance.
     * 
     * @return Grid instance
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Add new Ship instance to the Cell and add Cell to Ship (n:m relationship)
     * 
     * @param ship Instance of a ship
     */
    public void addShip(final Ship ship) {
        if (!containsShip(ship)) {
            ships.add(ship);
            ship.addCell(this);
        }
    }

    /**
     * Returns true, when Cell has already an instance of this Ship.
     * 
     * @param ship Instance of an ship
     * @return True/False
     */
    public boolean containsShip(final Ship ship) {
        return ships.contains(ship);
    }

    /**
     * Create with x- and y-coordinate a key for a Map
     * 
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @return Key of the cell
     */
    public static String createKey(final int x, final int y) {
        return x + "." + y;
    }
}