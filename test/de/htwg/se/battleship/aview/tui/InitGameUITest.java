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

    }

    private TestAppender testAppender;

    @Before
    public void setUp() {
        testAppender = new TestAppender();
        Logger.getRootLogger().removeAllAppenders();
        Logger.getRootLogger().addAppender(testAppender);
    }

    private void ui(StringBuilder sb) throws UnsupportedEncodingException {
        Scanner scanner = new Scanner(new ByteArrayInputStream(sb.toString().getBytes("UTF-8")));
        InitGameUI ui = new InitGameUI(new TestClass(), scanner);
        ui.update(new SetPlayer());
    }

    @Test
    public void test() throws UnsupportedEncodingException {

        String p1 = "1337";
        String p2 = "test player 2";

        StringBuilder sb = new StringBuilder();
        sb.append(p1);
        sb.append(System.getProperty("line.separator"));
        sb.append(p2);
        sb.append(System.getProperty("line.separator"));

        assertFalse(ping);
        ui(sb);
        assertTrue(ping);

        String log = testAppender.getLog();

        assertTrue(log.contains(String.format(InitGameUI.MSG_INPUT_NOTE, InitGameController.P1)));
        assertTrue(log.contains(String.format(InitGameUI.MSG_INPUT_NOTE, InitGameController.P2)));

        assertTrue(log.contains(String.format(InitGameUI.MSG_NAME_NOTE, InitGameController.P1, p1)));
        assertTrue(log.contains(String.format(InitGameUI.MSG_NAME_NOTE, InitGameController.P2, p2)));
        assertFalse(log.contains(InitGameUI.MSG_INPUT_EMPTY));
    }


    @Test
    public void testEmptyInput() throws UnsupportedEncodingException {

        String p1 = "nikolas";
        String p2 = "philipp";

        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append(p1);
        sb.append(System.getProperty("line.separator"));
        sb.append(p2);
        sb.append(System.getProperty("line.separator"));

        assertFalse(ping);
        ui(sb);
        assertTrue(ping);

        String log = testAppender.getLog();

        assertTrue(log.contains(String.format(InitGameUI.MSG_INPUT_NOTE, InitGameController.P1)));
        assertTrue(log.contains(String.format(InitGameUI.MSG_INPUT_NOTE, InitGameController.P2)));

        assertTrue(log.contains(String.format(InitGameUI.MSG_NAME_NOTE, InitGameController.P1, p1)));
        assertTrue(log.contains(String.format(InitGameUI.MSG_NAME_NOTE, InitGameController.P2, p2)));
        assertTrue(log.contains(InitGameUI.MSG_INPUT_EMPTY));
    }

}
