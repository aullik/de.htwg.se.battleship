/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.htwg.se.battleship.TestAppender;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.event.SetShip;
import de.htwg.se.battleship.controller.event.SetShipSuccess;
import de.htwg.se.battleship.controller.impl.InitGameController;
import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.model.Round;
import de.htwg.se.battleship.model.impl.GridImpl;
import de.htwg.se.battleship.model.impl.PlayerImpl;
import de.htwg.se.battleship.model.impl.RoundImpl;
import de.htwg.se.battleship.model.impl.ShipImpl;
import de.htwg.se.battleship.util.observer.impl.ObservableImpl;

/**
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public class InitGameUITest {

    private InitGameUI ui;

    private boolean ping;
    private String shipCoords;

    private class TestClass extends ObservableImpl implements IInitGameController {

        @Override
        public void init() {}

        @Override
        public void player(String p1, String p2) {
            ping = true;
        }

        @Override
        public void ship(Integer startX, Integer startY, Integer endX, Integer endY) {
            shipCoords = startX + "," + startY + "," + endX + "," + endY;
        }

        @Override
        public void shot(Integer x, Integer y) {
            // TODO Auto-generated method stub

        }
    }

    private TestAppender testAppender;

    @Before
    public void setUp() {
        ping = false;
        shipCoords = "";

        ui = new InitGameUI(new TestClass());

        testAppender = new TestAppender();
        Logger.getRootLogger().removeAllAppenders();
        Logger.getRootLogger().addAppender(testAppender);
    }

    @Test
    public void testUpdateSetPlayer() {
        assertTrue(testAppender.getLog().isEmpty());
        ui.update(new SetPlayer());
        assertTrue(testAppender.getLog().isEmpty());
    }

    @Ignore
    @Test
    public void testPlayer() throws UnsupportedEncodingException {

        String p1 = "1337";
        String p2 = "test player 2";

        StringBuilder sb = new StringBuilder();
        sb.append(p1);
        sb.append(System.getProperty("line.separator"));
        sb.append(p2);
        sb.append(System.getProperty("line.separator"));

        assertFalse(ping);
        assertTrue(ping);

        String log = testAppender.getLog();

        assertTrue(log.contains(String.format(InitGameUI.MSG_INPUT_NOTE, InitGameController.P1, 1)));
        assertTrue(log.contains(String.format(InitGameUI.MSG_INPUT_NOTE, InitGameController.P2, 2)));

        assertTrue(log.contains(String.format(InitGameUI.MSG_NAME_NOTE, InitGameController.P1, p1)));
        assertTrue(log.contains(String.format(InitGameUI.MSG_NAME_NOTE, InitGameController.P2, p2)));
    }


    @Ignore
    @Test
    public void testPlayerEmptyInput() throws UnsupportedEncodingException {

        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));

        assertFalse(ping);
        assertTrue(ping);

        String log = testAppender.getLog();

        assertTrue(log.contains(String.format(InitGameUI.MSG_INPUT_NOTE, InitGameController.P1, 1)));
        assertTrue(log.contains(String.format(InitGameUI.MSG_INPUT_NOTE, InitGameController.P2, 2)));

        assertTrue(log.contains(String.format(InitGameUI.MSG_NAME_NOTE, InitGameController.P1, String.format(InitGameUI.DEFAULT_NAME, 1))));
        assertTrue(log.contains(String.format(InitGameUI.MSG_NAME_NOTE, InitGameController.P2, String.format(InitGameUI.DEFAULT_NAME, 2))));
    }

    @Ignore
    @Test
    public void testPlayerSuccess() throws UnsupportedEncodingException {

        Player p1 = new PlayerImpl("test1");
        Grid g1 = new GridImpl(GridImpl.DEFAULT_SIZE, p1);
        Player p2 = new PlayerImpl("test2");
        Grid g2 = new GridImpl(GridImpl.DEFAULT_SIZE, p2);

        Round r = new RoundImpl(g1, g2);

        SetPlayerSuccess e = new SetPlayerSuccess(r);
        InitGameUI ui = new InitGameUI(new TestClass());
        ui.update(e);

        String log = testAppender.getLog();
        assertTrue(log.contains(String.format(InitGameUI.MSG_PLAYER_ADD, p1.getName(), p2.getName())));
    }


    @Ignore
    @Test
    public void testSetShip() throws UnsupportedEncodingException {
        Player p1 = new PlayerImpl("test1");
        Grid g1 = new GridImpl(GridImpl.DEFAULT_SIZE, p1);
        Player p2 = new PlayerImpl("test2");
        Grid g2 = new GridImpl(GridImpl.DEFAULT_SIZE, p2);
        Round r = new RoundImpl(g1, g2);


        SetShip e = new SetShip(r);
        StringBuilder sb = new StringBuilder();
        sb.append(1 + System.getProperty("line.separator"));
        sb.append(2 + System.getProperty("line.separator"));
        sb.append(3 + System.getProperty("line.separator"));
        sb.append(4 + System.getProperty("line.separator"));
        sb.append(5 + System.getProperty("line.separator"));
        sb.append(6 + System.getProperty("line.separator"));
        sb.append(7 + System.getProperty("line.separator"));
        sb.append(8 + System.getProperty("line.separator"));
        InitGameUI ui = new InitGameUI(new TestClass());

        ui.update(e);
        assertEquals("1,2,3,4", shipCoords);

        ui.update(e);
        assertEquals("5,6,7,8", shipCoords);
    }

    @Ignore
    @Test
    public void testSetShipNoInt() throws UnsupportedEncodingException {
        Player p1 = new PlayerImpl("test1");
        Grid g1 = new GridImpl(GridImpl.DEFAULT_SIZE, p1);
        Player p2 = new PlayerImpl("test2");
        Grid g2 = new GridImpl(GridImpl.DEFAULT_SIZE, p2);
        Round r = new RoundImpl(g1, g2);

        SetShip e = new SetShip(r);
        StringBuilder sb = new StringBuilder();
        sb.append("test" + System.getProperty("line.separator"));
        sb.append(2 + System.getProperty("line.separator"));
        sb.append(3 + System.getProperty("line.separator"));
        sb.append(4 + System.getProperty("line.separator"));
        InitGameUI ui = new InitGameUI(new TestClass());

        ui.update(e);
        assertEquals("null,2,3,4", shipCoords);
    }

    @Ignore
    @Test
    public void testShipSuccess() throws UnsupportedEncodingException {
        Player p1 = new PlayerImpl("test1");
        Grid g1 = new GridImpl(GridImpl.DEFAULT_SIZE, p1);
        Player p2 = new PlayerImpl("test2");
        Grid g2 = new GridImpl(GridImpl.DEFAULT_SIZE, p2);
        Round r = new RoundImpl(g1, g2);

        Map<String, Cell> map = new HashMap<String, Cell>();
        Cell c = g1.getCell(0, 0);
        map.put(c.getKey(), c);
        SetShipSuccess e = new SetShipSuccess(r, new ShipImpl(p1, map));
        InitGameUI ui = new InitGameUI(new TestClass());
        ui.update(e);

        String log = testAppender.getLog();
        assertTrue(log.contains(String.format(InitGameUI.MSG_SHIP_SUCCESS, "")));
    }
}