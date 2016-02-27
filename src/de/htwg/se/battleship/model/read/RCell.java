package de.htwg.se.battleship.model.read;

/**
 * The {@code RCell} interface defines the public read methods for any cell implementation.
 *
 * @author aullik on 07.12.2015.
 */
public interface RCell extends REnemyCell {

   /**
    * Returns true, when Cell has already an instance of this Ship.
    *
    * @return Instance of ship
    */
   RShip getShip();


   /**
    * @return whether or not a ship is set.
    */
   boolean hasShip();

}
