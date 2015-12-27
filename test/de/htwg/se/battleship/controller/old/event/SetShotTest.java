package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.OLDRound;
import de.htwg.se.battleship.model.old.impl.OLDRoundImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SetShotTest {

   @Test
   public void test() {
      OLDRound round = new OLDRoundImpl(null, null);
      SetShot setShot = new SetShot(round);
      assertEquals(round, setShot.getRound());
   }
}