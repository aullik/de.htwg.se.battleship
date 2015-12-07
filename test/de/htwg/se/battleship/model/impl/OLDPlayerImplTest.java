/**
 *
 */
package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.OLDCellImpl;
import de.htwg.se.battleship.model.old.OLDGridImpl;
import de.htwg.se.battleship.model.old.OLDPlayerImpl;
import de.htwg.se.battleship.model.old.Ship;
import de.htwg.se.battleship.model.old.ShipImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class OLDPlayerImplTest {

   private OLDPlayerImpl p1;
   private OLDPlayerImpl p2;

   private final static String NAME1 = "philipp";
   private final static String NAME2 = "nicolas";

   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception {
      p1 = new OLDPlayerImpl(NAME1);
      p2 = new OLDPlayerImpl(NAME2);
   }

   @Test
   public void testIsHuman() {
      assertTrue(p1.isHuman());
      assertTrue(p2.isHuman());
   }

   @Test
   public void testGetName() {
      assertEquals(NAME1, p1.getName());
      assertEquals(NAME2, p2.getName());
   }

   @Test
   public void testShip() {
      HashMap<String, OLDCell> map = new HashMap<String, OLDCell>();

      ShipImpl s1 = new ShipImpl(p1, map);
      p1.addShip(s1);
      assertTrue(p1.containsShip(s1));
      assertEquals(0, p1.getNumberOfShipCells());

      map.put(OLDCellImpl.createKey(1, 1), new OLDCellImpl(1, 1, new OLDGridImpl(OLDGridImpl.DEFAULT_SIZE, p1)));

      ShipImpl s2 = new ShipImpl(p1, map);
      assertTrue(p1.containsShip(s2));
      p1.addShip(s2);
      assertTrue(p1.containsShip(s1));
      assertEquals(1, p1.getNumberOfShipCells());

      List<Ship> list = p1.getShips();
      assertEquals(2, list.size());
      assertTrue(list.contains(s1));
      assertTrue(list.contains(s2));
   }

   @Test
   public void testGrid() {
      OLDGridImpl g1 = new OLDGridImpl(1, p1);
      assertEquals(p1.getGrid(), g1);

      OLDGridImpl g2 = new OLDGridImpl(1, p2);
      assertEquals(p2.getGrid(), g2);
   }
}
