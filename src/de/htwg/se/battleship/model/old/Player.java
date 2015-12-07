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
public interface Player {

   /**
    * Returns the OLDPlayer name
    *
    * @return OLDPlayer name
    */
   String getName();

   /**
    * Add new Ship instance to OLDPlayer and set OLDPlayer at Ship (1:n relationship)
    *
    * @param ship Instance of Ship
    */
   void addShip(final Ship ship);

   /**
    * Returns true, when OLDPlayer has already an instance of this Ship.
    *
    * @param ship Instance of Ship
    * @return True/False
    */
   boolean containsShip(final Ship ship);

   /**
    * Set relation between OLDGrid and OLDPlayer
    *
    * @param grid Instance of OLDGrid
    */
   void setGrid(final Grid grid);

   /**
    * Returns an instance of the OLDPlayer OLDGrid
    *
    * @return Instance of OLDGrid
    */
   Grid getGrid();

   /**
    * Return the number of fields for all Ship of an OLDPlayer
    *
    * @return int
    */
   int getNumberOfShipCells();


   /**
    * Returns a list of OLDPlayer Ship
    *
    * @return List<Ship>
    */
   List<Ship> getShips();
}
