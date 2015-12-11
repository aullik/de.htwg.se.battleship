package de.htwg.se.battleship.model.read;

/**
 * The {@code RCell} interface defines the public read methods for any cell implementation.
 *
 * @author aullik on 07.12.2015.
 */
public interface RCell extends Comparable<RCell> {

   /**
    * Returns the x-coordinate of this Cell.
    *
    * @return X-Coordinate
    */
   int getX();

   /**
    * Returns the y-coordinate of this Cell.
    *
    * @return Y-Coordinate
    */
   int getY();

   /**
    * Return the key for Cell identification
    *
    * @return Key
    */
   String getKey();


   /**
    * Returns true, when Cell has already an instance of this Ship.
    *
    * @return Instance of ship
    */
   RShip getShip();

   /**
    * Returns true, when the Player has made nothing with this Cell.
    *
    * @return Boolean
    */
   boolean isNormal();

   /**
    * Returns true, when the Player has hit a ship on this Cell.
    *
    * @return Boolean
    */
   boolean isHit();

   /**
    * Returns true, when the Player has made previously a shot on this Cell.
    *
    * @return Boolean
    */
   boolean isShot();

}
