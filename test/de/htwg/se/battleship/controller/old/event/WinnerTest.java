package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.Round;
import de.htwg.se.battleship.model.impl.RoundImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WinnerTest {

   @Test
   public void test() {
      Round round = new RoundImpl(null, null);
      Winner winner = new Winner(round);
      assertEquals(round, winner.getRound());
   }
}