package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.Round;
import de.htwg.se.battleship.model.old.impl.RoundImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SetShotTest {

   @Test
   public void test() {
      Round round = new RoundImpl(null, null);
      SetShot setShot = new SetShot(round);
      assertEquals(round, setShot.getRound());
   }
}