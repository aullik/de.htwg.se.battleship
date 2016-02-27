package de.htwg.se.battleship.aview.tui.gridpPainter;

import de.htwg.se.battleship.model.impl.Grid;
import de.htwg.se.battleship.model.impl.Ship;
import de.htwg.se.battleship.model.readwrite.RWCell;
import de.htwg.se.battleship.model.readwrite.RWGrid;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author aullik on 25.01.2016.
 */
public class MyGridPainterTest {

   @Test
   public void test() {
      final Grid grid = new Grid(10);

      setShip(grid, 4, 4, growthDirection.PlusY, 2);
      setShip(grid, 0, 1, growthDirection.PlusY, 9);
      setShip(grid, 9, 0, growthDirection.MinusX, 9);

      final MyGridPainter gridPainter = new MyGridPainter(grid);
      System.out.println(gridPainter.paintGrid());
      System.out.println("finished");
   }

   private void setShip(RWGrid grid, int xStart, int yStart, growthDirection direction, int size) {
      final ArrayList<RWCell> list = new ArrayList<>(size);
      for (int i = 0; i < size; i++) {
         list.add(grid.getCell(xStart, yStart));
         xStart = direction.xGrowth.apply(xStart);
         yStart = direction.yGrowth.apply(yStart);
      }
      final Ship ship = new Ship(list);
      list.forEach(c -> c.setShip(ship));
   }

   private interface intFunc {

      int apply(int i);
   }

   private enum growthDirection {
      PlusX(x -> x + 1, y -> y),
      MinusX(x -> x - 1, y -> y),
      PlusY(x -> x, y -> y + 1),
      MinuxY(x -> x, y -> y - 1);

      private final intFunc xGrowth;
      private final intFunc yGrowth;

      growthDirection(intFunc xGrowth, intFunc yGrowth) {
         this.xGrowth = xGrowth;
         this.yGrowth = yGrowth;
      }
   }

}