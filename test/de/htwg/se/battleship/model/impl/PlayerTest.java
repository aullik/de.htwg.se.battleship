package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RGrid;
import de.htwg.se.battleship.model.readwrite.RWCell;
import de.htwg.se.battleship.model.readwrite.RWShip;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static de.htwg.se.battleship.model.impl.BasicRShipTest.cellList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author aullik on 08.12.2015.
 */
public class PlayerTest {

   static final String PLAYER = "Player";


   @Test
   public void testGetName() throws Exception {
      Player player;

      player = new Player(PLAYER);
      assertSame(PLAYER, player.getName());

      final ThreadLocalRandom rnd = ThreadLocalRandom.current();
      String name = Cell.createKey(rnd.nextInt(), rnd.nextInt());
      player = new Player(name);
      assertSame(name, player.getName());

   }

   @Test
   public void testWrongName() {
      _testWrongName("");
      _testWrongName(null);
   }

   void _testWrongName(String name) {
      try {
         new Player(name);
      } catch (IllegalArgumentException ignore) {
         return;
      }
      fail();
   }


   @Test
   public void testShips() throws Exception {
      Player player = new Player(PLAYER);

      Ship s1 = new Ship(cellList(5, 1));
      Ship s2 = new Ship(cellList(2, 2));
      Ship s3 = new Ship(cellList(4, 3));
      final List<RWCell> cells = cellList(5, 4);
      cells.remove(0);
      Ship s4 = new Ship(cells);


      RWShip rws1 = player.addShip(s1);
      RWShip rws2 = player.addShip(s2);
      RWShip rws3 = player.addShip(s3);
      RWShip rws4 = player.addShip(s4);

      Ship wrongShip = new Ship(cellList(5, 1));
      assertNull(player.addShip(wrongShip));

      assertEquals(s1, rws1);
      assertEquals(s2, rws2);
      assertEquals(s3, rws3);
      assertEquals(s4, rws4);

      assertNotSame(s1, rws1);
      assertNotSame(s2, rws2);
      assertNotSame(s3, rws3);
      assertNotSame(s4, rws4);


      assertTrue(player.containsShip(s1));
      assertTrue(player.containsShip(s2));
      assertTrue(player.containsShip(s3));
      assertTrue(player.containsShip(s4));

      final List<RWShip> ships = player.getShips();

      assertTrue(ships.contains(rws1));
      assertTrue(ships.contains(rws2));
      assertTrue(ships.contains(rws3));
      assertTrue(ships.contains(rws4));

      final RWShip rwShip = ships.get(0);
      rwShip.getCells().forEach(c -> assertSame(rwShip, c.getShip()));

      final RGrid grid = player.getGrid();
      rwShip.getCells().forEach(c -> assertSame(c, grid.getCell(c)));

   }
}