/**
 *
 */
package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.OLDGrid;
import de.htwg.se.battleship.model.Player;
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

      Player p = new PlayerImpl("test1");
      OLDGrid g = new OLDGridImpl(OLDGridImpl.DEFAULT_SIZE, p);
      Cell c = new CellImpl(1, 1, g);

      HashMap<String, Cell> map = new HashMap<String, Cell>();
      map.put(CellImpl.createKey(c.getX(), c.getY()), c);
      map.put("test", c);

      ShipImpl s = new ShipImpl(p, map);

      assertEquals(p, s.getPlayer());
      assertEquals(1, s.getNumberOfCells());
      assertNotEquals(null, s.getCells());
   }
}
