package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.model.Round;
import de.htwg.se.battleship.model.impl.GridImpl;
import de.htwg.se.battleship.model.impl.PlayerImpl;
import de.htwg.se.battleship.model.impl.RoundImpl;
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
      Player p = new PlayerImpl("test1");
      Grid g = new GridImpl(GridImpl.DEFAULT_SIZE, p);

      Round r = new RoundImpl(g, null);

      AbstractEvent e = new TestClass(r);

      assertEquals(r, e.getRound());
      assertEquals(g, e.getGrid());
      assertEquals(p, e.getPlayer());

      assertEquals(2, e.getCell(2, 5).getX());
      assertEquals(3, e.getCell(4, 3).getY());
   }

}
