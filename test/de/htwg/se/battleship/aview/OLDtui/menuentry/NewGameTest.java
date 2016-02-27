/**
 *
 */
package de.htwg.se.battleship.aview.OLDtui.menuentry;

import de.htwg.se.battleship.controller.old.IOLDController;
import de.htwg.se.battleship.util._observer.impl.ObservableImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class NewGameTest {

   private boolean ping = false;

   private class TestClass extends ObservableImpl implements IOLDController {

      @Override
      public void newGame() {
         ping = true;
      }

      @Override
      public void close() {
      }

      @Override
      public void reset() {
         // TODO Auto-generated method stub

      }
   }

   @Test
   public void test() {
      assertFalse(ping);
      NewGame e = new NewGame(new TestClass());
      e.action();
      assertTrue(ping);

      assertEquals(NewGame.CMD, e.command());
      assertEquals(NewGame.DESC, e.description());

      assertNotEquals(e.command(), "");
      assertNotEquals(e.description(), "");
   }

}
