/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.impl.InitGameController;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class InitGameUITest {

    private boolean ping = false;

    private class TestClass extends Observable implements IInitGameController {

        @Override
        public void init() {}

        @Override
        public void player(String p1, String p2) {
            ping = true;
        }

        @Override
        public void ship(int startX, int startY, int endX, int endY) {}
    }

    private class TestFactory implements IScannerFactory {

        private final Scanner scanner;

        public TestFactory(StringBuilder sb) throws UnsupportedEncodingException {
            this.scanner = new Scanner(new ByteArrayInputStream(sb.toString().getBytes("UTF-8")));
        }

        @Override
        public Scanner getScanner() {
            return scanner;
        }
    }

    private TestAppender testAppender;

    @Before
    public void setUp() {
        testAppender = new TestAppender();
        Logger.getRootLogger().removeAllAppenders();
        Logger.getRootLogger().addAppender(testAppender);
    }

    private void uiPlayer(StringBuilder sb) throws UnsupportedEncodingException {
        IScannerFactory f = new TestFactory(sb);
        InitGameUI ui = new InitGameUI(new TestClass(), f);
        ui.update(new SetPlayer());
    }

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
        uiPlayer(sb);
        assertTrue(ping);

        String log = testAppender.getLog();

        assertTrue(log.contains(String.format(InitGameUI.MSG_INPUT_NOTE, InitGameController.P1, 1)));
        assertTrue(log.contains(String.format(InitGameUI.MSG_INPUT_NOTE, InitGameController.P2, 2)));

        assertTrue(log.contains(String.format(InitGameUI.MSG_NAME_NOTE, InitGameController.P1, p1)));
        assertTrue(log.contains(String.format(InitGameUI.MSG_NAME_NOTE, InitGameController.P2, p2)));
    }


    @Test
    public void testPlayerEmptyInput() throws UnsupportedEncodingException {

        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));

        assertFalse(ping);
        uiPlayer(sb);
        assertTrue(ping);

        String log = testAppender.getLog();

        assertTrue(log.contains(String.format(InitGameUI.MSG_INPUT_NOTE, InitGameController.P1, 1)));
        assertTrue(log.contains(String.format(InitGameUI.MSG_INPUT_NOTE, InitGameController.P2, 2)));

        assertTrue(log.contains(String.format(InitGameUI.MSG_NAME_NOTE, InitGameController.P1, String.format(InitGameUI.DEFAULT_NAME, 1))));
        assertTrue(log.contains(String.format(InitGameUI.MSG_NAME_NOTE, InitGameController.P2, String.format(InitGameUI.DEFAULT_NAME, 2))));
    }

}
