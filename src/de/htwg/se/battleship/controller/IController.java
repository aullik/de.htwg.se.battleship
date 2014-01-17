/**
 * 
 */
package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.util.observer.IObservable;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public interface IController extends IObservable  {

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
