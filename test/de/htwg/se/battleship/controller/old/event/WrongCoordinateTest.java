/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class WrongCoordinateTest {

   @Test
   public void test() {
      String s = "test";
      WrongCoordinate e = new WrongCoordinate(null, s);
      assertEquals(s, e.getMessage());
   }

}
