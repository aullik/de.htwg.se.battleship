/**
 *
 */
package de.htwg.se.battleship.model.old;

import java.util.List;

/**
 * The Ship interface contains all public methods for a Ship implementation.
 *
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public interface Ship {

   /**
    * Returns the owner (OLDPlayer) of this Ship
    *
    * @return OLDPlayer instance
    */
   OLDPlayer getPlayer();

   /**
    * Returns the number of OLDCell from this Ship
    *
    * @return int
    */
   int getNumberOfCells();

   /**
    * Returns List of OLDCell.
    *
    * @return List<OLDCell>
    */
   List<OLDCell> getCells();
}
