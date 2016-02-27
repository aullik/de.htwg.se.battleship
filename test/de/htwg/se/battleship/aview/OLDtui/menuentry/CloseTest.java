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
 * @author Philipp
 */
public class CloseTest {

   private boolean ping = false;

   private class TestClass extends ObservableImpl implements IOLDController {

      @Override
      public void newGame() {
      }

      @Override
      public void close() {
         ping = true;
      }

      @Override
      public void reset() {
         // TODO Auto-generated method stub

      }
   }

   @Test
   public void test() {
      assertFalse(ping);
      Close c = new Close(new TestClass());
      c.action();
      assertTrue(ping);

      assertEquals(Close.CMD, c.command());
      assertEquals(Close.DESC, c.description());

      assertNotEquals(c.command(), "");
      assertNotEquals(c.description(), "");
   }

}
