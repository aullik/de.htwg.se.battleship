/**
 * 
 */
package de.htwg.se.battleship.aview.tui.menuentry;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class NewGameTest {

    private boolean ping = false;

    private class TestClass extends Observable implements IController {

        @Override
        public void newGame() {
            ping = true;
        }

        @Override
        public void close() {}

        @Override
        public void reset() {
            // TODO Auto-generated method stub

        }
    }

    @Test
    public void test() {
        assertFalse(ping);
        NewGame e = new NewGame(new TestClass());
        e.action();
        assertTrue(ping);

        assertEquals(NewGame.CMD, e.command());
        assertEquals(NewGame.DESC, e.description());

        assertNotEquals(e.command(), "");
        assertNotEquals(e.description(), "");
    }

}
