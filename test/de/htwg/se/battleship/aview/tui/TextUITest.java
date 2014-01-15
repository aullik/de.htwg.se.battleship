package de.htwg.se.battleship.aview.tui;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.aview.tui.menu.MainMenu;
import de.htwg.se.battleship.aview.tui.menuentry.Close;
import de.htwg.se.battleship.controller.Controller;
import de.htwg.se.battleship.controller.IController;


public class TextUITest {

    private TestAppender testAppender;

    @Before
    public void setUp() {
        testAppender = new TestAppender();
        Logger.getRootLogger().removeAllAppenders();
        Logger.getRootLogger().addAppender(testAppender);
    }

    @Test
    public void testOnlyExit() throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        sb.append(Close.CMD);
        sb.append(System.getProperty("line.separator"));

        Scanner scanner = new Scanner(new ByteArrayInputStream(sb.toString().getBytes("UTF-8")));
        IController c = new Controller();
        new TextUI(c, scanner, new MainMenu(c).get());

        String log = testAppender.getLog().toString();
        assertTrue(log.contains(TextUI.MSG_EXIT));
    }

    @Test
    public void testMenuEntryNotExist() throws UnsupportedEncodingException {
        String s = "test";

        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(System.getProperty("line.separator"));
        sb.append(Close.CMD);
        sb.append(System.getProperty("line.separator"));

        Scanner scanner = new Scanner(new ByteArrayInputStream(sb.toString().getBytes("UTF-8")));
        IController c = new Controller();
        new TextUI(c, scanner, new MainMenu(c).get());

        String log = testAppender.getLog().toString();
        assertTrue(log.contains(String.format(TextUI.MSG_DEFAULT_MENU, s)));
        assertTrue(log.contains(TextUI.MSG_EXIT));
    }
}
