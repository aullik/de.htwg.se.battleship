/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.Ship;
import de.htwg.se.battleship.model.old.impl.OLDPlayerImpl;
import de.htwg.se.battleship.model.old.impl.ShipImpl;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author Philipp
 */
public class SetShipSuccessTest {

   @Test
   public void test() {
      Ship s = new ShipImpl(new OLDPlayerImpl(""), new HashMap<String, OLDCell>());
      SetShipSuccess e = new SetShipSuccess(null, s);
      assertEquals(s, e.getShip());
   }

}
