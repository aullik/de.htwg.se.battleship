/**
 *
 */
package de.htwg.se.battleship.model.old;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public interface OLDRound {

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
