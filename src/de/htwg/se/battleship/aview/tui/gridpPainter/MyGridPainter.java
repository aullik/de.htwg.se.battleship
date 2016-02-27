package de.htwg.se.battleship.aview.tui.gridpPainter;

import de.htwg.se.battleship.model.read.RCell;
import de.htwg.se.battleship.model.read.RGrid;

/**
 * @author aullik on 18.01.2016.
 */
public class MyGridPainter extends GridPainterBase<RGrid> {

   public MyGridPainter(final RGrid grid) {
      super(grid);
   }

   @Override
   protected CELL_STATE decideCellState(final int line, final int num) {
      RCell cell = grid.getCell(line, num);
      return printCell(cell);
   }

   private CELL_STATE printCell(final RCell cell) {
      if (cell.isHit())
         return CELL_STATE.HIT;
      else if (cell.isShot())
         return CELL_STATE.SHOT;
      else if (cell.hasShip())
         return CELL_STATE.SHIP;
      else
         return CELL_STATE.EMPTY;
   }
}
