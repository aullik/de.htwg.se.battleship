/**
 *
 */
package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.ICell;
import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IShip;

/**
 * This class contains all data of a single Cell
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public class Cell implements ICell {

    private static final int STATUS_NORMAL = 0;
    private static final int STATUS_SHOT = 1;
    private static final int STATUS_HIT = 2;

    private final int x;
    private final int y;
    private final String key;
    private final IGrid grid;
    private IShip ship;
    private int status;

    /**
     * Create a new Cell instance with coordinates and initialize list for Ship
     * and Player.
     * 
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public Cell(int x, int y, IGrid grid) {
        this.x = x;
        this.y = y;
        this.key = Cell.createKey(x, y);
        this.status = STATUS_NORMAL;
        this.grid = grid;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public IGrid getGrid() {
        return grid;
    }

    @Override
    public void setShip(final IShip ship) {
        this.ship = ship;
        ship.addCell(this);
    }

    @Override
    public IShip getShip() {
        return ship;
    }

    @Override
    public boolean isNormal() {
        return (status == STATUS_NORMAL);
    }

    @Override
    public boolean isHit() {
        return (status == STATUS_HIT);
    }

    @Override
    public boolean isShot() {
        return (status == STATUS_SHOT || isHit());
    }

    @Override
    public void setToHit() {
        status = STATUS_HIT;
    }

    @Override
    public void setToShot() {
        status = STATUS_SHOT;
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