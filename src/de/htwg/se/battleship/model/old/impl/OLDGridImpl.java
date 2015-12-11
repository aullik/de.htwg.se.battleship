/**
 *
 */
package de.htwg.se.battleship.model.old.impl;

import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.OLDGrid;
import de.htwg.se.battleship.model.old.OLDPlayer;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all data for the OLDGrid
 *
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class OLDGridImpl implements OLDGrid {

   public static final int DEFAULT_SIZE = 10;

   private final int size;
   private final Map<String, OLDCell> cells;
   private final OLDPlayer player;

   /**
    * Create new instance of a OLDGrid with his size.
    *
    * @param size Number of cell for width/height
    */
   public OLDGridImpl(final int size, final OLDPlayer player) {
      this.size = size;
      this.cells = new HashMap<String, OLDCell>();
      this.player = player;
      player.setGrid(this);

      initCells();
   }

   private void initCells() {
      for (int i = 0; i < this.size; i++) {
         for (int j = 0; j < this.size; j++) {
            addCell(new OLDCellImpl(i, j, this));
         }
      }
   }

   @Override
   public int getWidth() {
      return size;
   }

   private void addCell(final OLDCellImpl cell) {
      cells.put(cell.getKey(), cell);
   }

   @Override
   public OLDCell getCell(final int x, final int y) {
      String key = OLDCellImpl.createKey(x, y);
      OLDCell cell = null;

      if (cells.containsKey(key)) {
         cell = cells.get(key);
      }

      return cell;
   }

   @Override
   public OLDPlayer getPlayer() {
      return player;
   }
}