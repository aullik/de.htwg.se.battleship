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

    /**
     * Create instance of IShip implementation and add to IPlayer.
     * @param startX  X-position of start point
     * @param startY  Y-position of start point
     * @param endX    X-position of end point
     * @param endY    Y-position of end point
     */
    void ship(Integer startX, Integer startY, Integer endX, Integer endY);

    /**
     * Shot on field.
     * @param x X-position
     * @param y Y-position
     */
    void shot(Integer x, Integer y);
}
