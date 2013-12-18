package de.htwg.se.battleship.controller.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.INTInputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.input.InGame;
import de.htwg.se.battleship.controller.input.InitGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;

public class StartGameTest {
    private static final String DESCRIPTION = "starts the game";
    private static final String SOMETHING   = "sdfasdflScjDpoDneEdrsdGSternkdv";
    private static final int    PLAYER1     = 1;
    private static final int    PLAYER2     = 2;
    private INTCommands         command;
    private InitGame            init;
    private InputController     controller;
    private Event[]             out;
    private INTInputState       state;

    @Before
    public void setup() {
        controller = new InputController();
        init = new InitGame();
        command = new StartGame(init);
        state = new InGame(init.getFieldSize(), init.getPlayerName(PLAYER1),
                init.getPlayerName(PLAYER2));

    }

    @Test
    public void getCommandTest() {
        assertEquals(InGame.getCommand(), command.getCommand());

    }

    @Test
    public void getDescriptionTest() {
        assertEquals(DESCRIPTION, command.getDescription());

    }

    @Test
    public void actionTest() {
        out = command.action(new String[] { "", "", "" }, init, controller);
        assertEquals(state.getEvent().getMessage(), out[0].getMessage());
        assertEquals(state.getEvent().getClass(), out[0].getClass());
        assertNull(out[1]);
        out = command.action(new String[] { "", "", SOMETHING }, init,
                controller);
        assertEquals(state.getEvent().getMessage(), out[0].getMessage());
        assertEquals(state.getEvent().getClass(), out[0].getClass());
        assertTrue(out[1] instanceof ContinueEvent);
        assertEquals(SOMETHING, out[1].getMessage());

    }

}
