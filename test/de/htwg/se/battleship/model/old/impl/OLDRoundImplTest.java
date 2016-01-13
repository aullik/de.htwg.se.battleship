/**
 *
 */
package de.htwg.se.battleship.model.old.impl;

import de.htwg.se.battleship.model.old.OLDGrid;
import de.htwg.se.battleship.model.old.OLDPlayer;
import de.htwg.se.battleship.model.old.OLDRound;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Philipp
 */
public class OLDRoundImplTest {

   @Test
   public void test() {
      OLDPlayer p = new OLDPlayerImpl("");
      OLDGrid g1 = new OLDGridImpl(OLDGridImpl.DEFAULT_SIZE, p);
      OLDGrid g2 = new OLDGridImpl(OLDGridImpl.DEFAULT_SIZE, p);

      OLDRound r = new OLDRoundImpl(g1, g2);

      assertEquals(g1, r.getGrid());
      assertEquals(g2, r.getOpponentGrid());
      r.next();
      assertEquals(g2, r.getGrid());
      r.next();
      assertEquals(g1, r.getGrid());
   }

}
