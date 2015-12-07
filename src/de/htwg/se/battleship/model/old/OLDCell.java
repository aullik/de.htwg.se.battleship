/**
 *
 */
package de.htwg.se.battleship.model.old;

/**
 * The OLDCell interface defines the public methods for any cell implementation.
 *
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public interface OLDCell {

   /**
    * Returns the x-coordinate of this OLDCell.
    *
    * @return X-Coordinate
    */
   int getX();

   /**
    * Returns the y-coordinate of this OLDCell.
    *
    * @return Y-Coordinate
    */
   int getY();

   /**
    * Return the key for OLDCell identification
    *
    * @return Key
    */
   String getKey();

   /**
    * Returns OLDGrid instance.
    *
    * @return OLDGrid instance
    */
   OLDGrid getGrid();

   /**
    * Set relationship between Ship and cell (n:1 relationship)
    *
    * @param ship Instance of a ship
    */
   void setShip(final Ship ship);

   /**
    * Returns true, when OLDCell has already an instance of this Ship.
    *
    * @return Instance of ship
    */
   Ship getShip();

   /**
    * Returns true, when the OLDPlayer has made nothing with this OLDCell.
    *
    * @return Boolean
    */
   boolean isNormal();

   /**
    * Returns true, when the OLDPlayer has hit a ship on this OLDCell.
    *
    * @return Boolean
    */
   boolean isHit();

   /**
    * Returns true, when the OLDPlayer has made previously a shot on this OLDCell.
    *
    * @return Boolean
    */
   boolean isShot();

   /**
    * Set status of this OLDCell to HIT (OLDPlayer has hit a ship on this OLDCell).
    */
   void setToHit();

   /**
    * Set status of this OLDCell to SHOT (OLDPlayer has made a shot on this OLDCell).
    */
   void setToShot();
}
