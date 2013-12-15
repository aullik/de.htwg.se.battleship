package de.htwg.se.battleship.view.tui;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.controller.impl.Controller;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.Observable;

public class MainMenuTest {

    private OutputStream out;

    class TestController extends Observable implements IntController {

        @Override
        public void exit() {
            notifyObservers(new TestEvent());
        }

        @Override
        public void newGame() {
        }

    }

    class TestEvent extends Event {}


    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
    }


    @Test
    public void testProcessInputError() throws UnsupportedEncodingException {
        assertTrue(out.toString().isEmpty());
        String s = "test" + System.lineSeparator() + MainMenu.CLOSE;
        new MainMenu(new Controller(), new ByteArrayInputStream(s.getBytes()), out);
        assertTrue(out.toString().contains(MainMenu.MSG_INPUT_ERROR));
    }

    @Test
    public void testProcessInvalidEntry() throws UnsupportedEncodingException {
        assertTrue(out.toString().isEmpty());
        Integer i = 2;
        String s = i + System.lineSeparator() +  MainMenu.CLOSE;
        new MainMenu(new Controller(), new ByteArrayInputStream(s.getBytes()), out);
        assertTrue(out.toString().contains(String.format(MainMenu.MSG_DEFAULT_MENU, i)));
    }

    @Test
    public void testExit() throws UnsupportedEncodingException {
        assertTrue(out.toString().isEmpty());
        String s =  MainMenu.CLOSE + System.lineSeparator();
        new MainMenu(new Controller(), new ByteArrayInputStream(s.getBytes()), out);
        assertTrue(out.toString().contains(MainMenu.MSG_EXIT));
        assertTrue(out.toString().contains(MainMenu.MSG_INPUT_NOTE));
    }

    @Test
    public void testNewGame() throws UnsupportedEncodingException {
        assertTrue(out.toString().isEmpty());
        String s =  MainMenu.NEW_GAME + System.lineSeparator() + MainMenu.CLOSE;
        new MainMenu(new Controller(), new ByteArrayInputStream(s.getBytes()), out);
        assertFalse(out.toString().isEmpty());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testEvent() throws UnsupportedEncodingException {
        String s =  MainMenu.CLOSE + System.lineSeparator();
        new MainMenu(new TestController(), new ByteArrayInputStream(s.getBytes()), out);
    }


}
