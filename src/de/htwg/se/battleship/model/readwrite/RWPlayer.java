package de.htwg.se.battleship.model.readwrite;

import de.htwg.se.battleship.model.read.RGrid;
import de.htwg.se.battleship.model.read.RPlayer;

import java.util.List;

/**
 * @author aullik on 07.12.2015.
 */
public interface RWPlayer extends RPlayer {

   /**
    * Add new Ship instance to Player and set Player at Ship (1:n relationship)
    *
    * @param ship Instance of Ship
    */
   void addShip(final RWShip ship);

   /**
    * Set relation between Grid and Player
    *
    * @param grid Instance of Grid
    */
   void setGrid(final RWGrid grid);


   /**
    * Returns an instance of the Player Grid
    *
    * @return Instance of Grid
    */
   RGrid getGrid();


   /**
    * Returns a list of Player Ship
    *
    * @return List<RWShip>
    */
   List<RWShip> getShips();

}
