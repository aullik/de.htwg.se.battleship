package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RCell;
import de.htwg.se.battleship.model.read.REnemyCell;
import de.htwg.se.battleship.model.readwrite.RWCell;
import de.htwg.se.battleship.model.readwrite.RWShip;

/**
 * @author aullik on 07.12.2015.
 */
public class Cell implements RWCell {


   private final int x;
   private final int y;
   private final String key;

   private RWShip ship = null;
   private STATUS status;


   public Cell(final int x, final int y) {
      this.x = x;
      this.y = y;
      this.key = Cell.createKey(x, y);
      this.status = STATUS.NORMAL;

   }

   @Override
   public void setShip(final RWShip ship) {
      this.ship = ship;
   }

   @Override
   public void setToHit() {
      this.status = STATUS.HIT;
   }

   @Override
   public void setToShot() {
      this.status = STATUS.SHOT;
   }

   @Override
   public int getX() {
      return x;
   }

   @Override
   public int getY() {
      return y;
   }

   @Override
   public String getKey() {
      return key;
   }

   @Override
   public RWShip getShip() {
      return ship;
   }

   @Override
   public boolean isNormal() {
      return STATUS.NORMAL.equals(status);
   }

   @Override
   public boolean isHit() {
      return STATUS.HIT.equals(status);
   }

   @Override
   public boolean isShot() {
      return STATUS.SHOT.equals(status);
   }


   @Override
   public boolean equals(final Object obj) {
      if (this == obj)
         return true;
      if (!(obj instanceof RCell))
         return false;

      RCell cell = (RCell) obj;

      return cell.getKey().equals(getKey());

   }

   /**
    * Create with x- and y-coordinate a key for a Map
    *
    * @param x X-Coordinate
    * @param y Y-Coordinate
    * @return Key of the cell
    */
   public static String createKey(final int x, final int y) {
      return x + "." + y;
   }

   @Override
   public int compareTo(final REnemyCell o) {

      int x = Integer.compare(this.getX(), o.getX());

      if (x == 0)
         return Integer.compare(this.getY(), o.getY());
      else
         return x;
   }

   private enum STATUS {
      NORMAL, SHOT, HIT
   }

}
