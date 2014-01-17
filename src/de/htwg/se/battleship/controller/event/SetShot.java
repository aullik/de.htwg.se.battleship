/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.model.IRound;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class SetShot extends AbstractEvent {

    /**
     * Create an instance of SetShip
     * @param round IRound
     */
    public SetShot(IRound round) {
        super(round);
    }

}
