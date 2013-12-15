package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.util.observer.IObservable;

/**
 * interface for Controller
 * 
 * @author aullik
 */
public interface IntController extends IObservable {

    /**
     * Signal all user-interface to close for program exit
     */
    void exit();

    /**
     * Start new game
     */
    void newGame();

}
