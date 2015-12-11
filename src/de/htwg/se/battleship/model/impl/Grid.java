package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RCell;
import de.htwg.se.battleship.model.readwrite.RWCell;
import de.htwg.se.battleship.model.readwrite.RWGrid;

import java.util.HashMap;

/**
 * @author aullik on 07.12.2015.
 */
public class Grid implements RWGrid {

   public static final int DEFAULT_SIZE = 10;

   private final int size;
   private final HashMap<String, RWCell> cells;


   /**
    * Create new instance of a Grid with his size.
    *
    * @param size Number of cell for width/height
    */
   public Grid(final int size) {
      if (size <= 0)
         throw new IllegalArgumentException("size must be a positive number");

      this.size = size;
      this.cells = new HashMap<>();
      initCells();
   }

   private void initCells() {
      for (int x = 0; x < this.size; x++) {
         for (int y = 0; y < this.size; y++) {
            addCell(new Cell(x, y));
         }
      }
   }

   private void addCell(final RWCell cell) {
      cells.put(cell.getKey(), cell);
   }


   @Override
   public int getSize() {
      return size;
   }

   @Override
   public RWCell getCell(final int x, final int y) {
      return cells.get(Cell.createKey(x, y));
   }

   @Override
   public RWCell getCell(final RCell cell) {
      return cells.get(cell.getKey());
   }
}
