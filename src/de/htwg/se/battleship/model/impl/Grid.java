/**
 *
 */
package de.htwg.se.battleship.model.impl;

import java.util.HashMap;
import java.util.Map;

import de.htwg.se.battleship.model.ICell;
import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IPlayer;

/**
 * This class contains all data for the Grid
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public class Grid implements IGrid {

    public static final int DEFAULT_SIZE = 10;

    private final int size;
    private final Map<String, ICell> cells;
    private final IPlayer player;

    /**
     * Create new instance of a Grid with his size.
     * 
     * @param size Number of cell for width/height
     */
    public Grid(final int size, final IPlayer player) {
        this.size = size;
        this.cells = new HashMap<String, ICell>();
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

    @Override
    public int getWidth() {
        return size;
    }

    private void addCell(final Cell cell) {
        cells.put(cell.getKey(), cell);
    }

    @Override
    public ICell getCell(final int x, final int y) {
        String key = Cell.createKey(x, y);
        ICell cell = null;

        if (cells.containsKey(key)) {
            cell = cells.get(key);
        }

        return cell;
    }

    @Override
    public IPlayer getPlayer() {
        return player;
    }
}