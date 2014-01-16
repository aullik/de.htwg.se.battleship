package de.htwg.se.battleship.controller.event;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.model.IRound;
import de.htwg.se.battleship.model.impl.Grid;
import de.htwg.se.battleship.model.impl.Player;
import de.htwg.se.battleship.model.impl.Round;

public class AbstractEventTest {

    private class TestClass extends AbstractEvent {

        public TestClass(IRound round) {
            super(round);
        }

    }

    @Test
    public void test() {
        IPlayer p = new Player("test1");
        IGrid g = new Grid(Grid.DEFAULT_SIZE, p);

        IRound r = new Round(g, null);

        AbstractEvent e = new TestClass(r);

        assertEquals(r, e.getRound());
        assertEquals(g, e.getGrid());
        assertEquals(p, e.getPlayer());

        assertEquals(2, e.getCell(2, 5).getX());
        assertEquals(3, e.getCell(4, 3).getY());
    }

}
