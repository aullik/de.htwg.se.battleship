package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.util.observer.IObservable;

/**
 * interface for Controller
 * 
 * @author aullik
 */
public interface IntController extends IObservable {

    /**
     * just an notify to update Observers with standard event
     */
    void updateNotify();

    /**
     * process logic from input
     * 
     * @param line inputline
     * @return true while there is no CloseEvent
     */
    boolean processInputLine(String line);

}
