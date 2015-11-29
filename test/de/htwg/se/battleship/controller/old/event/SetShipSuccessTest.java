/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.Ship;
import de.htwg.se.battleship.model.impl.PlayerImpl;
import de.htwg.se.battleship.model.impl.ShipImpl;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author Philipp
 */
public class SetShipSuccessTest {

   @Test
   public void test() {
      Ship s = new ShipImpl(new PlayerImpl(""), new HashMap<String, Cell>());
      SetShipSuccess e = new SetShipSuccess(null, s);
      assertEquals(s, e.getShip());
   }

}
