package de.htwg.se.battleship.controller.input;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.htwg.se.battleship.util.observer.Event;

public class MainMenuTest {

    private static final String OPENCOMMAND = "-mainmenu";
    private static final String LINE        = "Test";
    private static final String ERROR_MSG   = "input didnt match: " + LINE;
    MainMenu                    state       = new MainMenu();

    @Test
    public void test() {
        Event[] out = state.processInput(null, LINE);
        assertEquals(ERROR_MSG, out[0].getMessage());
        assertEquals(OPENCOMMAND, MainMenu.getCommand());

    }

}
