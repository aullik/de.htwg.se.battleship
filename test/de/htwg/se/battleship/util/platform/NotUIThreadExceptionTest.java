package de.htwg.se.battleship.util.platform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author aullik on 29.12.2015.
 */
public class NotUIThreadExceptionTest {


   @Test
   public void testThrowWithMessage() {
      String message = "somethingrandom" + Math.random();

      boolean thrown = false;

      try {
         throwWithMessage(message);
      } catch (NotUIThreadException e) {
         assertEquals(message, e.getMessage());
         thrown = true;
      }
      assertTrue(thrown);
   }

   private void throwWithMessage(String message) throws NotUIThreadException {
      throw new NotUIThreadException(message);
   }


   @Test
   public void testThrowWithoutMessage() {
      boolean thrown = false;

      try {
         throwWithoutMessage();
      } catch (NotUIThreadException e) {
         assertNull(e.getMessage());
         thrown = true;
      }
      assertTrue(thrown);
   }


   private void throwWithoutMessage() throws NotUIThreadException {
      throw new NotUIThreadException();
   }
}

