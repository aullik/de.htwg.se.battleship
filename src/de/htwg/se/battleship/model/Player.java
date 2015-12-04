/**
 *
 */
package de.htwg.se.battleship.model;

import java.util.List;

/**
 * The Player interface contains all public methods for a player
 * implementation.
 *
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public interface Player {

   /**
    * Returns the Player name
    *
    * @return Player name
    */
   String getName();

   /**
    * Add new Ship instance to Player and set Player at Ship (1:n relationship)
    *
    * @param ship Instance of Ship
    */
   void addShip(final Ship ship);

   /**
    * Returns true, when Player has already an instance of this Ship.
    *
    * @param ship Instance of Ship
    * @return True/False
    */
   boolean containsShip(final Ship ship);

   /**
    * Set relation between OLDGrid and Player
    *
    * @param grid Instance of OLDGrid
    */
   void setGrid(final OLDGrid grid);

   /**
    * Returns an instance of the Player OLDGrid
    *
    * @return Instance of OLDGrid
    */
   OLDGrid getGrid();

   /**
    * Return the number of fields for all Ship of an Player
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
    * Returns a list of Player Ship
    *
    * @return List<Ship>
    */
   List<Ship> getShips();
}
