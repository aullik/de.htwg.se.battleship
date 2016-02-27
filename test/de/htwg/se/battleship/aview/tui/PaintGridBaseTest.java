package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.gridpPainter.MyGridPainter;
import de.htwg.se.battleship.model.impl.Grid;
import de.htwg.se.battleship.model.read.RGrid;
import org.junit.Test;

/**
 * @author aullik on 20.01.2016.
 */
public class PaintGridBaseTest {


   @Test
   public void test() {

      RGrid g = new Grid(10);
      final MyGridPainter paintGrid = new MyGridPainter(g);

      System.out.println(paintGrid.paintGrid());
   }

}