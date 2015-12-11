package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.readwrite.RWCell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author aullik on 08.12.2015.
 */
public class GridTest {

   @Test
   public void testGetSize() throws Exception {
      Grid grid;
      grid = new Grid(5);
      assertEquals(5, grid.getSize());
      grid = new Grid(10);
      assertEquals(10, grid.getSize());

      testSize(0);
      testSize(-1);
   }

   private void testSize(int i) {
      try {
         new Grid(i);
      } catch (IllegalArgumentException ignore) {
         return;
      }
      fail();

   }


   @Test
   public void testGetCell() throws Exception {
      Grid grid;
      grid = new Grid(5);
      assertNull(grid.getCell(6, 1)); //6 is out of bounds
      RWCell retCell = grid.getCell(1, 1);
      assertNotNull(retCell);
      Cell cell = new Cell(1, 1);
      assertTrue(cell.equals(retCell));
      assertSame(retCell, grid.getCell(retCell));
      assertSame(retCell, grid.getCell(cell));
      assertNotSame(cell, grid.getCell(cell));
   }

}