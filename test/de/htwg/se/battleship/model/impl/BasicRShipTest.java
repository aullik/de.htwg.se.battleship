package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RCell;
import de.htwg.se.battleship.model.readwrite.RWCell;
import org.junit.Assert;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author aullik on 10.12.2015.
 */
public class BasicRShipTest {

   class TestShip extends BasicRShip {

      protected TestShip(final List<? extends RCell> cells) {
         super(cells);
      }

      @Override
      public int getNumberOfCells() {
         throw new NotImplementedException();
      }

      @Override
      public List<? extends RCell> getCells() {
         throw new NotImplementedException();
      }

      @Override
      public boolean isDestroyed() {
         throw new NotImplementedException();
      }
   }

   public static List<RWCell> cellList(int size) {
      return cellList(size, 0);
   }

   public static List<RWCell> cellList(int size, int baseX) {
      final List<RWCell> ret = new ArrayList<>(size);
      for (int i = 0; i < size; i++) {
         ret.add(new Cell(i, baseX));
      }
      return ret;
   }

   @Test
   public void testCheckCellsConnected() throws Exception {
      List<RWCell> cells;
      cells = cellList(5);
      cells.remove(0); // remove cell 0.0
      new TestShip(cells); //if it fails, exception is thrown

      cells = cellList(5);
      cells.remove(3); //removed cell from the middle
      checkCellsExpectIllegalArgumentException(cells);

      cells = new LinkedList<>();
      checkCellsExpectIllegalArgumentException(cells);

      checkCellsExpectIllegalArgumentException(null);

      cells = new LinkedList<>();
      cells.add(new Cell(1, 1));
      cells.add(new Cell(2, 2));
      checkCellsExpectIllegalArgumentException(cells);

      cells = new LinkedList<>();
      cells.add(new Cell(1, 1));
      cells.add(new Cell(4, 4));
      checkCellsExpectIllegalArgumentException(cells);


      cells = new LinkedList<>();
      cells.add(new Cell(3, 0));
      cells.add(new Cell(1, 0));
      cells.add(new Cell(4, 0));
      cells.add(new Cell(2, 0));
      new Ship(cells);


      cells = new LinkedList<>();
      cells.add(new Cell(0, 3));
      cells.add(new Cell(0, 1));
      cells.add(new Cell(0, 4));
      cells.add(new Cell(0, 2));
      new Ship(cells);

      cells = new LinkedList<>();
      cells.add(new Cell(0, 3));
      new Ship(cells);

   }

   private void checkCellsExpectIllegalArgumentException(List<RWCell> cells) {
      try {
         new TestShip(cells);
      } catch (IllegalArgumentException ignore) {
         return;
      }
      // if not exception if thrown, this will be called
      Assert.fail();
   }

   @Test
   public void testEquals() throws Exception {
      List<RWCell> cells1 = cellList(4);
      Ship s1 = new Ship(cells1);
      List<RWCell> cells2 = cellList(5);
      cells2.remove(0); // remove cell 0.0
      Ship s2 = new Ship(cells2);
      List<RWCell> cells3 = cellList(4);
      Ship s3 = new Ship(cells3);
      List<RWCell> cells4 = cellList(5);
      Ship s4 = new Ship(cells4);
      @SuppressWarnings ("UnnecessaryLocalVariable")
      Ship s5 = s1;
      Ship s6 = new Ship(cells1);

      assertFalse(s1.equals(s2));
      assertTrue(s1.equals(s3));
      assertFalse(s1.equals(s4));
      assertTrue(s1.equals(s5));
      assertTrue(s1.equals(s6));

      assertFalse(s1.equals(new Object()));
   }

}