/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * @author Philipp
 *
 */
public class InitGameTest {

    private class TestClass extends Observable implements IInitGameController {

        @Override
        public void init() { }

        @Override
        public void player(String p1, String p2) {}
    }

    @Test
    public void test() {
        IInitGameController c1 = new TestClass();
        IInitGameController c2 = new TestClass();

        assertNotEquals(c1, c2);

        InitGame e1 = new InitGame(c1);
        InitGame e2 = new InitGame(c2);

        assertEquals(c1, e1.getController());
        assertEquals(c2, e2.getController());
    }

}
