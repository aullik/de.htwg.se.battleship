package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.util.observer.IObservable;

/**
 * interface for Controller
 * 
 * @author aullik
 */
public interface IntController extends IObservable {

    /**
     * just an notify to update Observers
     */
    void updateNotify();


    /**
     * Initialize game
     */
    void newgame();

    /**
     * Initialize game with grid size: size
     * 
     * @param size
     */
    void newgame(int size, final String playername1,
            final String playername2);

}
