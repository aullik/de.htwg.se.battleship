package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.readwrite.RWShip;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author aullik on 08.12.2015.
 */
public class CellTest {


   @Test
   public void testSet_GetShip() throws Exception {
      Cell c = new Cell(1, 1);
      RWShip ship = c.getShip();
      assertNull(ship);
      ship = new Ship(BasicRShipTest.cellList(5));
      c.setShip(ship);
      assertEquals(ship, c.getShip());

   }

   @Test
   public void testIsNormal() throws Exception {
      Cell c = new Cell(1, 1);
      assertTrue(c.isNormal());
      assertFalse(c.isHit());

      c.setToHit();
      assertFalse(c.isNormal());
   }

   @Test
   public void testSet_IsToHit() throws Exception {
      Cell c = new Cell(1, 1);
      assertFalse(c.isHit());
      c.setToHit();
      assertTrue(c.isHit());

   }

   @Test
   public void testSet_IsToShot() throws Exception {
      Cell c = new Cell(1, 1);
      assertFalse(c.isShot());
      c.setToShot();
      assertTrue(c.isShot());
   }

   @Test
   public void testCoords() throws Exception {
      _testCoords(1, 1);
      _testCoords(1, 2);
      _testCoords(1, 3);
      _testCoords(1, 4);
      _testCoords(2, 1);
      _testCoords(3, 1);
      _testCoords(4, 1);

   }

   private void _testCoords(final int x, final int y) {
      Cell c = new Cell(x, y);
      assertEquals(x, c.getX());
      assertEquals(y, c.getY());
      assertEquals(Cell.createKey(x, y), c.getKey());
   }


   @Test
   public void testEquals() throws Exception {
      Cell c1 = new Cell(1, 1);
      Cell c2 = new Cell(1, 1);
      Cell c3 = new Cell(2, 2);

      //noinspection EqualsWithItself
      assertTrue(c1.equals(c1));
      assertTrue(c1.equals(c2));
      assertFalse(c1.equals(c3));
      assertFalse(c1.equals(new Object()));
   }

   @Test
   public void testCompare() {
      Cell c1 = new Cell(1, 1);
      Cell c2 = new Cell(1, 1);
      Cell c3 = new Cell(2, 1);
      Cell c4 = new Cell(1, 2);

      assertEquals(0, c1.compareTo(c1));
      assertEquals(0, c1.compareTo(c2));
      
      assertTrue(c1.compareTo(c3) < 0);
      assertTrue(c1.compareTo(c4) < 0);

      assertTrue(c3.compareTo(c1) > 0);
      assertTrue(c4.compareTo(c1) > 0);

   }

}