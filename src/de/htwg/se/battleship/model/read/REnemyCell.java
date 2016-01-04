package de.htwg.se.battleship.model.read;

import javafx.beans.Observable;

/**
 * The {@code REnemyCell} interface defines the public read methods for any cell implementation, as the enemy can see
 * them.
 *
 * @author aullik on 04.01.2016.
 */
public interface REnemyCell extends Comparable<REnemyCell>, Observable {

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
