package de.htwg.se.battleship.util.platform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author aullik on 29.12.2015.
 */
public class AlreadyExecutedExceptionTest {

   @Test
   public void testThrowWithMessage() {
      String message = "somethingrandom" + Math.random();

      boolean thrown = false;

      try {
         throwWithMessage(message);
      } catch (AlreadyExecutedException e) {
         assertEquals(message, e.getMessage());
         thrown = true;
      }
      assertTrue(thrown);
   }

   private void throwWithMessage(String message) throws AlreadyExecutedException {
      throw new AlreadyExecutedException(message);
   }


   @Test
   public void testThrowWithoutMessage() {
      boolean thrown = false;

      try {
         throwWithoutMessage();
      } catch (AlreadyExecutedException e) {
         assertNull(e.getMessage());
         thrown = true;
      }
      assertTrue(thrown);
   }


   private void throwWithoutMessage() throws AlreadyExecutedException {
      throw new AlreadyExecutedException();
   }
}