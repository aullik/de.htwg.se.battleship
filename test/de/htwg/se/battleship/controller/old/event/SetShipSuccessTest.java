/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.OLDShip;
import de.htwg.se.battleship.model.old.impl.OLDPlayerImpl;
import de.htwg.se.battleship.model.old.impl.OLDShipImpl;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author Philipp
 */
public class SetShipSuccessTest {

   @Test
   public void test() {
      OLDShip s = new OLDShipImpl(new OLDPlayerImpl(""), new HashMap<String, OLDCell>());
      SetShipSuccess e = new SetShipSuccess(null, s);
      assertEquals(s, e.getShip());
   }

}
