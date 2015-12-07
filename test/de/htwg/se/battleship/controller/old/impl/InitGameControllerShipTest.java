/**
 *
 */
package de.htwg.se.battleship.controller.old.impl;

import de.htwg.se.battleship.controller.old.IInitGameController;
import de.htwg.se.battleship.controller.old.event.SetShipSuccess;
import de.htwg.se.battleship.controller.old.event.WrongCoordinate;
import de.htwg.se.battleship.model.old.ModelFabricImpl;
import de.htwg.se.battleship.util._observer.Event;
import de.htwg.se.battleship.util._observer.OLDObserver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class InitGameControllerShipTest {

   private IInitGameController c;
   private String message;
   private boolean pong;

   public class TestClass implements OLDObserver {

      @Override
      public void update(Event e) {
      }

      public void update(WrongCoordinate e) {
         message = e.getMessage();
      }

      public void update(SetShipSuccess e) {
         pong = true;
      }
   }

   @Before
   public void setUp() {
      pong = false;
      message = "";
      c = new OLDInitGameController(ModelFabricImpl.getInstance());
      c.addObserver(new TestClass());
      c.player("test1", "test2");
   }

   @Test
   public void testShipSuccess1() {
      assertFalse(pong);
      c.ship(0, 0, 0, 1);
      assertEquals("", message);
      assertTrue(pong);
   }

   @Test
   public void testShipSuccess2() {
      assertFalse(pong);
      c.ship(0, 0, 1, 0);
      assertEquals("", message);
      assertTrue(pong);
   }

   @Test
   public void testShipSuccess3() {
      assertFalse(pong);
      c.ship(0, 1, 0, 0);
      assertEquals("", message);
      assertTrue(pong);
   }

   @Test
   public void testShipSuccess4() {
      assertFalse(pong);
      c.ship(1, 0, 0, 0);
      assertEquals("", message);
      assertTrue(pong);
   }

   @Test
   public void testCellNotExist1() {
      c.ship(-1, -1, -1, -1);
      assertFalse(pong);
      assertEquals(OLDInitGameController.ERROR_COORDS_GRID, message);
   }

   @Test
   public void testCellNotExist2() {
      c.ship(0, 0, -1, -1);
      assertFalse(pong);
      assertEquals(OLDInitGameController.ERROR_COORDS_GRID, message);
   }

   @Test
   public void testCellRelation() {
      c.ship(0, 0, 1, 1);
      assertFalse(pong);
      assertEquals(OLDInitGameController.ERROR_COORDS, message);
   }

   @Test
   public void testCellSame() {
      c.ship(0, 0, 0, 0);
      assertFalse(pong);
      assertEquals(OLDInitGameController.ERROR_SAME_COORDS, message);
   }

   @Test
   public void testShipMany() {
      c.ship(0, 0, 0, 7);
      c.ship(1, 0, 1, 7);
      c.ship(2, 0, 2, 7);
      c.ship(2, 0, 2, 7);
      assertEquals(String.format(OLDInitGameController.ERROR_TO_MANY, 6, 8), message);
   }

   @Test
   public void testCoordNull1() {
      c.ship(null, 0, 0, 0);
      assertEquals(OLDInitGameController.ERROR_INPUT, message);
   }

   @Test
   public void testCoordNull2() {
      c.ship(0, null, 0, 0);
      assertEquals(OLDInitGameController.ERROR_INPUT, message);
   }

   @Test
   public void testCoordNull3() {
      c.ship(0, 0, null, 0);
      assertEquals(OLDInitGameController.ERROR_INPUT, message);
   }

   @Test
   public void testCoordNull4() {
      c.ship(0, 0, 0, null);
      assertEquals(OLDInitGameController.ERROR_INPUT, message);
   }

}
