/**
 * 
 */
package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.controller.impl.InitGameController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class ControllerFactory implements IControllerFactory {

    @Override
    public IInitGameController createIInitGameController() {
        return new InitGameController();
    }

}
