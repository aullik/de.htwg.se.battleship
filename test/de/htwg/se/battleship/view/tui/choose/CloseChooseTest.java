package de.htwg.se.battleship.view.tui.choose;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.util.observer.Observable;

public class CloseChooseTest {

    private boolean ping = false;

    class TestController extends Observable implements IntController {

        @Override
        public void exit() {
            ping = true;
        }

        @Override
        public void newGame() {}

    }

    @Test
    public void test() {
        assertFalse(ping);
        new CloseChoose(new TestController()).action();
        assertTrue(ping);
    }

}