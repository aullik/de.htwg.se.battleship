/**
 * 
 */
package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.util.observer.Observable;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public interface IController extends Observable  {

    /**
     * Initiate a new game
     */
    void newGame();

    /**
     * Close programm
     */
    void close();


    /**
     * Notify with empty Winner event.
     */
    void reset();
}
