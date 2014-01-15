package de.htwg.se.battleship.controller;

import static org.junit.Assert.*;


import org.junit.Test;

import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

public class ControllerTest {

    private boolean ping = false;
    private boolean pong = false;

    public class TestClass implements IObserver {

        @Override
        public void update(Event e) {
        }

        public void update(InitGame e) {
            ping = true;
        }

        public void update(CloseProgamm e) {
            pong = true;
        }

    }

    @Test
    public void testNewGame() {
        IController c = new Controller();
        c.addObserver(new TestClass());

        assertFalse(ping);
        c.newGame();
        assertTrue(ping);
    }

    @Test
    public void testClose() {
        IController c = new Controller();
        c.addObserver(new TestClass());

        assertFalse(pong);
        c.close();
        assertTrue(pong);
    }

}
