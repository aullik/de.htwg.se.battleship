/**
 * 
 */
package de.htwg.se.battleship.controller.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.event.SetShip;
import de.htwg.se.battleship.controller.impl.InitGameController;
import de.htwg.se.battleship.model.impl.ModelFabricImpl;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class IInitGameControllerTest {

    private boolean setPlayer;
    private boolean setShip;
    private boolean setPlayerSuccess;

    public class TestClass implements IObserver {

        @Override
        public void update(Event e) {}

        public void update(SetPlayer e) {
            setPlayer = true;
        }

        public void update(SetPlayerSuccess e) {
            setPlayerSuccess = true;
        }

        public void update(SetShip e) {
            setShip = true;
        }

    }

    private IInitGameController c;

    @Before
    public void setUp() {
        setPlayer = false;
        setShip = false;
        setPlayerSuccess = false;
        c = new InitGameController(new ModelFabricImpl());
        c.addObserver(new TestClass());
    }

    @Test
    public void testInit() {
        assertFalse(setPlayer);
        assertFalse(setPlayerSuccess);
        assertFalse(setShip);
        c.init();
        assertTrue(setPlayer);
        assertFalse(setPlayerSuccess);
        assertFalse(setShip);
    }


    @Test
    public void testPlayer() {
        assertFalse(setPlayer);
        assertFalse(setPlayerSuccess);
        assertFalse(setShip);
        c.player("test1", "test2");
        assertFalse(setPlayer);
        assertTrue(setPlayerSuccess);
        assertTrue(setShip);
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
