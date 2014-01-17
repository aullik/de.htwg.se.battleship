/**
 * 
 */
package de.htwg.se.battleship.aview.tui.menuentry;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * @author Philipp
 *
 */
public class CloseTest {

    private boolean ping = false;

    private class TestClass extends Observable implements IController {

        @Override
        public void newGame() {}

        @Override
        public void close() {
            ping = true;
        }

        @Override
        public void reset() {
            // TODO Auto-generated method stub

        }
    }

    @Test
    public void test() {
        assertFalse(ping);
        Close c = new Close(new TestClass());
        c.action();
        assertTrue(ping);

        assertEquals(Close.CMD, c.command());
        assertEquals(Close.DESC, c.description());

        assertNotEquals(c.command(), "");
        assertNotEquals(c.description(), "");
    }

}
