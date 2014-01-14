/**
 * 
 */
package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.util.observer.IObservable;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public interface IInitGameController extends IObservable {

    /**
     * Start game initialization
     */
    void init();

    /**
     * Init player.
     * @param p1 Name of player one
     * @param p2 Name of player two
     */
    void player(String p1, String p2);
}
