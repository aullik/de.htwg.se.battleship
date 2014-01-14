/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.controller.InitGameController;
import de.htwg.se.battleship.util.observer.Event;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class InitGame implements Event {

    private final InitGameController controller;

    /**
     * Controller for game initialization.
     * @param controller
     */
    public InitGame(InitGameController controller) {
        this.controller = controller;
    }

    /**
     * Return Controller.
     * @return InitGameController
     */
    public InitGameController getController() {
        return controller;
    }
}
