/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.model.ICell;
import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.model.IRound;
import de.htwg.se.battleship.util.observer.Event;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public abstract class AbstractEvent implements Event {

    private final IRound round;

    /**
     * Create instance of an AbstractEvent.
     * @param round IRound
     */
    public AbstractEvent(IRound round) {
        this.round = round;
    }

    /**
     * Returns a instance of IRound.
     * @return IRound
     */
    public IRound getRound() {
        return round;
    }

    /**
     * Returns a instance of IGrid.
     * @return IGrid
     */
    public IGrid getGrid() {
        return getRound().getGrid();
    }

    /**
     * Returns a instance of IPlayer.
     * @return IPlayer
     */
    public IPlayer getPlayer() {
        return getGrid().getPlayer();
    }

    /**
     * Returns a instance of ICell.
     * @param x  X-coordinate
     * @param y  Y-coordinate
     * @return ICell
     */
    public ICell getCell(int x, int y) {
        return getGrid().getCell(x, y);
    }

}
