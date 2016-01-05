/**
 *
 */
package de.htwg.se.battleship.model.old.impl;

import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.OLDPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class OLDCellImplTest {

   private OLDCellImpl c1;
   private OLDCellImpl c2;
   private OLDGridImpl g1;
   private OLDGridImpl g2;

   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception {
      g1 = new OLDGridImpl(1, new OLDPlayerImpl(""));
      g2 = new OLDGridImpl(1, new OLDPlayerImpl(""));
      c1 = new OLDCellImpl(1, 2, g1);
      c2 = new OLDCellImpl(12, 8, g2);
   }

   @Test
   public void testX() {
      assertEquals(1, c1.getX());
      assertEquals(12, c2.getX());
   }

   @Test
   public void testY() {
      assertEquals(2, c1.getY());
      assertEquals(8, c2.getY());
   }

   @Test
   public void testKey() {
      assertEquals("1.2", c1.getKey());
      assertEquals("12.8", c2.getKey());
   }

   @Test
   public void testGrid() {
      assertEquals(g1, c1.getGrid());
      assertEquals(g2, c2.getGrid());
   }

   @Test
   public void testShip() {
      OLDPlayer p = new OLDPlayerImpl("test");

      HashMap<String, OLDCell> map1 = new HashMap<String, OLDCell>();
      map1.put(OLDCellImpl.createKey(c1.getX(), c1.getY()), c1);
      assertNull(c1.getShip());
      OLDShipImpl s1 = new OLDShipImpl(p, map1);
      c1.setShip(s1);
      assertEquals(c1.getShip(), s1);

      HashMap<String, OLDCell> map2 = new HashMap<String, OLDCell>();
      map1.put(OLDCellImpl.createKey(c2.getX(), c2.getY()), c2);
      OLDShipImpl s2 = new OLDShipImpl(p, map2);
      assertNull(c2.getShip());
      c2.setShip(s2);
      assertEquals(c2.getShip(), s2);

      assertEquals(c1.getShip(), s1);
   }

   @Test
   public void testStatus() {
      assertTrue(c1.isNormal());
      assertFalse(c1.isHit());
      assertFalse(c1.isShot());

      c1.setToHit();
      assertFalse(c1.isNormal());
      assertTrue(c1.isHit());
      assertTrue(c1.isShot());

      c1.setToShot();
      assertFalse(c1.isNormal());
      assertFalse(c1.isHit());
      assertTrue(c1.isShot());
   }
}