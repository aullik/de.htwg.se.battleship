/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.controller.IControllerFactory;
import de.htwg.se.battleship.util.observer.Event;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class InitGame implements Event {

    private final IControllerFactory factory;

    /**
     * Controller for game initialization.
     * @param factory IControllerFactory
     */
    public InitGame(IControllerFactory factory) {
        this.factory = factory;
    }

    /**
     * Return instance of IControllerFactory implementation.
     * @return IControllerFactory
     */
    public IControllerFactory getFactory() {
        return factory;
    }
}
