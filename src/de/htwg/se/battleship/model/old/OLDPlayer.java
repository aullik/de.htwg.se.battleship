/**
 *
 */
package de.htwg.se.battleship.model.old;

import java.util.List;

/**
 * The OLDPlayer interface contains all public methods for a player
 * implementation.
 *
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public interface OLDPlayer {

   /**
    * Returns the OLDPlayer name
    *
    * @return OLDPlayer name
    */
   String getName();

   /**
    * Add new OLDShip instance to OLDPlayer and set OLDPlayer at OLDShip (1:n relationship)
    *
    * @param ship Instance of OLDShip
    */
   void addShip(final OLDShip ship);

   /**
    * Returns true, when OLDPlayer has already an instance of this OLDShip.
    *
    * @param ship Instance of OLDShip
    * @return True/False
    */
   boolean containsShip(final OLDShip ship);

   /**
    * Set relation between OLDGrid and OLDPlayer
    *
    * @param grid Instance of OLDGrid
    */
   void setGrid(final OLDGrid grid);

   /**
    * Returns an instance of the OLDPlayer OLDGrid
    *
    * @return Instance of OLDGrid
    */
   OLDGrid getGrid();

   /**
    * Return the number of fields for all OLDShip of an OLDPlayer
    *
    * @return int
    */
   int getNumberOfShipCells();

   /**
    * Return his human status.
    *
    * @return boolean
    */
   boolean isHuman();

   /**
    * Returns a list of OLDPlayer OLDShip
    *
    * @return List<OLDShip>
    */
   List<OLDShip> getShips();
}
