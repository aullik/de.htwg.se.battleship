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

    /**
     * Create new instance of a Grid with his size.
     * 
     * @param size Number of cell for width/height
     */
    public Grid(final int size) {
        this.size = size;
        this.cells = new HashMap<String, Cell>();
    }

    /**
     * Returns the size of the Grid instance
     * 
     * @return Number of cells for width/height
     */
    public int getWidth() {
        return size;
    }

    /**
     * Add Cell instance to the Grid and set Grid of Cell (1:n relationship)
     * 
     * @param cell Instance of a Cell
     */
    public void addCell(final Cell cell) {
        if (cells.containsKey(cell.getKey())) {
            return;
        }

        cells.put(cell.getKey(), cell);
        cell.setGrid(this);
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
        if (cells.containsKey(key)) {
            return cells.get(key);
        }
        return null;
    }
}