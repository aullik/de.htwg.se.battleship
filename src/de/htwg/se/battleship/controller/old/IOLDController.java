/**
 *
 */
package de.htwg.se.battleship.controller.old;

import de.htwg.se.battleship.util._observer.OLDObservable;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public interface IOLDController extends OLDObservable {


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
