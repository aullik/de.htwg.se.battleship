package de.htwg.se.battleship.controller.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.input.InitGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

public class RenamePlayerTest {

    private static final int    PLAYER1     = 1;
    private static final int    PLAYER2     = 2;
    private static final String NEW_NAME    = "what ever";
    private static final String PRE_COMMAND = "-p";
    private static final String PRE_DESC    = "set name of ";
    private static final String POST_DESC   = " to argument";
    private static final String SOMETHING   = "sdfasdflScjDpoDneEdrsdGSternkdv";
    private static final String CHANGE      = " changed to ";
    private static final String ERROR       = "couldn't rename ";
    private InputController     controller;
    private INTCommands         command1;
    private INTCommands         command2;
    private Event[]             out;
    private InitGame            init;
    private String[]            in1;
    private String[]            in2;

    @Before
    public void setup() {
        controller = new InputController();
        init = new InitGame();

        command1 = new RenamePlayer(PLAYER1, init);
        command2 = new RenamePlayer(PLAYER2, init);
        in1 = new String[] { "", "", NEW_NAME };
        in2 = new String[] { "", "",
                NEW_NAME + command1.getCommand() + SOMETHING };

    }

    @Test
    public void getCommandTest() {
        assertEquals(PRE_COMMAND + PLAYER1, command1.getCommand());
        assertEquals(PRE_COMMAND + PLAYER2, command2.getCommand());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals(PRE_DESC + init.getPlayerName(PLAYER1) + POST_DESC,
                command1.getDescription());
        assertEquals(PRE_DESC + init.getPlayerName(PLAYER2) + POST_DESC,
                command2.getDescription());
    }

    @Test
    public void actionTest() {

        String oldname1 = init.getPlayerName(PLAYER1);
        String oldname2 = init.getPlayerName(PLAYER2);
        out = command1.action(in1, init, controller);
        assertEquals(oldname1 + CHANGE + NEW_NAME, out[0].getMessage());
        assertTrue(out[0] instanceof StandardEvent);
        assertNull(out[1]);

        out = command2.action(in2, init, controller);
        assertEquals(oldname2 + CHANGE + NEW_NAME, out[0].getMessage());
        assertTrue(out[0] instanceof StandardEvent);

        assertTrue(out[1] instanceof ContinueEvent);
        assertEquals(command1.getCommand() + SOMETHING, out[1].getMessage());
        in1 = new String[] { "", "", "" };
        out = command1.action(in1, init, controller);
        assertEquals(ERROR + init.getPlayerName(PLAYER1), out[0].getMessage());
        assertTrue(out[0] instanceof ErrorEvent);
        assertNull(out[1]);
    }

}
