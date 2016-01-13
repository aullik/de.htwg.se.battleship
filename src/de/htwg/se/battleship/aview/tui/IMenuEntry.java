/**
 *
 */
package de.htwg.se.battleship.aview.tui;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public interface IMenuEntry {

   /**
    * Contains a strategy specific implementation.
    */
   void action();

   /**
    * TUIMenu command
    *
    * @return
    */
   String command();

   /**
    * TUIMenu entry description
    *
    * @return
    */
   String description();
}