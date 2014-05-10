/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.model.Round;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class SetShot extends AbstractEvent {

    /**
     * Create an instance of SetShip
     * @param round Round
     */
    public SetShot(Round round) {
        super(round);
    }
}