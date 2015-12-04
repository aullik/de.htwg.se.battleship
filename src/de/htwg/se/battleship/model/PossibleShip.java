package de.htwg.se.battleship.model;

// TODO RENAME

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Possible Ship. Ship is not set in  {@link OLDGrid}
 * <p>
 * Created by aullik on 04.12.2015.
 */
public abstract class PossibleShip implements Ship {

   private final int size;
   private final List<Cell> cells;

   public PossibleShip(int size, List<Cell> cells) {

      if (cells == null || cells.size() != size) {
         throw new IllegalArgumentException("the amount of cells must be equal to size");
      }

      this.size = size;
      this.cells = cells;
   }

   @Override
   public Player getPlayer() {
      throw new NotImplementedException();
   }

   @Override
   public int getNumberOfCells() {
      return size;
   }

   @Override
   public List<Cell> getCells() {
      return cells;
   }
}
