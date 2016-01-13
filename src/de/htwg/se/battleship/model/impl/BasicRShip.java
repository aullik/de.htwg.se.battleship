package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RCell;
import de.htwg.se.battleship.model.read.RShip;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author aullik on 09.12.2015.
 */
public abstract class BasicRShip implements RShip {


   @FunctionalInterface
   private interface IntSupp {

      int get(RCell cell);
   }

   @FunctionalInterface
   /**
    * mix of {@link java.util.function.BiPredicate<java.lang.Integer,java.lang.Integer } and
    * {@link java.util.function.IntPredicate}
    */
   private static interface IntBiPredicate {

      boolean test(int x, int y);
   }

   private final static IntBiPredicate IS_GROWING = (x, y) -> x + 1 == y;
   private final static IntBiPredicate IS_LEVEL = (x, y) -> x == y;


   protected BasicRShip(final List<? extends RCell> cells) {
      checkCellsConnected(cells);

   }

   protected void checkCellsConnected(final List<? extends RCell> cells) {
      if (cells == null || cells.size() == 0)
         throw new IllegalArgumentException("Cells must not be null");
      if (cells.size() == 1)
         return;
      Collections.sort(cells);

      RCell fist = cells.get(0);
      RCell second = cells.get(1);

      if (fist.getX() != second.getX())
         iterateAndTest(cells, RCell::getX, RCell::getY);
      else
         iterateAndTest(cells, RCell::getY, RCell::getX);

   }

   /**
    * @param cells        a list of cells of a minimum size of 2 (unchecked)
    * @param growingCoord the parameter growing
    * @param levelCoord   the parameter staying on level
    */
   private void iterateAndTest(final List<? extends RCell> cells, IntSupp growingCoord, IntSupp levelCoord) {
      final Iterator<? extends RCell> iter = cells.iterator();

      RCell current;
      RCell last = iter.next();


      while (iter.hasNext()) {
         current = iter.next();

         if (!check(last, current, growingCoord, IS_GROWING))
            throw new IllegalArgumentException("Cells are not growing continuously");

         if (!check(last, current, levelCoord, IS_LEVEL))
            throw new IllegalArgumentException("Cells must only be growing on one axis");

         last = current;

      }
   }

   private boolean check(RCell c1, RCell c2, IntSupp supp, final IntBiPredicate check) {
      return check.test(supp.get(c1), supp.get(c2));
   }


   /**
    * Checks two RShips for equal
    *
    * @param obj should be a RShip
    * @return whether obj equals this or not
    */
   @Override
   public boolean equals(final Object obj) {
      if (obj == this)
         return true;
      if (!(obj instanceof RShip))
         return false;

      RShip ship = (RShip) obj;

      if (this.getNumberOfCells() != ship.getNumberOfCells())
         return false;


      return this.getCells().containsAll(ship.getCells());
   }

}
