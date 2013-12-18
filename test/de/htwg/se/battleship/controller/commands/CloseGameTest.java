package de.htwg.se.battleship.controller.commands;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.CloseEvent;

public class CloseGameTest {

    private INTCommands           command;
    private static final String[] EMPTY = new String[] { "", "", "" };
    private Event[]               out;

    @Before
    public void setup() {
        command = new CloseGame();
    }

    @Test
    public void actiontest() {
        out = command.action(EMPTY, null, null);
        assertTrue(out[0] instanceof CloseEvent);
        assertNull(out[1]);

    }
}
