/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.model.ICell;
import de.htwg.se.battleship.model.IRound;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class IsShot extends AbstractEvent {

    private final ICell cell;

    /**
     * Create an instance of IsShot.
     * @param round IRound
     */
    public IsShot(IRound round, ICell cell) {
        super(round);

        this.cell = cell;
    }

    /**
     * 
     * @return ICell
     */
    public ICell getCell() {
        return cell;
    }

}
