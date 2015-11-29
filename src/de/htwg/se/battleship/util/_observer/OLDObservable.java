package de.htwg.se.battleship.util._observer;

/**
 * Interface for OLDObservable
 *
 * @author aullik
 */
public interface OLDObservable {

   /**
    * adds OLDObserver s to this class and saves s in a list
    *
    * @param s OLDObserver
    */
   void addObserver(OLDObserver s);

   /**
    * remove OLDObserver s from this class
    *
    * @param s OLDObserver
    */
   void removeObserver(OLDObserver s);

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
