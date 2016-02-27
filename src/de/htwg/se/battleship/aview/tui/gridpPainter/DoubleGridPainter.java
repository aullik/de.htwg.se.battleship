package de.htwg.se.battleship.aview.tui.gridpPainter;

import de.htwg.se.battleship.model.impl.Grid;
import de.htwg.se.battleship.model.read.REnemyCell;
import de.htwg.se.battleship.model.read.REnemyGrid;
import de.htwg.se.battleship.model.read.RGrid;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author aullik on 18.01.2016.
 */
public class DoubleGridPainter extends GridPainterBase<RGrid> {

   public final static String gridSeperator = "\t\t\t";

   private final MyGridPainter friendly;
   private final EnemyGridPainter enemy;
   private final String border;


   public DoubleGridPainter(final RGrid friendly, final REnemyGrid enemy) {
      super(new Grid(friendly.getSize()));
      if (friendly.getSize() != enemy.getSize())
         throw new IllegalArgumentException("both grids must be the same size");
      this.friendly = new MyGridPainter(friendly);
      this.enemy = new EnemyGridPainter(enemy);
      this.border = createBorder();
   }

   private String createBorder() {
      return friendly.printBorder() + gridSeperator + enemy.printBorder();
   }

   @Override
   protected String printBorder() {
      return border;
   }

   @Override
   protected String printCellLine(final int l) {
      return friendly.printCellLine(l) + gridSeperator + enemy.printCellLine(l);
   }


   @Override
   protected CELL_STATE decideCellState(final int line, final int num) {
      throw new NotImplementedException();
   }


   private class EnemyGridPainter extends GridPainterBase<REnemyGrid> {


      protected EnemyGridPainter(final REnemyGrid grid) {
         super(grid);
      }

      @Override
      protected CELL_STATE decideCellState(final int line, final int num) {
         return printCell(grid.getCell(line, num));
      }

      private CELL_STATE printCell(REnemyCell cell) {
         if (cell.isHit())
            return CELL_STATE.HIT;
         else if (cell.isShot())
            return CELL_STATE.SHOT;
         else
            return CELL_STATE.EMPTY;
      }
   }
}
