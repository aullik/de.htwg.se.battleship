package de.htwg.se.battleship.controller.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.htwg.se.battleship.util.observer.Event;

public class InitGameTest {

    private static final String LINE          = "Test";
    private static final String ERROR_MSG     = "input didnt match: " + LINE;
    private static final int    OUT_OF_BOUNDS = -1;
    private static final int    PLAYER1       = 1;
    private static final String PLAYER_NAME   = "what ever";
    private static final int    FIELDSIZE     = 10;
    InitGame                    state         = new InitGame();

    @Test
    public void test() {
        Event[] out = state.processInput(null, LINE);
        assertEquals(ERROR_MSG, out[0].getMessage());
        boolean thrown = false;
        try {
            state.getPlayerName(OUT_OF_BOUNDS);
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }
        assertTrue(thrown);
        try {
            state.setPlayerName(OUT_OF_BOUNDS, null);
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }
        assertTrue(thrown);
        state.setPlayerName(PLAYER1, PLAYER_NAME);
        assertEquals(PLAYER_NAME, state.getPlayerName(PLAYER1));
        try {
            state.setFieldSize(OUT_OF_BOUNDS);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
        state.setFieldSize(FIELDSIZE);
        assertEquals(FIELDSIZE, state.getFieldSize());
    }

}
