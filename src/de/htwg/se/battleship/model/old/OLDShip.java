/**
 *
 */
package de.htwg.se.battleship.model.old;

import java.util.List;

/**
 * The OLDShip interface contains all public methods for a OLDShip implementation.
 *
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public interface OLDShip {

   /**
    * Returns the owner (OLDPlayer) of this OLDShip
    *
    * @return OLDPlayer instance
    */
   OLDPlayer getPlayer();

   /**
    * Returns the number of OLDCell from this OLDShip
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
