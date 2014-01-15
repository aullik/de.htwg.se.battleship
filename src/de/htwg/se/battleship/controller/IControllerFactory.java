/**
 * 
 */
package de.htwg.se.battleship.controller;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public interface IControllerFactory {

    /**
     * Returns instance of IInitGameController implementation.
     * @return IInitGameController
     */
    IInitGameController createIInitGameController();
}
