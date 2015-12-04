/**
 *
 */
package de.htwg.se.battleship.model;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public interface Round {

   /**
    * Switch to other player.
    */
   void next();

   /**
    * Get current OLDGrid.
    *
    * @return OLDGrid
    */
   OLDGrid getGrid();

   /**
    * Get instance of Opponent OLDGrid.
    *
    * @return OLDGrid
    */
   OLDGrid getOpponentGrid();
}
