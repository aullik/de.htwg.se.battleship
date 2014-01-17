/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.model.IRound;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class Winner extends AbstractEvent {

    /**
     * Create an instance of Winner.
     * @param round IRound
     */
    public Winner(IRound round) {
        super(round);
    }

}
