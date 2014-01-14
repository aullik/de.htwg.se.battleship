/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.util.observer.Event;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class InitGame implements Event {

    private final IInitGameController controller;

    /**
     * Controller for game initialization.
     * @param controller
     */
    public InitGame(IInitGameController controller) {
        this.controller = controller;
    }

    /**
     * Return Controller.
     * @return IInitGameController
     */
    public IInitGameController getController() {
        return controller;
    }
}
