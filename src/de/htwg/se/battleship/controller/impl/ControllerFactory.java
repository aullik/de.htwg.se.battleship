/**
 * 
 */
package de.htwg.se.battleship.controller.impl;

import de.htwg.se.battleship.controller.IInitGameController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public final class ControllerFactory {

    private ControllerFactory() {}

    /**
     * Returns an implementation of IInitGameController.
     * @return IInitGameController
     */
    public static IInitGameController createIInitGameController() {
        return new InitGameController();
    }

}
