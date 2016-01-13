/**
 *
 */
package de.htwg.se.battleship.controller.old.impl;

import de.htwg.se.battleship.controller.old.IInitGameController;
import de.htwg.se.battleship.controller.old.event.SetPlayer;
import de.htwg.se.battleship.controller.old.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.old.event.SetShip;
import de.htwg.se.battleship.model.old.impl.ModelFabricImpl;
import de.htwg.se.battleship.util._observer.Event;
import de.htwg.se.battleship.util._observer.OLDObserver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class IInitGameControllerTest {

   private boolean setPlayer;
   private boolean setShip;
   private boolean setPlayerSuccess;

   public class TestClass implements OLDObserver {

      @Override
      public void update(Event e) {
      }

      public void update(SetPlayer e) {
         setPlayer = true;
      }

      public void update(SetPlayerSuccess e) {
         setPlayerSuccess = true;
      }

      public void update(SetShip e) {
         setShip = true;
      }

   }

   private IInitGameController c;

   @Before
   public void setUp() {
      setPlayer = false;
      setShip = false;
      setPlayerSuccess = false;
      c = new OLDInitGameController(ModelFabricImpl.getInstance());
      c.addObserver(new TestClass());
   }

   @Test
   public void testInit() {
      assertFalse(setPlayer);
      assertFalse(setPlayerSuccess);
      assertFalse(setShip);
      c.init();
      assertTrue(setPlayer);
      assertFalse(setPlayerSuccess);
      assertFalse(setShip);
   }


   @Test
   public void testPlayer() {
      assertFalse(setPlayer);
      assertFalse(setPlayerSuccess);
      assertFalse(setShip);
      c.player("test1", "test2");
      assertFalse(setPlayer);
      assertTrue(setPlayerSuccess);
      assertTrue(setShip);
   }

   @Test (expected = IllegalArgumentException.class)
   public void testPlayerEmpty() {
      c.player("", "test2");
   }

   @Test (expected = IllegalArgumentException.class)
   public void testPlayerNull() {
      c.player("test1", null);
   }

}
