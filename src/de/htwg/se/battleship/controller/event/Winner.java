/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.model.Round;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class Winner extends AbstractEvent {

    /**
     * Create an instance of Winner.
     * @param round Round
     */
    public Winner(Round round) {
        super(round);
    }

}
