package de.htwg.se.battleship.model.readwrite;

import de.htwg.se.battleship.model.read.RGrid;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.model.read.RShip;

import java.util.List;

/**
 * @author aullik on 07.12.2015.
 */
public interface RWPlayer extends RPlayer {

   /**
    * Add new Ship instance to Player and set Player at Ship (1:n relationship).
    * The Ship will be cloned
    *
    * @param ship Instance of Ship
    * @return returns the cloned Ship. Returns {@code null} if this Ship would collide with others
    */
   RWShip addShip(final RShip ship);


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
