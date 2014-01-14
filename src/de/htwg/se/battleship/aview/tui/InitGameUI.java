/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import java.io.InputStream;


import de.htwg.se.battleship.controller.InitGameController;
import de.htwg.se.battleship.controller.event.SetPlayer;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class InitGameUI extends UserInterface {

    private final InitGameController controller;

    /**
     * 
     */
    public InitGameUI(InitGameController controller, InputStream stream) {
        super(stream);

        this.controller = controller;
        controller.addObserver(this);
    }

    /**
     * Read input for new player
     * @param e SetPlayer
     */
    public void update(SetPlayer e) {
        logger.info(header());
        logger.info("This feature is not yet implemented");
    }

}
