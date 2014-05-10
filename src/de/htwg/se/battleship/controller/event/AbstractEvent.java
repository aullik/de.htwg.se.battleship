/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.model.Round;
import de.htwg.se.battleship.util.observer.Event;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public abstract class AbstractEvent implements Event {

    private final Round round;

    /**
     * Create instance of an AbstractEvent.
     * @param round IRound
     */
    public AbstractEvent(Round round) {
        this.round = round;
    }

    /**
     * Returns a instance of IRound.
     * @return IRound
     */
    public Round getRound() {
        return round;
    }

    /**
     * Returns a instance of IGrid.
     * @return IGrid
     */
    public Grid getGrid() {
        return getRound().getGrid();
    }

    /**
     * Returns a instance of IPlayer.
     * @return IPlayer
     */
    public Player getPlayer() {
        return getGrid().getPlayer();
    }

    /**
     * Returns a instance of ICell.
     * @param x  X-coordinate
     * @param y  Y-coordinate
     * @return ICell
     */
    public Cell getCell(int x, int y) {
        return getGrid().getCell(x, y);
    }

}
