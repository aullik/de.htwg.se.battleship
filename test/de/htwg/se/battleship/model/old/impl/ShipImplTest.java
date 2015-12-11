/**
 *
 */
package de.htwg.se.battleship.model.old.impl;

import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.OLDGrid;
import de.htwg.se.battleship.model.old.OLDPlayer;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class ShipImplTest {

   @Test
   public void testPlayer() {

      OLDPlayer p = new OLDPlayerImpl("test1");
      OLDGrid g = new OLDGridImpl(OLDGridImpl.DEFAULT_SIZE, p);
      OLDCell c = new OLDCellImpl(1, 1, g);

      HashMap<String, OLDCell> map = new HashMap<String, OLDCell>();
      map.put(OLDCellImpl.createKey(c.getX(), c.getY()), c);
      map.put("test", c);

      ShipImpl s = new ShipImpl(p, map);

      assertEquals(p, s.getPlayer());
      assertEquals(1, s.getNumberOfCells());
      assertNotEquals(null, s.getCells());
   }
}
