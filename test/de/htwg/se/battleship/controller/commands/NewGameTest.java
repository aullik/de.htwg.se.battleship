package de.htwg.se.battleship.controller.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.INTInputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.input.InitGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

public class NewGameTest {

    private static final String   SOMETHING = "sdfasdflScjDpoDneEdrsdGSternkdv";
    private static final String[] EMPTY     = new String[] { "", "", "" };
    private InputController       controller;
    private INTCommands           command;
    private Event[]               out;
    private INTInputState         init;
    private String[]              in;

    @Before
    public void setup() {
        controller = new InputController();
        command = new NewGame();
        init = new InitGame();
        in = new String[] { "", "", SOMETHING };

    }

    @Test
    public void actiontest() {
        out = command.action(EMPTY, null, controller);
        assertTrue(out[0] instanceof StandardEvent);
        assertEquals(init.getEvent().getMessage(), out[0].getMessage());
        assertNull(out[1]);
        out = command.action(in, null, controller);
        assertTrue(out[0] instanceof StandardEvent);
        assertEquals(init.getEvent().getMessage(), out[0].getMessage());
        assertTrue(out[1] instanceof ContinueEvent);
        assertEquals(SOMETHING, out[1].getMessage());

    }

}
