package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RCell;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author aullik on 08.12.2015.
 */
public class PossibleShipTest {

   class TestShipWithSetSize extends ShipWithSetSize {

      public TestShipWithSetSize(final int size, final List<RCell> cells) {
         super(size, cells);
      }
   }

   public static List<RCell> cellList(int size) {
      final List<RCell> ret = new ArrayList<>(size);
      for (int i = 0; i < size; i++) {
         ret.add(new Cell(i, 0));
      }
      return ret;
   }

   @Test
   public void testSize() {
      _testSize(1, cellList(2));
      _testSize(2, cellList(3));
      _testSize(5, cellList(2));
      _testSize(2, cellList(1));
   }

   private void _testSize(int i, List<RCell> cells) {
      try {
         new TestShipWithSetSize(i, cells);
      } catch (IllegalArgumentException ignore) {
         return;
      }
      // if not exception if thrown, this will be called
      Assert.fail();
   }


   @Test
   public void testGetNumberOfCells() throws Exception {
      int size = 5;
      List<RCell> cells = cellList(size);
      assertEquals(size, new TestShipWithSetSize(size, cells).getNumberOfCells());
   }

   @Test
   public void testGetCells() throws Exception {
      int size = 5;
      List<RCell> cells = cellList(size);
      final List<RCell> retCells = new TestShipWithSetSize(size, cells).getCells();

      assertEquals(cells.size(), retCells.size());
      assertTrue(cells.containsAll(retCells));
   }

}