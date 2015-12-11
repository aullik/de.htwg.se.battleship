package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RCell;

import java.util.List;

/**
 * BaseClass for Ships with a Set Size. Ship is not set in  {@link de.htwg.se.battleship.model.impl.Grid}
 * <p>
 * Created by aullik on 04.12.2015.
 */
public abstract class ShipWithSetSize extends BasicRShip {

   private final int size;
   private final List<RCell> cells;

   public ShipWithSetSize(int size, List<RCell> cells) {
      super(cells);
      if (cells.size() != size) {
         throw new IllegalArgumentException("the amount of cells must be equal to size");
      }

      this.size = size;
      this.cells = cells;
   }


   @Override
   public int getNumberOfCells() {
      return size;
   }

   @Override
   public List<RCell> getCells() {
      return cells;
   }

}
