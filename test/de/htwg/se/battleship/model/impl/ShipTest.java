package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.readwrite.RWCell;
import org.junit.Test;

import java.util.List;

import static de.htwg.se.battleship.model.impl.BasicRShipTest.cellList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author aullik on 08.12.2015.
 */
public class ShipTest {


   @Test
   public void testGetNumberOfCells() throws Exception {
      _testGetNumberOfCells(2);
      _testGetNumberOfCells(3);
      _testGetNumberOfCells(4);
      _testGetNumberOfCells(5);

      Ship s = new Ship(cellList(5));
      assertNotEquals(s.getNumberOfCells(), 1);
      assertNotEquals(s.getNumberOfCells(), 2);
      assertNotEquals(s.getNumberOfCells(), 3);
      assertNotEquals(s.getNumberOfCells(), 4);
      assertNotEquals(s.getNumberOfCells(), 6);

      assertEquals(s.getNumberOfCells(), s.getCells().size());
   }

   private void _testGetNumberOfCells(final int n) {
      Ship s = new Ship(cellList(n));
      assertEquals(n, s.getNumberOfCells());
   }


   @Test
   public void testGetCells() throws Exception {
      List<RWCell> cells = cellList(5);
      Ship s = new Ship(cells);
      assertEquals(cells.size(), s.getCells().size());
      assertTrue(cells.containsAll(s.getCells()));

      cells = cellList(4);
      List<RWCell> cells2 = cellList(5);
      assertTrue(cells2.containsAll(cells));
      cells2.remove(0); //remove cell 1.1

      s = new Ship(cells2);

      assertEquals(cells.size(), s.getCells().size());
      assertFalse(cells.containsAll(s.getCells()));
   }

}