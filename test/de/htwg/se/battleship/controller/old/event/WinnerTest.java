package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.OLDRound;
import de.htwg.se.battleship.model.old.impl.OLDRoundImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WinnerTest {

   @Test
   public void test() {
      OLDRound round = new OLDRoundImpl(null, null);
      Winner winner = new Winner(round);
      assertEquals(round, winner.getRound());
   }
}