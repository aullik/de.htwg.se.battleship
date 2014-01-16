/**
 *
 */
package de.htwg.se.battleship.model.impl;

import java.util.HashMap;
import java.util.Map;

import de.htwg.se.battleship.model.ICell;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.model.IShip;

/**
 * This class contains all data of a single Ship from a player
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class Ship implements IShip {

    private final Map<String, ICell> cells;
    private IPlayer player;

    /**
     * Create an instance of Ship and add a Map for Cell instances
     */
    public Ship(final IPlayer player, Map<String, ICell> cells) {
        this.cells = new HashMap<String, ICell>();

        for (ICell cell : cells.values()) {
            addCell(cell);
        }

        setPlayer(player);
    }

    private void setPlayer(final IPlayer player) {
        this.player = player;
        player.addShip(this);
    }

    private void addCell(final ICell cell) {
        if (!cells.containsKey(cell.getKey())) {
            cells.put(cell.getKey(), cell);
            cell.setShip(this);
        }
    }

    @Override
    public IPlayer getPlayer() {
        return player;
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
}
