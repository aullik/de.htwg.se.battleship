/**
 * 
 */
package de.htwg.se.battleship.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetShips;
import de.htwg.se.battleship.controller.impl.InitGameController;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class IInitGameControllerTest {

    private int check = 0;

    public class TestClass implements IObserver {

        @Override
        public void update(Event e) {
            check = 1;
        }

        public void update(SetPlayer e) {
            check = 2;
        }

        public void update(SetShips e) {
            check = 3;
        }

    }

    @Test
    public void testInit() {
        IInitGameController c = new InitGameController();
        c.addObserver(new TestClass());

        assertEquals(0, check);
        c.init();
        assertEquals(2, check);
    }


    @Test
    public void testPlayer() {
        IInitGameController c = new InitGameController();
        c.addObserver(new TestClass());

        assertEquals(0, check);
        c.player("test1", "test2");
        assertEquals(3, check);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPlayerEmpty() {
        IInitGameController c = new InitGameController();
        c.addObserver(new TestClass());
        c.player("", "test2");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPlayerNull() {
        IInitGameController c = new InitGameController();
        c.addObserver(new TestClass());
        c.player(null, "test2");
    }

}
