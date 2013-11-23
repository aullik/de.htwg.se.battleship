/**
 *
 */
package de.htwg.se.battleship.model;

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
    private Ship ship;

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
     * Set relationship between Ship and cell (n:1 relationship)
     * 
     * @param ship Instance of a ship
     */
    public void setShip(final Ship ship) {
        this.ship=ship;
        ship.addCell(this);
    }

    /**
     * Returns true, when Cell has already an instance of this Ship.
     * 
     * @return Instance of ship
     */
    public Ship getShip() {
        return ship;
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