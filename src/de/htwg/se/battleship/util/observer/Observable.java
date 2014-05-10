package de.htwg.se.battleship.util.observer;

/**
 * Interface for Observable
 * 
 * @author aullik
 */
public interface Observable {

    /**
     * adds Observer s to this class and saves s in a list
     * 
     * @param s Observer
     */
    void addObserver(Observer s);

    /**
     * remove Observer s from this class
     * 
     * @param s Observer
     */
    void removeObserver(Observer s);

    /**
     * removes all Observers from this class
     */
    void removeAllObservers();

    /**
     * Notify all Observers of this class
     */
    void notifyObservers();

    /**
     * Notify all Observers of this class with an specified Event
     * 
     * @param e
     */
    void notifyObservers(Event e);
}
