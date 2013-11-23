/**
 *
 */
package de.htwg.se.battleship.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all data for the Grid
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public class Grid {

    private final int size;
    private final Map<String, Cell> cells;
    private final Player player;

    /**
     * Create new instance of a Grid with his size.
     * 
     * @param size Number of cell for width/height
     */
    public Grid(final int size, final Player player) {
        this.size   = size;
        this.cells  = new HashMap<String, Cell>();
        this.player = player;
        player.setGrid(this);

        initCells();
    }

    private void initCells() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                addCell(new Cell(i, j, this));
            }
        }
    }

    /**
     * Returns the size of the Grid instance
     * 
     * @return Number of cells for width/height
     */
    public int getWidth() {
        return size;
    }

    private void addCell(final Cell cell) {
        cells.put(cell.getKey(), cell);
    }

    /**
     * Get a single Cell instance from the Grid.
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

    /**
     * Get the owner/player of this grid.
     * 
     * @return Player instance
     */
    public Player getPlayer() {
        return player;
    }
}