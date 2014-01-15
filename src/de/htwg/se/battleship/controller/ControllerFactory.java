/**
 * 
 */
package de.htwg.se.battleship.controller;

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
