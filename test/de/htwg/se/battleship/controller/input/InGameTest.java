package de.htwg.se.battleship.controller.input;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.INTInputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.commands.GameWon;
import de.htwg.se.battleship.util.observer.Event;

public class InGameTest {
    private static final int    SIZE       = 10;
    private static final String PLAYER1    = "Player1";
    private static final String PLAYER2    = "Player2";
    private static final String LINE       = "Test";
    private static final String ERROR_MSG  = "input didnt match: " + LINE;
    private static final String WIN_MSG    = "you won!";

    INTInputState               state      = new InGame(SIZE, PLAYER1, PLAYER2);
    INTCommands                 com        = new GameWon();
    INTInputState               main       = new MainMenu();
    InputController             controller = new InputController();

    @Before
    public void setup() {

    }

    @Test
    public void test() {
        Event[] out = state.processInput(null, LINE);
        assertEquals(ERROR_MSG, out[0].getMessage());
        out = state.processInput(controller, com.getCommand());
        assertEquals(WIN_MSG, out[0].getMessage());
        assertEquals(main.getEvent().getMessage(), out[1].getMessage());

        state.getKeySet();

    }
}
