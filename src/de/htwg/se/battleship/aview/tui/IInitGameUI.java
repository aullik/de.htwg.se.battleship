/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.controller.event.SetPlayer;

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
}
