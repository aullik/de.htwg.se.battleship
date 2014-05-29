package de.htwg.se.battleship.controller.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;
import de.htwg.se.battleship.controller.event.Winner;
import de.htwg.se.battleship.controller.impl.Controller;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.Observer;

/**
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public class ControllerTest {

    private Controller c;

    private boolean initGameEvent;
    private boolean closeProgrammEvent;
    private boolean winnerEvent;

    public class TestClass implements Observer {

        @Override
        public void update(Event e) {
        }

        public void update(InitGame e) {
            initGameEvent = true;
        }

        public void update(CloseProgamm e) {
            closeProgrammEvent = true;
        }

        public void update(Winner e) {
            winnerEvent = true;
        }
    }

    @Before
    public void setUp() {
        c = new Controller();
        c.addObserver(new TestClass());

        initGameEvent = false;
        closeProgrammEvent = false;
        winnerEvent = false;
    }

    @Test
    public void testNewGame() {
        c.newGame();

        assertTrue(initGameEvent);
        assertFalse(closeProgrammEvent);
        assertFalse(winnerEvent);
    }

    @Test
    public void testClose() {
        c.close();

        assertFalse(initGameEvent);
        assertTrue(closeProgrammEvent);
        assertFalse(winnerEvent);
    }

    @Test
    public void testReset() {
        c.reset();

        assertFalse(initGameEvent);
        assertFalse(closeProgrammEvent);
        assertTrue(winnerEvent);
    }
}