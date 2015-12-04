/**
 *
 */
package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.OLDGrid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.model.Round;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Philipp
 */
public class RoundImplTest {

   @Test
   public void test() {
      Player p = new PlayerImpl("");
      OLDGrid g1 = new OLDGridImpl(OLDGridImpl.DEFAULT_SIZE, p);
      OLDGrid g2 = new OLDGridImpl(OLDGridImpl.DEFAULT_SIZE, p);

      Round r = new RoundImpl(g1, g2);

      assertEquals(g1, r.getGrid());
      assertEquals(g2, r.getOpponentGrid());
      r.next();
      assertEquals(g2, r.getGrid());
      r.next();
      assertEquals(g1, r.getGrid());
   }

}
