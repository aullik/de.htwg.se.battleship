package de.htwg.se.battleship.aview.tui;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.TestAppender;
import de.htwg.se.battleship.aview.tui.menu.MainMenu;
import de.htwg.se.battleship.aview.tui.menuentry.Close;
import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.InitGame;
import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.event.SetShip;
import de.htwg.se.battleship.controller.event.SetShipSuccess;
import de.htwg.se.battleship.controller.impl.Controller;
import de.htwg.se.battleship.util.observer.impl.ObservableImpl;


public class TextUITest {

    private TestAppender testAppender;

    private class TestClass implements IScannerFactory {

        private final Scanner scanner;

        public TestClass(StringBuilder sb) throws UnsupportedEncodingException {
            scanner = new Scanner(new ByteArrayInputStream(sb.toString().getBytes("UTF-8")));
        }

        @Override
        public Scanner getScanner() {
            return scanner;
        }
    }

    private boolean ping;

    private class TestController extends ObservableImpl implements IInitGameController {

        @Override
        public void init() {
            ping = true;
        }

        @Override
        public void player(String p1, String p2) {}

        @Override
        public void ship(Integer startX, Integer startY, Integer endX, Integer endY) {}

        @Override
        public void shot(Integer x, Integer y) {
            // TODO Auto-generated method stub

        }

    }

    private class TestUi implements IInitGameUI {
        @Override
        public void update(SetPlayer e) {}

        @Override
        public void update(SetShip e) {}

        @Override
        public void update(SetPlayerSuccess e) {}

        @Override
        public void update(SetShipSuccess e) {}
    }

    @Before
    public void setUp() {
        ping = false;

        testAppender = new TestAppender();
        Logger.getRootLogger().removeAllAppenders();
        Logger.getRootLogger().addAppender(testAppender);
    }

    /*
    @Test
    public void testOnlyExit() throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        sb.append(Close.CMD);
        sb.append(System.getProperty("line.separator"));

        IScannerFactory f = new TestClass(sb);
        IController c = new Controller();
        TextUI t = new TextUI(c, f, new MainMenu(c), new TestController(), new TestUi());

        String log = testAppender.getLog().toString();
        assertTrue(log.contains(TextUI.MSG_EXIT));

        assertFalse(ping);
        t.update(new InitGame());
        assertTrue(ping);
    }

    @Test
    public void testMenuEntryNotExist() throws UnsupportedEncodingException {
        String s = "test";

        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(System.getProperty("line.separator"));
        sb.append(Close.CMD);
        sb.append(System.getProperty("line.separator"));

        IScannerFactory f = new TestClass(sb);
        IController c = new Controller();
        new TextUI(c, f, new MainMenu(c), null, new TestUi());

        String log = testAppender.getLog().toString();
        assertTrue(log.contains(String.format(TextUI.MSG_DEFAULT_MENU, s)));
        assertTrue(log.contains(TextUI.MSG_EXIT));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testException() throws UnsupportedEncodingException  {
        StringBuilder sb = new StringBuilder();
        sb.append(Close.CMD);
        sb.append(System.getProperty("line.separator"));

        IScannerFactory f = new TestClass(sb);
        IController c = new Controller();
        new TextUI(c, f, new MainMenu(c), new TestController(), null);
    }
     */
}
