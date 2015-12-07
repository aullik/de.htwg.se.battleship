package de.htwg.se.battleship.model.readwrite;

import de.htwg.se.battleship.model.read.RCell;

/**
 * The {@code RWCell} interface defines the public methods for any cell implementation.
 *
 * @author aullik on 07.12.2015.
 */
public interface RWCell extends RCell {


   /**
    * Set relationship between Ship and cell (n:1 relationship)
    *
    * @param ship Instance of a ship
    */
   void setShip(final RWShip ship);

   /**
    * Set status of this Cell to HIT (Player has hit a ship on this Cell).
    */
   void setToHit();

   /**
    * Set status of this Cell to SHOT (Player has made a shot on this Cell).
    */
   void setToShot();


   /**
    * Returns Grid instance.
    *
    * @return RWGrid instance
    */
   RWGrid getGrid();

}
