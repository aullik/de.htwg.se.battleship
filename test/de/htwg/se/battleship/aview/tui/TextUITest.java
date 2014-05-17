package de.htwg.se.battleship.aview.tui;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.junit.Test;

public class TextUITest {

    private boolean inputGet = false;
    private boolean inputClose = false;
    private boolean showText = false;
    private boolean executeInput = false;
    private boolean getUI = false;
    private final static String text = "test";

    private class TestInput implements Input {

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

    @Test
    public void test() {
        new TextUI(new TestInput(), new TestUi());

        assertTrue(inputGet);
        assertTrue(inputClose);
        assertTrue(showText);
        assertTrue(executeInput);
        assertTrue(getUI);
    }
}
