/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.model.IRound;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class SetPlayerSuccess extends AbstractEvent {

    /**
     * Create instance of SetPlayerSuccess.
     * @param round IRound
     */
    public SetPlayerSuccess(IRound round) {
        super(round);
    }

}
