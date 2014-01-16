/**
 * 
 */
package de.htwg.se.battleship.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetShips;
import de.htwg.se.battleship.controller.impl.InitGameController;
import de.htwg.se.battleship.model.impl.ModelFabric;
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

    private IInitGameController c;

    @Before
    public void setUp() {
        c = new InitGameController(new ModelFabric());
        c.addObserver(new TestClass());
    }

    @Test
    public void testInit() {
        assertEquals(0, check);
        c.init();
        assertEquals(2, check);
    }


    @Test
    public void testPlayer() {
        assertEquals(0, check);
        c.player("test1", "test2");
        assertEquals(3, check);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPlayerEmpty() {
        c.player("", "test2");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPlayerNull() {
        c.player("test1", null);
    }

}
