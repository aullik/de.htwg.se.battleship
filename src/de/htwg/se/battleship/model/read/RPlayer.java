package de.htwg.se.battleship.model.read;

import java.util.List;

/**
 * The Player interface contains all public read methods for a player
 * implementation.
 *
 * @author aullik on 07.12.2015.
 */
public interface RPlayer {


   /**
    * Returns the Player name
    *
    * @return Player name
    */
   String getName();


   /**
    * Returns true, when Player has already an instance of this Ship.
    *
    * @param ship Instance of Ship
    * @return True/False
    */
   boolean containsShip(final RShip ship);


   /**
    * Return the number of fields for all Ship of an Player
    *
    * @return int
    */
   int getNumberOfShipCells();

   /**
    * Returns an instance of the Player Grid
    *
    * @return Instance of Grid
    */
   RGrid getGrid();


   /**
    * Returns a list of Player Ship
    *
    * @return List<RShip>
    */
   List<? extends RShip> getShips();

}
