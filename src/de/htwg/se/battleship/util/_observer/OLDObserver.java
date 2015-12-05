package de.htwg.se.battleship.util._observer;

/**
 * Interface for OLDObserver
 *
 * @author aullik
 */
@Deprecated
public interface OLDObserver {

   /**
    * Updates specified to e whenever it got notified
    *
    * @param e Event
    */
   void update(Event e);

}
