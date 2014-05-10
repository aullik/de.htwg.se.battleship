/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.model.Round;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class SetPlayerSuccess extends AbstractEvent {

    /**
     * Create instance of SetPlayerSuccess.
     * @param round IRound
     */
    public SetPlayerSuccess(Round round) {
        super(round);
    }

}
