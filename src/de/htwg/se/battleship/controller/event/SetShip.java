/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.model.IRound;
import de.htwg.se.battleship.util.observer.Event;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class SetShip implements Event {

    private final IRound round;

    /**
     * Create instance of SetShip.
     * @param round IRound
     */
    public SetShip(IRound round) {
        this.round = round;
    }

    /**
     * Returns a instance of IRound.
     * @return IRound
     */
    public IRound getRound() {
        return round;
    }
}
