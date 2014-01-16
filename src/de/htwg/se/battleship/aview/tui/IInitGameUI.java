/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.event.SetShip;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public interface IInitGameUI {

    /**
     * Read input for new player
     * @param e SetPlayer
     */
    void update(SetPlayer e);

    /**
     * Let a player choose the position of his ships.
     * @param e SetShip
     */
    void update(SetShip e);

    /**
     * Event to signal that player are added successful.
     * @param e SetPlayerSuccess
     */
    void update(SetPlayerSuccess e);
}
