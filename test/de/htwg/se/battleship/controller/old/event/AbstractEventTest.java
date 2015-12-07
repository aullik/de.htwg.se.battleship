package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.OLDGrid;
import de.htwg.se.battleship.model.old.OLDGridImpl;
import de.htwg.se.battleship.model.old.OLDPlayer;
import de.htwg.se.battleship.model.old.OLDPlayerImpl;
import de.htwg.se.battleship.model.old.Round;
import de.htwg.se.battleship.model.old.RoundImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbstractEventTest {

   private class TestClass extends AbstractEvent {

      public TestClass(Round round) {
         super(round);
      }

   }

   @Test
   public void test() {
      OLDPlayer p = new OLDPlayerImpl("test1");
      OLDGrid g = new OLDGridImpl(OLDGridImpl.DEFAULT_SIZE, p);

      Round r = new RoundImpl(g, null);

      AbstractEvent e = new TestClass(r);

      assertEquals(r, e.getRound());
      assertEquals(g, e.getGrid());
      assertEquals(p, e.getPlayer());

      assertEquals(2, e.getCell(2, 5).getX());
      assertEquals(3, e.getCell(4, 3).getY());
   }

}
