package de.htwg.se.battleship.aview.tui;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TextUITest {

    private boolean inputGet;
    private boolean inputClose;
    private boolean showText;
    private boolean executeInput;
    private boolean getUI;
    private final static String text = "test";

    private class TestInput1 implements Input {

        @Override
        public String get() throws IOException {
            inputGet = true;
            return TextUITest.text;
        }

        @Override
        public void close() {
            inputClose = true;
        }
    }

    private class TestInput2 implements Input {

        @Override
        public String get() throws IOException {
            throw new IOException();
        }

        @Override
        public void close() {
            inputClose = true;
        }

    }

    private class TestUi extends UserInterface {

        @Override
        public void showText() {
            showText = true;
        }

        @Override
        public boolean executeInput(String input) {
            executeInput = true;
            return !input.equals(TextUITest.text);
        }

        @Override
        public UserInterface getUI() {
            getUI = true;
            return null;
        }
    }

    @Before
    public void setUp() {
        inputGet     = false;
        inputClose   = false;
        showText     = false;
        executeInput = false;
        getUI        = false;
    }


    @Test
    public void testNormalCall() {
        new TextUI(new TestInput1(), new TestUi());

        assertTrue(inputGet);
        assertTrue(inputClose);
        assertTrue(showText);
        assertTrue(executeInput);
        assertTrue(getUI);
    }

    @Test
    public void testException() {
        new TextUI(new TestInput2(), new TestUi());

        assertFalse(inputGet);
        assertTrue(inputClose);
        assertTrue(showText);
        assertFalse(executeInput);
        assertFalse(getUI);
    }
}
