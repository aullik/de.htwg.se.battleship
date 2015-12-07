package de.htwg.se.battleship.model.read;

import java.util.List;

/**
 * The RShip interface contains all public read methods for a Ship implementation.
 *
 * @author aullik on 07.12.2015.
 */
public interface RShip {

   /**
    * Returns the number of Cell from this Ship
    *
    * @return int
    */
   int getNumberOfCells();

   /**
    * Returns the owner (Player) of this Ship
    *
    * @return Player instance
    */
   RPlayer getPlayer();


   /**
    * Returns List of Cell.
    *
    * @return List<Cell>
    */
   List<? extends RCell> getCells();

}