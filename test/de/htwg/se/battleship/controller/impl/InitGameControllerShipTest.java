/**
 * 
 */
package de.htwg.se.battleship.controller.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.event.SetShipSuccess;
import de.htwg.se.battleship.controller.event.WrongCoordinate;
import de.htwg.se.battleship.controller.impl.IInitGameControllerTest.TestClass;
import de.htwg.se.battleship.model.impl.ModelFabric;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

/**
 * @author Philipp
 *
 */
public class InitGameControllerShipTest {

    private IInitGameController c;
    private String message;
    private boolean pong;

    public class TestClass implements IObserver {

        @Override
        public void update(Event e) {System.out.println(e.getClass());}

        public void update(WrongCoordinate e) {
            message = e.getMessage();
        }

        public void update(SetShipSuccess e) {
            pong = true;
        }
    }

    @Before
    public void setUp() {
        pong = false;
        message = "";
        c = new InitGameController(new ModelFabric());
        c.addObserver(new TestClass());
        c.player("test1", "test2");
    }

    @Test
    public void testShipSuccess() {
        assertFalse(pong);
        c.ship(0, 0, 0, 1);
        assertEquals("", message);
        assertTrue(pong);
    }

    @Test
    public void testCellNotExist1() {
        c.ship(-1, -1, -1, -1);
        assertFalse(pong);
        assertEquals(InitGameController.ERROR_COORDS_GRID, message);
    }

    @Test
    public void testCellNotExist2() {
        c.ship(0, 0, -1, -1);
        assertFalse(pong);
        assertEquals(InitGameController.ERROR_COORDS_GRID, message);
    }

    @Test
    public void testCellRelation() {
        c.ship(0, 0, 1, 1);
        assertFalse(pong);
        assertEquals(InitGameController.ERROR_COORDS, message);
    }

    @Test
    public void testCellSame() {
        c.ship(0, 0, 0, 0);
        assertFalse(pong);
        assertEquals(InitGameController.ERROR_SAME_COORDS, message);
    }

}
